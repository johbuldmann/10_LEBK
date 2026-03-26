<%*
// API Keys einlesen aus separater Datei
const secretsFile = app.vault.getAbstractFileByPath("99 Archiv/15 Templates/secrets.md");

if (!secretsFile) {
    new Notice("❌ Secrets.md wurde unter dem Pfad nicht gefunden!");
    return;
}

// 2. Metadaten (Frontmatter) auslesen
const cache = app.metadataCache.getFileCache(secretsFile);

if (!cache || !cache.frontmatter || !cache.frontmatter.gemini_api_key) {
    new Notice("❌ API Key in Secrets.md fehlt oder Frontmatter ist ungültig.");
    return;
}

const API_KEY = cache.frontmatter.gemini_api_key;
console.log("Key erfolgreich geladen.");

// Hilfsfunktion zum sicheren Einlesen
async function readFileSafe(path) {
    const file = app.vault.getAbstractFileByPath(path);
    if (!file) {
        new Notice("Datei nicht gefunden: " + path);
        console.error("Fehler: Datei existiert nicht unter " + path);
        return ""; // Gibt leeren String zurück statt abzustürzen
    }
    return await app.vault.read(file);
}

// Hier die korrigierten Pfade (Prüfe die Schreibweise!)
const instruction = await readFileSafe("99 Archiv/20 Gemini/Instruktionen.md");

// Laut deinem Kommentar liegt KW44 im Jahr 2025 Ordner!
const example1 = await readFileSafe("50 Ausbildung/Ausbildungsnachweise/2025/Ausbildungsnachweis KW44.md");
const example2 = await readFileSafe("50 Ausbildung/Ausbildungsnachweise/2026/Ausbildungsnachweis KW10.md");
const example3 = await readFileSafe("50 Ausbildung/Ausbildungsnachweise/2026/Ausbildungsnachweis KW12.md");

// Jetzt kannst du prüfen, ob alles da ist
if (instruction === "" || example1 === "") {
    tR += "⚠️ Fehler beim Laden der Quelldateien. Siehe Notices oben rechts.";
} else {
    // Hier geht dein restlicher Prompt-Code weiter...
    tR += "Dateien erfolgreich geladen!";
}

// 1. Benutzereingabe für die KW
const kwInput = await tp.system.prompt("Welche Kalenderwoche (KW) soll verarbeitet werden?", tp.date.now("WW"));

if (!kwInput) {
    new Notice("Abgebrochen: Keine KW eingegeben.");
    return;
}

// 2. Berechnung der Daten (Montag bis Freitag)
const year = new Date().getFullYear();
// ISO-Wochen-Logik: 4. Januar ist immer in KW 1
const firstThursday = new Date(year, 0, 4);
const firstMonday = firstThursday;
firstMonday.setDate(firstThursday.getDate() - ((firstThursday.getDay() + 6) % 7));

// Ziel-Montag berechnen: Montag KW1 + (Eingegebene KW - 1) * 7 Tage
const targetMonday = new Date(firstMonday);
targetMonday.setDate(firstMonday.getDate() + (parseInt(kwInput) - 1) * 7 + 1);

// Liste der 5 Arbeitstage (YYYY-MM-DD) erstellen
const weekDates = [];
for (let i = 0; i < 5; i++) {
    const d = new Date(targetMonday);
    d.setDate(targetMonday.getDate() + i);
    // Formatierung zu YYYY-MM-DD
    const dateStr = d.toISOString().split('T')[0];
    weekDates.push(dateStr);
}

// Test-Ausgabe in der Obsidian-Konsole (STRG+SHIFT+I zum Ansehen)
console.log("Gesuchte Daten für KW " + kwInput + ":", weekDates);
new Notice("Suche gestartet für KW " + kwInput);

// 2. Der Scanner ---

const dailyFolder = "90 Journal Daily Notes";
const schoolFolder = "30 Berufsschule";

let foundDailyNotes = [];
let foundSchoolNotes = [];

// Alle Markdown-Dateien im Vault holen
const allFiles = app.vault.getMarkdownFiles();

for (const file of allFiles) {
    // Prüfen, ob das Datum im Dateinamen vorkommt
    const matchesDate = weekDates.some(date => file.name.includes(date));

    if (matchesDate) {
        // Prüfen, ob die Datei im Daily-Ordner liegt
        if (file.path.startsWith(dailyFolder)) {
            foundDailyNotes.push(file);
        }
        // Prüfen, ob die Datei im Schul-Ordner (oder Unterordnern) liegt
        else if (file.path.startsWith(schoolFolder)) {
            foundSchoolNotes.push(file);
        }
    }
}

// Sortierung (Optional, damit die Tage chronologisch sind)
foundDailyNotes.sort((a, b) => a.name.localeCompare(b.name));
foundSchoolNotes.sort((a, b) => a.name.localeCompare(b.name));

console.log("Gefundene Dailies:", foundDailyNotes.map(f => f.name));
console.log("Gefundene Schule:", foundSchoolNotes.map(f => f.name));

if (foundDailyNotes.length === 0 && foundSchoolNotes.length === 0) {
    new Notice("Keine Notizen für KW " + kwInput + " gefunden.");
    return;
}

// 3. Einlesen, Sanitize & Prompt-Bau ---

// Hilfsfunktion: Macht den Text sicher für den JS-Template-String
const sanitize = (text) => {
    if (!text) return "";
    return text
        .replace(/\\/g, "\\\\") // Backslashes verdoppeln
        .replace(/`/g, "\\`")   // Backticks escapen
        .replace(/\$/g, "\\$");  // Dollarzeichen escapen
};

// 1. Daily Notes einlesen und säubern
let dailyContent = "";
for (const file of foundDailyNotes) {
    let content = await app.vault.read(file);

    // Wir splitten IMMER mit einem flexiblen Regex:
    // /#+\s*privat/i fängt: # privat, ## Privat, #### privat, etc. ab.
    const parts = content.split(/#+\s*privat/i);
    content = parts[0]; // Wir nehmen nur alles VOR dem ersten Treffer

    dailyContent += `\n--- DATEI: ${file.name} ---\n${content.trim()}\n`;
}

// 2. Schulnotizen einlesen und säubern
let schoolContent = "";
for (const file of foundSchoolNotes) {
    let content = await app.vault.read(file);

    // Gleiche Logik wie oben
    const parts = content.split(/#+\s*privat/i);
    content = parts[0];

    schoolContent += `\n--- DATEI: ${file.name} ---\n${content.trim()}\n`;
}


// 3. Den Prompt final zusammensetzen (Deine Variablen von vorhin nutzen)
// Hinweis: 'instruction' und 'examples' müssen im Skript definiert sein (siehe unten)
const finalPrompt = `
<instructions>
${sanitize(instruction)}
</instructions>

<examples>
${sanitize(example1)}
${sanitize(example2)}
${sanitize(example3)}
</examples>

<input_data>
<daily_notes>
${sanitize(dailyContent)}
</daily_notes>

<school_notes>
${sanitize(schoolContent)}
</school_notes>
</input_data>

ERSTELLE JETZT DEN BERICHT FÜR KW ${kwInput}:
`;

console.log("Prompt wurde generiert und gesäubert.")

tR += finalPrompt;

// 2. Dateinamen und Pfad bestimmen
const baseFileName = `Ausbildungsnachweis KW${kwInput}`;
const currentFolder = tp.file.folder(true); // Der Ordner, in dem das Template aufgerufen wurde
let finalFileName = baseFileName;
let fileCount = 1;

// 3. Prüfen, ob Datei existiert (Postfix-Logik)
while (app.vault.getAbstractFileByPath(`${currentFolder}/${finalFileName}.md`)) {
    finalFileName = `${baseFileName}_${fileCount}`;
    if (tp.file.title === finalFileName) break;

    fileCount++;
    finalFileName = `${baseFileName}_${fileCount}`;
}

await tp.file.rename(finalFileName);
new Notice(`Erfolg! ${finalFileName}.md wurde erstellt.`);
%>

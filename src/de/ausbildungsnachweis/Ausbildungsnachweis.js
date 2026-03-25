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


// hier die Instruktionen
const instruction = `
Rolle: Du bist ein spezialisierter Schreib-Assistent für einen Fachinformatiker in der Ausbildung. 

Aufgabe: Erstelle aus unstrukturierten Daily Notes und Schulnotizen einen formell korrekten "Ausbildungsnachweis" für die IHK.

Regeln:
1. SPRACHE: Nutze eine professionelle, sachliche IT-Fachsprache (z.B. "Implementierung", "Konfiguration", "Refactoring").
2. FORMAT: Halte dich EXAKT an die Markdown-Struktur der Beispiele. Keine Einleitung, kein "Hier ist dein Bericht". Starte direkt mit #ausbildungsnachweis.
3. INHALT: Fasse zusammenhängende Aufgaben sinnvoll zusammen. Wandle "Ich-Sätze" in sachliche Stichpunkte um.
4. VOLLSTÄNDIGKEIT: Alle Abschnitte (Abteilung, Tätigkeiten, Schulungen, Berufsschule) müssen vorhanden sein. Wenn eine Sektion im Input fehlt, schreibe "–".
5. DETAILGRAD: Übernimm Fachbegriffe (z.B. JDBC, Singleton, MIME-Types, Cisco Packet Tracer) präzise.
**6. OUTPUT-KONTROLLE:** Gib absolut keinen Text vor oder nach dem Markdown-Block aus. Die erste Zeile deiner Antwort MUSS #ausbildungsnachweis sein. 
`;

const examples = `
Beispiel 1:
#ausbildungsnachweis

#### Ausbildungsabschnitt / Ausbildungsabteilung:
JavaScript


#### Betriebliche Tätigkeiten:
- Tech Training Javascript: 
	- Games of Life: 
		- Implementierung von Funktionen wie: getNeighborCount, createNextGeneration, updateWord etc.
		- Zusätzliche Funktionen wie Generationenanzeige und Presets.
 
- Selbständige Arbeit an Java Lehrbuch:
	- Kapitel 18: Datenstrukturen und Algorithmen: List Interface, ArrayList, Sets

- Übungsaufgaben zu Datenstrukturen und Algorithmen.
#### Unterweisungen, betrieblicher Unterricht, sonstige Schulungen:
- Code Review: JavaScript Spiel und  Implementierungen 
- Jahresgespräch: Rückblick auf die ersten drei Monate und Vorblick auf das nächste Jahr und Zieldefinierung. 

#### Berufsschule (Unterrichtsthemen):
- EVP: Konfiguration PC Systeme für Arbeitsplätze nach Anforderungen
- GID: Thema Personalwirtschaft: Recruiting
- Englisch: Präsentation von Firmen vorbereiten
- Projektmanagement: Abschluss der Definitionsphase – Lasten- und Pflichtenheft
- STDM: Wiederholung Java-Konventionen und Fortführung Programmieraufgaben 


Beispiel 2:
#ausbildungsnachweis

#### Ausbildungsabschnitt / Ausbildungsabteilung:
Java Grundlagen


#### Betriebliche Tätigkeiten:
- Bearbeitung Übungsaufgabe AddressBook: Einlesen und aktualisieren der Listen überarbeitet, Service Layer implementiert, GUI Layer Methoden implementiert entsprechend der Use Cases. 

#### Unterweisungen, betrieblicher Unterricht, sonstige Schulungen:
- Besprechung Übungsaufgabe Adressenverwaltung

#### Berufsschule (Unterrichtsthemen):
- EVP: Cisco Kurs Netzwerk; Nachschreiben von EVP Klausur
- GID: Beitragsbemessungsgrenzen Sozialversicherungen; Arbeitszeugnisse
- Englisch: Notenbesprechung
- Politik und Gesellschaft: Referat Vorbereitung zu Arbeitszeitgesetz.
- Projektmanagement: Gant-Diagramm und Netzplan
- STDM: Übungen Struktogramm und Vorbesprechung Klausur. 


Beispiel 3:
#ausbildungsnachweis

#### Ausbildungsabschnitt / Ausbildungsabteilung:
REST API und Grundlagen JPA


#### Betriebliche Tätigkeiten:
- Implementierung REST APIs mit manuellem Schreiben und Parsen von .json Dateien. Ansprechen und Auslesen dieser Apis auf Website für die Use Cases in dem AddressBook Projekt.
- Tutorial zu JPA und Hibernate: Durcharbeiten und Übungen ausprobieren mit JPA/Hibernate

#### Unterweisungen, betrieblicher Unterricht, sonstige Schulungen:
- Review: REST APIs und Einstieg in JPA

#### Berufsschule (Unterrichtsthemen):
- EVP: Übungen mit Cisco Packet Tracer
- GID: Projektauftrag: Unternehmensgründung 
- Projektmanagement: Durchführungsphase
- Politik und Gesellschaft: Präsentation Elternzeit und -geld.
- Englisch: Übungen
- STDM: Diagramme: ER Diagramme und SQL-Übungen
`;

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
    // Alles nach #### privat abschneiden
    if (content.includes("#### privat")) {
        content = content.split(/#+ privat/i)[0];
    }
    dailyContent += `\n--- DATEI: ${file.name} ---\n${content}\n`;
}

// 2. Schulnotizen einlesen und säubern
let schoolContent = "";
for (const file of foundSchoolNotes) {
    let content = await app.vault.read(file);
    // Alles nach #### privat abschneiden
    if (content.includes("#### privat")) {
        content = content.split(/#+ privat/i)[0];
    }
    schoolContent += `\n--- DATEI: ${file.name} ---\n${content}\n`;
}

// 3. Den Prompt final zusammensetzen (Deine Variablen von vorhin nutzen)
// Hinweis: 'instruction' und 'examples' müssen im Skript definiert sein (siehe unten)
const finalPrompt = `
<instructions>
${sanitize(instruction)}
</instructions>

<examples>
${sanitize(examples)}
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

// 4. KI-Anfrage & Dateierstellung ---

// 1. KI-Anfrage senden (AIT Plugin Integration)
new Notice("Sende Daten an Gemini... Bitte warten.");
const url = `https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent?key=${API_KEY}`;

const payload = {
    contents: [{
        parts: [{
            text: finalPrompt
        }]
    }]
};

try {
    const response = await requestUrl({
        url: url,
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
    });

    if (response.status === 200) {
        // Extraktion der Antwort aus der Google-Struktur
        const aiText = response.json.candidates[0].content.parts[0].text;
        tR += aiText;

        // new Notice("Bericht erfolgreich generiert.")
    } else {
        const errorMsg = response.json.error?.message || "Unbekannter Fehler";
        tR += `⚠️ Fehler ${response.status}: ${errorMsg}`;
    }
} catch (error) {
    tR += "❌ Request fehlgeschlagen. Prüfe die Konsole. (Ctrl Shift i)";
    console.error("Gemini-Fehler Details:", error);
}

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

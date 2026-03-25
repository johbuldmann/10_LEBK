<%*
// --- KONFIGURATION: Deine Schulfächer hier anpassen ---
const noteTemplate = {
    "Monday": [
        "# EVP",
        "# GID",
        "# Englisch"
    ],
    "Wednesday": [
        "# STDM",
        "# PG",
        "# Projektmanagement",
        "# EVP",
        "# GID"
    ],
    "Default": [
        "# Berufsschule"
    ]
};

// --- LOGIK ---
const datum = tp.date.now("YYYY-MM-DD");
console.log(datum);
const weekday = tp.date.now("dddd");
console.log(weekday);
const dateiName = `${datum} Berufsschule`;
console.log(dateiName);

// Den passenden Plan wählen (oder Standard, falls es weder Mo noch Mi ist)
const courses = noteTemplate[weekday] || noteTemplate["Default"];

// Notizen zusammenfügen mit einigen Leerzeilen..
const inhalt = courses.join("\n\n\n\n");

// Neuesten Ordner in "30 Berufsschule" finden
const hauptOrdner = "30 Berufsschule";
const ordnerListe = app.vault.getAbstractFileByPath(hauptOrdner).children
    .filter(f => f instanceof tp.obsidian.TFolder)
    .map(f => f.path)
    .sort()
    .reverse();

const zielOrdner = ordnerListe[0] || hauptOrdner;
const pfad = `${zielOrdner}/${dateiName}.md`;


// await tp.file.create_new(inhalt, dateiName, true, app.vault.getAbstractFileByPath(zielOrdner));

// Datei erstellen (der dritte Parameter 'false' verhindert das automatische Öffnen im aktuellen Tab)
const neueDatei = await tp.file.create_new(
    inhalt,
    dateiName,
    false,
    app.vault.getAbstractFileByPath(zielOrdner)
);

// Die Datei in einem NEUEN Tab (Leaf) öffnen
const leaf = app.workspace.getLeaf('tab');
await leaf.openFile(neueDatei);
%>

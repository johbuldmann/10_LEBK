#dailynote
<%*
// --- BASIS DATEN & NAVIGATION ---
let dateStr = tp.file.title.substring(0, 10);
const format = "YYYY-MM-DD dddd - [Daily Note]";
const folder = "90 Journal Daily Notes";

let m = moment(dateStr, "YYYY-MM-DD");
const gesternName = m.clone().subtract(1, 'days').format(format);
const morgenName = m.clone().add(1, 'days').format(format);

tR += `[[${folder}/${gesternName}|gestern]] | [[${folder}/${morgenName}|morgen]]\n`;

let wochentag = m.format("dddd");
if (wochentag === "Monday" || wochentag === "Wednesday") {
    tR += `\n[[${dateStr} Berufsschule]]`;
}

// --- BACHELORARBEIT LOGIK ---
const start = moment("2026-03-31");
const ziel = moment("2026-05-26"); // 8 Wochen ab Anmeldung
const heute = moment(); // Für den Countdown nutzen wir das echte "Jetzt"

if (heute.isBefore(ziel)) {
    // 1. Zeitberechnung
    let diff = ziel.diff(heute);
    let duration = moment.duration(diff);

    let tage = Math.floor(duration.asDays());
    let stunden = duration.hours();
    let minuten = duration.minutes();

    // 2. Prozentberechnung
    let gesamtZeit = ziel.diff(start);
    let abgelaufeneZeit = heute.diff(start);
    let prozent = Math.min(100, Math.max(0, (abgelaufeneZeit / gesamtZeit) * 100)).toFixed(1);

    // 3. Progress Bar Design (HTML/CSS für Obsidian)
    let barWidth = 20; // Anzahl der Segmente
    let filled = Math.round((prozent / 100) * barWidth);
    let bar = "█".repeat(filled) + "░".repeat(barWidth - filled);

    // 4. Output Generierung
    var bachelorBlock = `\n\n#### 🎓 Bachelorarbeit\n`;
    // bachelorBlock += `> **Status:** ${prozent}% abgeschlossen\n`;
    bachelorBlock += `> \`${bar}\` ${prozent}\%\n`;
    bachelorBlock += `> **Countdown:** noch ${tage} Tage, ${stunden}h und ${minuten}m bis zur Abgabe.\n`;
    bachelorBlock += `> *Deadline: ${ziel.format("DD. MMMM YYYY")}*\n`;
} else {
    var bachelorBlock = `\n---\n### 🎓 Bachelorarbeit\n> 🎉 **Abgabefrist erreicht!** Hoffentlich ist alles abgegeben!`;
}
%>

#### Fokus für heute
- [ ]

#### Aufgaben (To-Do)
- [ ]




---
#### privat


<%- bachelorBlock %>
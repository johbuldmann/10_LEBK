#dailynote
<%*
let dateStr = tp.file.title.substring(0, 10);
// console.log(dateStr);

// Wir definieren das exakte Format deiner Dateien
const format = "YYYY-MM-DD dddd - [Daily Note]";
const folder = "90 Journal Daily Notes"; // HIER deinen Ordnernamen eintragen (z.B. "Journal" oder "00 Daily")
// "C:\Users\JBuldmann\Dropbox\300 Obsidian Vault\Obsidian_Johannes\90 Journal Daily Notes"

let m = moment(dateStr, "YYYY-MM-DD");
// Wir berechnen die Links für gestern und morgen
const gesternName = m.clone().subtract(1, 'days').format(format);
const morgenName = m.clone().add(1, 'days').format(format);

// console.log(gesternName);
// console.log(morgenName);


// Wir bauen den Pfad zusammen: Ordner/Dateiname
const gesternPfad = `${folder}/${gesternName}`;
// console.log(gesternPfad);
const morgenPfad = `${folder}/${morgenName}`;
// console.log(morgenPfad);

// Wir schreiben die Links in die Notiz
tR += `[[${gesternPfad}|gestern]] | [[${morgenPfad}|morgen]]\n`;

// Kleiner Bonus: Wenn heute Berufsschule ist (Mo/Mi), füge einen Link ein
let wochentag = tp.date.now("dddd"); // Mo, Di, Mi...
if (wochentag === "Monday" || wochentag === "Wednesday") {
    tR += "[[ " + tp.date.now("YYYY-MM-DD") + " Berufsschule]]";
}
%>



#### Fokus für heute
- [ ]



#### Aufgaben (To-Do)
- [ ]








---
#### privat










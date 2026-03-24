-- 1.
select *
from Verkaeufer;

-- 2.
select Name, Strasse, PLZ, Ort
from Lieferanten;

-- 3.
select Name, Verkaufsgebiet, Provisionssatz
from Verkaeufer
ORDER BY Name;

-- 4.
select Verkaufsgebiet, Name
FROM Verkaeufer
Order by Verkaufsgebiet DESC, Name ASC;

-- 5.
select Name, Verkaufsgebiet, Provisionssatz
from Verkaeufer
where Verkaufsgebiet = 'Nord';

-- 6.
select Lieferantennr, Name, Lieferzeit
from Lieferanten
where Lieferzeit > 3;

-- 7.
select Verkaeufernr, Name, Verkaufsgebiet, Provisionssatz
from Verkaeufer
where Provisionssatz = 3.5 OR Provisionssatz = 10;

-- 8.
-- erstmal übersprungen
select Lieferantennr, Name, Lieferzeit, Lieferzeit + 2 AS 'Neue Lieferzeit'
from Lieferanten;


-- 9.
select Verkaeufernr, Name
from Verkaeufer
Where Name like 'H%';

-- 10.
select Lieferantennr, Name, Strasse, PLZ, Ort, Lieferzeit
from Lieferanten
Where PLZ like '6%';

-- 11. a
select Verkaeufernr, Name, Verkaufsgebiet, Provisionssatz
from Verkaeufer
where Verkaufsgebiet IN ('Ost', 'Süd');

-- 11. b
select Verkaeufernr, Name, Verkaufsgebiet, Provisionssatz
from Verkaeufer
where Verkaufsgebiet = 'Ost' OR Verkaufsgebiet = 'Süd';
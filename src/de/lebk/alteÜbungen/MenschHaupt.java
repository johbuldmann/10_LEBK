package de.lebk.alteÜbungen;

public class MenschHaupt {
    public static void main(String[] args) {
        Person objekt1 = new Person("Stefan Raab", 56, 1.81, 'm', 88.8, 1, 3);
        Person objekt2 = new Person("Angela Merkel", 69, 1.65, 'w', 83.5, 1, 0);
//        objekt1.sprechen();
//        objekt2.sprechen();

        // ========== Teil 2 der Aufgabe
        Person objekt3 = new Person("Stefan Raab", 56, 1.81);
        Person objekt4 = new Person("Angela Merkel", 69, 1.65);
        objekt3.sprechen("kurz");
        objekt4.sprechen("lang");

//        System.out.println(objekt4.name); // das geht jetzt nicht mehr, das name private ist.
        System.out.println(objekt4.getName());

        /* Überlegen Sie, warum es in unserem Beispiel keinen Sinn macht, für die Instanzvariablen
        bmi und familiengroesse Setter und Getter zu codieren.
        - diese Variablen sollen gar nicht von außen geändert (gelesen/geschrieben) werden, sondern diese sind nur für Berechnungen innerhalb der Person-Klasse relevant.
         */

        // ========= das ist von dem Teil 1 der Aufgabe:
//        // Raab erst abnehmen und dann zunehmen
//        objekt1.abnehmen(3.0);
//        objekt1.zunehmen(1.5);
//
//        // Merkel erst zunehmen und dann abnehmen
//        objekt2.zunehmen(1.2);
//        objekt2.abnehmen(2.6);
//
//
//        System.out.println("");
//        System.out.println("BMI von " + objekt1.name + " ist " + objekt1.bmi);
//        System.out.println("BMI von " + objekt2.name + " ist " + objekt2.bmi);
//
//        System.out.println();
//
//        System.out.println("Den nächsten runden Geburtstag feiert " + objekt1.name + " im Jahr " + objekt1.ermittelnJahreBisRunderGeburtstag());
//        System.out.println("Den nächsten runden Geburtstag feiert " + objekt2.name + " im Jahr " + objekt2.ermittelnJahreBisRunderGeburtstag());
//
//        System.out.println();
//
//        System.out.println("Familiengröße von " + objekt1.name + " ist: " +objekt1.familiengroesse);
//        System.out.println("Familiengröße von " + objekt2.name + " ist: " +objekt2.familiengroesse);
    }
}

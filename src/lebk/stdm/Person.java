package lebk.stdm;

import java.text.DecimalFormat;

public class Person {

    private String name;
    private int alter;
    public int familiengroesse;
    private double groesse;
    public char gender;
    public String pronomen;
    private double gewicht;

    public double bmi;

    static final int CURRENT_YEAR = 2025;

    // Konstruktor
    public Person(String name, int alter, double groesse, char gender, double gewicht, int partner, int kinder) {
//        this.name = name;
//        this.alter = alter;
//        this.groesse = groesse;
//        this.gewicht = gewicht;
        setName(name);
        setAlter(alter);
        setGroesse(groesse);
        setGewicht(gewicht);
        this.gender = gender;
        this.pronomen = ermittelPronomen(gender);
        this.bmi = errechnenBmi();
        this.familiengroesse = ermittelnFamiliengroesse(partner, kinder);
    }

    // zweiter Konstruktor:
    public Person(String name, int alter, double groesse) {
        setName(name);
        setAlter(alter);
        setGroesse(groesse);
    }

    // Hilfsmethode für die den Konstruktor:
    public String ermittelPronomen(char gender) {
        if (gender == 'w') {
            return "Sie";
        } else if (gender == 'm') {
            return "Er";
        } else {
            return "Es";
        }
    }

    // Setter und
    public void setName(String name) {
        this.name = name;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }

    public void setGroesse(double groesse) {
        this.groesse = groesse;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    // Getter
    public String getName() {
        return name;
    }

    public int getAlter() {
        return alter;
    }

    public double getGroesse() {
        return groesse;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void sprechen() {
        System.out.println(name + " ist " + alter + " Jahre alt.");
        System.out.println(pronomen + " ist " + groesse + " Meter groß \n");

    }

    public void sprechen(String laengeDerRede) {
        /*Guten Tag! Mein Name ist Angela Merkel.
Mein Alter beträgt 69 Jahre
und meine Größe in Metern ist 1.65.
Ich heiße Stefan Raab und bin 56 Jahre alt und 1.81 cm groß.

         */
        if (laengeDerRede.equals("kurz")) {
            System.out.println("Ich heiße " + name + " und bin " + alter + " Jahre alt und " + groesse + " m groß.\n");
        } else if (laengeDerRede.equals("lang")) {
            System.out.println("Guten Tag! Mein Name ist " + name + ".\n"
                    + "Mein Alter beträgt " + alter + " Jahre.\n"
                    + "und meine Größe in Metern ist " + groesse + ".\n");
        }
    }

    public void zunehmen(double gewichtszunahme) {
        System.out.println(name + " hat " + gewicht + " Kilogramm gewogen");
        gewicht += gewichtszunahme;
        System.out.println(pronomen + " hat " + gewichtszunahme + " zugenommen.");
        System.out.println(pronomen + " wiegt jetzt " + gewicht + " Kilogramm\n");
    }

    public void abnehmen(double gewichtsabnahme) {
        System.out.println(name + " hat " + gewicht + " Kilogramm gewogen");
        gewicht += gewichtsabnahme;
        System.out.println(pronomen + " hat " + gewichtsabnahme + " abgenommen.");
        System.out.println(pronomen + " wiegt jetzt " + gewicht + " Kilogramm\n");
    }

    public double errechnenBmi() {
        double bmi = gewicht / (groesse * groesse);
        bmi = Math.round(bmi * 100.0) / 100.0;
        return bmi;
    }

    public int ermittelnJahreBisRunderGeburtstag() {
        int naechsterRunderGeb = CURRENT_YEAR + 10 - this.alter % 10;
        return naechsterRunderGeb;
    }

    public int ermittelnFamiliengroesse(int partner, int kinder) {
        return 1 + partner + kinder;
    }

}

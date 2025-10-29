import java.text.DecimalFormat;

public class Person {

    public String name;
    public int alter;
    public int familiengroesse;
    public double groesse;
    public char gender;
    public double gewicht;

    public double bmi;

    static final int CURRENT_YEAR = 2025;

    // Konstruktor
    public Person(String meinName, int meinAlter, double meineGroesse, char meinGender, double meinGewicht, int partner, int kinder) {
        name = meinName;
        alter = meinAlter;
        groesse = meineGroesse;
        gender = meinGender;
        gewicht = meinGewicht;
        this.bmi = errechnenBmi();
        this.familiengroesse = ermittelnFamiliengroesse(partner, kinder);
    }

    public void sprechen() {
        if (gender == 'w') {
            System.out.println("Frau " + name + " ist " + alter + " Jahre alt.");
            System.out.println("Sie ist " + groesse + " Meter groß \n");
        } else if (gender == 'm') {
            System.out.println("Herr " + name + " ist " + alter + " Jahre alt.");
            System.out.println("Er ist " + groesse + " Meter groß \n");
        } else {
            System.out.println("Geschlecht leider nicht angegeben.\n");
            System.out.print("test");
        }

    }

    public void zunehmen(double gewichtszunahme) {
        System.out.println(name + " hat " + gewicht + " Kilogramm gewogen");
        gewicht += gewichtszunahme;
        System.out.println("Er hat " + gewichtszunahme + " zugenommen.");
        System.out.println("Er wiegt jetzt " + gewicht + " Kilogramm\n");
    }

    public void abnehmen(double gewichtabnahme) {
        System.out.println(name + " hat " + gewicht + " Kilogramm gewogen");
        gewicht += gewichtabnahme;
        System.out.println("Er hat " + gewichtabnahme + " abgenommen.");
        System.out.println("Er wiegt jetzt " + gewicht + " Kilogramm\n");
    }

    public double errechnenBmi() {
        double bmi = gewicht / (groesse * groesse);

        bmi = Math.round(bmi * 100.0) / 100.0;

        return bmi;
    }

    public int ermittelnJahreBisRunderGeburtstag() {
        int naechsterRunderGeb;
        naechsterRunderGeb = CURRENT_YEAR + 10 - this.alter % 10;
        return naechsterRunderGeb;
    }

    public int ermittelnFamiliengroesse(int partner, int kinder) {
        return 1 + partner + kinder;
    }

}

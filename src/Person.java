public class Person {

    public String name;
    public int alter;
    public double groesse;
    public char gender;
    public double gewicht;

    // Konstruktor
    public Person(String meinName, int meinAlter, double meineGroesse, char meinGender, double meinGewicht) {
        name = meinName;
        alter = meinAlter;
        groesse = meineGroesse;
        gender = meinGender;
        gewicht = meinGewicht;
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
        return gewicht / (groesse * groesse);
    }


}

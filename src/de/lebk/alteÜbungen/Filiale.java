package de.lebk.alteÜbungen;

import java.util.Comparator;

public class Filiale {

    private String fName;
    public double quartal1;
    public double quartal2;
    public double quartal3;
    public double quartal4;

    public Filiale(String fName, double quartal1, double quartal2, double quartal3, double quartal4) {
        this.fName = fName;
        this.quartal1 = quartal1;
        this.quartal2 = quartal2;
        this.quartal3 = quartal3;
        this.quartal4 = quartal4;
    }

    public double berechnenJahresumsatz() {
        return quartal1 + quartal2 + quartal3 + quartal4;
    }

    public String getName () {
        return fName;
    }

    public double ermittelnMinimum() {
        double minimum = quartal1;
        if (quartal2 < minimum) minimum = quartal2;
        if (quartal3 < minimum) minimum = quartal3;
        if (quartal4 < minimum) minimum = quartal4;
        return minimum;
    }

    public double ermittelnMaximum() {
        double maximum = quartal1;
        if (quartal2 > maximum) maximum = quartal2;
        if (quartal3 > maximum) maximum = quartal3;
        if (quartal4 > maximum) maximum = quartal4;
        return maximum;
    }

    public double berechnenDurchschnitt() {
        return berechnenJahresumsatz() / 4;
    }

    public void aendernQuartalsUmsatz(int q, double newsum) {
        if (q == 1) {
            quartal1 = newsum;
        }
        if (q == 2) {
            quartal2 = newsum;
        }
        if (q == 3) {
            quartal3 = newsum;
        }
        if (q == 4) {
            quartal4 = newsum;
        }
    }

    public static void ermittelnRangfolge(Filiale a, Filiale b, Filiale c) {

        Filiale[] filialen = {a, b, c};

//        java.util.Arrays.sort(filialen, Comparator.comparing(x -> x.berechnenJahresumsatz()));
        java.util.Arrays.sort(filialen, Comparator.comparingDouble(Filiale::berechnenJahresumsatz));

//        java.util.Arrays.sort(filialen, (f1, f2) ->
//                Double.compare(f1.berechnenJahresumsatz(), f2.berechnenJahresumsatz()));
        System.out.printf("Rangfolge:\n" +
                "Kleinste Filiale: %s %.2f Euro\n" +
                "Mittlere Filiale: %s %.2f Euro\n" +
                "Größte Filiale: %s %.2f Euro\n",
                filialen[0].getName(), filialen[0].berechnenJahresumsatz(),
                filialen[1].getName(), filialen[1].berechnenJahresumsatz(),
                filialen[2].getName(), filialen[2].berechnenJahresumsatz());

    }

    public String toString() {
        String output =String.format("Die Filiale %s hat einen Jahresumsatz von %.2f Euro.\n" +
                        "Der höchste Quartalsumsatz war %.2f Euro.\n" +
                        "Der niedrigste Quartalsumsatz war %.2f Euro.\n" +
                        "Der durchschnittliche Quartalsumsatz beträgt %.2f Euro.\n",
                fName,
                berechnenJahresumsatz(),
                ermittelnMaximum(),
                ermittelnMinimum(),
                berechnenDurchschnitt()
                );
        return output;
    }
}

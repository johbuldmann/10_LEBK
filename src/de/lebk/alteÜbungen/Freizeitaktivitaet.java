package de.lebk.alteÜbungen;

public class Freizeitaktivitaet {
    double preisOhneRabatt;
    int anzahl;
    String ferienwohnung;

    public Freizeitaktivitaet(String ferienwohnung, int anzahl, double preisOhneRabatt) {
        this.ferienwohnung = ferienwohnung;
        this.anzahl = anzahl;
        this.preisOhneRabatt = preisOhneRabatt;
    }

//    public double ermittelnRabattsatz() {
//        boolean dritteUndweitereBuchung = (this.anzahl > 2);
//        boolean preisDerFreizeitaktivitaet = (this.preisOhneRabatt > 100.0);
//        boolean gebuchtZusammenhangMitFerienwohnung = false; // was genau wird hier überprüft?
//
//        double rabatt0 = 0.0;
//        double rabatt5 = 0.05;
//        double rabatt10 = 0.10;
//        double rabatt20 = 0.20;
//
//        // R8 -> keine Bedingung wahr
//        if (!dritteUndweitereBuchung && !preisDerFreizeitaktivitaet && !gebuchtZusammenhangMitFerienwohnung) {
//            return rabatt0;
//        }
//    }

}

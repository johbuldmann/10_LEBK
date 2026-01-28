package lebk.stdm;

public class Stellplatz {
    private String nummerStellplatz;
    private Artikel artikel;
    private double maxGewicht;

    public Stellplatz(String nummer, Artikel artikel, double maxGewicht) {
        this.nummerStellplatz = nummer;
        this.artikel = artikel;
        this.maxGewicht = maxGewicht;
    }

    // das ist ein Testfall

    public String ausgeben() {
        return "Auf Platz " + this.nummerStellplatz + " stehen " + artikel.gesamtgewicht() + " kg " + artikel.artikelbezeichnung;
    }

    public void pruefenGewicht() {
        if (maxGewicht < artikel.gesamtgewicht()) {
            System.out.println("Die Palette mit " + artikel.artikelbezeichnung + " ist überladen");
        } else if (maxGewicht > artikel.gesamtgewicht()) {
            System.out.println("Die Palette mit " + artikel.artikelbezeichnung + " ist noch nicht voll");
        } else {
            System.out.println("Die Palette mit " + artikel.artikelbezeichnung + " ist passend voll");
        }
    }

    public void umlagern(Artikel artikel) {
        this.artikel = artikel;
    }
}

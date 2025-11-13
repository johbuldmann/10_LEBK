package lebk.stdm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Artikel {

    public String artikelbezeichnung;
    public int bestand;
    public double nettopreis;
    public double kgProSck;
    public int steuersatz;
//    private DecimalFormat df;
    private NumberFormat cf = NumberFormat.getCurrencyInstance(Locale.GERMANY);

    // Konstruktor
    public Artikel(String artikelbezeichnung, int bestand, double nettopreis, double kgProSck, int steuersatz) {
        this.artikelbezeichnung = artikelbezeichnung;
        this.bestand = bestand;
        this.nettopreis = nettopreis;
        this.kgProSck = kgProSck;
        this.steuersatz = steuersatz;
//        this.df = new DecimalFormat("#,##0.00 €");    // Euro Format
    }


    public void veraendernBestand(int liefermenge) {
        this.bestand += liefermenge;
        ausgebenInformationen(liefermenge);
    }

    public void ausgebenInformationen(int liefermenge) {
        double stueckPreisBrutto = holenBruttopreis();
        double gesamtWert = berechnePreisPosition(liefermenge);
        System.out.println("Die Lieferung des Artikels \"" + artikelbezeichnung + "\" hat eine Menge von " + liefermenge + " Stück.");
        System.out.println("Mit einem Stückpreis von " + cf.format(stueckPreisBrutto) + " und einem Gesamtwert von " + cf.format(gesamtWert) + ".");
    }

    public double holenBruttopreis() {
        double bruttopreis = this.nettopreis * (1 + (double) steuersatz / 100);

        BigDecimal bd = new BigDecimal(bruttopreis);
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        return bd.doubleValue();

    }

    public double berechnePreisPosition(int liefermenge) {
        double bruttopreis = this.holenBruttopreis();
        return bruttopreis * liefermenge;
    }
}


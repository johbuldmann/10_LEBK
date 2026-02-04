package de.lebk.artikel.stellplatz;

import java.util.Arrays;

public class MainArtikel {

    public static void main(String[] args) {

        Artikel ungarischeSalami = new Artikel("Ungarische Salami", 250, 9.9, 1, 7);
        Artikel pragerSchicken = new Artikel("Prager Schicken", 100, 8.5, 2.0, 7);
        Artikel argentinischesSteak = new Artikel("Argentinisches Steak", 600, 15.0, 0.25, 7);

        Stellplatz platz1 = new Stellplatz("P101", ungarischeSalami, 200.0);
        Stellplatz platz2 = new Stellplatz("P102", pragerSchicken, 240.0);
        Stellplatz platz3 = new Stellplatz("P103", argentinischesSteak, 250.0);

        ungarischeSalami.veraendernBestand(50);
        pragerSchicken.veraendernBestand(20);
        argentinischesSteak.veraendernBestand(100);

        platz1.pruefenGewicht();
        platz2.pruefenGewicht();
        platz3.pruefenGewicht();

        System.out.println();   // Leerzeile

        System.out.println(platz1.ausgeben());
        System.out.println(platz2.ausgeben());
        System.out.println(platz3.ausgeben());

        platz1.umlagern(argentinischesSteak);
        platz2.umlagern(ungarischeSalami);
        platz3.umlagern(pragerSchicken);

        System.out.println();   // Leerzeile

        System.out.println(platz1.ausgeben());
        System.out.println(platz2.ausgeben());
        System.out.println(platz3.ausgeben());

        // eigentlich auch nach dem Umlagern Gewicht prüfen??
//        System.out.println();
//        platz1.pruefenGewicht();
//        platz2.pruefenGewicht();
//        platz3.pruefenGewicht();
        System.out.println("\n=================");
        int[] arr = new int[3];
        System.out.println(arr[0]);

        int[][] arrC = {{1, 2, 3}, {4, 5, 6, 7, 8}, {0}};
        System.out.println(Arrays.deepToString(arrC));

        String[] strArr = new String[3];
        System.out.println(strArr[0]);



    }
}

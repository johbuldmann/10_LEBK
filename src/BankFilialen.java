public class BankFilialen {
    public static void main(String[] args) {
        Filiale koeln = new Filiale("Köln", 20000, 30000, 40000, 50000);
        Filiale duess = new Filiale("Düsseldorf", 10000, 20000, 30000, 40000);
        Filiale bonn = new Filiale("Bonn", 30000, 40000, 50000, 60000);
        System.out.println(koeln.toString());
        koeln.aendernQuartalsUmsatz(3, 60000);
        System.out.println(koeln.toString());
        Filiale.ermittelnRangfolge(koeln, duess, bonn);
    }



}

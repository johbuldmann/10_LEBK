package lebk.stdm;

import java.util.Arrays;

public class Box {
    private double breite;
    private double hoehe;
    private double laenge;

    // Konstruktor
    public Box(double breite, double hoehe, double laenge) {
//        setBreite(breite);
//        setHoehe(hoehe);
//        setLaenge(laenge);
        // besser so:
        this.breite = breite;
        this.hoehe = hoehe;
        this.laenge = laenge;
    }

    // copy Konstruktor:
    public Box(Box alteBox) {
//        setBreite(alteBox.getBreite());
//        setHoehe(alteBox.getHoehe());
//        setLaenge(alteBox.getLaenge());
        // so besser:
        this.breite = alteBox.getBreite();
        this.hoehe = alteBox.getHoehe();
        this.laenge = alteBox.getLaenge();
    }

    /*
 - breite: double
 - hoehe: double
 - laenge: double
 + Box(double b, double h, double l)
 + berechneVolumen(): double
 + berechneFlaeche(): double
      */
    public double berechneVolumen() {
        return breite * hoehe * laenge;
    }

    public double berechneFlaeche(){
        // Oberfläche von Quader (oder Box) = 2 * a * b + 2 * a * c + 2 * b * c
        // return 2 * breite * hoehe + 2 * breite + laenge + 2 * hoehe * laenge;
        double f;
        f = 2 * berechneFlaecheVorn() +
            2 * berechneFlaecheOben() +
            2 * berechneFlaecheSeitlich();
        return f;
    }

    // Box passt in Box?
    public boolean passtIn(Box aeussereBox){
        return (this.breite <= aeussereBox.getBreite() && this.laenge <= aeussereBox.getLaenge() && this.hoehe <= aeussereBox.getHoehe());
    }

    public boolean passtInRotation(Box aeussereBox) {
        double[] innerBox = {this.breite, this.hoehe, this.laenge};
        double[] outerBox = {aeussereBox.getBreite(), aeussereBox.getHoehe(), aeussereBox.getLaenge()};

        Arrays.sort(innerBox);
        Arrays.sort(outerBox);

//        for (int i = 0; i < innerBox.length; i++) {
//            if (innerBox[i] > outerBox[i]) {
//                return false;
//            }
//        }
//        return true;

        // oder alternativ
        return (innerBox[0] <= outerBox[0] && innerBox[1] <= outerBox[1] && innerBox[2] <= outerBox[2]);
    }

    // public Box groessereBox(); – und kleinereBox();
    public Box groessereBox(){
        return new Box(this.breite * 1.25, this.hoehe * 1.25, this.laenge * 1.25);
    }
    public Box kleinereBox(){
        return new Box(this.breite * 0.75, this.hoehe * 0.75, this.laenge * 0.75);
    }

    // berechneFlaecheVorn(), berechneFlaecheOben() und berechneFlaecheSeitlich()
    private double berechneFlaecheVorn(){
        return breite * hoehe;
    }
    private double berechneFlaecheOben(){
        return breite * laenge;
    }
    private double berechneFlaecheSeitlich(){
        return hoehe * laenge;
    }

    // Getter und Setter
    public double getBreite() {
        return breite;
    }

    public void setBreite(double breite) {
        this.breite = breite;
    }

    public double getHoehe() {
        return hoehe;
    }

    public void setHoehe(double hoehe) {
        this.hoehe = hoehe;
    }

    public double getLaenge() {
        return laenge;
    }

    public void setLaenge(double laenge) {
        this.laenge = laenge;
    }

}

class Test{

    int test;

    public Test(int test) {
        this.test = test;
    }
}

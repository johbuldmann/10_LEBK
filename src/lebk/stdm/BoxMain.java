package lebk.stdm;

public class BoxMain {
    public static void main(String[] args) {

//        Box box = new Box( 2.5, 5.0, 6.0 ) ;
//        System.out.println("Flaeche: " + box.berechneFlaeche() +
//                " Volumen: " + box.berechneVolumen());
//        System.out.println( "Laenge: " + box.getLaenge() /* box.laenge */ +
//                " Hoehe: " + box.getHoehe() /* box.hoehe */ +
//                " Breite: " + box.getBreite() /* box.breite */ );

//        Box box2 = new Box( 2.5, 5.0, 6.0 ) ;
//        System.out.println("Flaeche: " + box2.berechneFlaeche() +
//                " Volumen: " + box2.berechneVolumen());
//        System.out.println("Flaeche oben: " + /* box2.berechneFlaecheOben() das lässt sich so nicht kompilieren, da berechneFlaecheOben private ist. */ );

//        Box boxCopy = new Box(box2);
//        System.out.println("Volumen " + boxCopy.berechneVolumen());


//        Box box3 = box2.groessereBox();
//        Box box4 = box2.kleinereBox();
//
//        System.out.println(box2.berechneVolumen());
//        System.out.println(box3.berechneVolumen());
//        System.out.println(box4.berechneVolumen());

        Box boxOuter = new Box(1, 2, 1);
        Box boxInner = new Box(1, 1, 2);

        System.out.println(boxInner.passtInRotation(boxOuter));


    }
}

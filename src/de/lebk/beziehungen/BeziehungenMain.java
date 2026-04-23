package de.lebk.beziehungen;

public class BeziehungenMain {
    public static void main(String[] args) {

        ManyToManyVerwaltung verwaltung = new ManyToManyVerwaltung();

//        Person p1 = verwaltung.createPersonAndHobby("Dieter", "Fussball", verwaltung);
        Person dieter = new Person("Dieter", "Fussball", verwaltung);

        System.out.println(dieter.toString());

        System.out.println(verwaltung.getPerson("Max"));

        // fixme: das ist hier so nicht erlaubt, weil Hobby wird ohne eine Person in dem Moment angelegt:
//        Hobby handball = new Hobby("Handball");
//        p1.addHobby(handball);
//        p1.addHobby(new Hobby("Handball"));
//        System.out.println(p1.toString());
//
//        System.out.println();
    }
}

package de.lebk.beziehungen;

import de.lebk.beziehungen.models.Hobby;
import de.lebk.beziehungen.models.Person;
import de.lebk.beziehungen.models.ManyToManyVerwaltung;

public class BeziehungenMain {
    public static void main(String[] args) {

        ManyToManyVerwaltung verwaltung = new ManyToManyVerwaltung();

        Person dieter = verwaltung.createPerson("Dieter", "Fussball");
        Person max = verwaltung.createPerson("Max", "Fussball");

        Hobby fussball = verwaltung.getHobby("Fussball");
        Hobby paddle = verwaltung.createHobby("Paddle", "Dieter");
        Hobby handball = verwaltung.createHobby("Handball", "Dieter");

        System.out.println(verwaltung.getHobbyList());

//        System.out.println(dieter.toString());
//        dieter.addHobby("Handball");


        System.out.println(dieter);

        System.out.println(fussball);
        System.out.println(max);
        System.out.println(paddle);

//        Person person = new Person("Dieter");
    }
}

package de.lebk.beziehungen;

import java.util.*;

public class ManyToManyVerwaltung {
    static List<Person> personList = new ArrayList<>();
    static List<Hobby> hobbyList = new ArrayList<>();


    //    public Person createPersonAndHobby(String personName, String hobbyBezeichnung) {
//
//        Hobby hobby = new Hobby(hobbyBezeichnung);
//        Person person = new Person(personName);
//        person.addHobby(hobby);
//        hobby.addPerson(person);
//
//        // schauen ob in der MapList der key vorhanden ist,
//        // wenn ja, dann person/hobby hinzufügen,
//        // sonst neue key value anlegen
//
//        personList.add(person);
//        hobbyList.add(hobby);
//
//        return person;
//    }
//
    public Person getPerson(String name) {
        return personList.stream()
                .filter(person1 -> person1.getPersonName().equals(name))
                .findFirst()
                .orElse(null);
//                .orElseThrow(() -> new IllegalArgumentException("Person existiert nicht..."));
    }

    public Hobby getHobby(String name) {
        return hobbyList.stream()
                .filter(hobby -> hobby.getHobbyDescription().equals(name))
                .findFirst()
                .orElse(null);
//                .orElseThrow(() -> new IllegalArgumentException("Hobby existiert nicht..."));
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public List<Hobby> getHobbyList() {
        return hobbyList;
    }

    public void addPerson(Person person) {
        personList.add(person);
    }

    public void addHobby(Hobby hobby) {
        hobbyList.add(hobby);
    }
}

package de.lebk.beziehungen.models;

import java.util.*;

public class ManyToManyVerwaltung {
    static List<Person> personList = new ArrayList<>();
    static List<Hobby> hobbyList = new ArrayList<>();

    public Person createPerson(String personName, String hobbyDescription) {
        Person person = getPerson(personName);
        if (person == null) {
            person = new Person(personName);
            personList.add(person);
        }

        Hobby hobby = getHobby(hobbyDescription);
        if (hobby == null) {
            hobby = new Hobby(hobbyDescription);
            hobbyList.add(hobby);
        }
        person.addHobby(hobby);
        hobby.addPerson(person);

        return person;
    }

    public Hobby createHobby(String hobbyDescription, String personName) {
        // leitet einfach an die andere Methode weiter...
        // -> charmante Idee geht aber so leider nicht..
//        this.createPerson(personName, hobbyDescription);

        Hobby hobby = getHobby(hobbyDescription);
        if (hobby == null) {
            hobby = new Hobby(hobbyDescription);
            hobbyList.add(hobby);
        }

        Person person = getPerson(personName);
        if (person == null) {
            person = new Person(personName);
            personList.add(person);
        }
        person.addHobby(hobby);
        hobby.addPerson(person);

        return hobby;
    }

    public Person getPerson(String name) {
        return personList.stream()
                .filter(person1 -> person1.getPersonName().equals(name))
                .findFirst()
                .orElse(null);
//                .orElseThrow(() -> new IllegalArgumentException("Person existiert nicht..."));
                // nullcheck mache ich in der Methode die Aufruft.
    }

    public Hobby getHobby(String name) {
        return hobbyList.stream()
                .filter(hobby -> hobby.getHobbyDescription().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public List<Hobby> getHobbyList() {
        return hobbyList;
    }
}

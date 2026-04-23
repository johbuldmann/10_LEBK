package de.lebk.beziehungen;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private String personName;
    private List<Hobby> hobbyList = new ArrayList<>();

    private ManyToManyVerwaltung verwaltung;

    public Person(String personName, String hobbyDescription, ManyToManyVerwaltung verwaltung) {
        this.personName = personName;
        this.verwaltung = verwaltung;


        Hobby hobby = verwaltung.getHobby(hobbyDescription);
        if (hobby == null) {
            hobby = new Hobby(hobbyDescription, personName, verwaltung);
        }
        hobbyList.add(hobby);
        verwaltung.addPerson(this);
    }

//    public Person(String personName, List<Hobby> hobbyList) {
//        this.personName = personName;
//        this.hobbyList = hobbyList;
//    }

    public void addHobby(String hobbyDescription) {
        Hobby hobby = verwaltung.getHobby(hobbyDescription);
        if (hobby == null) {
            hobby = new Hobby(hobbyDescription, personName, verwaltung);
        }
        hobbyList.add(hobby);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("personName='").append(personName).append('\'');
        sb.append(", hobbyList=").append(hobbyList);
        sb.append('}');
        return sb.toString();
    }

    public String getPersonName() {
        return personName;
    }
}
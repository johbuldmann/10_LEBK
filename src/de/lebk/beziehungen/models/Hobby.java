package de.lebk.beziehungen.models;

import java.util.ArrayList;
import java.util.List;

public class Hobby {
    private String hobbyDescription;
    private final List<Person> personList = new ArrayList<>();

    Hobby(String hobbyDescription) {
        this.hobbyDescription = hobbyDescription;
    }

    void addPerson(Person person) {
        personList.add(person);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Hobby{");
        sb.append(hobbyDescription).append('\'');
        sb.append(", Teilnehmer = "); //.append(personList);
        for (Person p : personList) {
            sb.append(p.getPersonName());
            sb.append(", ");
        }
        sb.append('}');
        return sb.toString();
    }

    public String getHobbyDescription() {
        return hobbyDescription;
    }
}
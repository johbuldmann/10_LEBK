package de.lebk.beziehungen.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {

    private String personName;
    private final List<Hobby> hobbyList = new ArrayList<>();

    Person(String personName) {
        this.personName = personName;
    }

    void addHobby(Hobby hobby) {
        // hier noch ein Test ob
        hobbyList.add(hobby);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append(personName).append('\'');
        sb.append(", Hobbies = ");  // append(hobbyList.forEach(hobby -> hobby.getHobbyDescription()));

        sb.append(hobbyList.stream()
                .map(hobby -> hobby.getHobbyDescription())
                .collect(Collectors.joining(", ")));
//        for (Hobby h : hobbyList) {
//            sb.append(h.getHobbyDescription());
//            sb.append(", ");
//        }
        sb.append('}');
        return sb.toString();
    }

    public String getPersonName() {
        return personName;
    }
}
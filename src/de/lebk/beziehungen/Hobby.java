package de.lebk.beziehungen;



import java.util.ArrayList;
import java.util.List;

public class Hobby {
    ManyToManyVerwaltung verwaltung;
    private String hobbyDescription;
    private List<Person> personList = new ArrayList<>();


//    public Hobby(String hobby) {
//        this.hobbyDescription = hobby;
//    }
//    public Hobby(String hobby, List<Person> personList) {
//        this.hobbyDescription = hobby;
//        this.personList = personList;
//    }

    public Hobby(String hobbyDescription, String personName, ManyToManyVerwaltung verwaltung) {
        this.hobbyDescription = hobbyDescription;
        this.verwaltung = verwaltung;

        Person person = verwaltung.getPerson(personName);
        if (person == null) {
            person = new Person(personName, hobbyDescription, verwaltung);
        }
        personList.add(person);
        verwaltung.addHobby(this);
    }

    public void addPerson(String personName) {
        Person person = verwaltung.getPerson(personName);
        if (person == null) {
            person = new Person(personName, hobbyDescription, verwaltung);
        }
        personList.add(person);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Hobby{");
        sb.append(", hobbyDescription='").append(hobbyDescription).append('\'');
        sb.append(", personList=").append(personList);
        sb.append('}');
        return sb.toString();
    }

    public String getHobbyDescription() {
        return hobbyDescription;
    }
}
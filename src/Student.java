public class Student {

    private String name;
    private double note;

    // Konstruktor
    Student(String name, double note) {
        setName(name);
        setNote(note);
    }

    // getter und setter
    public String getName() {
        return name;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("name='").append(name).append('\'');
        sb.append(", note=").append(note);
        sb.append('}');
        return sb.toString();
    }
}

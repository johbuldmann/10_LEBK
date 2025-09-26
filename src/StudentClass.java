import java.lang.reflect.Array;
import java.util.ArrayList;

public class StudentClass {
    private final ArrayList<Student> students;

    public StudentClass() {
        students = new ArrayList<>();
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    public double calcAverage() {
        if (students.isEmpty()) {
            throw new IllegalStateException("Class ist empty, please add students and notes first.");
        }

        double sum = 0;

        for (Student note : students) {
            sum += note.getNote();
        }
        return sum / students.size();
    }

    public Student bestStudent() {
        if (students.isEmpty()) {
            throw new IllegalStateException("No Students in the class.");
        }
        Student bestStudent = students.get(0);

        for (Student s : students) {
            if (s.getNote() < bestStudent.getNote()) {
                bestStudent = s;
            }
        }
        return bestStudent;
    }

    public Student worstStudent(){
        if (students.isEmpty()) {
            throw new IllegalStateException("No Students in the class.");
        }
        Student worstStudent = students.get(0);

        for (Student s : students){
            if (s.getNote() > worstStudent.getNote()) {
                worstStudent = s;
            }
        }
        return  worstStudent;
    }

    public ArrayList<Student> studentsAboveAverage(){
        double average = calcAverage();
        ArrayList<Student> studentsAboveAverage = new ArrayList<>();

        for (Student s : students){
            if (s.getNote() < average){
                studentsAboveAverage.add(s);
            }
        }
        return  studentsAboveAverage;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StudentClass{");
        sb.append("students=").append(students);
        sb.append('}');
        return sb.toString();
    }
}

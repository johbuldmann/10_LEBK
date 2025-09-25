import java.util.ArrayList;

public class StudentClass {
    private ArrayList<Student> students;

    StudentClass(){
        students = new ArrayList<>();
    }

    public void addStudent(Student s){
        students.add(s);
    }

    public double calcAverage(ArrayList<Student> list){

        if (list.isEmpty()){
            throw new RuntimeException("Class ist empty, please add students and notes first.");
        }

        double sum = 0;

        for (Student note : students){
            sum += note.getNote();
        }

        return sum / list.size();
    }


}

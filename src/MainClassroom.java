import java.util.Scanner;
public class MainClassroom {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentClass ratsgymnasium = new StudentClass();

        System.out.println("Welcome to the Class administration");
        System.out.println("Enter Students name and grades. Enter exit to exit.");

        while (true){
            System.out.println("Please enter the name of the Student:");

            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.println("Enter note for student: " + name);
            double note;
            try {
                String noteStr = scanner.nextLine().replace(',', '.');
                note = Double.parseDouble(noteStr);
                if (note < 1 || note > 6) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Note nicht gültig. Noten zwischen 1 und 6 und mit . Dezimaltrennzeichen");
                continue;
            }

            Student newStudent = new Student(name, note);

            ratsgymnasium.addStudent(newStudent);
        }

        try {
            double averageGrades = ratsgymnasium.calcAverage();
            System.out.println("Durcschnittsnote ist " + averageGrades);

            Student best = ratsgymnasium.bestStudent();
            System.out.println("Bester Schüler ist " + best);

            Student worst = ratsgymnasium.worstStudent();
            System.out.println("Schlechtester Schüler ist " + worst);

            System.out.println("Students above Average " + ratsgymnasium.studentsAboveAverage());
        } catch (IllegalStateException e) {
            System.out.println("No Students in class. Please Add");
        }
    }
}

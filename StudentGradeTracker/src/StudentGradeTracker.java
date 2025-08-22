import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    double grade;

    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("===== Student Grade Tracker =====");

        while (true) {
            System.out.print("Enter student name (or type 'exit' to finish): ");
            String name = sc.nextLine();
            if (name.equalsIgnoreCase("exit")) break;

            System.out.print("Enter grade for " + name + ": ");
            double grade = sc.nextDouble();
            sc.nextLine(); // consume leftover newline

            students.add(new Student(name, grade));
        }

        if (students.isEmpty()) {
            System.out.println("No student data entered.");
            return;
        }

        double sum = 0, highest = students.get(0).grade, lowest = students.get(0).grade;
        String topStudent = students.get(0).name, lowStudent = students.get(0).name;

        for (Student s : students) {
            sum += s.grade;
            if (s.grade > highest) {
                highest = s.grade;
                topStudent = s.name;
            }
            if (s.grade < lowest) {
                lowest = s.grade;
                lowStudent = s.name;
            }
        }

        double average = sum / students.size();

        System.out.println("\n===== Grade Summary =====");
        System.out.println("Total Students: " + students.size());
        System.out.println("Average Grade: " + average);
        System.out.println("Highest Grade: " + highest + " (" + topStudent + ")");
        System.out.println("Lowest Grade: " + lowest + " (" + lowStudent + ")");

        sc.close();
    }
}


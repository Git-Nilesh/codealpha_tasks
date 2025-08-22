import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class StudentRecord {
    String name;
    double grade;

    StudentRecord(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class StudentGradeTrackerGUI {
    private ArrayList<StudentRecord> students = new ArrayList<>();
    private JTextField nameField, gradeField;
    private JTextArea outputArea;

    public StudentGradeTrackerGUI() {
        JFrame frame = new JFrame("Student Grade Tracker - CodeAlpha");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        frame.add(new JLabel("Student Name:"));
        nameField = new JTextField(15);
        frame.add(nameField);

        frame.add(new JLabel("Grade:"));
        gradeField = new JTextField(5);
        frame.add(gradeField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> addStudent());
        frame.add(addButton);

        JButton showButton = new JButton("Show Summary");
        showButton.addActionListener(e -> showSummary());
        frame.add(showButton);

        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        frame.add(new JScrollPane(outputArea));

        frame.setVisible(true);
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String gradeText = gradeField.getText().trim();

        if (name.isEmpty() || gradeText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter both name and grade.");
            return;
        }

        try {
            double grade = Double.parseDouble(gradeText);
            students.add(new StudentRecord(name, grade));
            outputArea.append("Added: " + name + " - " + grade + "\n");
            nameField.setText("");
            gradeField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid grade. Please enter a number.");
        }
    }

    private void showSummary() {
        if (students.isEmpty()) {
            outputArea.append("No students added yet.\n");
            return;
        }

        double sum = 0, highest = students.get(0).grade, lowest = students.get(0).grade;
        String topStudent = students.get(0).name, lowStudent = students.get(0).name;

        for (StudentRecord s : students) {
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

        outputArea.append("\n===== Grade Summary =====\n");
        outputArea.append("Total Students: " + students.size() + "\n");
        outputArea.append("Average Grade: " + average + "\n");
        outputArea.append("Highest Grade: " + highest + " (" + topStudent + ")\n");
        outputArea.append("Lowest Grade: " + lowest + " (" + lowStudent + ")\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentGradeTrackerGUI::new);
    }
}



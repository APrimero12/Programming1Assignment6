package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class Course {

    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment> assignments;
    private ArrayList<Student> registeredStudents;
    private ArrayList<Double> finalScores;

    private static int nextId = 1;

    public Course(String courseName, double credits, Department department) {
        this.courseId = generateCourseId();
        this.courseName = courseName;
        this.credits = credits;
        this.department = department;
        this.assignments = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
        this.finalScores = new ArrayList<>();
    }

    private String generateCourseId() {
        String id = String.format("C %s %02d", department.getDepartmentId(), nextId);
        nextId++;
        return id;
    }

    public boolean isAssignmentWeightValid() {
        double totalWeight = 0;
        for (Assignment assignment : assignments) {
            totalWeight += assignment.getWeight();
        }
        return totalWeight == 1.0;
    }

    public boolean registerStudent(Student student) {
        if (registeredStudents.contains(student)) {
            return false;
        }
        registeredStudents.add(student);
        student.registerCourse(this);
        for (Assignment assignment : assignments) {
            assignment.getScores().add(null);
        }
        finalScores.add(null);
        return true;
    }

    public int[] calcStuentsAverage() {
        int[] averages = new int[registeredStudents.size()];
        for (int i = 0; i < registeredStudents.size(); i++) {
            double totalScore = 0;
            for (Assignment assignment : assignments) {
                Integer score = assignment.getScores().get(i);
                if (score != null) {
                    totalScore += score * assignment.getWeight();
                }
            }
            averages[i] = (int) totalScore;
            finalScores.set(i, totalScore);
        }
        return averages;
    }

    public boolean addAssignment(String assignmentName, double weight, int maxScore) {
        if (!isAssignmentWeightValid() && assignments.stream().mapToDouble(Assignment::getWeight).sum() > 1.0) {
            return false;
        }
        Assignment newAssignment = new Assignment(assignmentName, weight, maxScore);
        assignments.add(newAssignment);
        for (int i = 0; i < registeredStudents.size(); i++) {
            newAssignment.getScores().add(null);
        }
        return true;
    }

    public void generateScores() {
        for (Assignment assignment : assignments) {
            assignment.generateRandomScore(registeredStudents.size());
        }
        calcStuentsAverage();
    }

    public void displayScores() {
        System.out.println("Course: " + courseName + "(" + courseId + ")");
        System.out.println("              ");
        for (Assignment assignment : assignments) {
            System.out.printf("%-15s", assignment.getAssignmentName());
        }
        System.out.println("Final Score");

        for (int i = 0; i < registeredStudents.size(); i++) {
            Student student = registeredStudents.get(i);
            System.out.printf("%-15s", student.getStudentName());
            for (Assignment assignment : assignments) {
                Integer score = assignment.getScores().get(i);
                System.out.printf("%-15s", score == null ? "N/A" : score);
            }
            System.out.printf("%-15s", finalScores.get(i));
        }

        System.out.printf("%-15s", "Average");
        for (Assignment assignment : assignments) {
            assignment.calcAssignmentAvg();
            System.out.printf("%-15s", assignment.getAssignmentAverage());
        }
        System.out.println();
    }

    public String toSimplifiedString() {
        return String.format("Course{id='%s', name='%s', credits=%.2f, department='%s'}",
                courseId, courseName, credits, department.getDepartmentName());
    }

    @Override
    public String toString() {
        String assignmentsInfo = "";
        for (Assignment assignment : assignments) {
            assignmentsInfo += String.format("Assignment{id='%s', name='%s'} ",
                    assignment.getAssignmentId(), assignment.getAssignmentName());
        }

        String studentsInfo = "";
        for (Student student : registeredStudents) {
            studentsInfo += String.format("Student{id='%s', name='%s', department='%s'} ",
                    student.getStudentId(), student.getStudentName(), student.getDepartment().getDepartmentName());
        }

        return String.format("Course{id='%s', name='%s', credits=%.2f, department='%s'," +
                        " assignments=[%s], students=[%s]}", courseId, courseName, credits,
                department.getDepartmentName(), assignmentsInfo.trim(), studentsInfo.trim());
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void setRegisteredStudents(ArrayList<Student> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }

    public void setFinalScores(ArrayList<Double> finalScores) {
        this.finalScores = finalScores;
    }

    public static void setNextId(int nextId) {
        Course.nextId = nextId;
    }
}

package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class Student {

    @Setter
    private String studentId;
    @Setter
    private String studentName;
    @Setter
    private Gender gender;
    @Setter
    private Address address;
    @Setter
    private Department department;
    @Setter
    private ArrayList<Course> registeredCourses;

    private static int nextId = 1;

    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentId = generateStudentId();
        this.gender = gender;
        this.studentName = studentName;
        this.address = address;
        this.department = department;
        this.registeredCourses = new ArrayList<>();
    }

    public String generateStudentId() {
        String id = "S" + String.format("%06d", nextId);
        nextId++;
        return id;
    }

    public boolean registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            System.out.printf("%s has already been registered\n", course.toString());
            return false;
        }
        registeredCourses.add(course);
        System.out.printf("%s is successfully registered\n", course.toString());
        return true;
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            return false;
        }
        registeredCourses.remove(course);
        System.out.printf("%s is successfully dropped\n", course.toString());
        return true;
    }

    public String toSimplifiedString() {
        return String.format("Student{id='%s', name='%s', department='%s'", studentId, studentName, department);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", gender=" + gender +
                ", address=" + address +
                ", department=" + department +
                ", registeredCourses=" + registeredCourses +
                '}';
    }
}

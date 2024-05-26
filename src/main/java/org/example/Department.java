package org.example;

import lombok.*;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class Department {

    private String departmentId;
    private String departmentName;

    private static int nextId = 1;

    public Department(String departmentName) {
        if (validateDepartmentName(departmentName)) {
            this.departmentId = generateDepartmentId();
            this.departmentName = departmentName;
        } else {
            this.departmentId = null;
            this.departmentName = null;
        }
    }

    private String generateDepartmentId() {
        String id = "D" + String.format("%02d", nextId);
        nextId++;
        return id;
    }

    public static boolean validateDepartmentName(String departmentName) {
        return departmentName != null && departmentName.matches("[a-zA-Z ]+");
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}

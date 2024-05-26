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
    

    public Department(@NonNull String departmentId, String departmentName) {
        if (validateDepartmentName(departmentName)) {
            this.departmentId = null;
            this.departmentName = null;
        } else {
            this.departmentId = "" + nextId++;
            this.departmentName = departmentName;

        }
    }

    public static boolean validateDepartmentName(String departmentName) {
        if (departmentName == null) {
            return false;
        }
        return true;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}

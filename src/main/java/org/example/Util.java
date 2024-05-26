package org.example;

public class Util {

    public static String toTitleCase(String strIn) {
        if (strIn == null || strIn.isEmpty()) {
            return strIn;
        }

        String[] strings = strIn.split(" ");
        String titleCase = "";

        for (String string : strings) {
            if (!string.isEmpty()) {
                String UpperCaseString = Character.toUpperCase(string.charAt(0))
                        + string.substring(1).toLowerCase();
                titleCase += UpperCaseString;
            }
        }

        return titleCase.trim();
    }
}

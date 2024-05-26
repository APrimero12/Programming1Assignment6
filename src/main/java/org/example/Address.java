package org.example;

import lombok.*;

@ToString
@EqualsAndHashCode
@Getter
public class Address {

    private int streetNo;
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private String country;

    public Address(String street, int streetNo,  String city, String province, String postalCode, String country) {
        if (isPostalCodeValid(postalCode)) {
            this.streetNo = streetNo;
            this.street = street;
            this.city = city;
            this.province = province;
            this.postalCode = postalCode.toUpperCase();
            this.country = country;
        } else {
            this.streetNo = 0;
            this.street = null;
            this.city = null;
            this.province = null;
            this.postalCode = null;
            this.country = null;
        }
    }

    public static boolean isPostalCodeValid(String postalCode) {
    if (postalCode == null) {
        return false;
    }

    postalCode = postalCode.toUpperCase();

    if (postalCode.length() == 6) {
        return validateSixCharPostalCode(postalCode);
    } else if (postalCode.length() == 7) {
        return validateSevenCharPostalCode(postalCode);
    } else {
        return false;
    }
}

    private static boolean validateSixCharPostalCode(String postalCode) {
        for (int i = 0; i < postalCode.length(); i++) {
            if (i % 2 == 0) {
                if (Character.isLetter(postalCode.charAt(i))) {
                    return false;
                }
            } else {
                if (Character.isDigit(postalCode.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean validateSevenCharPostalCode(String postalCode) {
        if (postalCode.charAt(3) != ' ') {
            return false;
        }
        return validateSixCharPostalCode(postalCode.replace(" ", ""));
    }

    public void setStreetNo(int streetNo) {
        this.streetNo = streetNo;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

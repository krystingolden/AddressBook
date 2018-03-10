package com.pluralsight;

public class AddressBookContact {

    private int index;
    private String firstName;
    private String lastName;
    private String houseNumberAndStreet;
    private String cityName;
    private String province;
    private String postalCode;
    private long phoneNumber;
    private String email;


    public AddressBookContact(int index, String firstName, String lastName, String houseNumberAndStreet,
                              String cityName, String province, String postalCode, long phoneNumber, String email) {
        this.index = index;
        this.firstName = firstName;
        this.lastName = lastName;
        this.houseNumberAndStreet = houseNumberAndStreet;
        this.cityName = cityName;
        this.province = province;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String toStringForDisplay() {
        return (firstName + "\n" + lastName + "\n" + houseNumberAndStreet + "\n" + cityName + "\n" +
                province + "\n" + postalCode + "\n" + phoneNumber + "\n" + email);
    }

    @Override
    public String toString() {
        return (firstName + "," + lastName + "," + houseNumberAndStreet + "," + cityName + "," +
                province + "," + postalCode + "," + phoneNumber + "," + email);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }

    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }

    public void setHouseNumberAndStreet(String newHouseNumberAndStreet) {
        this.houseNumberAndStreet = newHouseNumberAndStreet;
    }

    public void setCityName(String newCityName) {
        this.cityName = newCityName;
    }

    public void setProvince(String newProvince) {
        this.province = newProvince;
    }

    public void setPostalCode(String newPostalCode) {
        this.postalCode = newPostalCode;
    }

    public void setPhoneNumber(long newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }


}


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

    @Override
    public String toString() {
        return (index + "\n" + firstName + "\n" + lastName  + "\n"+ houseNumberAndStreet  + "\n"+ cityName  + "\n"+
                province  + "\n"+ postalCode  + "\n"+ phoneNumber  + "\n"+ email);
    }
}


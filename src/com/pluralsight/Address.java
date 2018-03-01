package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class Address {

    private String houseNumberAndStreetName;
    private String cityName;
    private ArrayList<String> provinceCodes = new ArrayList<>();
    private String province;
    private String postalCode;


    public Address() {
        provinceCodes.add("AB");
        provinceCodes.add("BC");
        provinceCodes.add("MB");
        provinceCodes.add("SK");
        provinceCodes.add("ON");
        provinceCodes.add("QC");
        provinceCodes.add("NB");
        provinceCodes.add("NS");
        provinceCodes.add("PE");
        provinceCodes.add("NF");
        provinceCodes.add("YT");
        provinceCodes.add("NT");
    }



    public void getAddressInfo() throws Exception {
        Scanner keyboard = new Scanner(System.in);
        boolean pass;

        System.out.println("Enter the house number and street name:");
        this.houseNumberAndStreetName = keyboard.nextLine();

        System.out.println("Enter the city name:");
        this.cityName = keyboard.next();

        //Prompt the user for the province and verify if it's valid
        String provCode;
        do {
            System.out.println("Enter the province code:");
            keyboard = new Scanner(System.in);
            provCode = keyboard.nextLine();
            pass = validateProvinceCode(provCode);
            if(!pass){
                System.out.println("Invalid province code. Please try again.");
            }
        } while (!pass);
        this.province = provCode;

        //Prompt the user for the postal code and verify if it's valid
        String pc;
        do {
            System.out.println("Enter the postal code");
            keyboard = new Scanner(System.in);
            pc = keyboard.nextLine();
            pass = validatePostalCode(pc);
            if (!pass) {
                System.out.println("Invalid postal code. Please try again.");
            }
        }while(!pass);
        this.postalCode = pc.toUpperCase();
    }

    public String getCityName() {
        return cityName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getProvince() {
        return province;
    }

    public String getHouseNumberAndStreetName() {
        return houseNumberAndStreetName;
    }

    private boolean validateProvinceCode(String provCode) {
        return provinceCodes.contains(provCode.toUpperCase());
    }

    private boolean validatePostalCode(String pc) {
        pc = pc.replaceAll("\\s+","");

        if(pc.length() !=6){
            return false;
        }
        else {
            char[] pcChars = pc.toCharArray();

            return (Character.isLetter(pcChars[0]) && Character.isDigit(pcChars[1]) &&
                    Character.isLetter(pcChars[2]) && Character.isDigit(pcChars[3]) &&
                    Character.isLetter(pcChars[4]) && Character.isDigit(pcChars[5]));
        }
    }
}

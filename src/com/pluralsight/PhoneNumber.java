package com.pluralsight;

public class PhoneNumber {

    public long verifyPhoneNumber(String userEnteredPhoneNumber){

        //Determine the length of the user's entry for the phone number
        int length = userEnteredPhoneNumber.length();

        //Store only the numbers from the users entry (leaving out brackets, hyphen, spaces etc)
        StringBuilder sb = new StringBuilder();
        for (int a = 0; a < length; a++){
            char tempChar = userEnteredPhoneNumber.charAt(a);
            if(Character.isDigit(tempChar)) {
                sb.append(tempChar);
            }
        }
        //If the stored numbers result in the length of a valid phone number, return it
        if(sb.length() == 10){
            return Long.parseLong(sb.toString());
        }
        //Otherwise prompt the user
        else{
            System.out.println("Your phone number is missing digits");
            return 0;
        }
    }
}

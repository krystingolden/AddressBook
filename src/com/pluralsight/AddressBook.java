package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddressBook {

    private ArrayList<AddressBookContact> contacts = new ArrayList<>();
    private Scanner keyboard = new Scanner(System.in);
    private int index = 0;
    private boolean fileRead = false;

    public void addContact() throws Exception {
        //Create the contact index
        index += 1;
        //Prompt the user for the contact's first name and store it
        System.out.println("Enter the first name:");
        String firstName = keyboard.next();

        //Prompt the user for the contact's last name and store it
        System.out.println("Enter the last name:");
        String lastName = keyboard.next();

        //Prompt the user for the contact's phone number, verify it and store it
        long phoneNumber;
        do {
            System.out.println("Enter the phone number (ex. 519 555 1234):");
            keyboard = new Scanner(System.in);
            String userEnteredPhoneNumber = keyboard.nextLine();
            PhoneNumber number = new PhoneNumber();
            phoneNumber = number.verifyPhoneNumber(userEnteredPhoneNumber);
        } while (phoneNumber == 0);

        //Instantiate the address object
        Address address = new Address();

        //Prompt the user for the pieces of the contact's address
        address.getAddressInfo();

        //Store the pieces of the address
        String houseNumberAndStreetName = address.getHouseNumberAndStreetName();
        String cityName = address.getCityName();
        String province = address.getProvince();
        String postalCode = address.getPostalCode();

        //Prompt the user for the contact's email address
        System.out.println("Enter the email address:");
        String email = keyboard.next();

        //Instantiate the address book contact and populate it with the user's entries
        AddressBookContact newContact = new AddressBookContact(index, firstName, lastName, houseNumberAndStreetName,
                cityName, province, postalCode, phoneNumber, email);
        //Add the new contact to the arraylist of contacts
        contacts.add(newContact);

    }


    public void loadContactsFromFile() throws IOException {

        //Get the path to the file and open it
        try (BufferedReader br = Files.newBufferedReader(Paths.get("AddressBook.csv"))) {
            String loadedContactInfo;
            //Until there are no more lines to read from the file, loop through and add them to the list
            while ((loadedContactInfo = br.readLine()) != null) {
                //Split the loaded contact string into parts, separated by the ","
                String[] splitLoadedContactInfo = loadedContactInfo.split(",");

                //Store the parts into variables
                index += 1;
                String firstName = splitLoadedContactInfo[0];
                String lastName = splitLoadedContactInfo[1];
                String houseNumberAndStreetName = splitLoadedContactInfo[2];
                String cityName = splitLoadedContactInfo[3];
                String province = splitLoadedContactInfo[4];
                String postalCode = splitLoadedContactInfo[5];
                long phoneNumber = Long.parseLong(splitLoadedContactInfo[6]);
                String email = splitLoadedContactInfo[7];

                //Instantiate the address book contact and populate it with the user's entries
                AddressBookContact newContact = new AddressBookContact(index, firstName, lastName, houseNumberAndStreetName,
                        cityName, province, postalCode, phoneNumber, email);
                //Add the new contact to the arraylist of contacts
                contacts.add(newContact);

            }
            //If there is an issue opening the file, it will throw a message
        } catch (IOException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        if (index > 0) {
            fileRead = true;
        }
    }


    public void displayContacts() {
        System.out.println();
        for (int i = 0; i < contacts.size(); i++) {
            AddressBookContact currentContact = contacts.get(i);
            System.out.println(currentContact.toStringForDisplay());
            System.out.println();
        }
    }

    public void saveToFile() throws IOException {
        //Get the path to the file which will be created/overwritten,
        // read all the lines from the list into the file and close it
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("AddressBook.csv"))) {
            //As long as there are still lines to read from the list, loop through and write them out
            for (AddressBookContact contactInfo : contacts) {
                StringBuilder sb = new StringBuilder();
                sb.append(contactInfo);
                writer.write(sb.toString());
                writer.newLine();
            }
            writer.close();
            //If there is an issue creating/opening the file to be written to, it will throw a message
        } catch (IOException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    public boolean getFileRead() {
        return fileRead;
    }


    public void removeContact() {
        boolean matchFound = false;
        System.out.println("What is the first name of the contact you'd like to remove?");
        String tempName = keyboard.next();
        for (int i = 0; i < contacts.size(); i++) {
            AddressBookContact currentContact = contacts.get(i);
            String firstName = currentContact.getFirstName();
            if (tempName.equalsIgnoreCase(firstName)){
                matchFound = true;
                System.out.println(currentContact.toStringForDisplay());
                System.out.println("Do you want to remove this contact? Enter Y or N.");
                String userAnswer = keyboard.next();
                while(!(userAnswer.equalsIgnoreCase("Y") || userAnswer.equalsIgnoreCase("N"))){
                    System.out.println("Invalid entry. Try again");
                    userAnswer = keyboard.next();
                }
                if(userAnswer.equalsIgnoreCase("Y")){
                    contacts.remove(currentContact);
                    System.out.println("Contact successfully removed.");
                }
            }
        }
        if(!matchFound){
            System.out.println("There are no contacts with that first name.");
        }
    }

    public void editContact() throws Exception{
        boolean matchFound = false;
        System.out.println("What is the first name of the contact you'd like to edit?");
        String tempName = keyboard.next();
        for (int i = 0; i < contacts.size(); i++) {
            AddressBookContact currentContact = contacts.get(i);
            String firstName = currentContact.getFirstName();
            if (tempName.equalsIgnoreCase(firstName)){
                matchFound = true;
                System.out.println(currentContact.toStringForDisplay());
                System.out.println("Do you want to edit this contact? Enter Y or N.");
                String userAnswer = keyboard.next();
                while(!(userAnswer.equalsIgnoreCase("Y") || userAnswer.equalsIgnoreCase("N"))){
                    System.out.println("Invalid entry. Try again");
                    userAnswer = keyboard.next();
                }
                if(userAnswer.equalsIgnoreCase("Y")){
                    //Display the edit menu to the user
                    displayEditMenu();

                    int userOption = getUserOption();

                    //Edit the contact detail that corresponds with the users option
                    switch (userOption) {
                        //Option 1 = edit first name
                        case 1:
                            System.out.println("What is the edited first name?");
                            String newFirstName = keyboard.next();
                            currentContact.setFirstName(newFirstName);
                            break;
                        //Option 2 = edit last name
                        case 2:
                            System.out.println("What is the edited last name?");
                            String newLastName = keyboard.next();
                            currentContact.setLastName(newLastName);
                            break;
                        //Option 3 = address
                        case 3:
                            System.out.println("What are the edited address details?");
                            //Instantiate the address object
                            Address newAddress = new Address();

                            //Prompt the user for the pieces of the contact's address
                            newAddress.getAddressInfo();

                            //Reset the pieces of the address
                            String houseNumberAndStreetName = newAddress.getHouseNumberAndStreetName();
                            String cityName = newAddress.getCityName();
                            String province = newAddress.getProvince();
                            String postalCode = newAddress.getPostalCode();
                            currentContact.setHouseNumberAndStreet(houseNumberAndStreetName);
                            currentContact.setCityName(cityName);
                            currentContact.setProvince(province);
                            currentContact.setPostalCode(postalCode);
                            break;
                        //Option 7 = edit phone number
                        case 4:
                            //Prompt the user for the contact's phone number, verify it and reset it
                            long phoneNumber;
                            do {
                                System.out.println("What is the edited phone number?");
                                keyboard = new Scanner(System.in);
                                String userEnteredPhoneNumber = keyboard.nextLine();
                                PhoneNumber number = new PhoneNumber();
                                phoneNumber = number.verifyPhoneNumber(userEnteredPhoneNumber);
                            } while (phoneNumber == 0);
                            currentContact.setPhoneNumber(phoneNumber);
                            break;
                        //Option 8 = edit email
                        case 5:
                            System.out.println("What is the edited email?");
                            String newEmail = keyboard.next();
                            currentContact.setEmail(newEmail);
                            break;
                        default:
                            System.out.println("An error occurred");
                            break;
                    }
                    System.out.println("Contact successfully edited.");
                }
            }
        }
        if(!matchFound){
            System.out.println("There are no contacts with that first name.");
        }
    }

    public void displayEditMenu() {
        System.out.println();
        System.out.println("1) First Name");
        System.out.println("2) Last Name");
        System.out.println("3) Address");
        System.out.println("4) Phone Number");
        System.out.println("5) Email");
        System.out.println();
        System.out.println("Please choose a detail to edit:");
    }

    public int getUserOption() throws Exception {
        //Retrieve the menu choice from the user
        //Continue to prompt the user if either the number entered is not in the menu or
        //if the user's entry is not an integer
        Scanner keyboard = new Scanner(System.in);
        int choice;
        do {
            try {
                choice = keyboard.nextInt();
                if (choice > 0 && choice < 6) {
                    break;
                } else {
                    System.out.println("Your entry must be a valid menu item (1 - 5). Try again");
                }
            } catch (InputMismatchException e) {
                System.out.println("Your entry must be a number. Try again");
                keyboard.next();
            }
        } while (true);
        return choice;
    }
    /*
    public void sortTheAddressBook () {

        Collections.sort(entries);

        for(AddressBookContact str: entries){
            System.out.println(str);
        }
    }
    */

    //Empty the arraylist of contacts in preparation to fill it
    public void clearContacts() {
        contacts.clear();
    }
}

package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {

    private ArrayList<AddressBookContact> contacts = new ArrayList<>();
    private Scanner keyboard = new Scanner(System.in);
    int index = 0;

    public void addContact() throws Exception {
        //Create the contact index
        index = index++;
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

    //Empty the arraylist of contacts in preparation to fill it
    public void clearContacts() {
        contacts.clear();
    }

    public void loadContactsFromFile() throws IOException {
        int i = 0;
        //Get the path to the file and open it
        try (BufferedReader br = Files.newBufferedReader(Paths.get("AddressBook.csv"))) {
            String loadedContactInfo;
            //Until there are no more lines to read from the file, loop through and add them to the list
            while ((loadedContactInfo = br.readLine()) != null) {
                //Split the loaded contact string into parts, separated by the ","
                String[] splitLoadedContactInfo = loadedContactInfo.split(",");

                //Store the parts into variables
                int index = Integer.valueOf(splitLoadedContactInfo[0]);
                i++;
                String firstName = splitLoadedContactInfo[1];
                String lastName = splitLoadedContactInfo[2];
                long phoneNumber = Long.parseLong(splitLoadedContactInfo[3]);
                String houseNumberAndStreetName = splitLoadedContactInfo[4];
                String cityName = splitLoadedContactInfo[5];
                String province = splitLoadedContactInfo[6];
                String postalCode = splitLoadedContactInfo[7];
                String email = splitLoadedContactInfo[8];

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
    }


    public void displayContacts() {
        System.out.println();

        for (int i = 0; i < 8; i++){
            Object currentContact = contacts.get(i);

        }

        /*
        //For each contact
        for (int i = 0; i < contacts.size(); i++) {
            //Store the pieces of information as divided by a ","
            String[] contactInfo = ((contacts.get(i)).toString()).split(",");
            int count = 0;
            for (String info : contactInfo) {
                if (count == 2) {
                    System.out.println("(" + info.substring(0, 3) + ")" + info.substring(3, 6) + "-" + info.substring(6, 10));
                } else {
                    System.out.println(info);
                }
                count++;
            }
            System.out.println();
        }
        */

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

    /*
    public void sortTheAddressBook () {

        Collections.sort(entries);

        for(AddressBookContact str: entries){
            System.out.println(str);
        }
    }
    */


}

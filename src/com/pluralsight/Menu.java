package com.pluralsight;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private AddressBook addressBook = new AddressBook();

    public void displayMenu() {
        System.out.println();
        System.out.println("1) Load from file");
        System.out.println("2) Save to file");
        System.out.println("3) Add an entry");
        System.out.println("4) Remove an entry");
        System.out.println("5) Edit an existing entry");
        System.out.println("6) Sort the address book");
        System.out.println("7) Search for a specific entry");
        System.out.println("8) Quit");
        System.out.println();
        System.out.println("Please choose an action:");
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
                if (choice > 0 && choice < 9) {
                    break;
                } else {
                    System.out.println("Your entry must be a valid menu item (1 - 8). Try again");
                }
            } catch (InputMismatchException e) {
                System.out.println("Your entry must be a number. Try again");
                keyboard.next();
            }
        } while (true);
        return choice;
    }

    public void performUserOption(int userOption) throws Exception {

        switch (userOption) {
            //Option 1 = load from file
            case 1:
                if (addressBook.getFileRead()){
                    System.out.println("Your contacts have already been loaded from the address book.");
                }
                else {
                    addressBook.loadContactsFromFile();
                    System.out.println("Contacts successfully loaded");
                }
                break;
            //Option 2 = save to file
            case 2:
                addressBook.saveToFile();
                System.out.println("Contacts successfully saved");
                break;
            //Option 3 = add an entry
            case 3:
                addressBook.addContact();
                System.out.println("Contact successfully added");
                break;
            //Option 4 = remove an entry
            case 4:
                addressBook.removeContact();
                break;
            //Option 5 = edit an entry
            case 5:
                addressBook.editContact();
                break;
            //Option 6 = sort the address book
            case 6:
                //addressBook.sortTheAddressBook();
                break;
            //Option 7 = search for an entry
            case 7:
                //searchForAnEntry();
                //addressBook.displayContacts();
                break;
            case 8:
                break;
            default:
                System.out.println("An error occurred");
                break;
        }
    }
}
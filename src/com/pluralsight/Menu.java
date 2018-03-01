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
            case 1:
                addressBook.clearContacts();
                addressBook.loadContactsFromFile();
                addressBook.displayContacts();
                break;
            case 2:
                addressBook.saveToFile();
                break;
            case 3:
                addressBook.addContact();
                break;
            case 4:
                //removeAnEntry();
                break;
            case 5:
                //editAnEntry();
                break;
            case 6:
                //addressBook.sortTheAddressBook();
                break;
            case 7:
                //searchForAnEntry();
                break;
            case 8:
                break;
            default:
                System.out.println("An error occurred");
                break;
        }
    }

}
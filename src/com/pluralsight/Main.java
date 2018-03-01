package com.pluralsight;

public class Main {

    /*
    https://programmingbydoing.com/
    Address book - Assignment #191
     */
    public static void main(String[] args) throws Exception {

        //Instantiate the menu
        Menu menu = new Menu();
        //Display the menu to the user for the first time
        menu.displayMenu();

        //Retrieve the user's choice from the menu
        int userOption = menu.getUserOption();

        //Perform the option requested by the user until they want to quit
        while(userOption != 8) {
            menu.performUserOption(userOption);
            menu.displayMenu();
            userOption = menu.getUserOption();
        }

        System.out.println();
        System.out.println("Good-bye!");

    }


}

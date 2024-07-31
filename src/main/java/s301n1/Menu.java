package s301n1;

import java.util.Scanner;

public class Menu {

    public static void start(Scanner sc) {

        System.out.println("Initializing... ");
        char option;
        Undo.setInstance();

        do {
            option = getMenuOption(sc);
            option = menuInit(option, sc);
        } while (option != '0');
        menuClose();

    }


    public static char getMenuOption(Scanner sc) {
        char option = 'X';

        System.out.println("Select the desired action ");
        System.out.println("i. Command input "); // añadir elemento a commandList mediante teclado
        System.out.println("d. Delete commands"); // usar undoOffset()
        System.out.println("n. Show commands in memory "); // use undoNote()
        System.out.println("r. Show commands in file"); // use undoRead()
        System.out.println("a. List commands in memory "); // usar undoAppend()
        System.out.println("w. File overwrite with last added commands"); // usar undoWrite()
        System.out.println("c. Clear commands in memory "); // usar undoClear()
        System.out.println("s. Store memory commands in file as a single line "); // use undoStore
        System.out.println("0. Exit menu ");

        String input = sc.nextLine();

        if (input.length() != 1) {
            System.out.println("Command input error. \n");
        } else {
            option = input.charAt(0);
            if (option == 'i' || option == 'd' || option == 'n' || option == 'r' || option == 'a' || option == 'w'
                    || option == 'c' || option == 's' || option == '0') {
                return option;
            }
            else {
                System.out.println("Input doesn't match an existing command. \n");
            }
        }

        return option;
    }


    public static char menuInit(char option, Scanner sc) {
        char exit = 'X';
        Undo.undoAdd(option);

        switch (option) {
            case 'i' -> Undo.undoInput(sc);
            case 'd' -> Undo.undoOffset(sc);
            case 'n' -> Undo.undoNote();
            case 'r' -> Undo.undoRead();
            case 'a' -> Undo.undoAppend();
            case 'w' -> Undo.undoWrite(sc);
            case 'c' -> Undo.undoClear();
            case 's' -> Undo.undoStore();
            case '0' -> {
                exit = menuCheck(sc);
                return exit;
            }
        }
        return exit;
    }


    public static char menuCheck(Scanner sc) {
        System.out.println(
                "You are about to exit the program, all unsaved entries will be lost. Press '0' again to confirm. ");

        char exit = sc.nextLine().charAt(0);
        if (exit != '0') {
            System.out.println("Termination aborted. Returning to the menu. \n");
        }

        return exit;
    }


    public static void menuClose() {
        System.out.println("Terminating program execution... ");
    }

}
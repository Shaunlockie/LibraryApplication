import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApp {
    public static boolean isRunning;
    static Scanner in = new Scanner(System.in);
    static ArrayList<User> users = new ArrayList<User>();

    public static void main(String[] args) {
        isRunning = true;
        programLoop();
    }

    public static void programLoop() {
        while (isRunning) {
            mainMenu();
            // pick user
            int input = readInt("->", 4);
            if (input == 1) {
                users.add(new User());
            }
            if (input == 2) {
                loginUser(users);
            }
            if (input == 3) {
                viewUsers(users);
            }
            if (input == 4) {
                isRunning = false;
            }
        }
    }

    public static void viewUsers(ArrayList<User> users) {
        for (User each : users)
            System.out.println(each.getUsername());
        int input = readInt("->", 1);
        if (input == 1) {
            mainMenu();
        }
    }

    public static void mainMenu() {
        clearConsole();
        System.out.println("What would you like to do?: ");
        System.out.println("1. Create a new user");
        System.out.println("2. Log in to current user");
        System.out.println("3. View all users");
        System.out.println("4.Exit program!");

    }

    public static void userMenu() {
        System.out.println("What would you like to do?: ");
        System.out.println("1. Create a new collection");
        System.out.println("2. View collections");
        System.out.println("3. logout");
    }

    public static int readInt(String prompt, int userChoices) {
        int input;
        do {
            System.out.println(prompt);
            try {
                input = Integer.parseInt(in.next());
            } catch (Exception e) {
                input = -1;
                System.out.println("Please input an integer!");
            }
        } while (input < 1 || input > userChoices);
        return input;
    }

    public static void clearConsole() {
        for (int i = 0; i < 1; i++) {
            System.out.println();
        }
    }

    public static void loginUser(ArrayList<User> users) {
        Scanner in = new Scanner(System.in);
        User selectedUser = null;
        boolean userSet = false;
        do {
            System.out.println("Please input a user");
            String userSearch = in.next();
            for (User user : users) {
                if (user.getUsername().contains(userSearch)) {
                    selectedUser = user;
                    userSet = true;
                };
            }
        }while (!userSet) ;
        System.out.println("Welcome " + selectedUser.getUsername());
        userMenu();
        int input = readInt("->", 1);
        if(input == 1){
            // Create a collection
        }if(input == 2){
            // view a collection
        }if(input == 2){
            selectedUser = null;
            userSet = false;
            mainMenu();
        }
    }
}



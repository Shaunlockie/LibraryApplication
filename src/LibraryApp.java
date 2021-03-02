import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApp {
    public static boolean isRunning;
    private static Scanner in = new Scanner(System.in);
    private static ArrayList<User> users = new ArrayList<>();
    private static User selectedUser = null;
    private static boolean userSet;

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
        programLoop();
        }

    public static void mainMenu(){
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
        System.out.println("2. Settings");
        System.out.println("3. logout");
        int input = readInt("->", 4);
        if (input == 1){
            userMenu();
        }if (input == 2){
            userMenu();
        }if (input == 3){
            settingsMenu();
        }
        if (input == 4){
            LogoutUser();
            userMenu();
        }
    }
    public static void settingsMenu(){
        System.out.println("Settings:");
        System.out.println("1. Change username");
        System.out.println("2. Go back");
        int input = readInt("->", 4);
        if (input == 1){
            changeUsername();
        }if (input == 2){
            userMenu();
        }
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
        do {
            System.out.println("Please input a user");
            String userSearch = in.next();
            for (User user : users) {
                if (user.getUsername().contains(userSearch)) {
                    selectedUser = user;
                    userSet = true;
                }
            }
        }while (!userSet) ;
        System.out.println("Welcome " + selectedUser.getUsername());
        userMenu();

    }
    public static void LogoutUser() {
        userSet = false;
        selectedUser = null;
        System.out.println("You are now logged out");
        programLoop();
    }

    public static void changeUsername() {
        String newUsername = in.next();
        selectedUser.setUsername(newUsername);
        System.out.println("Username changed! : " + selectedUser.getUsername());
        userMenu();
    }
    public static void createCollection(){

    }
}



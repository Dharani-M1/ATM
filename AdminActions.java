import java.util.ArrayList;
import java.util.Scanner;

public class AdminActions {


    // add a new user
    public void addUser(Scanner sc) {
        System.out.print("Enter new username: ");
        String username = sc.nextLine();


        for(Accounts accounts:Accounts.getUsers()){
         if(accounts.getUsername().equals(username)){
             System.out.println("User already exists!");
             return; // Exit the method if user exists
         }
        }
        System.out.print("Enter new password: ");
        String password = sc.nextLine();

        // Add the new user
        Accounts.getUsers().add(new User(username,password));
        System.out.println("User added successfully.");
/*
        // Check if the user already exists in the system
        for (User user : ATMSystem.getUsers()) {//to get username
            if (user.getUsername().equals(username)) {//check if equal
                System.out.println("User already exists!");
                return; // Exit the method if user exists
            }
        }

        System.out.print("Enter new password: ");
        String password = sc.nextLine();

        // Add the new user
        ATMSystem.getUsers().add(new User(username, password));
        System.out.println("User added successfully.");

 */
    }

    // Method to delete an existing user
    public void deleteUser(Scanner sc) {
        System.out.print("Enter username to delete: ");
        String username = sc.nextLine();

        Accounts userToDelete = null; // Initialize a variable to hold the user to be deleted

        // Search for the user
        for (Accounts accounts : Accounts.getUsers()) {
            if (accounts.getUsername().equals(username)) {//if equal
                userToDelete = accounts; // Assign the  user to the variable
                break; // Exit the loop once the user is found
            }
        }

        // Check if the user was found
        if (userToDelete != null) {
            Accounts.getUsers().remove(userToDelete); // Remove the user
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found."); // if the user is not found
        }
    }

    //  to view the list of registered users
    public void viewUsers() {
        System.out.println("Registered Users:");
        for (Accounts accounts : Accounts.getUsers()) {//to get username
          //  System.out.println("->" + Accounts.()); // Print each username
        }
    }

    // to view the total ATM balance
    public void viewAtmBalance() {
        System.out.println("ATM Balance: " + ATMSystem.getAtmbalance()); // Display the current ATM balance
    }

    // Method to the admin to deposit money into the ATM
    public void depositAtmBalance(Scanner sc) {
        System.out.print("Enter deposit amount: ");
        double amount = Double.parseDouble(sc.nextLine()); // Get the deposit amount

        // Check if the deposit amount is valid
        if (amount > 0) {
            System.out.println("Enter denomination counts:");
            System.out.print("2000: ");
            int count2000 = Integer.parseInt(sc.nextLine()); // Get the count of 2000 notes
            System.out.print("500: ");
            int count500 = Integer.parseInt(sc.nextLine()); // Get the count of 500 notes
            System.out.print("200: ");
            int count200 = Integer.parseInt(sc.nextLine()); // Get the count of 200 notes
            System.out.print("100: ");
            int count100 = Integer.parseInt(sc.nextLine()); // Get the count of 100 notes

            // check if deposit amount i sequal to denomination given
            if ((count2000 * 2000 + count500 * 500 + count200 * 200 + count100 * 100) == amount) {
                // Add the note counts
                ATMSystem.getNotes().add(new Notes(2000, count2000));
                ATMSystem.getNotes().add(new Notes(500, count500));
                ATMSystem.getNotes().add(new Notes(200, count200));
                ATMSystem.getNotes().add(new Notes(100, count100));

                // Update the ATM balance
                ATMSystem.setAtmbalance(ATMSystem.getAtmbalance() + amount);

                // Record the transaction
                ATMSystem.getTransactions().add(new Transaction("Deposit", amount));
                System.out.println("Deposit successful!");
            } else {
                System.out.println("Invalid denominations. Deposit failed."); // if denominations are incorrect
            }
        } else {
            System.out.println("Invalid amount entered."); //  if the amount is invalid
        }
    }

    //  to view a user's transaction history
    public void viewUserTransactions(Scanner sc) {
        System.out.print("Enter the username to view transactions: ");
        String username = sc.nextLine(); // Get the username to search for

        User user = null; // Initialize a variable to hold the user

        // Search for the user
        for (User u : ATMSystem.getUsers()) {
            if (u.getUsername().equals(username)) {//to check if username is equal
                user = u; // Assign the found user to the variable
                break; // Exit the loop once the user is found
            }
        }

        // Check if the user was found
        if (user != null) {
            System.out.println("Transaction history for " + username + ":");
            // Check if the user has any transactions
            if (user.getTransactions().isEmpty()) {//check if the transaction is empty
                System.out.println("No transactions found.");
            } else {
                // Print each transaction
                for (Transaction transaction : user.getTransactions()) {
                    System.out.println(transaction);
                }
            }
        } else {
            System.out.println("User not found."); // if the user was not found
        }
    }

    // to view an admin's transaction history
    public void viewAdminTransactions(Scanner sc) {
        System.out.print("Enter Admin Username to view transactions: ");
        String adminUsername = sc.nextLine(); // Get the admin username

        Accounts admin = null; // Initialize a variable to hold the admin

        // Search for the admin
        for (Accounts a : Accounts.getAdmins()) {
            if (a.getUsername().equals(adminUsername)) {
                admin = a; // Assign the found admin to the variable
                break; // Exit the loop once the admin is found
            }
        }

        // Check if the admin was found
        if (admin != null) {
            System.out.println("Transaction history for Admin " + adminUsername + ":");
            // Check if the admin has any transactions
            if (admin.g.isEmpty()) {//check if the transaction is empty
                System.out.println("No transactions found.");
            } else {
                // Print each transaction
                for (Transaction transaction : admin.getTransactions()) {
                    System.out.println(transaction);
                }
            }
        } else {
            System.out.println("Admin not found."); // if the admin was not found
        }
    }
}
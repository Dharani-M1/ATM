
import java.util.Scanner;

public class AdminActions {
    public static Accounts adminLog(Scanner sc) {
        System.out.print("Enter Admin Username: ");
        String adminUsername = sc.nextLine();
        Accounts admin = null;

        // Iterate through the list of admins
        for (Accounts a : Accounts.getAdmins()) {
            if (a.getUsername().equals(adminUsername)) {
                admin = a;
                break;
            }
        }

        // Check if the found account is an instance of Admin
        if (admin instanceof Admin) {
            System.out.print("Enter Admin Pin: ");
            String adminPin = sc.nextLine();

            // Verify the password
            if (admin.getPassword().equals(adminPin)) {
                return admin;
            } else {
                System.out.println("Invalid Pin.");
            }
        } else {
            System.out.println("Invalid Username.");
        }
        return null;
    }













    // Add a new user
    public void addUser(Scanner sc) {
        System.out.print("Enter new username: ");
        String username = sc.nextLine();

        // Check if the user already exists
        for (Accounts account : Accounts.getUsers()) {
            if (account.getUsername().equals(username)) {
                System.out.println("User already exists!");
                return;
            }
        }

        System.out.print("Enter new password: ");
        String password = sc.nextLine();

        // Add the new user
        Accounts.getUsers().add(new User(username, password));
        System.out.println("User added successfully.");
    }

    // Delete an existing user
    public void deleteUser(Scanner sc) {
        System.out.print("Enter username to delete: ");
        String username = sc.nextLine();

        Accounts userToDelete = null;

        for (Accounts account : Accounts.getUsers()) {
            if (account.getUsername().equals(username)) {
                userToDelete = account;
                break;
            }
        }

        if (userToDelete != null) {
            Accounts.getUsers().remove(userToDelete);
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    // View the list of registered users
    public void viewUsers() {
        System.out.println("Registered Users:");
        for (Accounts account : Accounts.getUsers()) {
            System.out.println("-> " + account.getUsername());
        }
    }

    // View the ATM balance
    public void viewAtmBalance() {
        System.out.println("ATM Balance: " + ATMSystem.getAtmbalance()); // Display the current ATM balance
    }

    // Deposit money into the ATM
    public void depositAtmBalance(Scanner sc) {
        System.out.print("Enter deposit amount: ");
        double amount = Double.parseDouble(sc.nextLine());

        if (amount > 0) {
            System.out.println("Enter denomination counts:");
            System.out.print("2000: ");
            int count2000 = Integer.parseInt(sc.nextLine());
            System.out.print("500: ");
            int count500 = Integer.parseInt(sc.nextLine());
            System.out.print("200: ");
            int count200 = Integer.parseInt(sc.nextLine());
            System.out.print("100: ");
            int count100 = Integer.parseInt(sc.nextLine());

            if ((count2000 * 2000 + count500 * 500 + count200 * 200 + count100 * 100) == amount) {
                Accounts.getTransactions().add(new Transaction("Deposit", amount,"admin"));
                System.out.println("Deposit successful!");
            } else {
                System.out.println("Invalid denominations. Deposit failed.");
            }
        } else {
            System.out.println("Invalid amount entered.");
        }
    }

    // View a user's transaction history
    public void viewUserTransactions(Scanner sc,Admin admin) {
        System.out.print("Enter the username to view transactions: ");
        String username = sc.nextLine();
        for (Accounts account : Accounts.getUsers()) {
            if (account instanceof Admin) {

                if (account.getTransactions().isEmpty()) {
                    System.out.println("No transactions found.");
                } else {
                    for (Transaction transaction : account.getTransactions()) {
                        System.out.println(transaction);
                    }
                }
            }
        }
    }

    // View an admin's transaction history
    public void viewAdminTransactions(Scanner sc,Admin admins) {
        System.out.print("Enter Admin Username to view transactions: ");
        String adminUsername = sc.nextLine();

        Accounts admin=null;
        for (Accounts account : Accounts.getAdmins()) {
            if (account.getUsername().equals(adminUsername)) {
                admin = account;
                break;
            }
        }
        if (admin instanceof Admin) {
            if (admin != null) {
                System.out.println("Transaction history for Admin " + adminUsername + ":");
                if (Accounts.getTransactions().isEmpty()) {
                    System.out.println("No transactions found.");
                } else {
                    for (Transaction transaction : Accounts.getTransactions()) {
                        System.out.println(transaction);
                    }
                }
            } else {
                System.out.println("Admin not found.");
            }
        }
    }
}

import Notes.Notes;

import java.util.ArrayList;
import java.util.Scanner;


public class AdminActions {
    public static Accounts adminLog(Scanner sc) {
        System.out.print("Enter Admin Username: ");
        String adminUsername = sc.nextLine();
        Accounts admin = null;

        // Iterate through the list of admins
        for (Accounts a : ATMSystem.accounts()) {
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
        for (Accounts account : ATMSystem.accounts()) {
            if (account.getUsername().equals(username)) {
                System.out.println("User already exists!");
                return;
            }
        }

        System.out.print("Enter new password: ");
        String password = sc.nextLine();
        User newuser=new User(username,password,0);

        // Add the new user
        ATMSystem.accounts().add(newuser);
        System.out.println("User added successfully.");
    }

    // Delete an existing user
    public void deleteUser(Scanner sc) {
        System.out.print("Enter username to delete: ");
        String username = sc.nextLine();

        Accounts userToDelete = null;

        for (Accounts user : ATMSystem.accounts()) {
            if (user.getUsername().equals(username)) {
                userToDelete = user;
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
        if(ATMSystem.accounts().isEmpty()){
            System.out.println("No users available");

        }
        else{
            for(Accounts accounts:ATMSystem.accounts()){
                if(accounts instanceof User){
                    User user=(User)accounts;
                    System.out.println("User Name:"+user.getUsername());
                }
            }
        }
    }

    // View the ATM balance
    public void viewAtmBalance() {
        System.out.println("ATM Balance: " + ATMSystem.getAtmbalance()); // Display the current ATM balance
    }

    // Deposit money into the ATM
    public void depositAtmBalance(Scanner sc,Admin admin) {
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
                admin.getTransactions().add(new Transaction("Admin", "deposit",amount));



                updateNoteCount(2000, count2000);
                updateNoteCount(500, count500);
                updateNoteCount(200, count200);
                updateNoteCount(100, count100);



                System.out.println("Deposit Sucessfull");
            } else {
                System.out.println("Invalid denominations. Deposit failed.");
            }
        } else {
            System.out.println("Invalid amount entered.");
        }
    }

    // View a user's transaction history
    public void viewUserTransactions(Scanner sc) {
        System.out.print("Enter the username to view transactions: ");
        String username = sc.nextLine();
        for (Accounts account : ATMSystem.accounts()) {
            if (account instanceof User) {
                ArrayList<Accounts>userlist=ATMSystem.accounts();
                User user=null;
                for(Accounts u:userlist)
                {
                    if(u.getUsername().equals(username)){
                        user=(User) u;
                        break;
                    }
                }
                if(user==null){
                    System.out.println("User not found");
                }
                else{
                    ArrayList<Transaction>transactionhis=user.getTransactions();
                    if(transactionhis.isEmpty()){
                        System.out.println("No Transaction History found");
                    }
                    else {
                        System.out.println(transactionhis);

                    }
                }
            }
        }
    }

    // View an admin's transaction history
    public void viewAdminTransactions(Admin admins) {
        for (Accounts account : ATMSystem.accounts()) {
            if (account instanceof Admin){
                if(account.getTransactions().isEmpty()){
                    System.out.println("No Admin Transactions");
                }
                else{
                    System.out.println("Admin Transaction History:");
                    for(Transaction transaction:admins.getTransactions()){
                        System.out.println(transaction);
                    }
                }
            }

        }

    }
    private void updateNoteCount(int denomination, int count) {
        // Iterate through the notes and update the count for the given denomination
        for (Notes note : ATMSystem.getNotes()) {
            if (note.getDenomination() == denomination) {
                note.setCount(note.getCount() + count);
                return;
            }
        }

    }
}
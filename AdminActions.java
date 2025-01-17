import Notes.Notes;


import java.util.Scanner;


public class AdminActions {
    // Method to log in  admin by username and password
    public static Accounts adminLog(Scanner sc) {
        System.out.print("Enter Admin Username: ");
        String adminUsername = sc.nextLine();
        Accounts adminn = null;

        // Iterate through the list of account to find admin
        for (Accounts a : ATMSystem.accounts()) {
            if(a instanceof Admin ) {// Check if the found account is an instance of Admin
                if (a.getUsername().equals(adminUsername)) {
                    adminn = a;
                    break;
                }
            }
        }


        if (adminn!=null) {
            System.out.print("Enter Admin Pin: ");
            String adminPin = sc.nextLine();
            // Verify the password
            if (adminn.getPassword().equals(adminPin)) {
                return adminn;
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
        String password = sc.nextLine();//read the password
        User newuser=new User(username,password,0); //assign to newuser

        // Add the new user
        ATMSystem.accounts().add(newuser);//add newuser to accounts
        System.out.println("User added successfully.");
    }

    // Delete an existing user
    public void deleteUser(Scanner sc) {
        System.out.print("Enter username to delete: ");// read username
        String username = sc.nextLine();

        Accounts userToDelete = null;
        // Iterate through the list of account to find the username
        for (Accounts user : ATMSystem.accounts()) {
            if (user.getUsername().equals(username)) {
                userToDelete = user;
                break;
            }
        }

        if (userToDelete != null) {
            ATMSystem.accounts().remove(userToDelete);//remove username from Accounts
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    // View the list of registered users
    public void viewUsers() {
        if(ATMSystem.accounts().isEmpty()){// if empty
            System.out.println("No users available");

        }
        else{
            for(Accounts accounts:ATMSystem.accounts()){
                if(accounts instanceof User user){//check accounts isinstanceof User
                    System.out.println("User Name:"+user.getUsername());//print username
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
        //read denomination
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
                admin.getTransactions().add(new Transaction(admin.getUsername(), "deposit",amount));


                //Update Notes
                updateNoteCount(2000, count2000);
                updateNoteCount(500, count500);
                updateNoteCount(200, count200);
                updateNoteCount(100, count100);
                //Update ATM Balance
                ATMSystem.setAtmbalance(ATMSystem.getAtmbalance() + amount);



                System.out.println("Deposit Sucessfull");
            } else {
                System.out.println("Invalid denominations. Deposit failed.");//if denomination fails
            }
        } else {
            System.out.println("Invalid amount entered.");//if invalid amount
        }
    }


    // Method to View a user's transaction history
    public void viewUserTransactions(Scanner sc) {
        System.out.print("Enter the username to view transactions: ");
        String username = sc.nextLine();// read username
        boolean a=false;
        for (Accounts account : ATMSystem.accounts()) {
            if (account instanceof User) {//check account instance of User
                if (account.getUsername().equals(username)) {//check username
                    a=true;
                    if(account.getTransactions().isEmpty()){//check if empty
                        System.out.println("No Transaction History found");

                    }
                    else{
                        System.out.println("Admin Transaction History:");
                        for(Transaction transaction:account.getTransactions()){
                            System.out.println(transaction);//print transactions
                        }
                    }
                }
            }

        }
        if(!a){//if user not found
            System.out.println("User not found");
        }


    }

    public void viewAdminTransactions(Scanner sc) {
        System.out.print("Enter the username to view transactions: ");
        String username = sc.nextLine();// read username
        boolean a=false;
        for (Accounts account : ATMSystem.accounts()) {
            if (account instanceof Admin) {//check account instance of Admin
                if (account.getUsername().equals(username)) {//check username
                    a=true;
                    if(account.getTransactions().isEmpty()){//check if empty
                        System.out.println("No Transaction History found");

                    }
                    else{
                        System.out.println("Admin Transaction History:");
                        for(Transaction transaction:account.getTransactions()){
                            System.out.println(transaction);//print transactions
                        }
                    }
                }
            }

        }
        if(!a){//if Admin not found
            System.out.println("Admin not found");
        }


    }


    private void updateNoteCount(int denomination, int count) {
        // Iterate through the notes and update the count for the given denomination
        for (Notes note : ATMSystem.getNotes()) {
            if (note.getDenomination() == denomination) {
                note.setCount(note.getCount() + count);//set count
                return;
            }
        }

    }
}
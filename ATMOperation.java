import java.util.Scanner;

public class ATMOperation {

    private AdminActions adminActions;
    private UserActions userActions;


    public ATMOperation() {
        this.adminActions = new AdminActions();
        this.userActions = new UserActions();

        // Adding default admin accounts to the system
        ATMSystem.accounts().add(new Admin("admin","1234"));
        ATMSystem.accounts().add(new Admin("admin1","1234"));

    }
    // Method to start the ATM system
    public void start() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        // Loop to display the main menu
        while (!exit) {
            System.out.println("\nATM System Menu:");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    // Admin login process
                    Admin a=(Admin) AdminActions.adminLog(sc);
                    if(a!=null){
                        adminActionsMenu(sc,adminActions,a);
                    }
                    break;
                case 2:
                    // User login process
                    User user=(User)UserActions.userLog(sc);

                    if(user!=null) {
                        userActionsMenu(sc,user);
                        break;
                    }
                    break;
                case 3:
                    // Exit the system
                    System.out.println("Exiting the ATM system...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }



    // Method to display  admin actions menu
    private void adminActionsMenu(Scanner sc,AdminActions adminActions,Admin admin) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add User");
            System.out.println("2. Delete User");
            System.out.println("3. View User List");
            System.out.println("4. View ATM Balance");
            System.out.println("5. Deposit Amount");
            System.out.println("6. View User Transactions");
            System.out.println("7. View Admin Transactions");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());// Read admin's choice

            switch (choice) {
                case 1:
                    // Add a new user
                    adminActions.addUser(sc);
                    break;
                case 2:
                    // Delete an existing user
                    adminActions.deleteUser(sc);
                    break;
                case 3:
                    // View the list of all users
                    adminActions.viewUsers();
                    break;
                case 4:
                    // View the total balance in the ATM
                    adminActions.viewAtmBalance();
                    break;
                case 5:
                    // Deposit money into the ATM
                    adminActions.depositAtmBalance(sc,admin);
                    break;
                case 6:
                    // View transactions made by users
                    adminActions.viewUserTransactions(sc);
                    break;
                case 7:
                    // View transactions made by admins
                    adminActions.viewAdminTransactions(admin);
                    break;
                case 8:
                    // Exit the admin menu
                    exit = true;
                    break;
                default:
                    //invalid choices
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }



    // Method to display user actions menu
    private void userActionsMenu(Scanner sc,User user) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nUser Menu:");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. View Balance");
            System.out.println("4. View Transaction History");
            System.out.println("5. Change Pin");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());//Read user choice

            switch (choice) {
                case 1:
                    // Deposit money to the user's account
                    userActions.depositMoney(sc, user);
                    break;
                case 2:
                    // Withdraw money from the user's account
                    userActions.withdrawMoney(sc, user);
                    break;
                case 3:
                    // View the user's current balance
                    userActions.viewBalance(user);
                    break;
                case 4:
                    // View the user's transaction history
                    userActions.viewTransactionHistory(user);
                    break;
                case 5:
                    // Change the user's PIN
                    userActions.changePin(user, sc);
                    break;
                case 6:
                    // exit the user menu
                    exit = true;
                    break;
                default:
                    // invalid choices
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
import java.util.ArrayList;
import java.util.Scanner;

public class ATMOperation {

    private AdminActions adminActions;
    private UserActions userActions;
    private ATMSystem atmSystem;
  // private ArrayList<Admin>admins;


    // Constructor
    public ATMOperation(ATMSystem atmSystem) {
    this.atmSystem = atmSystem;
    this.adminActions = new AdminActions();
    this.userActions = new UserActions();
  //  this.admins=new ArrayList<>();

    }
    public void start() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        atmSystem.getAdmins().add(new Admin("admin",1234));
        atmSystem.getAdmins().add(new Admin("admin1",1234));


        while (!exit) {
            System.out.println("\nATM System Menu:");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            //int choice = sc.nextInt();
            int choice = Integer.parseInt(sc.nextLine());


            if(choice==1) {
                adminlog(sc,atmSystem);

            }
            else if (choice==2) {
                userLog(sc,atmSystem.getNotes(),atmSystem.getTransactions());


            } else if (choice==3) {
                System.out.println("Exiting the ATM system...");
                exit = true;
            }
            else{
                System.out.println(("Invalid Choice"));
            }
        }
    }

    public void adminlog(Scanner sc, ATMSystem atmSystem) {
        System.out.println("Enter Admin Username: ");
        String adminUsername = sc.nextLine();


        Admin adminn = null;
        for (Admin a : atmSystem.getAdmins()) {
            if (a.getAdminName().equals(adminUsername)) {
                adminn = a;
                break;
            }
        }

        if (adminn != null) {
            System.out.println("Enter Admin Pin: ");
            int adminPin = Integer.parseInt(sc.nextLine());
            if (adminPin == adminn.getAdminPin()) {
                adminActionsMenu(sc,adminn);
            } else {
                System.out.println("Invalid Pin");
            }
        } else {
            System.out.println("Invalid Username");
        }

    }

    private void adminActionsMenu(Scanner sc,Admin admin) {
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
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    adminActions.addUser(sc, atmSystem.getUsers());
                    break;
                case 2:
                    adminActions.deleteUser(sc, atmSystem.getUsers());
                    break;
                case 3:
                    adminActions.viewUsers(atmSystem.getUsers());
                    break;
                case 4:
                    adminActions.viewAtmBalance(atmSystem);
                    break;
                case 5:
                    adminActions.depositAtmBalance(sc, atmSystem,atmSystem.getNotes(),atmSystem.getTransactions(),admin);
                    break;
                case 6:
                    adminActions.viewUserTransactions(sc, atmSystem.getUsers(), atmSystem.getTransactions());
                    break;
                case 7:
                    adminActions.viewAdminTransactions(sc,atmSystem.getTransactions(), atmSystem);
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // User Login Process
    public void userLog(Scanner sc,ArrayList<Notes>notes,ArrayList<Transaction>transactions) {
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        User user = null;
        for (User u : atmSystem.getUsers()) {
            if (u.getUsername().equals(username)) {
                user = u;
                break;
            }
        }

        if (user != null) {
            System.out.println("Enter User Pin: ");
            String password = sc.nextLine();
            if (password.equals(user.getPassword())) {
                userActionsMenu(sc,user,notes,transactions,userActions);
            } else {
                System.out.println("Invalid Pin");
            }
        } else {
            System.out.println("Invalid Username");
        }
    }

    // User Menu Options
    private void userActionsMenu(Scanner sc, User user,ArrayList<Notes>notes,ArrayList<Transaction>transactions,UserActions userActions) {
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
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    userActions.depositMoney(sc, user, atmSystem, atmSystem.getNotes(),transactions);
                    break;
                case 2:
                    userActions.withdrawMoney(sc, user, notes,atmSystem);
                    break;
                case 3:
                    userActions.viewBalance(user);
                    break;
                case 4:
                    userActions.viewTransactionHistory(user);
                    break;
                case 5:
                    userActions.chancepin(user, sc);
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
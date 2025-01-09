import java.util.ArrayList;
import java.util.Scanner;
import Notes.Notes;

public class UserActions {
    public static Accounts userLog(Scanner sc) {
        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        Accounts user = null;

        // Iterate through the list of users
        for (Accounts u : ATMSystem.accounts()) {
            if (u.getUsername().equals(username)) {
                user=u;
                break;
            }
        }

        // Check if the found account is an instance of User
        if (user instanceof User) {
            System.out.print("Enter User Pin: ");
            String password = sc.nextLine();

            // Verify the password
            if (password.equals(user.getPassword())) {
                return (User) user;  // Safe cast after instanceof check
            } else {
                System.out.println("Invalid Pin.");
            }
        } else {
            System.out.println("Invalid Username.");
        }

        return null;
    }

    // to view the user's current balance
    public void viewBalance(User user) {
        System.out.println("Current Balance: " + user.getBalance());
    }

    // to change the user's PIN
    public void changePin(User user, Scanner sc) {
        System.out.println("Enter New PIN:");
        String newPin = sc.nextLine(); // Read the new PIN from the user
        System.out.println("Confirm New PIN:");
        String confirmPin = sc.nextLine(); // Read the confirmation PIN

        // Check if the new PIN matches the confirmation PIN
        if (newPin.equals(confirmPin)) {
            user.setPassword(confirmPin); // setPassword stores the PIN
            System.out.println("PIN changed successfully.");
        } else {
            System.out.println("PIN mismatch. Try again.");
        }
    }

    // to view the transaction history of the user
    public void viewTransactionHistory(User user) {
        for(Accounts account:ATMSystem.accounts()){
            if(account instanceof User){
                if(account.equals(user)){
                    ArrayList<Transaction>transactionshis=user.getTransactions();
                    if(transactionshis.isEmpty()){
                        System.out.println("No Transaction History found");
                    }
                    else {
                        for(Transaction transaction:transactionshis){
                            System.out.println(transaction);

                        }

                    }
                }

            }

        }
        System.out.println("Transaction history for " + user.getUsername() + ":");

        // Check if the user has any transaction history

    }


    // Method to withdraw money

    // Method to withdraw money from the ATM
    public void withdrawMoney(Scanner sc, User user) {
        System.out.print("Enter amount to withdraw: ");
        double amount = Double.parseDouble(sc.nextLine()); // Read the withdrawal amount

        // Validate the entered amount
        if (amount <= 0) {
            System.out.println("Invalid amount entered.");
            return;
        }

        // Check if the user has sufficient balance
        if (amount > user.getBalance()) {
            System.out.println("Insufficient balance.");
            return;
        }

        int remainingAmount = (int) amount; // Convert amount to an integer for calculations
        int totalATMAvailable = 0; // Variable to store total ATM balance

        // Calculate total balance available in the ATM based on the notes
        for (Notes note : ATMSystem.getNotes()) {
            totalATMAvailable += note.getDenomination() * note.getCount();
        }

        // Check if the ATM has enough money for the requested withdrawal
        if (remainingAmount > totalATMAvailable) {
            System.out.println("ATM does not have enough balance.");
            return;
        }

        // Variables to calculate required notes
        int required2000 = 0, required500 = 0, required200 = 0, required100 = 0;

        // Calculate the required number of notes for the withdrawal
        for (Notes note : ATMSystem.getNotes()) {
            int denomination = note.getDenomination();
            int count = note.getCount();

            if (denomination == 2000) {
                required2000 = Math.min(remainingAmount / 2000, count);
                remainingAmount -= required2000 * 2000;
            } else if (denomination == 500) {
                required500 = Math.min(remainingAmount / 500, count);
                remainingAmount -= required500 * 500;
            } else if (denomination == 200) {
                required200 = Math.min(remainingAmount / 200, count);
                remainingAmount -= required200 * 200;
            } else if (denomination == 100) {
                required100 = Math.min(remainingAmount / 100, count);
                remainingAmount -= required100 * 100;
            }
        }

        // Check if the withdrawal is possible with the available denominations
        if (remainingAmount > 0) {
            System.out.println("ATM has No available denominations.");
            return;
        }

        // Deduct the notes from the ATM
        for (Notes note : ATMSystem.getNotes()) {
            if (note.getDenomination() == 2000) {
                note.setCount(note.getCount() - required2000);
            } else if (note.getDenomination() == 500) {
                note.setCount(note.getCount() - required500);
            } else if (note.getDenomination() == 200) {
                note.setCount(note.getCount() - required200);
            } else if (note.getDenomination() == 100) {
                note.setCount(note.getCount() - required100);
            }
        }

        // Update the user's balance and ATM balance
        user.setBalance(user.getBalance() - amount);
        ATMSystem.setAtmbalance(ATMSystem.getAtmbalance() - amount);

        // Add the transaction to the user's transaction history
        // user.getTransactions().add(new Transaction("User", "Withdraw",amount));
        user.getTransactions().add(new Transaction("User","withdraw",amount));

        // Display the successful withdrawal message and notes Withdrawn count
        System.out.println("Withdrawal successful. Notes Withdrawn:");
        if (required2000 > 0) {
            System.out.println("2000 x " + required2000);
        }
        if (required500 > 0) {
            System.out.println("500 x " + required500);
        }
        if (required200 > 0) {
            System.out.println("200 x " + required200);
        }
        if (required100 > 0) {
            System.out.println("100 x " + required100);
        }
    }

    // Method to deposit money into the ATM
    public void depositMoney(Scanner sc, User user) {
        System.out.print("Enter deposit amount: ");
        double amount = Double.parseDouble(sc.nextLine()); // Read the deposit amount

        // Read the count of each denomination
        System.out.println("Enter denomination counts:");
        System.out.print("2000: ");
        int count2000 = Integer.parseInt(sc.nextLine());
        System.out.print("500: ");
        int count500 = Integer.parseInt(sc.nextLine());
        System.out.print("200: ");
        int count200 = Integer.parseInt(sc.nextLine());
        System.out.print("100: ");
        int count100 = Integer.parseInt(sc.nextLine());

        // Validate the total amount based on the entered denominations
        if ((count2000 * 2000 + count500 * 500 + count200 * 200 + count100 * 100) == amount) {
            user.setBalance(user.getBalance() + amount); // Update user's balance
            ATMSystem.setAtmbalance(ATMSystem.getAtmbalance() + amount);// Update ATM balance
            user.getTransactions().add(new Transaction("user","deposit",amount));
            //  Accounts.getTransactions().add(new Transaction("user", "deposit",amount)); // Add deposit transaction

            // Update the note counts in the ATM
            updateNoteCount(2000, count2000);
            updateNoteCount(500, count500);
            updateNoteCount(200, count200);
            updateNoteCount(100, count100);

            System.out.println("Deposit successful!");
        } else {
            System.out.println("Invalid denominations. Deposit failed.");
        }
    }

    //  method to update the note count
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
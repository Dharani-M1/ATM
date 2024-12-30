import java.util.ArrayList;
import java.util.Scanner;

public class UserActions {
    public void depositMoney(Scanner sc, User user, ATMSystem atm, ArrayList<Notes> notes, ArrayList<Transaction> transactions) {
        System.out.print("Enter deposit amount: ");
        double amount = Double.parseDouble(sc.nextLine());

        System.out.println("Enter denomination counts:");
        System.out.print("2000: ");
        int count2000 = Integer.parseInt(sc.nextLine());
        System.out.print("500: ");
        int count500 = Integer.parseInt(sc.nextLine());
        System.out.print("200: ");
        int count200 = Integer.parseInt(sc.nextLine());
        System.out.print("100: ");
        int count100 = Integer.parseInt(sc.nextLine());

        if ((count2000*2000+count500 * 500 + count200 * 200 + count100 * 100) == amount) {
            user.setBalance(user.getBalance() + amount);
            atm.setAtmbalance(atm.getAtmbalance() + amount);
            user.getTransactions().add(new Transaction("Deposit",amount));
            //transactions.add(new Transaction("Deposit",amount));

            notes.add(new Notes(2000, count2000));
            notes.add(new Notes(500, count500));
            notes.add(new Notes(200, count200));
            notes.add(new Notes(100, count100));
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Invalid denominations. Deposit failed.");
        }
    }



    public void viewBalance(User user) {
        System.out.println("Current Balance: " + user.getBalance());
    }



    public void chancepin(User user, Scanner sc) {
        System.out.println("Enter New Pin:");
        String newpin = sc.nextLine();
        System.out.println("Confirm New Pin:");
        String conpin = sc.nextLine();
        if (newpin.equals(conpin)) {
            user.setPassword(conpin);
        }
    }


    public void viewTransactionHistory(User user) {
        System.out.println("Transaction history for " + user.getUsername() + ":");
        for (Transaction transaction : user.getTransactions()) {
            System.out.println(transaction);
        }
    }
  public void withdrawMoney(Scanner sc, User user, ArrayList<Notes> notes, ATMSystem atm) {
      System.out.print("Enter amount to withdraw: ");
      double amount = Double.parseDouble(sc.nextLine());

      if (amount <= 0) {
          System.out.println("Invalid amount entered.");
          return;
      }

      if (amount > user.getBalance()) {
          System.out.println("Insufficient balance.");
          return;
      }

      int remainingAmount = (int) amount;
      int totalATMAvailable = 0;

      // Calculate total ATM balance from notes
      for (Notes note : notes) {
          totalATMAvailable += note.getDenomination() * note.getCount();
      }

      if (remainingAmount > totalATMAvailable) {
          System.out.println("ATM does not have enough balance.");
          return;
      }

      // Prepare to calculate the withdrawal
      int required2000 = 0, required500 = 0, required200 = 0, required100 = 0;

      for (Notes note : notes) {
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

      // Check if the withdrawal is possible with available denominations
      if (remainingAmount > 0) {
          System.out.println("ATM cannot dispense the exact amount with available denominations.");
          return;
      }

      // Deduct notes from ATM and update balances
      for (Notes note : notes) {
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

      user.setBalance(user.getBalance() - amount);
      atm.setAtmbalance(atm.getAtmbalance() - amount);
      user.getTransactions().add(new Transaction("withdraw", amount));

      // Display successful withdrawal
      System.out.println("Withdrawal successful. Notes Withdrawn:");
      if (required2000 > 0) System.out.println("2000 x " + required2000);
      if (required500 > 0) System.out.println("500 x " + required500);
      if (required200 > 0) System.out.println("200 x " + required200);
      if (required100 > 0) System.out.println("100 x " + required100);
  }




}



















import java.util.ArrayList;

public class ATMSystem {

    private static double atmbalance;
   // private static ArrayList<User> users=new ArrayList<>();//declaration of new arraylist for user
   // private static ArrayList<Admin> admins=new ArrayList<>();//declaration of new arraylist for admin
    private static ArrayList<Notes> notes=new ArrayList<>();//declaration of new arraylist for notes
    private static ArrayList<Transaction> transactions=new ArrayList<>();//declaration of new arraylist for transactions
    private static ArrayList<Accounts>accounts=new ArrayList<>();


    public static ArrayList<Accounts>accounts(){

        return accounts;
    }
    public static double getAtmbalance() {
        return atmbalance;
    }

    public static void setAtmbalance(double atmbalance) {
        ATMSystem.atmbalance = atmbalance;
    }

  //  public static ArrayList<User> getUsers() {
  //      return users;
  //}



  //  public static ArrayList<Admin> getAdmins() {
   //     return admins;
  //  }

   // public static void setAdmins(ArrayList<Admin> admins) {
  //      ATMSystem.admins = admins;
  //  }

    public static ArrayList<Notes> getNotes() {
        return notes;
    }

    public static void setNotes(ArrayList<Notes> notes) {
        ATMSystem.notes = notes;
    }

    public static ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public static void setTransactions(ArrayList<Transaction> transactions) {
        ATMSystem.transactions = transactions;
    }
}
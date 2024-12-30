import java.util.ArrayList;

public class ATMSystem {

    private double atmbalance;
    private ArrayList<User> users;
    private ArrayList<Admin> admins;
    private ArrayList<Notes> notes;
    private ArrayList<Transaction> transactions;


    // Default Constructor
    public ATMSystem() {
        this.users = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.atmbalance = 0.0;
    }


    public ATMSystem(double atmbalance, ArrayList<User> users, ArrayList<Admin> admins,
                     ArrayList<Notes> notes, ArrayList<Transaction> transactions) {
        this.atmbalance = atmbalance;
        this.users = users;
        this.admins = admins;
        this.notes = notes;
        this.transactions = transactions;
    }

    // Getters and Setters
   public double getAtmbalance() {
        return atmbalance;
    }

    public void setAtmbalance(double atmbalance) {
        this.atmbalance = atmbalance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }

    public ArrayList<Notes> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Notes> notes) {
        this.notes = notes;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
}
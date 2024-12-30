import java.util.ArrayList;

public class Admin {
    private String adminName;
    private int adminPin;
    private ArrayList<Transaction> transactions;

    public Admin(String adminName, int adminPin) {
        this.adminName = adminName;
        this.adminPin = adminPin;
        this.transactions=new ArrayList<>();
    }

    public Admin() {

    }

    // Getters and Setters
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public int getAdminPin() {
        return adminPin;
    }

    public void setAdminPin(int adminPin) {
        this.adminPin = adminPin;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
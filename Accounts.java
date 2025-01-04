import java.util.ArrayList;

public class Accounts {
    private String username;
    private String password;

    private static ArrayList<User>users;
    private static ArrayList<Admin>admins;
    private static ArrayList<Transaction> transactions;

    public Accounts(String username,String password){
        this.username=username;
        this.password=password;
        this.users=new ArrayList<>();
        this.admins=new ArrayList<>();

    }

    public Accounts(double balance) {
    }


    //  public Accounts() {

   // }

    public String getUsername(){
        return username;
    }

    public void setUsername(){
        this.username=username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(){
        this.password=password;
    }

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setAdmins(ArrayList<Admin> admins) {
        Accounts.admins = admins;
    }

    public static void setUsers(ArrayList<User> users) {
        Accounts.users = users;
    }

    public static ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public static void setTransactions(ArrayList<Transaction> transactions) {
        Accounts.transactions = transactions;
    }
}

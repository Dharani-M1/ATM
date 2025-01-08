import java.util.ArrayList;

public class User extends Accounts {
    // private String username;
    // private String password;
    private double balance;
    protected User user;
    public User(String username,String password,double balance){
        super(username,password);
        this.balance=balance;
    }




    /*  public User(String username, String password) {

          this.username = username;
          this.password = password;

          this.balance = 0.0;
          this.transactions = new ArrayList<>();
      }

     */


/*
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

 */

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // public ArrayList<Transaction> getTransactions() {
    //      return transactions;
    //  }
}

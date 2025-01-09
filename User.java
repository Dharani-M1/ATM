import java.util.ArrayList;

public class User extends Accounts {

    private double balance;
    protected User user;
    public User(String username,String password,double balance){
        super(username,password);
        this.balance=balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


}

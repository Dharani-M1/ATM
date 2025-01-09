import java.util.ArrayList;

public class Accounts {
    private String username;
    private String password;

    private static ArrayList<User>users=new ArrayList<>();
    private static ArrayList<Admin>admins=new ArrayList<>();
    private  ArrayList<Transaction> transactions=new ArrayList<>();

    public Accounts(String username,String password){
        this.username=username;
        this.password=password;


    }

    public Accounts() {

    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }


    public static ArrayList<User> getUsers() {
        return users;
    }



    public  ArrayList<Transaction> getTransactions() {
        return transactions;
    }



    public void  setPassword(String confirmPin) {
        this.password=confirmPin;
    }
}
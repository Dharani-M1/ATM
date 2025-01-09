import java.util.ArrayList;

public class Accounts {
    private String username;
    private String password;


    private  ArrayList<Transaction> transactions=new ArrayList<>();

    protected Accounts(String username,String password){
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






    public  ArrayList<Transaction> getTransactions() {
        return transactions;
    }



    public void  setPassword(String confirmPin) {
        this.password=confirmPin;
    }
}
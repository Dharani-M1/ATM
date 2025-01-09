import java.util.ArrayList;
import java.util.Arrays;
import Listofnotes.*;
import Notes.Notes;

public class ATMSystem {

    private static double atmbalance;
    // private static ArrayList<User> users=new ArrayList<>();//declaration of new arraylist for user
    // private static ArrayList<Admin> admins=new ArrayList<>();//declaration of new arraylist for admin
    private static ArrayList<Notes> notes=new ArrayList<>(Arrays.asList(new Notes2000(0),new Notes500(0),new Notes200(0),new Notes100(0)));//declaration of new arraylist for notes
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



    public static ArrayList<Notes> getNotes() {
        return notes;
    }



}
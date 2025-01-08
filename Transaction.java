public class Transaction {
    private String type;
    private double amount;
    private String name;



    public Transaction(String type, double amount,String name) {
        this.type = type;
        this.amount = amount;
        this.name=name;


    }


    @Override
    public String toString() {
        return "Type: " + type + ", Amount: " + amount +"BY"+name;
    }
}
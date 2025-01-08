public class Transaction {
    private String type;
    private double amount;
    private String name;



    public Transaction(String name,String type, double amount) {
        this.name=name;
        this.type = type;
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "Type: " + type + ", Amount: " + amount +"BY"+name;
    }
}
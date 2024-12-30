public class Transaction {
    private String type;
    private double amount;



    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;


    }

    // Getters
    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }


    @Override
    public String toString() {
        return "Type: " + type + ", Amount: " + amount;
    }
}

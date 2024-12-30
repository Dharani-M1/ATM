public  class Main {

    public static void main(String[] args) {
        ATMSystem atmSystem=new ATMSystem();
        ATMOperation atmOperation=new ATMOperation(atmSystem);
        atmOperation.start();
    }
}
package Notes;

public class Notes  {
    private int denomination;
    private int count;

    public Notes(int denomination,int count){
        this.denomination=denomination;
        this.count=count;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }

    public int getDenomination(){
        return denomination;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount(){
        return count;
    }



}
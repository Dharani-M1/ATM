
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
class Notes2000 extends Notes{
    public Notes2000(int count){
        super(2000,count);
    }
}
class Notes500 extends Notes {
    public Notes500(int count){

        super(500,count);
    }
}
 class Notes200 extends Notes {
    public Notes200(int count){
        super(200,count);
    }
}
 class Notes100 extends Notes {
    public Notes100(int count){
        super(100,count);
    }
}




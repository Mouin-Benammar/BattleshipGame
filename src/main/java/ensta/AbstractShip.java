package ensta;
import java.util.Arrays;
abstract class  AbstractShip
{
    private Character label;
    private  String name;
    private int size;//length,width
    private Oriontation oriontation;
    private int strikeCount;

    public void addStrike(){
        //number of strikes possible
    }
    public boolean isSunk(){
        return false;
    }
    public int getStrikeCount() {
        return strikeCount;
    }

    public void setStrikeCount(int strikeCount) {
        this.strikeCount = strikeCount;
    }

    public AbstractShip(Character label, String name, int size, Oriontation oriontation) {
        this.label = label;
        this.name = name;
        this.size = size;
        this.oriontation = oriontation;
    }
    public int getSize() {
        return size;
    }

    public Character getLabel() {
        return label;
    }



    public String getName() {
        return name;
    }


    public Oriontation getOriontation() {
        return oriontation;
    }

    public void setOriontation(Oriontation oriontation) {
        this.oriontation = oriontation;
    }


}

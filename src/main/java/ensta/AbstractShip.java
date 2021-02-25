package ensta;
import java.io.Serializable;
import java.util.Arrays;
abstract class  AbstractShip implements Serializable
{
    private Character label;
    private  String name;
    private int size;//length,width
    private Oriontation oriontation;
    private int strikeCount;
    /**
     * Get if a ship is placed at the given position
     * @param x
     * @param y
     * @return the board name
     */
    public void addStrike(){
        (strikeCount)++;

    }
    /**
     * Get if a ship is placed at the given position
     * @param x
     * @param y
     * @return the board name
     */
    public boolean isSunk(){
        return this.getStrikeCount()==this.getSize();

    }
    /**
     * Get if a ship is placed at the given position
     * @param x
     * @param y
     * @return the board name
     */
    public int getStrikeCount() {
        return strikeCount;
    }


    /**
     * Get if a ship is placed at the given position
     * @param x
     * @param y
     * @return the board name
     */
    public AbstractShip(Character label, String name, int size, Oriontation oriontation) {
        this.label = label;
        this.name = name;
        this.size = size;
        this.oriontation = oriontation;
    }
    /**
     * Get if a ship is placed at the given position
     * @param x
     * @param y
     * @return the board name
     */
    public int getSize() {
        return size;
    }
    /**
     * Get if a ship is placed at the given position
     * @param x
     * @param y
     * @return the board name
     */
    public Character getLabel() {
        return label;
    }

    /**
     * Get if a ship is placed at the given position
     * @param x
     * @param y
     * @return the board name
     */

    public String getName() {
        return name;
    }
    /**
     * Get if a ship is placed at the given position
     * @param x
     * @param y
     * @return the board name
     */

    public Oriontation getOriontation() {
        return oriontation;
    }
    /**
     * Get if a ship is placed at the given position
     * @param x
     * @param y
     * @return the board name
     */
    public void setOriontation(Oriontation oriontation) {
        this.oriontation = oriontation;
    }



}

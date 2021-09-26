package ensta.Board;

import ensta.Game.ColorUtil;
import ensta.Input.InvalidCoordenatesException;
import ensta.Ships.Oriontation;
import ensta.Ships.ShipState;
import ensta.Ships.AbstractShip;
import ensta.Game.Hit;
import java.io.Serializable;

public class Board implements IBoard, Serializable {
    protected String name;
    protected ShipState[][] ships ;
    protected Boolean[][] attacks;
    public Board(String n ){
        this.name=n;
        this.ships=new ShipState[10][10];
        this.attacks=new Boolean[10][10];
    } /**
     * Constructor
     * @param ship The ship to place on the board
     * @param n the name of the board
     * @param length the board one dimensional size
     */
    public Board(String n ,  int length){
        this.name=n;
        this.ships=new ShipState[length][length];
        this.attacks=new Boolean[length][length];
    }
    /**
     * @return the board name
     */
    public String getName() {
        return name;
    }
    /**

     * @return the shipState board
     */
    public ShipState[][] getShips() {
        return ships;
    }
    /**
     * Get if a ship is placed at the given position
     * @param x  horizontal coordenate
     * @param y vertical coordenate
     *  @param ship to be placed
     * @return the board name
     */
    public void setShips(int x, int y, AbstractShip ship){
        ships[x][y].setReference(ship);
        ships[x][y].setStruck(false);
    }

    /**

     * @return the Attacks board
     */
    public Boolean[][] getAttacks() {
        return attacks;
    }


    public void setAttacks(int x , int y, Boolean b){
        this.attacks [x][y] = b;
    }

    /**

     * @return prints the current state of the ships and attacks board
     */
    public  void print(){
        System.out.println("This is  board "+getName()+" state :");
        System.out.println("The attacks map is : ");
        for (int i = 0; i < getAttacks().length; i++)
        {   if(i==0){
            System.out.print("   ");
            for(int k=0;k<getAttacks()[i].length;k++){
                System.out.print((char)(k+ (int)'A'));
                System.out.print("    ");
            }
            System.out.println();
        }


            if(i<9) System.out.print(i+1 + "  ");
            else  System.out.print(i+1 + " ");
            for (int j = 0;  j < getAttacks()[i].length; j++){
                if(getAttacks()[i][j]!=null){
                    if (this.getShips()[i][j]!=null)
                        System.out.print(ColorUtil.colorize("X" +  "    ", ColorUtil.Color.RED));
                    else System.out.print("X"+"    ");}
                else  System.out.print("." + "    ");
            }
            System.out.println();
        }
        System.out.println("The ships map is : ");
        for (int i = 0; i < getShips().length; i++)
        {   if(i==0){
            System.out.print("   ");
            for(int k=0;k<ships[i].length;k++){
                System.out.print((char)(k+ (int)'A'));
                System.out.print("    ");
            }
            System.out.println();
        }
            if(i<9) System.out.print(i+1 + "  ");
            else  System.out.print(i+1 + " ");
            for (int j = 0;  j < this.getShips()[i].length; j++){


                if(null != this.ships[i][j]&&hasShip(i,j)) {
                    if (attacks[i][j]!=null)
                        System.out.print(ColorUtil.colorize(ships[i][j].toString() + "    ", ColorUtil.Color.RED));
                    else System.out.print(ships[i][j].toString() +"    " ) ;
                }
                else  System.out.print("." + "    ");
            }
            System.out.println();
        }
    }

    public int getSize(){
        return this.getShips().length;
    }
    /**
     * Get if a ship is placed at the given position
     * @param x horizontal coordenate
     * @param y vertical coordenate
     */
    public boolean hasShip(int x, int y){
        if(ships[x][y]!=null) { if(!ships[x][y].getReference().isSunk())return true;}
        return false;
    }
    /**
     * place a ship at the given position
     * @param x horizontal coordenate
     * @param y vertical coordenate
     * @param ship the ship to be placed
     * @return true if the ship was placed correctly
     */
    public boolean putShip(AbstractShip ship, int x, int y) {
        int s=ship.getSize();
        Oriontation o=ship.getOriontation();
        Character l = ship.getLabel();
        switch (o){
            case EAST:
                try{
                if ((x+s)>=this.getSize()||notintercepted(x,y,s,o)) throw new InvalidCoordenatesException();
                }
                catch(InvalidCoordenatesException e){
                    System.out.println(e.getMessage());
                    return false;
                }
                  for(int k=0;k<s;k++) this.ships[x+k][y]=new ShipState(ship,false);
                break;

            case WEST:
                try{
                    if ((x-s)<0||this.notintercepted(x,y,s,o)){

                        throw new InvalidCoordenatesException();
                    }}
                catch(InvalidCoordenatesException e){
                        System.out.println(e.getMessage());
                        return false;
                }
                for(int k=0;k<s;k++) this.ships[x-k][y]=new ShipState(ship,false);
                break;

            case NORTH:
                try{
                    if ((y-s)<0||notintercepted(x,y,s,o)){

                        throw new InvalidCoordenatesException();
                    }}
                catch(InvalidCoordenatesException e){
                    System.out.println(e.getMessage());
                    return false;
                }
                for(int k=0;k<s;k++) ships[x][y-k]=new ShipState(ship,false);
                break;

            default:
                try{
                    if ((y+s)>=this.getSize()||notintercepted(x,y,s,o)){

                        throw new InvalidCoordenatesException();
                    }}
                catch(InvalidCoordenatesException e){
                    System.out.println(e.getMessage());
                    return false;
                }
                for(int k=0;k<s;k++) this.ships[x][y+k]=new ShipState(ship,false);
                break;
        }
        return true;
    }
    /**
     * Get if the ship to be placed at that position may intercept another
     * @param x
     * @param y
     * @param o oriontation
     * @param size the "to be placed" ship size
     * @return true if no ship may intercept the placement of this ship
     */
 public boolean notintercepted(int x , int y , int size , Oriontation o){
        switch (o){
            case EAST:
                for (int i=0; i<size;i++){
                    if(hasShip(x+i,y)) return true;
                }

                break;
            case NORTH:
                for (int i=0; i<size;i++){
                    if(hasShip(x,y-i)) return true;
                }

                break;
            case WEST:
                for (int i=0; i<size;i++){
                    if(hasShip(x-i,y)) return true;
                }

                break;
            default:
                for(int i=0; i<size;i++){
                    if(hasShip(x,y+i)) return true;
                }

                break;
        }
        return false;

 }

    public void setHit(Boolean hit, int x, int y){
        setAttacks(x,y,hit);

    }
    /**
     * **
     * if an atack and a ship exist in the same spot , the hit was successful
     * @return  true in that case
     */
    public Boolean getHit(int x, int y){
        if(x<0 || y<0 ||x>= ships.length||y>=ships.length)  return false;
        return attacks[x][y] ;//&& hasShip(x,y)

    }

    /**
     * Sends a hit at the given position
     * @param x
     * @param y
     * @return status for the hit (eg : strike or miss)
     * if a ship is sunk , it will no longer apear on the board
     */

    public Hit sendHit(int x, int y){
        // if a stike happens in a spot already damaged , it will have no effect
      setHit(true,x,y);
      if(getHit(x,y)!=null && getHit(x,y)&&hasShip(x,y)){
          this.ships[x][y].addStrike();
          this.ships[x][y].isStruck();
          if(this.ships[x][y].isSunk()){
              switch (this.ships[x][y].getReference().getLabel()){
                  case 'D':

                      System.out.println(String.format("%c  coulé,  struck at the coordenates %d %d ",this.ships[x][y].getReference().getLabel(),x,y));

                      return Hit.valueOf("DESTROYER");
                  case 'S':
                      System.out.println(String.format("%c  coulé,  struck at the coordenates %d %d ",this.ships[x][y].getReference().getLabel(),x,y));

                      return Hit.valueOf("SUBMARINE");
                  case 'B':

                      System.out.println(String.format("%c  coulé, struck at the coordenates %d %d ",this.ships[x][y].getReference().getLabel(),x,y));

                      return Hit.valueOf("BATTLESHIP");

                  default:


                      System.out.println(String.format("%c  coulé, struck at the coordenates %d %d ",this.ships[x][y].getReference().getLabel(),x,y));

                      return Hit.valueOf("CARRIER");
              }
          }
          else {
              System.out.println(String.format("Successful hit  at the coordenates %d %d ! ",x,y));
              return Hit.valueOf("STRUCK");
          }
      }
        System.out.println(String.format(" hit missed at the coordenates %d %d ",x,y));
      return Hit.valueOf("MISS");
    }

}


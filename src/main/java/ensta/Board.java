package ensta;
import java.util.Vector;
public class Board implements IBoard  {
    protected String name;
    protected ShipState[][]  ships;
    protected Boolean[][] attacks;

    public Board(String n ,  int length){
        this.name=n;
        this.ships=new ShipState[length][length];
        this.attacks=new Boolean[length][length];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setShips(int x , int y, Character c) {
        this.ships[x][y] = c;
    }

    public Character[][] getShips() {
        return ships;
    }

    public Boolean[][] getAttacks() {
        return attacks;
    }

    public void setAttacks(int x , int y, Boolean b){
        this.attacks [x][y] = b;
    }

    public Board(String n ){
        this.name=n;
        this.ships=new Character[10][10];
        this.attacks=new Boolean[10][10];
    }
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
                if(getAttacks()[i][j]!=null)  System.out.print(getAttacks()[i][j] + " ") ;
                else  System.out.print("." + "    ");
            }
            System.out.println();
        }
        System.out.println("The ships map is : ");
        for (int i = 0; i < getShips().length; i++)
        {   if(i==0){
            System.out.print("   ");
            for(int k=0;k<getShips()[i].length;k++){
                System.out.print((char)(k+ (int)'A'));
                System.out.print("    ");
            }
            System.out.println();
        }
            if(i<9) System.out.print(i+1 + "  ");
            else  System.out.print(i+1 + " ");
            for (int j = 0;  j < getShips()[i].length; j++){


                if(getShips()[i][j]!=null)  System.out.print(getShips()[i][j] + "    ") ;
                else  System.out.print("." + "    ");
            }
            System.out.println();
        }
    }
    public int getSize(){
        return this.getShips().length;
    }

    public boolean hasShip(int x, int y){
        if(getShips()[x][y]!=null) return true;
        return false;
    }
    public boolean putShip(AbstractShip ship, int x, int y) {
        int s=ship.getSize();
        Oriontation o=ship.getOriontation();
        Character l = ship.getLabel();
        switch (o){
            case EAST:
                try{
                if ((x+s)>this.getSize()||notintercepted(x,y,s,o)) throw new InvalidCoordenatesException();
                }
                catch(InvalidCoordenatesException e){
                    System.out.println(e.getMessage());
                    return false;
                }
                for(int i=0;i<s;i++)  this.setShips(x+i,y,l);
                break;

            case WEST:
                try{
                    if ((x+s)>this.getSize()||notintercepted(x,y,s,o)){

                        throw new InvalidCoordenatesException();
                    }}
                catch(InvalidCoordenatesException e){
                        System.out.println(e.getMessage());
                        return false;
                }
                for(int i=0;i<s;i++)  this.setShips(x-i,y,l);
                break;

            case NORTH:
                try{
                    if ((x+s)>this.getSize()||notintercepted(x,y,s,o)){

                        throw new InvalidCoordenatesException();
                    }}
                catch(InvalidCoordenatesException e){
                    System.out.println(e.getMessage());
                    return false;
                }
                for(int i=0;i<s;i++)  this.setShips(x,y-i,l);
                break;

            default:
                try{
                    if ((x+s)>this.getSize()||notintercepted(x,y,s,o)){

                        throw new InvalidCoordenatesException();
                    }}
                catch(InvalidCoordenatesException e){
                    System.out.println(e.getMessage());
                    return false;
                }
                for(int i=0;i<s;i++)  this.setShips(x,y+i,l);
                break;
        }
        return true;
    }
    public void setHit(boolean hit, int x, int y){
        setAttacks(x,y,hit);

    }

    public Boolean getHit(int x, int y){
        /**
         * **
         * @return if an atack and a ship exist in the same spot , the hit was successful
         */
        return hasShip(x,y)&& getAttacks()[x][y];
    }
 public boolean notintercepted(int x , int y , int size , Oriontation o){
        switch (o){
            case EAST:
                for (int i=0; i<size;i++){
                    if(null != this.getShips()[x + i][y]) return false;
                }

                break;
            case NORTH:
                for (int i=0; i<size;i++){
                    if(null!=this.getShips()[x][y-i]) return false;
                }

                break;
            case WEST:
                for (int i=0; i<size;i++){
                    if(null!=this.getShips()[x-i][y]) return false;
                }

                break;
            default:
                for (int i=0; i<size;i++){
                    if(null!=this.getShips()[x][y+i]) return false;
                }

                break;
        }
        return true;

 }

}


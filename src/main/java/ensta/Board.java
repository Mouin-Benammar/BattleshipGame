package ensta;
public class Board<ships> implements IBoard  {
    protected String name;
    protected ShipState[][] ships ;
    protected Boolean[][] attacks;
    public Board(String n ){
        this.name=n;
        this.ships=new ShipState[10][10];
        this.attacks=new Boolean[10][10];
    }
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
    public ShipState[][] getShips() {
        return ships;
    }
    public void setShips(int x, int y,AbstractShip s){
        ships[x][y].setReference(s);
        ships[x][y].setStruck(false);
    }

    public Boolean[][] getAttacks() {
        return attacks;
    }

    public void setAttacks(int x , int y, Boolean b){
        this.attacks [x][y] = b;
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
                    if (ships[i][j].isStruck())
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

    public boolean hasShip(int x, int y){
        if(ships[x][y]!=null) { if(!ships[x][y].getReference().isSunk())return true;}
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
                    if ((y+s)>this.getSize()||notintercepted(x,y,s,o)){

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
     * @return if an atack and a ship exist in the same spot , the hit was successful
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

    public     Hit sendHit(int x, int y){
        // if a stike happens in a spot already damaged , it will have no effect
        Character[] types ={'D','S','B','C'};
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


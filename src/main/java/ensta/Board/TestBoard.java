package ensta.Board;

import ensta.Board.Board;
import ensta.Ships.*;

public class TestBoard {
    public  static void main(String[] args) {
        Board deff2 = new Board("deff2", 12);
        Board bor = new Board("bor", 10);


        Destroyer D = new Destroyer();
        Carrier C = new Carrier(Oriontation.valueOf("WEST"));
        Battleship b = new Battleship();
        Submarine S=new Submarine(Oriontation.valueOf("NORTH"));
        Submarine S1=new Submarine(Oriontation.valueOf("NORTH"));
        bor.putShip(b, 0, 0);
        deff2.putShip(C, 5, 6);
        deff2.putShip(S,5,6);
        deff2.putShip(S1,5,7);
        bor.putShip(D, 5, 6);
        deff2.sendHit(7, 8);

       for(int i=0 ;i<C.getSize();i++){
            deff2.sendHit(5-i,6); }
        deff2.putShip(S1,5,7);
        deff2.sendHit(5, 6);
        deff2.sendHit(8, 9);

        deff2.print();
        //bor.print();
    }
}

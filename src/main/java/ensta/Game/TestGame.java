package ensta.Game;

import ensta.Board.Board;
import ensta.Ships.*;
import ensta.Ships.AbstractShip;
import java.util.List;
import java.util.Arrays;

public class TestGame {
    public static void main(String arg[]){
        Board board=new Board("Game Board ",15);
        List<AbstractShip> shipList =Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new Battleship(), new Carrier()});
        BattleShipsAI ai =new BattleShipsAI(board,board);
        int destroyedShips=0;
        ai.putShips(shipList);
        int coordenates[]=new int[2];
        coordenates=new int[2];
        List<Hit> shipTypes=Arrays.asList(new Hit[]{Hit.valueOf("CARRIER"), Hit.valueOf("SUBMARINE"), Hit.valueOf("DESTROYER"), Hit.valueOf("BATTLESHIP")});
        Hit hit;
        while(destroyedShips<=4){

            hit=ai.sendHit(coordenates);
            if(shipTypes.contains(hit)) destroyedShips++;
            board.print();

            sleep(2000);
        }


    }
    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

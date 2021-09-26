package ensta.Players;
import ensta.Board.Board;
import ensta.Game.Hit;
import ensta.Input.InputHelper;
import ensta.Ships.AbstractShip;
import ensta.Ships.Oriontation;

import java.io.Serializable;
import java.util.List;

public class Player implements Serializable{
    /* **
     * Attributs
     */
    public Board board;
    protected    Board opponentBoard;
    public   int destroyedCount;
    protected  AbstractShip[] ships;
    public boolean lose;

    /* **
     * Constructeur
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    /* **
     * Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coodrinates.
     */
    public void putShips() {
        boolean done = false;
        int i = 0;

        do {
            AbstractShip s = ships[i];
            String msg = String.format("placer %d : %s(%d)", i + 1, s.getName(), s.getSize());
            System.out.println(msg);
            InputHelper.ShipInput res = InputHelper.readShipInput();
            // TODO set ship orientation
            // TODO put ship at given position
            s.setOriontation(Oriontation.valueOf(res.orientation));
            boolean b= this.board.putShip(s, res.x, res.y);
            if(!b) i--;//if ship placement not successful
            else this.board.print();// if ship placement  successful
            // TODO when ship placement successful
            ++i;
            done = i == 5;

            board.print();
        } while (!done);
    }
    /**
     * Get if a ship is placed at the given position
     * @param x
     * @param y
     * @return the board name
     */
    public Hit sendHit(int coords[]) {
        boolean done=false;/////////
        Hit hit = null;

        do {
            System.out.println("où frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            // TODO call sendHit on this.opponentBoard
            hit=this.opponentBoard.sendHit(hitInput.y,hitInput.x);
            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            // return hit is obvious. But how to return coords at the same time ?
            if(hit!=null) done=true;
            coords[0]=hitInput.x;
            coords[1]=hitInput.y;

        } while (!done);

        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}

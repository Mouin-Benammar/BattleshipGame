package ensta.Players;
import ensta.Ships.BattleShipsAI;
import ensta.Board.Board;
import ensta.Game.Hit;
import ensta.Ships.AbstractShip;

import java.util.Arrays;
import java.util.List;

public class AIPlayer extends Player {
    /*
     * ** Attribut
     */
    private transient BattleShipsAI ai;

    /*
     * ** Constructeur
     */
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

    // TODO AIPlayer must not inherit "keyboard behavior" from player. Call ai
    // instead.


    public void putShips() {
        ai.putShips(Arrays.asList(this.ships));
    }

    public Hit sendHit(int[] coords) {
        return ai.sendHit(coords);
    }

    @Override
    public AbstractShip[] getShips() {
        return super.getShips();
    }

    @Override
    public void setShips(AbstractShip[] ships) {
        super.setShips(ships);
    }
}

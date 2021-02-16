package ensta;

public class TestBoard {
    public  static void main(String[] args) {
        Board deff2 = new Board("deff2", 12);
        Board bor = new Board("bor", 10);


        Destroyer D = new Destroyer();
        Carrier C = new Carrier(Oriontation.valueOf("WEST"));
        Battleship b = new Battleship();
        bor.putShip(b, 0, 0);
        deff2.putShip(C, 5, 6);
        bor.putShip(D, 5, 6);
        deff2.sendHit(7, 8);
        bor.setHit(true, 5, 6);
        deff2.sendHit(5, 6);
        deff2.sendHit(8, 9);

        deff2.print();
        bor.print();
    }
}

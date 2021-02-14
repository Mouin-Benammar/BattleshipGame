package ensta;

public class TestBoard {
    public  static void main(String[] args){
        Board deff= new Board("deff2");
        Board deff2= new Board("deff2",12);
        deff.print();
        deff2.print();
        Destroyer D;
        D = new Destroyer();
        deff2.putShip(D,5,6);
        deff2.setHit(true,7,8);
        deff2.print();
    }
}

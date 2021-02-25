package ensta;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game implements Serializable {

    /*
     * *** Constante
     */
    public static final File SAVE_FILE = new File("savegame.dat");

    /*
     * *** Attributs
     */
    private Player player1;
    private Player player2;
    private Scanner sin;
    private int multyplayer;

    /*
     * *** Constructeurs
     */
    public Game() {
    }

    public Game init() {
        if(!loadSave()){
        // init attributes
            System.out.println("Pour le mode multijoueur taper 2:\n Si non, taper 1 pour une partie  Contre l'Ia");
            Scanner mode =new Scanner(System.in);
            multyplayer=mode.nextInt();
            if(multyplayer==1){
            System.out.println("entre ton nom:");
            // TODO use a scanner to read player name
            sin =new Scanner(System.in);
            String playerName=sin.nextLine();
            // TODO init boards
            Board b1, b2;
            System.out.println("entre la taille de board:");
            int taille= sin.nextInt();
            b1= new Board("Player 1 Board",taille);
            b2= new Board("Player 2 Board",taille);
            // TODO init this.player1 & this.player2
            List<AbstractShip> shipList_1 =Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new Battleship(), new Carrier()});
            List<AbstractShip> shipList_2 =Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new Battleship(), new Carrier()});
            player1=new Player(b1,b2,shipList_1);
            player2=new AIPlayer(b2,b1,shipList_2);
            b1.print();
            // place player ships
            player1.putShips();
            player2.putShips();
        }
            else{
                System.out.println("entre le nom du joueur 1:");
                sin =new Scanner(System.in);
                String player_1_Name=sin.nextLine();
                System.out.println("entre le nom du joueur 2:");
                String player_2_Name=sin.nextLine();
                Board b1, b2;
                System.out.println("entre la taille de board:");
                int taille= sin.nextInt();
                b1= new Board("Player 1 Board",taille);
                b2= new Board("Player 2 Board",taille);
                // TODO init this.player1 & this.player2
                List<AbstractShip> shipList_1 =Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new Battleship(), new Carrier()});
                List<AbstractShip> shipList_2 =Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new Battleship(), new Carrier()});
                player1=new Player(b1,b2,shipList_1);
                player2=new Player(b2,b1,shipList_2);
                b1.print();
                // place player ships
                player1.putShips();
                player2.putShips();

            }
        }
        return this;
    }

    /*
     * *** Méthodes
     */
    public void run() {
        if(multyplayer==1){
        int[] coords = new int[2];
        Board b1 = player1.board;
        Hit hit;

        // main loop
        b1.print();
        boolean done;
        do {
            hit = Hit.MISS; // TODO player1 send a hit
            hit=player1.sendHit(coords);
            boolean strike = hit != Hit.MISS; // TODO set this hit on his board (b1)

            done = updateScore();
            b1.print();
            System.out.println(makeHitMessage(false, coords, hit));

            save();

            if (!done && !strike) {
                do {
                    hit = Hit.MISS; // TODO player2 send a hit.
                    hit=player2.sendHit(coords);
                    strike = hit != Hit.MISS;
                    if (strike) {
                        b1.print();
                    }
                    System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                    done = updateScore();

                    if (!done) {
                         save();
                    }
                } while (strike && !done);
            }

        } while (!done);}
        else{
            int[] coords = new int[2];
            Board b1 = player1.board;
            Hit hit;

            // main loop
            b1.print();
            player2.board.print();
            boolean done;
            do {
                hit = Hit.MISS; // TODO player1 send a hit
                hit=player1.sendHit(coords);
                boolean strike = hit != Hit.MISS; // TODO set this hit on his board (b1)

                done = updateScore();
                b1.print();
                System.out.println(makeHitMessage(false, coords, hit));

                save();

                if (!done && !strike) {
                    do {
                        hit = Hit.MISS; // TODO player2 send a hit.
                        hit=player2.sendHit(coords);
                        strike = hit != Hit.MISS;
                        player2.board.print();
                        System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                        done = updateScore();

                        if (!done) {
                            save();
                        }
                    } while (strike && !done);
                }

            } while (!done);
        }

        SAVE_FILE.delete();
        System.out.println(String.format("joueur %d gagne", player1.lose ? 2 : 1));
        sin.close();
    }

    private void save() {
     try {
            //TODO bonus 2 : uncomment
             if (!SAVE_FILE.exists()) {
             SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();

             }
             // TODO bonus 2 : serialize players
         FileOutputStream fos = new FileOutputStream("t.tmp");
         ObjectOutputStream oos = new ObjectOutputStream(fos);
         oos.writeObject(this);
         oos.close();

       } catch (IOException e) {
           e.printStackTrace();
       }
      }

      private boolean loadSave() {
         if (SAVE_FILE.exists()) {
             try {
                 FileInputStream fis = new FileInputStream("t.tmp");
                 ObjectInputStream ois = new ObjectInputStream(fis);
                // TODO bonus 2 : deserialize players
                  Game g=(Game) ois.readObject();

                 ois.close();
                return true;
             } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
   }
        return false;
     }

    private boolean updateScore() {
        for (Player player : new Player[] { player1, player2 }) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips()) {
                if (ship.isSunk()) {
                    destroyed++;
                }
            }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;
    }

    private String makeHitMessage(boolean incoming, int[] coords, Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
            case MISS:
                msg = hit.toString();
                break;
            case STRUCK:
                msg = hit.toString();
                color = ColorUtil.Color.RED;
                break;
            default:
                msg = hit.toString() + " coulé";
                color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>", ((char) ('A' + coords[0])),
                (coords[1] + 1), msg);
        return ColorUtil.colorize(msg, color);
    }

    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[] { new Destroyer(), new Submarine(), new Submarine(), new Battleship(),
                new Carrier() });
    }
}


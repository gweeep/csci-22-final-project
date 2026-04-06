import java.awt.Color;
public class GameStarter {
    public static void main(String[] args) {
        Player p1 = new Player(100, 384, Color.GREEN, 1);
        Player p2 = new Player(900, 384, Color.RED, 2);
        GameCanvas canvas = new GameCanvas(p1, p2, 2); // Level 2
        new GameFrame(canvas, p1).setVisible(true);
    }
}
import java.awt.Color;
import javax.swing.SwingUtilities;

public class GameStarter {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            player p1 = new player(100, 384, Color.GREEN);
            player p2 = new player(900, 384, Color.RED);
            GameCanvas canvas = new GameCanvas(p1, p2);
            GameFrame frame = new GameFrame(canvas, p1);
            frame.setVisible(true);
        });
    }
}
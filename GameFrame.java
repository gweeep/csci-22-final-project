import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class GameFrame extends JFrame {
    public GameFrame(GameCanvas canvas, Player p) {
        this.setTitle("CSCI 22 Final: Nature Tron");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(canvas);
        this.pack();
        this.setLocationRelativeTo(null);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_UP -> p.setDirection(0, -2);
                    case KeyEvent.VK_DOWN -> p.setDirection(0, 2);
                    case KeyEvent.VK_LEFT -> p.setDirection(-2, 0);
                    case KeyEvent.VK_RIGHT -> p.setDirection(2, 0);
                }
            }
        });
    }
}
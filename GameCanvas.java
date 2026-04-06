
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class GameCanvas extends JComponent implements Runnable {
   
    private player player1;
    private player player2;
    private Thread gameThread;
    private boolean running = true;

   
    public GameCanvas(player p1, player p2) {
    
        this.setPreferredSize(new Dimension(1024, 768)); 
        this.setBackground(new Color(20, 40, 20));
        
        this.player1 = p1;
        this.player2 = p2;
 
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (running) {
            updateGame();
            checkCollisions();
            repaint(); 
            
            try {
                Thread.sleep(20); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateGame() {
        if (player1 != null) player1.move();
        if (player2 != null) player2.move();
    }

    private void checkCollisions() {

        if (player1.x < 0 || player1.x > 1024 || player1.y < 0 || player1.y > 768) {
            running = false;
            System.out.println("Player 1 crashed into a wall!");
        }
        
        // Boundary check for Player 2
        if (player2.x < 0 || player2.x > 1024 || player2.y < 0 || player2.y > 768) {
            running = false;
            System.out.println("Player 2 crashed into a wall!");
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        

        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

   
        if (player1 != null) player1.draw(g);
        if (player2 != null) player2.draw(g);
    }
}
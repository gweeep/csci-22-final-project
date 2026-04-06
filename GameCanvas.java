import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class GameCanvas extends JComponent implements Runnable {
    private Player localPlayer, remotePlayer;
    private ArrayList<Rectangle> obstacles;
    private boolean running = true;

    public GameCanvas(Player p1, Player p2, int level) {
        this.localPlayer = p1;
        this.remotePlayer = p2;
        this.setPreferredSize(new Dimension(1024, 768)); 
        this.obstacles = MapData.getLevel(level);
        new Thread(this).start(); 
    }

    public void run() {
        while (running) {
            if (localPlayer != null) localPlayer.move();
            checkCollisions();
            repaint();
            try { Thread.sleep(20); } catch (Exception e) {}
        }
    }

    private void checkCollisions() {
        if (localPlayer.x < 0 || localPlayer.x > 1024 || localPlayer.y < 0 || localPlayer.y > 768) {
            running = false;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(20, 40, 20)); // Nature Background
        g.fillRect(0, 0, 1024, 768);

        g.setColor(new Color(101, 67, 33)); // Tree/Obstacle color
        for (Rectangle r : obstacles) g.fillRect(r.x, r.y, r.width, r.height);

        if (localPlayer != null) localPlayer.draw(g);
        if (remotePlayer != null) remotePlayer.draw(g);
    }
}
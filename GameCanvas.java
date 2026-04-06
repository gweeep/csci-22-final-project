import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameCanvas extends JComponent implements Runnable {
    private Player p1, p2;
    private ArrayList<Rectangle> obstacles;
    private boolean running = true;

    public GameCanvas() {
        this.setPreferredSize(new Dimension(1024, 768));
        this.obstacles = new ArrayList<>();
    }

    public GameCanvas(Player p1, Player p2, int level) {
        this();
        this.p1 = p1;
        this.p2 = p2;
        this.obstacles = MapData.getLevel(level); 
        new Thread(this).start(); 
    }

    public void run() {
        while (running) {
            if (p1 != null) p1.move();
            repaint();
            try { Thread.sleep(20); } catch (Exception e) {}
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(20, 40, 20)); 
        g.fillRect(0, 0, 1024, 768);

        g.setColor(new Color(101, 67, 33)); 
        for (Rectangle r : obstacles) {
            g.fillRect(r.x, r.y, r.width, r.height);
        }

        if (p1 != null) p1.draw(g);
        if (p2 != null) p2.draw(g);
    }
}

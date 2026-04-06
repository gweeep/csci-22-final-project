import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class player extends GameObject {
    private int dx = 2, dy = 0; 
    private ArrayList<Point> trail = new ArrayList<>();
    private boolean isAlive = true;

    public player(int x, int y, Color color) {
        super(x, y, color);
    }

    public void move() {
        if (!isAlive) return;
        trail.add(new Point(x, y)); 
        x += dx;
        y += dy;
    }

    public void setDirection(int newDx, int newDy) {
  
        if ((newDx != 0 && dx == 0) || (newDy != 0 && dy == 0)) {
            this.dx = newDx;
            this.dy = newDy;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        for (Point p : trail) {
            g.fillRect(p.x, p.y, 5, 5); 
        }
        g.fillRect(x, y, 8, 8); 
    }
   
    public Rectangle getBounds() { return new Rectangle(x, y, 8, 8); }
    public ArrayList<Point> getTrail() { return trail; }
}

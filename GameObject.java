import java.awt.*;

// Abstract class for all game entities 
public abstract class GameObject {
    protected int x, y;
    protected Color color;

    public GameObject(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public abstract void draw(Graphics g);
}
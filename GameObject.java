import java.awt.*;
import java.io.Serializable;

public abstract class GameObject implements Serializable {
    protected int x, y;
    protected Color color;

    public GameObject(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public abstract void draw(Graphics g);
}
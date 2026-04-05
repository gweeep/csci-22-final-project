import javax.swing.*;
import java.awt.*;

/**
 * GameCanvas is the drawing surface for the game.
 * It extends JComponent and overrides paintComponent to render all game visuals.
 */
public class GameCanvas extends JComponent {

    /**
     * Constructs a GameCanvas and sets its preferred size to 1024 by 768.
     */
    public GameCanvas() {
        this.setPreferredSize(new Dimension(1024, 768));
    }

    /**
     * Draws all game elements onto the canvas.
     * Called automatically whenever the component needs to be rendered.
     *
     * @param g the Graphics context used for drawing
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}

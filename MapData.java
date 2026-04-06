import java.awt.Rectangle;
import java.util.ArrayList;


public class MapData {
    public static ArrayList<Rectangle> getLevel(int level) {
        ArrayList<Rectangle> obstacles = new ArrayList<>();
        if (level == 2) { 
            obstacles.add(new Rectangle(400, 200, 40, 300));
            obstacles.add(new Rectangle(600, 200, 40, 300));
        } else if (level == 3) { 
            obstacles.add(new Rectangle(200, 100, 600, 20));
            obstacles.add(new Rectangle(200, 648, 600, 20));
        }
        return obstacles;
    }
}
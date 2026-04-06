import java.awt.Rectangle;
import java.util.ArrayList;


public class MapData {

    public static ArrayList<Rectangle> getLevel(int level) {
        ArrayList<Rectangle> obstacles = new ArrayList<>();

        switch (level) {
            case 1:
                // No obstacles are added here, just the empty arena 
                break;

            case 2:
                obstacles.add(new Rectangle(200, 200, 50, 368)); // Left Pillar
                obstacles.add(new Rectangle(774, 200, 50, 368)); // Right Pillar
                obstacles.add(new Rectangle(412, 374, 200, 20)); // Center block
                break;

            case 3:
                obstacles.add(new Rectangle(0, 150, 400, 10));   // Top Lane 
                obstacles.add(new Rectangle(624, 150, 400, 10)); // Top Lane Gap
                obstacles.add(new Rectangle(0, 600, 400, 10));   // Bottom Lane
                obstacles.add(new Rectangle(624, 600, 400, 10)); // Bottom Lane Gap
                obstacles.add(new Rectangle(507, 100, 10, 568)); // Vertical Divider
                break;

            default:
                
                break;
        }

        return obstacles;
    }
}

/**
 * GameStarter is the entry point for the player-side of the game.
 * Run this class on each player's machine after the GameServer is running.
 */
public class GameStarter {

    /**
     * Creates and starts a Player instance, connecting it to the game server.
     */
    public static void main(String[] args) {
        Player player = new Player(1024, 768);
        player.connectToServer();
        player.setUpGUI();
    }
}

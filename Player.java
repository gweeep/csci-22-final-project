import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

/**
 * Player manages the client-side state and GUI for one player in the game.
 * It connects to the GameServer, receives a player ID, and displays the game window.
 */
public class Player extends JFrame {

    private int width;
    private int height;
    private int playerID;
    private int otherPlayerID;
    private GameCanvas canvas;
    private ClientSideConnection csc;

    /**
     * Constructs a Player window with the given dimensions.
     *
     * @param w the window width in pixels
     * @param h the window height in pixels
     */
    public Player(int w, int h) {
        width = w;
        height = h;
        canvas = new GameCanvas();
    }

    /**
     * Builds and displays the player window with the game canvas.
     */
    public void setUpGUI() {
        this.setSize(width, height);
        this.setTitle("Player #" + playerID);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(canvas);
        this.setVisible(true);
    }

    /**
     * Establishes a connection to the GameServer and receives the player ID.
     */
    public void connectToServer() {
        csc = new ClientSideConnection();
    }

    /**
     * ClientSideConnection handles the socket connection to the GameServer.
     * It reads the assigned player ID on startup and provides methods
     * to send and receive game data during play.
     */
    private class ClientSideConnection {
        private Socket socket;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;

        /**
         * Opens a socket to the server and reads the assigned player ID.
         */
        public ClientSideConnection() {
            System.out.println("Client");
            try {
                socket = new Socket("localhost", 2405);
                dataIn  = new DataInputStream(socket.getInputStream());
                dataOut = new DataOutputStream(socket.getOutputStream());
                playerID = dataIn.readInt();
                if (playerID == 1) {
                    otherPlayerID = 2;
                } else {
                    otherPlayerID = 1;
                }
                System.out.println("Connected to server as Player #" + playerID + ".");
            } catch (IOException e) {
                System.out.println("IOException from CSC constructor");
            }
        }

        /**
         * Sends a single integer value to the server.
         *
         * @param value the integer to send
         */
        public void sendData(int value) {
            try {
                dataOut.writeInt(value);
            } catch (IOException e) {
                System.out.println("IOException from sendData()");
            }
        }

        /**
         * Reads a single integer value sent by the server.
         *
         * @return the integer received from the server
         */
        public int readData() {
            int value = 0;
            try {
                value = dataIn.readInt();
            } catch (IOException e) {
                System.out.println("IOException from readData()");
            }
            return value;
        }
    }
}

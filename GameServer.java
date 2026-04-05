import java.io.*;
import java.net.*;

/**
 * GameServer manages the server-side networking for the multiplayer game.
 * It accepts two player connections and relays game data between them.
 */
public class GameServer {

    private ServerSocket serverSocket;
    private int numPlayers;
    private int maxPlayers;
    private ServerSideConnection player1;
    private ServerSideConnection player2;

    /**
     * Constructs the GameServer and opens a ServerSocket on port 2405.
     */
    public GameServer() {
        numPlayers = 0;
        maxPlayers = 2;
        try {
            serverSocket = new ServerSocket(2405);
            System.out.println("Game Server");
        } catch (IOException e) {
            System.out.println("IOException from GameServer()");
        }
    }

    /**
     * Blocks until two players have connected, then starts a thread for each.
     */
    public void acceptConnections() {
        try {
            while (numPlayers < maxPlayers) {
                Socket socket = serverSocket.accept();
                numPlayers++;
                System.out.println("Player " + numPlayers + " has connected.");

                ServerSideConnection ssc = new ServerSideConnection(socket, numPlayers);

                if (numPlayers == 1) {
                    player1 = ssc;
                } else {
                    player2 = ssc;
                }

                Thread t = new Thread(ssc);
                t.start();
            }
            System.out.println("We now have 2 players. Server is full.");
        } catch (IOException e) {
            System.out.println("IOException from acceptConnections()");
        }
    }

    /**
     * ServerSideConnection handles two-way communication between the server
     * and one connected player. Each instance runs on its own thread.
     */
    private class ServerSideConnection implements Runnable {

        private Socket socket;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;
        private int playerID;

        /**
         * Constructs a ServerSideConnection for the given socket and player ID.
         *
         * @param socket the socket connected to this player
         * @param playerID the ID assigned to this player (1 or 2)
         */
        public ServerSideConnection(Socket socket, int playerID) {
            this.socket = socket;
            this.playerID = playerID;
            try {
                dataIn  = new DataInputStream(socket.getInputStream());
                dataOut = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                System.out.println("IOException from SSC constructor");
            }
        }

        /**
         * Sends this player their assigned ID, then continuously reads move data
         * and forwards it to the opposing player.
         */
        public void run() {
            try {
                dataOut.writeInt(playerID);

                while (true) {
                    int moveData = dataIn.readInt();
                    ServerSideConnection opponent;
                    if (playerID == 1) {
                        opponent = player2;
                    } else {
                        opponent = player1;
                    }
                    opponent.sendData(moveData);
                }
            } catch (IOException e) {
                System.out.println("IOException from run() SSC");
            }
        }

        /**
         * Sends a single integer value to this player's client.
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
    }

    /**
     * Creates and starts the game server.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }
}

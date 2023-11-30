package pt.hmsk.week8.ex1.parallel;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private ServerSocket server;

    public void runServer() {
        try {
            // 1. Create server socket
            server = new ServerSocket(2377, 1);

            while (true) {
                // 2. Wait for new connection
                waitForConnection();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void waitForConnection() throws IOException {
        System.out.println("[waiting for connection...]");
        Socket connection = server.accept(); // WAITS!
        
        ConnecionHandler handler = new ConnecionHandler(connection);
        handler.start();

        System.out.println("[new connection]" + connection.getInetAddress().getHostName());
    }

    private class ConnecionHandler extends Thread {

        private Socket connection;

        private PrintWriter out;
        private Scanner in;

        public ConnecionHandler(Socket connection) {
            this.connection = connection;
        }

        @Override
        public void run() {
            try {
                // 3. Get i/o streams
                getStreams();

                // 4. Process connection
                processConnection();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 5. Close connection
                closeConnection();
            }
        }

        private void getStreams() throws IOException {
            // Output - Write
            out = new PrintWriter(connection.getOutputStream(), true);

            // Input - Read
            in = new Scanner(connection.getInputStream());
        }

        private void processConnection() {
            String msg;
            do {
                msg = in.nextLine(); // WAITS!
                System.out.println("[Read] " + msg);
                out.println("[Eco] " + msg);
            } while (!"END".equals(msg));
        }

        private void closeConnection() {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.runServer();
    }
}

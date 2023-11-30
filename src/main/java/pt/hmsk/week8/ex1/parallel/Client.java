package pt.hmsk.week8.ex1.parallel;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    
    private Socket connection;

    private PrintWriter out;
    private Scanner in;
    
    private InetAddress serverName;
    private int port;

    public Client(InetAddress byName, int port) {
        serverName = byName;
        this.port = port;
    }

    public void runClient() {
        try {
            // 1. Connect to server
            connection = new Socket(serverName, port);

            // 2. Get i/o streams
            getStreams();

            // 3. Process connection
            processConnection();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. Close connection
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
        String msg = "Hello ";
        for (int i = 0; i < 5; ++i) {
            // Write to socket
            out.println(msg + i);
            System.out.println("[Write] " + msg + i);
            
            // Read from socket
            System.out.println("[Read] " + in.nextLine()); // WAITS!

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        out.println("END");
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

    public static void main(String[] args) {
        try {
            Client client1 = new Client(InetAddress.getByName("localhost"), 2377);
            client1.runClient();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}

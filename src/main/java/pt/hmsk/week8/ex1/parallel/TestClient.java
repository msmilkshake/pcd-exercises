package pt.hmsk.week8.ex1.parallel;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestClient {

    public static void main(String[] args) {
        try {
            Client client2 = new Client(InetAddress.getByName("localhost"), 2377);
            client2.runClient();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}

package pt.hmsk.week4bis.ex2.v2;

public class Main {
	private static final int NUM_REPOSITORIOS = 4; // numero de repositorios no servidor

	public static void main(String[] args) throws InterruptedException {
		Server server = new Server(NUM_REPOSITORIOS);

		Client client1 = new Client(0, server, 4);
		client1.start();
		client1.join();
		server.stopServer();
	}

}

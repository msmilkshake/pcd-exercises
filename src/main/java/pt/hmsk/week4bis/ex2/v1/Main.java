package pt.hmsk.week4bis.ex2.v1;

public class Main {
	private static final int NUM_REPOSITORIOS = 4; // numero de repositorios no servidor

	public static void main(String[] args) throws InterruptedException {
		Server server = new Server(NUM_REPOSITORIOS);
		Client[] clients = new Client[50];

		for (int i = 0; i < clients.length; ++i) {
			clients[i] = new Client(i, server, 4);
			clients[i].start();
		}
		
		for (Client c : clients) {
			c.join();
		}
		
		server.stopServer();
	}

}

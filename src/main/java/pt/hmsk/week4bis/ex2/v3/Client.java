package pt.hmsk.week4bis.ex2.v3;

import java.util.List;

public class Client extends Thread {
	private int clientId;
	private Server server;
	private int numSongRequests;

	public Client(int clientId, Server server, int numSongRequests) {
		super("Client-" + clientId);
		this.clientId = clientId;
		this.server = server;
		this.numSongRequests = numSongRequests;
	}

	@Override
	public void run() {
		List<SongRequest> requests = SongRequest.getRandomListOfSongsRequests(clientId, numSongRequests);
		StringBuilder sb = new StringBuilder(getName() + " Download request:");
		for (SongRequest song : requests) {
			sb.append("\n" + song);
		}
		System.out.println(sb + "\n----------------------");
		
		List<SongRequest> downloadedSongs = null;
		
		try {
			downloadedSongs = server.downloadSongs(clientId, requests);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
		sb = new StringBuilder(getName() + " Result:");
		for (SongRequest song : downloadedSongs) {
			sb.append("\n" + song);
		}
		System.out.println(sb + "\n----------------------");
    }
}

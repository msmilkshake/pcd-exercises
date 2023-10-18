package pt.hmsk.week4bis.ex2.v1;

import java.util.LinkedList;
import java.util.List;

public class Client extends Thread {
	private int clientId;
	private Server server;
	private int numSongRequests;

	public Client(int clienteId, Server server, int numSongRequests) {
		super("Cliente-" + clienteId);
		this.clientId = clienteId;
		this.server = server;
		this.numSongRequests = numSongRequests;
	}

	@Override
	public void run() {
		List<SongRequest> requests = SongRequest.getRandomListOfSongsRequests(numSongRequests);
		List<SongRequest> downloadedSongs = null;
		try {
			downloadedSongs = server.downloadSongs(requests);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
		System.out.println(getName() + "Requested song list:");
		for (SongRequest song : downloadedSongs) {
			System.out.println(song);
		}
    }
}

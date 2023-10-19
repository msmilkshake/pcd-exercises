package pt.hmsk.week4bis.ex2.v3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Server {
	private static int requestNum = 0;
	
	private List<Repository> repoList = new LinkedList<>();
	private Map<Integer, Integer> downloadRequestSizes = new HashMap<>();
	private Map<Integer, List<SongRequest>> requestResults = new HashMap<>();
	private List<SongRequest> requestQueue = new LinkedList<>();

	public Server(int chunkSize) {

		// Creates pairs of repositories with #chunkSize musics until the #MAX_NUM_OF_TITLESth song is reached 
		for (int i = 0, offset = 0; offset < SongRequest.MAX_NUM_OF_TITLES; ++i, offset += chunkSize) {
			int to = offset + chunkSize;
			Repository repo = new Repository(i++, this, offset, to);
			repoList.add(repo);
			repo = new Repository(i, this, offset, to);
			repoList.add(repo);
		}
		
		for (Repository repo : repoList) {
			repo.start();
		}
	}

	public synchronized List<SongRequest> downloadSongs(int clientId, List<SongRequest> songs) throws InterruptedException {
		int requestNum = Server.requestNum++;
		System.out.println("[SERVER]: Started serving request #" + requestNum);
		downloadRequestSizes.put(clientId, songs.size());
		requestResults.put(clientId, new LinkedList<>());
		requestQueue.addAll(songs);
		notifyAll();
		while(requestResults.get(clientId).size() < downloadRequestSizes.get(clientId)) {
			wait();
		}
		List<SongRequest> downloadResults = requestResults.get(clientId);
		downloadRequestSizes.remove(clientId);
		requestResults.remove(clientId);
		System.out.println("[SERVER]: Completed request #" + requestNum);
		notifyAll();
		return downloadResults;
	}

	public synchronized SongRequest getSongRequest() throws InterruptedException {
		while (requestQueue.isEmpty()) {
			wait();
		}
		return requestQueue.remove(0);
	}

	public synchronized void uploadSong(SongRequest song) {
		requestResults.get(song.getClientId()).add(song);
		notifyAll();
	}
	
	public synchronized void reinsertSong(SongRequest song) {
		requestQueue.add(song);
		notifyAll();
	}

	public void stopServer() {
		for (Repository repo : repoList) {
			repo.interrupt();
		}
	}

}

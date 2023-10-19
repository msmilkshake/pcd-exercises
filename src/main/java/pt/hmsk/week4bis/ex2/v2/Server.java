package pt.hmsk.week4bis.ex2.v2;

import java.util.*;

public class Server {
	private static int requestNum = 0;
	
	private List<Repository> repoList = new LinkedList<>();
	private Map<Integer, Integer> downloadRequestSizes = new HashMap<>();
	private Map<Integer, List<SongRequest>> requestResults = new HashMap<>();
	private List<SongRequest> processingQueue = new LinkedList<>();

	public Server(int numRepositorios) {

		// Criar e inicia os repositorios
		for (int i = 0; i != numRepositorios; i++) {
			Repository repo = new Repository(i, this);
			repoList.add(repo);
		}
		for (Repository repo : repoList) {
			repo.start();
		}

		// TODO
	}

	public synchronized List<SongRequest> downloadSongs(int clientId, List<SongRequest> songs) throws InterruptedException {
		int requestNum = Server.requestNum++;
		System.out.println("[SERVER]: Started serving request #" + requestNum);
		downloadRequestSizes.put(clientId, songs.size());
		requestResults.put(clientId, new LinkedList<>());
		processingQueue.addAll(songs);
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
		while (processingQueue.isEmpty()) {
			wait();
		}
		return processingQueue.remove(0);
	}

	public synchronized void uploadSong(SongRequest song) {
		requestResults.get(song.getClientId()).add(song);
		notifyAll();
	}

	public void stopServer() {
		for (Repository repo : repoList) {
			repo.interrupt();
		}
		// TODO
	}

}

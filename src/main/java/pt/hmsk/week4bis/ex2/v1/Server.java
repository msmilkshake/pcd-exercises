package pt.hmsk.week4bis.ex2.v1;

import java.util.LinkedList;
import java.util.List;

public class Server {
	private static int requestNum = 0;
	
	private List<Repository> repoList = new LinkedList<>();
	private List<SongRequest> downloadRequest = null;
	private List<SongRequest> requestResults = new LinkedList<>();
	private int requestSize;

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

	public synchronized List<SongRequest> downloadSongs(List<SongRequest> songs) throws InterruptedException {
		while (downloadRequest != null) {
			wait();
		}
		int requestNum = Server.requestNum++;
		System.out.println("[SERVER]: Started serving request #" + requestNum);
		downloadRequest = songs;
		requestSize = songs.size();
		notifyAll();
		while(requestResults.size() < requestSize) {
			wait();
		}
		List<SongRequest> downloadResults = requestResults;
		downloadRequest = null;
		System.out.println("[SERVER]: Completed request #" + requestNum);
		notifyAll();
		return downloadResults;
	}

	public synchronized SongRequest getSongRequest() throws InterruptedException {
		while (downloadRequest == null || downloadRequest.isEmpty()) {
			wait();
		}
		return downloadRequest.remove(0);

	}

	public synchronized void uploadSong(SongRequest song) {
		requestResults.add(song);
		notifyAll();
	}

	public void stopServer() {
		for (Repository repo : repoList) {
			repo.interrupt();
		}
		// TODO
	}

}

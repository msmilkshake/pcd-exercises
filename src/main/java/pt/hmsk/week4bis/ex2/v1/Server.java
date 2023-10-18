package pt.hmsk.week4bis.ex2.v1;

import java.util.LinkedList;
import java.util.List;

public class Server {
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
		downloadRequest = songs;
		requestSize = songs.size();
		while(requestResults.size() < requestSize) {
			wait();
		}
		return null;
	}

	public synchronized SongRequest getSongRequest() throws InterruptedException {
		while (downloadRequest == null || downloadRequest.isEmpty()) {
			wait();
		}
		return downloadRequest.get(0);

	}

	public synchronized void uploadSong(SongRequest song) {
		// TODO
	}

	public void stopServer() {
		for (Repository repo : repoList) {
			repo.interrupt();
		}
		// TODO
	}

}

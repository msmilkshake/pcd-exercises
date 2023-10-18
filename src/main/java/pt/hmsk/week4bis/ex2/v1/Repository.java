package pt.hmsk.week4bis.ex2.v1;

import java.util.List;

public class Repository extends Thread {
	private Server server;
	private List<SongRequest> listOfTitles;

	public Repository(int id, Server server) {
		super("Repo-" + id);
		this.server = server;
		
		listOfTitles = SongRequest.getCompleteListOfSongs();
	}

	@Override
	public void run() {
        try {
            SongRequest song = server.getSongRequest();
			int index = listOfTitles.indexOf(song);
			server.uploadSong(listOfTitles.get(index));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}

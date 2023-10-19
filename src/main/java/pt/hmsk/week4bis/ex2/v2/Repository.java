package pt.hmsk.week4bis.ex2.v2;

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
		System.out.println(getName() + " Started!");
        try {
			while (true) {
				SongRequest song = server.getSongRequest();
				int index = listOfTitles.indexOf(song);
				SongRequest resultSong =listOfTitles.get(index);
				resultSong.setClientId(song.getClientId());
				server.uploadSong(resultSong);
			}
        } catch (InterruptedException e) {
			System.out.println(getName() + " Interrupted!");
        }
		System.out.println(getName() + " Stopped!");
    }


}

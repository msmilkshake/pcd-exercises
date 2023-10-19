package pt.hmsk.week4bis.ex2.v3;

import java.util.List;

public class Repository extends Thread {
	
	private static List<SongRequest> completeListOfTitles = SongRequest.getCompleteListOfSongs();
	
	private Server server;
	private List<SongRequest> listOfTitles;

	public Repository(int id, Server server, int from, int to) {
		super("Repo-" + id);
		this.server = server;
		
		listOfTitles = completeListOfTitles.subList(from, Math.min(to, completeListOfTitles.size()));
	}

	@Override
	public void run() {
		System.out.println(getName() + " Started!");
        try {
			while (true) {
				SongRequest song = server.getSongRequest();
				int index = listOfTitles.indexOf(song);
				if (index == -1) {
					System.out.println(getName() + " doesn't have the specified song.");
					server.reinsertSong(song);
				} else {
					SongRequest resultSong = listOfTitles.get(index);
					resultSong.setClientId(song.getClientId());
					server.uploadSong(resultSong);
				}
			}
        } catch (InterruptedException e) {
			System.out.println(getName() + " Interrupted!");
        }
		System.out.println(getName() + " Stopped!");
    }


}

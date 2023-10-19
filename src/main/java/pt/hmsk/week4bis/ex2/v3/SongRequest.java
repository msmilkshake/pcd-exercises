package pt.hmsk.week4bis.ex2.v3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;


public class SongRequest {
	public static final int MAX_NUM_OF_TITLES = 30;
	
	private final String title;
	private long duration;
	private byte[] data;
	private int clientId = -1;

	public SongRequest(String title) {
		this.title = title;
	}

	public byte[] getSongData() {
		return data;
	}

	public String getSongTitle() {
		return title;
	}

	public void setSongData(byte[] data) {
		this.data = data;
	}

	public void setSongDuration(long duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "SongRequest [title= \"" + title + "\" , duration=" + duration +
				", length=" + (data == null ? "null" : data.length) +
				", requesterID=" + clientId + "]";
	}


	public static List<SongRequest> getRandomListOfSongs(int numberOfTitles) {
		List<SongRequest> completeListOfSongs = getCompleteListOfSongs();
		List<SongRequest> list = new ArrayList<>();
		
		for (int i = 0; i != numberOfTitles; i++) {
			int number = (new Random()).nextInt(MAX_NUM_OF_TITLES) + 1;
			SongRequest song = completeListOfSongs.get(number);
			list.add(song);
		}
		return list;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getClientId() {
		return clientId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SongRequest)) return false;
		SongRequest that = (SongRequest) o;
		return Objects.equals(title, that.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title);
	}

	public static List<SongRequest> getCompleteListOfSongs() {
		List<SongRequest> completeListOfSongs = new ArrayList<>();
		for (int i = 0; i != MAX_NUM_OF_TITLES; i++) {
			long number = i + 1;
			long duration = (new Random()).nextInt(4) + 1; // [1, 5[ minutos
			String title = "Music title number " + number;
			SongRequest song = new SongRequest(title);
			String randomData=(new RandomString((int)duration)).nextString();
			song.setSongData(randomData.getBytes());
			song.setSongDuration(duration);
			completeListOfSongs.add(song);
		}
		return completeListOfSongs;
	}

	public static List<SongRequest> getRandomListOfSongsRequests(int clientId, int numberOfRequests) {
		List<SongRequest> list = new ArrayList<>();

		for (int i = 0; i != numberOfRequests; i++) {
			long number = (new Random()).nextInt(MAX_NUM_OF_TITLES) + 1;
			String title = "Music title number " + number;
			SongRequest song = new SongRequest(title);
			song.setClientId(clientId);
			list.add(song);
		}
		return list;
	}

}

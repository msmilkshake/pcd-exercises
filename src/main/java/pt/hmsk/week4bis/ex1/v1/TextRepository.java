package pt.hmsk.week4bis.ex1.v1;

import java.util.LinkedList;
import java.util.List;

public class TextRepository {
	List<TextChunk> chunksToAnalyze = new LinkedList<>();

	public TextRepository(String text, String stringToBeFound, int chunkSize) {
		// create chunk list
		for (int initPos = 0; initPos < text.length(); initPos += chunkSize) {
			int finalPos = Math.min(initPos + chunkSize, text.length());
			TextChunk chunk = new TextChunk(text.substring(initPos, finalPos), initPos, stringToBeFound);
			chunksToAnalyze.add(chunk);
		}
	}

	public synchronized TextChunk getChunk() {
		// non-blocking method
		if (chunksToAnalyze.isEmpty()) {
			return null;
		}
		return chunksToAnalyze.remove(0);
	}

	public synchronized void submitResult(TextChunk chunk) {
		// TODO V2
	}

	public synchronized List<Integer> getAllMatches() throws InterruptedException {
		// TODO V2
		
		return null;

	}
}

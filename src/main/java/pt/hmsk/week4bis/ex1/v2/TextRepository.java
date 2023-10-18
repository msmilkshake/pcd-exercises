package pt.hmsk.week4bis.ex1.v2;

import java.util.LinkedList;
import java.util.List;

public class TextRepository {
	List<TextChunk> chunksToAnalyze = new LinkedList<>();
	List<TextChunk> analyzedChunks = new LinkedList<>();
	private int totalChunks;

	public TextRepository(String text, String stringToBeFound, int chunkSize) {
		// create chunk list
		for (int initPos = 0; initPos < text.length(); initPos += chunkSize) {
			int finalPos = Math.min(initPos + chunkSize, text.length());
			TextChunk chunk = new TextChunk(text.substring(initPos, finalPos), initPos, stringToBeFound);
			chunksToAnalyze.add(chunk);
		}
		totalChunks = chunksToAnalyze.size();
	}

	public synchronized TextChunk getChunk() {
		// non-blocking method
		if (chunksToAnalyze.isEmpty()) {
			return null;
		}
		return chunksToAnalyze.remove(0);
	}

	public synchronized void submitResult(TextChunk chunk) {
		analyzedChunks.add(chunk);
		if (analyzedChunks.size() == totalChunks) {
			notifyAll();
		}
	}

	public synchronized List<Integer> getAllMatches() throws InterruptedException {
		while (analyzedChunks.size() < totalChunks) {
			wait();
		}
		return summaryResults();
	}

	private List<Integer> summaryResults() {
		List<Integer> results = new LinkedList<>();
		for (TextChunk chunk : analyzedChunks) {
			results.addAll(chunk.getFoundPos());
		}
		return results;
	}
}

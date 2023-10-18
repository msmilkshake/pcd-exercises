package pt.hmsk.week4bis.ex1.v1;

import java.util.ArrayList;
import java.util.List;

public class TextChunk {
	public final String text;
	private final int initialPos;
	public final String stringToBeFound;
	private List<Integer> foundPos = new ArrayList<>(); // Version 2

	public TextChunk(String text, int initialPos, String stringToBeFound) {
		super();
		this.text = text;
		this.initialPos = initialPos;
		this.stringToBeFound = stringToBeFound;
	}

	public int getInitialPos() {
		return initialPos;
	}

	public List<Integer> getFoundPos() {
		return List.copyOf(foundPos);
	}

	public void addFoundPos(int pos) {
		foundPos.add(pos);
	}

}

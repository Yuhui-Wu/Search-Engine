package PreProcessData;

import java.util.Deque;
import java.util.LinkedList;

/**
 * TextTokenizer can split a sequence of text into individual word tokens.
 */
public class WordTokenizer {
	// Essential private methods or variables can be added.
	
	Deque<Character> queue;
	public WordTokenizer(char[] texts) {
		// Tokenize the input texts.
		queue = new LinkedList<Character>();
		// add all char to queue. in the same time, get rid of signs.
		for (int i = 0; i < texts.length; i++) {
			char cur = texts[i];
			if (cur == ' ' || (cur >= 'a' && cur <= 'z') || (cur >= 'A' && cur <= 'Z')) {
				queue.add(cur);
			}
		}
	}

	public char[] nextWord() {
		// Return the next word in the document.
		// Return null, if it is the end of the document.
		LinkedList<Character> result = new LinkedList<>();
		while (!queue.isEmpty() && queue.peek() == ' ') queue.poll();
		while (!queue.isEmpty() && queue.peek() != ' ') {
			result.offer(queue.poll());
		}
		char[] res = new char[result.size()];
		for (int i = 0; i < result.size(); i++) {
			res[i] = result.poll();
		}
		return res.length > 0 ? res : null;
	}

}

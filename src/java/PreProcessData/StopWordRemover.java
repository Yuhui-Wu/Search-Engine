package PreProcessData;

import Classes.Path;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class StopWordRemover {
	// Essential private methods or variables can be added.
	// use hashset to store all the stop words, easy to search
	HashSet<String> stopWord = new HashSet<String>();
	FileInputStream stopwordfile = null;
	BufferedReader br;

	// YOU SHOULD IMPLEMENT THIS METHOD.
	public StopWordRemover() throws IOException {
		// Load and store the stop words from the fileinputstream with appropriate data
		// structure.
		// NT: address of stopword.txt is Path.StopwordDir
		
		// load the stop word line by line
		stopwordfile = new FileInputStream(Path.StopwordDir);
		br = new BufferedReader(new InputStreamReader(stopwordfile));
		String line = br.readLine();

		while (line != null) {
			stopWord.add(line);
			line = br.readLine();
		}
	}

	// YOU SHOULD IMPLEMENT THIS METHOD.
	public boolean isStopword(char[] word) {
		// Return true if the input word is a stopword, or false if not.
		if (stopWord.contains(word.toString())) { 
			return true;
		} else {
			return false;
		}
	}
}

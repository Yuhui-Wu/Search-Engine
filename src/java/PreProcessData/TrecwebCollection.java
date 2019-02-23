package PreProcessData;

import Classes.Path;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TrecwebCollection implements DocumentCollection {
	// Essential private methods or variables can be added.
	
	File folder;
	// YOU SHOULD IMPLEMENT THIS METHOD.
	public TrecwebCollection() throws IOException {
		// 1. Open the file in Path.DataWebDir.
		// 2. Make preparation for function nextDocument().
		
		this.folder = new File(Path.ProblemDir);
	}

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }

	// YOU SHOULD IMPLEMENT THIS METHOD.
	public Map<String, Object> nextDocument() throws IOException {
		// 1. When called, this API processes one document from corpus, and returns its
		// doc number and content.
		// 2. When no document left, return null, and close the file.
		// 3. the HTML tags should be removed in document content.
		// map<key: docno; value: content>
		File[] listOfFiles = folder.listFiles();
		Map<String, Object> map = new HashMap<>();
		
		for (File file : listOfFiles) {
			if (file.isFile()) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(file));
					String line;
					String DOCNO = "";
					char[] content = null;
					while ((line = br.readLine()) != null) {
                        System.out.println(html2text(line));
						if (line.indexOf("<title>") != -1) {
							content = new char[line.length()-15];
							for (int i = 7; i < line.length()-8;i++) {
								content[i-7] = line.charAt(i);
							}
						} else if (line.indexOf("<h1>") != -1) {
							StringBuilder NO = new StringBuilder();
							int index = 4;
							while (index < line.length() && Character.isDigit(line.charAt(index))) {
								NO.append(line.charAt(index));
								index++;
							}
							DOCNO = NO.toString();
							break;
						} 
					}
					br.close();
					System.out.println(DOCNO + " " + String.valueOf(content));
					map.put(DOCNO, content);
				} catch (IOException e) {
				}
			}
		}
		return map;
	}

}

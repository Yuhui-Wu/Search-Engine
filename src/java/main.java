import Classes.Document;
import Classes.Path;
import Classes.Query;
import IndexingLucene.MyIndexReader;
import IndexingLucene.MyIndexWriter;
import IndexingLucene.PreProcessedCorpusReader;
import PreProcessData.DocumentCollection;
import PreProcessData.StopWordRemover;
import PreProcessData.TrecwebCollection;
import PreProcessData.WordNormalizer;
import PreProcessData.WordTokenizer;
import PseudoRFSearch.PseudoRFRetrievalModel;
import SearchLucene.ExtractQuery;

import java.io.FileWriter;
import java.util.List;
import java.util.Map;

/**
 * !!! YOU CANNOT CHANGE ANYTHING IN THIS CLASS !!! For INFSCI 2140 in 2018.
 */
public class Main {

	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();
		HW1Main hm1 = new HW1Main();
		hm1.PreProcess("trecweb");// 1.39min 198361 files
		long endTime = System.currentTimeMillis();
		System.out.println("web corpus running time: " + (endTime - startTime) / 60000.0 + " min");

		startTime=System.currentTimeMillis();
		hm1.WriteIndex("trecweb");
		endTime=System.currentTimeMillis();
		System.out.println("index web corpus running time: "+(endTime-startTime)/60000.0+" min"); 
		startTime=System.currentTimeMillis();
		hm1.ReadIndex("trecweb", "sub");
		endTime=System.currentTimeMillis();
		System.out.println("load index & retrieve running time: "+(endTime-startTime)/60000.0+" min");
		
		MyIndexReader ixreader = new MyIndexReader("trectext");
		PseudoRFRetrievalModel PRFSearchModel = new PseudoRFRetrievalModel(ixreader);
		ExtractQuery queries = new ExtractQuery();
		
		// begin search
		startTime = System.currentTimeMillis();
		while (queries.hasNext()) {
			Query aQuery = queries.next();
			List<Document> results = PRFSearchModel.RetrieveQuery(aQuery, 20, 100, 0.4);
			if (results != null) {
				int rank = 1;
				for (Document result : results) {
					System.out.println(aQuery.GetTopicId() + " Q0 " + result.docno() + " " + rank + " "
							+ result.score());
					rank++;
				}
			}
		}
		endTime = System.currentTimeMillis(); 
		
		// output running time
		System.out.println("\n\n4 queries search time: " + (endTime - startTime) / 60000.0 + " min");
		ixreader.close();
	}

	public void PreProcess(String dataType) throws Exception {
		// Initiate the DocumentCollection.
		DocumentCollection corpus;
		corpus = new TrecwebCollection();

		// Loading stopword, and initiate StopWordRemover.
		StopWordRemover stopwordRemover = new StopWordRemover();
		// Initiate WordNormalizer.
		WordNormalizer normalizer = new WordNormalizer();

		// Initiate the BufferedWriter to output result.
		FileWriter wr = new FileWriter(Path.ResultHM1 + dataType);

		// <String, Object> can hold document number and content.
		Map<String, Object> doc = corpus.nextDocument();

		// Process the corpus, document by document, iteratively.
		int count = 0;
		for (String DOCNO : doc.keySet()) {
			// Load document number of the document.
			String docno = DOCNO;

			// Load document content.
			char[] content = (char[]) doc.get(docno);

			// Write docno into the result file.
			wr.append(docno + "\n");

			// Initiate the WordTokenizer class.
			WordTokenizer tokenizer = new WordTokenizer(content);

			// Initiate a word object, which can hold a word.
			char[] word = null;

			// Process the document word by word iteratively.
			while ((word = tokenizer.nextWord()) != null) {
				// Each word is transformed into lowercase.
				word = normalizer.lowercase(word);

				// Only non-stopword will appear in result file.
				if (!stopwordRemover.isStopword(word))
					// Words are stemmed.
					wr.append(normalizer.stem(word) + " ");
			}
			wr.append("\n");// Finish processing one document.
			count++;
			if (count % 10000 == 0)
				System.out.println("finish " + count + " docs");
		}
		System.out.println("totaly document count:  " + count);
		wr.close();
	}
	
	public void WriteIndex(String dataType) throws Exception {
		// Initiate pre-processed collection file reader
		PreProcessedCorpusReader corpus=new PreProcessedCorpusReader(dataType);
		
		// initiate the output object
		MyIndexWriter output=new MyIndexWriter(dataType);
		
		// initiate a doc object, which can hold document number and document content of a document
		Map<String, String> doc = null;

		int count=0;
		// build index of corpus document by document
		while ((doc = corpus.nextDocument()) != null) {
			// load document number and content of the document
			String docno = doc.keySet().iterator().next();
			String content = doc.get(docno);
			// index this document
			output.index(docno, content); 
			
			count++;
			if(count%10000==0)
				System.out.println("finish "+count+" docs");
		}
		System.out.println("totaly document count:  "+count);
		output.close();
	}
	
	public void ReadIndex(String dataType, String token) throws Exception {
		// Initiate the index file reader
		MyIndexReader ixreader=new MyIndexReader(dataType);
		
		// do retrieval
		int df = ixreader.DocFreq(token);
		long ctf = ixreader.CollectionFreq(token);
		System.out.println(" >> the token \""+token+"\" appeared in "+df+" documents and "+ctf+" times in total");
		if(df>0){
			int[][] posting = ixreader.getPostingList(token);
			for(int ix=0;ix<posting.length;ix++){
				int docid = posting[ix][0];
				int freq = posting[ix][1];
				String docno = ixreader.getDocno(docid);
				System.out.printf("    %20s    %6d    %6d\n", docno, docid, freq);
			}
		}
		ixreader.close();
	}
}

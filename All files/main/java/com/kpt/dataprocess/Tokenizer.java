package com.kpt.dataprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

import com.kpt.entities.Document;

/**
 * 
 * @author Anurag Malik, Lokesh Agrawal
 *
 */
public class Tokenizer {
	private HashSet<String> stopWords;

	private boolean stopwordRemoval;

	public Tokenizer(boolean removeStopwords) {
		stopwordRemoval = removeStopwords;
		if (removeStopwords) {
			stopWords = new HashSet<>();
			readStopWords();
		}
	}

	public Tokenizer() {
		stopwordRemoval = false;
	}

	public Document tokenize(String data, String category) {
		Document doc = new Document();
		if (category != null)
			doc.setCategory(category);

		String[] processData = preprocess(data).split(" ");
		if (stopwordRemoval)
			processData = removeStopwords(processData);

		doc.setTokenMap(getFrequencyMap(processData));
		return doc;

	}

	private HashMap<String, Integer> getFrequencyMap(String[] data) {
		HashMap<String, Integer> freqMap = new HashMap<>();
		Integer count;
		for (String token : data) {
			if (token.equals(""))
				continue;
			count = freqMap.get(token);
			if (count == null)
				freqMap.put(token, 1);
			else
				freqMap.put(token, count + 1);
		}
		return freqMap;
	}

	private String preprocess(String text) {
		return text.replaceAll("\\p{P}", " ").replaceAll("\\s+", " ").toLowerCase(Locale.getDefault());
	}

	/**
	 * This method reads stop words from a file into a hash map
	 */
	private void readStopWords() {
		stopWords = new HashSet<String>();
		for (String word : readFile("stopwords.txt").split("\n"))
			stopWords.add(word);
	}

	/**
	 * Clean data, remove stop words and do stemming. Create a hash map of all
	 * original words and their stem words.
	 * 
	 * @param data
	 * @return hash mapping of all words and their stem
	 */
	private String[] removeStopwords(String[] data) {
		ArrayList<String> cleanData = new ArrayList<>();
		for (String token : data) {
			// if the token is present in hash map or it is empty, skip it.
			if (stopWords.contains(token) || token.equals(""))
				continue;
			// add stem word to map with original token as value
			cleanData.add(token);
		}

		String[] finalList = new String[cleanData.size()];
		int count = 0;
		for (String ret : cleanData)
			finalList[count++] = ret;

		return finalList;
	}

	/**
	 * This method is responsible for reading text data from file.
	 * 
	 * @param fileName
	 * @return text data from file.
	 */
	private String readFile(String fileName) {
		File file;
		StringBuffer fileData = new StringBuffer();
		try {
			file = new File(this.getClass().getResource("/" + fileName).toURI());

			BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));
			char[] buffer = new char[1024];
			int count = 0;
			while ((count = reader.read(buffer)) != -1) {
				String readData = String.valueOf(buffer, 0, count);
				fileData.append(readData);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			return null;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return fileData.toString();
	}

}

package com.kpt.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

/**
 * @author Anurag Malik, Lokesh Agrawal
 */
public class DBConnect {
	static MongoClient client;

	public static MongoDatabase getDB() {
		if (client == null)
			;
		client = new MongoClient("localhost", 27017);
		return client.getDatabase("kpt");
	}

	public static void close() {
		client.close();
	}

	public static HashMap<String, ArrayList<String>> getTrainingDataset(int size) {
		HashMap<String, ArrayList<String>> trainData = new HashMap<>();
		ArrayList<String> posData = new ArrayList<>();
		ArrayList<String> negData = new ArrayList<>();
		ArrayList<String> ntrData = new ArrayList<>();

		MongoCollection<Document> collection = DBConnect.getDB().getCollection("review");

		List<Document> docs = collection.find().limit(500000)
				.projection(Projections.fields(Projections.include("text", "stars", "votes"), Projections.excludeId()))
				.into(new ArrayList<Document>());

		Iterator<Document> itr = docs.iterator();
		

		Document doc = new Document();
		String temp = null;
		int posCount = 0, negCount = 0, ntrCount = 0;
		boolean trainComplete = false;
		while (itr.hasNext()) {
			doc = itr.next();
			// votes, stars, text, sentiment

			int count = 0;
			String sentiment = "";
			for (String key : doc.keySet()) {
				temp = doc.get(key).toString();
				temp = temp.replaceAll("\"", "");
				temp = temp.replaceAll("\n", "");
				if (!trainComplete) {
					if (count == 1) {
						float val = Float.parseFloat(temp);
						if (val > 3.5 && posCount < (size / 3)) {
							sentiment = "pos";
							posCount++;
						} else if (val <= 1.5 && negCount < (size / 3)) {
							sentiment = "neg";
							negCount++;
						} else if (ntrCount < (size / 3)) {
							sentiment = "ntr";
							ntrCount++;
						}
						if (posCount == (size / 3) && negCount == (size / 3) && ntrCount == (size / 3)) {
							trainComplete = true;
						}
					} else if (count == 2 && !sentiment.equals("") && temp != null) {
						if (sentiment.equals("pos"))
							posData.add(temp);
						else if (sentiment.equals("neg"))
							negData.add(temp);
						else if (sentiment.equals("ntr"))
							ntrData.add(temp);
					}
				} else {
					break;
				}

				count++;
			}

			if (trainComplete)
				break;
		}
		if (posData.size() > 0) {
			trainData.put("pos", posData);
		}
		if (negData.size() > 0) {
			trainData.put("neg", negData);
		}
		if (ntrData.size() > 0) {
			trainData.put("ntr", ntrData);
		}
		DBConnect.close();
		return trainData;
	}

}

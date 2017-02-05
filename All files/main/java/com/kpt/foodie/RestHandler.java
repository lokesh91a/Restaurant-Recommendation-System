package com.kpt.foodie;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.kpt.classifier.NaiveBayesClassifier;
import com.kpt.database.DBConnect;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

public class RestHandler {
	static MongoDatabase db;
	static NaiveBayesClassifier nbc = null;
	static RestHandler rater = null;
	static RestHandler out;
	static Object newReq = new Object();

	private RestHandler() {
		db = DBConnect.getDB();
		nbc = NaiveBayesTrainer.runTrainer(true);
	}

	public static NaiveBayesClassifier getNBase() {
		getInstance();
		return nbc;
	}

	public static RestHandler getInstance() {
		if (rater == null)
			rater = new RestHandler();
		return rater;
	}

	public static void main(String[] args) {
	}

	public List<RatingResponse> execute(String location, Integer count) {
		FindIterable<Document> iterable = null;
		if (count == null) {
			iterable = db.getCollection("business").find(new Document("city", location)).projection(Projections
					.fields(Projections.include("business_id", "name", "full_address"), Projections.excludeId()));
		} else {
			iterable = db.getCollection("business").find(new Document("city", location)).limit(count)
					.projection(Projections.fields(Projections.include("business_id", "name", "full_address"),
							Projections.excludeId()));
		}
		List<RatingResponse> res = new ArrayList<RatingResponse>();
		for (Document doc : iterable) {
			res.add(new RatingResponse(doc.get("business_id").toString(), doc.get("name").toString(),
					doc.get("full_address").toString(), getReviewRatings(doc.get("business_id").toString())));
		}
		return res;
	}

	public static Double getReviewRatings(String busID) {
		String sentiment = null;
		double posCount = 0, negCount = 0, ntrCount = 0;
		FindIterable<Document> iterable = db.getCollection("review").find(new Document("business_id", busID))
				.projection(Projections.fields(Projections.include("text", "stars"), Projections.excludeId()));
		for (Document doc : iterable) {
			sentiment = nbc.predict(doc.get("text").toString());
			if (sentiment.equals("pos")) {
				posCount++;
			} else if (sentiment.equals("neg")) {
				negCount++;
			} else {
				ntrCount++;
			}
		}
		double rating = ((posCount * 5) + (ntrCount * 2.5)) / (posCount + negCount + ntrCount);
		if (rating > 4)
			rating = 4.0;
		return round(rating, 2) + 1;
	}

	private static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public String getPrediction(String text) {
		return nbc.predict(text);
	}

}

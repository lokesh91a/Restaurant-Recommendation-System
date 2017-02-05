package com.kpt.foodie;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.kpt.database.DBConnect;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

public class QueryHandler {

	static MongoDatabase db;
	static QueryHandler handler = null;

	private QueryHandler() {
		db = DBConnect.getDB();
	}

	public static QueryHandler getInstance() {
		if (handler == null)
			handler = new QueryHandler();
		return handler;
	}

	public List<LocationItem> getAllLocations() {
		List<LocationItem> allLoc = new ArrayList<>();
		DistinctIterable<String> iterable = null;
		iterable = db.getCollection("business").distinct("city", String.class);
		for (String loc : iterable) {
			allLoc.add(new LocationItem(loc));
		}
		return allLoc;
	}

	public List<ReviewItem> getReviews(String businessId, String sentiment, Integer limit) {
		FindIterable<Document> iterable = null;
		if (limit == null) {
			iterable = db.getCollection("review").find(new Document("business_id", businessId))
					.projection(Projections.fields(Projections.include("text"), Projections.excludeId()));
		} else {
			iterable = db.getCollection("review").find(new Document("business_id", businessId)).limit(limit)
					.projection(Projections.fields(Projections.include("text"), Projections.excludeId()));
		}

		List<ReviewItem> res = new ArrayList<>();
		for (Document doc : iterable) {
			String text = doc.get("text").toString();
			res.add(new ReviewItem(text,
					RestHandler.getInstance().getPrediction(text)));
		}
		return res;
	}
}

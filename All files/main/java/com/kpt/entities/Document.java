package com.kpt.entities;

import java.util.HashMap;

/**
 * This class represents a Document, responsible for storing all tokens from a
 * input file / test record.
 * 
 * @author Anurag Malik, Lokesh Agrawal
 */
public class Document {
	// hash map of all token in this document instance, with values for each
	// token representing frequency count.
	private HashMap<String, Integer> tokenMap;

	// category of this document, as fetched from the training data set.
	private String docCategory;

	public Document() {
		tokenMap = new HashMap<String, Integer>();
	}

	/*
	 * Set category for this document.
	 */
	public void setCategory(String category) {
		docCategory = category;
	}

	/*
	 * Get category of this document instance
	 */
	public String getCategory() {
		return docCategory;
	}

	/*
	 * Set token mappings for this document instance
	 */
	public void setTokenMap(HashMap<String, Integer> map) {
		tokenMap = map;
	}

	/*
	 * Get token instance for this document instance
	 */
	public HashMap<String, Integer> getTokenMap() {
		return tokenMap;
	}
} // Document

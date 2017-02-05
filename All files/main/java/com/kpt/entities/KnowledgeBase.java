package com.kpt.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 * This class represents a knowledge base instance for the Naive Bayes
 * Classifier. It stores the essential details retrieved after the algorithmic
 * learning from the training data set.
 * 
 * @author Anurag Malik, Lokesh Agrawal
 *
 */
public class KnowledgeBase implements Serializable{
	private int docCount;
	private int categoryCount;
	private int featureCount;

	private HashMap<String, Double> logPriorities = new HashMap<>();
	private HashMap<String, HashMap<String, Double>> condProbabilities = new HashMap<>();
	private int allFeaturesCount;
	private int bestFeaturesCount;

	/**
	 * @return the docCount
	 */
	public int getDocCount() {
		return docCount;
	}

	/**
	 * @param docCount
	 *            the docCount to set
	 */
	public void setDocCount(int docCount) {
		this.docCount = docCount;
	}

	/**
	 * @return the categoryCount
	 */
	public int getCategoryCount() {
		return categoryCount;
	}

	/**
	 * @param categoryCount
	 *            the categoryCount to set
	 */
	public void setCategoryCount(int categoryCount) {
		this.categoryCount = categoryCount;
	}

	/**
	 * @return the featureCount
	 */
	public int getFeatureCount() {
		return featureCount;
	}

	/**
	 * @param featureCount
	 *            the featureCount to set
	 */
	public void setFeatureCount(int featureCount) {
		this.featureCount = featureCount;
	}

	/**
	 * @return the logPriorities
	 */
	public HashMap<String, Double> getLogPriorities() {
		return logPriorities;
	}

	/**
	 * @param logPriorities
	 *            the logPriorities to set
	 */
	public void setLogPriorities(HashMap<String, Double> logPriorities) {
		this.logPriorities = logPriorities;
	}

	/**
	 * @return the condProbabilities
	 */
	public HashMap<String, HashMap<String, Double>> getCondProbabilities() {
		return condProbabilities;
	}

	/**
	 * @param condProbabilities
	 *            the condProbabilities to set
	 */
	public void setCondProbabilities(HashMap<String, HashMap<String, Double>> condProbabilities) {
		this.condProbabilities = condProbabilities;
	}

	public void setCondProb(String feature, String item, double condProb) {

		if (!condProbabilities.containsKey(feature)) {
			condProbabilities.put(feature, new HashMap<String, Double>());
		}
		condProbabilities.get(feature).put(item, condProb);
	}

	public boolean conditionalProbContains(String feature) {
		return condProbabilities.containsKey(feature);
	}

	public Double getCondProb(String feature, String category) {
		return condProbabilities.get(feature).get(category);
	}

	public Set<String> getBestFeatures() {
		return condProbabilities.keySet();
	}
	
	public int getAllFeaturesCount() {
		return allFeaturesCount;
	}

	public int getBestFeaturesCount() {
		return bestFeaturesCount;
	}

	public void setAllFeaturesCount(int allFeaturesCount) {
		this.allFeaturesCount = allFeaturesCount;
	}

	public void setBestFeaturesCount(int bestFeaturesCount) {
		this.bestFeaturesCount = bestFeaturesCount;
	}

}

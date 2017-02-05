package com.kpt.entities;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author Anurag Malik, Lokesh Agrawal
 *
 */
public class FeatureInfo {
	private int docCount;
	private HashMap<String, HashMap<String, Integer>> featureCategoryFreqMap;
	private HashMap<String, Integer> categoryFreqMap;

	private int allFeaturesCount, bestFeaturesCount;
	
	public FeatureInfo() {
		docCount = 0;
		featureCategoryFreqMap = new HashMap<>();
		categoryFreqMap = new HashMap<>();
	}

	/**
	 * @return the allFeatures
	 */
	public int getDocCount() {
		return docCount;
	}
	
	/**
	 * @return the featureCategoryFreqMap
	 */
	public HashMap<String, HashMap<String, Integer>> getFeatureCategoryFreqMap() {
		return featureCategoryFreqMap;
	}
	
	/**
	 * @return the featureCategoryFreqMap
	 */
	public HashMap<String, Integer> getFeatureCategoryFreqMap(String feature) {
		return featureCategoryFreqMap.get(feature);
	}

	/**
	 * @return the categoryFreqMap
	 */
	public HashMap<String, Integer> getCategoryFreqMap() {
		return categoryFreqMap;
	}

	public void incrementFeatureCount() {
		docCount++;
	}

	public Integer getCategoryCount(String category) {
		return categoryFreqMap.get(category);
	}

	public void addCategory(String category) {
		categoryFreqMap.put(category, 1);
	}

	public void incrementCategoryCount(String category) {
		categoryFreqMap.put(category, categoryFreqMap.get(category) + 1);
	}

	public void addFeature(String feature) {
		featureCategoryFreqMap.put(feature, new HashMap<String, Integer>());
	}

	public void incrementCategoryCountForFeature(String feature, String category) {
		Integer count = getFeatureCategoryFreqMap(feature).get(category);
		if (count == null) {
			count = 1;
		}
		getFeatureCategoryFreqMap(feature).put(category, count++);
	}

	public int getFeaturesSize() {
		return featureCategoryFreqMap.size();
	}
	
	public int getCategoriesSize() {
		return categoryFreqMap.size();
	}
	
	public double categorySum(String category ) {
		double featureOcc = 0.0;
		Integer occurrences;
        for(Map<String, Integer> record : featureCategoryFreqMap.values()) {
            occurrences = record.get(category);
            if(occurrences!=null) {
                featureOcc+=occurrences;
            }
        }
        return featureOcc;
	}

	public void saveAllFeaturesCount() {
		allFeaturesCount = getFeaturesSize();
		
	}

	public void saveBestFeaturesCount() {
		bestFeaturesCount = getFeaturesSize();
	}
	
	public int getAllFeaturesCount() {
		return allFeaturesCount;
	}
	
	public int getBestFeaturesCount() {
		return bestFeaturesCount;
	}
}

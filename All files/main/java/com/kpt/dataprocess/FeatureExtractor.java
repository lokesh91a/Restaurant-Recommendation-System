package com.kpt.dataprocess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.kpt.entities.Document;
import com.kpt.entities.FeatureInfo;

/**
 * FeatureExtractor is responsible for parsing through the data and selecting
 * the best features available in the data set for best results during
 * classification process. The following class provides options for either using
 * a mutual information feature extraction or Chi-square extraction method.
 * 
 * @author Anurag Malik, Lokesh Agrawal
 */
public class FeatureExtractor {
	private FeatureInfo features;
	double leastScore;

	public FeatureExtractor(double minScore) {
		features = new FeatureInfo();
		leastScore = minScore;
	}

	public FeatureInfo extractBestFeatures(ArrayList<Document> docList, boolean chiFilter) {

		String category = null;
		Integer categoryCount = 0;
		String feature = null;
		HashMap<String, Integer> featureCategoryCounts;
		for (Document doc : docList) {
			features.incrementFeatureCount();
			category = doc.getCategory();

			categoryCount = features.getCategoryCount(category);
			if (categoryCount == null)
				features.addCategory(category);
			else
				features.incrementCategoryCount(category);

			for (Entry<String, Integer> entry : doc.getTokenMap().entrySet()) {
				feature = entry.getKey();
				featureCategoryCounts = features.getFeatureCategoryFreqMap(feature);
				if (featureCategoryCounts == null)
					features.addFeature(feature);

				features.incrementCategoryCountForFeature(feature, category);
			}
		}
		features.saveAllFeaturesCount();
		
		String item;
		if (chiFilter) {
			HashMap<String, Double> miMappings = getChiSquareMappings();
			Iterator<Entry<String, HashMap<String, Integer>>> itr = features.getFeatureCategoryFreqMap().entrySet()
					.iterator();
			while (itr.hasNext()) {
				item = itr.next().getKey();
				if (!miMappings.containsKey(item)) {
					itr.remove();
				}
			}
		}
		
		features.saveBestFeaturesCount();
		
		return features;
	}

	private HashMap<String, Double> getChiSquareMappings() {
		HashMap<String, Double> selectedFeatures = new HashMap<>();
		String feature;
		String category;
		Map<String, Integer> categoryList;

		int docsWithFeature, docsNotWithFeature, tNeg, fPos, fNeg, tPos;
		Double previousScore;
		double chiScore;
		for (Entry<String, HashMap<String, Integer>> record : features.getFeatureCategoryFreqMap().entrySet()) {
			feature = record.getKey();
			categoryList = record.getValue();

			docsWithFeature = 0;
			for (Integer count : categoryList.values()) {
				docsWithFeature += count;
			}

			int allDocs = features.getDocCount();
			docsNotWithFeature = allDocs - docsWithFeature;

			for (Map.Entry<String, Integer> entry2 : categoryList.entrySet()) {
				category = entry2.getKey();
				tPos = entry2.getValue();
				fPos = features.getCategoryCount(category) - tPos;

				tNeg = docsNotWithFeature - fPos;
				fNeg = docsWithFeature - tPos;

				chiScore = allDocs * Math.pow(tPos * tNeg - fNeg * fPos, 2)
						/ ((tPos + fPos) * (tPos + fNeg) * (fNeg + tNeg) * (fPos + tNeg));

				if (chiScore >= leastScore) {
					previousScore = selectedFeatures.get(feature);
					if (previousScore == null || chiScore > previousScore) {
						selectedFeatures.put(feature, chiScore);
					}
				}
			}
		}
		return selectedFeatures;
	}
}

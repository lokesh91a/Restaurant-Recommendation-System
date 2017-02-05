package com.kpt.classifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.kpt.dataprocess.FeatureExtractor;
import com.kpt.dataprocess.Tokenizer;
import com.kpt.entities.Document;
import com.kpt.entities.FeatureInfo;
import com.kpt.entities.KnowledgeBase;

/**
 * 
 * @author Anurag Malik, Lokesh Agrawal
 */
public class NaiveBayesClassifier {
	public static KnowledgeBase knowledgeBase;
	public static boolean isSerialized;

	private ArrayList<Document> preprocessDataset(HashMap<String, ArrayList<String>> trainingData) {
		ArrayList<Document> dataset = new ArrayList<>();
		Document doc = null;

		Iterator<Map.Entry<String, ArrayList<String>>> it = trainingData.entrySet().iterator();
		Tokenizer tokenizer = new Tokenizer(true);
		String category;
		while (it.hasNext()) {
			Map.Entry<String, ArrayList<String>> entry = it.next();
			category = entry.getKey();
			for (String review : entry.getValue()) {
				doc = tokenizer.tokenize(review, category);
				dataset.add(doc);
			}
			it.remove();
		}
		return dataset;
	}

	public HashMap<String, HashMap<String, Double>> getConditionalProbabilities() {
		return knowledgeBase.getCondProbabilities();
	}

	public Set<String> getBestFeatures() {
		return knowledgeBase.getBestFeatures();
	}

	public void serializeKnowledgeBase() {
		System.out.println("*** SERIALIZING KNOWLEDGE BASE ***");
		String filename = "knowledgebase.obj";

		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(knowledgeBase);

			out.close();
			isSerialized = true;
			System.out.println("*** SERIALIZATION COMPLETED ***");
		} catch (Exception ex) {
			System.out.println("*** SERIALIZATION FAILED ***");
			ex.printStackTrace();
		}
	}

	private FeatureInfo selectFeatures(ArrayList<Document> dataset) {
		FeatureExtractor featureExtractor = new FeatureExtractor(1.0);
		FeatureInfo stats = featureExtractor.extractBestFeatures(dataset, true);
		return stats;
	}

	public void deserializeKnowledgeBase() {
		System.out.println("*** DESERIALIZING KNOWLEDGE BASE ***");
		File file = null;
		try {
			file = new File(this.getClass().getResource("/knowledgebase.obj").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			knowledgeBase = (KnowledgeBase) in.readObject();
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void train(HashMap<String, ArrayList<String>> trainingData) {
		ArrayList<Document> allDocs = preprocessDataset(trainingData);
		FeatureInfo features = selectFeatures(allDocs);

		knowledgeBase = new KnowledgeBase();
		int docCount = features.getDocCount();
		int featureCount = features.getFeaturesSize();
		knowledgeBase.setDocCount(docCount);
		knowledgeBase.setFeatureCount(featureCount);
		knowledgeBase.setCategoryCount(features.getCategoriesSize());
		knowledgeBase.setAllFeaturesCount(features.getAllFeaturesCount());

		HashMap<String, Double> logPriorities = new HashMap<>();
		String category;
		int freq;
		for (Map.Entry<String, Integer> entry : features.getCategoryFreqMap().entrySet()) {
			category = entry.getKey();
			freq = entry.getValue();
			logPriorities.put(category, Math.log((double) freq / docCount));
		}
		knowledgeBase.setLogPriorities(logPriorities);

		HashMap<String, Double> featureCategoriesOcc = new HashMap<>();
		for (String item : knowledgeBase.getLogPriorities().keySet()) {
			featureCategoriesOcc.put(item, features.categorySum(item));
		}

		String feature;
		Integer count;
		Map<String, Integer> featureCategoryCounts;
		double condProb;
		for (String item : knowledgeBase.getLogPriorities().keySet()) {
			for (Map.Entry<String, HashMap<String, Integer>> entry : features.getFeatureCategoryFreqMap().entrySet()) {
				feature = entry.getKey();
				featureCategoryCounts = entry.getValue();
				count = featureCategoryCounts.get(item);
				if (count == null)
					count = 0;

				condProb = Math.log((count + 1.0) / (featureCategoriesOcc.get(item) + featureCount));
				knowledgeBase.setCondProb(feature, item, condProb);
			}
		}

		featureCategoriesOcc = null;
	}

	public String predict(String text) throws IllegalArgumentException {
		if (knowledgeBase == null) {
			throw new IllegalArgumentException("Knowledge base not found. Training not completed.");
		}

		Tokenizer tokenizer = new Tokenizer(true);
		Document doc = tokenizer.tokenize(text, null);

		String category;
		String feature;
		Integer occurrences;
		Double logprob;

		String maxScoreCategory = null;
		Double maxScore = Double.NEGATIVE_INFINITY;

		for (Map.Entry<String, Double> record : knowledgeBase.getLogPriorities().entrySet()) {
			category = record.getKey();
			logprob = record.getValue();

			for (Map.Entry<String, Integer> token : doc.getTokenMap().entrySet()) {
				feature = token.getKey();

				if (!knowledgeBase.conditionalProbContains(feature))
					continue;

				occurrences = token.getValue();
				logprob += occurrences * knowledgeBase.getCondProb(feature, category);
			}

			if (logprob > maxScore) {
				maxScore = logprob;
				maxScoreCategory = category;
			}
		}

		return maxScoreCategory;
	}

	public void test(HashMap<String, ArrayList<String>> testData) {
		float allCorrect = 0;
		String category;
		String prediction;
		ArrayList<String> reviews = null;
		float allTests = 0;

		long startTime = System.currentTimeMillis();

		HashMap<String, Float> accuracyMap = new HashMap<>();
		float catCorrect = 0;
		int catCount = 0;
		HashMap<String, Integer> catCountMap = new HashMap<>();
		HashMap<String, Integer> confused;
		HashMap<String, HashMap<String, Integer>> confusionMatrix = new HashMap<>();
		for (Map.Entry<String, ArrayList<String>> record : testData.entrySet()) {
			category = record.getKey();
			reviews = record.getValue();
			catCorrect = 0;
			catCount = 0;
			confused = new HashMap<>();
			for (String review : reviews) {
				prediction = predict(review);
				if (prediction.equals(category)) {
					allCorrect++;
					catCorrect++;
				} else {
					Integer count = confused.get(prediction);
					if (count == null)
						confused.put(prediction, 1);
					else
						confused.put(prediction, ++count);
				}
				catCount++;
				allTests++;
			}
			catCountMap.put(category, catCount);
			confusionMatrix.put(category, confused);
			accuracyMap.put(category, (catCorrect / reviews.size()));
		}

		KnowledgeBase kb = knowledgeBase;
		System.out.println("*** TRAINING COMPLETED ***");
		System.out.println("Time taken : " + (System.currentTimeMillis() - startTime) + "ms");
		System.out.format("Overall Accuracy : %.2f", (allCorrect / allTests) * 100);
		System.out.println("%");
		System.out.println("Total documents read : " + kb.getDocCount());
		System.out.println("Features collected :" + kb.getAllFeaturesCount());
		System.out.println("Features selected :" + kb.getFeatureCount());
		System.out.println("Number of classes for classification :" + kb.getCategoryCount());
		System.out.println("--> Class Description <-- ");
		for (Entry<String, Float> record : accuracyMap.entrySet()) {
			System.out.format("\t%s : %.2f", record.getKey(), record.getValue() * 100);
			System.out.println("%");
		}

		int total = 0, val = 0;
		String incStr = "";
		System.out.println("--> Confusion Matrix <--");
		for (Entry<String, HashMap<String, Integer>> record : confusionMatrix.entrySet()) {
			total = catCountMap.get(record.getKey());
			val = 0;
			incStr = "";
			for (Entry<String, Integer> temp : record.getValue().entrySet()) {
				val += temp.getValue();
				incStr += ", " + temp.getValue() + "(" + temp.getKey() + ")";
			}
			System.out.println(
					record.getKey() + "(" + total + ") : " + record.getKey() + "(" + (total - val) + ")" + incStr);
		}
	}

	public boolean isSerialized() {
		return isSerialized;
	}

}

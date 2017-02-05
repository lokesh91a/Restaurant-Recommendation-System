package com.kpt.foodie;

import java.util.ArrayList;
import java.util.HashMap;

import com.kpt.classifier.NaiveBayesClassifier;
import com.kpt.database.DBConnect;

/**
 * @author Anurag Malik, Lokesh Agrawal
 */
public class NaiveBayesTrainer {
	static int TRAINING_RECORDS = 6500;
	static int TEST_RECORDS = 900;

	public static void main(String[] args) {
		NaiveBayesClassifier nbc = NaiveBayesTrainer.runTrainer(false);
		nbc.serializeKnowledgeBase();
	}

	public static NaiveBayesClassifier runTrainer(boolean apiCall) {
		NaiveBayesClassifier nbc = new NaiveBayesClassifier();
		if (apiCall) {
			// if(nbc.isSerialized())
			nbc.deserializeKnowledgeBase();
		} else {
			HashMap<String, ArrayList<String>> trainingData = DBConnect.getTrainingDataset(TRAINING_RECORDS);
			nbc.train(trainingData);

			HashMap<String, ArrayList<String>> testData = DBConnect.getTrainingDataset(TEST_RECORDS);
			nbc.test(testData);
		}
		return nbc;

	}
}

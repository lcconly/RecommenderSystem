package Similarity;

import Profile.Profile;

public interface SimilarityInterface {
	/**
	 * @returns the similarity between two profiles
	 * @param p1
	 * @param p2
	 */
	public double getSimilarity(Profile p1, Profile p2);
}

package Similarity;

import java.util.Iterator;

import Profile.Profile;

public class CosineSimilarity implements SimilarityInterface {
	public CosineSimilarity() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public double getSimilarity(Profile p1, Profile p2) {
		// TODO Auto-generated method stub
		Iterator<Integer> it=p1.getCommonID(p2).iterator();
		double dotProduct=0.0;
		while(it.hasNext()){
			Integer ID=it.next();
			dotProduct+=(p1.getValue(ID.intValue()))*(p2.getValue(ID.intValue()));
		}
		double n1=p1.getNorm();
		double n2=p2.getNorm();
		return (n1>0&&n2>0)?dotProduct/(n1*n2):0;

	}

}

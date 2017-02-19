package Similarity;

import java.util.Iterator;

import Profile.Profile;

public class MeanSquareDifferencesSimilarity implements SimilarityInterface{
	public MeanSquareDifferencesSimilarity() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public double getSimilarity(Profile p1, Profile p2) {
		// TODO Auto-generated method stub
		Iterator<Integer> it=p1.getCommonID(p2).iterator();
		double square_diffiecne=0.0;
		int count=0;
		while(it.hasNext()){
			Integer ID=it.next();
			square_diffiecne+=Math.pow(p1.getValue(ID.intValue())-p2.getValue(ID.intValue()),2);
			count++;
		}
		return count!=0?square_diffiecne/count:0;
	}
	
}

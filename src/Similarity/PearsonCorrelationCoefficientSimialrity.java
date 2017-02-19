package Similarity;

import java.util.Set;

import Profile.Profile;

public class PearsonCorrelationCoefficientSimialrity implements SimilarityInterface {
	public PearsonCorrelationCoefficientSimialrity(){
	
	}
	@Override
	public double getSimilarity(Profile p1, Profile p2) {
		// TODO Auto-generated method stub
        Set<Integer> common = p1.getCommonID(p2);
        double mean_p1=p1.getMeanValue();
        double mean_p2=p2.getMeanValue();
        double sum_above=0.0;
		for(Integer id: common){
			sum_above=(p1.getValue(id)-mean_p1)*(p2.getValue(id)-mean_p2);
		}
		double dev_p1=p1.GetStandardDeviation();
		double dev_p2=p2.GetStandardDeviation();
		
		return sum_above/((dev_p1*Math.sqrt(p1.getSize()))*(dev_p2*Math.sqrt(p2.getSize())));
	}

}

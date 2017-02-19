package Predictor;

import java.util.Map;

import FileDeal.DataReader;
import Neighbour.NeighbourMap;
import Neighbour.SimilarityMap;
import Profile.Profile;

public class PredictorBySim implements PredictorInterface {
	
	@Override
	public Double GetPredictRating(int user_id, int item_id,DataReader rd,NeighbourMap neighbour,SimilarityMap simMap) {
		// TODO Auto-generated method stub
		double above=0;
		double below=0;
		for(Integer id: rd.getUserMLRating().keySet()) // iterate over the target user
		{
			if(neighbour.isNeighbour(user_id, id)) // the current user is in the neighbourhood
			{
				Double rating = rd.getUserProfile(id).getValue(item_id);
				Double sim=simMap.getCertianSimilarity(user_id, id);
				Double weight=1-sim.doubleValue()/16;
				above += (rating!=null)?rating.doubleValue()*weight:0;
				below +=(rating!=null)?Math.abs(weight):0;
			}
		}		
		return (below > 0) ? new Double(above / below) : null;

	}

}

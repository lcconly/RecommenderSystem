package Predictor;

import java.util.Map;

import FileDeal.DataReader;
import Neighbour.NeighbourMap;
import Neighbour.SimilarityMap;
import Profile.Profile;

public class ResnickPredictor implements PredictorInterface{

	@Override
	public Double GetPredictRating(int user_id, int item_id, DataReader rd, NeighbourMap neighbour,
			SimilarityMap simMap) {
		// TODO Auto-generated method stub
		Map<Integer, Profile> userMLRating=rd.getUserMLRating();
		double meanItemOfUserID=userMLRating.get(user_id).getMeanValue();
		double above=0;
		double below=0;
		for(Integer id: rd.getUserMLRating().keySet()) // iterate over the target user
		{
			if(neighbour.isNeighbour(user_id, id)) // the current user is in the neighbourhood
			{
				double meanItemOfNeibourID=userMLRating.get(id).getMeanValue();
				Double sim=simMap.getCertianSimilarity(user_id, id);
				Double rating = rd.getUserProfile(id).getValue(item_id);
				above+=rating!=null?(rating-meanItemOfNeibourID)*sim:0;
				below+=rating!=null?Math.abs(sim):0;
			}
		}
		return below>0? new Double(meanItemOfUserID+above/below):null;
	}

}

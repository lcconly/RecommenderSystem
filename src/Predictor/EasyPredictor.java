package Predictor;

import java.util.Map;

import FileDeal.DataReader;
import Neighbour.NeighbourMap;
import Neighbour.SimilarityMap;
import Profile.Profile;

public class EasyPredictor implements PredictorInterface{
	/**
	 * @returns -1 unpredicted
	 * @returns >0 ok
	 * @return -2 input error
	 */
	public Double GetPredictRating(int user_id, int item_id,DataReader rd,NeighbourMap neighbour,SimilarityMap simMap) {
		if(user_id>rd.getUserNum() || item_id>rd.getitemNum()||user_id<1 || item_id<1){
			System.out.println("unUnrecognized userID or itemID");
			return null;
		}
		else{
			double sum=0.0;
			Profile pro=rd.getItemMLRating().get(item_id);
			return new Double(pro.getMeanWithoutID(user_id));
		}
	}
}

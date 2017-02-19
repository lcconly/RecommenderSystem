package Predictor;

import java.util.Map;

import FileDeal.DataReader;
import Neighbour.NeighbourMap;
import Neighbour.SimilarityMap;
import Profile.Profile;

public interface PredictorInterface {
	/**
	 * @returns -1 unpredicted
	 * @returns >0 ok
	 * @return -2 input error
	 */
	public Double GetPredictRating(int user_id, int item_id,DataReader rd,NeighbourMap neighbour,SimilarityMap simMap);

}

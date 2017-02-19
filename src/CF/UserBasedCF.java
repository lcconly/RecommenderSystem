package CF;

import FileDeal.DataReader;
import Neighbour.NeighbourMap;
import Neighbour.SimilarityMap;
import Predictor.PredictorBySim;
import Predictor.PredictorInterface;
import Similarity.SimilarityInterface;

public class UserBasedCF {
	private PredictorInterface predict;
	private NeighbourMap neighbour;
	private SimilarityMap simMap;
	private DataReader rd;
	/**
	 * constructor to create a userbasedCF object
	 * @param neighbour
	 * @param rd
	 * @param similarity
	 */
	public UserBasedCF(NeighbourMap neighbour,DataReader rd,SimilarityInterface similarity,PredictorInterface predict){
		this.predict=predict;
		this.rd=rd;
		this.neighbour=neighbour;
		this.simMap=new SimilarityMap(rd.getUserMLRating(), similarity);
		this.neighbour.computeNeighbourhoods(simMap);
	}
	public Double getPrediction( Integer userId,  Integer itemId){
		return predict.GetPredictRating(userId, itemId, rd, neighbour, simMap);
	}

	
}

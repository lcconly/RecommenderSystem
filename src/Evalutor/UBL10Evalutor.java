package Evalutor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.text.MaskFormatter;

import CF.UserBasedCF;
import FileDeal.DataReader;
import Neighbour.NeighbourMap;
import Neighbour.SimilarityMap;
import Pairs.ActualPredictPair;
import Pairs.UserItemPair;
import Predictor.PredictorInterface;

public class UBL10Evalutor {
	private Map<UserItemPair, ActualPredictPair> result;//result map
	private DataReader dr;
	/**
	 * constructor - creates a new L10Evalutor object
	 * @param predictor 
	 * @param dataSet
	 */
	public UBL10Evalutor(UserBasedCF ubcf,DataReader dr){
		this.dr=dr;
		result=new HashMap<UserItemPair, ActualPredictPair>();
		for(Iterator<Map.Entry<UserItemPair, Double>> it=dr.getDataSet().entrySet().iterator();it.hasNext();){
			Map.Entry<UserItemPair,Double> entry = (Map.Entry<UserItemPair,Double>)it.next();
			UserItemPair pair = entry.getKey();
			Double actualRating=entry.getValue();
			Double predictRating=ubcf.getPrediction(pair.getUserID().intValue(), pair.getItemID().intValue());
			if(predictRating==null){
				result.put(pair, new ActualPredictPair(actualRating, predictRating,new Double(-1)));
			}
			else {
				Double rmse=Math.sqrt(Math.pow(actualRating.doubleValue()-predictRating.doubleValue(),2));
				result.put(pair, new ActualPredictPair(actualRating, predictRating,rmse));				
			}
		}
	}
	public Map<UserItemPair, ActualPredictPair> getResultMap(){
		return result;
	}
	/**
	 * @returns the root mean square error (RMSE) or -1 if the actual ratings are not available
	 */
	public double getOverRMSE(){
		int count=0;
		double squareError=0;
		for(ActualPredictPair ratings: result.values())
		{	
			if(ratings.getRMSE().doubleValue()>0){
				squareError += Math.pow(ratings.getRMSE(),2);
				count++;
			}
		}
		if(count == 0)
			return -1;
		else
			return new Double(Math.sqrt(squareError / count));	

	}
	/**
	 * @returns the coverage
	 */
	public double getCoverage(){
		int count=0;
		for(ActualPredictPair pair:result.values()){
			if(pair.getPredictedRating()!=null)
				count++;
		}
		return (result.size()>0)?(double)count/(double)(dr.getitemNum()*dr.getUserNum()):0;
	}
	public double getL10Coverage(){
		int count_predict=0;
		int count_actual=0;
		for(ActualPredictPair pair:result.values()){
			if(pair.getPredictedRating()!=null)
				count_predict++;
		}
		return (result.size()>0)?(double)count_predict/(double)result.size():0;

	}
}

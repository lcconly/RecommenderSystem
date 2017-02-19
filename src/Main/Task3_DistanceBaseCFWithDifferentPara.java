package Main;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import CF.UserBasedCF;
import Evalutor.L10Evalutor;
import Evalutor.UBL10Evalutor;
import FileDeal.DataReader;
import FileDeal.DataWriter;
import Neighbour.KNearestNeighbour;
import Neighbour.NeighbourMap;
import Neighbour.SimilarityMap;
import Pairs.ActualPredictPair;
import Predictor.EasyPredictor;
import Predictor.PredictorBySim;
import Predictor.PredictorInterface;
import Similarity.CosineSimilarity;
import Similarity.MeanSquareDifferencesSimilarity;
import Similarity.PearsonCorrelationCoefficientSimialrity;
import Similarity.SimilarityInterface;

public class Task3_DistanceBaseCFWithDifferentPara {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String mlFile = "dataFile" + File.separator + "100k.dat";
		DataReader dr=new DataReader(mlFile);
		//System.out.println(dr.getUserNum()+"  "+dr.getitemNum()+"  "+dr.getTotalRatingNum());
		PredictorInterface pre=new PredictorBySim();
		NeighbourMap neighbour=new KNearestNeighbour(600);
		SimilarityInterface similarity=new CosineSimilarity();
		//SimilarityInterface similarity=new MeanSquareDifferencesSimilarity();
		//SimilarityInterface similarity=new PearsonCorrelationCoefficientSimialrity();
		SimilarityMap simMap=new SimilarityMap(dr.getUserMLRating(), similarity);
		UserBasedCF ubcf=new UserBasedCF(neighbour, dr, similarity, pre);
		UBL10Evalutor eva=new UBL10Evalutor(ubcf,dr);
		String CSVFile = "Results" + File.separator + "Task_3"+File.separator+"RMSElist.csv";
		//write csv file
		try {
			DataWriter dw= new DataWriter(CSVFile);
			for(Iterator<Map.Entry<Pairs.UserItemPair,ActualPredictPair>> it = eva.getResultMap().entrySet().iterator(); it.hasNext(); ){
				Map.Entry<Pairs.UserItemPair,ActualPredictPair> entry = (Map.Entry<Pairs.UserItemPair,ActualPredictPair>)it.next();
				Pairs.UserItemPair userItem=entry.getKey();
				ActualPredictPair predictPair=entry.getValue();
				if(predictPair.getPredictedRating()!=null)
					dw.printLine(userItem.toString()+","+predictPair.toString());
			}	
			dw.closeFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//print rmse and coverage
		System.out.println("Overall RMSE "+eva.getOverRMSE());
		System.out.println(String.format("Overall Coverage %.2f %%",(eva.getL10Coverage()*100)));
		//print running time
		long start = System.currentTimeMillis();
		for(int i=0;i<10;i++){
			eva=new UBL10Evalutor(ubcf,dr);
		}
		long end = System.currentTimeMillis();
		System.out.println("Running time "+(float)((end-start)/10)/1000+" s");


	}

}

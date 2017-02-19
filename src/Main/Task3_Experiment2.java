package Main;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import CF.UserBasedCF;
import Evalutor.UBL10Evalutor;
import FileDeal.DataReader;
import FileDeal.DataWriter;
import Neighbour.KNNWithSimilarityThresholdNeighbour;
import Neighbour.KNearestNeighbour;
import Neighbour.NeighbourMap;
import Neighbour.SimilarityMap;
import Neighbour.SimilarityThresholdNeighbour;
import Pairs.ActualPredictPair;
import Predictor.PredictorBySim;
import Predictor.PredictorInterface;
import Similarity.CosineSimilarity;
import Similarity.MeanSquareDifferencesSimilarity;
import Similarity.PearsonCorrelationCoefficientSimialrity;
import Similarity.SimilarityInterface;

public class Task3_Experiment2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String mlFile = "dataFile" + File.separator + "100k.dat";
		String detailFile = "Results" + File.separator + "Task_3" + File.separator + "Experiment2" + File.separator
				+ "Details.txt";
		DataReader dr = new DataReader(mlFile);
		String rMSE_timeString = "";
		// System.out.println(dr.getUserNum()+" "+dr.getitemNum()+"
		// "+dr.getTotalRatingNum());
		for (double i = 0.1; i <= 2.0; i+=0.1) {
			for (int k = 100; k <= 600; k += 100) {
				PredictorInterface pre = new PredictorBySim();
				NeighbourMap neighbour = new KNNWithSimilarityThresholdNeighbour(i, k);
				// SimilarityInterface similarity = new CosineSimilarity();
				SimilarityInterface similarity = new MeanSquareDifferencesSimilarity();
				SimilarityMap simMap = new SimilarityMap(dr.getUserMLRating(), similarity);
				UserBasedCF ubcf = new UserBasedCF(neighbour, dr, similarity, pre);
				UBL10Evalutor eva = new UBL10Evalutor(ubcf, dr);
				String CSVFile = "Results" + File.separator + "Task_3" + File.separator + "Experiment2" + File.separator
						+ "RMSElistThreshold_" + i +"K_"+k+ ".csv";
				// write csv file
				try {
					DataWriter dw = new DataWriter(CSVFile);
					for (Iterator<Map.Entry<Pairs.UserItemPair, ActualPredictPair>> it = eva.getResultMap().entrySet()
							.iterator(); it.hasNext();) {
						Map.Entry<Pairs.UserItemPair, ActualPredictPair> entry = (Map.Entry<Pairs.UserItemPair, ActualPredictPair>) it
								.next();
						Pairs.UserItemPair userItem = entry.getKey();
						ActualPredictPair predictPair = entry.getValue();
						if (predictPair.getPredictedRating() != null)
							dw.printLine(userItem.toString() + "," + predictPair.toString());
					}
					dw.closeFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// print rmse and coverage
				System.out.println("Neighbour shreshold=" + i);
				rMSE_timeString += "Neighbour shreshold=" + i + "\n";
				System.out.println("    Overall RMSE " + eva.getOverRMSE());
				rMSE_timeString += "    Overall RMSE " + eva.getOverRMSE() + "\n";
				System.out.println(String.format("    Overall Coverage %.2f %%", (eva.getL10Coverage() * 100)));
				rMSE_timeString += String.format("    Overall Coverage %.2f %%\n", (eva.getL10Coverage() * 100));
				// print running time
				long start = System.currentTimeMillis();
				for (int j = 0; j < 10; j++) {
					eva = new UBL10Evalutor(ubcf, dr);
				}
				long end = System.currentTimeMillis();
				System.out.println("    Running time " + (float) ((end - start) / 10) / 1000 + " s");
				rMSE_timeString += "    Running time " + (float) ((end - start) / 10) / 1000 + " s\n";
			}
		}
		try {
			DataWriter dWriter = new DataWriter(detailFile);
			dWriter.printLine(rMSE_timeString);
			dWriter.closeFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
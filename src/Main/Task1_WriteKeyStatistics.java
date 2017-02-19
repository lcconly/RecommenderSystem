package Main;

import java.io.File;
import java.io.IOException;

import FileDeal.DataReader;
import FileDeal.DataWriter;
import Predictor.EasyPredictor;
import Profile.Profile;

public class Task1_WriteKeyStatistics {

	public static void main(String[] args) {
		int count_Unpredictable=0;
		// TODO Auto-generated method stub
		String mlFile = "dataFile" + File.separator + "100k.dat";
		DataReader dr=new DataReader(mlFile);
		//System.out.println(dr.getUserNum()+"  "+dr.getitemNum()+"  "+dr.getTotalRatingNum());
		String TotalFile = "Results" + File.separator + "Task_1"+File.separator+"TotalnumAndDensity.txt";
		String UserStaticFile = "Results" + File.separator + "Task_1"+File.separator+"UserStatistics.txt";
		String ItemStaticFile = "Results" + File.separator + "Task_1"+File.separator+"ItemStatistics.txt";
		String EasyPredictFile = "Results" + File.separator + "Task_1"+File.separator+"PredictByMean.txt";
		try {
			DataWriter dw= new DataWriter(EasyPredictFile);
			for(int i=1;i<=dr.getUserNum();i++)
				for(int j=1;j<=dr.getitemNum();j++){
					double predict;
					EasyPredictor predictor=new EasyPredictor();
					predict=predictor.GetPredictRating(i, j, dr, null, null);
					if(predict==-1.0)
						count_Unpredictable++;
					else if(predict>0){
						dw.printLine(i+","+j+","+predict);
					}
				}
			dw.closeFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//write total number of users, items, ratings to file
		try {
			DataWriter dw=new DataWriter(TotalFile);
			dw.printLine("Total number of users: "+dr.getUserNum());
			dw.printLine("Total number of items: "+dr.getitemNum());
			dw.printLine("Total number of ratings: "+dr.getTotalRatingNum());
			dw.printLine(String.format("ratings density metric: %.2f %%",(double)((dr.getDensityMatric())*100)));
			dw.printLine("Total num of 1-star: "+dr.getNumOfStar()[0]);
			dw.printLine("Total num of 2-star: "+dr.getNumOfStar()[1]);
			dw.printLine("Total num of 3-star: "+dr.getNumOfStar()[2]);
			dw.printLine("Total num of 4-star: "+dr.getNumOfStar()[3]);
			dw.printLine("Total num of 5-star: "+dr.getNumOfStar()[4]);
			dw.printLine("The num of unpredicted by the mean_item_rating approach: "+count_Unpredictable);
			dw.printLine(String.format("The coverage of the mean_item_rating approach: %.2f %%",
			(((double)dr.getTotalRatingNum()-(double)count_Unpredictable)/(double)dr.getTotalRatingNum())*100));
			dw.closeFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//write Mean, median, standard deviation, max, min ratings per user to file
		try {
			DataWriter dw=new DataWriter(UserStaticFile);
			for(Profile pro:dr.getUserMLRating().values()){
				dw.printLine("usrID: "+pro.getID().intValue()+"  mean: "+pro.getMeanValue()+"  median: "+pro.getMedianValue()
				+"  standard_deviation: "+pro.GetStandardDeviation()+"  max_rating: "+pro.getMaxValue()+"  min_rating: "+pro.getMinValue());
			}
			dw.closeFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//write Mean, median, standard deviation, max, min ratings per item to file
		try {
			DataWriter dw=new DataWriter(ItemStaticFile);
			for(Profile pro:dr.getItemMLRating().values()){
				dw.printLine("itemID: "+pro.getID().intValue()+"  mean: "+pro.getMeanValue()+"  median: "+pro.getMedianValue()
				+"  standard_deviation: "+pro.GetStandardDeviation()+"  max_rating: "+pro.getMaxValue()+"  min_rating: "+pro.getMinValue());
			}
			dw.closeFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package Main;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import Evalutor.L10Evalutor;
import FileDeal.DataReader;
import FileDeal.DataWriter;
import Pairs.ActualPredictPair;
import Predictor.EasyPredictor;
import Predictor.PredictorInterface;

public class Task2_WriteCSVandRuntime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String mlFile = "dataFile" + File.separator + "100k.dat";
		DataReader dr=new DataReader(mlFile);
		//System.out.println(dr.getUserNum()+"  "+dr.getitemNum()+"  "+dr.getTotalRatingNum());
		String CSVFile = "Results" + File.separator + "Task_2"+File.separator+"RMSElist.csv";
		PredictorInterface pre=new EasyPredictor();
		L10Evalutor eva=new L10Evalutor(pre, dr,null,null);
		//write csv file
		try {
			DataWriter dw= new DataWriter(CSVFile);
			for(Iterator<Map.Entry<Pairs.UserItemPair,ActualPredictPair>> it = eva.getResultMap().entrySet().iterator(); it.hasNext(); ){
				Map.Entry<Pairs.UserItemPair,ActualPredictPair> entry = (Map.Entry<Pairs.UserItemPair,ActualPredictPair>)it.next();
				Pairs.UserItemPair userItem=entry.getKey();
				ActualPredictPair predictPair=entry.getValue();
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
			eva=new L10Evalutor(pre, dr,null,null);
		}
		long end = System.currentTimeMillis();
		System.out.println("Running time "+(float)((end-start)/10)/1000+" s");

	}

}

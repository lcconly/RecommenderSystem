package Pairs;

public class ActualPredictPair {
	private Double actual; // the actual rating
	private Double predicted; // the predicted rating 
	private Double rmse;
	/**
	 * constructor - creates a new RatingsPair object
	 * @param actual - the actual rating
	 * @param predicted - the predicted rating
	 */
	public ActualPredictPair(final Double actual, final Double predicted,final Double rmse) {
		// TODO Auto-generated constructor stub	
		this.actual = actual;
		this.predicted = predicted;
		this.rmse=rmse;
	}
	
	/**
	 * @returns the actual rating
	 */
	public Double getActualRating()
	{
		return actual;
	}

	/**
	 * @returns the predicted rating
	 */
	public Double getPredictedRating()
	{
		return predicted;
	}
	/**
	 * @returns the RMSE rating
	 */
	public Double getRMSE()
	{
		return rmse;
	}
	/**
	 * @returns String actual rating,predict rating,rmse
	 */	
	public String toString(){
		return actual.doubleValue()+","+predicted.doubleValue()+","+rmse.doubleValue();
	}

}

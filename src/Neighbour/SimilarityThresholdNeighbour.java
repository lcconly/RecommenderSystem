package Neighbour;


public class SimilarityThresholdNeighbour extends NeighbourMap{

	private double threshold;
	public SimilarityThresholdNeighbour(double threshold){
		super();
		this.threshold=threshold;
	}
	@Override
	public void computeNeighbourhoods(SimilarityMap simMap) {
		// TODO Auto-generated method stub
		for(Integer itemId: simMap.getIDs()){
			Profile.Profile pro=simMap.getSimilarities(itemId.intValue());
			if(pro!=null){
				for(Integer ids:pro.getIDs()){
					if(Math.abs(pro.getValue(ids.intValue()))<threshold){
						this.addValue(itemId, ids);
					}
				}
			}
		}
	}

}

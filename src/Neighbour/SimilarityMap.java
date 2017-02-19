package Neighbour;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Profile.Profile;
import Similarity.SimilarityInterface;

public class SimilarityMap {
	private Map<Integer, Profile> simMap;//simimarity map to restore the distance 
	/**
	 * constructor - creates a new SimilarityMap object
	 */
	public SimilarityMap(){
		simMap=new HashMap<Integer, Profile>();
	}
	/**
	 * constructor - creates a new SimilarityMap object
     *@param data map 
     *@param similarity
	 */
	public SimilarityMap(Map<Integer, Profile> dataMap, SimilarityInterface similarity){
		simMap=new HashMap<Integer,Profile>();
		for(Integer id1:dataMap.keySet())
			for(Integer id2:dataMap.keySet()){
				if(id2.intValue()<id1.intValue()){
					double sim=similarity.getSimilarity(dataMap.get(id1.intValue()), dataMap.get(id2.intValue()));
					setSimilarity(id1, id2, sim);
					setSimilarity(id2, id1, sim);
				}
			}
	}
	/**
	 * @returns the numeric IDs of the map
	 */
	public Set<Integer> getIDs(){
		return simMap.keySet();
	}
	/**
	 * @returns the similarity profile
	 * @param the numeric ID of the profile
	 */
	public Profile getSimilarities(Integer id)
	{
		return simMap.get(id);
	}
	/**
	 * @returns the double similarity
	 * @param the Integer ID id1
	 * @param the integer ID id2
	 */	
	public double getCertianSimilarity(Integer id1,Integer id2){
		if(simMap.containsKey(id1))
			return (simMap.get(id1).containID(id2) ? simMap.get(id1).getValue(id2) : 0);
		else 
			return 0;

	}
	/**
	 * @param the Integer ID id1
	 * @param the integer ID id2
	 * @param double similarity
	 */	

	public void setSimilarity(Integer id1, Integer id2, double sim){
		Profile profile = simMap.containsKey(id1) ? simMap.get(id1) : new Profile(id1);
		profile.addvalue(id2, new Double(sim));
		simMap.put(id1, profile);
	}
}

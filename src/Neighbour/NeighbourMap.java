package Neighbour;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public abstract class NeighbourMap {
	private Map<Integer, Set<Integer>> neighbourMap;//map to store neighbour id
	/**
	 * constructor - creates a new NeighbourMap object
	 */
	public NeighbourMap(){
		neighbourMap=new HashMap<Integer, Set<Integer>>();
	}
	/**
	 * @return ture id id1&id2 are neighbour, flase if not
	 * @param id1
	 * @param id2
	 * @return
	 */
	public boolean isNeighbour(Integer id1,Integer id2){
		if(neighbourMap.containsKey(id1)){
			return neighbourMap.get(id1).contains(id2);
		}
		return false;
	}
	/**
	 * add neighbour id to certian place
	 * @param numeric id1
	 * @param numeric id2
	 */
	public void addValue(Integer id1,Integer id2){
		Set<Integer> set = neighbourMap.containsKey(id1) ? neighbourMap.get(id1) : new HashSet<Integer>();
		set.add(id2);
		neighbourMap.put(id1, set);
	}
	public abstract void computeNeighbourhoods(final SimilarityMap simMap);
}

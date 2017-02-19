package Profile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import StatisDeal.QuieckSortMedium;

public class Profile {
	private Integer id;//the id of the itemlist
	private Map<Integer, Double> itemListMap;//list map to store item rating of certain user
	/**
	 * constructor - creates a new ItemList object
	 * @param id
	 */
	public Profile(Integer id){
		this.id=id;
		itemListMap=new HashMap<Integer,Double>();
	}
	/**
	 * @returns the ItemList ID
	 */
	public Integer getID() {
		return id;
	}
	/**
	 * @returns the ItemList Size
	 */
	public int getSize() {
		return itemListMap.size();
	}
	/**
	 * @returns true if itemlist contians the id
	 */	
	public boolean containID(final int id){
		return itemListMap.containsKey(id);
	}
	/**
	 * @returns value of certain id
	 */		
	public Double getValue(int id) {
		return itemListMap.get(id);
	}
	/**
	 * @returns list of id it contains
	 */		
	public Set<Integer> getIDs(){
		return itemListMap.keySet();
	}
	/**
	 * @returns mean without certian id
	 */		
	public double getMeanWithoutID(int id){
		double sum=0;
		int count=0;
		for(Integer key:getIDs()){
			if(key.intValue()!=id){
				sum+=itemListMap.get(key);
				count++;
			}
		}
		if(count>0)
			return sum/count;
		else 
			return -1;
	}
	/**
	 * @add value to itemlist
	 */		
	public void addvalue(Integer id,Double itemValue) {
		itemListMap.put(id, itemValue);
	}
	/**
	 * @returns mean of each line
	 */	
	public double getMeanValue(){
		double sum=0;
		for(Double r:itemListMap.values())
			sum+=r.doubleValue();
		return getSize()>0?sum/getSize():0;
	}
	/**
	 * @returns median of each line
	 */
	public double getMedianValue(){
		QuieckSortMedium QuickMedium=new QuieckSortMedium();
		QuickMedium.findMedium(itemListMap.values().toArray());
		return QuickMedium.getMedium();
	}
	/**
	 * @returns standard deviation of each line
	 */
	public double GetStandardDeviation(){
		double mean=getMeanValue();
		double sum=0.0;
		for(Double r:itemListMap.values())
			sum+=Math.pow((r.doubleValue()-mean), 2);
		return Math.sqrt(sum/getSize());
	}
	/**
	 * @returns max of each line
	 */
	public double getMaxValue(){
		double max=-1.0;
		for(Double r:itemListMap.values())
			if(max<r.doubleValue())
				max=r.doubleValue();
		return max;
	}
	/**
	 * @returns min of each line
	 */
	public double getMinValue(){
		double min=10;
		for(Double r: itemListMap.values())
			if(min>r.doubleValue())
				min=r.doubleValue();
		return min;
	}
	/**
	 * @returns set of common IDs
	 * @param profile p
	 */
	public Set<Integer> getCommonID(Profile p){
		Set<Integer> common=new HashSet<Integer>();
		for(Integer id:getIDs())
			if(p.containID(id))
				common.add(id);
		return common;
	}
	/**
	 * @returns set of common IDs
	 */
	public double getNorm(){
		double sumsq = 0;

		for(Double r: itemListMap.values())
			sumsq += Math.pow(r.doubleValue(), 2);

		return Math.sqrt(sumsq);

	}
}

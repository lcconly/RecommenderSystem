package Neighbour;


public class SortedElement implements Comparable<Object> {
	public double score;//score as the key to be sorted
	public Object thing;//thing
	public boolean abs;//boolean abs
	/**
	 * conductor to create a sortedElement object
	 * @param score
	 * @param thing
	 */
	public SortedElement(double score,Object thing){
		this.score=score;
		this.thing=thing;
		abs=false;
	}
	/**
	 * conductor to create a sortedElement object
	 * @param score
	 * @param thing
	 * @param abs
	 */
	public SortedElement(double score,Object thing,boolean abs){
		this.score=score;
		this.thing=thing;
		this.abs=abs;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		SortedElement st = (SortedElement) o;
		if(abs)
			return (Math.abs(score) > Math.abs(st.score)) ? -1 : +1;
		else
			return (score > st.score) ? -1 : +1;

	}

}

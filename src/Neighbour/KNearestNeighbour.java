package Neighbour;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import Profile.Profile;

public class KNearestNeighbour extends NeighbourMap{
	private int k;//para k
	/**
	 * conductor to init knearestneighbour object
	 * @param k
	 */
	public	KNearestNeighbour(int k){
		super();
		this.k=k;
	}
	@Override
	public void computeNeighbourhoods(SimilarityMap simMap) {
		// TODO Auto-generated method stub
		SortedSet<SortedElement> sortS=new TreeSet<SortedElement>();
		for(Integer id:simMap.getIDs()){
			Profile pro=simMap.getSimilarities(id);
			if(pro!=null){
				for(Integer id_pro: pro.getIDs()) // iterate over each item in the profile
				{
					double sim = pro.getValue(id_pro);
					if(sim > 0)
						sortS.add(new SortedElement(sim, id_pro));
				}
			}
			// get the k most similar items (neighbours)
			int counter = 0;
			for(Iterator<SortedElement> iter = sortS.iterator(); iter.hasNext() && counter < k; )
			{
				SortedElement st = iter.next();
				Integer id_ele = (Integer)st.thing;
				this.addValue(id, id_ele);
				counter++;
			}
		}
	}

}

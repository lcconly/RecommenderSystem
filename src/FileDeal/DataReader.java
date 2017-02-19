package FileDeal;

import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import Pairs.UserItemPair;
import Profile.Profile;

public class DataReader {
	private String filename;
	private Map<Integer, Profile> userMLRating;//user based rating map
	private Map<Integer, Profile> itemMLRating;//item based rating map
	private Map<UserItemPair,Double> dataSet;//dataSet including user,item and rating
	private int total_rating_num;//total rating num
	private int userNum;
	private int itemNum;
	private int numOfStar[];
	/**
	 * constructor - creates a new FileReader object and load file
	 * @param file
	 */
	public DataReader(String file){
		this.filename=file;
		total_rating_num=0;
		userNum=0;
		itemNum=0;
		numOfStar=new int[5];
		for(int i=0;i<5;i++){
			numOfStar[i]=0;
		}
		userMLRating=new HashMap<Integer, Profile>();
		itemMLRating=new HashMap<Integer, Profile>();
		dataSet=new HashMap<UserItemPair, Double>();
		loadMLRating();
	}
	/**
	 * @returns the total rating num
	 */
	public int getTotalRatingNum() {
		return total_rating_num;
	}
	/**
	 * @returns the user num
	 */
	public int getUserNum() {
		return userNum;
	}
	/**
	 * @returns the numofstar
	 */
	public int[] getNumOfStar() {
		return numOfStar;
	}
	/**
	 * @returns the item num
	 */
	public int getitemNum() {
		return itemNum;
	}
	/**
	 * @returns the density matric =totalnum/(userNum*itemNum)
	 */
	public double getDensityMatric(){
		return ((double)total_rating_num)/(((double)userNum)*((double)itemNum));
	}
	/**
	 * @returns the userMLRating
	 */
	public Map<Integer, Profile>getUserMLRating(){
		return userMLRating;
	}
	/**
	 * @returns the profile of certain user: userID
	 */
	public Profile getUserProfile(int userID){
		return userMLRating.get(userID);
	}
	/**
	 * @returns the profile of certain user: userID
	 */
	public Profile getItemProfile(int itemID){
		return itemMLRating.get(itemID);
	}	
	/**
	 * @returns the all item id
	 */
	public Set<Integer> getItemIDs(){
		return itemMLRating.keySet();
	}
	/**
	 * @returns the all user id
	 */
	public Set<Integer> getUserIDs(){
		return userMLRating.keySet();
	}
	/**
	 * @returns the userMLRating
	 */
	public Map<Integer, Profile>getItemMLRating(){
		return itemMLRating;
	}
	/**
	 * @returns dataSet
	 */
	public Map<UserItemPair,Double> getDataSet(){
		return dataSet;
	}
	/**
	 * Used for load MovieLens 100K
	 */
	private void loadMLRating(){
		try{
			BufferedReader rd=new BufferedReader(new FileReader(new File(filename)));
			String line;
			//loop to read data file
			while((line=rd.readLine())!=null){
				StringTokenizer st = new StringTokenizer(line, ", \t\n\r\f");
				if(st.countTokens() != 4)
				{
					System.out.println("Error reading from file \"" + filename + "\"");
					System.exit(1);
				}
				Integer userId = Integer.valueOf(st.nextToken());
				Integer itemId = Integer.valueOf(st.nextToken());
				Double rating = Double.valueOf(st.nextToken());
				//init user-based matrix
				Profile item=userMLRating.containsKey(userId)?userMLRating.get(userId):new Profile(userId);
				item.addvalue(itemId, rating);
				userMLRating.put(userId, item);
				//init item-based matrix				
				Profile user=itemMLRating.containsKey(itemId)?itemMLRating.get(itemId):new Profile(itemId);
				user.addvalue(userId, rating);
				itemMLRating.put(itemId, user);
				
				//init dataSet
				UserItemPair pair=new UserItemPair(userId, itemId);
				dataSet.put(pair, rating);
				
				//count total rating num, user num and item num
				total_rating_num++;
				//count useNum and item num
				if(userId>userNum)
					userNum=userId;
				if(itemId>itemNum)
					itemNum=itemId;
				//count num of each star
				if(rating.doubleValue()==1.0)
					numOfStar[0]=numOfStar[0]+1;
				else if(rating.doubleValue()==2.0)
					numOfStar[1]=numOfStar[1]+1;
				else if(rating.doubleValue()==3.0)
					numOfStar[2]=numOfStar[2]+1;
				else if(rating.doubleValue()==4.0)
					numOfStar[3]=numOfStar[3]+1;
				else if(rating.doubleValue()==5.0)
					numOfStar[4]=numOfStar[4]+1;

			}
			rd.close();			
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}

	}
}

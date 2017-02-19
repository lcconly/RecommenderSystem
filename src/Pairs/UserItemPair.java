package Pairs;

public class UserItemPair {
	private Integer UserID;//userID
	private Integer ItemID;//ItemID
	/**
	 * constructor - creates a new UserItemPair object
	 */
	public UserItemPair( Integer UserID,  Integer ItemID) {
		// TODO Auto-generated constructor stub
		this.UserID=UserID;
		this.ItemID=ItemID;
	}
	/**
	 * @returns the user ID
	 */
	public Integer getUserID() {
		return UserID;
	}
	/**
	 * @returns the Item ID
	 */
	public Integer getItemID(){
		return ItemID;
	}
	/**
	 * @returns String userid,itemid
	 */
	public String toString(){
		return UserID.intValue()+","+ItemID.intValue();
	}
}

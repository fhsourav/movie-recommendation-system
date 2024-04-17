import java.util.ArrayList;

/**
 * keeps track of one rater and all their ratings
 */
public class PlainRater implements Rater {

	private String myID;                    // a unique ID for this rater
	private ArrayList<Rating> myRatings;    // an ArrayList of Ratings

	public PlainRater(String id) {
		myID = id;
		myRatings = new ArrayList<>();
	}

	/**
	 * A new Rating is created and added to myRatings
	 * @param item
	 * @param rating
	 */
	public void addRating(String item, double rating) {
		myRatings.add(new Rating(item, rating));
	}

	public boolean hasRating(String item) {
		for (int k = 0; k < myRatings.size(); k++) {
			if (myRatings.get(k).getItem().equals(item)) {
				return true;
			}
		}
		return false;
	}

	public String getID() {
		return myID;
	}

	/**
	 *
	 * @param item
	 * @return the double rating of this item if it is in myRatings. Otherwise, this method returns -1.
	 */
	public double getRating(String item) {
		for (int k = 0; k < myRatings.size(); k++) {
			if (myRatings.get(k).getItem().equals(item)) {
				return myRatings.get(k).getValue();
			}
		}
		return -1;
	}

	/**
	 *
	 * @return the number of ratings this rater has
	 */
	public int numRatings() {
		return myRatings.size();
	}

	/**
	 *
	 * @return an ArrayList of Strings representing a list of all the items that have been rated
	 */
	public ArrayList<String> getItemsRated() {
		ArrayList<String> list = new ArrayList<>();
		for (int k = 0; k < myRatings.size(); k++) {
			list.add(myRatings.get(k).getItem());
		}
		return list;
	}

}

/**
 * An immutable passive data object (PDO) to represent the rating data
 */
public class Rating implements Comparable<Rating> {

	private String item;    // a String description of the item being rated (IMDB ID)
	private double value;   // a double of the actual rating

	public Rating(String anItem, double aValue) {
		item = anItem;
		value = aValue;
	}

	/**
	 *
	 * @return item being rated
	 */
	public String getItem() {
		return item;
	}

	/**
	 *
	 * @return the value of this rating (as a number so it can be used in calculations)
	 */
	public double getValue() {
		return value;
	}

	/**
	 *
	 * @return a string of all the rating information
	 */
	@Override
	public String toString() {
		return "[" + getItem() + ", " + getValue() + "]";
	}

	/**
	 * @param other the object to be compared.
	 * @return
	 */
	@Override
	public int compareTo(Rating other) {
		if (value < other.value) return -1;
		if (value > other.value) return 1;
		return 0;
	}
}

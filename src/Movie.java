/**
 * An immutable passive data object (PDO) to represent item data
 */
public class Movie {

	private String id;          // a String variable representing the IMDB ID of the movie
	private String title;       // a String variable for the movie's title
	private int year;           // an integer representing the year of release
	private String genres;      // one String of one or more genres separated by commas
	private String director;    // one String of one or more directors of the movie separated by commas
	private String country;     // one String of one or more countries the film was made in, separated by commas
	private String poster;      // a String that is a link to an image of the movie poster if one exists, or "N/A" if no poster exists
	private int minutes;        // an integer for the length of the movie

	public Movie(String anID, String aTitle, String aYear, String theGenres) {
		// just in case data file contains extra whitespace
		id = anID.trim();
		title = aTitle.trim();
		year = Integer.parseInt(aYear.trim());
		genres = theGenres;
	}

	public Movie(String anID, String aTitle, String aYear, String theGenres,
	             String aDirector, String aCountry, String aPoster, int theMinutes) {
		// just in case data file contains extra whitespace
		id = anID.trim();
		title = aTitle.trim();
		year = Integer.parseInt(aYear.trim());
		genres = theGenres;
		director = aDirector;
		country = aCountry;
		poster = aPoster;
		minutes = theMinutes;
	}

	/**
	 *
	 * @return ID associated with this item
	 */
	public String getID() {
		return id;
	}

	/**
	 *
	 * @return title of this item
	 */
	public String getTitle() {
		return title;
	}

	/**
	 *
	 * @return year in which this item was published
	 */
	public int getYear() {
		return year;
	}

	/**
	 *
	 * @return genres associated with this item
	 */
	public String getGenres() {
		return genres;
	}

	public String getDirector() {
		return director;
	}

	public String getCountry() {
		return country;
	}

	public String getPoster() {
		return poster;
	}

	public int getMinutes() {
		return minutes;
	}

	/**
	 *
	 * @return a string representation of the object
	 */
	@Override
	public String toString() {
		String result = "Movie [id=" + id + ", title=" + title + ", year=" + year;
		result += ", genres=" + genres + "]";
		return result;
	}

}

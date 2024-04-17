import java.io.IOException;
import java.util.ArrayList;

public class SecondRatings {

	private ArrayList<Movie> myMovies;
	private ArrayList<Rater> myRaters;

	public SecondRatings(String moviefile, String ratingsfile) {
		FirstRatings fr = new FirstRatings();
		myMovies = fr.loadMovies(moviefile);
		myRaters = fr.loadRaters(ratingsfile);
	}

	public SecondRatings() {
		this("ratedmoviesfull.csv", "ratings.csv");
	}

	/**
	 *
	 * @return the number of movies that were read in and stored in the ArrayList of type Movie
	 */
	public int getMovieSize() {
		return myMovies.size();
	}

	/**
	 *
	 * @return the number of raters that were read in and stored in the ArrayList of type Rater
	 */
	public int getRaterSize() {
		return myRaters.size();
	}

	/**
	 * find the average rating for every movie that has been rated by at least minimalRaters raters
	 * @param id representing a movie ID
	 * @param minimalRaters
	 * @return a double representing the average movie rating for this ID if there are at least minimalRaters ratings. if there are not minimalRaters ratings, then it returns 0.0
	 */
	private double getAverageByID(String id, int minimalRaters) {
		double average = 0.0;
		double sum = 0.0;
		int count = 0;

		for (Rater r : myRaters) {
			double rate = r.getRating(id);
			if (rate != -1) {
				sum += rate;
				count++;
			}
		}

		if (count < minimalRaters) {
			return average;
		}

		average = sum / count;
		return average;
	}

	/**
	 * find the average rating for every movie that has been rated by at least minimalRaters raters
	 * @param minimalRaters
	 * @return an ArrayList of all the Rating objects for movies that have at least the minimal number of raters supplying a rating
	 */
	public ArrayList<Rating> getAvergeRatings(int minimalRaters) {
		ArrayList<Rating> averageRatings = new ArrayList<>();

		for (Movie m : myMovies) {
			double average = getAverageByID(m.getID(), minimalRaters);
			if (average != 0.0) {
				Rating r = new Rating(m.getID(), average);
				averageRatings.add(r);
			}
		}

		return averageRatings;
	}

	/**
	 *
	 * @param id ID of a movie
	 * @return the title of the movie with given ID. if the movie ID does not exist, returns a String indicating the ID was not found.
	 */
	public String getTitle(String id) {
		for (Movie m : myMovies) {
			if (m.getID().equals(id)) {
				return m.getTitle();
			}
		}

		return "ID was not found";
	}

	/**
	 *
	 * @param title the title of a movie
	 * @return the movie ID of given movie title. if the title is not found, returns a message, NO SUCH TITLE
	 */
	public String getID(String title) {
		for (Movie m : myMovies) {
			if (m.getTitle().equals(title)) {
				return m.getID();
			}
		}

		return "NO SUCH TITLE";
	}

}

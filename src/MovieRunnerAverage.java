import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {

	public void printAverageRatings() {
		SecondRatings sr = new SecondRatings("files/ratedmovies_short.csv", "files/ratings_short.csv");

//		System.out.println("Movie size:\t" + sr.getMovieSize());
//		System.out.println("Rater size:\t" + sr.getRaterSize());

		ArrayList<Rating> averageRatings = sr.getAvergeRatings(3);

		Collections.sort(averageRatings);
		for (Rating r : averageRatings) {
			System.out.println(r.getValue() + " " + sr.getTitle(r.getItem()));
		}
	}

	public void getAverageRatingOneMovie() {
		SecondRatings sr = new SecondRatings("files/ratedmovies_short.csv", "files/ratings_short.csv");

		ArrayList<Rating> averageRatings = sr.getAvergeRatings(0);
		String title = "The Godfather";
		String id = sr.getID(title);

		if (id.equals("ID was not found")) {
			System.out.println(id);
			return;
		}

		for (Rating r : averageRatings) {
			if (r.getItem().equals(id)) {
				System.out.println(r.getValue());
				break;
			}
		}
	}

}

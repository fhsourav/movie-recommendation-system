import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {

	public void printAverageRatings() {
		ThirdRatings tr = new ThirdRatings("files/ratings_short.csv");
		System.out.println("read data for " + tr.getRaterSize() + " rater");

		MovieDatabase.initialize("files/ratedmovies_short.csv");
		System.out.println("read data for " + MovieDatabase.size() + " movies");

		ArrayList<Rating> averageRatings = tr.getAverageRatings(1);
		System.out.println("found " + averageRatings.size() + " movies");

		Collections.sort(averageRatings);

		for (Rating r : averageRatings) {
			System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
		}
	}

	public void printAverageRatingsByYear() {
		ThirdRatings tr = new ThirdRatings("files/ratings_short.csv");
		System.out.println("read data for " + tr.getRaterSize() + " rater");

		MovieDatabase.initialize("files/ratedmovies_short.csv");
		System.out.println("read data for " + MovieDatabase.size() + " movies");

		YearAfterFilter filter = new YearAfterFilter(2000);
		ArrayList<Rating> averageRatings = tr.getAverageRatingsByFilter(1, filter);
		System.out.println("found " + averageRatings.size() + " movies");

		Collections.sort(averageRatings);

		for (Rating r : averageRatings) {
			System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
		}
	}

	public void printAverageRatingsByGenre() {
		ThirdRatings tr = new ThirdRatings("files/ratings_short.csv");
		System.out.println("read data for " + tr.getRaterSize() + " rater");

		MovieDatabase.initialize("files/ratedmovies_short.csv");
		System.out.println("read data for " + MovieDatabase.size() + " movies");

		GenreFilter filter = new GenreFilter("Crime");
		ArrayList<Rating> averageRatings = tr.getAverageRatingsByFilter(1, filter);
		System.out.println("found " + averageRatings.size() + " movies");

		Collections.sort(averageRatings);

		for (Rating r : averageRatings) {
			System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
		}
	}

	public void printAverageRatingsByMinutes() {
		ThirdRatings tr = new ThirdRatings("files/ratings_short.csv");
		System.out.println("read data for " + tr.getRaterSize() + " rater");

		MovieDatabase.initialize("files/ratedmovies_short.csv");
		System.out.println("read data for " + MovieDatabase.size() + " movies");

		MinutesFilter filter = new MinutesFilter(110, 170);
		ArrayList<Rating> averageRatings = tr.getAverageRatingsByFilter(1, filter);
		System.out.println("found " + averageRatings.size() + " movies");

		Collections.sort(averageRatings);

		for (Rating r : averageRatings) {
			System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
		}
	}

	public void printAverageRatingsByDirectors() {
		ThirdRatings tr = new ThirdRatings("files/ratings_short.csv");
		System.out.println("read data for " + tr.getRaterSize() + " rater");

		MovieDatabase.initialize("files/ratedmovies_short.csv");
		System.out.println("read data for " + MovieDatabase.size() + " movies");

		DirectorsFilter filter = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
		ArrayList<Rating> averageRatings = tr.getAverageRatingsByFilter(1, filter);
		System.out.println("found " + averageRatings.size() + " movies");

		Collections.sort(averageRatings);

		for (Rating r : averageRatings) {
			System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
		}
	}

	public void printAverageRatingsByYearAfterAndGenre() {
		ThirdRatings tr = new ThirdRatings("files/ratings_short.csv");
		System.out.println("read data for " + tr.getRaterSize() + " rater");

		MovieDatabase.initialize("files/ratedmovies_short.csv");
		System.out.println("read data for " + MovieDatabase.size() + " movies");

		AllFilters filter = new AllFilters();
		filter.addFilter(new YearAfterFilter(1980));
		filter.addFilter(new GenreFilter("Romance"));

		ArrayList<Rating> averageRatings = tr.getAverageRatingsByFilter(1, filter);
		System.out.println("found " + averageRatings.size() + " movies");

		Collections.sort(averageRatings);

		for (Rating r : averageRatings) {
			System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
		}
	}

	public void printAverageRatingsByDirectorsAndMinutes() {
		ThirdRatings tr = new ThirdRatings("files/ratings_short.csv");
		System.out.println("read data for " + tr.getRaterSize() + " rater");

		MovieDatabase.initialize("files/ratedmovies_short.csv");
		System.out.println("read data for " + MovieDatabase.size() + " movies");

		AllFilters filter = new AllFilters();
		filter.addFilter(new MinutesFilter(30, 170));
		filter.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));

		ArrayList<Rating> averageRatings = tr.getAverageRatingsByFilter(1, filter);
		System.out.println("found " + averageRatings.size() + " movies");

		Collections.sort(averageRatings);

		for (Rating r : averageRatings) {
			System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
		}
	}

}

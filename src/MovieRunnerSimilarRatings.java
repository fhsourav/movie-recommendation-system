import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {

	public void printAverageRatings() {
		FourthRatings fr = new FourthRatings();

		RaterDatabase.initialize("files/ratings.csv");
		System.out.println("read data for " + RaterDatabase.size() + " raters");

		MovieDatabase.initialize("files/ratedmoviesfull.csv");
		System.out.println("read data for " + MovieDatabase.size() + " movies");

		ArrayList<Rating> averageRatings = fr.getAverageRatings(1);

		System.out.println("found " + averageRatings.size() + " movies");

		Collections.sort(averageRatings);
		for (Rating r : averageRatings) {
			System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
		}
	}

	public void printAverageRatingsByYearAfterAndGenre() {
		FourthRatings fr = new FourthRatings();

		RaterDatabase.initialize("files/ratings.csv");
		System.out.println("read data for " + RaterDatabase.size() + " raters");

		MovieDatabase.initialize("files/ratedmoviesfull.csv");
		System.out.println("read data for " + MovieDatabase.size() + " movies");

		AllFilters filter = new AllFilters();
		filter.addFilter(new YearAfterFilter(1980));
		filter.addFilter(new GenreFilter("Romance"));

		ArrayList<Rating> averageRatings = fr.getAverageRatingsByFilter(1, filter);

		System.out.println("found " + averageRatings.size() + " movies");

		Collections.sort(averageRatings);
		for (Rating r : averageRatings) {
			System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
		}
	}

	public void printSimilarRatings() {
		FourthRatings fr = new FourthRatings();
		MovieDatabase.initialize("files/ratedmoviesfull.csv");
		RaterDatabase.initialize("files/ratings.csv");

		ArrayList<Rating> similarRatings = fr.getSimilarRatings("65", 20, 5);

		for (Rating r : similarRatings) {
			System.out.println(MovieDatabase.getMovie(r.getItem()));
		}
	}

	public void printSimilarRatingsByGenre() {
		FourthRatings fr = new FourthRatings();
		MovieDatabase.initialize("files/ratedmoviesfull.csv");
		RaterDatabase.initialize("files/ratings.csv");

		GenreFilter filter = new GenreFilter("Action");
		ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter("65", 20, 5, filter);

		for (Rating r : similarRatings) {
			System.out.println(MovieDatabase.getMovie(r.getItem()));
		}
	}

	public void printSimilarRatingsByDirector() {
		FourthRatings fr = new FourthRatings();
		MovieDatabase.initialize("files/ratedmoviesfull.csv");
		RaterDatabase.initialize("files/ratings.csv");

		DirectorsFilter filter = new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone");
		ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter("1034", 10, 3, filter);

		for (Rating r : similarRatings) {
			System.out.println(MovieDatabase.getMovie(r.getItem()));
		}
	}

	public void printSimilarRatingsByGenreAndMinutes() {
		FourthRatings fr = new FourthRatings();
		MovieDatabase.initialize("files/ratedmoviesfull.csv");
		RaterDatabase.initialize("files/ratings.csv");

		AllFilters filter = new AllFilters();
		filter.addFilter(new GenreFilter("Adventure"));
		filter.addFilter(new MinutesFilter(100, 200));
		ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter("65", 10, 5, filter);

		for (Rating r : similarRatings) {
			System.out.println(MovieDatabase.getMovie(r.getItem()));
		}
	}

	public void printSimilarRatingsByYearAfterAndMinutes() {
		FourthRatings fr = new FourthRatings();
		MovieDatabase.initialize("files/ratedmoviesfull.csv");
		RaterDatabase.initialize("files/ratings.csv");

		AllFilters filter = new AllFilters();
		filter.addFilter(new YearAfterFilter(2000));
		filter.addFilter(new MinutesFilter(80, 100));
		ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter("65", 10, 5, filter);

		for (Rating r : similarRatings) {
			System.out.println(MovieDatabase.getMovie(r.getItem()));
		}
	}

}

import java.util.ArrayList;

public class ThirdRatings {

	private ArrayList<Rater> myRaters;

	public ThirdRatings(String ratingsfile) {
		FirstRatings fr = new FirstRatings();
		myRaters = fr.loadRaters(ratingsfile);
	}

	public ThirdRatings() {
		this("ratings.csv");
	}

	public int getRaterSize() {
		return myRaters.size();
	}

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

	public ArrayList<Rating> getAverageRatings(int minimalRaters) {
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		ArrayList<Rating> averageRatings = new ArrayList<>();

		for (String id : movies) {
			double average = getAverageByID(id, minimalRaters);
			if (average != 0.0) {
				Rating r = new Rating(id, average);
				averageRatings.add(r);
			}
		}

		return averageRatings;
	}

	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
		ArrayList<String> moviesByFilter = MovieDatabase.filterBy(filterCriteria);
		ArrayList<Rating> averageRatings = new ArrayList<>();

		for (String id : moviesByFilter) {
			double average = getAverageByID(id, minimalRaters);
			if (average != 0.0) {
				Rating r = new Rating(id, average);
				averageRatings.add(r);
			}
		}

		return averageRatings;
	}

}

import java.util.ArrayList;
import java.util.Collections;

public class FourthRatings {

	private double getAverageByID(String id, int minimalRaters) {
		double average = 0.0;
		double sum = 0.0;
		int count = 0;

		for (Rater r : RaterDatabase.getRaters()) {
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
		ArrayList<Rating> averageRatings = new ArrayList<>();
		ArrayList<String> moviesByFilter = MovieDatabase.filterBy(filterCriteria);

		for (String id : moviesByFilter) {
			double average = getAverageByID(id, minimalRaters);
			if (average != 0.0) {
				Rating r = new Rating(id, average);
				averageRatings.add(r);
			}
		}

		return averageRatings;
	}

	private double dotProduct(Rater me, Rater r) {
		double result = 0;

		for (String movieID : me.getItemsRated()) {
			if (r.hasRating(movieID)) {
				double meRating = me.getRating(movieID) - 5;
				double rRating = r.getRating(movieID) - 5;
				result += meRating * rRating;
			}
		}

		return result;
	}

	private ArrayList<Rating> getSimilarities(String id) {
		ArrayList<Rating> similaritiesList = new ArrayList<>();
		Rater me = RaterDatabase.getRater(id);

		for (Rater r : RaterDatabase.getRaters()) {
			if (r.equals(me)) {
				continue;
			}

			double similarityRating = dotProduct(me, r);
			if (similarityRating > 0) {
				Rating rating = new Rating(r.getID(), similarityRating);
				similaritiesList.add(rating);
			}
		}

		Collections.sort(similaritiesList, Collections.reverseOrder());
		return similaritiesList;
	}

	public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
		ArrayList<Rating> similarRatings = new ArrayList<>();
		ArrayList<Rating> similarRaters = getSimilarities(id);

		for (String movieID : MovieDatabase.filterBy(new TrueFilter())) {
			double sum = 0.0;
			double average = 0.0;
			int count = 0;

			for (int i = 0; i < numSimilarRaters; i++) {
				Rating sr = similarRaters.get(i);

				Rater rater = RaterDatabase.getRater(sr.getItem());
				double weight = sr.getValue();
				double rating = rater.getRating(movieID);

				if (rating != -1) {
					sum += rating * weight;
					count++;
				}
			}

			if (count < minimalRaters) {
				continue;
			}

			average = sum / count;
			Rating rating = new Rating(movieID, average);
			similarRatings.add(rating);
		}

		Collections.sort(similarRatings, Collections.reverseOrder());
		return similarRatings;
	}

	public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
		ArrayList<Rating> similarRatings = new ArrayList<>();
		ArrayList<Rating> similarRaters = getSimilarities(id);

		for (String movieID : MovieDatabase.filterBy(filterCriteria)) {
			double sum = 0.0;
			double average = 0.0;
			int count = 0;

			for (int i = 0; i < numSimilarRaters; i++) {
				Rating sr = similarRaters.get(i);

				Rater rater = RaterDatabase.getRater(sr.getItem());
				double weight = sr.getValue();
				double rating = rater.getRating(movieID);

				if (rating != -1) {
					sum += rating * weight;
					count++;
				}
			}

			if (count < minimalRaters) {
				continue;
			}

			average = sum / count;
			Rating rating = new Rating(movieID, average);
			similarRatings.add(rating);
		}

		Collections.sort(similarRatings, Collections.reverseOrder());
		return similarRatings;
	}

}

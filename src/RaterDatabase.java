import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class RaterDatabase {

	private static HashMap<String, Rater> ourRaters;

	private static void initialize() {
		// this method is only called from addRatings
		if (ourRaters == null) {
			ourRaters = new HashMap<>();
		}
	}

	public static void initialize(String filename) {
		if (ourRaters == null) {
			ourRaters = new HashMap<>();
			addRatings(filename);
		}
	}

	private static ArrayList<HashMap<String, String>> parseCSV(String filename) {
		Path p = Paths.get(filename);
		BufferedReader reader = null;
		try {
			reader = Files.newBufferedReader(p);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		ArrayList<HashMap<String, String>> elements = new ArrayList<>();

		String headers = null;
		try {
			headers = reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		String[] headerArray = null;

		if (headers != null) {
			headerArray = headers.trim().split(",");
		} else {
			System.out.println("Empty file");
			System.exit(1);
		}

		while (true) {
			String line = null;
			try {
				line = reader.readLine();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			if (line == null) {
				break;
			}

			String[] data = line.split(",");
			HashMap<String, String> map = new HashMap<>();

			for (int i = 0; i < data.length; i++) {
				map.put(headerArray[i], data[i]);
			}

			elements.add(map);
		}

		return elements;
	}

	public static void addRatings(String filename) {
		initialize();

		for (HashMap<String, String> rec : parseCSV(filename)) {
			String id = rec.get("rater_id");
			String item = rec.get("movie_id");
			String rating = rec.get("rating");
			addRaterRating(id, item, Double.parseDouble(rating));
		}
	}

	public static void addRaterRating(String raterID, String movieID, double rating) {
		initialize();
		Rater rater = null;

		if (ourRaters.containsKey(raterID)) {
			rater = ourRaters.get(raterID);
		} else {
			rater = new EfficientRater(raterID);
			ourRaters.put(raterID, rater);
		}
		rater.addRating(movieID, rating);
	}

	public static Rater getRater(String id) {
		initialize();

		return ourRaters.get(id);
	}

	public static ArrayList<Rater> getRaters() {
		initialize();
		ArrayList<Rater> list = new ArrayList<>(ourRaters.values());

		return list;
	}

	public static int size() {
		return ourRaters.size();
	}

}

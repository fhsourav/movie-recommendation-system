import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class FirstRatings {

	public ArrayList<Movie> loadMovies(String filename) throws IOException {
		Path p = Paths.get(filename); // filepath
		BufferedReader reader = Files.newBufferedReader(p); // reading the file

//		ArrayList<HashMap<String, String>> movieData = new ArrayList<>(); // Each entry in the ArrayList represents each movie in the CSV file // update: using ArrayList of type Movie instead
		ArrayList<Movie> movieData = new ArrayList<>();

		String headers = reader.readLine(); // getting the CSV headers from the first line
		String[] headerArray = new String[0]; // initializing String array for the headers

		if (headers != null) {
			headerArray = headers.trim().split(","); // populating headerArray by splitting the headers by commas
		} else {
			System.out.println("Empty file"); // in case the file is empty
			System.exit(1);
		}

		while (true) { // iterating through the CSV lines in BufferedReader reader
			String line = reader.readLine();
			if (line == null) {
				break; // iteration stops if line is null
			}

			HashMap<String, String> map = getMapForLoadMovies(line, headerArray); // getting the movie data mapped to appropriate headers
//			movieData.add(map); // after the map is complete, we add it to the movieData ArrayList. we will use this list to create Movie objects. //update: this is now unnecessary since we directly create a movie object using the map and add the movie to the ArrayList

			Movie movie = new Movie( // creating a Movie object from the HashMap
					map.get("id"),
					map.get("title"),
					map.get("year"),
					map.get("genre"),
					map.get("director"),
					map.get("country"),
					map.get("poster"),
					Integer.parseInt(map.get("minutes")));

			movieData.add(movie); // adding movie to instance variable movieList
		}

		return movieData; // movieData is ultimately returned
	}

	/**
	 *
	 * @param line Any CSV line except for the first line or header line
	 * @param headerArray a String array that contains the headers
	 * @return a HashMap that maps the data found in line to headers from headerArray
	 */
	private HashMap<String, String> getMapForLoadMovies(String line, String[] headerArray) {
		String[] data = line.split("[,\"]"); // splitting the line to separate movie data
		HashMap<String, String> map = new HashMap<>(); // hashmap to map the data to appropriate header

		int headerCount = 0; // for iterating the headers
		StringBuilder sb = new StringBuilder(); // since the splitting has caused the separation of non-separate values (e.g. genres get separated because of the commas between them), a StringBuilder is used to negate the separation
		for (int i = 0; i < data.length; i++) {
			if (!data[i].isBlank()) { // due to the nature of the delimiter used for splitting, there are considerable number of blank strings. thankfully the blank lines can help us in negating the separation of non-separate values
				sb.append(data[i]); // while data[i] is not blank, we keep appending it to the StringBuilder
				sb.append(','); // And we append the commas we lost (e.g. genre)
			} else if (!sb.isEmpty()) { // StringBuilder can be empty if there are consecutive blank strings in data
				sb.deleteCharAt(sb.length() - 1); // if StringBuilder is not empty and data[i] is blank, we delete the extra comma at the end
				map.put(headerArray[headerCount++], sb.toString()); // the string is put with the appropriate header in the map, headerCount is incremented by 1
				sb = new StringBuilder(); // sb now refers to a new StringBuilder to accommodate a new data
			}
		}
		sb.deleteCharAt(sb.length() - 1); // preparing the last entry
		map.put(headerArray[headerCount++], sb.toString()); // putting the last entry in the map
		return map;
	}

	public void testLoadMovies() throws IOException {
		ArrayList<Movie> movieList = loadMovies("files/ratedmovies_short.csv");
		System.out.println("Number of movies:\t" + movieList.size());
		int comedyCount = 0;
		int greater150count = 0;
		int maxMovies = 0;
		HashMap<String, Integer> directions = new HashMap<>();
		for (Movie movie : movieList) {
			if (movie.getGenres().contains("Comedy")) {
				comedyCount++;
			}
			if (movie.getMinutes() > 150) {
				greater150count++;
			}
			String[] directors = movie.getDirector().split(",");
			for (String director : directors) {
				director = director.trim();
				if (!directions.containsKey(director)) {
					directions.put(director, 1);
				} else {
					directions.put(director, directions.get(director) + 1);
				}
				if (directions.get(director) > maxMovies) {
					maxMovies = directions.get(director);
				}
			}
		}
		System.out.println("Comedy genre:\t" + comedyCount);
		System.out.println("Greater than 150 minutes:\t" + greater150count);
		System.out.println("Maximum movies by a director:\t" + maxMovies);
		for (String director: directions.keySet()) {
			if (directions.get(director) == maxMovies) {
				System.out.println(director);
			}
		}
	}

	public ArrayList<Rater> loadRaters(String filename) throws IOException {
		Path p = Paths.get(filename); // filepath
		BufferedReader reader = Files.newBufferedReader(p); // reading the file

		ArrayList<Rater> raterList = new ArrayList<>(); // Initializing the ArrayList

		String headers = reader.readLine(); // getting the CSV headers from the first line
		String[] headerArray = new String[0]; // initializing String array for the headers

		if (headers != null) {
			headerArray = headers.trim().split(","); // populating headerArray by splitting the headers by commas
		} else {
			System.out.println("Empty file"); // in case the file is empty
			System.exit(1);
		}

		while (true) { // iterating through the CSV lines in BufferedReader reader
			String line = reader.readLine();
			if (line == null) {
				break; // iteration stops if line is null
			}

			String[] data = line.split(","); // splitting the line to separate rating data
			HashMap<String, String> map = new HashMap<>(); // HashMap to map the data to appropriate header

			for (int i = 0; i < data.length; i++) { // since the CSV file does not contain quotation marks this time and is separated only by comma, we do not need a different counter variable for headers
				map.put(headerArray[i], data[i]); // mapping
			}

			boolean contains = false; // this is useful to check if a rater is already in the ArrayList
			for (Rater r : raterList) {
				if (r.getID().equals(map.get("rater_id"))) { // checking if a rater is already in the ArrayList using rater_id
					contains = true; // ArrayList contains the rater
					r.addRating( // add rating data to the rater
							map.get("movie_id"),
							Double.parseDouble(map.get("rating")));
					break;
				}
			}

			if (!contains) {
				Rater rater = new Rater(map.get("rater_id")); // creating new rater
				rater.addRating( // adding rating data to the rater
						map.get("movie_id"),
						Double.parseDouble(map.get("rating")));
				raterList.add(rater); // adding rater to the ArrayList
			}
		}

		return raterList; // ultimately returning the ArrayList
	}

	public void testLoadRaters() throws IOException {
		ArrayList<Rater> raterList = loadRaters("files/ratings_short.csv");
		System.out.println(raterList.size());

//		for (Rater rater : raterList) {
//			ArrayList<String> itemsRated = rater.getItemsRated();
//			System.out.println(rater.getID() + "\t" + rater.numRatings());
//			for (String item : itemsRated) {
//				System.out.println(item + "\t" + rater.getRating(item));
//			}
//		}

		String raterID = "2";
		int maxRates = 0;
		for (Rater rater : raterList) {
			if (rater.getID().equals(raterID)) {
				System.out.println("Ratings of rater " + raterID + ":\t" + rater.numRatings());
			}
			if (rater.numRatings() > maxRates) {
				maxRates = rater.numRatings();
			}
		}

		System.out.println("Maximum ratings by a single rater:\t" + maxRates);

		String movieID = "1798709";
		int raterCountForMovie = 0;
		HashSet<String> uniqueMovieSet = new HashSet<>();

		for (Rater rater : raterList) {
			if (rater.numRatings() == maxRates) {
				System.out.println(rater.getID());
			}
			if (rater.getItemsRated().contains(movieID)) {
				raterCountForMovie++;
			}
			for (String item : rater.getItemsRated()) {
				uniqueMovieSet.add(item);
			}
		}

		System.out.println("Movie " + movieID + " was rated by:\t" + raterCountForMovie);
		System.out.println("Different movies rated:\t" + uniqueMovieSet.size());

	}

}

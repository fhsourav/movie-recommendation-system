public class DirectorsFilter implements Filter {

	private String[] directorsArray;

	public DirectorsFilter(String directors) {
		directorsArray = directors.split(",");
	}

	@Override
	public boolean satisfies(String id) {
		for (String director : directorsArray) {
			if (MovieDatabase.getDirector(id).contains(director)) {
				return true;
			}
		}
		return false;
	}
}

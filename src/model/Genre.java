package model;

public class Genre {

	private int genre_id;
	private String genre;
	
	public Genre(int genre_id, String genre) {
		this.genre_id = genre_id;
		this.genre = genre;
	}
	
	public Genre(String genre) {
		this.genre = genre;
	}

	public int getGenre_id() {
		return genre_id;
	}
	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	
}

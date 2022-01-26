package model;

public class Release {

	private int release_id;
	private String country;
	private int release_year;
	
	public Release(int release_id, String country, int release_year) {
		this.release_id = release_id;
		this.country = country;
		this.release_year = release_year;
	}

	public Release(String country, int release_year) {
		this.country = country;
		this.release_year = release_year;
	}

	public int getRelease_id() {
		return release_id;
	}

	public void setRelease_id(int release_id) {
		this.release_id = release_id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getRelease_year() {
		return release_year;
	}

	public void setRelease_year(int release_year) {
		this.release_year = release_year;
	}
	
	
}

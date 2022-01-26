package model;
import java.util.ArrayList;

public class Artist {

	private int artist_id;
	private String artist_name;
	private int track_count;
	private int album_count;
	private int age;
	private Image image;
	private String gender;
	
	private ArrayList<Album> albums = new ArrayList<Album>();
	
	public ArrayList<Album> getAlbums() {
		return albums;
	}
	public void setAlbums(ArrayList<Album> albums) {
		this.albums = albums;
	}
	public int getArtist_id() {
		return artist_id;
	}
	public void setArtist_id(int artist_id) {
		this.artist_id = artist_id;
	}
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	public int getTrack_count() {
		return track_count;
	}
	public void setTrack_count(int track_count) {
		this.track_count = track_count;
	}
	public int getAlbum_count() {
		return album_count;
	}
	public void setAlbum_count(int album_count) {
		this.album_count = album_count;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
}

package model;
import java.util.ArrayList;

public class User {

	private String email;
	private String name;
	private String password;
	private String gender;
	private Image image;
	
	private ArrayList<Playlist> playlists;
	public ArrayList<Playlist> getPlaylists() {
		return playlists;
	}
	public void setPlaylists(ArrayList<Playlist> playlists) {
		this.playlists = playlists;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
}

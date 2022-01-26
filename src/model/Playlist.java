package model;
import java.util.ArrayList;

public class Playlist {

	private int playlist_id;
	private String name;
	private int total_track;
	private float total_length;
	private String description;
	
	private ArrayList<Track> tracks = new ArrayList<Track>();

	public int getPlaylist_id() {
		return playlist_id;
	}

	public void setPlaylist_id(int playlist_id) {
		this.playlist_id = playlist_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotal_track() {
		return total_track;
	}

	public void setTotal_track(int total_track) {
		this.total_track = total_track;
	}

	public float getTotal_length() {
		return total_length;
	}

	public void setTotal_length(float total_length) {
		this.total_length = total_length;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Track> getTracks() {
		return tracks;
	}

	public void setTracks(ArrayList<Track> tracks) {
		this.tracks = tracks;
	}
	
	
	
}

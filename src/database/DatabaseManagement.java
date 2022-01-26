package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Album;
import model.Artist;
import model.Genre;
import model.Image;
import model.Label;
import model.Playlist;
import model.Release;
import model.Track;
import model.User;


public class DatabaseManagement {

	private Connection con = DatabaseConnection.getConnection();
	
	public Artist getArtist(int artist_id)
	{
		String query = "Select * from artist where artist_id = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, artist_id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
			  Artist artist = new Artist();
			  artist.setArtist_id(rs.getInt("artist_id"));
			  artist.setArtist_name(rs.getString("artist_name"));
			  artist.setTrack_count(rs.getInt("track_count"));
			  artist.setAlbum_count(rs.getInt("album_count"));
			  artist.setAge(rs.getInt("age"));
			  int imageID = rs.getInt("image_id");
			  if(imageID!=0)
			  {
				  artist.setImage(getImage(imageID));
			  }
			  artist.setGender(rs.getString("gender"));
			  
			  return artist;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private Image getImage(int imageID) {
		// TODO Auto-generated method stub
		Image image = new Image();
		String query = "Select * from image where image_id = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, imageID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				image.setImage_id(imageID);
				image.setImage_name(rs.getString("image_name"));
				image.setImage_type(rs.getString("image_type"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	public Album getAlbum(int album_id)
	{
		String query = "Select * from album where album_id = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, album_id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Album album = new Album();
				album.setAlbum_id(album_id);
				album.setAlbum_name(rs.getString("album_name"));
				album.setArtist(getArtist(rs.getInt("artist_id")));
				album.setGenre(getGenre(rs.getInt("genre_id")));
				album.setLabel(getLabel(rs.getInt("label_id")));
				album.setRelease(getRelease(rs.getInt("release_id")));
				int imageID = rs.getInt("image_id");
				  if(imageID!=0)
				  {
					  album.setImage(getImage(imageID));
				  }
				album.setTracks(getTracksByAlbum(album_id));
				return album;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private ArrayList<Track> getTracksByAlbum(int album_id) {
		ArrayList<Track> tracks = new ArrayList<Track>();
		
		String query = "Select track_id from sungby where album_id = ?";
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1,album_id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				tracks.add(getTrack(rs.getInt("track_id")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tracks;
	}
	public Track getTrack(int track_id)
	{
		String query = "Select * from track where track_id = ?";
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, track_id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Track track = new Track();
				track.setTrack_id(track_id);
				track.setTrack_name(rs.getString("track_name"));
				track.setLength(rs.getFloat("length"));
				track.setGenre(getGenre(rs.getInt("genre_id")));
				track.setLanguage(rs.getString("language"));
				
				return track;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public ArrayList<Album> getTrackAlbums(int track_id) {
		
		String query = " Select album_id from sungby where track_id = "+track_id;
		ArrayList<Album> albums = new ArrayList<Album>();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				albums.add(getAlbum(rs.getInt("album_id")));
			}
			return albums;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return albums;
		
	}
	public ArrayList<Track> getAllTracks()
	{
		String query = "Select track_id from track";
		ArrayList<Track> tracks = new ArrayList<Track>();
		
		
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				tracks.add(getTrack(rs.getInt("track_id")));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tracks;
	}
	
	public ArrayList<Album> getAllAlbums()
	{
		String query = "Select album_id from album";
		ArrayList<Album> albums = new ArrayList<Album>();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				albums.add(getAlbum(rs.getInt("album_id")));
			}
			return albums;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return albums;
	}
	public ArrayList<Artist> getAllArtists()
	{
		String query = "Select artist_id from artist";
		ArrayList<Artist> artists = new ArrayList<Artist>();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				artists.add(getArtist(rs.getInt("artist_id")));
			}
			return artists;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return artists;
	}
	
	public void deleteArtist(int id)
	{
		String query = "delete from artist where artist_id = "+id;
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deleteAlbum(int id)
	{
		String query = "delete from album where album_id = "+id;
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void deleteTrack(int id)
	{
		String query = "delete from track where track_id = "+id;
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean addArtist(Artist artist)
	{
		String query = "Insert into artist(artist_name,track_count, album_count, age, gender, image_id) values (?,?,?,?,?,?)";
		
		if(artist.getImage()==null)
			query = "Insert into artist(artist_name,track_count, album_count, age, gender) values (?,?,?,?,?)";
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1,artist.getArtist_name());
			ps.setInt(2,artist.getTrack_count());
			ps.setInt(3, artist.getAlbum_count());
			ps.setInt(4, artist.getAge());
			ps.setString(5, artist.getGender());
			if(artist.getImage()!=null)
			{
				ps.setInt(6, addImage(artist.getImage()));
			}
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Add Artist: "+e);
			return false;
		}
		
	}
	
	public boolean addUser(User user)
	{
		String query = "Insert into user (email, name, password, gender, profile_image) values (?, ?, ?, ?, ?)";
		if(user.getImage() == null)
			query = "Insert into user (email, name, password, gender) values (?, ?, ?, ?)";
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getGender());
			if(user.getImage()!=null)
			{
				ps.setInt(5, addImage(user.getImage()));
			}
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Adding User: "+e);
			return false;
		}
	}
	
	private int addImage(Image image) {
		// TODO Auto-generated method stub
		String query = "Insert into image (image_name, image_type) values (?, ?)";
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, image.getImage_name());
			ps.setString(2, image.getImage_type());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
			    return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public boolean addAlbum(Album album)
	{
		String query = "Insert into album (album_name,release_id,label_id,genre_id,artist_id,image_id) values(?,?,?,?,?,?)";
		
		if(album.getImage()==null)
			query = "Insert into album (album_name,release_id,label_id,genre_id,artist_id) values(?,?,?,?,?)";
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, album.getAlbum_name());
			ps.setInt(2, addRelease(album.getRelease()));
			ps.setInt(3, addLabel(album.getLabel()));
			ps.setInt(4, addGenre(album.getGenre()));
			ps.setInt(5, album.getArtist().getArtist_id());
			if(album.getImage()!=null)
			{
				ps.setInt(6, addImage(album.getImage()));
			}
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Add Album: "+e);
			return false;
		}
		
	}
	public boolean addTrack(Track track)
	{
		String query = "Insert into track (track_name,length,genre_id,language) values (?,?,?,?)";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, track.getTrack_name());
			ps.setFloat(2, track.getLength());
			ps.setInt(3, addGenre(track.getGenre()));
			ps.setString(4, track.getLanguage());
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Add Track : "+e);
			return false;
		}
		
	}
	
	public boolean updateArtist(Artist artist)
	{
		String query = "update artist set artist_name = ? , track_count = ? , album_count = ?, age = ?, gender = ?, image_id = ? where artist_id =  "+artist.getArtist_id();
		if(artist.getImage()==null)
			query = "update artist set artist_name = ? , track_count = ? , album_count = ?, age = ?, gender = ? where artist_id =  "+artist.getArtist_id();
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1,artist.getArtist_name());
			ps.setInt(2, artist.getTrack_count());
			ps.setInt(3, artist.getAlbum_count());
			ps.setInt(4, artist.getAge());
			ps.setString(5, artist.getGender());
			if(artist.getImage()!=null)
			{
				if(artist.getImage().getImage_id()==0)
					ps.setInt(6, addImage(artist.getImage()));
			}
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Update Artist: "+e);
			return false;
		}
		
	}
	
	public boolean updateAlbum(Album album)
	{
		String query = "update album set album_name = ? , image_id = ? where album_id = "+album.getAlbum_id();
		if(album.getImage()==null)
			query = "update album set album_name = ? where album_id = "+album.getAlbum_id();
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1,album.getAlbum_name());
			if(album.getImage()!=null)
			{
				if(album.getImage().getImage_id()==0)
					ps.setInt(2, addImage(album.getImage()));
			}
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Update Album: "+e);
			return false;
		}
	}
	public boolean updateTrack(Track track)
	{
		String query = "Update track set track_name = ? , length = ?, language = ? where track_id = "+track.getTrack_id();
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, track.getTrack_name());
			ps.setFloat(2,track.getLength());
			ps.setString(3, track.getLanguage());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Update Track: "+e);
			return false;
		}
		
		
	}
	
	public Genre getGenre(int id)
	{
		String query = "Select genre_name from genre where genre_id = "+id;
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				return new Genre(id,rs.getString("genre_name"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public int getGenreID(String genre)
	{
		String query = "Select genre_id from genre where genre_name like ?";
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, genre);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				return rs.getInt("genre_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public Release getRelease(int id)
	{
		String query = "Select * from `release` where release_id = "+id;
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				return new Release(id, rs.getString("release_country"), rs.getInt("release_year"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	public int getReleaseID(String country, int year)
	{
		String query = "Select release_id from `release` where release_country like ? and release_year = ?";
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, country);
			ps.setInt(2, year);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				return rs.getInt("release_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}
	public Label getLabel(int id)
	{
		String query = "Select label_name from label where lablel_id = "+id;
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				return new Label(id, rs.getString("label_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public int getLabelID(String name)
	{
		String query = "Select lablel_id from label where label_name like ?";
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				return rs.getInt("lablel_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public int addGenre(Genre genre)
	{
		int id = getGenreID(genre.getGenre());
		if(id == 0)
		{
			String query = "Insert into genre (genre_name) values (?)";
			
			PreparedStatement ps;
			try {
				ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, genre.getGenre());
				
				ps.executeUpdate();
				
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
				    return rs.getInt(1);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return id;
	}
	public int addRelease(Release release)
	{
		int id = getReleaseID(release.getCountry(), release.getRelease_year());
		if(id == 0)
		{
			
			String query = "Insert into `release` (release_country, release_year) values (?,?)";
			
			PreparedStatement ps;
			
			try {
				ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, release.getCountry());
				ps.setInt(2, release.getRelease_year());
				
				ps.executeUpdate();
				
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
				    id = rs.getInt(1);
				    System.out.println("Returning ID: "+id);
				    return id;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Add Release: "+e);
			}
			

		}
		return id;
	}
	public int addLabel(Label label)
	{
		int id = getLabelID(label.getLabel_name());
		if(id == 0)
		{
			String query = "Insert into label(label_name) values (?)";
			
			PreparedStatement ps;
			try {
				ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, label.getLabel_name());
				
				ps.executeUpdate();
				
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
				    return rs.getInt(1);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return id;
	}
	
	public User userLogin(String email, String password)
	{
		String query = "SELECT * FROM user WHERE email = ? AND password = ?";
		PreparedStatement ps;
		   try {
			ps = con.prepareStatement(query);
			ps.setString(1,  email);
			ps.setString(2,  password);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				User user = new User();
				user.setEmail(email);
				user.setPassword(password);
				user.setName(rs.getString("name"));
				user.setGender(rs.getString("gender"));
				int imageID = rs.getInt("profile_image");
				  if(imageID!=0)
				  {
					  user.setImage(getImage(imageID));
				  }
				user.setPlaylists(getPlaylists(email));
				return user;
			}
				
			
		} catch (SQLException e) {
			
			System.out.println("Login Error: "+e);
			
		}
		return null;
	}
	
	public ArrayList<Track> getPlaylistTracks(int playlist_id)
	{
		ArrayList<Track> tracks = new ArrayList<Track>();
		String query = "Select sb.track_id from sungby sb where sb.album_id in (Select album_id from includes i where i.playlist_id = ?)";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, playlist_id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				tracks.add(getTrack(rs.getInt("track_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return tracks;
	}
	
	public ArrayList<Playlist> getPlaylists(String email)
	{
		ArrayList<Playlist> playlists = new ArrayList<Playlist>();
		String query = "Select * from playlist where user like ? ";
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Playlist playlist = new Playlist();
				playlist.setName(rs.getString("playlist_name"));
				playlist.setPlaylist_id(rs.getInt("playlist_id"));
				playlist.setTotal_length(rs.getFloat("total_length"));
				playlist.setTotal_track(rs.getInt("total_track"));
				playlist.setDescription(rs.getString("description"));
				playlist.setTracks(getPlaylistTracks(playlist.getPlaylist_id()));
				
				playlists.add(playlist);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return playlists;
	}
	public boolean addTrackToAlbum(int track_id, int album_id) {
		
		String query = "Insert into sungby values(?,?)";
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, track_id);
			ps.setInt(2, album_id);
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("AddTrackToAlbum: "+e);
			return false;
		}
		
		
	}
	
	public boolean addPlaylist(Playlist playlist,  User user)
	{
		String query = "Insert into playlist (playlist_name, total_track, total_length, description, user) values (?,?,?,?,?)";
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, playlist.getName());
			ps.setInt(2, playlist.getTotal_track());
			ps.setFloat(3, playlist.getTotal_length());
			ps.setString(4, playlist.getDescription());
			ps.setString(5, user.getEmail());
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Add playlist: "+e);
			return false;
		}
		
	}
	
	public boolean updatePlaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		String query = "Update playlist Set playlist_name = ?, total_track = ?, total_length = ?, description = ? where playlist_id = "+playlist.getPlaylist_id();
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, playlist.getName());
			ps.setInt(2, playlist.getTotal_track());
			ps.setFloat(3,playlist.getTotal_length());
			ps.setString(4, playlist.getDescription());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Update Playlist: "+e);
			return false;
		}
	}
	public boolean deletePlaylist(int playlist_id)
	{
		String query = "Delete from playlist where playlist_id = "+playlist_id;
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Delete Playlist: "+e);
			return false;
		}
		
		
	}
	public void deleteTrackFromPlaylist(int track_id, int playlist_id) {
		String query = "Delete from includes where playlist_id = ? and track_id = ?";
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, playlist_id);
			ps.setInt(2, track_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean addTrackToPlaylist(int track_id, int playlist_id, int album_id) {
		
		String query = "Insert into includes values(?,?,?)";
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, playlist_id);
			ps.setInt(2, track_id);
			ps.setInt(3, album_id);
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Add track to Playlist: "+e);
			return false;
		}
		
		
	}
	public void addReview(String email, int album_id, int track_id, int rating, String comment) {
		
		String query = "Insert into review values(?,?,?,?,?)";
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setInt(2, album_id);
			ps.setInt(3, track_id);
			ps.setInt(4, rating);
			ps.setString(5, comment);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

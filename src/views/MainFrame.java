package views;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import database.DatabaseManagement;
import model.Album;
import model.Artist;
import model.Playlist;
import model.Track;
import model.User;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame implements ActionListener {

	
	private JTabbedPane tabbedPane;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPanel signInPane;
	private JButton loginBtn;
	private JComboBox roleBox;
	private JButton signUpBtn;
	private JPanel trackPane;
	private JButton addToPlaylistBtn;
	private JButton addTrackBtn;
	private JButton updateTrackBtn;
	private JButton deleteTrackBtn;
	private JPanel albumPane;
	private JButton viewAlbumBtn;
	private JButton addAlbumBtn;
	private JButton updateAlbumBtn;
	private JButton deleteAlbumBtn;
	private JPanel artistPane;
	private JButton addArtistBtn;
	private JButton updateArtistBtn;
	private JButton deleteArtistBtn;
	private JPanel playlistPane;
	private JButton viewPlaylistBtn;
	private JButton createPlaylistBtn;
	private JButton editPlaylistBtn;
	private JButton deletePlaylistBtn;
	private boolean admin = true;
	private DatabaseManagement db = new DatabaseManagement();
	private ArrayList<Track> trackList;
	private ArrayList<Album> albumList;
	private ArrayList<Artist> artistList;
	private JTable trackTable;
	private User user;
	private JTable albumTable;
	private JTable artistTable;
	private JTable playlistTable;
	private JButton btnAddTrack;
	private ArrayList<Playlist> playlists;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
		
		setBounds(100, 100, 619, 392);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		 tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 605, 355);
		getContentPane().add(tabbedPane);
		
		signInPane = new JPanel();
		tabbedPane.addTab("Sign In", null, signInPane, null);
		signInPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Role");
		lblNewLabel.setBounds(193, 58, 45, 13);
		signInPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(193, 98, 56, 13);
		signInPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(193, 148, 45, 13);
		signInPane.add(lblNewLabel_2);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(286, 95, 112, 19);
		signInPane.add(emailField);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(286, 145, 112, 19);
		signInPane.add(passwordField);
		
		loginBtn = new JButton("Login");
		loginBtn.addActionListener(this);
		loginBtn.setBounds(198, 185, 184, 21);
		signInPane.add(loginBtn);
		
		roleBox = new JComboBox();
		roleBox.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Customer"}));
		roleBox.setBounds(286, 54, 112, 21);
		signInPane.add(roleBox);
		
		signUpBtn = new JButton("Sign Up");
		signUpBtn.addActionListener(this);
		signUpBtn.setBounds(255, 228, 85, 21);
		signInPane.add(signUpBtn);
		
		trackPane = new JPanel();
		tabbedPane.addTab("Tracks", null, trackPane, null);
		tabbedPane.setEnabledAt(1, false);
		trackPane.setLayout(null);
		
		
		
		
		
		
		 addToPlaylistBtn = new JButton("Add to Playlist");
		 addToPlaylistBtn.addActionListener(this);
		addToPlaylistBtn.setBounds(189, 275, 250, 21);
		trackPane.add(addToPlaylistBtn);
		
		 addTrackBtn = new JButton("Add Track");
		
		addTrackBtn.setBounds(150, 275, 98, 21);
		addTrackBtn.addActionListener(this);
		trackPane.add(addTrackBtn);
		
		updateTrackBtn = new JButton("Update Track");
		updateTrackBtn.setBounds(258, 275, 98, 21);
		updateTrackBtn.addActionListener(this);
		trackPane.add(updateTrackBtn);
		
		 deleteTrackBtn = new JButton("Delete Track");
		deleteTrackBtn.setBounds(366, 275, 98, 21);
		deleteTrackBtn.addActionListener(this);
		trackPane.add(deleteTrackBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 580, 229);
		trackPane.add(scrollPane);
		
		trackTable = new JTable();
		displayTracks();
		scrollPane.setViewportView(trackTable);
		
		
		
		 albumPane = new JPanel();
		tabbedPane.addTab("Albums", null, albumPane, null);
		tabbedPane.setEnabledAt(2, false);
		albumPane.setLayout(null);
		
		 viewAlbumBtn = new JButton("View Album");
		 viewAlbumBtn.addActionListener(this);
		viewAlbumBtn.setBounds(179, 276, 250, 21);
		albumPane.add(viewAlbumBtn);
		
		 addAlbumBtn = new JButton("Add Album");
		addAlbumBtn.setBounds(90, 276, 98, 21);
		addAlbumBtn.addActionListener(this);
		albumPane.add(addAlbumBtn);
		
		 updateAlbumBtn = new JButton("Update Album");
		updateAlbumBtn.setBounds(198, 276, 98, 21);
		updateAlbumBtn.addActionListener(this);
		albumPane.add(updateAlbumBtn);
		
		 deleteAlbumBtn = new JButton("Delete Album");
		deleteAlbumBtn.setBounds(306, 276, 98, 21);
		deleteAlbumBtn.addActionListener(this);
		albumPane.add(deleteAlbumBtn);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 10, 580, 243);
		albumPane.add(scrollPane_1);
		
		albumTable = new JTable();
		displayAlbums();
		scrollPane_1.setViewportView(albumTable);
		
		 btnAddTrack = new JButton("Add Track");
		btnAddTrack.setBounds(414, 276, 98, 21);
		btnAddTrack.addActionListener(this);
		albumPane.add(btnAddTrack);
		
		 artistPane = new JPanel();
		tabbedPane.addTab("Artists", null, artistPane, null);
		tabbedPane.setEnabledAt(3, false);
		artistPane.setLayout(null);
		
		 addArtistBtn = new JButton("Add Artist");
		addArtistBtn.setBounds(153, 276, 98, 21);
		addArtistBtn.addActionListener(this);
		artistPane.add(addArtistBtn);
		
		 updateArtistBtn = new JButton("Update Artist");
		updateArtistBtn.setBounds(261, 276, 98, 21);
		updateArtistBtn.addActionListener(this);
		artistPane.add(updateArtistBtn);
		
		 deleteArtistBtn = new JButton("Delete Artist");
		deleteArtistBtn.setBounds(369, 276, 98, 21);
		deleteArtistBtn.addActionListener(this);
		artistPane.add(deleteArtistBtn);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 10, 580, 242);
		artistPane.add(scrollPane_2);
		
		artistTable = new JTable();
		displayArtists();
		scrollPane_2.setViewportView(artistTable);
		
		 playlistPane = new JPanel();
		tabbedPane.addTab("Playlists", null, playlistPane, null);
		tabbedPane.setEnabledAt(4, false);
		playlistPane.setLayout(null);
		
		 viewPlaylistBtn = new JButton("View Playlist Tracks");
		 viewPlaylistBtn.addActionListener(this);
		viewPlaylistBtn.setBounds(179, 260, 250, 21);
		playlistPane.add(viewPlaylistBtn);
		
		 createPlaylistBtn = new JButton("Create Playlist");
		 createPlaylistBtn.addActionListener(this);
		createPlaylistBtn.setBounds(149, 284, 98, 21);
		playlistPane.add(createPlaylistBtn);
		
		 editPlaylistBtn = new JButton("Edit Playlist");
		 editPlaylistBtn.addActionListener(this);
		editPlaylistBtn.setBounds(257, 284, 98, 21);
		playlistPane.add(editPlaylistBtn);
		
		 deletePlaylistBtn = new JButton("Delete Playlist");
		 deletePlaylistBtn.addActionListener(this);
		deletePlaylistBtn.setBounds(365, 284, 98, 21);
		playlistPane.add(deletePlaylistBtn);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 10, 580, 223);
		playlistPane.add(scrollPane_3);
		
		playlistTable = new JTable();
		scrollPane_3.setViewportView(playlistTable);
		
		setVisible(true);
	}
	
	private void turnOnAdminMode()
	{
		//Enable track tab and its attributes
		tabbedPane.setEnabledAt(1, true);
		
		
		
		addToPlaylistBtn.setVisible(false);
		 addTrackBtn.setVisible(true);
		 updateTrackBtn.setVisible(true);
		 deleteTrackBtn.setVisible(true);
		 
		//Enable Album tab and its attributes 
		tabbedPane.setEnabledAt(2, true);
		
		viewAlbumBtn.setVisible(false);
		addAlbumBtn.setVisible(true);
		updateAlbumBtn.setVisible(true);
		deleteAlbumBtn.setVisible(true);
		btnAddTrack.setVisible(true);	
		
		//Enable Artist tab and its attributes
		tabbedPane.setEnabledAt(3, true);
		
		
		addArtistBtn.setVisible(true);
		updateArtistBtn.setVisible(true);
		deleteArtistBtn.setVisible(true);
		 
		//disable Playlist tab and its attribute
		tabbedPane.setEnabledAt(4, false);
		tabbedPane.setSelectedIndex(1);
	}
	
	private void turnOnCustomerMode()
	{
		//Enable track tab and its attributes
		tabbedPane.setEnabledAt(1, true);
		
		addToPlaylistBtn.setVisible(true);
		addTrackBtn.setVisible(false);
		updateTrackBtn.setVisible(false);
		deleteTrackBtn.setVisible(false);
		 
		//Enable Album tab and its attributes 
		tabbedPane.setEnabledAt(2, true);
		
		viewAlbumBtn.setVisible(true);
		addAlbumBtn.setVisible(false);
		updateAlbumBtn.setVisible(false);
		deleteAlbumBtn.setVisible(false);
		btnAddTrack.setVisible(false);
		
		//Enable Artist tab and its attributes
		tabbedPane.setEnabledAt(3, true);
		
		
		addArtistBtn.setVisible(false);
		updateArtistBtn.setVisible(false);
		deleteArtistBtn.setVisible(false);
		
		//Enable Playlist tab and its attribute
		tabbedPane.setEnabledAt(4, true);
		
		tabbedPane.setSelectedIndex(1);
	}
	
	public void displayTracks()
	{
		trackList = db.getAllTracks();
		
		String[] columns = new String[] {"ID","Track Name","Length","Genre","Language","Album"};
		Object[][] data = new Object[trackList.size()][7];
		for(int i=0; i<trackList.size(); i++)
		{
			Track track = trackList.get(i);
			data[i][0] = track.getTrack_id();
			data[i][1] = track.getTrack_name();
			data[i][2] = track.getLength();
			data[i][3] = track.getGenre().getGenre();
			data[i][4] = track.getLanguage();
			ArrayList<Album> albums = db.getTrackAlbums(track.getTrack_id());
			if(!albums.isEmpty())
				data[i][5] = albums.get(0).getAlbum_name();
		}

		DefaultTableModel dataModel = new DefaultTableModel(data, columns);		
		trackTable.setModel(dataModel);
		
	}
	public void displayAlbums()
	{
		albumList = db.getAllAlbums();
		
		String[] columns = new String[] {"ID","Album Name","Genre","Label","Release Year","Release Country", "Artist", "Image"};
		Object[][] data = new Object[albumList.size()][9];
		for(int i=0; i<albumList.size(); i++)
		{
			Album album = albumList.get(i);
			data[i][0] = album.getAlbum_id();
			data[i][1] = album.getAlbum_name();
			data[i][2] = album.getGenre().getGenre();
			data[i][3] = album.getLabel().getLabel_name();
			data[i][4] = album.getRelease().getRelease_year();
			data[i][5] = album.getRelease().getCountry();
			data[i][6] = album.getArtist().getArtist_name();
			
			if(album.getImage()!=null)
				data[i][7] = album.getImage().getImage_name() + "."+album.getImage().getImage_type();
		}

		DefaultTableModel dataModel = new DefaultTableModel(data, columns);		
		albumTable.setModel(dataModel);
		
	}
	
	public void displayArtists()
	{
		artistList = db.getAllArtists();
		
		String[] columns = new String[] {"ID","Artist Name","Total Track","Total Album","Age","Gender", "Image"};
		Object[][] data = new Object[artistList.size()][8];
		for(int i=0; i<artistList.size(); i++)
		{
			Artist artist = artistList.get(i);
			data[i][0] = artist.getArtist_id();
			data[i][1] = artist.getArtist_name();
			data[i][2] = artist.getTrack_count();
			data[i][3] = artist.getAlbum_count();
			data[i][4] = artist.getAge();
			data[i][5] = artist.getGender();
			
			
			if(artist.getImage()!=null)
				data[i][6] = artist.getImage().getImage_name() + "."+artist.getImage().getImage_type();
		}

		DefaultTableModel dataModel = new DefaultTableModel(data, columns);		
		artistTable.setModel(dataModel);
		
	}
	
	public void displayPlaylist()
	{
		playlists = db.getPlaylists(user.getEmail());
		
		String[] columns = new String[] {"ID","Playlist Name","Total Track","Total Length","Description"};
		Object[][] data = new Object[playlists.size()][6];
		for(int i=0; i<playlists.size(); i++)
		{
			Playlist playlist = playlists.get(i);
			data[i][0] = playlist.getPlaylist_id();
			data[i][1] = playlist.getName();
			data[i][2] = playlist.getTotal_track();
			data[i][3] = playlist.getTotal_length();
			data[i][4] = playlist.getDescription();
		}

		DefaultTableModel dataModel = new DefaultTableModel(data, columns);		
		playlistTable.setModel(dataModel);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==signUpBtn)
			new SignUpFrame(this);
		if(e.getSource()==loginBtn)
		{
			if(roleBox.getSelectedItem().equals("Admin"))
			{
				if(emailField.getText().equals("admin") && passwordField.getText().equals("admin12"))
				{
					admin = true;
					turnOnAdminMode();
				}
				else
					
					
					JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			else
			{
				user = db.userLogin(emailField.getText(), passwordField.getText());
				if(user!=null)
				{
					admin = false;
					displayPlaylist();
					turnOnCustomerMode();
				}
				else
					JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
			}
				
		}
		
		if(e.getSource()==addTrackBtn)
		{
			new AddTrackFrame(this);
		}
		if(e.getSource()==deleteTrackBtn)
		{
			int[] rows = trackTable.getSelectedRows();
			if(rows.length==0)
			{
				JOptionPane.showMessageDialog(this, "Select one or more rows", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				for(int i = 0; i<rows.length; i++)
				{
					
					db.deleteTrack(trackList.get(rows[i]).getTrack_id());
				}
				JOptionPane.showMessageDialog(this, rows.length+" rows deleted successfully.","Success",JOptionPane.INFORMATION_MESSAGE);
				displayTracks();
				
				
			}
		}
		
		if(e.getSource() == updateTrackBtn)
		{
			if(trackTable.getSelectedRowCount()==1)
			{
				new AddTrackFrame(this,trackList.get(trackTable.getSelectedRow()));
			}
			else
				JOptionPane.showMessageDialog(this, "Select single row", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		if(e.getSource()==addArtistBtn)
			new AddArtistFrame(this);
		
		if(e.getSource()==updateArtistBtn)
		{
			if(artistTable.getSelectedRowCount()==1)
			{
				new AddArtistFrame(this,artistList.get(artistTable.getSelectedRow()));
			}
			else
				JOptionPane.showMessageDialog(this, "Select single row", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		if(e.getSource() == deleteArtistBtn)
		{
			int[] rows = artistTable.getSelectedRows();
			if(rows.length==0)
			{
				JOptionPane.showMessageDialog(this, "Select one or more rows", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				for(int i = 0; i<rows.length; i++)
				{
					
					db.deleteArtist(artistList.get(rows[i]).getArtist_id());
				}
				JOptionPane.showMessageDialog(this, rows.length+" rows deleted successfully.","Success",JOptionPane.INFORMATION_MESSAGE);
				displayArtists();
				
				
			}
		}
		
		if(e.getSource()==addAlbumBtn)
			new AlbumFrame(this);
		if(e.getSource()==updateAlbumBtn)
		{
			if(albumTable.getSelectedRowCount()==1)
			{
				new AlbumFrame(this,albumList.get(albumTable.getSelectedRow()));
			}
			else
				JOptionPane.showMessageDialog(this, "Select single row", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if(e.getSource()==deleteAlbumBtn)
		{
			int[] rows = albumTable.getSelectedRows();
			if(rows.length==0)
			{
				JOptionPane.showMessageDialog(this, "Select one or more rows", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				for(int i = 0; i<rows.length; i++)
				{
					
					db.deleteAlbum(albumList.get(rows[i]).getAlbum_id());
				}
				JOptionPane.showMessageDialog(this, rows.length+" rows deleted successfully.","Success",JOptionPane.INFORMATION_MESSAGE);
				displayAlbums();
				displayTracks();
				
				
			}
		}
		if(e.getSource()==btnAddTrack)
		{
			if(albumTable.getSelectedRowCount()==1)
			{
				 int track_id= Integer.parseInt(JOptionPane.showInputDialog(this,"Enter Track ID"));
				 int album_id = albumList.get(albumTable.getSelectedRow()).getAlbum_id();
				 if(db.addTrackToAlbum(track_id,album_id))
				 {
					 displayTracks();
					 JOptionPane.showMessageDialog(this,"Track is Successfully Added.","Alert",JOptionPane.WARNING_MESSAGE);
					 
				 }
				 else
						JOptionPane.showMessageDialog(this, "Invalid Track ID", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(this, "Select single row", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if(e.getSource()==addToPlaylistBtn)
		{
			if(trackTable.getSelectedRowCount()==1)
			{	 
				 Track track = trackList.get(trackTable.getSelectedRow());
				 ArrayList<Album> albums = db.getTrackAlbums(track.getTrack_id());
				 if(albums.isEmpty())
					 JOptionPane.showMessageDialog(this, "This song is not available right now", "Sorry", JOptionPane.INFORMATION_MESSAGE);
				 else
				 {
					int playlist_id =  Integer.parseInt(JOptionPane.showInputDialog(this,"Enter playlist ID"));
					int album_id = albums.get(0).getAlbum_id();
					int track_id = track.getTrack_id();
					
					if(db.addTrackToPlaylist(track_id, playlist_id, album_id))
					{
						JOptionPane.showMessageDialog(this,"Track is Successfully Added.","Alert",JOptionPane.WARNING_MESSAGE);
						 displayPlaylist();
					}
					else
						JOptionPane.showMessageDialog(this, "Invalid Playlist ID", "Error", JOptionPane.ERROR_MESSAGE);
				 }
			}
			else
				JOptionPane.showMessageDialog(this, "Select single row", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if(e.getSource()==createPlaylistBtn)
		{
			new PlaylistForm(this,user);
		}
		
		if(e.getSource()==editPlaylistBtn)
		{
			if(playlistTable.getSelectedRowCount()==1)
			{
				new PlaylistForm(this,playlists.get(playlistTable.getSelectedRow()));
			}
			else
				JOptionPane.showMessageDialog(this, "Select single row", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if(e.getSource()==deletePlaylistBtn)
		{
			int[] rows = playlistTable.getSelectedRows();
			if(rows.length==0)
			{
				JOptionPane.showMessageDialog(this, "Select one or more rows", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				for(int i = 0; i<rows.length; i++)
				{
					
					db.deletePlaylist(playlists.get(rows[i]).getPlaylist_id());
				}
				JOptionPane.showMessageDialog(this, rows.length+" rows deleted successfully.","Success",JOptionPane.INFORMATION_MESSAGE);
				displayPlaylist();
				
				
			}
		}
		
		if(e.getSource()==viewAlbumBtn)
		{

			if(albumTable.getSelectedRowCount()==1)
			{
				 
				 
				 new TrackListFrame(this,albumList.get(albumTable.getSelectedRow()));
			}
			else
				JOptionPane.showMessageDialog(this, "Select single row", "Error", JOptionPane.ERROR_MESSAGE);
		
		}
		
		if(e.getSource()==viewPlaylistBtn)
		{
			if(playlistTable.getSelectedRowCount()==1)
			{
				new TrackListFrame(this,playlists.get(playlistTable.getSelectedRow()));
			}
			else
				JOptionPane.showMessageDialog(this, "Select single row", "Error", JOptionPane.ERROR_MESSAGE);
		}
			
		
		
	}

	public User getUser() {
		return user;
	}
}

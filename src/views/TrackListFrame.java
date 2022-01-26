package views;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.DatabaseManagement;
import model.Album;
import model.Playlist;
import model.Track;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TrackListFrame extends JFrame {

	private MainFrame mf;
	private JPanel contentPane;
	private JTable trackTable;
	private JButton addPlaylistBtn;
	private Playlist playlist;
	private Album album;
	ArrayList<Track> trackList;
	private DatabaseManagement db = new DatabaseManagement();
	private JButton rateBtn;
	
	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public TrackListFrame(MainFrame mf, Playlist playlist) {
		this.mf = mf;
		this.playlist = playlist;
		this.trackList = playlist.getTracks();
		intialize();
		addPlaylistBtn.setText("Remove from Playlist");
		
		
		
		setVisible(true);
	}
	public TrackListFrame(MainFrame mf, Album album) {
		this.mf = mf;
		this.album = album;
		this.trackList = album.getTracks();
		
		intialize();
		rateBtn = new JButton("Rate");
		rateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(trackTable.getSelectedRowCount()==1)
				{
					int rating= Integer.parseInt(JOptionPane.showInputDialog(mf,"Rating (1 - 5)"));
					String comment = JOptionPane.showInputDialog(mf,"Comment: ");
					int track_id = trackList.get(trackTable.getSelectedRow()).getTrack_id();
					int album_id = album.getAlbum_id();
					String email = mf.getUser().getEmail();
					db.addReview(email, album_id, track_id, rating,comment);
					JOptionPane.showMessageDialog(mf, "Thank you for review", "Thanks", JOptionPane.INFORMATION_MESSAGE);
				}
				
				else
					JOptionPane.showMessageDialog(mf, "Select single row", "Error", JOptionPane.ERROR_MESSAGE);
				
				
			}
		});
		rateBtn.setBounds(478, 256, 85, 21);
		contentPane.add(rateBtn);
		setVisible(true);
	}
	
	private void intialize()
	{

		
		setBounds(100, 100, 587, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 553, 226);
		contentPane.add(scrollPane);
		
		trackTable = new JTable();
		displayTracks();
		scrollPane.setViewportView(trackTable);
		
		 addPlaylistBtn = new JButton("Add to PlayList");
		 addPlaylistBtn.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		//Remove from Playlist
		 		if(playlist != null)
		 		{

					int[] rows = trackTable.getSelectedRows();
					if(rows.length==0)
					{
						JOptionPane.showMessageDialog(mf, "Select one or more rows", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						for(int i = 0; i<rows.length; i++)
						{
							
							db.deleteTrackFromPlaylist(trackList.get(rows[i]).getTrack_id(),playlist.getPlaylist_id());
						}
						JOptionPane.showMessageDialog(mf, rows.length+" rows deleted successfully.","Success",JOptionPane.INFORMATION_MESSAGE);
						mf.displayPlaylist();
						setVisible(false);
						
						
					}
				
		 		}
		 		//Add to playlist
		 		else
		 		{
		 			if(trackTable.getSelectedRowCount()==1)
					{
		 			
						 int playlist_id= Integer.parseInt(JOptionPane.showInputDialog(mf,"Enter Playlist ID"));
						 int track_id = trackList.get(trackTable.getSelectedRow()).getTrack_id();
						 if(db.addTrackToPlaylist(track_id,playlist_id,album.getAlbum_id()))
						 {
							 
							 JOptionPane.showMessageDialog(mf,"Track is Successfully Added.","Alert",JOptionPane.WARNING_MESSAGE);
							 mf.displayPlaylist();
							 setVisible(false);
						 }
						 else
								JOptionPane.showMessageDialog(mf, "Invalid Playlist ID", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(mf, "Select single row", "Error", JOptionPane.ERROR_MESSAGE);
		 		}
		 		
		 	}
		 });
		addPlaylistBtn.setBounds(141, 256, 287, 21);
		contentPane.add(addPlaylistBtn);
	
	}
	
	public void displayTracks()
	{
		String[] columns = new String[] {"ID","Track Name","Length","Genre","Language"};
		Object[][] data = new Object[trackList.size()][6];
		for(int i=0; i<trackList.size(); i++)
		{
			Track track = trackList.get(i);
			data[i][0] = track.getTrack_id();
			data[i][1] = track.getTrack_name();
			data[i][2] = track.getLength();
			data[i][3] = track.getGenre().getGenre();
			data[i][4] = track.getLanguage();
		}

		DefaultTableModel dataModel = new DefaultTableModel(data, columns);		
		trackTable.setModel(dataModel);
		
	}
}

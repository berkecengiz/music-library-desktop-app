package views;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.DatabaseManagement;
import model.Playlist;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Font;

public class PlaylistForm extends JFrame {

	private MainFrame mf;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField tracksField;
	private JTextField lengthField;
	private JButton createPlayListBtn;
	private JTextArea descriptionField;
	private DatabaseManagement db = new DatabaseManagement();
	private int id = 0;
	private User user;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public PlaylistForm(MainFrame mf, User user) {
		this.mf = mf;
		this.user = user;
		initialize();
		tracksField.setText("0");
		lengthField.setText("0.0");
		tracksField.setEditable(false);
		lengthField.setEditable(false);
		setVisible(true);
		
	}
	
	public PlaylistForm(MainFrame mf, Playlist playlist) {
		this.mf = mf;
		
		this.id = playlist.getPlaylist_id();
		initialize();
		nameField.setText(playlist.getName());
		tracksField.setText(playlist.getTotal_track()+"");
		lengthField.setText(playlist.getTotal_length()+"");
		descriptionField.setText(playlist.getDescription());
		createPlayListBtn.setText("Update Playlist");
		setVisible(true);
		
	}
	
	private void initialize()
	{
		setBounds(100, 100, 466, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPlaylistName = new JLabel("Playlist Name");
		lblPlaylistName.setBounds(112, 77, 68, 13);
		contentPane.add(lblPlaylistName);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(211, 74, 127, 19);
		contentPane.add(nameField);
		
		JLabel lblTotalTracks = new JLabel("Total Tracks");
		lblTotalTracks.setBounds(112, 110, 68, 13);
		contentPane.add(lblTotalTracks);
		
		tracksField = new JTextField();
		tracksField.setColumns(10);
		tracksField.setBounds(211, 107, 127, 19);
		contentPane.add(tracksField);
		
		JLabel lblTotalLength = new JLabel("Total Length");
		lblTotalLength.setBounds(112, 144, 68, 13);
		contentPane.add(lblTotalLength);
		
		lengthField = new JTextField();
		lengthField.setColumns(10);
		lengthField.setBounds(211, 141, 127, 19);
		contentPane.add(lengthField);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(112, 176, 68, 13);
		contentPane.add(lblDescription);
		 
		 scrollPane = new JScrollPane();
		 scrollPane.setBounds(211, 170, 127, 50);
		 contentPane.add(scrollPane);
		
		 descriptionField = new JTextArea();
		 descriptionField.setFont(new Font("Monospaced", Font.PLAIN, 10));
		 scrollPane.setViewportView(descriptionField);
		
		createPlayListBtn = new JButton("Create Playlist");
		createPlayListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Playlist playlist = new Playlist();
				playlist.setName(nameField.getText());
				playlist.setPlaylist_id(id);
				playlist.setDescription(descriptionField.getText());
				playlist.setTotal_length( Float.parseFloat(lengthField.getText()));
				playlist.setTotal_track( Integer.parseInt(tracksField.getText()));
				

				if(id!=0)
		 		{
		 			if(db.updatePlaylist(playlist))
					{
						JOptionPane.showMessageDialog(mf, "Updated successfully","Successful",JOptionPane.INFORMATION_MESSAGE);
						mf.displayPlaylist();
						setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(mf, "Incomplete or invalid info.", "Error", JOptionPane.ERROR_MESSAGE);
					}
		 		}
		 		else
		 		{
		 			if(db.addPlaylist(playlist,user))
					{
						JOptionPane.showMessageDialog(mf, "Playlist Created successfully","Successful",JOptionPane.INFORMATION_MESSAGE);
						mf.displayPlaylist();
						setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(mf, "Incomplete or invalid info.", "Error", JOptionPane.ERROR_MESSAGE);
					}
		 		}
				
			}
		});
		createPlayListBtn.setBounds(112, 243, 231, 19);
		contentPane.add(createPlayListBtn);
	}
}

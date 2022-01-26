package views;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.DatabaseManagement;
import model.Album;
import model.Genre;
import model.Image;
import model.Label;
import model.Release;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AlbumFrame extends JFrame {

	private MainFrame mf;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField genreField;
	private JTextField labelField;
	private JTextField countryField;
	private JTextField yearField;
	private JTextField artistField;
	private JTextField imageField;
	private JComboBox imageComboBox;
	private JButton createAlbumBtn;
	private DatabaseManagement db = new DatabaseManagement();
	private int id = 0;
	

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public AlbumFrame(MainFrame mf) {
		this.mf = mf;
		initialize();
		setVisible(true);
	}

	public AlbumFrame(MainFrame mf, Album album) {
		this.mf = mf;
		this.id = album.getAlbum_id();
		initialize();
		nameField.setText(album.getAlbum_name());
		genreField.setText(album.getGenre().getGenre());
		labelField.setText(album.getLabel().getLabel_name());
		countryField.setText(album.getRelease().getCountry());
		yearField.setText(album.getRelease().getRelease_year()+"");
		artistField.setText(album.getArtist().getArtist_id()+"");
		if(album.getImage()!=null)
		{
			imageField.setText(album.getImage().getImage_name());
			imageComboBox.setSelectedItem(album.getImage().getImage_type());
		}
		genreField.setEditable(false);
		labelField.setEditable(false);
		countryField.setEditable(false);
		yearField.setEditable(false);
		artistField.setEditable(false);
		createAlbumBtn.setText("Update Album");
		setVisible(true);
	}
	private void initialize()
	{
		
		setBounds(100, 100, 483, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAlbumName = new JLabel("Album Name");
		lblAlbumName.setBounds(113, 56, 68, 13);
		contentPane.add(lblAlbumName);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(212, 53, 127, 19);
		contentPane.add(nameField);
		
		JLabel lblGenre_1 = new JLabel("Genre");
		lblGenre_1.setBounds(113, 95, 68, 13);
		contentPane.add(lblGenre_1);
		
		genreField = new JTextField();
		genreField.setColumns(10);
		genreField.setBounds(212, 92, 127, 19);
		contentPane.add(genreField);
		
		JLabel lblLabelName = new JLabel("Label");
		lblLabelName.setBounds(113, 132, 68, 13);
		contentPane.add(lblLabelName);
		
		labelField = new JTextField();
		labelField.setColumns(10);
		labelField.setBounds(212, 129, 127, 19);
		contentPane.add(labelField);
		
		JLabel lblReleaseCountry = new JLabel("Release Country");
		lblReleaseCountry.setBounds(113, 170, 74, 13);
		contentPane.add(lblReleaseCountry);
		
		countryField = new JTextField();
		countryField.setColumns(10);
		countryField.setBounds(212, 167, 127, 19);
		contentPane.add(countryField);
		
		JLabel lblYear = new JLabel("Release Year");
		lblYear.setBounds(113, 205, 74, 13);
		contentPane.add(lblYear);
		
		yearField = new JTextField();
		yearField.setColumns(10);
		yearField.setBounds(212, 202, 127, 19);
		contentPane.add(yearField);
		
		JLabel lblArtistId = new JLabel("Artist ID");
		lblArtistId.setBounds(113, 242, 74, 13);
		contentPane.add(lblArtistId);
		
		artistField = new JTextField();
		artistField.setColumns(10);
		artistField.setBounds(212, 239, 127, 19);
		contentPane.add(artistField);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Image");
		lblNewLabel_1_1_1.setBounds(113, 282, 83, 13);
		contentPane.add(lblNewLabel_1_1_1);
		
		imageField = new JTextField();
		imageField.setColumns(10);
		imageField.setBounds(212, 279, 88, 19);
		contentPane.add(imageField);
		
		imageComboBox = new JComboBox();
		imageComboBox.setModel(new DefaultComboBoxModel(new String[] {"JPEG", "PNG", "GIF", "TIFF", "RAW", "BMP", "SVG"}));
		imageComboBox.setBounds(310, 278, 60, 21);
		contentPane.add(imageComboBox);
		
		createAlbumBtn = new JButton("Create Album");
		createAlbumBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Album album = new Album();
				album.setAlbum_id(id);
				album.setAlbum_name(nameField.getText());
				album.setArtist(db.getArtist(Integer.parseInt(artistField.getText()) ));
				album.setGenre(new Genre(genreField.getText()));
				if(!imageField.getText().isBlank())
				{
					album.setImage(new Image(imageField.getText(), imageComboBox.getSelectedItem().toString()));
				}
				album.setLabel(new Label(labelField.getText()));
				album.setRelease(new Release(countryField.getText(), Integer.parseInt(yearField.getText()) ));
				
				if(id!=0)
		 		{
		 			if(db.updateAlbum(album))
					{
						JOptionPane.showMessageDialog(mf, "Updated successfully","Successful",JOptionPane.INFORMATION_MESSAGE);
						mf.displayAlbums();
						setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(mf, "Incomplete or invalid info.", "Error", JOptionPane.ERROR_MESSAGE);
					}
		 		}
		 		else
		 		{
		 			if(db.addAlbum(album))
					{
						JOptionPane.showMessageDialog(mf, "Album added successfully","Successful",JOptionPane.INFORMATION_MESSAGE);
						mf.displayAlbums();
						setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(mf, "Incomplete or invalid info.", "Error", JOptionPane.ERROR_MESSAGE);
					}
		 		}
			}
		});
		createAlbumBtn.setBounds(113, 316, 257, 21);
		contentPane.add(createAlbumBtn);
		setLocationRelativeTo(mf);
	}
	
}

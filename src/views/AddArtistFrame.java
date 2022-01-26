package views;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.DatabaseManagement;
import model.Artist;
import model.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddArtistFrame extends JFrame {

	private MainFrame mf;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField ageField;
	private JTextField totalTracksField;
	private JTextField totalAlbumFields;
	private JTextField imageField;
	private JComboBox genderComboBox;
	private JComboBox imageComboBox;
	private JButton addArtistBtn;
	private DatabaseManagement db = new DatabaseManagement();
	private int id = 0;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public AddArtistFrame(MainFrame mf) {
		this.mf = mf;
		initialize();
		setVisible(true);
	}
	
	public AddArtistFrame(MainFrame mf, Artist artist) {
		this.mf = mf;
		this.id = artist.getArtist_id();
		initialize();
		nameField.setText(artist.getArtist_name());
		ageField.setText(artist.getAge()+"");
		totalTracksField.setText(artist.getTrack_count()+"");
		totalAlbumFields.setText(artist.getAlbum_count()+"");
		
		if(artist.getImage()!=null)
		{
			imageField.setText(artist.getImage().getImage_name());
			imageComboBox.setSelectedItem(artist.getImage().getImage_type());
		}
		genderComboBox.setSelectedItem(artist.getGender());
		addArtistBtn.setText("Update Artist");
		
		setVisible(true);
	}
	
	private void initialize()
	{
		
		setBounds(100, 100, 466, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblArtistName = new JLabel("Artist Name");
		lblArtistName.setBounds(111, 62, 68, 13);
		contentPane.add(lblArtistName);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(210, 59, 127, 19);
		contentPane.add(nameField);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(111, 133, 68, 13);
		contentPane.add(lblAge);
		
		ageField = new JTextField();
		ageField.setColumns(10);
		ageField.setBounds(210, 130, 127, 19);
		contentPane.add(ageField);
		
		JLabel lblTotalTrack = new JLabel("Total Tracks");
		lblTotalTrack.setBounds(111, 170, 68, 13);
		contentPane.add(lblTotalTrack);
		
		totalTracksField = new JTextField();
		totalTracksField.setColumns(10);
		totalTracksField.setBounds(210, 167, 127, 19);
		contentPane.add(totalTracksField);
		
		JLabel lblTotalAlbums = new JLabel("Total Albums");
		lblTotalAlbums.setBounds(111, 208, 68, 13);
		contentPane.add(lblTotalAlbums);
		
		totalAlbumFields = new JTextField();
		totalAlbumFields.setColumns(10);
		totalAlbumFields.setBounds(210, 205, 127, 19);
		contentPane.add(totalAlbumFields);
		
		JLabel lblNewLabel_2 = new JLabel("Gendre");
		lblNewLabel_2.setBounds(111, 99, 60, 13);
		contentPane.add(lblNewLabel_2);
		
		 genderComboBox = new JComboBox();
		genderComboBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		genderComboBox.setBounds(210, 95, 127, 21);
		contentPane.add(genderComboBox);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Profile Image");
		lblNewLabel_1_1_1.setBounds(111, 244, 83, 13);
		contentPane.add(lblNewLabel_1_1_1);
		
		imageField = new JTextField();
		imageField.setColumns(10);
		imageField.setBounds(210, 241, 88, 19);
		contentPane.add(imageField);
		
		 imageComboBox = new JComboBox();
		imageComboBox.setModel(new DefaultComboBoxModel(new String[] {"JPEG", "PNG", "GIF", "TIFF", "RAW", "BMP", "SVG"}));
		imageComboBox.setBounds(308, 240, 60, 21);
		contentPane.add(imageComboBox);
		
		 addArtistBtn = new JButton("Add Artist");
		 addArtistBtn.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		Artist artist = new Artist();
		 		artist.setArtist_id(id);
		 		artist.setArtist_name(nameField.getText());
		 		artist.setAge(Integer.parseInt(ageField.getText()) );
		 		artist.setGender(genderComboBox.getSelectedItem()+"");
		 		artist.setTrack_count(Integer.parseInt(totalTracksField.getText()) );
		 		artist.setAlbum_count(Integer.parseInt(totalAlbumFields.getText()));
		 		if(!imageField.getText().isBlank())
				{
					artist.setImage(new Image(imageField.getText(), imageComboBox.getSelectedItem().toString()));
				}
		 		
		 		if(id!=0)
		 		{
		 			if(db.updateArtist(artist))
					{
						JOptionPane.showMessageDialog(mf, "Updated successfully","Successful",JOptionPane.INFORMATION_MESSAGE);
						mf.displayArtists();
						setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(mf, "Incomplete or invalid info.", "Error", JOptionPane.ERROR_MESSAGE);
					}
		 		}
		 		else
		 		{
		 			if(db.addArtist(artist))
					{
						JOptionPane.showMessageDialog(mf, "Artist added successfully","Successful",JOptionPane.INFORMATION_MESSAGE);
						mf.displayArtists();
						setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(mf, "Incomplete or invalid info.", "Error", JOptionPane.ERROR_MESSAGE);
					}
		 		}
		 	}
		 });
		addArtistBtn.setBounds(111, 280, 257, 21);
		
		contentPane.add(addArtistBtn);
		setLocationRelativeTo(mf);
	}
}

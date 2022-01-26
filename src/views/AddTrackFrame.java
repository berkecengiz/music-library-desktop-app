package views;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.DatabaseManagement;
import model.Genre;
import model.Track;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;

public class AddTrackFrame extends JFrame {

	private MainFrame mf;
	private JPanel contentPane;
	private JTextField trackNameField;
	private JTextField lengthField;
	private JTextField genreField;
	private JTextField languageField;
	private JButton addTrackBtn;
	private DatabaseManagement db = new DatabaseManagement();
	private int id = 0;
	
	

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public AddTrackFrame(MainFrame mf) {
		this.mf = mf;
		initialize();
		setVisible(true);
		
	}
	
	public AddTrackFrame(MainFrame mf, Track track) {
		this.mf = mf;
		this.id = track.getTrack_id();
		initialize();
		trackNameField.setText(track.getTrack_name());
		lengthField.setText(track.getLength()+"");
		genreField.setText(track.getGenre().getGenre());
		languageField.setText(track.getLanguage());
		addTrackBtn.setText("Update Track");
		setVisible(true);
	}
	
	private void initialize()
	{

		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
		
		
		setBounds(100, 100, 424, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Track Name");
		lblNewLabel.setBounds(111, 71, 68, 13);
		contentPane.add(lblNewLabel);
		
		trackNameField = new JTextField();
		trackNameField.setBounds(210, 68, 127, 19);
		contentPane.add(trackNameField);
		trackNameField.setColumns(10);
		
		JLabel lblLength = new JLabel("Length");
		lblLength.setBounds(111, 110, 68, 13);
		contentPane.add(lblLength);
		
		lengthField = new JTextField();
		lengthField.setColumns(10);
		lengthField.setBounds(210, 107, 127, 19);
		contentPane.add(lengthField);
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setBounds(111, 147, 68, 13);
		contentPane.add(lblGenre);
		
		genreField = new JTextField();
		genreField.setColumns(10);
		genreField.setBounds(210, 144, 127, 19);
		contentPane.add(genreField);
		
		JLabel lblLanguage = new JLabel("Language");
		lblLanguage.setBounds(111, 185, 68, 13);
		contentPane.add(lblLanguage);
		
		languageField = new JTextField();
		languageField.setColumns(10);
		languageField.setBounds(210, 182, 127, 19);
		contentPane.add(languageField);
		
		 addTrackBtn = new JButton("AddTrack");
		 addTrackBtn.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		Track track = new Track();
		 		track.setTrack_id(id);
		 		track.setTrack_name(trackNameField.getText());
		 		track.setLength( Float.parseFloat(lengthField.getText()));
		 		track.setLanguage(languageField.getText());
		 		track.setGenre(new Genre(genreField.getText()));
		 		
		 		if(id!=0)
				{
					if(db.updateTrack(track))
					{
						JOptionPane.showMessageDialog(mf, "Updated successfully","Successful",JOptionPane.INFORMATION_MESSAGE);
						mf.displayTracks();
						setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(mf, "Incomplete or invalid info.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
		 		else
				{
					if(db.addTrack(track))
					{
						JOptionPane.showMessageDialog(mf, "Track added successfully","Successful",JOptionPane.INFORMATION_MESSAGE);
						mf.displayTracks();
						setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(mf, "Incomplete or invalid info.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
		 		
		 	}
		 });
		addTrackBtn.setBounds(111, 233, 226, 21);
		
		contentPane.add(addTrackBtn);
		setLocationRelativeTo(mf);
	}

}

package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.DatabaseManagement;
import model.Image;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class SignUpFrame extends JFrame {

	private MainFrame mf;
	private JPanel contentPane;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPasswordField confirmPassField;
	private JTextField nameField;
	private JComboBox genderComboBox;
	private JTextField imageField;
	private JComboBox imageComboBox;
	private DatabaseManagement db = new DatabaseManagement();
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public SignUpFrame(MainFrame mf) {
		this.mf = mf;
		initialize();
		
	}

	private void initialize() {
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
		
		setTitle("Sign UP");
		
		
		setBounds(100, 100, 488, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(112, 139, 60, 13);
		contentPane.add(lblNewLabel);
		
		emailField = new JTextField();
		emailField.setBounds(221, 136, 111, 19);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(112, 177, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(221, 174, 111, 19);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Confirm Password");
		lblNewLabel_1_1.setBounds(112, 215, 83, 13);
		contentPane.add(lblNewLabel_1_1);
		
		confirmPassField = new JPasswordField();
		confirmPassField.setBounds(221, 212, 111, 19);
		contentPane.add(confirmPassField);
		
		JLabel lblNewLabel_2 = new JLabel("Gendre");
		lblNewLabel_2.setBounds(112, 105, 60, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Name");
		lblNewLabel_3.setBounds(112, 71, 60, 13);
		contentPane.add(lblNewLabel_3);
		
		nameField = new JTextField();
		nameField.setBounds(221, 68, 111, 19);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		JButton signUpBtn = new JButton("SignUp");
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String password =passwordField.getText();
				String confirm = confirmPassField.getText();
				
				if(!password.equals(confirm))
					JOptionPane.showMessageDialog(null, "Cofirm Password doesn't match", "Error", JOptionPane.ERROR_MESSAGE);
				else
				{
					User user = new User();
					
					user.setPassword(password);
					user.setName(nameField.getText());
					user.setEmail(emailField.getText());
					user.setGender(genderComboBox.getSelectedItem().toString());
					if(!imageField.getText().isBlank())
					{
						user.setImage(new Image(imageField.getText(), imageComboBox.getSelectedItem().toString()));
					}
					
					
					if(db.addUser(user))
					{
						JOptionPane.showMessageDialog(signUpBtn, "Signed Up successfully","Successful",JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
					}
						
					else
						JOptionPane.showMessageDialog(null, "Incomplete info or username already exists", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		signUpBtn.setBounds(112, 293, 267, 21);
		contentPane.add(signUpBtn);
		
		genderComboBox = new JComboBox();
		genderComboBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		genderComboBox.setBounds(221, 105, 111, 21);
		contentPane.add(genderComboBox);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Profile Image");
		lblNewLabel_1_1_1.setBounds(112, 254, 83, 13);
		contentPane.add(lblNewLabel_1_1_1);
		
		imageField = new JTextField();
		imageField.setColumns(10);
		imageField.setBounds(221, 251, 88, 19);
		contentPane.add(imageField);
		
		 imageComboBox = new JComboBox();
		imageComboBox.setModel(new DefaultComboBoxModel(new String[] {"JPEG", "PNG", "GIF", "TIFF", "RAW", "BMP", "SVG"}));
		imageComboBox.setBounds(319, 250, 60, 21);
		contentPane.add(imageComboBox);
		
		 setLocationRelativeTo(mf);
		
		setVisible(true);
		
	}
}

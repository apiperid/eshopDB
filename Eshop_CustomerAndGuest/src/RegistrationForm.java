
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class RegistrationForm extends JFrame 
{
	private String SQLusername = "Admin";
	private String SQLpassword = "Admin";
	
	private static Connection conn;
	private static Statement st;
	@SuppressWarnings("unused")
	private static int rs;
	
	private JTextField userNameField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField birthDateField;
	private JTextField phoneField;
	private JPasswordField passwordField;
	private JTextField emailField;
	private JTextField ibanField;
	private JTextField cityField;
	private JTextField sreetField;
	private JTextField postalCodeField;

	private String userName,firstName,lastName,birthDate,phone,email,iban,city,street,postalCode;
	private char[] passWord;
	private String[] genderOptions = {"","Male","Female"};
	private String gender;
	
	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public RegistrationForm(String SchemaName,int Port) throws ClassNotFoundException, SQLException 
	{	
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:"+Port+"/"+SchemaName,SQLusername,SQLpassword);
	        st=conn.createStatement();
		}catch (ClassNotFoundException | SQLException e)
		{
			//e.printStackTrace();
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null,
					"An Unexpected Error Happened.",
					"ERROR",JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Image titleLogo = new ImageIcon(this.getClass().getResource("TitleImage.png")).getImage();
		setIconImage(titleLogo);
		setBounds(100, 100, 402, 542);
		setResizable(false);
		setTitle("Registration Form");
		getContentPane().setLayout(null);
		
		userNameField = new JTextField();
		userNameField.setBounds(121, 33, 163, 20);
		getContentPane().add(userNameField);
		userNameField.setColumns(10);
		
		firstNameField = new JTextField();
		firstNameField.setColumns(10);
		firstNameField.setBounds(121, 95, 163, 20);
		getContentPane().add(firstNameField);
		
		lastNameField = new JTextField();
		lastNameField.setColumns(10);
		lastNameField.setBounds(121, 126, 163, 20);
		getContentPane().add(lastNameField);
		
		birthDateField = new JTextField();
		birthDateField.setColumns(10);
		birthDateField.setBounds(121, 157, 163, 20);
		getContentPane().add(birthDateField);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(121, 188, 163, 20);
		getContentPane().add(phoneField);
		
		JLabel usernameLabel = new JLabel("Username :");
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		usernameLabel.setBounds(10, 35, 101, 14);
		getContentPane().add(usernameLabel);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(10, 66, 101, 14);
		getContentPane().add(lblPassword);
		
		JLabel lblFirstName = new JLabel("First Name :");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFirstName.setBounds(10, 97, 101, 14);
		getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name :");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLastName.setBounds(10, 128, 101, 14);
		getContentPane().add(lblLastName);
		
		JLabel lblBirthDate = new JLabel("Birth Date :");
		lblBirthDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBirthDate.setBounds(10, 159, 101, 14);
		getContentPane().add(lblBirthDate);
		
		JLabel lblPhone = new JLabel("Phone :");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPhone.setBounds(10, 190, 101, 14);
		getContentPane().add(lblPhone);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(121, 64, 163, 20);
		getContentPane().add(passwordField);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(121, 219, 163, 20);
		getContentPane().add(emailField);
		
		ibanField = new JTextField();
		ibanField.setColumns(10);
		ibanField.setBounds(121, 250, 163, 20);
		getContentPane().add(ibanField);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(10, 221, 101, 14);
		getContentPane().add(lblEmail);
		
		JLabel lblIban = new JLabel("IBAN :");
		lblIban.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIban.setBounds(10, 252, 101, 14);
		getContentPane().add(lblIban);
		
		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(294, 36, 25, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(294, 67, 25, 14);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(294, 98, 25, 14);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(294, 129, 25, 14);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(294, 160, 25, 14);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(294, 191, 25, 14);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("*");
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBounds(294, 222, 25, 14);
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("*");
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setBounds(294, 253, 25, 14);
		getContentPane().add(label_6);
		
		JLabel lblNewLabel_2 = new JLabel("NOTE : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 400, 54, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Text Fields with ");
		lblNewLabel_3.setBounds(73, 401, 101, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel label_7 = new JLabel("*");
		label_7.setForeground(Color.RED);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_7.setBounds(163, 401, 25, 14);
		getContentPane().add(label_7);
		
		JLabel lblAreNecessary = new JLabel("are necessary.");
		lblAreNecessary.setBounds(184, 401, 85, 14);
		getContentPane().add(lblAreNecessary);
		
		cityField = new JTextField();
		cityField.setColumns(10);
		cityField.setBounds(121, 281, 163, 20);
		getContentPane().add(cityField);
		
		sreetField = new JTextField();
		sreetField.setColumns(10);
		sreetField.setBounds(121, 312, 163, 20);
		getContentPane().add(sreetField);
		
		postalCodeField = new JTextField();
		postalCodeField.setColumns(10);
		postalCodeField.setBounds(121, 343, 163, 20);
		getContentPane().add(postalCodeField);
		
		JLabel label_8 = new JLabel("*");
		label_8.setForeground(Color.RED);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setBounds(294, 284, 25, 14);
		getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("*");
		label_9.setForeground(Color.RED);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setBounds(294, 315, 25, 14);
		getContentPane().add(label_9);
		
		JLabel label_10 = new JLabel("*");
		label_10.setForeground(Color.RED);
		label_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_10.setBounds(294, 346, 25, 14);
		getContentPane().add(label_10);
		
		JLabel lblCity = new JLabel("City :");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCity.setBounds(10, 284, 101, 14);
		getContentPane().add(lblCity);
		
		JLabel lblStreet = new JLabel("Street :");
		lblStreet.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStreet.setBounds(10, 315, 101, 14);
		getContentPane().add(lblStreet);
		
		JLabel lblPostalCode = new JLabel("Postal Code:");
		lblPostalCode.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPostalCode.setBounds(10, 346, 101, 14);
		getContentPane().add(lblPostalCode);
		
		JLabel lblGender = new JLabel("Gender :");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGender.setBounds(10, 375, 101, 14);
		getContentPane().add(lblGender);
		
		JComboBox<Object> comboBox = new JComboBox<Object>(genderOptions);
		comboBox.setBounds(121, 370, 112, 20);
		getContentPane().add(comboBox);
		
		JLabel label_11 = new JLabel("*");
		label_11.setForeground(Color.RED);
		label_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_11.setBounds(243, 374, 25, 14);
		getContentPane().add(label_11);

		JButton btnNewButton = new JButton("Complete Registration");
		btnNewButton.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) 
			{
				userName = userNameField.getText();
				passWord = passwordField.getPassword();
				String passWordTmp = new String(passWord);
				firstName = "'"+firstNameField.getText()+"'";
				lastName = "'"+lastNameField.getText()+"'";
				birthDate = "'"+birthDateField.getText()+"'";
				phone = "'"+phoneField.getText()+"'";
				email = "'"+emailField.getText()+"'";
				iban = "'"+ibanField.getText()+"'";
				city = "'"+cityField.getText()+"'";
				street = "'"+sreetField.getText()+"'";
				postalCode = "'"+postalCodeField.getText()+"'";
				gender = "'"+comboBox.getSelectedItem().toString()+"'";
				
				String query = "INSERT INTO user VALUES ("+"'"+userName+"'"+",'"+passWordTmp+"',"+email+","
				+phone+","+birthDate+","+iban+","+gender+","+street+","+city+","+postalCode+","+firstName+","+lastName+")";
				
				//System.out.println(query);			
				try 
				{
				  rs = st.executeUpdate(query);
				  CreateUserAndPrivileges user = new CreateUserAndPrivileges(SchemaName,userName,passWordTmp,Port);
				} catch (SQLException | ClassNotFoundException e1)  
				{
					//e1.printStackTrace();
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null,
							"An Unexpected Error Happened.\nPlease try again.",
							"ERROR",JOptionPane.ERROR_MESSAGE);
				}		

				FirstScreen firstScreen = null;
				try 
				{
					firstScreen = new FirstScreen();
				} catch (ClassNotFoundException e1) 
				{
					e1.printStackTrace();
				}
				firstScreen.showInScreen(true, firstScreen);
				dispose();
			}
		});
		btnNewButton.setBounds(121, 455, 163, 37);
		getContentPane().add(btnNewButton);	
	}  
}
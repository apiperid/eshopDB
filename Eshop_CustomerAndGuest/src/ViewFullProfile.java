

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.awt.Color;


@SuppressWarnings("serial")
public class ViewFullProfile extends JFrame 
{
	private String SQLusername = "Admin";
	private String SQLpassword = "Admin";
	
	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
	
	private String firstName,lastName,birthDate,iban,email,city,street,postalCode,phone,gender;
	
	
	/** 
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ViewFullProfile(String SchemaName,String username,String password,int Port) throws SQLException, ClassNotFoundException 
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
		
		Image titleLogo = new ImageIcon(this.getClass().getResource("TitleImage.png")).getImage();
		setIconImage(titleLogo);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 387, 482);
		getContentPane().setLayout(null);
		setTitle("User's Profile");
		setResizable(false);
		
		JLabel usernameLabel = new JLabel("Username :");
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		usernameLabel.setBounds(10, 22, 120, 23);
		getContentPane().add(usernameLabel);
		
		JLabel birthDateLabel = new JLabel("Birth Date :");
		birthDateLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		birthDateLabel.setBounds(10, 124, 120, 23);
		getContentPane().add(birthDateLabel);
		
		JLabel genderLabel = new JLabel("Gender :");
		genderLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		genderLabel.setBounds(10, 192, 120, 23);
		getContentPane().add(genderLabel);
		
		JLabel cityLabel = new JLabel("City :");
		cityLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		cityLabel.setBounds(10, 226, 120, 23);
		getContentPane().add(cityLabel);
		
		JLabel streetLabel = new JLabel("Street :");
		streetLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		streetLabel.setBounds(10, 260, 120, 23);
		getContentPane().add(streetLabel);
		
		JLabel ibanLabel = new JLabel("IBAN :");
		ibanLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		ibanLabel.setBounds(10, 396, 120, 23);
		getContentPane().add(ibanLabel);
		
		JLabel ageLabel = new JLabel("Age :");
		ageLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		ageLabel.setBounds(10, 158, 120, 23);
		getContentPane().add(ageLabel);
		
		JLabel firstNameLabel = new JLabel("First Name :");
		firstNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		firstNameLabel.setBounds(10, 56, 120, 23);
		getContentPane().add(firstNameLabel);
		
		JLabel lastNameLabel = new JLabel("Last Name :");
		lastNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lastNameLabel.setBounds(10, 90, 120, 23);
		getContentPane().add(lastNameLabel);
		
		JLabel fistNameInfo = new JLabel("");
		fistNameInfo.setForeground(Color.RED);
		fistNameInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		fistNameInfo.setBounds(140, 62, 174, 14);
		getContentPane().add(fistNameInfo);
		
		JLabel lastNameInfo = new JLabel("");
		lastNameInfo.setForeground(Color.RED);
		lastNameInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lastNameInfo.setBounds(140, 96, 174, 14);
		getContentPane().add(lastNameInfo);
		
		JLabel birthDateInfo = new JLabel("");
		birthDateInfo.setForeground(Color.RED);
		birthDateInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		birthDateInfo.setBounds(140, 130, 174, 14);
		getContentPane().add(birthDateInfo);
		
		JLabel genderInfo = new JLabel("");
		genderInfo.setForeground(Color.RED);
		genderInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		genderInfo.setBounds(140, 198, 174, 14);
		getContentPane().add(genderInfo);
		
		JLabel cityInfo = new JLabel("");
		cityInfo.setForeground(Color.RED);
		cityInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		cityInfo.setBounds(140, 232, 174, 14);
		getContentPane().add(cityInfo);
		
		JLabel streetInfo = new JLabel("");
		streetInfo.setForeground(Color.RED);
		streetInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		streetInfo.setBounds(140, 266, 174, 14);
		getContentPane().add(streetInfo);
		
		JLabel ibanInfo = new JLabel("");
		ibanInfo.setForeground(Color.RED);
		ibanInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		ibanInfo.setBounds(140, 396, 231, 14);
		getContentPane().add(ibanInfo);
		
		JLabel usernameInfo = new JLabel("");
		usernameInfo.setForeground(Color.RED);
		usernameInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		usernameInfo.setBounds(140, 28, 174, 14);
		getContentPane().add(usernameInfo);
		
		JLabel lblPostalCode = new JLabel("Postal Code :");
		lblPostalCode.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPostalCode.setBounds(10, 303, 120, 14);
		getContentPane().add(lblPostalCode);
		   
		JLabel postalCodeIfo = new JLabel("");
		postalCodeIfo.setForeground(Color.RED);
		postalCodeIfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		postalCodeIfo.setBounds(140, 303, 174, 14);
	    getContentPane().add(postalCodeIfo);
		   
		JLabel ageInfo = new JLabel("");
		ageInfo.setForeground(Color.RED);
		ageInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		ageInfo.setBounds(140, 164, 174, 14);
		getContentPane().add(ageInfo);
		   
		JLabel lblEmail = new JLabel("E-mail :");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(10, 328, 120, 23);
		getContentPane().add(lblEmail);
		   
		JLabel lblPhone = new JLabel("Phone :");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPhone.setBounds(10, 362, 120, 23);
		getContentPane().add(lblPhone);
		   
		JLabel emailInfo = new JLabel("");
		emailInfo.setForeground(Color.RED);
		emailInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		emailInfo.setBounds(140, 334, 174, 14); 
		getContentPane().add(emailInfo);
		   
		JLabel phoneInfo = new JLabel("");
		phoneInfo.setForeground(Color.RED);
		phoneInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		phoneInfo.setBounds(140, 368, 174, 14);
		getContentPane().add(phoneInfo);
		
		String query = "SELECT * FROM user WHERE Username ='"+username+"'";
		//System.out.println(query);
	   rs = st.executeQuery(query);
	   while(rs.next())
	   {
		   firstName = rs.getString("FirstName");
		   lastName = rs.getString("LastName");
		   city = rs.getString("City");
		   postalCode = rs.getString("PostalCode");
		   birthDate = rs.getString("BirthDate");
		   street = rs.getString("Street");
	       gender = rs.getString("Gender");
		   iban = rs.getString("IBAN");
		   phone = rs.getString("Phone");
		   email = rs.getString("Email");
	   }          
	   
	   fistNameInfo.setText(firstName);
	   lastNameInfo.setText(lastName);
	   birthDateInfo.setText(birthDate);
	   genderInfo.setText(gender);
	   cityInfo.setText(city);
	   streetInfo.setText(street);
	   ibanInfo.setText(iban);
	   usernameInfo.setText(username);
	   postalCodeIfo.setText(postalCode);
	   emailInfo.setText(email);
	   phoneInfo.setText(phone);
	   
	   String age = String.valueOf(Year.now().getValue() - Integer.valueOf(birthDate.substring(0,4)));
	   ageInfo.setText(age);
	   
	   conn.close();
	   st.close();
	   rs.close();

	}
}
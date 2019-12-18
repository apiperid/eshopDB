
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;


public class FirstScreen 
{	
	private static String SchemaName = "EshopDB";
	private static int Port = 3307;
	
	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
    private String username;
    private String password; 
    
    
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws ClassNotFoundException
	{	
		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					FirstScreen window = new FirstScreen();
					window.frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 */
	public FirstScreen() throws ClassNotFoundException 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	private void initialize() throws ClassNotFoundException
	{		
		Image titleLogo = new ImageIcon(this.getClass().getResource("TitleImage.png")).getImage();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 290, 349);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setTitle("E-shop");
		frame.setIconImage(titleLogo);
		
		JLabel usernameLabel = new JLabel("Username :");
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		usernameLabel.setForeground(Color.BLACK);
		usernameLabel.setBounds(26, 128, 83, 14);
		frame.getContentPane().add(usernameLabel);
		 
		JLabel passwordLabel = new JLabel("Password  :");
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		passwordLabel.setForeground(Color.BLACK);
		passwordLabel.setBounds(26, 165, 83, 14);
		frame.getContentPane().add(passwordLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(119, 125, 142, 19);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(119, 162, 142, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("");
		Image logo = new ImageIcon(this.getClass().getResource("firstScreen.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(logo));
		lblNewLabel_2.setBounds(93, 11, 100, 89);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
                RegistrationForm regForm = null;
				try 
				{
					regForm = new RegistrationForm(SchemaName,Port);
				} catch (ClassNotFoundException e1) 
				{
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				regForm.setVisible(true);
				frame.dispose();
			}
		});
		registerButton.setBounds(85, 251, 108, 36);
		frame.getContentPane().add(registerButton);		
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				username = usernameField.getText();
				password = new String (passwordField.getPassword());
				try 
				{
					@SuppressWarnings("unused")
					Login login = new Login(username,password,SchemaName,Port);
				} catch (ClassNotFoundException | SQLException e1) 
				{
					e1.printStackTrace();
				}	
			}
		});
		btnLogin.setBounds(22, 204, 108, 36);
		frame.getContentPane().add(btnLogin);
		
		JButton btnLoginAsGuest = new JButton("Login As Guest");
		btnLoginAsGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				java.awt.Window win[] = java.awt.Window.getWindows(); 
		    	for(int i=0;i<win.length;i++)
		    		win[i].dispose(); 
		    		
		    	Guest guest = null;
				try {
					guest = new Guest(SchemaName,Port);
				} catch (ClassNotFoundException | SQLException e) 
				{
					e.printStackTrace();
				}
		    	 guest.setVisible(true);
			}
		});
		btnLoginAsGuest.setBounds(142, 204, 120, 36);
		frame.getContentPane().add(btnLoginAsGuest);
	}

	public void showInScreen(boolean show,FirstScreen obj) 
	{
		frame.setVisible(true);
	}
}
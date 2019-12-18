
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class Login 
{	
	private String SQLusername = "Admin";
	private String SQLpassword = "Admin";

	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
	
	@SuppressWarnings("unused")
	public Login(String username,String password,String SchemaName,int Port) throws ClassNotFoundException, SQLException
	{
	    if( (username.equals("") == true) || (password.equals("") == true))
	    {
	    	Toolkit.getDefaultToolkit().beep();
 	    	JOptionPane.showMessageDialog(null,"You must insert a username and a password.\nPlease Try again.", 
 	    			"Error",JOptionPane.ERROR_MESSAGE);
	    }
	    else
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
	        
			String query,COUNT = null;
			query = "SELECT COUNT(Username) FROM user WHERE Username='"+username+"'";
			rs = st.executeQuery(query);
			while(rs.next())
	 	    {
				COUNT = rs.getString("COUNT(Username)");
	 	    }  
			if( COUNT.equals("0") == true)
			{
				Toolkit.getDefaultToolkit().beep();
	 	    	JOptionPane.showMessageDialog(null,"Wrong Password or Username, Please Try again.", 
	 	    			"Error",JOptionPane.ERROR_MESSAGE); 
			}
			else
			{
				query = "SELECT Password FROM user WHERE Username='"+username+"'";
				String passwordTaken = null,userType = null; 
				
				rs = st.executeQuery(query);

		 	    while(rs.next())
		 	    {
		 		    passwordTaken = rs.getString("Password");
		 	    }    
		        //System.out.println(userType);
		        //System.out.println(passwordTaken);
		 	    if(passwordTaken.equals(password)== false)
		 	    {
		 	    	Toolkit.getDefaultToolkit().beep();
		 	    	JOptionPane.showMessageDialog(null,"Wrong Password or Username, Please Try again.", 
		 	    			"Error",JOptionPane.ERROR_MESSAGE); 	
		 	    }
		 	    else
		 	    {
		 	    	java.awt.Window win[] = java.awt.Window.getWindows(); 
			    	for(int i=0;i<win.length;i++)
			    		win[i].dispose(); 
			    		
			    	 Customer customer = new Customer(SchemaName,username,password,Port);
			    	 customer.setVisible(true); 
		 	    }	   
		    }
		}	    
	}
}
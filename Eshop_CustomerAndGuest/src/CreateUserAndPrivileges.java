
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class CreateUserAndPrivileges 
{
	private String SQLusername = "Admin";
	private String SQLpassword = "Admin";
	
	private static Connection conn;
	private static Statement st;
	@SuppressWarnings("unused")
	private static int rs;
	
	public CreateUserAndPrivileges(String SchemaName,String username,String password,int Port) throws SQLException, ClassNotFoundException
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
        
        String query = "CREATE USER '"+username+"'@'%'"+" IDENTIFIED BY "+"'"+password+"';";
        rs = st.executeUpdate(query);      
        query = "GRANT SELECT ON EshopDB.cpus TO '"+username+"'@'%';";
        rs = st.executeUpdate(query);
        query = "GRANT SELECT ON EshopDB.gpu TO '"+username+"'@'%';";
        rs = st.executeUpdate(query);
        query = "GRANT SELECT ON EshopDB.ram TO '"+username+"'@'%';";
        rs = st.executeUpdate(query);
        query = "GRANT SELECT ON EshopDB.psu TO '"+username+"'@'%';";
        rs = st.executeUpdate(query);
        query = "GRANT SELECT ON EshopDB.tower TO '"+username+"'@'%';";
        rs = st.executeUpdate(query);
        query = "GRANT SELECT ON EshopDB.computer TO '"+username+"'@'%';";
        rs = st.executeUpdate(query);
        query = "GRANT SELECT ON EshopDB.product TO '"+username+"'@'%';";
        rs = st.executeUpdate(query);
        query = "GRANT SELECT ON EshopDB.motherboard TO '"+username+"'@'%';";
        rs = st.executeUpdate(query);
        query = "GRANT SELECT ON EshopDB.harddisk TO '"+username+"'@'%';"; 
        rs = st.executeUpdate(query);
        query = "GRANT SELECT , INSERT ON EshopDB.orders TO '"+username+"'@'%';";
        rs = st.executeUpdate(query);
        query = "GRANT SELECT , INSERT ON EshopDB.order_contains_product TO '"+username+"'@'%';";
        rs = st.executeUpdate(query);
        
        JOptionPane.showMessageDialog(null,
			    "Thank You For Your Registration : "+username,
			    "Registration Success",
			    JOptionPane.PLAIN_MESSAGE);
        
        conn.close();
        st.close();      
	} 
}

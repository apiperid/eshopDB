
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

public class UpdateStock 
{
	private String SQLusername = "Admin";
	private String SQLpassword = "Admin";

	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
	@SuppressWarnings("unused")
	private static int rs2;
	
	public UpdateStock(String SchemaName,Vector<Integer> Products,Vector<Integer> Quantities,int Port) throws ClassNotFoundException, SQLException
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
		
        String query;
        int Availability = 0;
        for (int i=0;i<Products.size();i++)
        {
        	query = "SELECT Availability FROM product WHERE ProductID="+Products.get(i);
        	rs = st.executeQuery(query);
        	while(rs.next())
        	{
        		Availability = Integer.parseInt(rs.getString("Availability")); 
        	}
        	query = "UPDATE product SET Availability = "+(Availability - Quantities.get(i))+" WHERE ProductID = "+Products.get(i);
        	rs2 = st.executeUpdate(query);
        }  
	}
}
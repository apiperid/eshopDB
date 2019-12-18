
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel; 
import java.awt.Font;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class GetOrderAdditionalInfo extends JFrame 
{	
	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
	
	private JPanel contentPane;
	DefaultListModel<String> OrderListModel = new DefaultListModel<String>();


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GetOrderAdditionalInfo(String SchemaName,String SQLusername,String SQLpassword,String OrderID,int Port) throws SQLException, ClassNotFoundException 
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
			dispose();
		}
		
		Image titleLogo = new ImageIcon(this.getClass().getResource("TitleImage.png")).getImage();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 311, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Order's Additional Info"); 
		setIconImage(titleLogo);
		setResizable(false);
		
		JLabel lblOrderID = new JLabel("OrderID :");
		lblOrderID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOrderID.setBounds(10, 11, 93, 27);
		contentPane.add(lblOrderID);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(10, 49, 93, 27);
		contentPane.add(lblUsername);
		
		JLabel lblTotalValue = new JLabel("Total Value :");
		lblTotalValue.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotalValue.setBounds(10, 87, 93, 27);
		contentPane.add(lblTotalValue);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDate.setBounds(10, 125, 93, 27);
		contentPane.add(lblDate);
		
		JLabel lblProgress = new JLabel("Progress :");
		lblProgress.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProgress.setBounds(10, 163, 93, 27);
		contentPane.add(lblProgress);
		
		JLabel orderIdInfo = new JLabel("");
		orderIdInfo.setForeground(Color.RED);
		orderIdInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		orderIdInfo.setBounds(113, 11, 151, 27);
		contentPane.add(orderIdInfo);
		
		JLabel usernameInfo = new JLabel("");
		usernameInfo.setForeground(Color.RED);
		usernameInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		usernameInfo.setBounds(113, 49, 151, 27);
		contentPane.add(usernameInfo);
		
		JLabel totalValueInfo = new JLabel("");
		totalValueInfo.setForeground(Color.RED);
		totalValueInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalValueInfo.setBounds(113, 87, 151, 27);
		contentPane.add(totalValueInfo);
		
		JLabel dateInfo = new JLabel("");
		dateInfo.setForeground(Color.RED);
		dateInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		dateInfo.setBounds(113, 125, 151, 27);
		contentPane.add(dateInfo);
		
		JLabel progressInfo = new JLabel("");
		progressInfo.setForeground(Color.RED);
		progressInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		progressInfo.setBounds(113, 163, 151, 27);
		contentPane.add(progressInfo);
		
		JLabel lblNewLabel_1 = new JLabel("___________________________________________");
		lblNewLabel_1.setBounds(0, 190, 305, 14);
		contentPane.add(lblNewLabel_1);
		
		
		JList list = new JList();
		list.setForeground(Color.BLACK);
		list.setFont(new Font("Tahoma", Font.BOLD, 11));
		list.setEnabled(false);
		list.setBounds(10, 247, 21, 49);
		contentPane.add(list);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(62, 215, 181, 152);
		contentPane.add(scrollPane);
			
		String query = "SELECT * FROM orders WHERE OrderID="+OrderID;
		rs = st.executeQuery(query);
		while(rs.next())
		{
			orderIdInfo.setText(OrderID);
			usernameInfo.setText(rs.getString("Username"));
			totalValueInfo.setText(rs.getString("TotalValue"));
			dateInfo.setText(rs.getString("Date"));
			progressInfo.setText(rs.getString("Progress"));		
		}
		query = "SELECT ProductID,Quantity FROM order_contains_product WHERE OrderID="+OrderID;
		String Header = "ProductID       Quantity";
		String ProductID,Quantity;
		OrderListModel.addElement(Header);
		OrderListModel.addElement("_________________________");
		rs = st.executeQuery(query);
		while(rs.next())
		{
            ProductID = rs.getString("ProductID");
            Quantity = rs.getString("Quantity");	
            OrderListModel.addElement("      "+ProductID+"                      "+Quantity);
		}
		list.setModel(OrderListModel);
		conn.close();
		rs.close();
		st.close();	
	}
}
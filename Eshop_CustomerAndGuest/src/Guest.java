
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class Guest extends JFrame 
{
	private JPanel contentPane;
	
    private String SQLusername = "Guest";
    private String SQLpassword = "Guest";
    
    private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
     
    final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    DefaultListModel<String> SearchListModel = new DefaultListModel<String>();
    private String[] selectOptions = {"","CPU","GPU","RAM","MotherBoard","HardDisk","PSU","Tower","Computer"};

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public Guest(String SchemaName,int Port) throws ClassNotFoundException, SQLException 
	{
		
        Image titleLogo = new ImageIcon(this.getClass().getResource("TitleImage.png")).getImage();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 643);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("E-shop  - Guest Mode"); 
		setIconImage(titleLogo);
		setResizable(false);
		
		JLabel lblNewLabel_1 = new JLabel("Category : Guest");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 11, 131, 14);
		getContentPane().add(lblNewLabel_1);
		
		Calendar cal = Calendar.getInstance();
		JLabel lastLogin = new JLabel("Last Login : " + sdf.format(cal.getTime()));
		lastLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lastLogin.setBounds(10, 36, 201, 14);
		getContentPane().add(lastLogin);
		
		JList<String> SearchList = new JList<String>();
		JScrollPane scrollPane = new JScrollPane(SearchList);
		scrollPane.setBounds(10, 114, 601, 479);
		contentPane.add(scrollPane);
		
		JComboBox<Object> comboBox = new JComboBox<Object>(selectOptions);
		comboBox.setBounds(384, 72, 109, 29);
		contentPane.add(comboBox);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String choice = comboBox.getSelectedItem().toString();
				try 
				{
					SearchListModel.removeAllElements();
					SearchList.setModel(SearchListModel);
					
					SearchListModel = search(SchemaName,choice,SearchListModel,Port);
					SearchList.setModel(SearchListModel);
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		searchButton.setBounds(514, 70, 97, 33);
		contentPane.add(searchButton);	
		
		JButton logOutBtn = new JButton("Log Out");
		logOutBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				JOptionPane.showMessageDialog(null,
						"You are Successfully Logged Out.",
						"Log Out",JOptionPane.PLAIN_MESSAGE);          
        	    dispose();
			}
		});
		logOutBtn.setBounds(514, 7, 97, 33);
		contentPane.add(logOutBtn);
	}
	
	public DefaultListModel<String> search(String SchemaName,String choice,DefaultListModel<String> SearchListModel,int Port) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:"+Port+"/"+SchemaName,SQLusername,SQLpassword);
        st=conn.createStatement();
        
        String ProductID,Price,Availability,Model,Socket,Watts;
        String Manufacturer,Cores,Clock,Type,Capacity,Dimensions;
        String query,Header;
        String CPUID,GPUID,RAMID,PSUID,HardDiskID,MotherBoardID,TowerID;
        
        switch (choice)
        {
        case "CPU":
        	Header = "ProductID    Price    Availability    Manufacturer    Model    Cores    Clock";
        	SearchListModel.addElement(Header);
        	SearchListModel.addElement("_______________________________________________________________");
        	query = "SELECT * FROM cpus JOIN product ON cpus.CPUID = product.ProductID";
        	rs = st.executeQuery(query);
        	while(rs.next())
        	{
        		ProductID = rs.getString("ProductID");
        		Price = rs.getString("Price");
        		Availability = (rs.getString ("Availability"))!= null? rs.getString ("Availability"): "-";
        		Manufacturer = rs.getString("Manufacturer");
        		Model = rs.getString("Model");
        		Cores = rs.getString("Cores");
        		Clock = rs.getString("Clock");
        		SearchListModel.addElement(ProductID+"      "+Price+"      "+Availability+"      "
        				+ ""+Manufacturer+"      "+Model+"      "+Cores+"      "+Clock); 		   		
        	}	
        	break;
        case "GPU":
        	Header = "ProductID    Price    Availability    Manufacturer    Model    Capacity";
        	SearchListModel.addElement(Header);
        	SearchListModel.addElement("_______________________________________________________________");
        	query = "SELECT * FROM gpu JOIN product ON gpu.GPUID = product.ProductID";
        	rs = st.executeQuery(query);
        	while(rs.next())
        	{
        		ProductID = rs.getString("ProductID");
        		Price = rs.getString("Price");
        		Availability = (rs.getString ("Availability"))!= null? rs.getString ("Availability"): "-";
        		Manufacturer = rs.getString("Manufacturer");
        		Model = rs.getString("Model");
        		Capacity = rs.getString("Capacity");
        		SearchListModel.addElement(ProductID+"      "+Price+"      "+Availability+"      "
        				+ ""+Manufacturer+"      "+Model+"      "+Capacity); 		   		
        	}	
        	break;
        case "RAM":
        	Header = "ProductID    Price    Availability    Manufacturer    Model    Capacity";
        	SearchListModel.addElement(Header);
        	SearchListModel.addElement("_______________________________________________________________");
        	query = "SELECT * FROM ram JOIN product ON ram.RAMID = product.ProductID";
        	rs = st.executeQuery(query);
        	while(rs.next())
        	{
        		ProductID = rs.getString("ProductID");
        		Price = rs.getString("Price");
        		Availability = (rs.getString ("Availability"))!= null? rs.getString ("Availability"): "-";
        		Manufacturer = rs.getString("Manufacturer");
        		Model = rs.getString("Model");
        		Capacity = rs.getString("Capacity");
        		SearchListModel.addElement(ProductID+"      "+Price+"      "+Availability+"      "
        				+ ""+Manufacturer+"      "+Model+"      "+Capacity); 		   		
        	}	
        	break;
        case "MotherBoard":
        	Header = "ProductID    Price    Availability    Manufacturer    Model    Socket";
        	SearchListModel.addElement(Header);
        	SearchListModel.addElement("_______________________________________________________________");
        	query = "SELECT * FROM motherboard JOIN product ON motherboard.MotherBoardID = product.ProductID";
        	rs = st.executeQuery(query);
        	while(rs.next())
        	{
        		ProductID = rs.getString("ProductID");
        		Price = rs.getString("Price");
        		Availability = (rs.getString ("Availability"))!= null? rs.getString ("Availability"): "-";
        		Manufacturer = rs.getString("Manufacturer");
        		Model = rs.getString("Model");
        		Socket = rs.getString("Socket");
        		SearchListModel.addElement(ProductID+"      "+Price+"      "+Availability+"      "
        				+ ""+Manufacturer+"      "+Model+"      "+Socket); 		   		
        	}	
        	break;
        case "HardDisk":
        	Header = "ProductID    Price    Availability    Manufacturer    Model    Capacity    Type";
        	SearchListModel.addElement(Header);
        	SearchListModel.addElement("_______________________________________________________________");
        	query = "SELECT * FROM harddisk JOIN product ON harddisk.HardDiskID = product.ProductID";
        	rs = st.executeQuery(query);
        	while(rs.next())
        	{
        		ProductID = rs.getString("ProductID");
        		Price = rs.getString("Price");
        		Availability = (rs.getString ("Availability"))!= null? rs.getString ("Availability"): "-";
        		Manufacturer = rs.getString("Manufacturer");
        		Model = rs.getString("Model");
        		Capacity = rs.getString("Capacity");
        		Type = rs.getString("Type");
        		SearchListModel.addElement(ProductID+"      "+Price+"      "+Availability+"      "
        				+ ""+Manufacturer+"      "+Model+"      "+Capacity+"      "+Type); 		   		
        	}	
        	break;
        case "PSU":
        	Header = "ProductID    Price    Availability    Manufacturer    Model    Watts";
        	SearchListModel.addElement(Header);
        	SearchListModel.addElement("_______________________________________________________________");
        	query = "SELECT * FROM psu JOIN product ON psu.PSUID = product.ProductID";
        	rs = st.executeQuery(query);
        	while(rs.next())
        	{
        		ProductID = rs.getString("ProductID");
        		Price = rs.getString("Price");
        		Availability = (rs.getString ("Availability"))!= null? rs.getString ("Availability"): "-";
        		Manufacturer = rs.getString("Manufacturer");
        		Model = rs.getString("Model");
        		Watts = rs.getString("Watts");
        		SearchListModel.addElement(ProductID+"      "+Price+"      "+Availability+"      "
        				+ ""+Manufacturer+"      "+Model+"      "+Watts); 		   		
        	}	
        	break;
        case "Tower":
        	Header = "ProductID    Price    Availability    Manufacturer    Model    Dimensions";
        	SearchListModel.addElement(Header);
        	SearchListModel.addElement("_______________________________________________________________");
        	query = "SELECT * FROM tower JOIN product ON tower.TowerID = product.ProductID";
        	rs = st.executeQuery(query);
        	while(rs.next())
        	{
        		ProductID = rs.getString("ProductID");
        		Price = rs.getString("Price");
        		Availability = (rs.getString ("Availability"))!= null? rs.getString ("Availability"): "-";
        		Manufacturer = rs.getString("Manufacturer"); 
        		Model = rs.getString("Model");
        		Dimensions = rs.getString("Dimensions");
        		SearchListModel.addElement(ProductID+"      "+Price+"      "+Availability+"      "
        				+ ""+Manufacturer+"      "+Model+"      "+Dimensions); 		   		
        	}
        	break;
        case "Computer":
        	Header = "ProductID    Price    Availability    Manufacturer    Model    CPU_ID    GPU_ID    RAM_ID    PSU_ID    HardDisk_ID    MotherBoard_ID    Tower_ID";
        	SearchListModel.addElement(Header);
        	SearchListModel.addElement("______________________________________________________________________________________________________________________________");
        	query = "SELECT * FROM computer JOIN product ON computer.ComputerID = product.ProductID";
        	rs = st.executeQuery(query);
        	while(rs.next())
        	{
        		ProductID = rs.getString("ProductID");
        		Price = rs.getString("Price");
        		Availability = (rs.getString ("Availability"))!= null? rs.getString ("Availability"): "-";
        		Manufacturer = rs.getString("Manufacturer");
        		Model = rs.getString("Model");
        		CPUID = rs.getString("CPU_ID");
        		GPUID = rs.getString("GPU_ID");
        		RAMID = rs.getString("RAM_ID");
        		PSUID = rs.getString("PSU_ID");
        		HardDiskID = rs.getString("HardDisk_ID");
        		MotherBoardID = rs.getString("MotherBoard_ID");
        		TowerID = rs.getString("Tower_ID");
        		SearchListModel.addElement(ProductID+"      "+Price+"      "+Availability+"      "
        				+ ""+Manufacturer+"      "+Model+"      "+CPUID+"      "+GPUID+"      "
        				+RAMID+"      "+PSUID+"      "+HardDiskID+"      "+MotherBoardID+"      "
        				+TowerID); 		   		
        	}
        	break;
        default :
        	//System.out.println("null");
        	break;    
        }
        conn.close();
        st.close();
        rs.close();
        return SearchListModel;	
	}
}
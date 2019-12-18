
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Employee extends JFrame 
{

private JPanel contentPane;
	
	private static int Port = 3307;
	
    private String SQLusername = "Employee";
    private String SQLpassword = "Employee";
    private String SchemaName = "EshopDB";
    
    
    private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
    
    final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    DefaultListModel<String> SearchListModel = new DefaultListModel<String>();
    private String[] selectOptions = {"","Users","Orders","CPU","GPU","RAM","MotherBoard","HardDisk","PSU","Tower","Computer"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Employee frame = new Employee();
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Employee() 
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 643);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("E-shop  - Employee Mode"); 
		setIconImage(titleLogo);
		setResizable(false);
		
		JLabel lblNewLabel_1 = new JLabel("Category : Employee");
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
		comboBox.setBounds(383, 74, 109, 29);
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
					SearchListModel = search(SchemaName,choice,SearchListModel);
					SearchList.setModel(SearchListModel);
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		searchButton.setBounds(514, 74, 97, 29);
		contentPane.add(searchButton);
		
		JButton employeeSettingsButton = new JButton("Employee Settings");
		employeeSettingsButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				EmployeeSettings employeeSettings = null;
				try 
				{
					employeeSettings = new EmployeeSettings(SchemaName,SQLusername,SQLpassword,Port);
				} catch (ClassNotFoundException | SQLException e) 
				{
					//e.printStackTrace();
				}
				employeeSettings.setVisible(true);
			}
		});
		employeeSettingsButton.setBounds(10, 74, 146, 29);
		contentPane.add(employeeSettingsButton);
		
		JButton logOutButton = new JButton("Log Out");
		logOutButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					conn.close();
			        st.close();
			        //rs.close();
				}catch (SQLException e1)
				{
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null,
						"You are Successfully Logged Out.",
						"Log Out",JOptionPane.PLAIN_MESSAGE);          
        	    dispose();
			}
		});
		logOutButton.setBounds(514, 4, 97, 29);
		contentPane.add(logOutButton);
	}
	
	@SuppressWarnings("unused")
	public DefaultListModel<String> search(String SchemaName,String choice,DefaultListModel<String> SearchListModel) throws ClassNotFoundException, SQLException
	{
        String ProductID,Price,Availability,Model,Socket,Watts;
        String Manufacturer,Cores,Clock,Type,Capacity,Dimensions;
        String query,Header;
        String CPUID,GPUID,RAMID,PSUID,HardDiskID,MotherBoardID,TowerID,OrderID;
        String TotalValue,Progress,Username,Date;
        String password,email,phone,birthDate,iban,gender,street,city,postalCode,firstName,lastName;
        
        switch (choice)
        {
        case "Users":
        	String username,Email,Phone,BirthDate,IBAN,Gender,PostalCode,FirstName,LastName;
        	Header = "Username               Email               Phone               BirthDate               IBAN               Gender               "
        			+ "PostalCode               FirstName               LastName";
        	SearchListModel.addElement(Header);
        	SearchListModel.addElement("________________________________________________________________________________________"
        			+ "___________________________");
        	query = "SELECT * FROM user";
        	rs = st.executeQuery(query);
        	while(rs.next())
        	{
        		username = rs.getString("username");
        		Email = rs.getString("Email");
        		Phone = rs.getString("Phone");
        		BirthDate = rs.getString("BirthDate");
        		IBAN = rs.getString("IBAN");
        		Gender = rs.getString("Gender");
        		PostalCode = rs.getString("PostalCode");
        		FirstName = rs.getString("FirstName");
        		LastName = rs.getString("LastName");
        		SearchListModel.addElement(username+"      "+Email+"      "+Phone+"      "
        				+ ""+BirthDate+"      "+IBAN+"      "+Gender+"      "+PostalCode+"      "+FirstName
        				+"      "+LastName); 		   		
        	}
        	break;
        case "Orders":
        	Header = "OrderID    Total Value    Progress    Username    Date";
        	SearchListModel.addElement(Header);
        	SearchListModel.addElement("__________________________________________");
        	query = "SELECT * FROM orders";
        	rs = st.executeQuery(query);
        	while(rs.next())
        	{
        		OrderID = rs.getString("OrderID");
        		TotalValue = rs.getString("TotalValue");
        		Progress = rs.getString("Progress");
        		Username = rs.getString("Username");
        		Date = rs.getString("Date");
        		SearchListModel.addElement(OrderID+"      "+TotalValue+"      "+Progress+"      "
        				+ ""+Username+"      "+Date); 		   		
        	}	
        	break;
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
        return SearchListModel;	
	}
}
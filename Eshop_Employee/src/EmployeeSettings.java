
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class EmployeeSettings extends JFrame
{

	private JPanel contentPane;
	
	private static Connection conn;
	private static Statement st;
	@SuppressWarnings("unused")
	private static int rs;
	private static ResultSet rs2;
	
	private JTextField orderIdChangeProgressField;
	private String[] progressOptions = {"","Initial","Waiting","Done"};
	private String[] productOptions = {"","CPU","GPU","RAM","MotherBoard","HardDisk","PSU","Tower","Computer"};
	private String[] hardDiskOptions = {"SSD","HDD"};
	private JTextField productIdUpdateStockField;
	private JTextField availabilityUpdateStockField;
	private JTextField productIdInsertField;
	private JTextField priceField;
	private JTextField availabilityField;
	private JTextField manufacturerField;
	private JTextField modelField;
	private JTextField clockCapacitySocketWattsDimensionsCPUIDfield;
	private JTextField coresGPUIDfield;
	private JTextField RAMIDfield;
	private JTextField motherboardIDfield;
	private JTextField harddiskIDfield;
	private JTextField PSUIDfield;
	private JTextField towerIDfield;
	
	public EmployeeSettings(String SchemaName,String SQLusername,String SQLpassword,int Port) throws ClassNotFoundException, SQLException  
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:"+Port+"/"+SchemaName,SQLusername,SQLpassword);
	        st=conn.createStatement();
		}catch(ClassNotFoundException | SQLException e)
		{
			//e.printStackTrace();
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null,
					"An Unexpected Error Happened.",
					"ERROR",JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		Image titleLogo = new ImageIcon(this.getClass().getResource("TitleImage.png")).getImage();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 514, 654);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Employee Settings"); 
		setIconImage(titleLogo);
		setResizable(false);
		
		JLabel orderIDlabel = new JLabel("OrderID :");
		orderIDlabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		orderIDlabel.setBounds(24, 24, 87, 26);
		contentPane.add(orderIDlabel);
		
		orderIdChangeProgressField = new JTextField();
		orderIdChangeProgressField.setBounds(24, 61, 106, 22);
		contentPane.add(orderIdChangeProgressField);
		orderIdChangeProgressField.setColumns(10);
		
		JLabel progressLabel = new JLabel("Progress :");
		progressLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		progressLabel.setBounds(182, 26, 87, 22);
		contentPane.add(progressLabel);
		
		JComboBox<Object> comboBox = new JComboBox<Object>(progressOptions);
		comboBox.setBounds(182, 61, 98, 22);
		contentPane.add(comboBox); 
		
		JButton changeProgressButton = new JButton("Change Progress");
		changeProgressButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String option = comboBox.getSelectedItem().toString();		
				comboBox.setSelectedItem("");
				if(option.equals("")==true || orderIdChangeProgressField.getText().equals("")==true )
				{
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null,
							"The Progress and the OrderID must not be null.",
							"ERROR",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String OrderID = orderIdChangeProgressField.getText();
				    //System.out.println(query);
					try 
					{
						String COUNT = null;
						String query = "SELECT COUNT(OrderID) FROM orders WHERE OrderID="+OrderID;
						rs2 = st.executeQuery(query);
						while(rs2.next())
						{
							COUNT = rs2.getString("COUNT(OrderID)");
						}
						if (COUNT.equals("0")==true) 
						{
							Toolkit.getDefaultToolkit().beep();
							JOptionPane.showMessageDialog(null,
									"The OrderID you inserted does not Exist.",
									"ERROR",JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							query = "UPDATE orders SET Progress='"+option+"' WHERE OrderID="+OrderID;
							rs = st.executeUpdate(query);
							Toolkit.getDefaultToolkit().beep();
							JOptionPane.showMessageDialog(null,
									"The Progress for OrderID : "+OrderID+" has changed to : "+option,
									"Success",JOptionPane.PLAIN_MESSAGE);
						}				
					} catch (SQLException e) 
					{
						//e.printStackTrace();
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null,
								"An Unexpected Error Happened.",
								"ERROR",JOptionPane.ERROR_MESSAGE);
					}	
				}
				orderIdChangeProgressField.setText("");
			}
		});
		changeProgressButton.setBounds(24, 98, 137, 33);
		contentPane.add(changeProgressButton);
		
		JLabel productIdLabel = new JLabel("ProductID :");
		productIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		productIdLabel.setBounds(24, 154, 92, 26);
		contentPane.add(productIdLabel);
		
		productIdUpdateStockField = new JTextField();
		productIdUpdateStockField.setColumns(10);
		productIdUpdateStockField.setBounds(24, 191, 106, 22);
		contentPane.add(productIdUpdateStockField);
		
		JLabel lblAvailability = new JLabel("Availability :");
		lblAvailability.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAvailability.setBounds(182, 156, 98, 22);
		contentPane.add(lblAvailability);
		
		availabilityUpdateStockField = new JTextField();
		availabilityUpdateStockField.setColumns(10);
		availabilityUpdateStockField.setBounds(182, 191, 106, 22);
		contentPane.add(availabilityUpdateStockField);
		
		JButton btnUpdateStock = new JButton("Update Stock");
		btnUpdateStock.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String Availability = availabilityUpdateStockField.getText();
				String ProductID = productIdUpdateStockField.getText();
				if((productIdUpdateStockField.getText().equals(""))==true)   
				{
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null,
							"The ProductID must not be null.",
							"ERROR",JOptionPane.ERROR_MESSAGE);
					productIdUpdateStockField.setText("");
					availabilityUpdateStockField.setText("");
				}
				else
				{		
					if((availabilityUpdateStockField.getText().equals(""))==true)
						Availability="NULL";			
					try 
					{
						String COUNT = null;
						String query = "SELECT COUNT(ProductID) FROM product WHERE ProductID="+ProductID;
						rs2 = st.executeQuery(query);
						while(rs2.next())
						{
							COUNT = rs2.getString("COUNT(ProductID)");
						}
						if (COUNT.equals("0")==true) 
						{
							Toolkit.getDefaultToolkit().beep();
							JOptionPane.showMessageDialog(null,
									"The ProductID you inserted does not Exist.",
									"ERROR",JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							query = "UPDATE product SET Availability="+Availability+" WHERE ProductID = "+ProductID;
							//System.out.println(query);
							rs = st.executeUpdate(query);
							Toolkit.getDefaultToolkit().beep();
							JOptionPane.showMessageDialog(null,
									"The Stock is Updated for ProductID : "+ProductID,
									"Success",JOptionPane.PLAIN_MESSAGE);
						}				
					} catch (SQLException e) 
					{
						//e.printStackTrace();
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null,
								"An Unexpected Error Happened.",
								"ERROR",JOptionPane.ERROR_MESSAGE);
						productIdUpdateStockField.setText("");
						availabilityUpdateStockField.setText("");
					}
					productIdUpdateStockField.setText("");
					availabilityUpdateStockField.setText("");
				}
			}
		});
		btnUpdateStock.setBounds(24, 235, 137, 33);
		contentPane.add(btnUpdateStock);
		
		JButton btnGetAdditionalInfo = new JButton("Get Additional Info");
		btnGetAdditionalInfo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{	
				String OrderID = orderIdChangeProgressField.getText();	
				if(OrderID.equals("")==true)
				{
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null,
							"The OrderID must not be null.",
							"ERROR",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try 
					{
						String COUNT = null; 
						String query = "SELECT COUNT(OrderID) FROM orders WHERE OrderID="+OrderID;
						rs2 = st.executeQuery(query);
						while(rs2.next())
						{
							COUNT = rs2.getString("COUNT(OrderID)");
						}
						if (COUNT.equals("0")==true) 
						{
							Toolkit.getDefaultToolkit().beep();
							JOptionPane.showMessageDialog(null,
									"The OrderID you inserted does not Exist.",
									"ERROR",JOptionPane.ERROR_MESSAGE);
						}
						else
						{	
							GetOrderAdditionalInfo getOrderAdditionalInfo = new GetOrderAdditionalInfo(SchemaName,SQLusername,SQLpassword,OrderID,Port);
							getOrderAdditionalInfo.setVisible(true);
						}
						
					} catch (SQLException | ClassNotFoundException e1) 
					{
						//e1.printStackTrace();
					}
				}
				orderIdChangeProgressField.setText("");
				comboBox.setSelectedItem("");
				
			}
		});
		btnGetAdditionalInfo.setBounds(182, 98, 159, 33);
		contentPane.add(btnGetAdditionalInfo);
		
		JLabel lblNewLabel_3 = new JLabel("_____________________________________________");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_3.setBounds(0, 0, 495, 17);
		contentPane.add(lblNewLabel_3);
		
		JLabel productIdLabel2 = new JLabel("ProductID :");
		productIdLabel2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		productIdLabel2.setBounds(24, 307, 92, 26);
		contentPane.add(productIdLabel2);
		
		productIdInsertField = new JTextField();
		productIdInsertField.setColumns(10);
		productIdInsertField.setBounds(24, 344, 106, 22);
		contentPane.add(productIdInsertField);
		
		JLabel lblType = new JLabel("Type :");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblType.setBounds(182, 307, 92, 26);
		contentPane.add(lblType);		
		
		JLabel label = new JLabel("_____________________________________________");
		label.setFont(new Font("Tahoma", Font.BOLD, 17));
		label.setBounds(0, 138, 495, 17);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("_____________________________________________");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		label_1.setBounds(0, 279, 495, 17);
		contentPane.add(label_1);
			
		JLabel priceLabel = new JLabel("Price :");
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceLabel.setBounds(24, 398, 57, 26);
		contentPane.add(priceLabel);
		priceLabel.setVisible(true);
		
		JLabel lblAvailability_1 = new JLabel("Availability :");
		lblAvailability_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAvailability_1.setBounds(124, 398, 92, 26);
		contentPane.add(lblAvailability_1);
		
		JLabel lblManufacturer = new JLabel("Manufacturer :");
		lblManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblManufacturer.setBounds(233, 398, 98, 26);
		contentPane.add(lblManufacturer);
		
		JLabel lblModel = new JLabel("Model :");
		lblModel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblModel.setBounds(346, 398, 92, 26);
		contentPane.add(lblModel);
		
		priceField = new JTextField();
		priceField.setColumns(10);
		priceField.setBounds(24, 435, 57, 22);
		contentPane.add(priceField);
		
		availabilityField = new JTextField();
		availabilityField.setColumns(10);
		availabilityField.setBounds(124, 435, 73, 22);
		contentPane.add(availabilityField);
		
		manufacturerField = new JTextField();
		manufacturerField.setColumns(10);
		manufacturerField.setBounds(233, 435, 87, 22);
		contentPane.add(manufacturerField);
		
		modelField = new JTextField();
		modelField.setColumns(10);
		modelField.setBounds(346, 435, 118, 22);
		contentPane.add(modelField);
		
		JLabel clockCapacitySocketWattsDimensionsCPUIDlabel = new JLabel("");
		clockCapacitySocketWattsDimensionsCPUIDlabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		clockCapacitySocketWattsDimensionsCPUIDlabel.setBounds(24, 476, 87, 26);
		contentPane.add(clockCapacitySocketWattsDimensionsCPUIDlabel);
		clockCapacitySocketWattsDimensionsCPUIDlabel.setVisible(false);
		
		JLabel coresTypeGPUIDfield = new JLabel("");
		coresTypeGPUIDfield.setFont(new Font("Tahoma", Font.PLAIN, 14));
		coresTypeGPUIDfield.setBounds(124, 476, 73, 26);
		contentPane.add(coresTypeGPUIDfield);
		coresTypeGPUIDfield.setVisible(false);
		
		clockCapacitySocketWattsDimensionsCPUIDfield = new JTextField();
		clockCapacitySocketWattsDimensionsCPUIDfield.setColumns(10);
		clockCapacitySocketWattsDimensionsCPUIDfield.setBounds(24, 511, 73, 22);
		contentPane.add(clockCapacitySocketWattsDimensionsCPUIDfield);
		clockCapacitySocketWattsDimensionsCPUIDfield.setVisible(false);
		
		coresGPUIDfield = new JTextField();
		coresGPUIDfield.setColumns(10);
		coresGPUIDfield.setBounds(124, 511, 73, 22);
		contentPane.add(coresGPUIDfield);
		coresGPUIDfield.setVisible(false);
		
		JLabel ramIDlabel = new JLabel("");
		ramIDlabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ramIDlabel.setBounds(233, 476, 92, 26);
		contentPane.add(ramIDlabel);
		ramIDlabel.setVisible(false);
		
		JLabel motherboardIDlabel = new JLabel("");
		motherboardIDlabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		motherboardIDlabel.setBounds(346, 476, 118, 26);
		contentPane.add(motherboardIDlabel);
		motherboardIDlabel.setVisible(false);
		
		JLabel harddiskIDlabel = new JLabel("");
		harddiskIDlabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		harddiskIDlabel.setBounds(24, 544, 92, 26);
		contentPane.add(harddiskIDlabel);
		harddiskIDlabel.setVisible(false);
		
		JLabel PSUIDlabel = new JLabel("");
		PSUIDlabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PSUIDlabel.setBounds(124, 544, 92, 26);
		contentPane.add(PSUIDlabel);
		PSUIDlabel.setVisible(false);
		
		JLabel towerIDlabel = new JLabel("");
		towerIDlabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		towerIDlabel.setBounds(233, 544, 92, 26);
		contentPane.add(towerIDlabel);
		towerIDlabel.setVisible(false);
		
		RAMIDfield = new JTextField();
		RAMIDfield.setColumns(10);
		RAMIDfield.setBounds(233, 511, 73, 22);
		contentPane.add(RAMIDfield);
		RAMIDfield.setVisible(false);
		
		motherboardIDfield = new JTextField();
		motherboardIDfield.setColumns(10);
		motherboardIDfield.setBounds(346, 511, 73, 22);
		contentPane.add(motherboardIDfield);
		motherboardIDfield.setVisible(false);
		
		harddiskIDfield = new JTextField();
		harddiskIDfield.setColumns(10);
		harddiskIDfield.setBounds(24, 582, 73, 22);
		contentPane.add(harddiskIDfield);
		harddiskIDfield.setVisible(false);
		
		PSUIDfield = new JTextField();
		PSUIDfield.setColumns(10);
		PSUIDfield.setBounds(124, 581, 73, 22);
		contentPane.add(PSUIDfield);
		PSUIDfield.setVisible(false);
		
		towerIDfield = new JTextField();
		towerIDfield.setColumns(10);
		towerIDfield.setBounds(233, 581, 73, 22);
		contentPane.add(towerIDfield);
		towerIDfield.setVisible(false);
		
		JComboBox<Object> comboBox_2 = new JComboBox<Object>(hardDiskOptions);
		comboBox_2.setBounds(124, 512, 73, 20);
		contentPane.add(comboBox_2);
		comboBox_2.setVisible(false);
		
		JComboBox<Object> comboBox_1 = new JComboBox<Object>(productOptions);
		comboBox_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String option = comboBox_1.getSelectedItem().toString();
				switch(option)
				{
				case "":
					clockCapacitySocketWattsDimensionsCPUIDlabel.setVisible(false);
					coresTypeGPUIDfield.setVisible(false);
					ramIDlabel.setVisible(false);
					motherboardIDlabel.setVisible(false);
					harddiskIDlabel.setVisible(false);
					PSUIDlabel.setVisible(false);
					towerIDlabel.setVisible(false);		
					clockCapacitySocketWattsDimensionsCPUIDfield.setVisible(false);
					coresGPUIDfield.setVisible(false);
					RAMIDfield.setVisible(false);
					motherboardIDfield.setVisible(false);
					harddiskIDfield.setVisible(false);
					PSUIDfield.setVisible(false);
					towerIDfield.setVisible(false);
					comboBox_2.setVisible(false);
					break;
				case "CPU":
					clockCapacitySocketWattsDimensionsCPUIDlabel.setVisible(true);
					coresTypeGPUIDfield.setVisible(true);
					clockCapacitySocketWattsDimensionsCPUIDlabel.setText("Clock :");
					coresTypeGPUIDfield.setText("Cores :");
					ramIDlabel.setVisible(false);
					motherboardIDlabel.setVisible(false);
					harddiskIDlabel.setVisible(false);
					PSUIDlabel.setVisible(false);
					towerIDlabel.setVisible(false);
					clockCapacitySocketWattsDimensionsCPUIDfield.setVisible(true);
					coresGPUIDfield.setVisible(true);
					RAMIDfield.setVisible(false);
					motherboardIDfield.setVisible(false);
					harddiskIDfield.setVisible(false);
					PSUIDfield.setVisible(false);
					towerIDfield.setVisible(false);
					comboBox_2.setVisible(false);
					break;
				case "GPU":
					clockCapacitySocketWattsDimensionsCPUIDlabel.setVisible(true);
					coresTypeGPUIDfield.setVisible(false);
					clockCapacitySocketWattsDimensionsCPUIDlabel.setText("Capacity :");
					ramIDlabel.setVisible(false);
					motherboardIDlabel.setVisible(false);
					harddiskIDlabel.setVisible(false);
					PSUIDlabel.setVisible(false);
					towerIDlabel.setVisible(false);
					clockCapacitySocketWattsDimensionsCPUIDfield.setVisible(true);
					coresGPUIDfield.setVisible(false);
					RAMIDfield.setVisible(false);
					motherboardIDfield.setVisible(false);
					harddiskIDfield.setVisible(false);
					PSUIDfield.setVisible(false);
					towerIDfield.setVisible(false);
					comboBox_2.setVisible(false);
					break;
				case "RAM":
					clockCapacitySocketWattsDimensionsCPUIDlabel.setVisible(true);
					coresTypeGPUIDfield.setVisible(false);
					clockCapacitySocketWattsDimensionsCPUIDlabel.setText("Capacity :");
					ramIDlabel.setVisible(false);
					motherboardIDlabel.setVisible(false);
					harddiskIDlabel.setVisible(false);
					PSUIDlabel.setVisible(false);
					towerIDlabel.setVisible(false);
					clockCapacitySocketWattsDimensionsCPUIDfield.setVisible(true);
					coresGPUIDfield.setVisible(false);
					RAMIDfield.setVisible(false);
					motherboardIDfield.setVisible(false);
					harddiskIDfield.setVisible(false);
					PSUIDfield.setVisible(false);
					towerIDfield.setVisible(false);
					comboBox_2.setVisible(false);
					break;
				case "MotherBoard":
					clockCapacitySocketWattsDimensionsCPUIDlabel.setVisible(true);
					coresTypeGPUIDfield.setVisible(false);
					clockCapacitySocketWattsDimensionsCPUIDlabel.setText("Socket :");
					ramIDlabel.setVisible(false);
					motherboardIDlabel.setVisible(false);
					harddiskIDlabel.setVisible(false);
					PSUIDlabel.setVisible(false);
					towerIDlabel.setVisible(false);
					clockCapacitySocketWattsDimensionsCPUIDfield.setVisible(true);
					coresGPUIDfield.setVisible(false);
					RAMIDfield.setVisible(false);
					motherboardIDfield.setVisible(false);
					harddiskIDfield.setVisible(false);
					PSUIDfield.setVisible(false);
					towerIDfield.setVisible(false);
					comboBox_2.setVisible(false);
					break;
				case "HardDisk":
					clockCapacitySocketWattsDimensionsCPUIDlabel.setVisible(true);
					coresTypeGPUIDfield.setVisible(true);
					clockCapacitySocketWattsDimensionsCPUIDlabel.setText("Capacity :");
					coresTypeGPUIDfield.setText("Type :");
					ramIDlabel.setVisible(false);
					motherboardIDlabel.setVisible(false);
					harddiskIDlabel.setVisible(false);
					PSUIDlabel.setVisible(false);
					towerIDlabel.setVisible(false);
					clockCapacitySocketWattsDimensionsCPUIDfield.setVisible(true);
					coresGPUIDfield.setVisible(false);
					comboBox_2.setVisible(true);
					RAMIDfield.setVisible(false);
					motherboardIDfield.setVisible(false);
					harddiskIDfield.setVisible(false);
					PSUIDfield.setVisible(false);
					towerIDfield.setVisible(false);
					break;
				case "PSU":
					clockCapacitySocketWattsDimensionsCPUIDlabel.setVisible(true);
					coresTypeGPUIDfield.setVisible(false);
					clockCapacitySocketWattsDimensionsCPUIDlabel.setText("Watts :");
					ramIDlabel.setVisible(false);
					motherboardIDlabel.setVisible(false);
					harddiskIDlabel.setVisible(false);
					PSUIDlabel.setVisible(false);
					towerIDlabel.setVisible(false);
					clockCapacitySocketWattsDimensionsCPUIDfield.setVisible(true);
					coresGPUIDfield.setVisible(false);
					RAMIDfield.setVisible(false);
					motherboardIDfield.setVisible(false);
					harddiskIDfield.setVisible(false);
					PSUIDfield.setVisible(false);
					towerIDfield.setVisible(false);
					comboBox_2.setVisible(false);
					break;
				case "Tower":
					clockCapacitySocketWattsDimensionsCPUIDlabel.setVisible(true);
					coresTypeGPUIDfield.setVisible(false);
					clockCapacitySocketWattsDimensionsCPUIDlabel.setText("Dimensions :");
					ramIDlabel.setVisible(false);
					motherboardIDlabel.setVisible(false);
					harddiskIDlabel.setVisible(false);
					PSUIDlabel.setVisible(false);
					towerIDlabel.setVisible(false);
					clockCapacitySocketWattsDimensionsCPUIDfield.setVisible(true);
					coresGPUIDfield.setVisible(false);
					RAMIDfield.setVisible(false);
					motherboardIDfield.setVisible(false);
					harddiskIDfield.setVisible(false);
					PSUIDfield.setVisible(false);
					towerIDfield.setVisible(false);
					comboBox_2.setVisible(false);
					break;
				case "Computer":
					comboBox_2.setVisible(false);
					clockCapacitySocketWattsDimensionsCPUIDlabel.setVisible(true);
					coresTypeGPUIDfield.setVisible(true);
					ramIDlabel.setVisible(true);
					motherboardIDlabel.setVisible(true);
					harddiskIDlabel.setVisible(true);
					PSUIDlabel.setVisible(true);
					towerIDlabel.setVisible(true);
					clockCapacitySocketWattsDimensionsCPUIDlabel.setText("CPU_ID :");
					coresTypeGPUIDfield.setText("GPU_ID :");
					ramIDlabel.setText("RAM_ID :");
					motherboardIDlabel.setText("MotherBoard_ID :");
					harddiskIDlabel.setText("HardDisk_ID :");
					PSUIDlabel.setText("PSU_ID :");
					towerIDlabel.setText("Tower_ID :");
					clockCapacitySocketWattsDimensionsCPUIDfield.setVisible(true);
					coresGPUIDfield.setVisible(true);
					RAMIDfield.setVisible(true);
					motherboardIDfield.setVisible(true);
					harddiskIDfield.setVisible(true);
					PSUIDfield.setVisible(true);
					towerIDfield.setVisible(true);
					break;
				}		
			}
		});
		comboBox_1.setBounds(182, 344, 118, 22);
		contentPane.add(comboBox_1);	
		
		JButton btnInsertProduct = new JButton("Insert Product");
		btnInsertProduct.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) 
			{
				if (productIdInsertField.getText().equals("")==true || priceField.getText().equals("")==true 
						|| availabilityField.getText().equals("")==true || manufacturerField.getText().equals("")==true
						|| modelField.getText().equals("")==true || comboBox_1.getSelectedItem().toString().equals("")==true)
				{
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null,
							"You Must Fill All The Fields.",
							"ERROR",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try 
					{	
						String ProductID,Availability,Choice,Price,Model,Manufacturer;
						String COUNT = null,query;
						ProductID = productIdInsertField.getText();
						Availability = availabilityField.getText();
						Price = priceField.getText();
						Model = modelField.getText();
						Manufacturer = manufacturerField.getText();
						Choice = comboBox_1.getSelectedItem().toString();
						
						int intTemp;
						double doubleTemp;
						
						intTemp = Integer.parseInt(ProductID);
						intTemp = Integer.parseInt(Availability);
						doubleTemp = Double.parseDouble(Price);
						
						switch (Choice)
						{
						case "CPU":
							String Clock = clockCapacitySocketWattsDimensionsCPUIDfield.getText();
							String Cores = coresGPUIDfield.getText();
							
							doubleTemp = Double.parseDouble(Clock);
							doubleTemp = Double.parseDouble(Cores);
							
							if (Clock.equals("") == true || Cores.equals("") == true)
							{
								Toolkit.getDefaultToolkit().beep();
								JOptionPane.showMessageDialog(null,
										"You Must Fill All The Fields.",
										"ERROR",JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								query = "SELECT COUNT(ProductID) FROM product WHERE ProductID="+ProductID;
								rs2 = st.executeQuery(query);
								while(rs2.next())
								{
									COUNT = rs2.getString("COUNT(ProductID)");
								}
								if ( COUNT.equals("1")==true)
								{
									Toolkit.getDefaultToolkit().beep();
									JOptionPane.showMessageDialog(null,
											"The Product With ProductID = "+ProductID+" Already Exists.",
											"ERROR",JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									query = "INSERT INTO product VALUES ('"+ProductID+"','"+Price+"','"+Availability+"','"
											+Manufacturer+"','"+Model+"')";
									rs = st.executeUpdate(query);
									query = "INSERT INTO cpus VALUES ('"+ProductID+"','"+Clock+"','"+Cores+"')";
									rs = st.executeUpdate(query);
									
									Toolkit.getDefaultToolkit().beep();
									JOptionPane.showMessageDialog(null,
											"Product Successfully Inserted.",
											"Success",JOptionPane.PLAIN_MESSAGE);
								}	
							}
							break;
						case "GPU":
							String Capacity = clockCapacitySocketWattsDimensionsCPUIDfield.getText();
							
							intTemp = Integer.parseInt(Capacity);
							
							if (Capacity.equals("") == true)
							{
								Toolkit.getDefaultToolkit().beep();
								JOptionPane.showMessageDialog(null,
										"You Must Fill All The Fields.",
										"ERROR",JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								query = "SELECT COUNT(ProductID) FROM product WHERE ProductID="+ProductID;
								rs2 = st.executeQuery(query);
								while(rs2.next())
								{
									COUNT = rs2.getString("COUNT(ProductID)");
								}
								if ( COUNT.equals("1")==true)
								{
									Toolkit.getDefaultToolkit().beep();
									JOptionPane.showMessageDialog(null,
											"The Product With ProductID = "+ProductID+" Already Exists.",
											"ERROR",JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									query = "INSERT INTO product VALUES ('"+ProductID+"','"+Price+"','"+Availability+"','"
											+Manufacturer+"','"+Model+"')";
									rs = st.executeUpdate(query);
									query = "INSERT INTO gpu VALUES ('"+ProductID+"','"+Capacity+"')";
									rs = st.executeUpdate(query);
									
									Toolkit.getDefaultToolkit().beep();
									JOptionPane.showMessageDialog(null,
											"Product Successfully Inserted.",
											"Success",JOptionPane.PLAIN_MESSAGE);
								}
								
							}
							break;
						case "RAM":
							Capacity = clockCapacitySocketWattsDimensionsCPUIDfield.getText();
							
							intTemp = Integer.parseInt(Capacity);
							
							if (Capacity.equals("") == true)
							{
								Toolkit.getDefaultToolkit().beep();
								JOptionPane.showMessageDialog(null,
										"You Must Fill All The Fields.",
										"ERROR",JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								query = "SELECT COUNT(ProductID) FROM product WHERE ProductID="+ProductID;
								rs2 = st.executeQuery(query);
								while(rs2.next())
								{
									COUNT = rs2.getString("COUNT(ProductID)");
								}
								if ( COUNT.equals("1")==true)
								{
									Toolkit.getDefaultToolkit().beep();
									JOptionPane.showMessageDialog(null,
											"The Product With ProductID = "+ProductID+" Already Exists.",
											"ERROR",JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									query = "INSERT INTO product VALUES ('"+ProductID+"','"+Price+"','"+Availability+"','"
											+Manufacturer+"','"+Model+"')";
									rs = st.executeUpdate(query);
									query = "INSERT INTO ram VALUES ('"+ProductID+"','"+Capacity+"')";
									rs = st.executeUpdate(query);
									
									Toolkit.getDefaultToolkit().beep();
									JOptionPane.showMessageDialog(null,
											"Product Successfully Inserted.",
											"Success",JOptionPane.PLAIN_MESSAGE);
								}
								
							}
							break;
						case "MotherBoard":
							String Socket = clockCapacitySocketWattsDimensionsCPUIDfield.getText();
							if (Socket.equals("") == true)
							{
								Toolkit.getDefaultToolkit().beep();
								JOptionPane.showMessageDialog(null,
										"You Must Fill All The Fields.",
										"ERROR",JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								query = "SELECT COUNT(ProductID) FROM product WHERE ProductID="+ProductID;
								rs2 = st.executeQuery(query);
								while(rs2.next())
								{
									COUNT = rs2.getString("COUNT(ProductID)");
								}
								if ( COUNT.equals("1")==true)
								{
									Toolkit.getDefaultToolkit().beep();
									JOptionPane.showMessageDialog(null,
											"The Product With ProductID = "+ProductID+" Already Exists.",
											"ERROR",JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									query = "INSERT INTO product VALUES ('"+ProductID+"','"+Price+"','"+Availability+"','"
											+Manufacturer+"','"+Model+"')";
									rs = st.executeUpdate(query);
									query = "INSERT INTO motherboard VALUES ('"+ProductID+"','"+Socket+"')";
									rs = st.executeUpdate(query);
									
									Toolkit.getDefaultToolkit().beep();
									JOptionPane.showMessageDialog(null,
											"Product Successfully Inserted.",
											"Success",JOptionPane.PLAIN_MESSAGE);
								}
								
							}
							break;
						case "PSU":
							String Watts = clockCapacitySocketWattsDimensionsCPUIDfield.getText();
							
							intTemp = Integer.parseInt(Watts);
							
							if (Watts.equals("") == true)
							{
								Toolkit.getDefaultToolkit().beep();
								JOptionPane.showMessageDialog(null,
										"You Must Fill All The Fields.",
										"ERROR",JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								query = "SELECT COUNT(ProductID) FROM product WHERE ProductID="+ProductID;
								rs2 = st.executeQuery(query);
								while(rs2.next())
								{
									COUNT = rs2.getString("COUNT(ProductID)");
								}
								if ( COUNT.equals("1")==true)
								{
									Toolkit.getDefaultToolkit().beep();
									JOptionPane.showMessageDialog(null,
											"The Product With ProductID = "+ProductID+" Already Exists.",
											"ERROR",JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									query = "INSERT INTO product VALUES ('"+ProductID+"','"+Price+"','"+Availability+"','"
											+Manufacturer+"','"+Model+"')";
									rs = st.executeUpdate(query);
									query = "INSERT INTO psu VALUES ('"+ProductID+"','"+Watts+"')";
									rs = st.executeUpdate(query);
									
									Toolkit.getDefaultToolkit().beep();
									JOptionPane.showMessageDialog(null,
											"Product Successfully Inserted.",
											"Success",JOptionPane.PLAIN_MESSAGE);
								}
								
							}
							break;
						case "Tower":
							String Dimensions = clockCapacitySocketWattsDimensionsCPUIDfield.getText();
							if (Dimensions.equals("") == true)
							{
								Toolkit.getDefaultToolkit().beep();
								JOptionPane.showMessageDialog(null,
										"You Must Fill All The Fields.",
										"ERROR",JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								query = "SELECT COUNT(ProductID) FROM product WHERE ProductID="+ProductID;
								rs2 = st.executeQuery(query);
								while(rs2.next())
								{
									COUNT = rs2.getString("COUNT(ProductID)");
								}
								if ( COUNT.equals("1")==true)
								{
									Toolkit.getDefaultToolkit().beep();
									JOptionPane.showMessageDialog(null,
											"The Product With ProductID = "+ProductID+" Already Exists.",
											"ERROR",JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									query = "INSERT INTO product VALUES ('"+ProductID+"','"+Price+"','"+Availability+"','"
											+Manufacturer+"','"+Model+"')";
									rs = st.executeUpdate(query);
									query = "INSERT INTO tower VALUES ('"+ProductID+"','"+Dimensions+"')";
									rs = st.executeUpdate(query);
									
									Toolkit.getDefaultToolkit().beep();
									JOptionPane.showMessageDialog(null,
											"Product Successfully Inserted.",
											"Success",JOptionPane.PLAIN_MESSAGE);
								}
								
							}
							break;
						case "HardDisk":
							Capacity = clockCapacitySocketWattsDimensionsCPUIDfield.getText();
							
							intTemp = Integer.parseInt(Capacity);
							
							String HardDiskType = comboBox_1.getSelectedItem().toString();
							if (Capacity.equals("") == true || HardDiskType.equals("") == true)
							{
								Toolkit.getDefaultToolkit().beep();
								JOptionPane.showMessageDialog(null,
										"You Must Fill All The Fields.",
										"ERROR",JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								query = "SELECT COUNT(ProductID) FROM product WHERE ProductID="+ProductID;
								rs2 = st.executeQuery(query);
								while(rs2.next())
								{
									COUNT = rs2.getString("COUNT(ProductID)");
								}
								if ( COUNT.equals("1")==true)
								{
									Toolkit.getDefaultToolkit().beep();
									JOptionPane.showMessageDialog(null,
											"The Product With ProductID = "+ProductID+" Already Exists.",
											"ERROR",JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									query = "INSERT INTO product VALUES ('"+ProductID+"','"+Price+"','"+Availability+"','"
											+Manufacturer+"','"+Model+"')";
									rs = st.executeUpdate(query);
									query = "INSERT INTO harddisk VALUES ('"+ProductID+"','"+Capacity+"','"+HardDiskType+"')";
									rs = st.executeUpdate(query);
									
									Toolkit.getDefaultToolkit().beep();
									JOptionPane.showMessageDialog(null,
											"Product Successfully Inserted.",
											"Success",JOptionPane.PLAIN_MESSAGE);
								}		
							}
							break;
						case "Computer":
							String CPU_ID = clockCapacitySocketWattsDimensionsCPUIDfield.getText();
							String GPU_ID = coresGPUIDfield.getText();
							String RAM_ID = RAMIDfield.getText();
							String MotherBoard_ID = motherboardIDfield.getText();
							String HardDisk_ID = harddiskIDfield.getText();
							String PSU_ID = PSUIDfield.getText();
							String Tower_ID = towerIDfield.getText();
							if (CPU_ID.equals("") == true || GPU_ID.equals("") == true
									|| RAM_ID.equals("") == true || MotherBoard_ID.equals("") == true
									|| HardDisk_ID.equals("") == true || PSU_ID.equals("") == true
									|| Tower_ID.equals("") == true)
							{
								Toolkit.getDefaultToolkit().beep();
								JOptionPane.showMessageDialog(null,
										"You Must Fill All The Fields.",
										"ERROR",JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								intTemp = Integer.parseInt(CPU_ID);
								intTemp = Integer.parseInt(GPU_ID);
								intTemp = Integer.parseInt(RAM_ID);
								intTemp = Integer.parseInt(PSU_ID);
								intTemp = Integer.parseInt(Tower_ID);
								intTemp = Integer.parseInt(HardDisk_ID);
								intTemp = Integer.parseInt(MotherBoard_ID);
								
								
								query = "SELECT COUNT(ProductID) FROM product WHERE ProductID="+ProductID;
								rs2 = st.executeQuery(query);
								while(rs2.next())
									COUNT = rs2.getString("COUNT(ProductID)");
								if ( COUNT.equals("1")==true)
								{
									Toolkit.getDefaultToolkit().beep();
									JOptionPane.showMessageDialog(null,
											"The Product With ProductID = "+ProductID+" Already Exists.",
											"ERROR",JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									intTemp = 0;
									
									query = "SELECT COUNT(CPUID) FROM cpus WHERE CPUID="+CPU_ID;
									rs2 = st.executeQuery(query);
									while(rs2.next())
										intTemp += Integer.parseInt(rs2.getString("COUNT(CPUID)"));
									
									query = "SELECT COUNT(GPUID) FROM gpu WHERE GPUID="+GPU_ID;
									rs2 = st.executeQuery(query);
									while(rs2.next())
										intTemp += Integer.parseInt(rs2.getString("COUNT(GPUID)"));
									
									query = "SELECT COUNT(RAMID) FROM ram WHERE RAMID="+RAM_ID;
									rs2 = st.executeQuery(query);
									while(rs2.next())
										intTemp += Integer.parseInt(rs2.getString("COUNT(RAMID)"));
									
									query = "SELECT COUNT(PSUID) FROM psu WHERE PSUID="+PSU_ID;
									rs2 = st.executeQuery(query);
									while(rs2.next())
										intTemp += Integer.parseInt(rs2.getString("COUNT(PSUID)"));
									
									query = "SELECT COUNT(TowerID) FROM tower WHERE TowerID="+Tower_ID;
									rs2 = st.executeQuery(query);
									while(rs2.next())
										intTemp += Integer.parseInt(rs2.getString("COUNT(TowerID)"));
									
									query = "SELECT COUNT(MotherBoardID) FROM motherboard WHERE MotherBoardID="+MotherBoard_ID;
									rs2 = st.executeQuery(query);
									while(rs2.next())
										intTemp += Integer.parseInt(rs2.getString("COUNT(MotherBoardID)"));
									
									query = "SELECT COUNT(HardDiskID) FROM cpus WHERE HardDiskID="+HardDisk_ID;
									rs2 = st.executeQuery(query);
									while(rs2.next())
										intTemp += Integer.parseInt(rs2.getString("COUNT(HardDiskID)"));

									/*
									 * if a component ( cpu,ram ..etc ) doesn't exist then don't insert the
									 * computer into EshopDB
									 */
									if (intTemp>0)
									{
										Toolkit.getDefaultToolkit().beep();
										JOptionPane.showMessageDialog(null,
												"An Unexpected Error Happened.",
												"ERROR",JOptionPane.ERROR_MESSAGE);
										productIdInsertField.setText("");
										priceField.setText("");
										availabilityField.setText("");
										manufacturerField.setText("");
										modelField.setText("");
										clockCapacitySocketWattsDimensionsCPUIDfield.setText("");
										coresGPUIDfield.setText("");
										RAMIDfield.setText("");
										motherboardIDfield.setText("");
										harddiskIDfield.setText("");
										PSUIDfield.setText("");
										towerIDfield.setText("");
										comboBox_1.setSelectedItem("");
										return;
									}
									
									query = "INSERT INTO product VALUES ('"+ProductID+"','"+Price+"','"+Availability+"','"
											+Manufacturer+"','"+Model+"')";
									rs = st.executeUpdate(query);
									query = "INSERT INTO computer VALUES ('"+ProductID+"','"+RAM_ID+"','"+PSU_ID+"','"
											+Tower_ID+"','"+GPU_ID+"','"+MotherBoard_ID+"','"+CPU_ID+"','"+HardDisk_ID+"')";
									rs = st.executeUpdate(query);
									query = "UPDATE product SET Availability=NULL WHERE ProductID = "+CPU_ID;
									rs = st.executeUpdate(query);
									query = "UPDATE product SET Availability=NULL WHERE ProductID = "+GPU_ID;
									rs = st.executeUpdate(query);
									query = "UPDATE product SET Availability=NULL WHERE ProductID = "+RAM_ID;
									rs = st.executeUpdate(query);
									query = "UPDATE product SET Availability=NULL WHERE ProductID = "+PSU_ID;
									rs = st.executeUpdate(query);
									query = "UPDATE product SET Availability=NULL WHERE ProductID = "+Tower_ID;
									rs = st.executeUpdate(query);
									query = "UPDATE product SET Availability=NULL WHERE ProductID = "+MotherBoard_ID;
									rs = st.executeUpdate(query);
									query = "UPDATE product SET Availability=NULL WHERE ProductID = "+HardDisk_ID;
									rs = st.executeUpdate(query);
									
									Toolkit.getDefaultToolkit().beep();
									JOptionPane.showMessageDialog(null,
											"Product Successfully Inserted.",
											"Success",JOptionPane.PLAIN_MESSAGE);
								}		
							}
							break;		
						}
						productIdInsertField.setText("");
						priceField.setText("");
						availabilityField.setText("");
						manufacturerField.setText("");
						modelField.setText("");
						clockCapacitySocketWattsDimensionsCPUIDfield.setText("");
						coresGPUIDfield.setText("");
						RAMIDfield.setText("");
						motherboardIDfield.setText("");
						harddiskIDfield.setText("");
						PSUIDfield.setText("");
						towerIDfield.setText("");
						comboBox_1.setSelectedItem("");
					} catch (SQLException | NumberFormatException e)  
					{
						//e.printStackTrace();
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null,
								"An Unexpected Error Happened.",
								"ERROR",JOptionPane.ERROR_MESSAGE);
						productIdInsertField.setText("");
						priceField.setText("");
						availabilityField.setText("");
						manufacturerField.setText("");
						modelField.setText("");
						clockCapacitySocketWattsDimensionsCPUIDfield.setText("");
						coresGPUIDfield.setText("");
						RAMIDfield.setText("");
						motherboardIDfield.setText("");
						harddiskIDfield.setText("");
						PSUIDfield.setText("");
						towerIDfield.setText("");
						comboBox_1.setSelectedItem("");
						
					}
				}
			}
		});
		btnInsertProduct.setBounds(336, 333, 159, 33);
		contentPane.add(btnInsertProduct);
	}
}
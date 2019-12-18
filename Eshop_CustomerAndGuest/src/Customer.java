
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Customer extends JFrame 
{
	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
	@SuppressWarnings("unused")
	private static int rs2;
	
	@SuppressWarnings("unused")
	private JTextField textField_1;
	final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	DefaultListModel<String> SearchListModel = new DefaultListModel<String>(); 
	DefaultListModel<String> CartListModel = new DefaultListModel<String>();
	DefaultListModel<String> HeaderListModel = new DefaultListModel<String>();
	private Vector<Integer> Products = new Vector<>();
	private Vector<Integer> Quantities = new Vector<>();
	private Vector<Double> Prices = new Vector<>();
	
	String comboBoxValue,textFieldValue;
	private String[] SearchOptions = {"","My Orders","CPU","GPU","RAM","MotherBoard","HardDisk","PSU","Tower","Computer"};
	
	private JTextField productIdField;
	private JTextField QuantityField;
	private static DecimalFormat df2 = new DecimalFormat("#.00");
	private JTextField OrderIDfield;
	
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Customer(String SchemaName,String SQLusername,String SQLpassword,int Port) throws ClassNotFoundException, SQLException 
	{
		Image titleLogo = new ImageIcon(this.getClass().getResource("TitleImage.png")).getImage();
		setIconImage(titleLogo);
		setTitle("E-shop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 654);
		setResizable(false);
		getContentPane().setLayout(null);
		
		Products.clear();
		Prices.clear();
		Quantities.clear();
		df2.setRoundingMode(RoundingMode.UP);
		
		Components(SchemaName,SQLusername,SQLpassword,Port);

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void Components(String SchemaName,String SQLusername,String SQLpassword,int Port) throws ClassNotFoundException, SQLException
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 11, 370, 14);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setText("Username : "+SQLusername);
		
		JLabel lblNewLabel_1 = new JLabel("Category : Customer");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 31, 131, 14);
		getContentPane().add(lblNewLabel_1);		
		
		JComboBox<Object> comboBox = new JComboBox<Object>(SearchOptions);
		comboBox.setBounds(403, 112, 131, 25);
		getContentPane().add(comboBox);
		
		JList SearchList = new JList();
		SearchList.setBounds(153, 602, 58, 19);
		getContentPane().add(SearchList);
		
		JList CartList = new JList();
		CartList.setBounds(221, 602, 58, 19);
		getContentPane().add(CartList);
		
		JList HeaderList = new JList();
		HeaderList.setEnabled(false);
		HeaderList.setBounds(20, 602, 63, 12);
		getContentPane().add(HeaderList);
	
		JScrollPane scrollPane = new JScrollPane(SearchList);
		scrollPane.setBounds(10, 145, 635, 170);
		getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane(CartList);
		scrollPane_1.setBounds(10, 408, 635, 154);
		getContentPane().add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane(HeaderList);
		scrollPane_2.setBounds(10, 384, 635, 25);
		getContentPane().add(scrollPane_2);
		HeaderListModel.addElement("ProductID           Quantity           Price");
		HeaderList.setModel(HeaderListModel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(10, 319, 81, 55);
		Image myCart = new ImageIcon(this.getClass().getResource("myCart.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(myCart));
		getContentPane().add(lblNewLabel_2);
		
		Calendar cal = Calendar.getInstance();
		JLabel lastLogin = new JLabel("Last Login : " + sdf.format(cal.getTime()));
		lastLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lastLogin.setBounds(10, 51, 201, 14);
		getContentPane().add(lastLogin);	
		
		JLabel myCartLabel = new JLabel("My Cart");
		myCartLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		myCartLabel.setBounds(101, 327, 94, 47);
		getContentPane().add(myCartLabel);
		
		JLabel totalPriceLabel = new JLabel("Total Price : ");
		totalPriceLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		totalPriceLabel.setBounds(179, 569, 192, 30);
		getContentPane().add(totalPriceLabel);
		
		JLabel productIdLabel = new JLabel("ProductID :");
		productIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		productIdLabel.setBounds(319, 326, 81, 19);
		getContentPane().add(productIdLabel);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantity.setBounds(433, 326, 81, 19);
		getContentPane().add(lblQuantity);
		
		JLabel itemsInCartLabel = new JLabel("Items In Cart : 0");
		itemsInCartLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		itemsInCartLabel.setBounds(10, 573, 159, 23);
		getContentPane().add(itemsInCartLabel);
		
		JOptionPane.showMessageDialog(null,
			    "Welcome Customer : "+SQLusername,
			    "Connection Success",
			    JOptionPane.PLAIN_MESSAGE);
		
		
		/*
		 *           BUTTONS
		 * 
		 */
		
		JButton logOutButton = new JButton("Log Out");
		logOutButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(null,
						"You are Successfully Logged Out.",
						"Log Out",JOptionPane.PLAIN_MESSAGE);          
        	    dispose();
			}
		});
		logOutButton.setBounds(556, 11, 89, 30);
		getContentPane().add(logOutButton);
		
		productIdField = new JTextField();
		productIdField.setBounds(319, 354, 86, 20);
		getContentPane().add(productIdField);
		productIdField.setColumns(10);
		
		QuantityField = new JTextField();
		QuantityField.setColumns(10);
		QuantityField.setBounds(433, 354, 86, 20);
		getContentPane().add(QuantityField);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String choice = comboBox.getSelectedItem().toString();
				try 
				{
					SearchListModel.removeAllElements();
					SearchList.setModel(SearchListModel);
					
					SearchListModel = search(SchemaName,choice,SearchListModel,SQLusername,SQLpassword);
					SearchList.setModel(SearchListModel);
					
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		searchButton.setBounds(553, 112, 92, 27);
		getContentPane().add(searchButton);
		
		JButton addToCartButton = new JButton("Add to Cart");
		addToCartButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if ( productIdField.getText().equals("")==true || QuantityField.getText().equals("")==true)
				{
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null,
							"The ProductID and Quantity must not be null.",
							"ERROR",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					int ProductID,Quantity;
					try
					{
						ProductID = Integer.parseInt(productIdField.getText());
						Quantity = Integer.parseInt(QuantityField.getText());
						if(Quantity > 0)
						{
							String query = "SELECT COUNT(ProductID) FROM product WHERE ProductID="+ProductID;
							String COUNT = null;
							rs = st.executeQuery(query);
							while(rs.next())
							{
								COUNT = rs.getString("COUNT(ProductID)");
							}
							if (COUNT.equals("0")==true)
							{
								Toolkit.getDefaultToolkit().beep();
								JOptionPane.showMessageDialog(null,
										"The Product you inserted does not exist.",
										"ERROR",JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								String Availability = null;
								query = "SELECT Availability FROM product WHERE ProductID="+ProductID;
								rs = st.executeQuery(query);
								while(rs.next())
								{
									Availability = (rs.getString ("Availability"))!= null? rs.getString ("Availability"): "-";
								}
								if( Availability.equals("-")==true)
								{
									Toolkit.getDefaultToolkit().beep();
									JOptionPane.showMessageDialog(null,
											"The Product you inserted \nis not Available for sale.",
											"ERROR",JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									String Price = null; 
									double Price_as_double;
									query = "SELECT Price FROM product WHERE ProductID="+ProductID;
									rs = st.executeQuery(query);
									while(rs.next())
									{
										Price = rs.getString("Price");
									}
									Price_as_double = Double.parseDouble(Price); 
									
									boolean flag=false;
									int position = 0;
									for(int i=0;i<Products.size();i++)
									{
										if(ProductID==Products.get(i))
										{
											flag=true;
											position=i;
										}
									}
									if(!flag)
									{
										Products.add(ProductID);
										Quantities.add(Quantity);
										Prices.add(Quantity*Price_as_double);	
									}
									else
									{
										Quantities.set(position, Quantities.get(position) + Quantity);
										Prices.set(position, Quantities.get(position)*Price_as_double);
									}

									CartListModel.removeAllElements();
									CartList.setModel(CartListModel);
									for(int i=0;i<Products.size();i++)
										CartListModel.addElement("       "+Products.get(i)+"                       "+Quantities.get(i)+"                  "+Prices.get(i));
									CartList.setModel(CartListModel);
									
									int itemsInCart=0;
									double totalPrice=0;
									for(int i=0;i<Products.size();i++)
									{
										itemsInCart += Quantities.get(i);
										totalPrice += Prices.get(i);
									}
									totalPriceLabel.setText("Total Price : "+totalPrice);
									itemsInCartLabel.setText("Items In Cart : "+itemsInCart);
								}				
							}
						}
						else
						{
							Toolkit.getDefaultToolkit().beep();
							JOptionPane.showMessageDialog(null,
									"The Quantity must be a positive number.",
									"ERROR",JOptionPane.ERROR_MESSAGE);
						}
					}catch(NumberFormatException | SQLException e1)
					{
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null,
								"Something Went Wrong.\nPlease Try Again.",
								"ERROR",JOptionPane.ERROR_MESSAGE);
						productIdField.setText("");
						QuantityField.setText("");
						return ;
					}		
				}
				productIdField.setText("");
				QuantityField.setText("");
	
			}
		});
		addToCartButton.setBounds(538, 344, 107, 30);
		getContentPane().add(addToCartButton);
		
		JButton buyButton = new JButton("Buy");
		buyButton.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) 
			{
				if(CartListModel.isEmpty())
				{
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null,
							"Your Cart Is Empty.",
							"ERROR",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String query,ProductID;
					int Quantity,Availability = 0;
					boolean flag = false;
					for(int i=0;i<Products.size();i++)
					{	
						ProductID = Products.get(i).toString();
						Quantity = Quantities.get(i); 
						query = "SELECT Availability FROM product WHERE ProductID="+ProductID;
						try 
						{
							rs = st.executeQuery(query);
							while(rs.next())
							{
								Availability = Integer.parseInt(rs.getString("Availability"));
							}
							if( Availability < Quantity)
							{
								flag = true;
								break;
							}	
						} catch (SQLException e1) 
						{
							e1.printStackTrace();
						}		
					}
					if(flag)
					{
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null,
								"You Asked For a Product in Quantity More\nThan our Availability.",
								"ERROR",JOptionPane.ERROR_MESSAGE);
					}
					else // EVERYTHING OK WITH THE ORDER
					{
						double totalPrice=0.0;
						for(int i=0;i<Products.size();i++)
							totalPrice += Prices.get(i);
						
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Date date = new Date();
						String orderDate = dateFormat.format(date).toString();
						
						int maxOrderID = 0;
						query = "SELECT MAX(OrderID) FROM orders";
						try 
						{
							rs = st.executeQuery(query);
							while(rs.next())
							{
								maxOrderID = Integer.parseInt(rs.getString("MAX(OrderID)"));
							}
							query = "INSERT INTO orders VALUES ('"+(maxOrderID+1)+"','"+totalPrice+"','Initial','"
							        +SQLusername+"','"+orderDate+"')";
							//System.out.println(query);
							rs2 = st.executeUpdate(query);
							
							for (int i=0;i<Products.size();i++)
							{
								query = "INSERT INTO order_contains_product VALUES ('"+Products.get(i)+"','"
							             +(maxOrderID+1)+"','"+Quantities.get(i)+"')";
								rs2 = st.executeUpdate(query);
							}
							
							// UPDATE STOCK
							
							UpdateStock updateStock = new UpdateStock(SchemaName,Products,Quantities,Port);
							
							Toolkit.getDefaultToolkit().beep();
							JOptionPane.showMessageDialog(null,
									"Thank You For Your Order.",
									"SUCCESS",JOptionPane.PLAIN_MESSAGE);		
							
						} catch (SQLException | ClassNotFoundException e1)  
						{
							//e1.printStackTrace();
							Toolkit.getDefaultToolkit().beep();
							JOptionPane.showMessageDialog(null,
									"An Unexpected Error Happened.",
									"ERROR",JOptionPane.ERROR_MESSAGE);
						}
						Products.clear();
						Quantities.clear();
						Prices.clear();
						CartListModel.removeAllElements();
						CartList.setModel(CartListModel);
						totalPriceLabel.setText("Total Price : 0");
						itemsInCartLabel.setText("Items In Cart : 0");
						
					}
				}	
			}
		});
		buyButton.setBounds(556, 568, 89, 30);
		getContentPane().add(buyButton);
		
		JButton removeFromCartButton = new JButton("Remove From Cart");
		removeFromCartButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//System.out.println(CartList.getSelectedIndex());
				if( CartList.getSelectedIndex() == -1 )
				{
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null,
							"You Did Not Select Something\nFrom Your Cart.",
							"ERROR",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					int index = CartList.getSelectedIndex();
					Products.remove(index);
					Quantities.remove(index);
					Prices.remove(index);
					CartListModel.removeElementAt(index);
					CartList.setModel(CartListModel);
					int itemsInCart=0;
					double totalPrice=0;
					for(int i=0;i<Products.size();i++)
					{
						itemsInCart += Quantities.get(i);
						totalPrice += Prices.get(i);
					}
					totalPriceLabel.setText("Total Price : "+totalPrice);
					itemsInCartLabel.setText("Items In Cart : "+itemsInCart);	
				}
			}
		});
		removeFromCartButton.setBounds(390, 568, 150, 30);
		getContentPane().add(removeFromCartButton);	
		
		JButton viewFullProfileButton = new JButton("View Full Profile");
		viewFullProfileButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ViewFullProfile viewFullProfile = null;
				try 
				{
					viewFullProfile = new ViewFullProfile(SchemaName,SQLusername,SQLpassword,Port);
				} catch (SQLException | ClassNotFoundException e1) 
				{
					//e1.printStackTrace();
				}
				viewFullProfile.setVisible(true);
			}
		}); 
		viewFullProfileButton.setBounds(10, 76, 131, 30);
		getContentPane().add(viewFullProfileButton);	
		
		OrderIDfield = new JTextField();
		OrderIDfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				OrderIDfield.setText("");
			}
		});
		OrderIDfield.setText("Insert The OrderID");
		OrderIDfield.setBounds(10, 114, 131, 23);
		getContentPane().add(OrderIDfield);
		OrderIDfield.setColumns(10);
		
		JButton OrderAdditionalInfo = new JButton("Get Order Info");
		OrderAdditionalInfo.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) 
			{	
				try
				{
					String OrderID = OrderIDfield.getText();
					String Count = null;
					int tmpOrderID = Integer.parseInt(OrderID);
					String query = "SELECT COUNT(OrderID) FROM orders WHERE username = '"+SQLusername+"' AND OrderID = "+OrderID;
					rs = st.executeQuery(query);
					while(rs.next())
					{
						Count = rs.getString("COUNT(OrderID)");
					}
					if (Count.equals("0")==true) 
					{
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null,
								"You Did Not Made An Order With This OrderID.",
								"ERROR",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						GetOrderAdditionalInfo getOrderAdditionalInfo = new GetOrderAdditionalInfo(SchemaName,SQLusername,SQLpassword,OrderID,Port);
						getOrderAdditionalInfo.setVisible(true);
					}		
				}catch(NumberFormatException | SQLException | ClassNotFoundException e)
				{
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null,
							"An Unexpected Error Happened.",
							"ERROR",JOptionPane.ERROR_MESSAGE);	
					OrderIDfield.setText("Insert The OrderID");
				}
				OrderIDfield.setText("Insert The OrderID");
			}
		});
		OrderAdditionalInfo.setBounds(153, 112, 131, 25);
		getContentPane().add(OrderAdditionalInfo);
	}
	
	@SuppressWarnings("unused")
	public DefaultListModel<String> search(String SchemaName,String choice,DefaultListModel<String> SearchListModel,String SQLusername,String SQLpassword) throws ClassNotFoundException, SQLException
	{   
        String ProductID,Price,Availability,Model,Socket,Watts;
        String Manufacturer,Cores,Clock,Type,Capacity,Dimensions;
        String query,Header;
        String CPUID,GPUID,RAMID,PSUID,HardDiskID,MotherBoardID,TowerID,OrderID;
        String TotalValue,Progress,Date;
        String password,email,phone,birthDate,iban,gender,street,city,postalCode,firstName,lastName;
        
        switch (choice)
        {
        case "My Orders":
        	Header = "OrderID    Total Value    Progress    Date";
        	SearchListModel.addElement(Header);
        	SearchListModel.addElement("___________________________________");
        	query = "SELECT * FROM orders WHERE Username='"+SQLusername+"'";
        	rs = st.executeQuery(query);
        	while(rs.next())
        	{
        		OrderID = rs.getString("OrderID");
        		TotalValue = rs.getString("TotalValue");
        		Progress = rs.getString("Progress");
        		Date = rs.getString("Date");
        		SearchListModel.addElement(OrderID+"      "+TotalValue+"      "+Progress+"      "
        				+"     "+Date); 		   		
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
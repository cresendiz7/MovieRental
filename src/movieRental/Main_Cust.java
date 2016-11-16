package movieRental;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import javax.swing.border.*;
import net.proteanit.sql.DbUtils;
import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;

public class Main_Cust extends JFrame {

	static Connection connection = null;
	private JPanel contentPane;
	private JTable tableViewEditCust;
	private JTextField tfUsername;
	private JPasswordField tfPassword;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfAge;
	private JTable tableViewMov;
	private JTextField tfUserID;
	private JTable tableCurrentUsername;
	private String current;
	public static JLabel lbCurrentUsernameCust;
	public static JLabel lbCurrentUserIDCust;
	private JTextField textField;
	private JTable tableCurrentRentals;
	private JTable tableCart;
	private JTextField tfRentMovieSearch;
	private JComboBox<String> comboBoxRentMov;
	private JTable tableCheckout;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Cust frame = new Main_Cust();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		//Delete Customer Cart on exit
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	        	Main_Cust.delCart();
	        }
	    }, "Shutdown-thread"));
	}
	
	public void refEditCustTbl(){
		try{
			String query = "SELECT * FROM customers WHERE userid = '"+ lbCurrentUserIDCust.getText() +"'";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableViewEditCust.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}	
	
		try{
			String query = "SELECT * FROM customers WHERE userid = ' " + lbCurrentUserIDCust.getText() + " ' ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
		
		while(rs.next())
		{
			tfUserID.setText(rs.getString("userid"));
			tfFirstName.setText(rs.getString("first_name"));
			tfLastName.setText(rs.getString("last_name"));
			tfAge.setText(rs.getString("age"));
			tfUsername.setText(rs.getString("username"));
			tfPassword.setText(rs.getString("password"));
		}
		
		rs.close();
		pst.close();
		
		}catch (Exception ex) { 
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	public void refAllMovTbl(){
		try{
			String query = "SELECT movieid as 'Movie ID', title as 'Title',description as 'Description',"
					+ " genre as 'Genre', release_year as 'Release Year', rental_rate as 'Rental Rate', "
					+ "rating as 'Rating', replacement_cost as 'Replacement Cost', length as 'Length (Minutes)' FROM movies";
				
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableViewMov.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void delCart(){
		try{
			String query = "TRUNCATE TABLE cart";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.execute();
			pst.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void refCartTbl(){
		try{
			String query = "SELECT userid as 'User ID', movieid as 'Movie ID', title as 'Title', rental_rate as 'Rental Rate', replacement_cost as 'Replacement Cost' FROM cart where userid = '"+ lbCurrentUserIDCust.getText() +"'";
				
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableCart.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void refCheckoutTbl(){
		try{
			String query = "SELECT userid as 'User ID', movieid as 'Movie ID', title as 'Title', rental_rate as 'Rental Rate', replacement_cost as 'Replacement Cost' FROM cart where userid = '"+ lbCurrentUserIDCust.getText() +"'";
				
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableCheckout.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void refCurrentRentalsTbl(){
		try{
			String query = "SELECT userid as 'Customer ID', movieid as 'Movie ID', rental_date as 'Date Rented', return_date as 'Return Date' FROM rentals where userid = '"+ lbCurrentUserIDCust.getText() +"'";
				
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableCurrentRentals.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void fillComboRentMov(){
		try{
			comboBoxRentMov.removeAllItems();
			comboBoxRentMov.addItem("movieid");
			comboBoxRentMov.addItem("title");
			comboBoxRentMov.addItem("genre");
			comboBoxRentMov.addItem("rating");
			comboBoxRentMov.addItem("rental_rate");
			comboBoxRentMov.addItem("release_year");
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	/**
	 * Create the frame.
	 */
	public Main_Cust() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main_Cust.class.getResource("/fortyeight/device-tv.png")));
		setTitle("Movie Project");
		connection = databaseConnection.dbConnection();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 969, 627);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Movie Project");
		lblNewLabel.setBounds(10, 11, 308, 57);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		contentPane.add(lblNewLabel);
		
		JPanel panelCards = new JPanel();
		panelCards.setBounds(152, 80, 805, 477);
		contentPane.add(panelCards);
		panelCards.setLayout(new CardLayout(0, 0));
		
		JPanel panelWelcome = new JPanel();
		panelWelcome.setBackground(SystemColor.textHighlight);
		panelCards.add(panelWelcome, "name_145839516092709");
		panelWelcome.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 70));
		panelWelcome.add(lblWelcome, BorderLayout.CENTER);
		
		JPanel panelCust = new JPanel();
		panelCust.setBackground(SystemColor.textHighlight);
		panelCards.add(panelCust, "Customers");
		panelCust.setLayout(null);
		
		JTabbedPane tabbedPaneCust = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneCust.setBounds(0, 0, 793, 471);
		panelCust.add(tabbedPaneCust);
		
		JPanel panelEditCust = new JPanel();
		tabbedPaneCust.addTab("Edit Profile", null, panelEditCust, null);
		panelEditCust.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panelEditCust.add(scrollPane_2);
		
		tfFirstName = new JTextField();
		tfFirstName.setColumns(10);
		tfFirstName.setBounds(125, 99, 145, 29);
		panelEditCust.add(tfFirstName);
		
		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		tfLastName.setBounds(125, 140, 145, 29);
		panelEditCust.add(tfLastName);
		
		tfAge = new JTextField();
		tfAge.setColumns(10);
		tfAge.setBounds(125, 263, 145, 29);
		panelEditCust.add(tfAge);
		
		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		tfUsername.setBounds(125, 181, 145, 29);
		panelEditCust.add(tfUsername);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(125, 222, 145, 29);
		panelEditCust.add(tfPassword);
		
		tableViewEditCust = new JTable();
		scrollPane_2.setViewportView(tableViewEditCust);
		
		JLabel label_9 = new JLabel("First Name:");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_9.setBounds(31, 98, 82, 29);
		panelEditCust.add(label_9);

		JLabel label_8 = new JLabel("Last Name:");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_8.setBounds(31, 139, 82, 29);
		panelEditCust.add(label_8);

		JLabel label_7 = new JLabel("Age:");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_7.setBounds(31, 262, 82, 29);
		panelEditCust.add(label_7);
		
		JLabel label_6 = new JLabel("Username:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_6.setBounds(31, 180, 82, 29);
		panelEditCust.add(label_6);
		
		JLabel label_5 = new JLabel("Password:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_5.setBounds(31, 221, 82, 29);
		panelEditCust.add(label_5);
		
		JButton button_1 = new JButton("Submit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				      String query3 = ("SELECT username from customers");
				      PreparedStatement pst3 = connection.prepareStatement(query3);
				      ResultSet rs3 = pst3.executeQuery();
				      tableCurrentUsername.setModel(DbUtils.resultSetToTableModel(rs3));
				      int row =0;
				      int column = 0;
				      current = (tableCurrentUsername.getModel().getValueAt(row,column).toString());
				      }catch(Exception ex){
				       JOptionPane.showMessageDialog(null,ex);
				      }
				    try{
				      String query2 = ("SELECT username from customers where username = ?");
				      PreparedStatement pst2 = connection.prepareStatement(query2);
				      pst2.setString(1, tfUsername.getText());
				      ResultSet rs2 = pst2.executeQuery();
				      if(rs2.next()){
				       if(current.equals(tfUsername.getText())){}
				       else{
				        JOptionPane.showMessageDialog(null, "Username already exists.");
				       return;
				       }
				      }
				      }catch(Exception ex){
				       JOptionPane.showMessageDialog(null,ex);
				      }
				try{
					String value0 = tfUserID.getText();
					String value1 = tfFirstName.getText();
					String value2 = tfLastName.getText();
					String value3 = tfAge.getText();
					String value4 = tfUsername.getText();
					String value5 = tfPassword.getText();
					
					String query = "UPDATE customers SET  first_name = '"+ value1 +"', last_name = '"+ value2 +
								   "', age = '"+ value3 +"', username = '"+ value4 +"', password = '"+ value5 +
								   "' WHERE userid = "+value0+" ";
					
					PreparedStatement pst = connection.prepareStatement(query);
					pst.execute();
				
					JOptionPane.showMessageDialog(null, "Profile Updated.");
					
				}catch (Exception ex) { 
					JOptionPane.showMessageDialog(null, ex);
				}
				refEditCustTbl();
				
			}
		});
		button_1.setBounds(282, 99, 102, 29);
		panelEditCust.add(button_1);
		
		JLabel lblEditCustomerInformation = new JLabel("Edit Profile Information:");
		lblEditCustomerInformation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEditCustomerInformation.setBounds(6, 6, 237, 28);
		panelEditCust.add(lblEditCustomerInformation);
		
		tfUserID = new JTextField();
		panelEditCust.add(tfUserID);
		
		JPanel panelMov = new JPanel();
		panelMov.setBackground(SystemColor.textHighlight);
		panelCards.add(panelMov, "Movies");
		panelMov.setLayout(null);
		
		JTabbedPane tabbedPaneMov = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneMov.setBounds(0, 0, 793, 471);
		panelMov.add(tabbedPaneMov);
		
		JPanel panelRentMovie = new JPanel();
		tabbedPaneMov.addTab("Rent Movie", null, panelRentMovie, null);
		panelRentMovie.setLayout(null);
		
		JPanel panelRentCards = new JPanel();
		panelRentCards.setBounds(0, 0, 793, 441);
		panelRentMovie.add(panelRentCards);
		panelRentCards.setLayout(new CardLayout(0, 0));
		
		JPanel panelSelectMov = new JPanel();
		panelRentCards.add(panelSelectMov);
		panelSelectMov.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(6, 48, 769, 347);
		panelSelectMov.add(scrollPane_4);
		
		tableViewMov = new JTable();
		scrollPane_4.setViewportView(tableViewMov);
		
		JPanel panelCart = new JPanel();
		panelRentCards.add(panelCart);
		panelCart.setLayout(null);
		
		JButton btnReturnToMovies = new JButton("Return to Movies");
		btnReturnToMovies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRentCards.removeAll();
				panelRentCards.add(panelSelectMov);
				panelRentCards.repaint();
				panelRentCards.revalidate();
			}
		});
		btnReturnToMovies.setBounds(6, 407, 119, 28);
		panelCart.add(btnReturnToMovies);
		
		JPanel panelCheckout = new JPanel();
		panelRentCards.add(panelCheckout, "name_40668533167542");
		panelCheckout.setLayout(null);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count= tableCart.getModel().getRowCount();
				if(count<1){
					JOptionPane.showMessageDialog(null, "You have no items in your cart.");
					return;
				}
				panelRentCards.removeAll();
				panelRentCards.add(panelCheckout);
				panelRentCards.repaint();
				panelRentCards.revalidate();
				refCheckoutTbl();
			}
		});
		btnCheckout.setBounds(697, 407, 90, 28);
		panelCart.add(btnCheckout);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 46, 781, 349);
		panelCart.add(scrollPane_1);
		
		tableCart = new JTable();
		scrollPane_1.setViewportView(tableCart);
		
		JButton btnRemoveFromCart = new JButton("Remove from Cart");
		btnRemoveFromCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int row = tableCart.getSelectedRow();
					String Table_click = (tableCart.getModel().getValueAt(row, 1).toString());
					
					String query = "DELETE FROM cart WHERE movieid = '"+Table_click+"' ";
					
					PreparedStatement pst = connection.prepareStatement(query);
					pst.execute();
				
				}catch (Exception ex) { 
					JOptionPane.showMessageDialog(null, "Select the movie you wish to remove from your cart.");
				}
				refCartTbl();
			}
		});
		btnRemoveFromCart.setBounds(137, 407, 128, 28);
		panelCart.add(btnRemoveFromCart);
		
		JLabel lblCart = new JLabel("Cart");
		lblCart.setHorizontalAlignment(SwingConstants.CENTER);
		lblCart.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblCart.setBounds(6, 6, 781, 39);
		panelCart.add(lblCart);
		
		JButton btnViewCart = new JButton("View Cart");
		btnViewCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelRentCards.removeAll();
				panelRentCards.add(panelCart);
				panelRentCards.repaint();
				panelRentCards.revalidate();
			}
		});
		btnViewCart.setBounds(685, 407, 90, 28);
		panelSelectMov.add(btnViewCart);
		
		JButton btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.setBounds(6, 407, 90, 28);
		panelSelectMov.add(btnAddToCart);
		
		JLabel label = new JLabel("Search Movie:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(6, 8, 174, 28);
		panelSelectMov.add(label);
		
		comboBoxRentMov = new JComboBox<String>();
		comboBoxRentMov.setBounds(190, 8, 145, 29);
		panelSelectMov.add(comboBoxRentMov);
		
		tfRentMovieSearch = new JTextField();
		tfRentMovieSearch.setColumns(10);
		tfRentMovieSearch.setBounds(345, 8, 145, 29);
		panelSelectMov.add(tfRentMovieSearch);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String value0 = (String)comboBoxRentMov.getSelectedItem();
					String value1 = tfRentMovieSearch.getText()+"%";
					String query = "SELECT movieid as 'Movie ID', title as 'Title',description as 'Description',"
							+ " genre as 'Genre', release_year as 'Release Year', rental_rate as 'Rental Rate', rating as 'Rating',"
							+ " replacement_cost as 'Replacement Cost', length as 'Length (Minutes)' FROM movies where "+ value0 +" LIKE '"+value1+"' ";
					
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tableViewMov.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					rs.close();

				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnSearch.setBounds(676, 8, 102, 29);
		panelSelectMov.add(btnSearch);
		
		
		JLabel lblCheckout = new JLabel("Checkout");
		lblCheckout.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckout.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblCheckout.setBounds(6, 6, 781, 39);
		panelCheckout.add(lblCheckout);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(6, 46, 781, 175);
		panelCheckout.add(scrollPane_3);
		
		tableCheckout = new JTable();
		scrollPane_3.setViewportView(tableCheckout);
		
		JButton btnReturnToCart = new JButton("Return to Cart");
		btnReturnToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRentCards.removeAll();
				panelRentCards.add(panelCart);
				panelRentCards.repaint();
				panelRentCards.revalidate();
			}
		});
		btnReturnToCart.setBounds(6, 407, 104, 28);
		panelCheckout.add(btnReturnToCart);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*int count = tableCheckout.getModel().getRowCount();
				
				try{
					int userid = Integer.parseInt(tableViewMov.getModel().getValueAt(row, 0).toString());
					int movieid = Integer.parseInt(tableViewMov.getModel().getValueAt(row, 1).toString());
					String title = (tableViewMov.getModel().getValueAt(row, 1).toString());
					String rent_rate = (tableViewMov.getModel().getValueAt(row, 5).toString());
					String return_date = (tableViewMov.getModel().getValueAt(row, 7).toString());
					
					String query = "INSERT INTO rentals (userid,movieid,rental_rate,return_date) VALUES (?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setInt(1, Integer.parseInt(lbCurrentUserIDCust.getText()));
					pst.setInt(2, movieid);
					pst.setString(3, rent_rate);
					pst.setString(4, return_date);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Transaction Complete");
					
					pst.close();
					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex);
				}
				delCart();
			  }*/
			}
		});
		btnSubmit.setBounds(697, 407, 90, 28);
		panelCheckout.add(btnSubmit);
		
		JLabel lblDaysToBe = new JLabel("Days to be Rented:");
		lblDaysToBe.setBounds(411, 233, 104, 16);
		panelCheckout.add(lblDaysToBe);
		
		JRadioButton radioButton = new JRadioButton("1");
		radioButton.setBounds(411, 254, 115, 18);
		panelCheckout.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("3");
		radioButton_1.setBounds(411, 284, 115, 18);
		panelCheckout.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("5");
		radioButton_2.setBounds(411, 314, 115, 18);
		panelCheckout.add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("7");
		radioButton_3.setBounds(411, 344, 115, 18);
		panelCheckout.add(radioButton_3);
		
		JLabel lblReturnDate = new JLabel("Return Date:");
		lblReturnDate.setBounds(601, 233, 104, 16);
		panelCheckout.add(lblReturnDate);
		
		textField_1 = new JTextField();
		textField_1.setBounds(601, 249, 122, 28);
		panelCheckout.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setBounds(443, 374, 55, 16);
		panelCheckout.add(lblBalance);
		
		textField_2 = new JTextField();
		textField_2.setBounds(443, 396, 122, 28);
		panelCheckout.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Customer Information:");
		lblNewLabel_2.setBounds(6, 233, 131, 16);
		panelCheckout.add(lblNewLabel_2);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(6, 255, 61, 16);
		panelCheckout.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(6, 285, 61, 16);
		panelCheckout.add(lblLastName);
		
		JLabel lblId_1 = new JLabel("ID:");
		lblId_1.setBounds(6, 315, 15, 16);
		panelCheckout.add(lblId_1);
		
		JLabel lblID = new JLabel("ID");
		lblID.setBounds(33, 315, 15, 16);
		panelCheckout.add(lblID);
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try{
						int row = tableViewMov.getSelectedRow();
						int movieid = Integer.parseInt(tableViewMov.getModel().getValueAt(row, 0).toString());
						String title = (tableViewMov.getModel().getValueAt(row, 1).toString());
						String rent_rate = (tableViewMov.getModel().getValueAt(row, 5).toString());
						String repl_cost = (tableViewMov.getModel().getValueAt(row, 7).toString());
						
						String query = "INSERT INTO cart (userid,movieid,title,rental_rate,replacement_cost) VALUES (?,?,?,?,?)";
						PreparedStatement pst = connection.prepareStatement(query);
						
						pst.setInt(1, Integer.parseInt(lbCurrentUserIDCust.getText()));
						pst.setInt(2, movieid);
						pst.setString(3, title);
						pst.setString(4, rent_rate);
						pst.setString(5, repl_cost);
						
						pst.execute();
						
						JOptionPane.showMessageDialog(null, "Added to Cart");
						
						pst.close();
						
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex);
					}
					refAllMovTbl();
					refCartTbl();
				  }
		});
		
		JPanel panelReturnMovie = new JPanel();
		tabbedPaneMov.addTab("Return Movie", null, panelReturnMovie, null);
		
		JPanel panelAccount = new JPanel();
		tabbedPaneMov.addTab("Account Summary", null, panelAccount, null);
		panelAccount.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Account Balance:");
		lblNewLabel_1.setBounds(6, 6, 94, 16);
		panelAccount.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(6, 25, 122, 28);
		panelAccount.add(textField);
		textField.setColumns(10);
		
		JLabel lblCurrentRentals = new JLabel("Current Rentals");
		lblCurrentRentals.setBounds(6, 65, 94, 16);
		panelAccount.add(lblCurrentRentals);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 93, 366, 162);
		panelAccount.add(scrollPane);
		
		tableCurrentRentals = new JTable();
		scrollPane.setViewportView(tableCurrentRentals);
		
		JPanel ButtonMenu = new JPanel();
		ButtonMenu.setBackground(SystemColor.textHighlight);
		ButtonMenu.setBounds(10, 79, 130, 478);
		contentPane.add(ButtonMenu);
		
		JButton btnCustomers = new JButton("Edit Profile");
		btnCustomers.setBounds(10, 5, 100, 90);
		btnCustomers.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCustomers.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCustomers.setIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/user-male.png")));
		btnCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelCards.removeAll();
				panelCards.add(panelCust);
				panelCards.repaint();
				panelCards.revalidate();
				refEditCustTbl();
			}
		
		});
		
		JButton btnMovies = new JButton("Movies");
		btnMovies.setBounds(10, 107, 100, 90);
		btnMovies.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMovies.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnMovies.setIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/device-tv.png")));
		btnMovies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCards.removeAll();
				panelCards.add(panelMov);
				panelCards.repaint();
				panelCards.revalidate();
				refAllMovTbl();
				refCartTbl();
				refCurrentRentalsTbl();
				fillComboRentMov();
			}
		});
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(10, 415, 100, 57);
		btnLogout.setIcon(new ImageIcon(Main_Cust.class.getResource("/twentyfour/sign-ban.png")));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action = JOptionPane.showConfirmDialog(null, "Are you sure want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
				if(action == 0){
					delCart();
					contentPane.setVisible(false);
					dispose();
					Login.main(null);
			}
			}
		});
		ButtonMenu.setLayout(null);
		ButtonMenu.add(btnCustomers);
		ButtonMenu.add(btnMovies);
		ButtonMenu.add(btnLogout);
		
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelCards.removeAll();
				panelCards.add(panelWelcome);
				panelCards.repaint();
				panelCards.revalidate();
			}
		});
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 569, 963, 29);
		contentPane.add(panel);
		
		JLabel lblCurrentlyLoggedIn = new JLabel("Currently Logged in as Customer");
		lblCurrentlyLoggedIn.setBounds(6, 7, 182, 16);
		panel.add(lblCurrentlyLoggedIn);
		
		JLabel label_1 = new JLabel("Username:");
		label_1.setBounds(200, 7, 73, 16);
		panel.add(label_1);
		
		lbCurrentUsernameCust = new JLabel();
		lbCurrentUsernameCust.setBounds(264, 7, 112, 16);
		panel.add(lbCurrentUsernameCust);
		
		JLabel label_3 = new JLabel("ID:");
		label_3.setBounds(388, 7, 27, 16);
		panel.add(label_3);
		
		lbCurrentUserIDCust = new JLabel();
		lbCurrentUserIDCust.setText("22");
		lbCurrentUserIDCust.setBounds(408, 7, 55, 16);
		panel.add(lbCurrentUserIDCust);
		
		tableCurrentUsername = new JTable();
		contentPane.add(tableCurrentUsername);
	}
}
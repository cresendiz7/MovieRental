package movieRental;

import java.awt.*;
import java.util.*;
import java.util.Date;
import java.awt.event.*;
import java.math.BigDecimal;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;
import net.proteanit.sql.DbUtils;
import com.toedter.calendar.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Main_Cust extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4395387123685247355L;
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
	private JTable tableRentalHistory;
	private JTable tableCart;
	private JTextField tfRentMovieSearch;
	private JComboBox<String> comboBoxRentMov;
	private JTable tableCheckout;
	private JTextField tfBalance;
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblID;
	private JDateChooser ReturnDateChooser;
	private JTable tableCurrentRentals;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
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
			lblID.setText(rs.getString("userid"));
			tfFirstName.setText(rs.getString("first_name"));
			lblFirstName.setText(rs.getString("first_name"));
			tfLastName.setText(rs.getString("last_name"));
			lblLastName.setText(rs.getString("last_name"));
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
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	public static void delCart(){
		try{
			String query = "TRUNCATE TABLE cart";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.execute();
			pst.close();

		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
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
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	public void refRentalHistoryTbl(){
		try{
			String query = "SELECT rentid as 'Rental ID', movieid as 'Movie ID', rental_date as 'Date Rented', return_date as 'Return Date', returned as 'Returned?' FROM rentals where userid = '"+ lbCurrentUserIDCust.getText() +"'";

			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableRentalHistory.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	public void refCurrentRentalsTbl(){
		try{
			String query = "SELECT rentid as 'Rental ID', movieid as 'Movie ID', rental_date as 'Date Rented', return_date as 'Return Date', returned as 'Returned?' FROM rentals where userid = '"+ lbCurrentUserIDCust.getText() +"' AND returned = 'NO'";

			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableCurrentRentals.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
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
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	public void CalculateBalance(){
		try{
			//Calculate total rent amount 
			int rowcount = tableCheckout.getModel().getRowCount();
			BigDecimal newbalance = BigDecimal.ZERO;
			for(int row =0; row < rowcount; row++){
				try{
					newbalance = newbalance.add(new BigDecimal(tableCheckout.getModel().getValueAt(row, 3).toString()));
				}catch(Exception ex){
				JOptionPane.showMessageDialog(null, ex);
				}				
			}
			
			//Calculate number of days to be rented
			String return_date = ((JTextField)ReturnDateChooser.getDateEditor().getUiComponent()).getText();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d, yyyy");
			format = format.withLocale( Locale.US );
			LocalDate date = LocalDate.parse(return_date, format);
			LocalDate today = LocalDate.now();
			int days = (int) ChronoUnit.DAYS.between(today,date);
			BigDecimal days2 = BigDecimal.valueOf(days);
			
			//Calculate total balance 
			BigDecimal total_cost = newbalance.multiply(days2);
			
			//Set Balance into textfield
			tfBalance.setText(total_cost.toString());

		}catch(Exception ex){
			
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
		setBackground(Color.DARK_GRAY);
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

		JPanel panelCards = new JPanel();
		panelCards.setBounds(0, 80, 963, 490);
		contentPane.add(panelCards);
		panelCards.setLayout(new CardLayout(0, 0));

		JPanel panelWelcome = new JPanel();
		panelWelcome.setBackground(Color.DARK_GRAY);
		panelCards.add(panelWelcome, "name_145839516092709");
		panelWelcome.setLayout(new BorderLayout(0, 0));
		
		JPanel panelEditCust = new JPanel();
		panelEditCust.setBackground(Color.LIGHT_GRAY);
		panelCards.add(panelEditCust, "name_42682709774829");
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
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.DARK_GRAY);
		button_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
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
		
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
		Date date = Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		JPanel panelMov = new JPanel();
		panelMov.setBackground(Color.DARK_GRAY);
		panelCards.add(panelMov, "Movies");
		panelMov.setLayout(null);
		
		JPanel panelMovCards = new JPanel();
		panelMovCards.setBounds(0, 30, 963, 460);
		panelMov.add(panelMovCards);
		panelMovCards.setLayout(new CardLayout(0, 0));
		
				JPanel panelRentMovie = new JPanel();
				panelMovCards.add(panelRentMovie, "name_45118157024435");
				panelRentMovie.setBackground(Color.LIGHT_GRAY);
				panelRentMovie.setLayout(null);
				
				JPanel panelRentCards = new JPanel();
				panelRentCards.setBounds(0, 0, 963, 460);
				panelRentMovie.add(panelRentCards);
				panelRentCards.setLayout(new CardLayout(0, 0));
				
		JPanel panelSelectMov = new JPanel();
		panelSelectMov.setBackground(Color.LIGHT_GRAY);
		panelRentCards.add(panelSelectMov);
		panelSelectMov.setLayout(null);		
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(6, 48, 951, 347);
		panelSelectMov.add(scrollPane_4);				
		
		tableViewMov = new JTable();
		tableViewMov.setSelectionForeground(Color.BLACK);
		tableViewMov.setSelectionBackground(Color.LIGHT_GRAY);
		tableViewMov.setForeground(Color.WHITE);
		tableViewMov.setBackground(Color.GRAY);
		scrollPane_4.setViewportView(tableViewMov);						
		
		JPanel panelCart = new JPanel();
		panelCart.setBackground(Color.LIGHT_GRAY);
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
		panelCheckout.setBackground(Color.LIGHT_GRAY);
		panelRentCards.add(panelCheckout);
		panelCheckout.setLayout(null);														
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count= tableCart.getModel().getRowCount();
				if(count<1){
					JOptionPane.showMessageDialog(null, "You have no items in your cart.");
					return;
				}
				refCheckoutTbl();
				panelRentCards.removeAll();
				panelRentCards.add(panelCheckout);
				panelRentCards.repaint();
				panelRentCards.revalidate();
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
		btnViewCart.setBounds(867, 407, 90, 28);
		panelSelectMov.add(btnViewCart);																										
		
		JButton btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.setBounds(6, 407, 90, 28);
		panelSelectMov.add(btnAddToCart);
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
						pst.close();

					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, "Select the movie you wish to add to your cart.");
					}
					refCartTbl();
				  }
		});																												
		
		JLabel label = new JLabel("Search Movie:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(6, 8, 174, 28);
		panelSelectMov.add(label);
		
		comboBoxRentMov = new JComboBox<String>();
		comboBoxRentMov.setBounds(140, 9, 145, 29);
		panelSelectMov.add(comboBoxRentMov);		
		
		tfRentMovieSearch = new JTextField();
		tfRentMovieSearch.setColumns(10);
		tfRentMovieSearch.setBounds(297, 9, 145, 29);
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
		btnSearch.setBounds(855, 9, 102, 29);
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
		
		ReturnDateChooser = new JDateChooser();
		ReturnDateChooser.setBounds(212, 262, 122, 28);
		ReturnDateChooser.setMinSelectableDate(date);
		ReturnDateChooser.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent ex) {
						CalculateBalance();
					}
			    });
		panelCheckout.add(ReturnDateChooser);																																										
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String empty = "";
				String date2 = ((JTextField)ReturnDateChooser.getDateEditor().getUiComponent()).getText();
				  if(date2.equals(empty)){
					  JOptionPane.showMessageDialog(null,"Must select a return date");
					  return;
					  }
				int action = JOptionPane.showConfirmDialog(null, "Has the customer paid?", "Confirm Payment", JOptionPane.YES_NO_OPTION);
				 if(action == 0){
					 int count = tableCheckout.getModel().getRowCount();
						int row = 0;
						while(row<count){
						try{
							LocalDate today = LocalDate.now();
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
							String date = today.format(formatter);
							int movieid = Integer.parseInt(tableCheckout.getModel().getValueAt(row, 1).toString());
							String return_date = ((JTextField)ReturnDateChooser.getDateEditor().getUiComponent()).getText();

							String query = "INSERT INTO rentals (userid,movieid,rental_date,return_date) VALUES (?,?,?,?)";
							PreparedStatement pst = connection.prepareStatement(query);

							pst.setInt(1, Integer.parseInt(lbCurrentUserIDCust.getText()));
							pst.setInt(2, movieid);
							pst.setString(3, date);
							pst.setString(4, return_date);

							pst.execute();
							pst.close();
							row++;

						}catch(Exception ex){
							JOptionPane.showMessageDialog(null, ex);
						}
						}
						
						JOptionPane.showMessageDialog(null, "Transaction Complete");
						delCart();
						ReturnDateChooser.setCalendar(null);
						tfBalance.setText("");
						refCurrentRentalsTbl();
						refRentalHistoryTbl();
						refCheckoutTbl();
						refCartTbl();
						panelRentCards.removeAll();
						panelRentCards.add(panelCart);
						panelRentCards.repaint();
						panelRentCards.revalidate();
				 }
			  }
		});																																												
		
				btnSubmit.setBounds(697, 407, 90, 28);
				panelCheckout.add(btnSubmit);
				
				JLabel lblReturnDate = new JLabel("Return Date:");
				lblReturnDate.setBounds(212, 233, 104, 16);
				panelCheckout.add(lblReturnDate);		
				
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setBounds(411, 232, 55, 16);
		panelCheckout.add(lblBalance);				
		
		tfBalance = new JTextField();
		tfBalance.setEditable(false);
		tfBalance.setBounds(408, 254, 122, 28);
		panelCheckout.add(tfBalance);
		tfBalance.setColumns(10);						
		
		JLabel lblNewLabel_2 = new JLabel("Customer Information:");
		lblNewLabel_2.setBounds(6, 233, 131, 16);
		panelCheckout.add(lblNewLabel_2);								
		
		lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(6, 255, 61, 16);
		panelCheckout.add(lblFirstName);										
		
		lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(6, 285, 61, 16);
		panelCheckout.add(lblLastName);																																																								
		
		JLabel lblId_1 = new JLabel("ID:");
		lblId_1.setBounds(6, 315, 15, 16);
		panelCheckout.add(lblId_1);
		
		lblID = new JLabel("ID");
		lblID.setBounds(33, 315, 15, 16);
		panelCheckout.add(lblID);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(411, 337, 122, 28);
		panelCheckout.add(textField);
		
		JLabel lblCashToBe = new JLabel("Cash to be paid:");
		lblCashToBe.setBounds(414, 315, 104, 16);
		panelCheckout.add(lblCashToBe);																																																								
		
		JPanel panelAccount = new JPanel();
		panelAccount.setBackground(Color.LIGHT_GRAY);
		panelMovCards.add(panelAccount, "name_45120651096660");
		panelAccount.setLayout(null);
		
		JLabel lblCurrentRentals = new JLabel("Rental History");
		lblCurrentRentals.setBounds(13, 253, 94, 16);
		panelAccount.add(lblCurrentRentals);		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 270, 390, 162);
		panelAccount.add(scrollPane);				
		
		tableRentalHistory = new JTable();
		scrollPane.setViewportView(tableRentalHistory);						
		
		JButton btnReturnMovie = new JButton("Return Movie");
		btnReturnMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int row = tableCurrentRentals.getSelectedRow();
					String movieid = (tableCurrentRentals.getModel().getValueAt(row, 1).toString());
					String rentid = (tableCurrentRentals.getModel().getValueAt(row, 0).toString());
					
					String query = "UPDATE rentals SET returned = 'YES' WHERE rentid = '"+ rentid +"' AND movieid = '"+ movieid +"' AND userid = '"+ lbCurrentUserIDCust.getText() +"'";
					
					PreparedStatement pst = connection.prepareStatement(query);
					pst.execute();
					pst.close();
				
				}catch (Exception ex) { 
					JOptionPane.showMessageDialog(null, "Please select the movie you wish to return");
				}
				refCurrentRentalsTbl();
				refRentalHistoryTbl();
			}
		});
		btnReturnMovie.setBounds(13, 195, 107, 23);
		panelAccount.add(btnReturnMovie);						
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 35, 390, 162);
		panelAccount.add(scrollPane_5);
		
		tableCurrentRentals = new JTable();
		scrollPane_5.setViewportView(tableCurrentRentals);
		
		JLabel lblCurrentRentals_1 = new JLabel("Current Rentals");
		lblCurrentRentals_1.setBounds(13, 15, 94, 16);
		panelAccount.add(lblCurrentRentals_1);						
		
		JButton btnNewButton = new JButton("Rent Movie");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setOpaque(true);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelMovCards.removeAll();
				panelMovCards.add(panelRentMovie);
				panelMovCards.repaint();
				panelMovCards.revalidate();
			}
		});
		btnNewButton.setBounds(0, 0, 90, 30);
		panelMov.add(btnNewButton);
		
		JButton btnAccount = new JButton("Account");
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelMovCards.removeAll();
				panelMovCards.add(panelAccount);
				panelMovCards.repaint();
				panelMovCards.revalidate();
			}
		});
		btnAccount.setBackground(Color.DARK_GRAY);
		btnAccount.setForeground(Color.WHITE);
		btnAccount.setOpaque(true);
		btnAccount.setBounds(90, 0, 90, 30);
		panelMov.add(btnAccount);

		JPanel ButtonMenu = new JPanel();
		ButtonMenu.setBackground(Color.DARK_GRAY);
		ButtonMenu.setBounds(0, 0, 963, 80);
		contentPane.add(ButtonMenu);

		JButton btnCustomers = new JButton("");
		btnCustomers.setFocusPainted(false);
		btnCustomers.setOpaque(true);
		btnCustomers.setForeground(Color.WHITE);
		btnCustomers.setBackground(Color.DARK_GRAY);
		btnCustomers.setBounds(603, 0, 120, 80);
		btnCustomers.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCustomers.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCustomers.setIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/user-male.png")));
		btnCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelCards.removeAll();
				panelCards.add(panelEditCust);
				panelCards.repaint();
				panelCards.revalidate();
				refEditCustTbl();
			}

		});

		JButton btnMovies = new JButton("");
		btnMovies.setFocusPainted(false);
		btnMovies.setOpaque(true);
		btnMovies.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnMovies.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMovies.setForeground(Color.WHITE);
		btnMovies.setBackground(Color.DARK_GRAY);
		btnMovies.setBounds(723, 0, 120, 80);
		btnMovies.setIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/device-tv.png")));
		btnMovies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCards.removeAll();
				panelCards.add(panelMov);
				panelCards.repaint();
				panelCards.revalidate();
				refAllMovTbl();
				refCartTbl();
				refEditCustTbl();
				refCurrentRentalsTbl();
				refRentalHistoryTbl();
				fillComboRentMov();
			}
		});

		JButton btnLogout = new JButton("");
		btnLogout.setFocusPainted(false);
		btnLogout.setOpaque(true);
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogout.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnLogout.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogout.setBounds(843, 0, 120, 80);
		btnLogout.setBackground(Color.DARK_GRAY);
		btnLogout.setIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/sign-ban48.png")));
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

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);
		panel.setBounds(0, 569, 963, 29);
		contentPane.add(panel);

		JLabel lblCurrentlyLoggedIn = new JLabel("Currently Logged in as Customer");
		lblCurrentlyLoggedIn.setForeground(Color.WHITE);
		lblCurrentlyLoggedIn.setBounds(6, 7, 182, 16);
		panel.add(lblCurrentlyLoggedIn);

		JLabel label_1 = new JLabel("Username:");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(200, 7, 73, 16);
		panel.add(label_1);

		lbCurrentUsernameCust = new JLabel();
		lbCurrentUsernameCust.setForeground(Color.WHITE);
		lbCurrentUsernameCust.setBounds(264, 7, 112, 16);
		panel.add(lbCurrentUsernameCust);

		JLabel label_3 = new JLabel("ID:");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(388, 7, 27, 16);
		panel.add(label_3);

		lbCurrentUserIDCust = new JLabel();
		lbCurrentUserIDCust.setForeground(Color.WHITE);
		lbCurrentUserIDCust.setText("22");
		lbCurrentUserIDCust.setBounds(408, 7, 55, 16);
		panel.add(lbCurrentUserIDCust);

		tableCurrentUsername = new JTable();
		contentPane.add(tableCurrentUsername);
	}
}
package movieRental;

import java.awt.*;
import java.util.*;
import java.util.Date;
import java.awt.event.*;
import java.math.BigDecimal;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;
import javax.swing.table.*;
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
	private JTextField tfCash;
	private BigDecimal total_cost;
	private LocalDate today = LocalDate.now();
	public static JLabel lbNameCust;
	public static JLabel lbCurrentCustAge;
	private String allCust;
	private String currentCust;

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
			String query;
			if(Integer.parseInt(lbCurrentCustAge.getText()) > 17){
			query = "SELECT movieid as 'Movie ID', title as 'Title',description as 'Description',"
					+ " genre as 'Genre', release_year as 'Release Year', rental_rate as 'Rental Rate', "
					+ "rating as 'Rating', length as 'Length (Minutes)' FROM movies";

			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableViewMov.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			}else if(Integer.parseInt(lbCurrentCustAge.getText()) == 17){
				query = "SELECT movieid as 'Movie ID', title as 'Title',description as 'Description',"
						+ " genre as 'Genre', release_year as 'Release Year', rental_rate as 'Rental Rate', "
						+ "rating as 'Rating', length as 'Length (Minutes)' FROM movies WHERE rating = 'G' OR rating = 'PG' OR rating = 'PG-13' OR rating = 'R'";

				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				tableViewMov.setModel(DbUtils.resultSetToTableModel(rs));
				pst.close();
				rs.close();
			}else if(Integer.parseInt(lbCurrentCustAge.getText()) < 17 && Integer.parseInt(lbCurrentCustAge.getText()) >= 13){
				query = "SELECT movieid as 'Movie ID', title as 'Title',description as 'Description',"
						+ " genre as 'Genre', release_year as 'Release Year', rental_rate as 'Rental Rate', "
						+ "rating as 'Rating', length as 'Length (Minutes)' FROM movies WHERE rating = 'G' OR rating = 'PG' OR rating = 'PG-13'";

				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				tableViewMov.setModel(DbUtils.resultSetToTableModel(rs));
				pst.close();
				rs.close();
			}else if(Integer.parseInt(lbCurrentCustAge.getText()) < 13){
				query = "SELECT movieid as 'Movie ID', title as 'Title',description as 'Description',"
						+ " genre as 'Genre', release_year as 'Release Year', rental_rate as 'Rental Rate', "
						+ "rating as 'Rating', length as 'Length (Minutes)' FROM movies WHERE rating = 'G' OR rating = 'PG'";

				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				tableViewMov.setModel(DbUtils.resultSetToTableModel(rs));
				pst.close();
				rs.close();
			}

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
			tableCart.getModel();

			pst.close();
			rs.close();

		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	public void refRentalHistoryTbl(){
		try{
			String query = "SELECT rentid as 'Rental ID', movieid as 'Movie ID', rental_date as 'Date Rented', return_date as 'Return Date' FROM rentals where userid = '"+ lbCurrentUserIDCust.getText() +"'";

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
			String return_date;
			LocalDate return_date2;
			String query = "SELECT rentid as 'Rental ID', movieid as 'Movie ID', rental_date as 'Date Rented', return_date as 'Return Date' FROM rentals where userid = '"+ lbCurrentUserIDCust.getText() +"'";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				return_date = rs.getString("Return Date");
				DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d, yyyy");
				format = format.withLocale( Locale.US );
				return_date2 = LocalDate.parse(return_date, format);
			}
				if(return_date2.isAfter(today)){
					try{
						String query2 = "UPDATE rentals SET returned = 'NO'";
						PreparedStatement pst2 = connection.prepareStatement(query2);
						pst.executeQuery();
						pst2.execute();
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex);
					}
					}
				if(return_date2.isBefore(today)){
						try{
							String query3 = "UPDATE rentals SET returned = 'YES'";
							PreparedStatement pst3 = connection.prepareStatement(query3);
							pst.executeQuery();
							pst3.execute();
						}catch(Exception ex){
							JOptionPane.showMessageDialog(null, ex);
						}
						}
				if(return_date2.equals(today)){
					try{
						String query4 = "UPDATE rentals SET returned = 'NO'";
						PreparedStatement pst4 = connection.prepareStatement(query4);
						pst.executeQuery();
						pst4.execute();
						
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex);
					}
					}
			tableCurrentRentals.setModel(DbUtils.resultSetToTableModel(rs));
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
			int days = (int) ChronoUnit.DAYS.between(today,date);
			BigDecimal days2 = BigDecimal.valueOf(days);
			
			//Calculate total balance 
			total_cost = newbalance.multiply(days2);
			
			//Set Balance into textfield
			tfBalance.setText(total_cost.toString());

		}catch(Exception ex){
			
		}
	}
	
	public void Payment(){
		int count = tableCheckout.getModel().getRowCount();
		 int row = 0;
		 while(row<count){
			try{
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
			tfBalance.setText(null);
			tfCash.setText(null);
			refCurrentRentalsTbl();
			refRentalHistoryTbl();
			refCheckoutTbl();
			refCartTbl();
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
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelCards = new JPanel();
		panelCards.setBounds(0, 90, 963, 479);
		contentPane.add(panelCards);
		panelCards.setLayout(new CardLayout(0, 0));

		JPanel panelWelcome = new JPanel();
		panelWelcome.setBackground(Color.DARK_GRAY);
		panelCards.add(panelWelcome, "name_145839516092709");
		panelWelcome.setLayout(null);
		
		JLabel label_2 = new JLabel("Welcome");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 33));
		label_2.setBounds(0, 0, 164, 40);
		panelWelcome.add(label_2);
		
		lbNameCust = new JLabel("Name");
		lbNameCust.setForeground(Color.WHITE);
		lbNameCust.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 33));
		lbNameCust.setBounds(68, 48, 164, 40);
		panelWelcome.add(lbNameCust);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Main_Cust.class.getResource("/Logo/movie-vector-icons.png")));
		lblNewLabel.setBounds(0, 6, 963, 473);
		panelWelcome.add(lblNewLabel);
		
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
		Date date = Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		JPanel panelMov = new JPanel();
		panelMov.setBackground(Color.DARK_GRAY);
		panelCards.add(panelMov, "Movies");
		panelMov.setLayout(null);
		
		JPanel panelMovCards = new JPanel();
		panelMovCards.setBounds(0, 30, 963, 449);
		panelMov.add(panelMovCards);
		panelMovCards.setLayout(new CardLayout(0, 0));
		
		JPanel panelRentMovie = new JPanel();
		panelMovCards.add(panelRentMovie, "name_45118157024435");
		panelRentMovie.setBackground(Color.LIGHT_GRAY);
		panelRentMovie.setLayout(null);
		
		JPanel panelRentCards = new JPanel();
		panelRentCards.setBounds(0, 0, 963, 449);
		panelRentMovie.add(panelRentCards);
		panelRentCards.setLayout(new CardLayout(0, 0));		
				
		JPanel panelSelectMov = new JPanel();
		panelSelectMov.setBackground(Color.LIGHT_GRAY);
		panelRentCards.add(panelSelectMov);
		panelSelectMov.setLayout(null);		
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(0, 97, 963, 288);
		scrollPane_4.getViewport().setBackground(Color.GRAY);
		panelSelectMov.add(scrollPane_4);				
		
		tableViewMov = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 4318462929069833067L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
				}
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		        Component c = super.prepareRenderer(renderer, row, column);
		        if (c instanceof JComponent) {
		        	if(column == 1 || column == 2){
		        		JComponent jc = (JComponent) c;
			            jc.setToolTipText(getValueAt(row, column).toString());
		        	}else if(column == 0 || column == 3 || column == 4 || column == 5 || column == 6 || column == 7){
		        		JComponent jc = (JComponent) c;
		        	}
		        }
		        return c;
		    }
			};
		tableViewMov.setSelectionForeground(Color.BLACK);
		tableViewMov.setSelectionBackground(Color.LIGHT_GRAY);
		tableViewMov.setForeground(Color.WHITE);
		tableViewMov.setBackground(Color.GRAY);
		tableViewMov.getTableHeader().setReorderingAllowed(false);
		tableViewMov.getTableHeader().setResizingAllowed(false);
		tableViewMov.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_4.setViewportView(tableViewMov);						
		
		JPanel panelCart = new JPanel();
		panelCart.setBackground(Color.LIGHT_GRAY);
		panelRentCards.add(panelCart);
		panelCart.setLayout(null);								
		
		JButton btnReturnToMovies = new JButton("");
		btnReturnToMovies.setHorizontalAlignment(SwingConstants.LEADING);
		btnReturnToMovies.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReturnToMovies.setRolloverIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/sign-left2.png")));
		btnReturnToMovies.setContentAreaFilled(false);
		btnReturnToMovies.setIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/sign-left.png")));
		btnReturnToMovies.setBackground(Color.DARK_GRAY);
		btnReturnToMovies.setForeground(Color.WHITE);
		btnReturnToMovies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRentCards.removeAll();
				panelRentCards.add(panelSelectMov);
				panelRentCards.repaint();
				panelRentCards.revalidate();
			}
		});
		btnReturnToMovies.setBounds(0, 393, 100, 50);
		panelCart.add(btnReturnToMovies);												
				
		JPanel panelCheckout = new JPanel();
		panelCheckout.setBackground(Color.LIGHT_GRAY);
		panelRentCards.add(panelCheckout);
		panelCheckout.setLayout(null);														
		
		JButton btnCheckout = new JButton("");
		btnCheckout.setHorizontalAlignment(SwingConstants.TRAILING);
		btnCheckout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCheckout.setRolloverIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/money2.png")));
		btnCheckout.setContentAreaFilled(false);
		btnCheckout.setIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/money.png")));
		btnCheckout.setBackground(Color.DARK_GRAY);
		btnCheckout.setForeground(Color.WHITE);
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
		btnCheckout.setBounds(843, 393, 120, 50);
		panelCart.add(btnCheckout);																
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(Color.GRAY);
		scrollPane_1.setBounds(0, 46, 963, 342);
		scrollPane_1.getViewport().setBackground(Color.GRAY);
		panelCart.add(scrollPane_1);																		
		
		tableCart = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 4318462929069833067L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
				}
			};
		tableCart.setSelectionForeground(Color.BLACK);
		tableCart.setSelectionBackground(Color.LIGHT_GRAY);
		tableCart.setForeground(Color.WHITE);
		tableCart.setBackground(Color.GRAY);
		tableCart.getTableHeader().setReorderingAllowed(false);
		tableCart.getTableHeader().setResizingAllowed(false);
		tableCart.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(tableCart);																				
		
		JButton btnRemoveFromCart = new JButton("");
		btnRemoveFromCart.setHorizontalAlignment(SwingConstants.LEADING);
		btnRemoveFromCart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemoveFromCart.setRolloverIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/box-out2.png")));
		btnRemoveFromCart.setContentAreaFilled(false);
		btnRemoveFromCart.setIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/box-out.png")));
		btnRemoveFromCart.setForeground(Color.WHITE);
		btnRemoveFromCart.setBackground(Color.DARK_GRAY);
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
		btnRemoveFromCart.setBounds(131, 393, 173, 50);
		panelCart.add(btnRemoveFromCart);																						
		
		JLabel lblCart = new JLabel("Cart");
		lblCart.setOpaque(true);
		lblCart.setBackground(Color.GRAY);
		lblCart.setHorizontalAlignment(SwingConstants.CENTER);
		lblCart.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblCart.setBounds(0, 0, 963, 48);
		panelCart.add(lblCart);																								
		
		JLabel lblBack = new JLabel("Back");
		lblBack.setBounds(65, 410, 27, 16);
		panelCart.add(lblBack);
		
		JLabel lblRemoveFromCart = new JLabel("Remove from Cart");
		lblRemoveFromCart.setBounds(193, 410, 100, 16);
		panelCart.add(lblRemoveFromCart);
		
		JLabel lblCheckout_1 = new JLabel("Checkout");
		lblCheckout_1.setBounds(843, 410, 60, 16);
		panelCart.add(lblCheckout_1);
		
		JButton btnViewCart = new JButton("");
		btnViewCart.setHorizontalAlignment(SwingConstants.TRAILING);
		btnViewCart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnViewCart.setRolloverIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/sign-right2.png")));
		btnViewCart.setContentAreaFilled(false);
		btnViewCart.setIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/sign-right.png")));
		btnViewCart.setForeground(Color.WHITE);
		btnViewCart.setBackground(Color.DARK_GRAY);
		btnViewCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelRentCards.removeAll();
				panelRentCards.add(panelCart);
				panelRentCards.repaint();
				panelRentCards.revalidate();
			}
		});
		btnViewCart.setBounds(842, 393, 121, 50);
		panelSelectMov.add(btnViewCart);																										
		
		JButton btnAddToCart = new JButton("");
		btnAddToCart.setHorizontalAlignment(SwingConstants.LEADING);
		btnAddToCart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddToCart.setRolloverIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/box-in2.png")));
		btnAddToCart.setIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/box-in.png")));
		btnAddToCart.setContentAreaFilled(false);
		btnAddToCart.setForeground(Color.WHITE);
		btnAddToCart.setBackground(Color.DARK_GRAY);
		btnAddToCart.setBounds(0, 393, 137, 50);
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
		label.setBounds(6, 57, 174, 28);
		panelSelectMov.add(label);
		
		comboBoxRentMov = new JComboBox<String>();
		comboBoxRentMov.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBoxRentMov.setBackground(Color.GRAY);
		comboBoxRentMov.setBounds(142, 58, 145, 29);
		panelSelectMov.add(comboBoxRentMov);		
		
		tfRentMovieSearch = new JTextField();
		tfRentMovieSearch.setColumns(10);
		tfRentMovieSearch.setBounds(299, 58, 145, 29);
		panelSelectMov.add(tfRentMovieSearch);																																		
		
		JButton btnSearch = new JButton("");
		btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearch.setIcon(new ImageIcon(Main_Cust.class.getResource("/twentyfour/search.png")));
		btnSearch.setRolloverIcon(new ImageIcon(Main_Cust.class.getResource("/twentyfour/search-hover.png")));
		btnSearch.setContentAreaFilled(false);
		btnSearch.setBackground(Color.DARK_GRAY);
		btnSearch.setForeground(Color.WHITE);
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
		btnSearch.setBounds(446, 56, 30, 29);
		panelSelectMov.add(btnSearch);
		
		JLabel lblFindMovies = new JLabel("Find Movies");
		lblFindMovies.setOpaque(true);
		lblFindMovies.setBackground(Color.GRAY);
		lblFindMovies.setHorizontalAlignment(SwingConstants.CENTER);
		lblFindMovies.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblFindMovies.setBounds(0, 0, 963, 45);
		panelSelectMov.add(lblFindMovies);
		
		JLabel lblAddToCart = new JLabel("Add to Cart");
		lblAddToCart.setBounds(72, 413, 60, 16);
		panelSelectMov.add(lblAddToCart);
		
		JLabel lblViewCart = new JLabel("View Cart");
		lblViewCart.setBounds(848, 413, 60, 16);
		panelSelectMov.add(lblViewCart);
		
		JLabel lblCheckout = new JLabel("Checkout");
		lblCheckout.setBackground(Color.GRAY);
		lblCheckout.setOpaque(true);
		lblCheckout.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckout.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblCheckout.setBounds(0, 0, 963, 48);
		panelCheckout.add(lblCheckout);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBackground(Color.GRAY);
		scrollPane_3.setForeground(Color.WHITE);
		scrollPane_3.getViewport().setBackground(Color.GRAY);
		scrollPane_3.setBounds(0, 46, 963, 175);
		panelCheckout.add(scrollPane_3);																																						
		
		tableCheckout = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 4318462929069833067L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
				}
			};
		tableCheckout.setSelectionForeground(Color.BLACK);
		tableCheckout.setSelectionBackground(Color.LIGHT_GRAY);
		tableCheckout.setForeground(Color.WHITE);
		tableCheckout.setBackground(Color.GRAY);
		tableCheckout.getTableHeader().setReorderingAllowed(false);
		tableCheckout.getTableHeader().setResizingAllowed(false);
		tableCheckout.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_3.setViewportView(tableCheckout);
		
		JButton btnReturnToCart = new JButton("");
		btnReturnToCart.setHorizontalAlignment(SwingConstants.LEADING);
		btnReturnToCart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReturnToCart.setRolloverIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/sign-left2.png")));
		btnReturnToCart.setIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/sign-left.png")));
		btnReturnToCart.setContentAreaFilled(false);
		btnReturnToCart.setForeground(Color.WHITE);
		btnReturnToCart.setBackground(Color.DARK_GRAY);
		btnReturnToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRentCards.removeAll();
				panelRentCards.add(panelCart);
				panelRentCards.repaint();
				panelRentCards.revalidate();
			}
		});
		btnReturnToCart.setBounds(0, 393, 113, 50);
		panelCheckout.add(btnReturnToCart);
		
		ReturnDateChooser = new JDateChooser();
		ReturnDateChooser.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ReturnDateChooser.setForeground(Color.WHITE);
		ReturnDateChooser.setBackground(Color.GRAY);
		ReturnDateChooser.setBounds(466, 262, 131, 28);
		ReturnDateChooser.setMinSelectableDate(date);
		ReturnDateChooser.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent ex) {
						CalculateBalance();
					}
			    });
		panelCheckout.add(ReturnDateChooser);																																										
		
		JButton btnSubmit = new JButton("");
		btnSubmit.setHorizontalAlignment(SwingConstants.TRAILING);
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.setRolloverIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/basket2.png")));
		btnSubmit.setIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/basket.png")));
		btnSubmit.setContentAreaFilled(false);
		btnSubmit.setBackground(Color.DARK_GRAY);
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String empty = "";
				String date2 = ((JTextField)ReturnDateChooser.getDateEditor().getUiComponent()).getText();
				  if(date2.equals(empty)){
					  JOptionPane.showMessageDialog(null,"Must select a return date.");
					  return;
					  }
				  
				String cash3 = tfCash.getText();
				  if(cash3.equals(empty)){
					  JOptionPane.showMessageDialog(null,"Must insert cash value.");
					  return;
					  }
				  
				  BigDecimal cash = new BigDecimal(tfCash.getText());
				  boolean equal = total_cost.compareTo(cash) == 0;
				  boolean more = total_cost.compareTo(cash) == 1;
				  boolean change = total_cost.compareTo(cash) == -1;
				  if(equal){
					 Payment();
					 panelRentCards.removeAll();
					 panelRentCards.add(panelCart);
					 panelRentCards.repaint();
					 panelRentCards.revalidate();
				 }else if(more){
					 JOptionPane.showMessageDialog(null, "Sorry, but I'm going to need more money.");
				 }else if(change){
					 BigDecimal change2 = cash.subtract(total_cost);
					 JOptionPane.showMessageDialog(null, "Thank you for your business!\nHere is your change, $"+change2+".");
					 Payment();
					 panelRentCards.removeAll();
					 panelRentCards.add(panelCart);
					 panelRentCards.repaint();
					 panelRentCards.revalidate();
				 }
			}
		});																																												
		
				btnSubmit.setBounds(851, 393, 112, 50);
				panelCheckout.add(btnSubmit);
				
				JLabel lblReturnDate = new JLabel("Return Date:");
				lblReturnDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblReturnDate.setBounds(475, 233, 104, 16);
				panelCheckout.add(lblReturnDate);		
				
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBalance.setBounds(657, 233, 61, 16);
		panelCheckout.add(lblBalance);				
		
		tfBalance = new JTextField();
		tfBalance.setEditable(false);
		tfBalance.setBounds(657, 262, 122, 28);
		panelCheckout.add(tfBalance);
		tfBalance.setColumns(10);						
		
		JLabel lblNewLabel_2 = new JLabel("Customer Information:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(98, 233, 163, 16);
		panelCheckout.add(lblNewLabel_2);								
		
		lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(98, 262, 61, 16);
		panelCheckout.add(lblFirstName);										
		
		lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(98, 290, 61, 16);
		panelCheckout.add(lblLastName);																																																								
		
		JLabel lblId_1 = new JLabel("ID:");
		lblId_1.setBounds(98, 318, 15, 16);
		panelCheckout.add(lblId_1);
		
		lblID = new JLabel("ID");
		lblID.setBounds(125, 318, 15, 16);
		panelCheckout.add(lblID);
		
		tfCash = new JTextField();
		tfCash.setColumns(10);
		tfCash.setBounds(657, 330, 122, 28);
		panelCheckout.add(tfCash);
		
		JLabel lblCashToBe = new JLabel("Cash to be paid:");
		lblCashToBe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCashToBe.setBounds(657, 302, 122, 16);
		panelCheckout.add(lblCashToBe);																																																								
		
		JLabel lblBack_1 = new JLabel("Back");
		lblBack_1.setBounds(62, 410, 60, 16);
		panelCheckout.add(lblBack_1);
		
		JLabel lblSubmit = new JLabel("Submit");
		lblSubmit.setBounds(851, 410, 44, 16);
		panelCheckout.add(lblSubmit);
		
		JPanel panelAccount = new JPanel();
		panelAccount.setBackground(Color.LIGHT_GRAY);
		panelMovCards.add(panelAccount, "name_45120651096660");
		panelAccount.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 0, 0);
		panelAccount.add(scrollPane_2);
		
		tableViewEditCust = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 4318462929069833067L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
				}
			};
		scrollPane_2.setViewportView(tableViewEditCust);
		
		tfUserID = new JTextField();
		tfUserID.setBounds(0, 0, 0, 0);
		panelAccount.add(tfUserID);
		
		JLabel lblCurrentRentals = new JLabel("Rental History");
		lblCurrentRentals.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCurrentRentals.setBounds(13, 253, 107, 16);
		panelAccount.add(lblCurrentRentals);		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.GRAY);
		scrollPane.setBounds(10, 270, 456, 162);
		panelAccount.add(scrollPane);				
		
		tableRentalHistory = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 4318462929069833067L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
				}
			};
		tableRentalHistory.setBackground(Color.GRAY);
		tableRentalHistory.setForeground(Color.WHITE);
		tableRentalHistory.setSelectionBackground(Color.LIGHT_GRAY);
		tableRentalHistory.setSelectionForeground(Color.BLACK);
		tableRentalHistory.getTableHeader().setReorderingAllowed(false);
		tableRentalHistory.getTableHeader().setResizingAllowed(false);
		tableRentalHistory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableRentalHistory);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 35, 456, 162);
		scrollPane_5.getViewport().setBackground(Color.GRAY);
		panelAccount.add(scrollPane_5);
		
		tableCurrentRentals = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 4318462929069833067L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
				}
			};
		tableCurrentRentals.setSelectionForeground(Color.BLACK);
		tableCurrentRentals.setSelectionBackground(Color.LIGHT_GRAY);
		tableCurrentRentals.setForeground(Color.WHITE);
		tableCurrentRentals.setBackground(Color.GRAY);
		tableCurrentRentals.getTableHeader().setReorderingAllowed(false);
		tableCurrentRentals.getTableHeader().setResizingAllowed(false);
		tableCurrentRentals.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_5.setViewportView(tableCurrentRentals);
		
		JLabel lblCurrentRentals_1 = new JLabel("Current Rentals");
		lblCurrentRentals_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCurrentRentals_1.setBounds(13, 15, 114, 16);
		panelAccount.add(lblCurrentRentals_1);						
		
		JLabel lblEditCustomerInformation = new JLabel("Edit Profile Information:");
		lblEditCustomerInformation.setBounds(528, 8, 237, 28);
		panelAccount.add(lblEditCustomerInformation);
		lblEditCustomerInformation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel label_9 = new JLabel("First Name:");
		label_9.setBounds(528, 50, 82, 29);
		panelAccount.add(label_9);
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		tfFirstName = new JTextField();
		tfFirstName.setBounds(622, 48, 145, 29);
		panelAccount.add(tfFirstName);
		tfFirstName.setColumns(10);
		
		JButton button_1 = new JButton("");
		button_1.setHorizontalAlignment(SwingConstants.TRAILING);
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_1.setRolloverIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/sign-check2.png")));
		button_1.setContentAreaFilled(false);
		button_1.setSelected(true);
		button_1.setIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/sign-check.png")));
		button_1.setBounds(797, 48, 107, 50);
		panelAccount.add(button_1);
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.DARK_GRAY);
		
		JLabel label_8 = new JLabel("Last Name:");
		label_8.setBounds(528, 91, 82, 29);
		panelAccount.add(label_8);
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		tfLastName = new JTextField();
		tfLastName.setBounds(622, 89, 145, 29);
		panelAccount.add(tfLastName);
		tfLastName.setColumns(10);
		
		JLabel label_6 = new JLabel("Username:");
		label_6.setBounds(528, 132, 82, 29);
		panelAccount.add(label_6);
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		tfUsername = new JTextField();
		tfUsername.setBounds(622, 130, 145, 29);
		panelAccount.add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel label_5 = new JLabel("Password:");
		label_5.setBounds(528, 173, 82, 29);
		panelAccount.add(label_5);
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(622, 168, 145, 29);
		panelAccount.add(tfPassword);
		
		JLabel label_7 = new JLabel("Age:");
		label_7.setBounds(528, 214, 82, 29);
		panelAccount.add(label_7);
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		tfAge = new JTextField();
		tfAge.setBounds(622, 209, 145, 29);
		panelAccount.add(tfAge);
		tfAge.setColumns(10);
		
		JLabel lblSubmit_1 = new JLabel("Submit");
		lblSubmit_1.setBounds(803, 63, 60, 16);
		panelAccount.add(lblSubmit_1);
		button_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try{
				      String query2 = ("SELECT username FROM customers WHERE userid = ?");
				      PreparedStatement pst2 = connection.prepareStatement(query2);
				      pst2.setString(1, tfUserID.getText());
				      ResultSet rs2 = pst2.executeQuery();
				      while(rs2.next()){
				    	  currentCust = rs2.getString("username");
				      }
				      if(currentCust.equals(tfUsername.getText())){}
				       else{   
				    	   try{
				    		   String query3 = ("SELECT username FROM customers");
				    		   PreparedStatement pst3 = connection.prepareStatement(query3);
							   ResultSet rs3 = pst3.executeQuery();
							   while(rs3.next()){
								   allCust = rs3.getString("username");
								   if(tfUsername.getText().equals(allCust)){
									   JOptionPane.showMessageDialog(null, "Username already exists.");
							    	   return;
								   }
							   }
				    	   }catch (Exception ex){
				    		   JOptionPane.showMessageDialog(null,ex);
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
		
		JButton btnNewButton = new JButton("Rent Movie");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		btnAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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

		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(Color.DARK_GRAY);
		panelButtons.setBounds(0, 0, 963, 90);
		contentPane.add(panelButtons);

		JButton btnCustomers = new JButton("");
		btnCustomers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCustomers.setRolloverIcon(new ImageIcon(Main_Cust.class.getResource("/nintysix/house2.png")));
		btnCustomers.setContentAreaFilled(false);
		btnCustomers.setFocusPainted(false);
		btnCustomers.setOpaque(true);
		btnCustomers.setForeground(Color.WHITE);
		btnCustomers.setBackground(Color.DARK_GRAY);
		btnCustomers.setBounds(603, 0, 120, 90);
		btnCustomers.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCustomers.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCustomers.setIcon(new ImageIcon(Main_Cust.class.getResource("/nintysix/house.png")));
		btnCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelCards.removeAll();
				panelCards.add(panelWelcome);
				panelCards.repaint();
				panelCards.revalidate();
				refEditCustTbl();
			}

		});

		JButton btnMovies = new JButton("");
		btnMovies.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMovies.setRolloverIcon(new ImageIcon(Main_Cust.class.getResource("/nintysix/device-tv2.png")));
		btnMovies.setContentAreaFilled(false);
		btnMovies.setFocusPainted(false);
		btnMovies.setOpaque(true);
		btnMovies.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnMovies.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMovies.setForeground(Color.WHITE);
		btnMovies.setBackground(Color.DARK_GRAY);
		btnMovies.setBounds(723, 0, 120, 90);
		btnMovies.setIcon(new ImageIcon(Main_Cust.class.getResource("/nintysix/device-tv.png")));
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
		btnLogout.setRolloverIcon(new ImageIcon(Main_Cust.class.getResource("/nintysix/sign-ban2.png")));
		btnLogout.setContentAreaFilled(false);
		btnLogout.setFocusPainted(false);
		btnLogout.setOpaque(true);
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogout.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogout.setBounds(843, 0, 120, 90);
		btnLogout.setBackground(Color.DARK_GRAY);
		btnLogout.setIcon(new ImageIcon(Main_Cust.class.getResource("/nintysix/sign-ban.png")));
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
		panelButtons.setLayout(null);
		panelButtons.add(btnCustomers);
		panelButtons.add(btnMovies);
		panelButtons.add(btnLogout);

		JPanel panelStatusBar = new JPanel();
		panelStatusBar.setBackground(Color.GRAY);
		panelStatusBar.setLayout(null);
		panelStatusBar.setBounds(0, 569, 963, 29);
		contentPane.add(panelStatusBar);

		JLabel lblCurrentlyLoggedIn = new JLabel("Currently Logged in as Customer");
		lblCurrentlyLoggedIn.setForeground(Color.WHITE);
		lblCurrentlyLoggedIn.setBounds(6, 7, 182, 16);
		panelStatusBar.add(lblCurrentlyLoggedIn);

		JLabel label_1 = new JLabel("Username:");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(200, 7, 73, 16);
		panelStatusBar.add(label_1);

		lbCurrentUsernameCust = new JLabel();
		lbCurrentUsernameCust.setForeground(Color.WHITE);
		lbCurrentUsernameCust.setBounds(264, 7, 112, 16);
		panelStatusBar.add(lbCurrentUsernameCust);

		JLabel label_3 = new JLabel("ID:");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(388, 7, 27, 16);
		panelStatusBar.add(label_3);

		lbCurrentUserIDCust = new JLabel();
		lbCurrentUserIDCust.setForeground(Color.WHITE);
		lbCurrentUserIDCust.setText("22");
		lbCurrentUserIDCust.setBounds(408, 7, 55, 16);
		panelStatusBar.add(lbCurrentUserIDCust);
		
		lbCurrentCustAge = new JLabel();
		panelStatusBar.add(lbCurrentCustAge);
	}
}
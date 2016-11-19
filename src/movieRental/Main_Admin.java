package movieRental;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;
import net.proteanit.sql.DbUtils;
import com.toedter.calendar.JYearChooser;

public class Main_Admin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3524921925112957488L;
	Connection connection = null;
	private JPanel contentPane;
	private JPanel panelCards;
	private JPanel panelHome;
	private JPanel panelCust;
	private JPanel panelCustCards;
	private JPanel panelViewCust;
	private JPanel panelAddCust;
	private JPanel panelEditCust;
	private JPanel panelDelCust;
	private JPanel panelMov;
	private JPanel panelMovCards;
	private JPanel panelViewMov;
	private JPanel panelAddMov;
	private JPanel panelEditMov;
	private JPanel panelDelMov;
	private JPanel panelAdm;
	private JPanel panelAdmCards;
	private JPanel panelViewAdm;
	private JPanel panelAddAdm;
	private JPanel panelEditAdm;
	private JPanel panelDelAdm;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfAge;
	private JTextField tfUsername;
	private JPasswordField tfPassword;
	private JTable tableNewCust;
	private JTable tableViewCust;
	private JTable tableEditCust;
	private JTextField tfUsername2;
	private JPasswordField tfPassword2;
	private JTextField tfFirstName2;
	private JTextField tfLastName2;
	private JTextField tfAge2;
	private JTextField tfEditCustSearch;
	private JTextField tfDelUserSearch;
	private JTable tableDelCust;
	private JTable tableViewMov;
	private JTable tableNewMov;
	private JTextField tfTitle;
	private JTable tableEditMov;
	private JTable tableDelMov;
	private JTextField tfCustID;
	private JTable tableViewAdm;
	private JTable tableNewAdm;
	private JTextField tfLastName3;
	private JTextField tfFirstName3;
	private JPasswordField tfPassword3;
	private JTextField tfUsername3;
	private JTextField tfAge3;
	private JTable tableEditAdm;
	private JTextField tfEditAdmSearch;
	private JTextField tfLastName4;
	private JTextField tfFirstName4;
	private JTextField tfUsername4;
	private JPasswordField tfPassword4;
	private JTextField tfAdminID;
	private JTextField tfAge4;
	private JTable tableDelAdm;
	private JTextField tfDelAdmSearch;
	private JComboBox<String> comboBoxEditAdm;
	private JComboBox<String> comboBoxEditCust;
	private JComboBox<String> comboBoxDelCust;
	private JComboBox<String> comboBoxDelAdm;
	private JComboBox<String> comboBoxEditMov;
	private JComboBox<String> comboBoxDelMov;
	private JComboBox<String> comboBoxNewGenre;
	private JComboBox<String> comboBoxNewRating;
	private JComboBox<String> comboBoxEditGenre;
	private JComboBox<String> comboBoxEditRating;
	private JTextField tfTitle2;
	private JTextField tfEditMovSearch;
	private JTextField tfDelMovSearch;
	private JTextField tfmovieID;
	public static JLabel lbCurrentUsernameAdmin;
	public static JLabel lbCurrentUserIDAdmin;
	private JTable tableViewRent;
	private String current;
	private JTable tableCurrentUsername;
	private JTextField tfRentRateNew;
	private JTextField tfReplaceNew;
	private JTextField tfRentRateEdit;
	private JTextField tfReplaceEdit;
	public static JLabel lbNameAdmin;
	public static JLabel lbCurrentAdminAge;
	
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
					Main_Admin frame = new Main_Admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	public void refAllCustTbl(){
		try{
			String query = "SELECT userid as'Customer ID', first_name as 'First Name', last_name as 'Last Name', age as 'Age', username as 'Username', password as 'Password' FROM customers";
			
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableViewCust.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void refNewCustTbl(){
		try{
			String query = "SELECT userid as'Customer ID', first_name as 'First Name', last_name as 'Last Name', age as 'Age', username as 'Username', password as 'Password' FROM customers";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableNewCust.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void refEditCustTbl(){
		try{
			String query = "SELECT userid as'Customer ID', first_name as 'First Name', last_name as 'Last Name', age as 'Age', username as 'Username', password as 'Password' FROM customers";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableEditCust.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void refDelCustTbl(){
		try{
			String query = "SELECT userid as'Customer ID', first_name as 'First Name', last_name as 'Last Name', age as 'Age', username as 'Username', password as 'Password' FROM customers";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableDelCust.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
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
	
	public void refNewMovTbl(){
		try{
			String query = "SELECT movieid as 'Movie ID', title as 'Title',description as 'Description',"
					+ " genre as 'Genre', release_year as 'Release Year', rental_rate as 'Rental Rate', "
					+ "rating as 'Rating', replacement_cost as 'Replacement Cost', length as 'Length (Minutes)' FROM movies";
			
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableNewMov.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void refEditMovTbl(){
		try{
			String query = "SELECT movieid as 'Movie ID', title as 'Title',description as 'Description',"
					+ " genre as 'Genre', release_year as 'Release Year', rental_rate as 'Rental Rate', "
					+ "rating as 'Rating', replacement_cost as 'Replacement Cost', length as 'Length (Minutes)' FROM movies";
			
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableEditMov.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void refDelMovTbl(){
		try{
			String query = "SELECT movieid as 'Movie ID', title as 'Title',description as 'Description',"
					+ " genre as 'Genre', release_year as 'Release Year', rental_rate as 'Rental Rate', "
					+ "rating as 'Rating', replacement_cost as 'Replacement Cost', length as 'Length (Minutes)' FROM movies";
			
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableDelMov.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void refAllAdmTbl(){
		try{
			String query = "SELECT adminid as'Admin ID', first_name as 'First Name', last_name as 'Last Name', age as 'Age', username as 'Username', password as 'Password' FROM admins";
			
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableViewAdm.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void refNewAdmTbl(){
		try{
			String query = "SELECT adminid as'Admin ID', first_name as 'First Name', last_name as 'Last Name', age as 'Age', username as 'Username', password as 'Password' FROM admins";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableNewAdm.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void refEditAdmTbl(){
		try{
			String query = "SELECT adminid as'Admin ID', first_name as 'First Name', last_name as 'Last Name', age as 'Age', username as 'Username', password as 'Password' FROM admins";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableEditAdm.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void refDelAdmTbl(){
		try{
			String query = "SELECT adminid as'Admin ID', first_name as 'First Name', last_name as 'Last Name', age as 'Age', username as 'Username', password as 'Password' FROM admins";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableDelAdm.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void refAllRentTbl(){
		try{
			String query = "SELECT userid as 'User ID', rentid as 'Rental ID', movieid as 'Movie ID', rental_date as 'Date Rented', return_date as 'Return Date' FROM rentals";
			
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableViewRent.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void fillComboEditCust(){
		try{
			comboBoxEditCust.removeAllItems();
			comboBoxEditCust.addItem("userid");
			comboBoxEditCust.addItem("first_name");
			comboBoxEditCust.addItem("last_name");
			comboBoxEditCust.addItem("age");
			comboBoxEditCust.addItem("username");
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	public void fillComboEditAdm(){
		try{
			comboBoxEditAdm.removeAllItems();
			comboBoxEditAdm.addItem("adminid");
			comboBoxEditAdm.addItem("first_name");
			comboBoxEditAdm.addItem("last_name");
			comboBoxEditAdm.addItem("age");
			comboBoxEditAdm.addItem("username");
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	public void fillComboEditMov(){
		try{
			comboBoxEditMov.removeAllItems();
			comboBoxEditMov.addItem("movieid");
			comboBoxEditMov.addItem("title");
			comboBoxEditMov.addItem("genre");
			comboBoxEditMov.addItem("rating");
			comboBoxEditMov.addItem("rental_rate");
			comboBoxEditMov.addItem("release_year");
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	public void fillComboDelCust(){
		try{
			comboBoxDelCust.removeAllItems();
			comboBoxDelCust.addItem("userid");
			comboBoxDelCust.addItem("first_name");
			comboBoxDelCust.addItem("last_name");
			comboBoxDelCust.addItem("age");
			comboBoxDelCust.addItem("username");
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	public void fillComboDelAdm(){
		try{
			comboBoxDelAdm.removeAllItems();
			comboBoxDelAdm.addItem("adminid");
			comboBoxDelAdm.addItem("first_name");
			comboBoxDelAdm.addItem("last_name");
			comboBoxDelAdm.addItem("age");
			comboBoxDelAdm.addItem("username");
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	public void fillComboDelMov(){
		try{
			comboBoxDelMov.removeAllItems();
			comboBoxDelMov.addItem("movieid");
			comboBoxDelMov.addItem("title");
			comboBoxDelMov.addItem("genre");
			comboBoxDelMov.addItem("rating");
			comboBoxDelMov.addItem("rental_rate");
			comboBoxDelMov.addItem("release_year");
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	
	
	public void fillComboNewGenre(){
		try{
			comboBoxNewGenre.removeAllItems();
			comboBoxNewGenre.addItem("Action");
			comboBoxNewGenre.addItem("Adventure");
			comboBoxNewGenre.addItem("Comedy");
			comboBoxNewGenre.addItem("Crime");
			comboBoxNewGenre.addItem("Drama");
			comboBoxNewGenre.addItem("Fantasy");
			comboBoxNewGenre.addItem("Historical");
			comboBoxNewGenre.addItem("Horror");
			comboBoxNewGenre.addItem("Mystery");
			comboBoxNewGenre.addItem("Political");
			comboBoxNewGenre.addItem("Romance");
			comboBoxNewGenre.addItem("Satire");
			comboBoxNewGenre.addItem("Thriller");
			comboBoxNewGenre.addItem("Western");
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	public void fillComboNewRating(){
		try{
			comboBoxNewRating.removeAllItems();
			comboBoxNewRating.addItem("G");
			comboBoxNewRating.addItem("PG");
			comboBoxNewRating.addItem("PG-13");
			comboBoxNewRating.addItem("R");
			comboBoxNewRating.addItem("NC-17");
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	public void fillComboEditGenre(){
		try{
			comboBoxEditGenre.removeAllItems();
			comboBoxEditGenre.addItem("Action");
			comboBoxEditGenre.addItem("Adventure");
			comboBoxEditGenre.addItem("Comedy");
			comboBoxEditGenre.addItem("Crime");
			comboBoxEditGenre.addItem("Drama");
			comboBoxEditGenre.addItem("Fantasy");
			comboBoxEditGenre.addItem("Historical");
			comboBoxEditGenre.addItem("Horror");
			comboBoxEditGenre.addItem("Mystery");
			comboBoxEditGenre.addItem("Political");
			comboBoxEditGenre.addItem("Romance");
			comboBoxEditGenre.addItem("Satire");
			comboBoxEditGenre.addItem("Thriller");
			comboBoxEditGenre.addItem("Western");
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	public void fillComboEditRating(){
		try{
			comboBoxEditRating.removeAllItems();
			comboBoxEditRating.addItem("G");
			comboBoxEditRating.addItem("PG");
			comboBoxEditRating.addItem("PG-13");
			comboBoxEditRating.addItem("R");
			comboBoxEditRating.addItem("NC-17");
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	/**
	 * Create the frame.
	 */
	public Main_Admin() {
		setBackground(Color.DARK_GRAY);
		setResizable(false);
		setTitle("Movie Project");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main_Admin.class.getResource("/fortyeight/device-tv.png")));
		connection = databaseConnection.dbConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 969, 627);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelCards = new JPanel();
		panelCards.setBackground(Color.DARK_GRAY);
		panelCards.setBounds(0, 100, 963, 469);
		contentPane.add(panelCards);
		panelCards.setLayout(new CardLayout(0, 0));
		
		panelHome = new JPanel();
		panelHome.setBackground(Color.DARK_GRAY);
		panelCards.add(panelHome, "name_145839516092709");
		panelHome.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel.setBounds(0, 0, 164, 40);
		panelHome.add(lblNewLabel);
		
		lbNameAdmin = new JLabel("Name");
		lbNameAdmin.setForeground(Color.WHITE);
		lbNameAdmin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 33));
		lbNameAdmin.setBounds(69, 52, 164, 40);
		panelHome.add(lbNameAdmin);
		
		JLabel label_24 = new JLabel("");
		label_24.setIcon(new ImageIcon(Main_Admin.class.getResource("/Logo/movie-vector-icons.png")));
		label_24.setVerticalAlignment(SwingConstants.BOTTOM);
		label_24.setHorizontalAlignment(SwingConstants.CENTER);
		label_24.setBounds(0, 0, 963, 473);
		panelHome.add(label_24);
		
		panelCust = new JPanel();
		panelCust.setBackground(Color.DARK_GRAY);
		panelCards.add(panelCust, "Customers");
		panelCust.setLayout(null);
		
		JButton btnViewAllCustomers = new JButton("View All Customers");
		btnViewAllCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelCustCards.removeAll();
				panelCustCards.add(panelViewCust);
				panelCustCards.repaint();
				panelCustCards.revalidate();
			}
		});
		
		panelCustCards = new JPanel();
		panelCustCards.setBounds(0, 30, 963, 439);
		panelCust.add(panelCustCards);
		panelCustCards.setLayout(new CardLayout(0, 0));
		
		panelViewCust = new JPanel();
		panelViewCust.setBackground(Color.LIGHT_GRAY);
		panelCustCards.add(panelViewCust, "name_12324793501895");
		panelViewCust.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 45, 963, 394);
		scrollPane.getViewport().setBackground(Color.GRAY);
		panelViewCust.add(scrollPane);
		
		tableViewCust = new JTable();
		tableViewCust.setSelectionForeground(Color.BLACK);
		tableViewCust.setSelectionBackground(Color.LIGHT_GRAY);
		tableViewCust.setForeground(Color.WHITE);
		tableViewCust.setBackground(Color.GRAY);
		tableViewCust.getTableHeader().setReorderingAllowed(false);
		tableViewCust.getTableHeader().setResizingAllowed(false);
		tableViewCust.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableViewCust);
		
		JLabel lblCustomers = new JLabel("Customers");
		lblCustomers.setForeground(Color.WHITE);
		lblCustomers.setOpaque(true);
		lblCustomers.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomers.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblCustomers.setBackground(Color.GRAY);
		lblCustomers.setBounds(0, 0, 963, 45);
		panelViewCust.add(lblCustomers);
		
		panelAddCust = new JPanel();
		panelAddCust.setBackground(Color.LIGHT_GRAY);
		panelCustCards.add(panelAddCust, "name_12332862391272");
		panelAddCust.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 46, 963, 268);
		scrollPane_1.getViewport().setBackground(Color.GRAY);
		panelAddCust.add(scrollPane_1);
		
		tableNewCust = new JTable();
		tableNewCust.setBackground(Color.GRAY);
		tableNewCust.setForeground(Color.WHITE);
		tableNewCust.setSelectionForeground(Color.BLACK);
		tableNewCust.setSelectionBackground(Color.LIGHT_GRAY);
		tableNewCust.getTableHeader().setReorderingAllowed(false);
		tableNewCust.getTableHeader().setResizingAllowed(false);
		tableNewCust.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableNewCust.setRowSelectionAllowed(true);
		scrollPane_1.setViewportView(tableNewCust);
		
		tfFirstName = new JTextField();
		tfFirstName.setColumns(10);
		tfFirstName.setBounds(98, 327, 145, 29);
		panelAddCust.add(tfFirstName);
		
		JLabel label_1 = new JLabel("Last Name:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(6, 368, 82, 29);
		panelAddCust.add(label_1);
		
		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		tfLastName.setBounds(98, 369, 145, 29);
		panelAddCust.add(tfLastName);
		
		JLabel label_2 = new JLabel("Age:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(502, 326, 82, 29);
		panelAddCust.add(label_2);
		
		tfAge = new JTextField();
		tfAge.setColumns(10);
		tfAge.setBounds(596, 327, 145, 29);
		panelAddCust.add(tfAge);
		
		JLabel label_3 = new JLabel("Username:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(255, 326, 82, 29);
		panelAddCust.add(label_3);
		
		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		tfUsername.setBounds(345, 327, 145, 29);
		panelAddCust.add(tfUsername);
		
		JLabel label_4 = new JLabel("Password:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(255, 367, 82, 29);
		panelAddCust.add(label_4);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(345, 369, 145, 29);
		panelAddCust.add(tfPassword);
		
		JButton button = new JButton("");
		button.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/sign-check2.png")));
		button.setIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/sign-check.png")));
		button.setContentAreaFilled(false);
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String empty = "";
				String first = tfFirstName.getText();
				String last = tfLastName.getText();
				String age = tfAge.getText();
				String username = tfUsername.getText();
				String password = tfPassword.getText();
			  if(first.equals(empty) || last.equals(empty) || age.equals(empty) || username.equals(empty) || password.equals(empty)){
				  JOptionPane.showMessageDialog(null,"Must fill out all fields");
				  return;
				  }
				int action = JOptionPane.showConfirmDialog(null, "Are you sure want to add a new customer?", "Confirm Submission", JOptionPane.YES_NO_OPTION);
			  if(action == 0){
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
						String query = "INSERT INTO customers (first_name, last_name, age, username, password) VALUES (?,?,?,?,?)";
						PreparedStatement pst = connection.prepareStatement(query);
						pst.setString(1, tfFirstName.getText() );
						pst.setString(2, tfLastName.getText() );
						pst.setString(3, tfAge.getText() );
						pst.setString(4, tfUsername.getText() );
						pst.setString(5, tfPassword.getText() );
						
						pst.execute();
						
						JOptionPane.showMessageDialog(null, "Profile Created.");
						
						pst.close();
						
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex);
					}
					refNewCustTbl();
					refEditCustTbl();
					refAllCustTbl();
					refDelCustTbl();
			  }
			}
		});
		button.setBounds(907, 383, 50, 50);
		panelAddCust.add(button);
		
		JLabel label = new JLabel("First Name:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(6, 327, 82, 29);
		panelAddCust.add(label);
		
		JLabel lblAddCustomer = new JLabel("Add Customer");
		lblAddCustomer.setForeground(Color.WHITE);
		lblAddCustomer.setOpaque(true);
		lblAddCustomer.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddCustomer.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblAddCustomer.setBackground(Color.GRAY);
		lblAddCustomer.setBounds(0, 0, 963, 45);
		panelAddCust.add(lblAddCustomer);
		
		JLabel label_15 = new JLabel("Submit");
		label_15.setHorizontalAlignment(SwingConstants.TRAILING);
		label_15.setBounds(840, 401, 55, 16);
		panelAddCust.add(label_15);
		
		panelEditCust = new JPanel();
		panelEditCust.setBackground(Color.LIGHT_GRAY);
		panelCustCards.add(panelEditCust, "name_12336283601299");
		panelEditCust.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.getViewport().setBackground(Color.GRAY);
		scrollPane_2.setBounds(0, 98, 963, 216);
		panelEditCust.add(scrollPane_2);
		
		tfFirstName2 = new JTextField();
		tfFirstName2.setColumns(10);
		tfFirstName2.setBounds(98, 327, 145, 29);
		panelEditCust.add(tfFirstName2);
		
		tfLastName2 = new JTextField();
		tfLastName2.setColumns(10);
		tfLastName2.setBounds(98, 369, 145, 29);
		panelEditCust.add(tfLastName2);
		
		tfAge2 = new JTextField();
		tfAge2.setColumns(10);
		tfAge2.setBounds(596, 327, 145, 29);
		panelEditCust.add(tfAge2);
		
		tfUsername2 = new JTextField();
		tfUsername2.setColumns(10);
		tfUsername2.setBounds(345, 327, 145, 29);
		panelEditCust.add(tfUsername2);
		
		tfPassword2 = new JPasswordField();
		tfPassword2.setBounds(345, 369, 145, 29);
		panelEditCust.add(tfPassword2);
		
		JButton btnSearch = new JButton("");
		btnSearch.setIcon(new ImageIcon(Main_Admin.class.getResource("/twentyfour/search.png")));
		btnSearch.setContentAreaFilled(false);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String value0 = (String)comboBoxEditCust.getSelectedItem();
					String value1 = tfEditCustSearch.getText()+"%";
					String query = "SELECT userid as'Customer ID', first_name as 'First Name', last_name as 'Last Name', age as 'Age', username as 'Username', password as 'Password' FROM customers WHERE "+value0+" LIKE '"+value1+"'";
					
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tableEditCust.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					rs.close();

				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnSearch.setBounds(501, 56, 30, 30);
		panelEditCust.add(btnSearch);
		
		tableEditCust = new JTable();
		tableEditCust.setSelectionForeground(Color.BLACK);
		tableEditCust.setSelectionBackground(Color.LIGHT_GRAY);
		tableEditCust.setBackground(Color.GRAY);
		tableEditCust.setForeground(Color.WHITE);
		tableEditCust.getTableHeader().setReorderingAllowed(false);
		tableEditCust.getTableHeader().setResizingAllowed(false);
		tableEditCust.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEditCust.setRowSelectionAllowed(true);
		tableEditCust.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					int row = tableEditCust.getSelectedRow();
					String Table_click = (tableEditCust.getModel().getValueAt(row, 0).toString());
					
					String query = "SELECT userid as'Customer ID', first_name as 'First Name', last_name as 'Last Name', age as 'Age', username as 'Username', password as 'Password' FROM customers WHERE userid = ' "+Table_click+" ' ";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
				
				while(rs.next())
				{
					tfCustID.setText(rs.getString("Customer ID"));
					tfFirstName2.setText(rs.getString("First Name"));
					tfLastName2.setText(rs.getString("Last Name"));
					tfAge2.setText(rs.getString("Age"));
					tfUsername2.setText(rs.getString("Username"));
					tfPassword2.setText(rs.getString("Password"));
				}
				
				rs.close();
				pst.close();
				
				}catch (Exception ex) { 
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		scrollPane_2.setViewportView(tableEditCust);
		
		JLabel label_9 = new JLabel("First Name:");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_9.setBounds(6, 327, 82, 29);
		panelEditCust.add(label_9);
		
				JLabel label_8 = new JLabel("Last Name:");
				label_8.setHorizontalAlignment(SwingConstants.RIGHT);
				label_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
				label_8.setBounds(6, 368, 82, 29);
				panelEditCust.add(label_8);
				
						JLabel label_7 = new JLabel("Age:");
						label_7.setHorizontalAlignment(SwingConstants.RIGHT);
						label_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
						label_7.setBounds(502, 326, 82, 29);
						panelEditCust.add(label_7);
						
						JLabel label_6 = new JLabel("Username:");
						label_6.setHorizontalAlignment(SwingConstants.RIGHT);
						label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
						label_6.setBounds(255, 326, 82, 29);
						panelEditCust.add(label_6);
						
						JLabel label_5 = new JLabel("Password:");
						label_5.setHorizontalAlignment(SwingConstants.RIGHT);
						label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
						label_5.setBounds(255, 367, 82, 29);
						panelEditCust.add(label_5);
						
						JButton button_1 = new JButton("");
						button_1.setContentAreaFilled(false);
						button_1.setIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/sign-check.png")));
						button_1.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/sign-check2.png")));
						button_1.addActionListener(new ActionListener() {
							@SuppressWarnings("deprecation")
							public void actionPerformed(ActionEvent arg0) {
								String empty = "";
								String first = tfFirstName2.getText();
								String last = tfLastName2.getText();
								String age = tfAge2.getText();
								String username = tfUsername2.getText();
								String password = tfPassword2.getText();
							  if(first.equals(empty) || last.equals(empty) || age.equals(empty) || username.equals(empty) || password.equals(empty)){
								  JOptionPane.showMessageDialog(null,"Must fill out all fields");
								  return;
								  }
							  int action = JOptionPane.showConfirmDialog(null, "Are you sure want to edit?", "Confirm Edit", JOptionPane.YES_NO_OPTION);
							  if(action == 0){
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
								      pst2.setString(1, tfUsername2.getText());
								      ResultSet rs2 = pst2.executeQuery();
								      if(rs2.next()){
								       if(current.equals(tfUsername2.getText())){}
								       else{
								        JOptionPane.showMessageDialog(null, "Username already exists.");
								       return;
								       }
								      }
								      }catch(Exception ex){
								       JOptionPane.showMessageDialog(null,ex);
								      }
								  try{
										String value0 = tfCustID.getText();
										String value1 = tfFirstName2.getText();
										String value2 = tfLastName2.getText();
										String value3 = tfAge2.getText();
										String value4 = tfUsername2.getText();
										String value5 = tfPassword2.getText();
										
										String query = "UPDATE customers SET  first_name = '"+ value1 +"', last_name = '"+ value2 +
													   "', age = '"+ value3 +"', username = '"+ value4 +"', password = '"+ value5 +
													   "' WHERE userid = "+value0+" ";
										
										PreparedStatement pst = connection.prepareStatement(query);
										pst.execute();
									
									}catch (Exception ex) { 
										JOptionPane.showMessageDialog(null, ex);
									}
									refNewCustTbl();
									refEditCustTbl();
									refAllCustTbl();
									refDelCustTbl();
							  }
							}
						});
						button_1.setBounds(907, 383, 50, 50);
						panelEditCust.add(button_1);
						
						tfEditCustSearch = new JTextField();
						tfEditCustSearch.setColumns(10);
						tfEditCustSearch.setBounds(345, 57, 145, 29);
						panelEditCust.add(tfEditCustSearch);
						
						tfCustID = new JTextField();
						panelEditCust.add(tfCustID);
						
						JLabel label_13 = new JLabel("Search Customer:");
						label_13.setFont(new Font("Tahoma", Font.PLAIN, 16));
						label_13.setBounds(6, 57, 174, 28);
						panelEditCust.add(label_13);
						
						comboBoxEditCust = new JComboBox<String>();
						comboBoxEditCust.setBounds(190, 57, 145, 29);
						panelEditCust.add(comboBoxEditCust);
						
						JLabel lblEditCustomer = new JLabel("Edit Customer");
						lblEditCustomer.setForeground(Color.WHITE);
						lblEditCustomer.setOpaque(true);
						lblEditCustomer.setHorizontalAlignment(SwingConstants.CENTER);
						lblEditCustomer.setFont(new Font("Tahoma", Font.PLAIN, 39));
						lblEditCustomer.setBackground(Color.GRAY);
						lblEditCustomer.setBounds(0, 0, 963, 45);
						panelEditCust.add(lblEditCustomer);
						
						JLabel lblSubmit = new JLabel("Submit");
						lblSubmit.setHorizontalAlignment(SwingConstants.TRAILING);
						lblSubmit.setBounds(840, 401, 55, 16);
						panelEditCust.add(lblSubmit);
						
						panelDelCust = new JPanel();
						panelDelCust.setBackground(Color.LIGHT_GRAY);
						panelCustCards.add(panelDelCust, "name_12338691653946");
						panelDelCust.setLayout(null);
						
						JScrollPane scrollPane_3 = new JScrollPane();
						scrollPane_3.getViewport().setBackground(Color.GRAY);
						scrollPane_3.setBounds(0, 98, 963, 341);
						panelDelCust.add(scrollPane_3);
						
						tableDelCust = new JTable();
						tableDelCust.setBackground(Color.GRAY);
						tableDelCust.setForeground(Color.WHITE);
						tableDelCust.setSelectionBackground(Color.LIGHT_GRAY);
						tableDelCust.setSelectionForeground(Color.BLACK);
						tableDelCust.getTableHeader().setReorderingAllowed(false);
						tableDelCust.getTableHeader().setResizingAllowed(false);
						tableDelCust.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						scrollPane_3.setViewportView(tableDelCust);
						
						tfDelUserSearch = new JTextField();
						tfDelUserSearch.setColumns(10);
						tfDelUserSearch.setBounds(345, 57, 145, 29);
						panelDelCust.add(tfDelUserSearch);
						
						JButton btnDelete = new JButton("");
						btnDelete.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/sign-ban2.png")));
						btnDelete.setContentAreaFilled(false);
						btnDelete.setIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/sign-ban48.png")));
						btnDelete.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							  int action = JOptionPane.showConfirmDialog(null, "Are you sure want to delete?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
							  if(action == 0){
								try{
									int row = tableDelCust.getSelectedRow();
									String Table_click = (tableDelCust.getModel().getValueAt(row, 0).toString());
									
									String query = "DELETE FROM customers WHERE userid = '"+Table_click+"' ";
									
									PreparedStatement pst = connection.prepareStatement(query);
									pst.execute();
								
								}catch (Exception ex) { 
									JOptionPane.showMessageDialog(null, ex);
								}
								refNewCustTbl();
								refEditCustTbl();
								refAllCustTbl();
								refDelCustTbl();
							  }
							}
						});
						btnDelete.setBounds(907, 47, 50, 50);
						panelDelCust.add(btnDelete);
						
						JButton button_6 = new JButton("");
						button_6.setIcon(new ImageIcon(Main_Admin.class.getResource("/twentyfour/search.png")));
						button_6.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/twentyfour/search-hover.png")));
						button_6.setContentAreaFilled(false);
						button_6.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try{
									String value0 = (String)comboBoxDelCust.getSelectedItem();
									String value1 = tfDelUserSearch.getText()+"%";
									String query = "SELECT userid as'Customer ID', first_name as 'First Name', last_name as 'Last Name', age as 'Age', username as 'Username', password as 'Password' FROM customers where "+value0+" LIKE '"+value1+"' ";
									
									PreparedStatement pst = connection.prepareStatement(query);
									ResultSet rs = pst.executeQuery();
									tableDelCust.setModel(DbUtils.resultSetToTableModel(rs));

									pst.close();
									rs.close();

								}catch(Exception ex){
									ex.printStackTrace();
								}
							}
						});
						button_6.setBounds(501, 56, 30, 30);
						panelDelCust.add(button_6);
						
						JLabel label_11 = new JLabel("Search Customer:");
						label_11.setFont(new Font("Tahoma", Font.PLAIN, 16));
						label_11.setBounds(6, 57, 174, 28);
						panelDelCust.add(label_11);
						
						comboBoxDelCust = new JComboBox<String>();
						comboBoxDelCust.setBounds(190, 57, 145, 29);
						panelDelCust.add(comboBoxDelCust);
						
						JLabel lblDeleteCustomer = new JLabel("Delete Customer");
						lblDeleteCustomer.setForeground(Color.WHITE);
						lblDeleteCustomer.setOpaque(true);
						lblDeleteCustomer.setHorizontalAlignment(SwingConstants.CENTER);
						lblDeleteCustomer.setFont(new Font("Tahoma", Font.PLAIN, 39));
						lblDeleteCustomer.setBackground(Color.GRAY);
						lblDeleteCustomer.setBounds(0, 0, 963, 45);
						panelDelCust.add(lblDeleteCustomer);
						
						JLabel lblDelete = new JLabel("Delete");
						lblDelete.setHorizontalAlignment(SwingConstants.TRAILING);
						lblDelete.setBounds(840, 63, 55, 16);
						panelDelCust.add(lblDelete);
		btnViewAllCustomers.setOpaque(true);
		btnViewAllCustomers.setForeground(Color.WHITE);
		btnViewAllCustomers.setBackground(Color.DARK_GRAY);
		btnViewAllCustomers.setBounds(0, 0, 135, 30);
		panelCust.add(btnViewAllCustomers);
		
		JButton btnAddNewCustomer = new JButton("Add New Customer");
		btnAddNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCustCards.removeAll();
				panelCustCards.add(panelAddCust);
				panelCustCards.repaint();
				panelCustCards.revalidate();
			}
		});
		btnAddNewCustomer.setOpaque(true);
		btnAddNewCustomer.setForeground(Color.WHITE);
		btnAddNewCustomer.setBackground(Color.DARK_GRAY);
		btnAddNewCustomer.setBounds(135, 0, 135, 30);
		panelCust.add(btnAddNewCustomer);
		
		JButton btnEditCustomer = new JButton("Edit Customer");
		btnEditCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCustCards.removeAll();
				panelCustCards.add(panelEditCust);
				panelCustCards.repaint();
				panelCustCards.revalidate();
			}
		});
		btnEditCustomer.setOpaque(true);
		btnEditCustomer.setForeground(Color.WHITE);
		btnEditCustomer.setBackground(Color.DARK_GRAY);
		btnEditCustomer.setBounds(270, 0, 135, 30);
		panelCust.add(btnEditCustomer);
		
		JButton btnDeleteCustomer = new JButton("Delete Customer");
		btnDeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCustCards.removeAll();
				panelCustCards.add(panelDelCust);
				panelCustCards.repaint();
				panelCustCards.revalidate();
			}
		});
		btnDeleteCustomer.setOpaque(true);
		btnDeleteCustomer.setForeground(Color.WHITE);
		btnDeleteCustomer.setBackground(Color.DARK_GRAY);
		btnDeleteCustomer.setBounds(405, 0, 135, 30);
		panelCust.add(btnDeleteCustomer);
		
		panelMov = new JPanel();
		panelMov.setBackground(Color.DARK_GRAY);
		panelCards.add(panelMov, "Movies");
		panelMov.setLayout(null);
		
		panelMovCards = new JPanel();
		panelMovCards.setBounds(0, 30, 963, 439);
		panelMov.add(panelMovCards);
		panelMovCards.setLayout(new CardLayout(0, 0));
		
		panelViewMov = new JPanel();
		panelViewMov.setBackground(Color.LIGHT_GRAY);
		panelMovCards.add(panelViewMov, "name_13855558412619");
		panelViewMov.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.getViewport().setBackground(Color.GRAY);
		scrollPane_4.setBounds(0, 44, 963, 395);
		panelViewMov.add(scrollPane_4);
		
		tableViewMov = new JTable();
		tableViewMov.setSelectionForeground(Color.BLACK);
		tableViewMov.setSelectionBackground(Color.LIGHT_GRAY);
		tableViewMov.setForeground(Color.WHITE);
		tableViewMov.setBackground(Color.GRAY);
		tableViewMov.getTableHeader().setReorderingAllowed(false);
		tableViewMov.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_4.setViewportView(tableViewMov);
		
		JLabel lblMovies = new JLabel("Movies");
		lblMovies.setForeground(Color.WHITE);
		lblMovies.setOpaque(true);
		lblMovies.setHorizontalAlignment(SwingConstants.CENTER);
		lblMovies.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblMovies.setBackground(Color.GRAY);
		lblMovies.setBounds(0, 0, 963, 45);
		panelViewMov.add(lblMovies);
		
		panelAddMov = new JPanel();
		panelAddMov.setBackground(Color.LIGHT_GRAY);
		panelMovCards.add(panelAddMov, "name_13863670380272");
		panelAddMov.setLayout(null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.getViewport().setBackground(Color.GRAY);
		scrollPane_5.setBounds(0, 44, 963, 267);
		panelAddMov.add(scrollPane_5);
		
		tableNewMov = new JTable();
		tableNewMov.setSelectionForeground(Color.BLACK);
		tableNewMov.setSelectionBackground(Color.LIGHT_GRAY);
		tableNewMov.setForeground(Color.WHITE);
		tableNewMov.setBackground(Color.GRAY);
		tableNewMov.getTableHeader().setReorderingAllowed(false);
		tableNewMov.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_5.setViewportView(tableNewMov);
		
		tfTitle = new JTextField();
		tfTitle.setColumns(10);
		tfTitle.setBounds(72, 323, 145, 29);
		panelAddMov.add(tfTitle);
		
		JYearChooser yearChooserNew = new JYearChooser();
		yearChooserNew.getSpinner().setBackground(Color.DARK_GRAY);
		yearChooserNew.setBounds(537, 323, 54, 28);
		panelAddMov.add(yearChooserNew);
		
		JSpinner spinnerNewMin = new JSpinner();
		spinnerNewMin.setBackground(Color.DARK_GRAY);
		spinnerNewMin.setModel(new SpinnerNumberModel(120, 0, 999, 1));
		spinnerNewMin.setBounds(349, 362, 54, 29);
		panelAddMov.add(spinnerNewMin);
		
		JTextArea taDescriptionNew = new JTextArea();
		taDescriptionNew.setWrapStyleWord(true);
		taDescriptionNew.setLineWrap(true);
		taDescriptionNew.setBounds(537, 362, 235, 73);
		panelAddMov.add(taDescriptionNew);
		
		tfRentRateNew = new JTextField();
		tfRentRateNew.setBounds(349, 323, 54, 28);
		panelAddMov.add(tfRentRateNew);
		tfRentRateNew.setColumns(10);
		
		tfReplaceNew = new JTextField();
		tfReplaceNew.setColumns(10);
		tfReplaceNew.setBounds(349, 403, 54, 28);
		panelAddMov.add(tfReplaceNew);
		
		JButton button_2 = new JButton("");
		button_2.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/sign-check2.png")));
		button_2.setIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/sign-check.png")));
		button_2.setContentAreaFilled(false);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String empty = "";
				String title = tfTitle.getText();
				String descrp = taDescriptionNew.getText();
				String rent = tfRentRateNew.getText();
			    String replc = tfReplaceNew.getText();
			  if(title.equals(empty)|| descrp.equals(empty) || rent.equals(empty) || replc.equals(empty)){
				  JOptionPane.showMessageDialog(null,"Must fill out all fields");
				  return;
				  }
			  int action = JOptionPane.showConfirmDialog(null, "Are you sure want to add a new movie?", "Confirm Submission", JOptionPane.YES_NO_OPTION);
				 if(action == 0){
					try{
						String query = "INSERT INTO movies (title, genre, release_year, length, rating, description, rental_rate, replacement_cost) VALUES (?,?,?,?,?,?,?,?)";
						PreparedStatement pst = connection.prepareStatement(query);
						pst.setString(1, tfTitle.getText());
						pst.setString(2, (String)comboBoxNewGenre.getSelectedItem());
						pst.setInt(3, yearChooserNew.getYear());
						pst.setInt(4, ((Integer)spinnerNewMin.getValue()));
						pst.setString(5, (String)comboBoxNewRating.getSelectedItem());
						pst.setString(6, taDescriptionNew.getText());
						pst.setString(7, tfRentRateNew.getText());
						pst.setString(8, tfReplaceNew.getText());
						
						pst.execute();
						
						JOptionPane.showMessageDialog(null, "Movie Added.");
						
						pst.close();
						
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex);
					}
					refNewMovTbl();
					refEditMovTbl();
					refAllMovTbl();
					refDelMovTbl();
				  }
			}
		});
		button_2.setBounds(907, 386, 50, 50);
		panelAddMov.add(button_2);
		
		JLabel lblTitle_1 = new JLabel("Title:");
		lblTitle_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitle_1.setBounds(6, 323, 54, 29);
		panelAddMov.add(lblTitle_1);
		
		JLabel lblGenre = new JLabel("Genre:");
		lblGenre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGenre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGenre.setBounds(6, 362, 54, 29);
		panelAddMov.add(lblGenre);
		
		JLabel lblReleaseDate = new JLabel("Release Year:");
		lblReleaseDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReleaseDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblReleaseDate.setBounds(415, 323, 110, 29);
		panelAddMov.add(lblReleaseDate);
		
		JLabel lblRating = new JLabel("Rating:");
		lblRating.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRating.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRating.setBounds(6, 403, 54, 29);
		panelAddMov.add(lblRating);
		
		JLabel lblLength = new JLabel("Length:");
		lblLength.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLength.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLength.setBounds(273, 362, 64, 29);
		panelAddMov.add(lblLength);
		
		comboBoxNewGenre = new JComboBox<String>();
		comboBoxNewGenre.setBounds(72, 362, 145, 29);
		panelAddMov.add(comboBoxNewGenre);
		
		comboBoxNewRating = new JComboBox<String>();
		comboBoxNewRating.setBounds(72, 403, 82, 28);
		panelAddMov.add(comboBoxNewRating);
		
		JLabel lblMin = new JLabel("Minutes");
		lblMin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMin.setBounds(349, 347, 54, 16);
		panelAddMov.add(lblMin);
		
		JLabel label_10 = new JLabel("Rental Rate:");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_10.setBounds(229, 323, 110, 29);
		panelAddMov.add(label_10);
		
		JLabel label_14 = new JLabel("Replacement Cost:");
		label_14.setHorizontalAlignment(SwingConstants.RIGHT);
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_14.setBounds(205, 403, 132, 29);
		panelAddMov.add(label_14);
		
		JLabel label_19 = new JLabel("Description:");
		label_19.setHorizontalAlignment(SwingConstants.TRAILING);
		label_19.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_19.setBounds(415, 362, 110, 29);
		panelAddMov.add(label_19);
		
		JLabel lblAddMovie = new JLabel("Add Movie");
		lblAddMovie.setForeground(Color.WHITE);
		lblAddMovie.setOpaque(true);
		lblAddMovie.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddMovie.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblAddMovie.setBackground(Color.GRAY);
		lblAddMovie.setBounds(0, 0, 963, 45);
		panelAddMov.add(lblAddMovie);
		
		JLabel label_22 = new JLabel("Submit");
		label_22.setHorizontalAlignment(SwingConstants.TRAILING);
		label_22.setBounds(840, 401, 55, 16);
		panelAddMov.add(label_22);
		
		panelEditMov = new JPanel();
		panelEditMov.setBackground(Color.LIGHT_GRAY);
		panelMovCards.add(panelEditMov, "name_13866982324442");
		panelEditMov.setLayout(null);
		
		comboBoxEditGenre = new JComboBox<String>();
		comboBoxEditGenre.setBounds(72, 362, 145, 29);
		panelEditMov.add(comboBoxEditGenre);
		
		comboBoxEditRating = new JComboBox<String>();
		comboBoxEditRating.setBounds(72, 403, 82, 28);
		panelEditMov.add(comboBoxEditRating);
		
		JSpinner spinnerEditMin = new JSpinner();
		spinnerEditMin.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
		spinnerEditMin.setBounds(349, 362, 54, 29);
		panelEditMov.add(spinnerEditMin);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.getViewport().setBackground(Color.GRAY);
		scrollPane_6.setBounds(0, 97, 963, 214);
		panelEditMov.add(scrollPane_6);
		
		JYearChooser yearChooserEdit = new JYearChooser();
		yearChooserEdit.setBounds(537, 323, 54, 28);
		panelEditMov.add(yearChooserEdit);
		
		JTextArea taDescriptionEdit = new JTextArea();
		taDescriptionEdit.setWrapStyleWord(true);
		taDescriptionEdit.setLineWrap(true);
		taDescriptionEdit.setBounds(537, 362, 235, 73);
		panelEditMov.add(taDescriptionEdit);
		
		tfRentRateEdit = new JTextField();
		tfRentRateEdit.setColumns(10);
		tfRentRateEdit.setBounds(349, 323, 54, 28);
		panelEditMov.add(tfRentRateEdit);
		
		tfReplaceEdit = new JTextField();
		tfReplaceEdit.setColumns(10);
		tfReplaceEdit.setBounds(349, 403, 54, 28);
		panelEditMov.add(tfReplaceEdit);
		
		tableEditMov = new JTable();
		tableEditMov.setBackground(Color.GRAY);
		tableEditMov.setForeground(Color.WHITE);
		tableEditMov.setSelectionBackground(Color.LIGHT_GRAY);
		tableEditMov.setSelectionForeground(Color.BLACK);
		tableEditMov.getTableHeader().setReorderingAllowed(false);
		tableEditMov.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEditMov.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					int row = tableEditMov.getSelectedRow();
					String Table_click = (tableEditMov.getModel().getValueAt(row, 0).toString());
					
					String query = "SELECT movieid as 'Movie ID', title as 'Title', description as 'Description',"
					+ " genre as 'Genre', release_year as 'Release Year', rental_rate as 'Rental Rate', rating as 'Rating',"
					+ " replacement_cost as 'Replacement Cost', length as 'Length (Minutes)' FROM movies WHERE movieid = ' "+Table_click+" ' ";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
				
				while(rs.next())
				{
					tfmovieID.setText(rs.getString("Movie ID"));
					tfTitle2.setText(rs.getString("Title"));
					comboBoxEditGenre.setSelectedItem(rs.getString("Genre"));
					yearChooserEdit.setYear(rs.getInt("Release Year"));
					spinnerEditMin.setValue(new Integer(rs.getInt("Length (Minutes)")));
					comboBoxEditRating.setSelectedItem(rs.getString("Rating"));
					taDescriptionEdit.setText(rs.getString("Description"));
					tfRentRateEdit.setText(rs.getString("Rental Rate"));
					tfReplaceEdit.setText(rs.getString("Replacement Cost"));
				}
				
				rs.close();
				pst.close();
				
				}catch (Exception ex) { 
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		scrollPane_6.setViewportView(tableEditMov);
		
		JButton button_4 = new JButton("");
		button_4.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/sign-check2.png")));
		button_4.setIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/sign-check.png")));
		button_4.setContentAreaFilled(false);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String empty = "";
				String title = tfTitle2.getText();
				String descrp = taDescriptionEdit.getText();
				String rent = tfRentRateEdit.getText();
			    String replc = tfReplaceEdit.getText();
			  if(title.equals(empty)|| descrp.equals(empty) || rent.equals(empty) || replc.equals(empty)){
				  JOptionPane.showMessageDialog(null,"Must fill out all fields");
				  return;
				  }
			  int action = JOptionPane.showConfirmDialog(null, "Are you sure want to edit?", "Confirm Edit", JOptionPane.YES_NO_OPTION);
				 if(action == 0){
					try{
						String value0 = tfmovieID.getText();
						String value1 = tfTitle2.getText();
						String value2 = (String)comboBoxEditGenre.getSelectedItem();
						int value3 = yearChooserEdit.getYear();
						int value4 = (Integer)spinnerEditMin.getValue();
						String value5 = (String)comboBoxEditRating.getSelectedItem();
						String value6 = taDescriptionEdit.getText();
						String value7 = tfRentRateEdit.getText();
						String value8 = tfReplaceEdit.getText();
	
						String query = "UPDATE movies SET  title = '"+ value1 +"', genre = '"+ value2
								       + "', release_year = '"+ value3 +"', length = '"+ value4
								       + "', rating = '"+ value5 +"', description = '"+ value6
									   + "', replacement_cost = '"+ value8 +"', rental_rate = '"+ value7
									   +"' WHERE movieid = "+value0+" ";
						
						PreparedStatement pst = connection.prepareStatement(query);
						pst.execute();
					
					}catch (Exception ex) { 
						JOptionPane.showMessageDialog(null, ex);
					}
					refNewMovTbl();
					refEditMovTbl();
					refAllMovTbl();
					refDelMovTbl();
				  }
			}
		});
		button_4.setBounds(907, 386, 50, 50);
		panelEditMov.add(button_4);
		
		JLabel lblReleaseYear = new JLabel("Release Year:");
		lblReleaseYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReleaseYear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblReleaseYear.setBounds(415, 323, 110, 29);
		panelEditMov.add(lblReleaseYear);
		
		tfTitle2 = new JTextField();
		tfTitle2.setColumns(10);
		tfTitle2.setBounds(72, 323, 145, 29);
		panelEditMov.add(tfTitle2);
		
		JLabel label_16 = new JLabel("Title:");
		label_16.setHorizontalAlignment(SwingConstants.RIGHT);
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_16.setBounds(6, 323, 54, 29);
		panelEditMov.add(label_16);
		
		JLabel label_17 = new JLabel("Genre:");
		label_17.setHorizontalAlignment(SwingConstants.RIGHT);
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_17.setBounds(6, 362, 54, 29);
		panelEditMov.add(label_17);
		
		JLabel lblSearchMovie_1 = new JLabel("Search Movie:");
		lblSearchMovie_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSearchMovie_1.setBounds(6, 57, 174, 28);
		panelEditMov.add(lblSearchMovie_1);
		
		comboBoxEditMov = new JComboBox<String>();
		comboBoxEditMov.setBounds(190, 57, 145, 29);
		panelEditMov.add(comboBoxEditMov);
		
		tfEditMovSearch = new JTextField();
		tfEditMovSearch.setColumns(10);
		tfEditMovSearch.setBounds(345, 57, 145, 29);
		panelEditMov.add(tfEditMovSearch);
		
		JButton btnEditMovSearch = new JButton("");
		btnEditMovSearch.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/twentyfour/search-hover.png")));
		btnEditMovSearch.setIcon(new ImageIcon(Main_Admin.class.getResource("/twentyfour/search.png")));
		btnEditMovSearch.setContentAreaFilled(false);
		btnEditMovSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String value0 = (String)comboBoxEditMov.getSelectedItem();
					String value1 = tfEditMovSearch.getText()+"%";
					String query = "SELECT movieid as 'Movie ID', title as 'Title',description as 'Description',"
							+ " genre as 'Genre', release_year as 'Release Year', rental_rate as 'Rental Rate', rating as 'Rating',"
							+ " replacement_cost as 'Replacement Cost', length as 'Length (Minutes)' FROM movies where "+ value0 +" LIKE '"+value1+"' ";
					
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tableEditMov.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					rs.close();

				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnEditMovSearch.setBounds(502, 56, 30, 30);
		panelEditMov.add(btnEditMovSearch);
		
		JLabel label_12 = new JLabel("Rating:");
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_12.setBounds(6, 403, 54, 29);
		panelEditMov.add(label_12);
		
		JLabel label_18 = new JLabel("Length:");
		label_18.setHorizontalAlignment(SwingConstants.RIGHT);
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_18.setBounds(273, 362, 64, 29);
		panelEditMov.add(label_18);
		
		JLabel lblMinutes = new JLabel("Minutes");
		lblMinutes.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinutes.setBounds(347, 347, 54, 16);
		panelEditMov.add(lblMinutes);
		
		tfmovieID = new JTextField();
		panelEditMov.add(tfmovieID);
		
		JLabel lblRentalRate = new JLabel("Rental Rate:");
		lblRentalRate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRentalRate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRentalRate.setBounds(229, 323, 110, 29);
		panelEditMov.add(lblRentalRate);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescription.setBounds(415, 362, 110, 29);
		panelEditMov.add(lblDescription);
		
		JLabel lblReplacementCost = new JLabel("Replacement Cost:");
		lblReplacementCost.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReplacementCost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblReplacementCost.setBounds(205, 403, 132, 29);
		panelEditMov.add(lblReplacementCost);
		
		JLabel lblEditMovie = new JLabel("Edit Movie");
		lblEditMovie.setForeground(Color.WHITE);
		lblEditMovie.setOpaque(true);
		lblEditMovie.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditMovie.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblEditMovie.setBackground(Color.GRAY);
		lblEditMovie.setBounds(0, 0, 963, 45);
		panelEditMov.add(lblEditMovie);
		
		JLabel label_23 = new JLabel("Submit");
		label_23.setHorizontalAlignment(SwingConstants.TRAILING);
		label_23.setBounds(840, 401, 55, 16);
		panelEditMov.add(label_23);
		
		panelDelMov = new JPanel();
		panelDelMov.setBackground(Color.LIGHT_GRAY);
		panelMovCards.add(panelDelMov, "name_13869062679929");
		panelDelMov.setLayout(null);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.getViewport().setBackground(Color.GRAY);
		scrollPane_7.setBounds(0, 98, 963, 338);
		panelDelMov.add(scrollPane_7);
		
		tableDelMov = new JTable();
		tableDelMov.setSelectionForeground(Color.BLACK);
		tableDelMov.setSelectionBackground(Color.LIGHT_GRAY);
		tableDelMov.setForeground(Color.WHITE);
		tableDelMov.setBackground(Color.GRAY);
		tableDelMov.getTableHeader().setReorderingAllowed(false);
		tableDelMov.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_7.setViewportView(tableDelMov);
		
		JButton button_5 = new JButton("");
		button_5.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/sign-ban2.png")));
		button_5.setContentAreaFilled(false);
		button_5.setIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/sign-ban48.png")));
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(null, "Are you sure want to delete?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
				  if(action == 0){
					try{
						int row = tableDelMov.getSelectedRow();
						String Table_click = (tableDelMov.getModel().getValueAt(row, 0).toString());
						
						String query = "DELETE FROM movies WHERE movieid = '"+Table_click+"' ";
						
						PreparedStatement pst = connection.prepareStatement(query);
						pst.execute();
					
					}catch (Exception ex) { 
						JOptionPane.showMessageDialog(null, ex);
					}
					refNewMovTbl();
					refEditMovTbl();
					refAllMovTbl();
					refDelMovTbl();
				  }
			}
		});
		button_5.setBounds(907, 47, 50, 50);
		panelDelMov.add(button_5);
		
		JLabel lblSearchMovie = new JLabel("Search Movie:");
		lblSearchMovie.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSearchMovie.setBounds(6, 57, 174, 28);
		panelDelMov.add(lblSearchMovie);
		
		comboBoxDelMov = new JComboBox<String>();
		comboBoxDelMov.setBounds(190, 57, 145, 29);
		panelDelMov.add(comboBoxDelMov);
		
		tfDelMovSearch = new JTextField();
		tfDelMovSearch.setColumns(10);
		tfDelMovSearch.setBounds(345, 57, 145, 29);
		panelDelMov.add(tfDelMovSearch);
		
		JButton button_3 = new JButton("");
		button_3.setContentAreaFilled(false);
		button_3.setIcon(new ImageIcon(Main_Admin.class.getResource("/twentyfour/search.png")));
		button_3.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/twentyfour/search-hover.png")));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				try{
					String value0 = (String)comboBoxDelMov.getSelectedItem();
					String value1 = tfDelMovSearch.getText()+"%";
					String query = "SELECT movieid as 'Movie ID', title as 'Title',description as 'Description',"
							+ " genre as 'Genre', release_year as 'Release Year', rental_rate as 'Rental Rate', rating as 'Rating',"
							+ " replacement_cost as 'Replacement Cost', length as 'Length (Minutes)' FROM movies where "+ value0 +" LIKE '"+value1+"' ";
					
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tableDelMov.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					rs.close();

				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		button_3.setBounds(502, 56, 30, 30);
		panelDelMov.add(button_3);
		
		JLabel lblDeleteMovie = new JLabel("Delete Movie");
		lblDeleteMovie.setForeground(Color.WHITE);
		lblDeleteMovie.setOpaque(true);
		lblDeleteMovie.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteMovie.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblDeleteMovie.setBackground(Color.GRAY);
		lblDeleteMovie.setBounds(0, 0, 963, 45);
		panelDelMov.add(lblDeleteMovie);
		
		JLabel lblDelete_2 = new JLabel("Delete");
		lblDelete_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDelete_2.setBounds(840, 64, 55, 16);
		panelDelMov.add(lblDelete_2);
		
		JButton btnViewAllMovies = new JButton("View All Movies");
		btnViewAllMovies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMovCards.removeAll();
				panelMovCards.add(panelViewMov);
				panelMovCards.repaint();
				panelMovCards.revalidate();
			}
		});
		btnViewAllMovies.setOpaque(true);
		btnViewAllMovies.setForeground(Color.WHITE);
		btnViewAllMovies.setBackground(Color.DARK_GRAY);
		btnViewAllMovies.setBounds(0, 0, 135, 30);
		panelMov.add(btnViewAllMovies);
		
		JButton btnAddNewMovie = new JButton("Add New Movie");
		btnAddNewMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMovCards.removeAll();
				panelMovCards.add(panelAddMov);
				panelMovCards.repaint();
				panelMovCards.revalidate();
			}
		});
		btnAddNewMovie.setOpaque(true);
		btnAddNewMovie.setForeground(Color.WHITE);
		btnAddNewMovie.setBackground(Color.DARK_GRAY);
		btnAddNewMovie.setBounds(135, 0, 135, 30);
		panelMov.add(btnAddNewMovie);
		
		JButton btnEditMovie = new JButton("Edit Movie");
		btnEditMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMovCards.removeAll();
				panelMovCards.add(panelEditMov);
				panelMovCards.repaint();
				panelMovCards.revalidate();
			}
		});
		btnEditMovie.setOpaque(true);
		btnEditMovie.setForeground(Color.WHITE);
		btnEditMovie.setBackground(Color.DARK_GRAY);
		btnEditMovie.setBounds(270, 0, 135, 30);
		panelMov.add(btnEditMovie);
		
		JButton btnDeleteMovie = new JButton("Delete Movie");
		btnDeleteMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMovCards.removeAll();
				panelMovCards.add(panelDelMov);
				panelMovCards.repaint();
				panelMovCards.revalidate();
			}
		});
		btnDeleteMovie.setOpaque(true);
		btnDeleteMovie.setForeground(Color.WHITE);
		btnDeleteMovie.setBackground(Color.DARK_GRAY);
		btnDeleteMovie.setBounds(405, 0, 135, 30);
		panelMov.add(btnDeleteMovie);
		
		panelAdm = new JPanel();
		panelAdm.setBackground(Color.DARK_GRAY);
		panelCards.add(panelAdm, "name_6980025516546");
		panelAdm.setLayout(null);
		
		panelAdmCards = new JPanel();
		panelAdmCards.setBounds(0, 30, 963, 439);
		panelAdm.add(panelAdmCards);
		panelAdmCards.setLayout(new CardLayout(0, 0));
		
		panelViewAdm = new JPanel();
		panelViewAdm.setBackground(Color.LIGHT_GRAY);
		panelAdmCards.add(panelViewAdm, "name_14212581228389");
		panelViewAdm.setLayout(null);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.getViewport().setBackground(Color.GRAY);
		scrollPane_8.setBounds(0, 46, 963, 393);
		panelViewAdm.add(scrollPane_8);
		
		tableViewAdm = new JTable();
		tableViewAdm.setSelectionForeground(Color.BLACK);
		tableViewAdm.setSelectionBackground(Color.LIGHT_GRAY);
		tableViewAdm.setForeground(Color.WHITE);
		tableViewAdm.setBackground(Color.GRAY);
		tableViewAdm.getTableHeader().setReorderingAllowed(false);
		tableViewAdm.getTableHeader().setResizingAllowed(false);
		tableViewAdm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableViewAdm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_8.setViewportView(tableViewAdm);
		
		JLabel lblAdministrators = new JLabel("Administrators");
		lblAdministrators.setForeground(Color.WHITE);
		lblAdministrators.setOpaque(true);
		lblAdministrators.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrators.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblAdministrators.setBackground(Color.GRAY);
		lblAdministrators.setBounds(0, 0, 963, 45);
		panelViewAdm.add(lblAdministrators);
		
		panelAddAdm = new JPanel();
		panelAddAdm.setBackground(Color.LIGHT_GRAY);
		panelAdmCards.add(panelAddAdm, "name_14216324656918");
		panelAddAdm.setLayout(null);
		
		JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.getViewport().setBackground(Color.GRAY);
		scrollPane_9.setBounds(0, 45, 963, 269);
		panelAddAdm.add(scrollPane_9);
		
		tableNewAdm = new JTable();
		tableNewAdm.setSelectionForeground(Color.BLACK);
		tableNewAdm.setSelectionBackground(Color.LIGHT_GRAY);
		tableNewAdm.setForeground(Color.WHITE);
		tableNewAdm.setBackground(Color.GRAY);
		tableNewAdm.getTableHeader().setReorderingAllowed(false);
		tableNewAdm.getTableHeader().setResizingAllowed(false);
		tableNewAdm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_9.setViewportView(tableNewAdm);
		
		JLabel label_26 = new JLabel("First Name:");
		label_26.setHorizontalAlignment(SwingConstants.RIGHT);
		label_26.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_26.setBounds(6, 326, 82, 29);
		panelAddAdm.add(label_26);
		
		JLabel label_27 = new JLabel("Last Name:");
		label_27.setHorizontalAlignment(SwingConstants.RIGHT);
		label_27.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_27.setBounds(6, 367, 82, 29);
		panelAddAdm.add(label_27);
		
		tfLastName3 = new JTextField();
		tfLastName3.setColumns(10);
		tfLastName3.setBounds(100, 368, 145, 29);
		panelAddAdm.add(tfLastName3);
		
		tfFirstName3 = new JTextField();
		tfFirstName3.setColumns(10);
		tfFirstName3.setBounds(100, 327, 145, 29);
		panelAddAdm.add(tfFirstName3);
		
		JLabel label_28 = new JLabel("Username:");
		label_28.setHorizontalAlignment(SwingConstants.RIGHT);
		label_28.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_28.setBounds(257, 326, 82, 29);
		panelAddAdm.add(label_28);
		
		JLabel label_29 = new JLabel("Password:");
		label_29.setHorizontalAlignment(SwingConstants.RIGHT);
		label_29.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_29.setBounds(257, 367, 82, 29);
		panelAddAdm.add(label_29);
		
		tfPassword3 = new JPasswordField();
		tfPassword3.setBounds(351, 368, 145, 29);
		panelAddAdm.add(tfPassword3);
		
		tfUsername3 = new JTextField();
		tfUsername3.setColumns(10);
		tfUsername3.setBounds(351, 327, 145, 29);
		panelAddAdm.add(tfUsername3);
		
		JLabel label_30 = new JLabel("Age:");
		label_30.setHorizontalAlignment(SwingConstants.RIGHT);
		label_30.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_30.setBounds(508, 326, 82, 29);
		panelAddAdm.add(label_30);
		
		tfAge3 = new JTextField();
		tfAge3.setColumns(10);
		tfAge3.setBounds(602, 327, 145, 29);
		panelAddAdm.add(tfAge3);
		
		JButton button_7 = new JButton("");
		button_7.setContentAreaFilled(false);
		button_7.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/sign-check2.png")));
		button_7.setIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/sign-check.png")));
		button_7.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String empty = "";
				String first = tfFirstName3.getText();
				String last = tfLastName3.getText();
				String age = tfAge3.getText();
				String username = tfUsername3.getText();
				String password = tfPassword3.getText();
			  if(first.equals(empty) || last.equals(empty) || age.equals(empty) || username.equals(empty) || password.equals(empty)){
				  JOptionPane.showMessageDialog(null,"Must fill out all fields");
				  return;
				  }
				int action = JOptionPane.showConfirmDialog(null, "Are you sure want to add a new administrator?", "Confirm Submission", JOptionPane.YES_NO_OPTION);
				  if(action == 0){
					  try{
					      String query3 = ("SELECT username from admins");
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
					      String query2 = ("SELECT username from admins where username = ?");
					      PreparedStatement pst2 = connection.prepareStatement(query2);
					      pst2.setString(1, tfUsername3.getText());
					      ResultSet rs2 = pst2.executeQuery();
					      if(rs2.next()){
					       if(current.equals(tfUsername3.getText())){}
					       else{
					        JOptionPane.showMessageDialog(null, "Username already exists.");
					       return;
					       }
					      }
					      }catch(Exception ex){
					       JOptionPane.showMessageDialog(null,ex);
					      }
					  try{
							String query = "INSERT INTO admins (first_name, last_name, age, username, password) VALUES (?,?,?,?,?)";
							PreparedStatement pst = connection.prepareStatement(query);
							pst.setString(1, tfFirstName3.getText() );
							pst.setString(2, tfLastName3.getText() );
							pst.setString(3, tfAge3.getText() );
							pst.setString(4, tfUsername3.getText() );
							pst.setString(5, tfPassword3.getText() );
							
							pst.execute();
							
							JOptionPane.showMessageDialog(null, "Profile Created.");
							
							pst.close();
							
						}catch(Exception ex){
							ex.printStackTrace();
						}
						refNewAdmTbl();
						refEditAdmTbl();
						refAllAdmTbl();
						refDelAdmTbl();
					  }
			}
		});
		button_7.setBounds(907, 383, 50, 50);
		panelAddAdm.add(button_7);
		
		JLabel lblAddAdministrator = new JLabel("Add Administrator");
		lblAddAdministrator.setForeground(Color.WHITE);
		lblAddAdministrator.setOpaque(true);
		lblAddAdministrator.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAdministrator.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblAddAdministrator.setBackground(Color.GRAY);
		lblAddAdministrator.setBounds(0, 0, 963, 45);
		panelAddAdm.add(lblAddAdministrator);
		
		JLabel label_20 = new JLabel("Submit");
		label_20.setHorizontalAlignment(SwingConstants.TRAILING);
		label_20.setBounds(840, 401, 55, 16);
		panelAddAdm.add(label_20);
		
		panelEditAdm = new JPanel();
		panelEditAdm.setBackground(Color.LIGHT_GRAY);
		panelAdmCards.add(panelEditAdm, "name_14218286520278");
		panelEditAdm.setLayout(null);
		
		JScrollPane scrollPane_10 = new JScrollPane();
		scrollPane_10.getViewport().setBackground(Color.GRAY);
		scrollPane_10.setBounds(0, 97, 963, 217);
		panelEditAdm.add(scrollPane_10);
		
		tableEditAdm = new JTable();
		tableEditAdm.setBackground(Color.GRAY);
		tableEditAdm.setForeground(Color.WHITE);
		tableEditAdm.setSelectionForeground(Color.BLACK);
		tableEditAdm.setSelectionBackground(Color.LIGHT_GRAY);
		tableEditAdm.getTableHeader().setReorderingAllowed(false);
		tableEditAdm.getTableHeader().setResizingAllowed(false);
		tableEditAdm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEditAdm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					int row = tableEditAdm.getSelectedRow();
					String Table_click = (tableEditAdm.getModel().getValueAt(row, 0).toString());
					
					String query = "SELECT adminid as'Admin ID', first_name as 'First Name', last_name as 'Last Name', age as 'Age', username as 'Username', password as 'Password' FROM admins WHERE adminid = ' "+Table_click+" ' ";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
				
				while(rs.next())
				{
					tfAdminID.setText(rs.getString("Admin ID"));
					tfFirstName4.setText(rs.getString("First Name"));
					tfLastName4.setText(rs.getString("Last Name"));
					tfAge4.setText(rs.getString("Age"));
					tfUsername4.setText(rs.getString("Username"));
					tfPassword4.setText(rs.getString("Password"));
				}
				
				rs.close();
				pst.close();
				
				}catch (Exception ex) { 
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		scrollPane_10.setViewportView(tableEditAdm);
		
		JLabel lblSearchAdministrator = new JLabel("Search Administrator:");
		lblSearchAdministrator.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSearchAdministrator.setBounds(6, 57, 174, 28);
		panelEditAdm.add(lblSearchAdministrator);
		
		tfEditAdmSearch = new JTextField();
		tfEditAdmSearch.setColumns(10);
		tfEditAdmSearch.setBounds(345, 57, 145, 29);
		panelEditAdm.add(tfEditAdmSearch);
		
		JButton button_8 = new JButton("");
		button_8.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/twentyfour/search-hover.png")));
		button_8.setIcon(new ImageIcon(Main_Admin.class.getResource("/twentyfour/search.png")));
		button_8.setContentAreaFilled(false);
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String value0 = (String)comboBoxEditAdm.getSelectedItem();
					String value1 = tfEditAdmSearch.getText()+"%";
					String query = "SELECT adminid as'Admin ID', first_name as 'First Name', last_name as 'Last Name', age as 'Age', username as 'Username', password as 'Password' FROM admins where " + value0 + " LIKE '"+value1+"' ";
					
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tableEditAdm.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					rs.close();

				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		button_8.setBounds(502, 56, 30, 30);
		panelEditAdm.add(button_8);
		
		JLabel label_32 = new JLabel("Password:");
		label_32.setHorizontalAlignment(SwingConstants.RIGHT);
		label_32.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_32.setBounds(257, 367, 82, 29);
		panelEditAdm.add(label_32);
		
		JLabel label_34 = new JLabel("First Name:");
		label_34.setHorizontalAlignment(SwingConstants.RIGHT);
		label_34.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_34.setBounds(6, 326, 82, 29);
		panelEditAdm.add(label_34);
		
		JLabel label_35 = new JLabel("Last Name:");
		label_35.setHorizontalAlignment(SwingConstants.RIGHT);
		label_35.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_35.setBounds(6, 367, 82, 29);
		panelEditAdm.add(label_35);
		
		tfLastName4 = new JTextField();
		tfLastName4.setColumns(10);
		tfLastName4.setBounds(100, 368, 145, 29);
		panelEditAdm.add(tfLastName4);
		
		tfFirstName4 = new JTextField();
		tfFirstName4.setColumns(10);
		tfFirstName4.setBounds(100, 327, 145, 29);
		panelEditAdm.add(tfFirstName4);
		
		JLabel label_36 = new JLabel("Username:");
		label_36.setHorizontalAlignment(SwingConstants.RIGHT);
		label_36.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_36.setBounds(257, 326, 82, 29);
		panelEditAdm.add(label_36);
		
		tfUsername4 = new JTextField();
		tfUsername4.setColumns(10);
		tfUsername4.setBounds(351, 327, 145, 29);
		panelEditAdm.add(tfUsername4);
		
		tfPassword4 = new JPasswordField();
		tfPassword4.setBounds(351, 368, 145, 29);
		panelEditAdm.add(tfPassword4);
		
		tfAdminID = new JTextField();
		panelEditAdm.add(tfAdminID);
		
		tfAge4 = new JTextField();
		tfAge4.setColumns(10);
		tfAge4.setBounds(602, 327, 145, 29);
		panelEditAdm.add(tfAge4);
		
		JLabel label_38 = new JLabel("Age:");
		label_38.setHorizontalAlignment(SwingConstants.RIGHT);
		label_38.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_38.setBounds(508, 326, 82, 29);
		panelEditAdm.add(label_38);
		
		JButton button_9 = new JButton("");
		button_9.setContentAreaFilled(false);
		button_9.setIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/sign-check.png")));
		button_9.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/sign-check2.png")));
		button_9.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String empty = "";
				String first = tfFirstName4.getText();
				String last = tfLastName4.getText();
				String age = tfAge4.getText();
				String username = tfUsername4.getText();
				String password = tfPassword4.getText();
			  if(first.equals(empty) || last.equals(empty) || age.equals(empty) || username.equals(empty) || password.equals(empty)){
				  JOptionPane.showMessageDialog(null,"Must fill out all fields");
				  return;
				  }
				int action = JOptionPane.showConfirmDialog(null, "Are you sure want to edit?", "Confirm Edit", JOptionPane.YES_NO_OPTION);
				  if(action == 0){
					  try{
					      String query3 = ("SELECT username from admins");
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
					      String query2 = ("SELECT username from admins where username = ?");
					      PreparedStatement pst2 = connection.prepareStatement(query2);
					      pst2.setString(1, tfUsername4.getText());
					      ResultSet rs2 = pst2.executeQuery();
					      if(rs2.next()){
					       if(current.equals(tfUsername4.getText())){}
					       else{
					        JOptionPane.showMessageDialog(null, "Username already exists.");
					       return;
					       }
					      }
					      }catch(Exception ex){
					       JOptionPane.showMessageDialog(null,ex);
					      }
					  try{
							String value0 = tfAdminID.getText();
							String value1 = tfFirstName4.getText();
							String value2 = tfLastName4.getText();
							String value3 = tfAge4.getText();
							String value4 = tfUsername4.getText();
							String value5 = tfPassword4.getText();
							
							String query = "UPDATE admins SET  first_name = '"+ value1 +"', last_name = '"+ value2 +
										   "', age = '"+ value3 +"', username = '"+ value4 +"', password = '"+ value5 +
										   "' WHERE adminid = "+value0+" ";
							
							PreparedStatement pst = connection.prepareStatement(query);
							pst.execute();
						
						}catch (Exception ex) { 
							JOptionPane.showMessageDialog(null, ex);
						}
						refNewAdmTbl();
						refEditAdmTbl();
						refAllAdmTbl();
						refDelAdmTbl();
				  }
			}
		});
		button_9.setBounds(907, 383, 50, 50);
		panelEditAdm.add(button_9);
		
		comboBoxEditAdm = new JComboBox<String>();
		comboBoxEditAdm.setBounds(190, 57, 145, 29);
		panelEditAdm.add(comboBoxEditAdm);
		
		JLabel lblEditAdministrator = new JLabel("Edit Administrator");
		lblEditAdministrator.setForeground(Color.WHITE);
		lblEditAdministrator.setOpaque(true);
		lblEditAdministrator.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditAdministrator.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblEditAdministrator.setBackground(Color.GRAY);
		lblEditAdministrator.setBounds(0, 0, 963, 45);
		panelEditAdm.add(lblEditAdministrator);
		
		JLabel label_21 = new JLabel("Submit");
		label_21.setHorizontalAlignment(SwingConstants.TRAILING);
		label_21.setBounds(840, 401, 55, 16);
		panelEditAdm.add(label_21);
		
		panelDelAdm = new JPanel();
		panelDelAdm.setBackground(Color.LIGHT_GRAY);
		panelAdmCards.add(panelDelAdm, "name_14219930632628");
		panelDelAdm.setLayout(null);
		
		JScrollPane scrollPane_11 = new JScrollPane();
		scrollPane_11.getViewport().setBackground(Color.GRAY);
		scrollPane_11.setBounds(0, 97, 963, 339);
		panelDelAdm.add(scrollPane_11);
		
		tableDelAdm = new JTable();
		tableDelAdm.setSelectionForeground(Color.BLACK);
		tableDelAdm.setSelectionBackground(Color.LIGHT_GRAY);
		tableDelAdm.setForeground(Color.WHITE);
		tableDelAdm.setBackground(Color.GRAY);
		tableDelAdm.getTableHeader().setReorderingAllowed(false);
		tableDelAdm.getTableHeader().setResizingAllowed(false);
		tableDelAdm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_11.setViewportView(tableDelAdm);
		
		JLabel lblSearchAdministrator_1 = new JLabel("Search Administrator:");
		lblSearchAdministrator_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSearchAdministrator_1.setBounds(6, 57, 237, 28);
		panelDelAdm.add(lblSearchAdministrator_1);
		
		tfDelAdmSearch = new JTextField();
		tfDelAdmSearch.setColumns(10);
		tfDelAdmSearch.setBounds(345, 57, 145, 29);
		panelDelAdm.add(tfDelAdmSearch);
		
		JButton button_10 = new JButton("");
		button_10.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/twentyfour/search-hover.png")));
		button_10.setIcon(new ImageIcon(Main_Admin.class.getResource("/twentyfour/search.png")));
		button_10.setContentAreaFilled(false);
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String value0 = (String)comboBoxDelAdm.getSelectedItem();
					String value1 = tfDelAdmSearch.getText()+"%";
					String query = "SELECT adminid as'Admin ID', first_name as 'First Name', last_name as 'Last Name', age as 'Age', username as 'Username', password as 'Password' FROM admins where "+value0+" LIKE '"+value1+"' ";
					
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tableDelAdm.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					rs.close();

				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		button_10.setBounds(502, 58, 30, 30);
		panelDelAdm.add(button_10);
		
		JButton button_11 = new JButton("");
		button_11.setContentAreaFilled(false);
		button_11.setIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/sign-ban48.png")));
		button_11.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/sign-ban2.png")));
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(null, "Are you sure want to delete?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
				  if(action == 0){
					try{
						int row = tableDelAdm.getSelectedRow();
						String Table_click = (tableDelAdm.getModel().getValueAt(row, 0).toString());
						
						String query = "DELETE FROM admins WHERE adminid = '"+Table_click+"' ";
						
						PreparedStatement pst = connection.prepareStatement(query);
						pst.execute();
					
					}catch (Exception ex) { 
						JOptionPane.showMessageDialog(null, ex);
					}
					refNewAdmTbl();
					refEditAdmTbl();
					refAllAdmTbl();
					refDelAdmTbl();
				  }
			}
		});
		button_11.setBounds(907, 47, 50, 50);
		panelDelAdm.add(button_11);
		
		comboBoxDelAdm = new JComboBox<String>();
		comboBoxDelAdm.setBounds(190, 57, 145, 29);
		panelDelAdm.add(comboBoxDelAdm);
		
		JLabel lblDeleteAdministrator = new JLabel("Delete Administrator");
		lblDeleteAdministrator.setForeground(Color.WHITE);
		lblDeleteAdministrator.setOpaque(true);
		lblDeleteAdministrator.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteAdministrator.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblDeleteAdministrator.setBackground(Color.GRAY);
		lblDeleteAdministrator.setBounds(0, 0, 963, 45);
		panelDelAdm.add(lblDeleteAdministrator);
		
		JLabel lblDelete_1 = new JLabel("Delete");
		lblDelete_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDelete_1.setBounds(840, 63, 55, 16);
		panelDelAdm.add(lblDelete_1);
		
		JButton btnViewAllAdministrators = new JButton("View All Administrators");
		btnViewAllAdministrators.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAdmCards.removeAll();
				panelAdmCards.add(panelViewAdm);
				panelAdmCards.repaint();
				panelAdmCards.revalidate();
			}
		});
		btnViewAllAdministrators.setOpaque(true);
		btnViewAllAdministrators.setForeground(Color.WHITE);
		btnViewAllAdministrators.setBackground(Color.DARK_GRAY);
		btnViewAllAdministrators.setBounds(0, 0, 155, 30);
		panelAdm.add(btnViewAllAdministrators);
		
		JButton btnAddNewAdministrator = new JButton("Add New Administrator");
		btnAddNewAdministrator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAdmCards.removeAll();
				panelAdmCards.add(panelAddAdm);
				panelAdmCards.repaint();
				panelAdmCards.revalidate();
			}
		});
		btnAddNewAdministrator.setOpaque(true);
		btnAddNewAdministrator.setForeground(Color.WHITE);
		btnAddNewAdministrator.setBackground(Color.DARK_GRAY);
		btnAddNewAdministrator.setBounds(155, 0, 155, 30);
		panelAdm.add(btnAddNewAdministrator);
		
		JButton btnEditAdministrator = new JButton("Edit Administrator");
		btnEditAdministrator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAdmCards.removeAll();
				panelAdmCards.add(panelEditAdm);
				panelAdmCards.repaint();
				panelAdmCards.revalidate();
			}
		});
		btnEditAdministrator.setOpaque(true);
		btnEditAdministrator.setForeground(Color.WHITE);
		btnEditAdministrator.setBackground(Color.DARK_GRAY);
		btnEditAdministrator.setBounds(310, 0, 135, 30);
		panelAdm.add(btnEditAdministrator);
		
		JButton btnDeleteAdministrator = new JButton("Delete Administrator");
		btnDeleteAdministrator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAdmCards.removeAll();
				panelAdmCards.add(panelDelAdm);
				panelAdmCards.repaint();
				panelAdmCards.revalidate();
			}
		});
		btnDeleteAdministrator.setOpaque(true);
		btnDeleteAdministrator.setForeground(Color.WHITE);
		btnDeleteAdministrator.setBackground(Color.DARK_GRAY);
		btnDeleteAdministrator.setBounds(445, 0, 140, 30);
		panelAdm.add(btnDeleteAdministrator);
		
		JPanel panelRent = new JPanel();
		panelRent.setBackground(Color.DARK_GRAY);
		panelCards.add(panelRent, "name_21985245195754");
		panelRent.setLayout(null);
		
		JPanel panelRentCards = new JPanel();
		panelRentCards.setBounds(0, 30, 963, 439);
		panelRent.add(panelRentCards);
		panelRentCards.setLayout(new CardLayout(0, 0));
		
		JPanel panelViewRentals = new JPanel();
		panelViewRentals.setBackground(Color.LIGHT_GRAY);
		panelRentCards.add(panelViewRentals, "name_17670032545699");
		panelViewRentals.setLayout(null);
		
		JScrollPane scrollPane_12 = new JScrollPane();
		scrollPane_12.getViewport().setBackground(Color.GRAY);
		scrollPane_12.setBounds(0, 46, 963, 390);
		panelViewRentals.add(scrollPane_12);
		
		tableViewRent = new JTable();
		tableViewRent.setSelectionForeground(Color.BLACK);
		tableViewRent.setSelectionBackground(Color.LIGHT_GRAY);
		tableViewRent.setForeground(Color.WHITE);
		tableViewRent.setBackground(Color.GRAY);
		tableViewRent.getTableHeader().setReorderingAllowed(false);
		tableViewRent.getTableHeader().setResizingAllowed(false);
		tableViewRent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_12.setViewportView(tableViewRent);
		
		JLabel lblRentalHistory = new JLabel("Rental History");
		lblRentalHistory.setForeground(Color.WHITE);
		lblRentalHistory.setOpaque(true);
		lblRentalHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblRentalHistory.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblRentalHistory.setBackground(Color.GRAY);
		lblRentalHistory.setBounds(0, 0, 963, 45);
		panelViewRentals.add(lblRentalHistory);
		
		JPanel panelOverdue = new JPanel();
		panelOverdue.setBackground(Color.LIGHT_GRAY);
		panelRentCards.add(panelOverdue, "name_17671714306749");
		panelOverdue.setLayout(null);
		
		JButton btnViewAllRentals = new JButton("View All Rentals");
		btnViewAllRentals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRentCards.removeAll();
				panelRentCards.add(panelViewRentals);
				panelRentCards.repaint();
				panelRentCards.revalidate();
			}
		});
		btnViewAllRentals.setOpaque(true);
		btnViewAllRentals.setForeground(Color.WHITE);
		btnViewAllRentals.setBackground(Color.DARK_GRAY);
		btnViewAllRentals.setBounds(0, 0, 135, 30);
		panelRent.add(btnViewAllRentals);
		
		JButton btnOverdueRentals = new JButton("Overdue Rentals");
		btnOverdueRentals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRentCards.removeAll();
				panelRentCards.add(panelOverdue);
				panelRentCards.repaint();
				panelRentCards.revalidate();
			}
		});
		btnOverdueRentals.setOpaque(true);
		btnOverdueRentals.setForeground(Color.WHITE);
		btnOverdueRentals.setBackground(Color.DARK_GRAY);
		btnOverdueRentals.setBounds(135, 0, 135, 30);
		panelRent.add(btnOverdueRentals);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(Color.DARK_GRAY);
		panelButtons.setBounds(0, 0, 963, 100);
		contentPane.add(panelButtons);
		
		JButton btnCustomers = new JButton("");
		btnCustomers.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/nintysix/user-male2.png")));
		btnCustomers.setContentAreaFilled(false);
		btnCustomers.setBounds(363, 0, 120, 100);
		btnCustomers.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCustomers.setIcon(new ImageIcon(Main_Admin.class.getResource("/nintysix/user-male3.png")));
		btnCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelCards.removeAll();
				panelCards.add(panelCust);
				panelCards.repaint();
				panelCards.revalidate();
				refNewCustTbl();
				refEditCustTbl();
				refAllCustTbl();
				refDelCustTbl();
				fillComboEditCust();
				fillComboDelCust();
			}
		});
		
		JButton btnMovies = new JButton("");
		btnMovies.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/nintysix/device-tv2.png")));
		btnMovies.setContentAreaFilled(false);
		btnMovies.setBounds(483, 0, 120, 100);
		btnMovies.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMovies.setIcon(new ImageIcon(Main_Admin.class.getResource("/nintysix/device-tv.png")));
		btnMovies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCards.removeAll();
				panelCards.add(panelMov);
				panelCards.repaint();
				panelCards.revalidate();
				refNewMovTbl();
				refEditMovTbl();
				refAllMovTbl();
				refDelMovTbl();
				fillComboEditMov();
				fillComboDelMov();
				fillComboNewGenre();
				fillComboNewRating();
				fillComboEditGenre();
				fillComboEditRating();
			}
		});
		
		JButton btnSettings = new JButton("");
		btnSettings.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/nintysix/keyring2.png")));
		btnSettings.setContentAreaFilled(false);
		btnSettings.setBounds(603, 0, 120, 100);
		btnSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSettings.setIcon(new ImageIcon(Main_Admin.class.getResource("/nintysix/keyring.png")));
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCards.removeAll();
				panelCards.add(panelAdm);
				panelCards.repaint();
				panelCards.revalidate();
				refNewAdmTbl();
				refEditAdmTbl();
				refAllAdmTbl();
				refDelAdmTbl();
				fillComboEditAdm();
				fillComboDelAdm();
			}
		});
		
		JButton btnRentals = new JButton("");
		btnRentals.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/nintysix/database2.png")));
		btnRentals.setContentAreaFilled(false);
		btnRentals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelCards.removeAll();
				panelCards.add(panelRent);
				panelCards.repaint();
				panelCards.revalidate();
				refAllRentTbl();
			}
		});
		
		btnRentals.setIcon(new ImageIcon(Main_Admin.class.getResource("/nintysix/database.png")));
		btnRentals.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRentals.setBounds(723, 0, 120, 100);
		
		panelButtons.setLayout(null);
		panelButtons.add(btnCustomers);
		panelButtons.add(btnMovies);
		panelButtons.add(btnSettings);
		panelButtons.add(btnRentals);
		
		JButton btnLogout = new JButton("");
		btnLogout.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/nintysix/sign-ban2.png")));
		btnLogout.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBounds(843, 0, 120, 100);
		panelButtons.add(btnLogout);
		btnLogout.setIcon(new ImageIcon(Main_Admin.class.getResource("/nintysix/sign-ban.png")));
		
		JButton button_12 = new JButton("");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCards.removeAll();
				panelCards.add(panelHome);
				panelCards.repaint();
				panelCards.revalidate();
			}
		});
		button_12.setRolloverIcon(new ImageIcon(Main_Admin.class.getResource("/nintysix/house2.png")));
		button_12.setIcon(new ImageIcon(Main_Admin.class.getResource("/nintysix/house.png")));
		button_12.setVerticalTextPosition(SwingConstants.BOTTOM);
		button_12.setOpaque(true);
		button_12.setHorizontalTextPosition(SwingConstants.CENTER);
		button_12.setForeground(Color.WHITE);
		button_12.setFocusPainted(false);
		button_12.setContentAreaFilled(false);
		button_12.setBackground(Color.DARK_GRAY);
		button_12.setBounds(243, 0, 120, 100);
		panelButtons.add(button_12);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action = JOptionPane.showConfirmDialog(null, "Are you sure want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
				if(action == 0){
				contentPane.setVisible(false);
				dispose();
				Login.main(null);
				}
			}
		});
		
		JPanel panelStatusBar = new JPanel();
		panelStatusBar.setBackground(Color.GRAY);
		panelStatusBar.setBounds(0, 569, 963, 29);
		contentPane.add(panelStatusBar);
		
		JLabel lblCurrentlyLoggedIn = new JLabel("Currently Logged in as Administrator ");
		lblCurrentlyLoggedIn.setForeground(Color.WHITE);
		lblCurrentlyLoggedIn.setBounds(6, 7, 206, 16);
		
		JLabel lblId_2 = new JLabel("ID:");
		lblId_2.setForeground(Color.WHITE);
		lblId_2.setBounds(413, 7, 27, 16);
		
		lbCurrentUserIDAdmin = new JLabel();
		lbCurrentUserIDAdmin.setForeground(Color.WHITE);
		lbCurrentUserIDAdmin.setBounds(431, 7, 55, 16);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(224, 7, 73, 16);
		
		lbCurrentUsernameAdmin = new JLabel();
		lbCurrentUsernameAdmin.setForeground(Color.WHITE);
		lbCurrentUsernameAdmin.setBounds(289, 7, 112, 16);
		panelStatusBar.setLayout(null);
		panelStatusBar.add(lblCurrentlyLoggedIn);
		panelStatusBar.add(lblUsername);
		panelStatusBar.add(lbCurrentUsernameAdmin);
		panelStatusBar.add(lblId_2);
		panelStatusBar.add(lbCurrentUserIDAdmin);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setForeground(Color.WHITE);
		lblAge.setBounds(563, 7, 27, 16);
		panelStatusBar.add(lblAge);
		
		lbCurrentAdminAge = new JLabel();
		lbCurrentAdminAge.setForeground(Color.WHITE);
		lbCurrentAdminAge.setBounds(591, 7, 55, 16);
		panelStatusBar.add(lbCurrentAdminAge);
		
		tableCurrentUsername = new JTable();
		contentPane.add(tableCurrentUsername);
	}
}
package movieRental;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

import javax.swing.border.*;
import net.proteanit.sql.DbUtils;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

public class Main_Admin extends JFrame {

	Connection connection = null;
	private JPanel contentPane;
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
	private JTextField tfSearchEditCust;
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
			String query = "SELECT movieid as 'Movie ID', title as 'Title', genre as 'Genre', release_date as 'Release Date', rating as 'Rating', length as 'Length (Minutes)' FROM movies";
			
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
			String query = "SELECT movieid as 'Movie ID', title as 'Title', genre as 'Genre', release_date as 'Release Date', rating as 'Rating', length as 'Length (Minutes)' FROM movies";
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
			String query = "SELECT movieid as 'Movie ID', title as 'Title', genre as 'Genre', release_date as 'Release Date', rating as 'Rating', length as 'Length (Minutes)' FROM movies";
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
			String query = "SELECT movieid as 'Movie ID', title as 'Title', genre as 'Genre', release_date as 'Release Date', rating as 'Rating', length as 'Length (Minutes)' FROM movies";
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
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	/**
	 * Create the frame.
	 */
	public Main_Admin() {
		setResizable(false);
		setTitle("Movie Project");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main_Admin.class.getResource("/fortyeight/device-tv.png")));
		connection = databaseConnection.dbConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 969, 541);
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
		panelCards.setBounds(150, 79, 793, 412);
		contentPane.add(panelCards);
		panelCards.setLayout(new CardLayout(0, 0));
		
		JPanel panelBlank = new JPanel();
		panelBlank.setBackground(SystemColor.textHighlight);
		panelCards.add(panelBlank, "name_145839516092709");
		
		JPanel panelCust = new JPanel();
		panelCust.setBackground(SystemColor.textHighlight);
		panelCards.add(panelCust, "Customers");
		panelCust.setLayout(null);
		
		JTabbedPane tabbedPaneCust = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneCust.setBounds(0, 0, 793, 412);
		panelCust.add(tabbedPaneCust);
		
		JPanel panelViewCust = new JPanel();
		tabbedPaneCust.addTab("View All Customers", null, panelViewCust, null);
		panelViewCust.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 780, 375);
		panelViewCust.add(scrollPane);
		
		tableViewCust = new JTable();
		scrollPane.setViewportView(tableViewCust);
		
		JPanel panelAddCust = new JPanel();
		tabbedPaneCust.addTab("Add New Customer", null, panelAddCust, null);
		panelAddCust.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 6, 780, 250);
		panelAddCust.add(scrollPane_1);
		
		tableNewCust = new JTable();
		tableNewCust.setRowSelectionAllowed(true);
		scrollPane_1.setViewportView(tableNewCust);
		
		tfFirstName = new JTextField();
		tfFirstName.setColumns(10);
		tfFirstName.setBounds(98, 309, 145, 29);
		panelAddCust.add(tfFirstName);
		
		JLabel label_1 = new JLabel("Last Name:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(6, 347, 82, 29);
		panelAddCust.add(label_1);
		
		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		tfLastName.setBounds(98, 347, 145, 29);
		panelAddCust.add(tfLastName);
		
		JLabel label_2 = new JLabel("Age:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(500, 309, 82, 29);
		panelAddCust.add(label_2);
		
		tfAge = new JTextField();
		tfAge.setColumns(10);
		tfAge.setBounds(592, 309, 145, 29);
		panelAddCust.add(tfAge);
		
		JLabel label_3 = new JLabel("Username:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(253, 307, 82, 29);
		panelAddCust.add(label_3);
		
		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		tfUsername.setBounds(345, 309, 145, 29);
		panelAddCust.add(tfUsername);
		
		JLabel label_4 = new JLabel("Password:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(253, 345, 82, 29);
		panelAddCust.add(label_4);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(345, 349, 145, 29);
		panelAddCust.add(tfPassword);
		
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  int action = JOptionPane.showConfirmDialog(null, "Are you sure want to add a new customer?", "Confirm Submission", JOptionPane.YES_NO_OPTION);
			  if(action == 0){
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
		button.setBounds(635, 347, 102, 29);
		panelAddCust.add(button);
		
		JLabel lblAddCustomerInformation = new JLabel("Add Customer Information:");
		lblAddCustomerInformation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddCustomerInformation.setBounds(6, 268, 237, 28);
		panelAddCust.add(lblAddCustomerInformation);
		
		JLabel label = new JLabel("First Name:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(6, 307, 82, 29);
		panelAddCust.add(label);
		
		JPanel panelEditCust = new JPanel();
		tabbedPaneCust.addTab("Edit Customer", null, panelEditCust, null);
		panelEditCust.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(6, 50, 780, 210);
		panelEditCust.add(scrollPane_2);
		
		tfFirstName2 = new JTextField();
		tfFirstName2.setColumns(10);
		tfFirstName2.setBounds(98, 307, 145, 29);
		panelEditCust.add(tfFirstName2);
		
		tfLastName2 = new JTextField();
		tfLastName2.setColumns(10);
		tfLastName2.setBounds(98, 347, 145, 29);
		panelEditCust.add(tfLastName2);
		
		tfAge2 = new JTextField();
		tfAge2.setColumns(10);
		tfAge2.setBounds(592, 307, 145, 29);
		panelEditCust.add(tfAge2);
		
		tfUsername2 = new JTextField();
		tfUsername2.setColumns(10);
		tfUsername2.setBounds(345, 307, 145, 29);
		panelEditCust.add(tfUsername2);
		
		tfPassword2 = new JPasswordField();
		tfPassword2.setBounds(345, 347, 145, 29);
		panelEditCust.add(tfPassword2);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String value0 = (String)comboBoxEditCust.getSelectedItem();
					String value1 = tfSearchEditCust.getText();
					String query = "SELECT userid as'Customer ID', first_name as 'First Name', last_name as 'Last Name', age as 'Age', username as 'Username', password as 'Password' FROM customers where "+ value0 +" = '"+value1+"' ";
					
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
		btnSearch.setBounds(635, 6, 102, 29);
		panelEditCust.add(btnSearch);
		
		tableEditCust = new JTable();
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
		label_9.setBounds(6, 307, 82, 29);
		panelEditCust.add(label_9);

		JLabel label_8 = new JLabel("Last Name:");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_8.setBounds(6, 347, 82, 29);
		panelEditCust.add(label_8);

		JLabel label_7 = new JLabel("Age:");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_7.setBounds(500, 305, 82, 29);
		panelEditCust.add(label_7);
		
		JLabel label_6 = new JLabel("Username:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_6.setBounds(253, 305, 82, 29);
		panelEditCust.add(label_6);
		
		JLabel label_5 = new JLabel("Password:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_5.setBounds(253, 345, 82, 29);
		panelEditCust.add(label_5);
		
		JButton button_1 = new JButton("Submit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  int action = JOptionPane.showConfirmDialog(null, "Are you sure want to edit?", "Confirm Edit", JOptionPane.YES_NO_OPTION);
			  if(action == 0){
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
		button_1.setBounds(635, 266, 102, 29);
		panelEditCust.add(button_1);
		
		JLabel lblEditCustomerInformation = new JLabel("Edit Customer Information:");
		lblEditCustomerInformation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEditCustomerInformation.setBounds(6, 267, 237, 28);
		panelEditCust.add(lblEditCustomerInformation);
		
		tfSearchEditCust = new JTextField();
		tfSearchEditCust.setColumns(10);
		tfSearchEditCust.setBounds(345, 6, 145, 29);
		panelEditCust.add(tfSearchEditCust);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblId.setBounds(500, 347, 82, 29);
		panelEditCust.add(lblId);
		
		tfCustID = new JTextField();
		tfCustID.setEditable(false);
		tfCustID.setColumns(10);
		tfCustID.setBounds(592, 347, 145, 29);
		panelEditCust.add(tfCustID);
		
		JLabel label_13 = new JLabel("Search Customer:");
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_13.setBounds(6, 6, 174, 28);
		panelEditCust.add(label_13);
		
		comboBoxEditCust = new JComboBox<String>();
		comboBoxEditCust.setBounds(190, 6, 145, 29);
		panelEditCust.add(comboBoxEditCust);
		
		JPanel panelDelCust = new JPanel();
		tabbedPaneCust.addTab("Delete Customer", null, panelDelCust, null);
		panelDelCust.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(6, 50, 780, 330);
		panelDelCust.add(scrollPane_3);
		
		tableDelCust = new JTable();
		scrollPane_3.setViewportView(tableDelCust);
		
		tfDelUserSearch = new JTextField();
		tfDelUserSearch.setColumns(10);
		tfDelUserSearch.setBounds(345, 6, 145, 29);
		panelDelCust.add(tfDelUserSearch);
		
		JButton btnDelete = new JButton("Delete");
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
		btnDelete.setBounds(685, 6, 102, 29);
		panelDelCust.add(btnDelete);
		
		JButton button_6 = new JButton("Search");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String value0 = (String)comboBoxDelCust.getSelectedItem();
					String value1 = tfDelUserSearch.getText();
					String query = "SELECT userid as'Customer ID', first_name as 'First Name', last_name as 'Last Name', age as 'Age', username as 'Username', password as 'Password' FROM customers where "+value0+" = '"+value1+"' ";
					
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
		button_6.setBounds(573, 6, 102, 29);
		panelDelCust.add(button_6);
		
		JLabel label_11 = new JLabel("Search Customer:");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_11.setBounds(6, 6, 237, 28);
		panelDelCust.add(label_11);
		
		comboBoxDelCust = new JComboBox<String>();
		comboBoxDelCust.setBounds(190, 6, 145, 29);
		panelDelCust.add(comboBoxDelCust);
		
		JPanel panelMov = new JPanel();
		panelMov.setBackground(SystemColor.textHighlight);
		panelCards.add(panelMov, "Movies");
		panelMov.setLayout(null);
		
		JTabbedPane tabbedPaneMov = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneMov.setBounds(0, 0, 793, 412);
		panelMov.add(tabbedPaneMov);
		
		JPanel panelViewMov = new JPanel();
		tabbedPaneMov.addTab("View All Movies", null, panelViewMov, null);
		panelViewMov.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(6, 6, 781, 370);
		panelViewMov.add(scrollPane_4);
		
		tableViewMov = new JTable();
		scrollPane_4.setViewportView(tableViewMov);
		
		JPanel panelAddMov = new JPanel();
		tabbedPaneMov.addTab("Add New Movie", null, panelAddMov, null);
		panelAddMov.setLayout(null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(6, 6, 781, 249);
		panelAddMov.add(scrollPane_5);
		
		tableNewMov = new JTable();
		scrollPane_5.setViewportView(tableNewMov);
		
		JLabel lblAddMovieInformation = new JLabel("Add Movie Information:");
		lblAddMovieInformation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddMovieInformation.setBounds(6, 265, 267, 28);
		panelAddMov.add(lblAddMovieInformation);
		
		tfTitle = new JTextField();
		tfTitle.setColumns(10);
		tfTitle.setBounds(70, 304, 145, 29);
		panelAddMov.add(tfTitle);
		
		JSpinner spinnerNewMin = new JSpinner();
		spinnerNewMin.setModel(new SpinnerNumberModel(0, 0, 999, 1));
		spinnerNewMin.setBounds(345, 345, 54, 29);
		panelAddMov.add(spinnerNewMin);
		
		JDateChooser dateChooserNew = new JDateChooser();
		dateChooserNew.setDateFormatString("MM-dd-yyyy");
		dateChooserNew.setBounds(345, 304, 145, 29);
		panelAddMov.add(dateChooserNew);
		
		JButton button_2 = new JButton("Submit");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  int action = JOptionPane.showConfirmDialog(null, "Are you sure want to add a new movie?", "Confirm Submission", JOptionPane.YES_NO_OPTION);
				 if(action == 0){
					try{
						String query = "INSERT INTO movies (title, genre, release_date, length, rating) VALUES (?,?,?,?,?)";
						PreparedStatement pst = connection.prepareStatement(query);
						pst.setString(1, tfTitle.getText());
						pst.setString(2, (String)comboBoxNewGenre.getSelectedItem());
						pst.setString(3, ((JTextField)dateChooserNew.getDateEditor().getUiComponent()).getText());
						pst.setInt(4, ((Integer)spinnerNewMin.getValue()));
						pst.setString(5, (String)comboBoxNewRating.getSelectedItem());
						
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
		button_2.setBounds(676, 344, 102, 29);
		panelAddMov.add(button_2);
		
		JLabel lblTitle_1 = new JLabel("Title:");
		lblTitle_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitle_1.setBounds(6, 304, 54, 29);
		panelAddMov.add(lblTitle_1);
		
		JLabel lblGenre = new JLabel("Genre:");
		lblGenre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGenre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGenre.setBounds(6, 344, 54, 29);
		panelAddMov.add(lblGenre);
		
		JLabel lblReleaseDate = new JLabel("Release Date:");
		lblReleaseDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReleaseDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblReleaseDate.setBounds(225, 304, 110, 29);
		panelAddMov.add(lblReleaseDate);
		
		JLabel lblRating = new JLabel("Rating:");
		lblRating.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRating.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRating.setBounds(500, 302, 64, 29);
		panelAddMov.add(lblRating);
		
		JLabel lblLength = new JLabel("Length:");
		lblLength.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLength.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLength.setBounds(271, 342, 64, 29);
		panelAddMov.add(lblLength);
		
		comboBoxNewGenre = new JComboBox<String>();
		comboBoxNewGenre.setBounds(70, 344, 145, 29);
		panelAddMov.add(comboBoxNewGenre);
		
		comboBoxNewRating = new JComboBox<String>();
		comboBoxNewRating.setBounds(574, 304, 82, 28);
		panelAddMov.add(comboBoxNewRating);
		
		JLabel lblMin = new JLabel("Minutes");
		lblMin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMin.setBounds(345, 330, 52, 16);
		panelAddMov.add(lblMin);
		
		JPanel panelEditMov = new JPanel();
		tabbedPaneMov.addTab("Edit Movies", null, panelEditMov, null);
		panelEditMov.setLayout(null);
		
		comboBoxEditGenre = new JComboBox<String>();
		comboBoxEditGenre.setBounds(70, 346, 145, 29);
		panelEditMov.add(comboBoxEditGenre);
		
		comboBoxEditRating = new JComboBox<String>();
		comboBoxEditRating.setBounds(574, 305, 82, 28);
		panelEditMov.add(comboBoxEditRating);
		
		JSpinner spinnerEditMin = new JSpinner();
		spinnerEditMin.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
		spinnerEditMin.setBounds(347, 346, 54, 29);
		panelEditMov.add(spinnerEditMin);
		
		JDateChooser dateChooserEdit = new JDateChooser();
		dateChooserEdit.setDateFormatString("MM-dd-yyyy");
		dateChooserEdit.setBounds(347, 305, 145, 29);
		panelEditMov.add(dateChooserEdit);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(6, 47, 781, 208);
		panelEditMov.add(scrollPane_6);
		
		tableEditMov = new JTable();
		tableEditMov.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					int row = tableEditMov.getSelectedRow();
					String Table_click = (tableEditMov.getModel().getValueAt(row, 0).toString());
					
					String query = "SELECT movieid as 'Movie ID', title as 'Title', genre as 'Genre', release_date as 'Release Date', rating as 'Rating', length as 'Length (Minutes)' FROM movies WHERE movieid = ' "+Table_click+" ' ";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
				
				while(rs.next())
				{
					tfmovieID.setText(rs.getString("Movie ID"));
					tfTitle2.setText(rs.getString("Title"));
					comboBoxEditGenre.setSelectedItem(rs.getString("Genre"));
					((JTextField)dateChooserEdit.getDateEditor().getUiComponent()).setText(rs.getString("Release Date"));
					spinnerEditMin.setValue(new Integer(rs.getInt("Length (Minutes)")));
					comboBoxEditRating.setSelectedItem(rs.getString("Rating"));
				}
				
				rs.close();
				pst.close();
				
				}catch (Exception ex) { 
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		scrollPane_6.setViewportView(tableEditMov);
		
		JButton button_4 = new JButton("Submit");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  int action = JOptionPane.showConfirmDialog(null, "Are you sure want to edit?", "Confirm Edit", JOptionPane.YES_NO_OPTION);
				 if(action == 0){
					try{
						String value0 = tfmovieID.getText();
						String value1 = tfTitle2.getText();
						String value2 = (String)comboBoxEditGenre.getSelectedItem();
						String value3 = ((JTextField)dateChooserEdit.getDateEditor().getUiComponent()).getText();
						int value4 = (Integer)spinnerEditMin.getValue();
						String value5 = (String)comboBoxEditRating.getSelectedItem();
	
						String query = "UPDATE movies SET  title = '"+ value1 +"', genre = '"+ value2 +
									   "', release_date = '"+ value3 +"', length = '"+ value4 +"', rating = '"+ value5 +
									   "' WHERE movieid = "+value0+" ";
						
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
		button_4.setBounds(685, 345, 102, 29);
		panelEditMov.add(button_4);
		
		JLabel label_10 = new JLabel("Release Date:");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_10.setBounds(225, 305, 110, 29);
		panelEditMov.add(label_10);
		
		JLabel label_15 = new JLabel("Add Movie Information:");
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_15.setBounds(6, 266, 267, 28);
		panelEditMov.add(label_15);
		
		tfTitle2 = new JTextField();
		tfTitle2.setColumns(10);
		tfTitle2.setBounds(70, 305, 145, 29);
		panelEditMov.add(tfTitle2);
		
		JLabel label_16 = new JLabel("Title:");
		label_16.setHorizontalAlignment(SwingConstants.RIGHT);
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_16.setBounds(6, 305, 54, 29);
		panelEditMov.add(label_16);
		
		JLabel label_17 = new JLabel("Genre:");
		label_17.setHorizontalAlignment(SwingConstants.RIGHT);
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_17.setBounds(6, 345, 54, 29);
		panelEditMov.add(label_17);
		
		JLabel lblSearchMovie_1 = new JLabel("Search Movie:");
		lblSearchMovie_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSearchMovie_1.setBounds(6, 8, 174, 28);
		panelEditMov.add(lblSearchMovie_1);
		
		comboBoxEditMov = new JComboBox<String>();
		comboBoxEditMov.setBounds(190, 8, 145, 29);
		panelEditMov.add(comboBoxEditMov);
		
		tfEditMovSearch = new JTextField();
		tfEditMovSearch.setColumns(10);
		tfEditMovSearch.setBounds(345, 8, 145, 29);
		panelEditMov.add(tfEditMovSearch);
		
		JButton btnEditMovSearch = new JButton("Search");
		btnEditMovSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String value0 = (String)comboBoxEditMov.getSelectedItem();
					String value1 = tfEditMovSearch.getText();
					String query = "SELECT movieid as 'Movie ID', title as 'Title', genre as 'Genre', release_date as 'Release Date', rating as 'Rating', length as 'Length (Minutes)' FROM movies where "+ value0 +" = '"+value1+"' ";
					
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
		btnEditMovSearch.setBounds(676, 10, 102, 29);
		panelEditMov.add(btnEditMovSearch);
		
		JLabel lblId_1 = new JLabel("ID:");
		lblId_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblId_1.setBounds(500, 345, 64, 29);
		panelEditMov.add(lblId_1);
		
		JLabel label_12 = new JLabel("Rating:");
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_12.setBounds(500, 305, 64, 29);
		panelEditMov.add(label_12);
		
		JLabel label_18 = new JLabel("Length:");
		label_18.setHorizontalAlignment(SwingConstants.RIGHT);
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_18.setBounds(271, 347, 64, 29);
		panelEditMov.add(label_18);
		
		JLabel lblMinutes = new JLabel("Minutes");
		lblMinutes.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinutes.setBounds(347, 332, 54, 16);
		panelEditMov.add(lblMinutes);
		
		tfmovieID = new JTextField();
		tfmovieID.setEditable(false);
		tfmovieID.setColumns(10);
		tfmovieID.setBounds(576, 345, 82, 29);
		panelEditMov.add(tfmovieID);
		
		JPanel panelDelMov = new JPanel();
		tabbedPaneMov.addTab("Delete Movies", null, panelDelMov, null);
		panelDelMov.setLayout(null);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(6, 47, 781, 329);
		panelDelMov.add(scrollPane_7);
		
		tableDelMov = new JTable();
		scrollPane_7.setViewportView(tableDelMov);
		
		JButton button_5 = new JButton("Delete");
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
		button_5.setBounds(676, 6, 102, 29);
		panelDelMov.add(button_5);
		
		JLabel lblSearchMovie = new JLabel("Search Movie:");
		lblSearchMovie.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSearchMovie.setBounds(6, 6, 174, 28);
		panelDelMov.add(lblSearchMovie);
		
		comboBoxDelMov = new JComboBox<String>();
		comboBoxDelMov.setBounds(190, 6, 145, 29);
		panelDelMov.add(comboBoxDelMov);
		
		tfDelMovSearch = new JTextField();
		tfDelMovSearch.setColumns(10);
		tfDelMovSearch.setBounds(345, 6, 145, 29);
		panelDelMov.add(tfDelMovSearch);
		
		JButton button_3 = new JButton("Search");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				try{
					String value0 = (String)comboBoxDelMov.getSelectedItem();
					String value1 = tfDelMovSearch.getText();
					String query = "SELECT movieid as 'Movie ID', title as 'Title', genre as 'Genre', release_date as 'Release Date', rating as 'Rating', length as 'Length (Minutes)' FROM movies where "+ value0 +" = '"+value1+"' ";
					
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
		button_3.setBounds(562, 6, 102, 29);
		panelDelMov.add(button_3);
		
		JPanel panelAdm = new JPanel();
		panelAdm.setBackground(SystemColor.textHighlight);
		panelCards.add(panelAdm, "name_6980025516546");
		panelAdm.setLayout(null);
		
		JTabbedPane tabbedPaneAdm = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneAdm.setBounds(0, 0, 793, 412);
		panelAdm.add(tabbedPaneAdm);
		
		JPanel panelViewAdm = new JPanel();
		tabbedPaneAdm.addTab("View All Administrators", null, panelViewAdm, null);
		panelViewAdm.setLayout(null);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(6, 6, 780, 375);
		panelViewAdm.add(scrollPane_8);
		
		tableViewAdm = new JTable();
		scrollPane_8.setViewportView(tableViewAdm);
		
		JPanel panelAddAdm = new JPanel();
		tabbedPaneAdm.addTab("Add New Administrator", null, panelAddAdm, null);
		panelAddAdm.setLayout(null);
		
		JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(6, 6, 780, 250);
		panelAddAdm.add(scrollPane_9);
		
		tableNewAdm = new JTable();
		scrollPane_9.setViewportView(tableNewAdm);
		
		JLabel lblAddAdministratorInformation = new JLabel("Add Administrator Information:");
		lblAddAdministratorInformation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddAdministratorInformation.setBounds(6, 267, 237, 28);
		panelAddAdm.add(lblAddAdministratorInformation);
		
		JLabel label_26 = new JLabel("First Name:");
		label_26.setHorizontalAlignment(SwingConstants.RIGHT);
		label_26.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_26.setBounds(6, 306, 82, 29);
		panelAddAdm.add(label_26);
		
		JLabel label_27 = new JLabel("Last Name:");
		label_27.setHorizontalAlignment(SwingConstants.RIGHT);
		label_27.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_27.setBounds(6, 346, 82, 29);
		panelAddAdm.add(label_27);
		
		tfLastName3 = new JTextField();
		tfLastName3.setColumns(10);
		tfLastName3.setBounds(98, 346, 145, 29);
		panelAddAdm.add(tfLastName3);
		
		tfFirstName3 = new JTextField();
		tfFirstName3.setColumns(10);
		tfFirstName3.setBounds(98, 308, 145, 29);
		panelAddAdm.add(tfFirstName3);
		
		JLabel label_28 = new JLabel("Username:");
		label_28.setHorizontalAlignment(SwingConstants.RIGHT);
		label_28.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_28.setBounds(253, 306, 82, 29);
		panelAddAdm.add(label_28);
		
		JLabel label_29 = new JLabel("Password:");
		label_29.setHorizontalAlignment(SwingConstants.RIGHT);
		label_29.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_29.setBounds(253, 344, 82, 29);
		panelAddAdm.add(label_29);
		
		tfPassword3 = new JPasswordField();
		tfPassword3.setBounds(345, 348, 145, 29);
		panelAddAdm.add(tfPassword3);
		
		tfUsername3 = new JTextField();
		tfUsername3.setColumns(10);
		tfUsername3.setBounds(345, 308, 145, 29);
		panelAddAdm.add(tfUsername3);
		
		JLabel label_30 = new JLabel("Age:");
		label_30.setHorizontalAlignment(SwingConstants.RIGHT);
		label_30.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_30.setBounds(500, 308, 82, 29);
		panelAddAdm.add(label_30);
		
		tfAge3 = new JTextField();
		tfAge3.setColumns(10);
		tfAge3.setBounds(592, 308, 145, 29);
		panelAddAdm.add(tfAge3);
		
		JButton button_7 = new JButton("Submit");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(null, "Are you sure want to add a new administrator?", "Confirm Submission", JOptionPane.YES_NO_OPTION);
				  if(action == 0){
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
		button_7.setBounds(635, 346, 102, 29);
		panelAddAdm.add(button_7);
		
		JPanel panelEditAdm = new JPanel();
		tabbedPaneAdm.addTab("Edit Administrator", null, panelEditAdm, null);
		panelEditAdm.setLayout(null);
		
		JScrollPane scrollPane_10 = new JScrollPane();
		scrollPane_10.setBounds(6, 50, 780, 210);
		panelEditAdm.add(scrollPane_10);
		
		tableEditAdm = new JTable();
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
		lblSearchAdministrator.setBounds(6, 6, 174, 28);
		panelEditAdm.add(lblSearchAdministrator);
		
		tfEditAdmSearch = new JTextField();
		tfEditAdmSearch.setColumns(10);
		tfEditAdmSearch.setBounds(345, 6, 145, 29);
		panelEditAdm.add(tfEditAdmSearch);
		
		JButton button_8 = new JButton("Search");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String value0 = (String)comboBoxEditAdm.getSelectedItem();
					String value1 = tfEditAdmSearch.getText();
					String query = "SELECT adminid as'Admin ID', first_name as 'First Name', last_name as 'Last Name', age as 'Age', username as 'Username', password as 'Password' FROM admins where " + value0 + " = '"+value1+"' ";
					
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
		button_8.setBounds(635, 6, 102, 29);
		panelEditAdm.add(button_8);
		
		JLabel label_32 = new JLabel("Password:");
		label_32.setHorizontalAlignment(SwingConstants.RIGHT);
		label_32.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_32.setBounds(253, 342, 82, 29);
		panelEditAdm.add(label_32);
		
		JLabel lblEditAdministratorInformation = new JLabel("Edit Administrator Information:");
		lblEditAdministratorInformation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEditAdministratorInformation.setBounds(6, 264, 237, 28);
		panelEditAdm.add(lblEditAdministratorInformation);
		
		JLabel label_34 = new JLabel("First Name:");
		label_34.setHorizontalAlignment(SwingConstants.RIGHT);
		label_34.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_34.setBounds(6, 304, 82, 29);
		panelEditAdm.add(label_34);
		
		JLabel label_35 = new JLabel("Last Name:");
		label_35.setHorizontalAlignment(SwingConstants.RIGHT);
		label_35.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_35.setBounds(6, 344, 82, 29);
		panelEditAdm.add(label_35);
		
		tfLastName4 = new JTextField();
		tfLastName4.setColumns(10);
		tfLastName4.setBounds(98, 344, 145, 29);
		panelEditAdm.add(tfLastName4);
		
		tfFirstName4 = new JTextField();
		tfFirstName4.setColumns(10);
		tfFirstName4.setBounds(98, 304, 145, 29);
		panelEditAdm.add(tfFirstName4);
		
		JLabel label_36 = new JLabel("Username:");
		label_36.setHorizontalAlignment(SwingConstants.RIGHT);
		label_36.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_36.setBounds(253, 302, 82, 29);
		panelEditAdm.add(label_36);
		
		tfUsername4 = new JTextField();
		tfUsername4.setColumns(10);
		tfUsername4.setBounds(345, 304, 145, 29);
		panelEditAdm.add(tfUsername4);
		
		tfPassword4 = new JPasswordField();
		tfPassword4.setBounds(345, 344, 145, 29);
		panelEditAdm.add(tfPassword4);
		
		JLabel label_37 = new JLabel("ID:");
		label_37.setHorizontalAlignment(SwingConstants.RIGHT);
		label_37.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_37.setBounds(500, 344, 82, 29);
		panelEditAdm.add(label_37);
		
		tfAdminID = new JTextField();
		tfAdminID.setEditable(false);
		tfAdminID.setColumns(10);
		tfAdminID.setBounds(592, 344, 145, 29);
		panelEditAdm.add(tfAdminID);
		
		tfAge4 = new JTextField();
		tfAge4.setColumns(10);
		tfAge4.setBounds(592, 304, 145, 29);
		panelEditAdm.add(tfAge4);
		
		JLabel label_38 = new JLabel("Age:");
		label_38.setHorizontalAlignment(SwingConstants.RIGHT);
		label_38.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_38.setBounds(500, 302, 82, 29);
		panelEditAdm.add(label_38);
		
		JButton button_9 = new JButton("Submit");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(null, "Are you sure want to edit?", "Confirm Edit", JOptionPane.YES_NO_OPTION);
				  if(action == 0){
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
		button_9.setBounds(635, 263, 102, 29);
		panelEditAdm.add(button_9);
		
		comboBoxEditAdm = new JComboBox<String>();
		comboBoxEditAdm.setBounds(190, 6, 145, 29);
		panelEditAdm.add(comboBoxEditAdm);
		
		JPanel panelDelAdm = new JPanel();
		tabbedPaneAdm.addTab("Delete Administrator", null, panelDelAdm, null);
		panelDelAdm.setLayout(null);
		
		JScrollPane scrollPane_11 = new JScrollPane();
		scrollPane_11.setBounds(6, 50, 780, 330);
		panelDelAdm.add(scrollPane_11);
		
		tableDelAdm = new JTable();
		scrollPane_11.setViewportView(tableDelAdm);
		
		JLabel label_19 = new JLabel("Search Customer:");
		label_19.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_19.setBounds(6, 6, 237, 28);
		panelDelAdm.add(label_19);
		
		tfDelAdmSearch = new JTextField();
		tfDelAdmSearch.setColumns(10);
		tfDelAdmSearch.setBounds(345, 8, 145, 29);
		panelDelAdm.add(tfDelAdmSearch);
		
		JButton button_10 = new JButton("Search");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String value0 = (String)comboBoxDelAdm.getSelectedItem();
					String value1 = tfDelAdmSearch.getText();
					String query = "SELECT adminid as'Admin ID', first_name as 'First Name', last_name as 'Last Name', age as 'Age', username as 'Username', password as 'Password' FROM admins where "+value0+" = '"+value1+"' ";
					
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
		button_10.setBounds(573, 8, 102, 29);
		panelDelAdm.add(button_10);
		
		JButton button_11 = new JButton("Delete");
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
		button_11.setBounds(684, 8, 102, 29);
		panelDelAdm.add(button_11);
		
		comboBoxDelAdm = new JComboBox<String>();
		comboBoxDelAdm.setBounds(190, 6, 145, 29);
		panelDelAdm.add(comboBoxDelAdm);
		
		JPanel ButtonMenu = new JPanel();
		ButtonMenu.setBackground(SystemColor.textHighlight);
		ButtonMenu.setBounds(10, 79, 130, 412);
		contentPane.add(ButtonMenu);
		
		JButton btnCustomers = new JButton("Customers");
		btnCustomers.setBounds(10, 5, 100, 90);
		btnCustomers.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCustomers.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCustomers.setIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/user-male.png")));
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
		
		JButton btnMovies = new JButton("Movies");
		btnMovies.setBounds(10, 107, 100, 90);
		btnMovies.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMovies.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnMovies.setIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/device-tv.png")));
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
		
		JButton btnSettings = new JButton("Admins");
		btnSettings.setBounds(10, 209, 100, 90);
		btnSettings.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSettings.setIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/keyring.png")));
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
		ButtonMenu.setLayout(null);
		ButtonMenu.add(btnCustomers);
		ButtonMenu.add(btnMovies);
		ButtonMenu.add(btnSettings);
		
		JButton btnRentals = new JButton("Rentals");
		btnRentals.setIcon(new ImageIcon(Main_Admin.class.getResource("/fortyeight/database.png")));
		btnRentals.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRentals.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRentals.setBounds(10, 311, 100, 90);
		ButtonMenu.add(btnRentals);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogout.setBounds(843, 11, 100, 57);
		contentPane.add(btnLogout);
		btnLogout.setIcon(new ImageIcon(Main_Admin.class.getResource("/twentyfour/sign-ban.png")));
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
	}
}
package movieRental;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import javax.swing.border.*;
import net.proteanit.sql.DbUtils;

public class Main_Cust extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldFirstName;
	private JTextField txtFieldLastName;
	private JTextField txtFieldAge;
	private JTextField txtFieldUsername;
	private JPasswordField txtFieldPassword;

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
	}
	
	Connection connection = null;
	
	private JTable tableViewNewCust;
	private JTable tableViewAllCust;
	private JTable tableViewEditCust;
	private JTextField txtFieldUser2;
	private JPasswordField txtFieldPass2;
	private JTextField txtFieldFirstName2;
	private JTextField txtFieldLastName2;
	private JTextField txtFieldAge2;
	private JTextField txtFieldSearchEditCust;
	private JTextField tfDelUserSearch;
	private JTable tableViewDelCust;
	private JTable table_3;
	private JTable table_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JPasswordField passwordField_1;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTable table_6;
	private JTextField textField_11;
	private JTextField textField_12;
	private JPasswordField passwordField_2;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTable table_7;
	private JTextField textField_15;
	private JTextField txtFieldUserID;

	public void refNewCustTbl(){
		try{
			String query = "SELECT * FROM customers";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableViewNewCust.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void refEditCustTbl(){
		try{
			String query = "SELECT * FROM customers";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableViewEditCust.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void refDelCustTbl(){
		try{
			String query = "SELECT * FROM customers";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableViewDelCust.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void refAllCustTbl(){
		try{
			String query = "SELECT * FROM customers";
			
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableViewAllCust.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Main_Cust() {
		connection = databaseConnection.dbConnection();
		
		setResizable(false);
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
		
		JPanel panelViewAllCust = new JPanel();
		tabbedPaneCust.addTab("View All Customers", null, panelViewAllCust, null);
		panelViewAllCust.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 781, 344);
		panelViewAllCust.add(scrollPane);
		
		tableViewAllCust = new JTable();
		scrollPane.setViewportView(tableViewAllCust);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refAllCustTbl();
			}
		});
		btnRefresh.setBounds(6, 350, 102, 29);
		panelViewAllCust.add(btnRefresh);
		
		JPanel panelAddCust = new JPanel();
		tabbedPaneCust.addTab("Add New Customer", null, panelAddCust, null);
		panelAddCust.setLayout(null);
		
		txtFieldFirstName = new JTextField();
		txtFieldFirstName.setColumns(10);
		txtFieldFirstName.setBounds(98, 309, 145, 29);
		panelAddCust.add(txtFieldFirstName);
		
		JLabel label_1 = new JLabel("Last Name:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(6, 347, 82, 29);
		panelAddCust.add(label_1);
		
		txtFieldLastName = new JTextField();
		txtFieldLastName.setColumns(10);
		txtFieldLastName.setBounds(98, 347, 145, 29);
		panelAddCust.add(txtFieldLastName);
		
		JLabel label_2 = new JLabel("Age:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(500, 309, 82, 29);
		panelAddCust.add(label_2);
		
		txtFieldAge = new JTextField();
		txtFieldAge.setColumns(10);
		txtFieldAge.setBounds(592, 309, 145, 29);
		panelAddCust.add(txtFieldAge);
		
		JLabel label_3 = new JLabel("Username:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(253, 307, 82, 29);
		panelAddCust.add(label_3);
		
		txtFieldUsername = new JTextField();
		txtFieldUsername.setColumns(10);
		txtFieldUsername.setBounds(345, 309, 145, 29);
		panelAddCust.add(txtFieldUsername);
		
		JLabel label_4 = new JLabel("Password:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(253, 345, 82, 29);
		panelAddCust.add(label_4);
		
		txtFieldPassword = new JPasswordField();
		txtFieldPassword.setBounds(345, 349, 145, 29);
		panelAddCust.add(txtFieldPassword);
		
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query = "INSERT INTO customers (first_name, last_name, age, username, password) VALUES (?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, txtFieldFirstName.getText() );
					pst.setString(2, txtFieldLastName.getText() );
					pst.setString(3, txtFieldAge.getText() );
					pst.setString(4, txtFieldUsername.getText() );
					pst.setString(5, txtFieldPassword.getText() );
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Profile Created.");
					
					pst.close();
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
				refNewCustTbl();
			}
		});
		button.setBounds(635, 347, 102, 29);
		panelAddCust.add(button);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 6, 781, 249);
		panelAddCust.add(scrollPane_1);
		
		tableViewNewCust = new JTable();
		tableViewNewCust.setRowSelectionAllowed(true);
		scrollPane_1.setViewportView(tableViewNewCust);
		
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
		scrollPane_2.setBounds(6, 47, 781, 208);
		panelEditCust.add(scrollPane_2);
		
		txtFieldFirstName2 = new JTextField();
		txtFieldFirstName2.setColumns(10);
		txtFieldFirstName2.setBounds(98, 307, 145, 29);
		panelEditCust.add(txtFieldFirstName2);
		
		txtFieldLastName2 = new JTextField();
		txtFieldLastName2.setColumns(10);
		txtFieldLastName2.setBounds(98, 347, 145, 29);
		panelEditCust.add(txtFieldLastName2);
		
		txtFieldAge2 = new JTextField();
		txtFieldAge2.setColumns(10);
		txtFieldAge2.setBounds(592, 307, 145, 29);
		panelEditCust.add(txtFieldAge2);
		
		txtFieldUser2 = new JTextField();
		txtFieldUser2.setColumns(10);
		txtFieldUser2.setBounds(345, 307, 145, 29);
		panelEditCust.add(txtFieldUser2);
		
		txtFieldPass2 = new JPasswordField();
		txtFieldPass2.setBounds(345, 347, 145, 29);
		panelEditCust.add(txtFieldPass2);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String value1 = txtFieldSearchEditCust.getText();
					String query = "SELECT * FROM customers where username = '"+value1+"' ";
					
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tableViewEditCust.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					rs.close();

				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(635, 6, 102, 29);
		panelEditCust.add(btnSearch);
		
		tableViewEditCust = new JTable();
		tableViewEditCust.setRowSelectionAllowed(true);
		tableViewEditCust.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					int row = tableViewEditCust.getSelectedRow();
					String Table_click = (tableViewEditCust.getModel().getValueAt(row, 0).toString());
					
					String query = "SELECT * FROM customers WHERE userid = ' "+Table_click+" ' ";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
				
				while(rs.next())
				{
					txtFieldUserID.setText(rs.getString("userid"));
					txtFieldFirstName2.setText(rs.getString("first_name"));
					txtFieldLastName2.setText(rs.getString("last_name"));
					txtFieldAge2.setText(rs.getString("age"));
					txtFieldUser2.setText(rs.getString("username"));
					txtFieldPass2.setText(rs.getString("password"));
				}
				
				rs.close();
				pst.close();
				
				}catch (Exception ex) { 
					JOptionPane.showMessageDialog(null, ex);
				}
				
			}
		});
		scrollPane_2.setViewportView(tableViewEditCust);
		
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
				try{
					String value0 = txtFieldUserID.getText();
					String value1 = txtFieldFirstName2.getText();
					String value2 = txtFieldLastName2.getText();
					String value3 = txtFieldAge2.getText();
					String value4 = txtFieldUser2.getText();
					String value5 = txtFieldPass2.getText();
					
					String query = "UPDATE customers SET  first_name = '"+ value1 +"', last_name = '"+ value2 +
								   "', age = '"+ value3 +"', username = '"+ value4 +"', password = '"+ value5 +
								   "' WHERE userid = "+value0+" ";
					
					PreparedStatement pst = connection.prepareStatement(query);
					pst.execute();
				
				}catch (Exception ex) { 
					JOptionPane.showMessageDialog(null, ex);
				}
				refEditCustTbl();
				
			}
		});
		button_1.setBounds(635, 266, 102, 29);
		panelEditCust.add(button_1);
		
		JLabel label_10 = new JLabel("Username:");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_10.setBounds(253, 7, 82, 29);
		panelEditCust.add(label_10);
		
		JLabel lblEditCustomerInformation = new JLabel("Edit Customer Information:");
		lblEditCustomerInformation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEditCustomerInformation.setBounds(6, 267, 237, 28);
		panelEditCust.add(lblEditCustomerInformation);
		
		txtFieldSearchEditCust = new JTextField();
		txtFieldSearchEditCust.setColumns(10);
		txtFieldSearchEditCust.setBounds(345, 6, 145, 29);
		panelEditCust.add(txtFieldSearchEditCust);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblId.setBounds(500, 347, 82, 29);
		panelEditCust.add(lblId);
		
		txtFieldUserID = new JTextField();
		txtFieldUserID.setEditable(false);
		txtFieldUserID.setColumns(10);
		txtFieldUserID.setBounds(592, 347, 145, 29);
		panelEditCust.add(txtFieldUserID);
		
		JLabel label_13 = new JLabel("Search Customer:");
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_13.setBounds(6, 6, 237, 28);
		panelEditCust.add(label_13);
		
		JPanel panelDeleteCust = new JPanel();
		tabbedPaneCust.addTab("Delete Customer", null, panelDeleteCust, null);
		panelDeleteCust.setLayout(null);
		
		tfDelUserSearch = new JTextField();
		tfDelUserSearch.setColumns(10);
		tfDelUserSearch.setBounds(345, 6, 145, 29);
		panelDeleteCust.add(tfDelUserSearch);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int row = tableViewDelCust.getSelectedRow();
					String Table_click = (tableViewDelCust.getModel().getValueAt(row, 0).toString());
					
					String query = "DELETE FROM customers WHERE userid = '"+Table_click+"' ";
					
					PreparedStatement pst = connection.prepareStatement(query);
					pst.execute();
				
				}catch (Exception ex) { 
					JOptionPane.showMessageDialog(null, ex);
				}
				refDelCustTbl();
			}
		});
		btnDelete.setBounds(685, 6, 102, 29);
		panelDeleteCust.add(btnDelete);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(6, 47, 781, 329);
		panelDeleteCust.add(scrollPane_3);
		
		tableViewDelCust = new JTable();
		scrollPane_3.setViewportView(tableViewDelCust);
		
		JButton button_6 = new JButton("Search");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String value1 = tfDelUserSearch.getText();
					String query = "SELECT * FROM customers where username = '"+value1+"' ";
					
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tableViewDelCust.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					rs.close();

				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		button_6.setBounds(573, 6, 102, 29);
		panelDeleteCust.add(button_6);
		
		JLabel label_11 = new JLabel("Search Customer:");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_11.setBounds(6, 6, 237, 28);
		panelDeleteCust.add(label_11);
		
		JLabel label_12 = new JLabel("Username:");
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_12.setBounds(253, 6, 82, 29);
		panelDeleteCust.add(label_12);
		
		JPanel panelMov = new JPanel();
		panelMov.setBackground(SystemColor.textHighlight);
		panelCards.add(panelMov, "Movies");
		panelMov.setLayout(null);
		
		JTabbedPane tabbedPaneMov = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneMov.setBounds(0, 0, 793, 412);
		panelMov.add(tabbedPaneMov);
		
		JPanel panelViewAllMov = new JPanel();
		tabbedPaneMov.addTab("View All Movies", null, panelViewAllMov, null);
		panelViewAllMov.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(6, 6, 781, 370);
		panelViewAllMov.add(scrollPane_4);
		
		table_3 = new JTable();
		scrollPane_4.setViewportView(table_3);
		
		JPanel panelAddMov = new JPanel();
		tabbedPaneMov.addTab("Add New Movie", null, panelAddMov, null);
		panelAddMov.setLayout(null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(6, 6, 781, 249);
		panelAddMov.add(scrollPane_5);
		
		table_5 = new JTable();
		scrollPane_5.setViewportView(table_5);
		
		JLabel lblAddMovieInformation = new JLabel("Add Movie Information:");
		lblAddMovieInformation.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAddMovieInformation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddMovieInformation.setBounds(6, 267, 267, 28);
		panelAddMov.add(lblAddMovieInformation);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(126, 307, 145, 29);
		panelAddMov.add(textField_6);
		
		JLabel label_14 = new JLabel("First Name:");
		label_14.setHorizontalAlignment(SwingConstants.RIGHT);
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_14.setBounds(6, 307, 110, 29);
		panelAddMov.add(label_14);
		
		JLabel label_15 = new JLabel("Last Name:");
		label_15.setHorizontalAlignment(SwingConstants.RIGHT);
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_15.setBounds(6, 347, 110, 29);
		panelAddMov.add(label_15);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(126, 347, 145, 29);
		panelAddMov.add(textField_7);
		
		JLabel label_16 = new JLabel("Password:");
		label_16.setHorizontalAlignment(SwingConstants.RIGHT);
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_16.setBounds(283, 347, 110, 29);
		panelAddMov.add(label_16);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(403, 347, 145, 29);
		panelAddMov.add(passwordField_1);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(403, 307, 145, 29);
		panelAddMov.add(textField_8);
		
		JLabel label_17 = new JLabel("Username:");
		label_17.setHorizontalAlignment(SwingConstants.RIGHT);
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_17.setBounds(283, 307, 110, 29);
		panelAddMov.add(label_17);
		
		JLabel label_18 = new JLabel("Age:");
		label_18.setHorizontalAlignment(SwingConstants.RIGHT);
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_18.setBounds(283, 267, 110, 29);
		panelAddMov.add(label_18);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(403, 267, 145, 29);
		panelAddMov.add(textField_9);
		
		JButton button_2 = new JButton("Submit");
		button_2.setBounds(685, 348, 102, 29);
		panelAddMov.add(button_2);
		
		JPanel panelEditMov = new JPanel();
		tabbedPaneMov.addTab("Edit Movies", null, panelEditMov, null);
		panelEditMov.setLayout(null);
		
		JLabel lblSearchMovie_1 = new JLabel("Search Movie:");
		lblSearchMovie_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSearchMovie_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSearchMovie_1.setBounds(6, 7, 267, 28);
		panelEditMov.add(lblSearchMovie_1);
		
		JLabel label_20 = new JLabel("Username:");
		label_20.setHorizontalAlignment(SwingConstants.RIGHT);
		label_20.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_20.setBounds(283, 6, 110, 29);
		panelEditMov.add(label_20);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(403, 6, 145, 29);
		panelEditMov.add(textField_10);
		
		JButton button_3 = new JButton("Search");
		button_3.setBounds(685, 6, 102, 29);
		panelEditMov.add(button_3);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(6, 47, 781, 208);
		panelEditMov.add(scrollPane_6);
		
		table_6 = new JTable();
		scrollPane_6.setViewportView(table_6);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(403, 267, 145, 29);
		panelEditMov.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(403, 307, 145, 29);
		panelEditMov.add(textField_12);
		
		JLabel label_21 = new JLabel("Username:");
		label_21.setHorizontalAlignment(SwingConstants.RIGHT);
		label_21.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_21.setBounds(283, 307, 110, 29);
		panelEditMov.add(label_21);
		
		JLabel label_22 = new JLabel("Age:");
		label_22.setHorizontalAlignment(SwingConstants.RIGHT);
		label_22.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_22.setBounds(283, 267, 110, 29);
		panelEditMov.add(label_22);
		
		JLabel label_23 = new JLabel("Password:");
		label_23.setHorizontalAlignment(SwingConstants.RIGHT);
		label_23.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_23.setBounds(283, 347, 110, 29);
		panelEditMov.add(label_23);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(403, 347, 145, 29);
		panelEditMov.add(passwordField_2);
		
		JButton button_4 = new JButton("Submit");
		button_4.setBounds(685, 347, 102, 29);
		panelEditMov.add(button_4);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(126, 347, 145, 29);
		panelEditMov.add(textField_13);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(126, 307, 145, 29);
		panelEditMov.add(textField_14);
		
		JLabel label_24 = new JLabel("First Name:");
		label_24.setHorizontalAlignment(SwingConstants.RIGHT);
		label_24.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_24.setBounds(6, 307, 110, 29);
		panelEditMov.add(label_24);
		
		JLabel label_25 = new JLabel("Last Name:");
		label_25.setHorizontalAlignment(SwingConstants.RIGHT);
		label_25.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_25.setBounds(6, 347, 110, 29);
		panelEditMov.add(label_25);
		
		JLabel lblEditMovieInformation = new JLabel("Edit Movie Information:");
		lblEditMovieInformation.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEditMovieInformation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEditMovieInformation.setBounds(6, 267, 267, 28);
		panelEditMov.add(lblEditMovieInformation);
		
		JPanel panelDeleteMov = new JPanel();
		tabbedPaneMov.addTab("Delete Movies", null, panelDeleteMov, null);
		panelDeleteMov.setLayout(null);
		
		JLabel lblSearchMovie = new JLabel("Search Movie:");
		lblSearchMovie.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSearchMovie.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSearchMovie.setBounds(6, 7, 267, 28);
		panelDeleteMov.add(lblSearchMovie);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(6, 47, 781, 329);
		panelDeleteMov.add(scrollPane_7);
		
		table_7 = new JTable();
		scrollPane_7.setViewportView(table_7);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitle.setBounds(283, 6, 110, 29);
		panelDeleteMov.add(lblTitle);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(403, 6, 145, 29);
		panelDeleteMov.add(textField_15);
		
		JButton button_5 = new JButton("Delete");
		button_5.setBounds(685, 6, 102, 29);
		panelDeleteMov.add(button_5);
		
		JPanel ButtonMenu = new JPanel();
		ButtonMenu.setBackground(SystemColor.textHighlight);
		ButtonMenu.setBounds(10, 79, 130, 412);
		contentPane.add(ButtonMenu);
		
		JButton btnCustomers = new JButton("Customers");
		btnCustomers.setEnabled(false);
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
				refNewCustTbl();
				refEditCustTbl();
				refAllCustTbl();
				refDelCustTbl();
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
			}
		});
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(10, 312, 100, 90);
		btnLogout.setIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/sign-ban48.png")));
		btnLogout.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogout.setVerticalTextPosition(SwingConstants.BOTTOM);
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
		
		JButton btnSettings = new JButton("Admins");
		btnSettings.setEnabled(false);
		btnSettings.setBounds(10, 209, 100, 90);
		btnSettings.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSettings.setIcon(new ImageIcon(Main_Cust.class.getResource("/fortyeight/keyring.png")));
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ButtonMenu.setLayout(null);
		ButtonMenu.add(btnCustomers);
		ButtonMenu.add(btnMovies);
		ButtonMenu.add(btnLogout);
		ButtonMenu.add(btnSettings);
	}
}
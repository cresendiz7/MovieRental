package movieRental;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import javax.swing.border.*;
import net.proteanit.sql.DbUtils;

public class Main_Cust extends JFrame {

	Connection connection = null;
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

	/**
	 * Create the frame.
	 */
	public Main_Cust() {
		connection = databaseConnection.dbConnection();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 969, 552);
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
					 String query2 = ("SELECT username from customers where username = ?");
					 PreparedStatement pst2 = connection.prepareStatement(query2);
					 pst2.setString(1, tfUsername.getText());
					 ResultSet rs2 = pst2.executeQuery();
					 if(rs2.next()){
						 if(rs2.getString(1).equals(tfUsername.getText())){
							 JOptionPane.showMessageDialog(null, "Username same."); 
						 }else{
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
		
		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblId.setBounds(31, 303, 82, 29);
		panelEditCust.add(lblId);
		
		tfUserID = new JTextField();
		tfUserID.setEditable(false);
		tfUserID.setColumns(10);
		tfUserID.setBounds(125, 304, 145, 29);
		panelEditCust.add(tfUserID);
		
		JPanel panelMov = new JPanel();
		panelMov.setBackground(SystemColor.textHighlight);
		panelCards.add(panelMov, "Movies");
		panelMov.setLayout(null);
		
		JTabbedPane tabbedPaneMov = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneMov.setBounds(0, 0, 793, 412);
		panelMov.add(tabbedPaneMov);
		
		JPanel panel_2 = new JPanel();
		tabbedPaneMov.addTab("View Current Rentals", null, panel_2, null);
		
		JPanel panelViewAllMov = new JPanel();
		tabbedPaneMov.addTab("Rent Movie", null, panelViewAllMov, null);
		panelViewAllMov.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(6, 6, 781, 321);
		panelViewAllMov.add(scrollPane_4);
		
		tableViewMov = new JTable();
		scrollPane_4.setViewportView(tableViewMov);
		
		JButton btnRent = new JButton("Rent");
		btnRent.setBounds(6, 339, 90, 28);
		panelViewAllMov.add(btnRent);
		
		JPanel panel_1 = new JPanel();
		tabbedPaneMov.addTab("Return Movie", null, panel_1, null);
		
		JPanel ButtonMenu = new JPanel();
		ButtonMenu.setBackground(SystemColor.textHighlight);
		ButtonMenu.setBounds(10, 79, 130, 412);
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
		ButtonMenu.setLayout(null);
		ButtonMenu.add(btnCustomers);
		ButtonMenu.add(btnMovies);
		ButtonMenu.add(btnLogout);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 494, 963, 29);
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
		lbCurrentUserIDCust.setBounds(408, 7, 55, 16);
		panel.add(lbCurrentUserIDCust);
	}
}
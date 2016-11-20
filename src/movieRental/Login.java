package movieRental;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.sql.*;

public class Login{

	private JFrame frmLogin;
	private JPasswordField Password;
	private JTextField Username;	
	private JTable tableCurrentIDAdmin;
	private JTable tableCurrentIDCust;
	private String str;
	private String name;
	private String age;

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
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		getUserID();
		connection = databaseConnection.dbConnection();
	}
	
	Action signup = new AbstractAction() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -999937965029355453L;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			frmLogin.dispose();
			Signup sign = new Signup();
			sign.setVisible(true);
		}
	};

	/**
	 * Initialize the contents of the frame.
	 */
	private void getUserID() {
		
	}
	private void initialize() {
		frmLogin =  new JFrame();
		frmLogin.setBackground(Color.DARK_GRAY);
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/fortyeight/device-tv.png")));
		frmLogin.setResizable(false);
		frmLogin.setTitle("Login");
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.setBounds(100, 100, 400, 300);
		frmLogin.getContentPane().setBackground(Color.DARK_GRAY);
		frmLogin.getContentPane().setLayout(null);
		
		JCheckBox checkBoxAdmin = new JCheckBox("Admin?");
		checkBoxAdmin.setForeground(Color.WHITE);
		checkBoxAdmin.setBounds(287, 107, 75, 29);
		frmLogin.getContentPane().add(checkBoxAdmin);
		
		JButton btnLogin = new JButton("");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setRolloverIcon(new ImageIcon(Login.class.getResource("/twentyfour/sign-check2.png")));
		btnLogin.setBackground(Color.DARK_GRAY);
		btnLogin.setIcon(new ImageIcon(Login.class.getResource("/twentyfour/sign-check.png")));
		btnLogin.setBounds(120, 187, 85, 30);
		frmLogin.getContentPane().add(btnLogin);
		btnLogin.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(checkBoxAdmin.isSelected()){
					try{
						String query = "SELECT * FROM admins WHERE username = ? and password = ?";
						PreparedStatement pst = connection.prepareStatement(query);
						pst.setString(1, Username.getText() );
						pst.setString(2, Password.getText() );
						
						ResultSet rs = pst.executeQuery();
						int count = 0;
						while(rs.next()){
							count++;
						}
						if(count == 1){
							try{
								String query2 = "SELECT adminid,first_name,age from admins where username = '"+ Username.getText() +"'";
								PreparedStatement pst2 = connection.prepareStatement(query2);
								ResultSet rs2 = pst2.executeQuery();
									
								tableCurrentIDAdmin.setModel(DbUtils.resultSetToTableModel(rs2));
								int row =0;
								int column = 0;
								int column2 = 1;
								int column3 = 2;
								str = (tableCurrentIDAdmin.getModel().getValueAt(row,column).toString());
								name = (tableCurrentIDAdmin.getModel().getValueAt(row,column2).toString());
								age = (tableCurrentIDAdmin.getModel().getValueAt(row,column3).toString());
								
							}catch (Exception ex) { 
								JOptionPane.showMessageDialog(null, ex);
							}
							
							JOptionPane.showMessageDialog(null, "Username and Password is correct");
							Main_Admin wel = new Main_Admin();
							Main_Admin.lbCurrentUserIDAdmin.setText(str);
							Main_Admin.lbNameAdmin.setText(name);
							Main_Admin.lbCurrentUsernameAdmin.setText(Username.getText());
							Main_Admin.lbCurrentAdminAge.setText(age);
							wel.setVisible(true);
							frmLogin.dispose();
						}
						else if(count > 1){
							JOptionPane.showMessageDialog(null, "Duplicate Username and Password");
						}
						else{
							JOptionPane.showMessageDialog(null, "Username/Password is not correct. Try Again.");
						}
						rs.close();
						pst.close();
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null,ex);
				}
				}else{
					try{
						String query = "SELECT * FROM customers WHERE username = ? and password = ?";
						PreparedStatement pst = connection.prepareStatement(query);
						pst.setString(1, Username.getText() );
						pst.setString(2, Password.getText() );
						
						ResultSet rs = pst.executeQuery();
						int count = 0;
						while(rs.next()){
							count++;
						}
						if(count == 1){
							try{
								String query2 = "SELECT userid,first_name,age from customers where username = '"+ Username.getText() +"'";
								PreparedStatement pst2 = connection.prepareStatement(query2);
								ResultSet rs2 = pst2.executeQuery();
									
								tableCurrentIDCust.setModel(DbUtils.resultSetToTableModel(rs2));
								int row =0;
								int column = 0;
								int column2 = 1;
								int column3 = 2;
								str = (tableCurrentIDCust.getModel().getValueAt(row,column).toString());toString();
								name = (tableCurrentIDCust.getModel().getValueAt(row,column2).toString());
								age = (tableCurrentIDCust.getModel().getValueAt(row,column3).toString());
								
							}catch (Exception ex) { 
								JOptionPane.showMessageDialog(null, ex);
							}
							
							JOptionPane.showMessageDialog(null, "Username and Password is correct");
							Main_Cust window = new Main_Cust();
							Main_Cust.lbCurrentUserIDCust.setText(str);
							Main_Cust.lbNameCust.setText(name);
							Main_Cust.lbCurrentUsernameCust.setText(Username.getText());
							Main_Cust.lbCurrentCustAge.setText(age);
							window.setVisible(true);
							frmLogin.dispose();
						}
						else if(count > 1){
							JOptionPane.showMessageDialog(null, "Duplicate Username and Password");
						}
						else{
							JOptionPane.showMessageDialog(null, "Username/Password is not correct. Try Again.");
						}
						
						rs.close();
						pst.close();
					}catch(Exception ex){
						ex.printStackTrace();
				}
				}
		}
		});
		
		Password = new JPasswordField();
		Password.setBounds(130, 147, 145, 29);
		frmLogin.getContentPane().add(Password);
		
		Username = new JTextField();
		Username.setBounds(130, 107, 145, 29);
		frmLogin.getContentPane().add(Username);
		Username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(10, 147, 110, 29);
		frmLogin.getContentPane().add(lblPassword);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(10, 107, 110, 29);
		frmLogin.getContentPane().add(lblUsername);
		
		JLabel lblUserLogin = new JLabel("Login");
		lblUserLogin.setBackground(Color.GRAY);
		lblUserLogin.setForeground(Color.WHITE);
		lblUserLogin.setOpaque(true);
		lblUserLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserLogin.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblUserLogin.setBounds(0, 0, 394, 72);
		frmLogin.getContentPane().add(lblUserLogin);
		
		JButton btnCustomerSignUp = new JButton("");
		btnCustomerSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCustomerSignUp.setHorizontalAlignment(SwingConstants.LEADING);
		btnCustomerSignUp.setRolloverIcon(new ImageIcon(Login.class.getResource("/twentyfour/user-id2.png")));
		btnCustomerSignUp.setContentAreaFilled(false);
		btnCustomerSignUp.setIcon(new ImageIcon(Login.class.getResource("/twentyfour/user-id.png")));
		btnCustomerSignUp.addActionListener(signup); 
		btnCustomerSignUp.setBounds(120, 227, 103, 30);
		frmLogin.getContentPane().add(btnCustomerSignUp);
		
		tableCurrentIDAdmin = new JTable();
		frmLogin.getContentPane().add(tableCurrentIDAdmin);
		
		tableCurrentIDCust = new JTable();
		frmLogin.getContentPane().add(tableCurrentIDCust);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLogin.setBounds(160, 188, 110, 29);
		frmLogin.getContentPane().add(lblLogin);
		
		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setForeground(Color.WHITE);
		lblSignUp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSignUp.setBounds(160, 228, 110, 29);
		frmLogin.getContentPane().add(lblSignUp);
	}
}

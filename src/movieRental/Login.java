package movieRental;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login{

	private JFrame frmLogin;
	private JPasswordField Password;
	private JTextField Username;
	
	///sdf
	///ssdf
	///sdfsdf
	//asdasd

	//sfsdfdfd	

	///sdfsdf

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
		connection = databaseConnection.dbConnection();
	}
	
	Action signup = new AbstractAction() {
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
	private void initialize() {
		frmLogin =  new JFrame();
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/fortyeight/device-tv.png")));
		frmLogin.setResizable(false);
		frmLogin.setTitle("Login");
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.setBounds(100, 100, 400, 300);
		frmLogin.getContentPane().setBackground(SystemColor.textHighlight);
		frmLogin.getContentPane().setLayout(null);
		
		JCheckBox checkBoxAdmin = new JCheckBox("Admin?");
		checkBoxAdmin.setBounds(287, 107, 75, 29);
		frmLogin.getContentPane().add(checkBoxAdmin);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon(Login.class.getResource("/twentyfour/sign-check.png")));
		btnLogin.setBounds(130, 187, 145, 29);
		frmLogin.getContentPane().add(btnLogin);
		btnLogin.addActionListener(new ActionListener(){
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
							JOptionPane.showMessageDialog(null, "Username and Password is correct");
							frmLogin.dispose();
							Main_Admin wel = new Main_Admin();
							wel.setVisible(true);
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
							JOptionPane.showMessageDialog(null, "Username and Password is correct");
							frmLogin.dispose();
							Main_Cust window = new Main_Cust();
							window.setVisible(true);
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
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(10, 147, 110, 29);
		frmLogin.getContentPane().add(lblPassword);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(10, 107, 110, 29);
		frmLogin.getContentPane().add(lblUsername);
		
		JLabel lblUserLogin = new JLabel("Login");
		lblUserLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserLogin.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblUserLogin.setBounds(10, 11, 374, 61);
		frmLogin.getContentPane().add(lblUserLogin);
		
		JButton btnCustomerSignUp = new JButton("Sign up");
		btnCustomerSignUp.setIcon(new ImageIcon(Login.class.getResource("/twentyfour/user-id.png")));
		btnCustomerSignUp.addActionListener(signup); 
		btnCustomerSignUp.setBounds(130, 227, 145, 29);
		frmLogin.getContentPane().add(btnCustomerSignUp);
	}
}

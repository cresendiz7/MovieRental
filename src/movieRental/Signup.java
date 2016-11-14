package movieRental;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;


public class Signup extends JFrame {

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
					Signup frame = new Signup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;

	/**
	 * Create the frame.
	 */
	public Signup() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Signup.class.getResource("/fortyeight/user-id.png")));
		setTitle("Sign Up");
		setResizable(false);
		connection = databaseConnection.dbConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCustomerSignUp = new JLabel("Sign Up");
		lblCustomerSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerSignUp.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblCustomerSignUp.setBounds(6, 13, 482, 73);
		contentPane.add(lblCustomerSignUp);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setBounds(10, 117, 110, 29);
		contentPane.add(lblFirstName);
		
		txtFieldFirstName = new JTextField();
		txtFieldFirstName.setBounds(130, 117, 145, 29);
		contentPane.add(txtFieldFirstName);
		txtFieldFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setBounds(10, 157, 110, 29);
		contentPane.add(lblLastName);
		
		txtFieldLastName = new JTextField();
		txtFieldLastName.setColumns(10);
		txtFieldLastName.setBounds(130, 157, 145, 29);
		contentPane.add(txtFieldLastName);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAge.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAge.setBounds(10, 197, 110, 29);
		contentPane.add(lblAge);
		
		txtFieldAge = new JTextField();
		txtFieldAge.setColumns(10);
		txtFieldAge.setBounds(130, 197, 145, 29);
		contentPane.add(txtFieldAge);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(10, 237, 110, 29);
		contentPane.add(lblUsername);
		
		txtFieldUsername = new JTextField();
		txtFieldUsername.setColumns(10);
		txtFieldUsername.setBounds(130, 237, 145, 29);
		contentPane.add(txtFieldUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(10, 277, 110, 29);
		contentPane.add(lblPassword);
		
		txtFieldPassword = new JPasswordField();
		txtFieldPassword.setBounds(130, 277, 145, 29);
		contentPane.add(txtFieldPassword);
		
		JCheckBox checkBoxAdmin = new JCheckBox("Admin?");
		checkBoxAdmin.setBounds(130, 318, 75, 29);
		contentPane.add(checkBoxAdmin);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setIcon(new ImageIcon(Signup.class.getResource("/twentyfour/sign-check.png")));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  if(checkBoxAdmin.isSelected()){
				 try{
					 String query2 = ("SELECT username from admins where username = ?");
					 PreparedStatement pst2 = connection.prepareStatement(query2);
					 pst2.setString(1, txtFieldUsername.getText());
					 ResultSet rs2 = pst2.executeQuery();
					 if(rs2.next()){
							JOptionPane.showMessageDialog(null, "Username already exists."); 
							return;
					 }
				  }catch(Exception ex){
					  JOptionPane.showMessageDialog(null,ex);
				  }
				try{
					String query = "INSERT INTO admins (first_name, last_name, age, username, password) VALUES (?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, txtFieldFirstName.getText() );
					pst.setString(2, txtFieldLastName.getText() );
					pst.setString(3, txtFieldAge.getText() );
					pst.setString(4, txtFieldUsername.getText() );
					pst.setString(5, txtFieldPassword.getText() );
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Profile Created.");
					
					pst.close();
					
					contentPane.setVisible(false);
					dispose();
					Login.main(null);
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			  }else{
				  try{
						 String query2 = ("SELECT username from customers where username = ?");
						 PreparedStatement pst2 = connection.prepareStatement(query2);
						 pst2.setString(1, txtFieldUsername.getText());
						 ResultSet rs2 = pst2.executeQuery();
						 if(rs2.next()){
								JOptionPane.showMessageDialog(null, "Username already exists."); 
								return;
						 }
					  }catch(Exception ex){
						  JOptionPane.showMessageDialog(null,ex);
					  }
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
						
						contentPane.setVisible(false);
						dispose();
						Login.main(null);
						
					}catch(Exception ex){
						ex.printStackTrace();
					}
			  }
				
			}
		});
		btnSubmit.setBounds(328, 117, 102, 29);
		contentPane.add(btnSubmit);
		
		JButton btnLoginPage = new JButton("Cancel");
		btnLoginPage.setIcon(new ImageIcon(Signup.class.getResource("/twentyfour/sign-ban.png")));
		btnLoginPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(null, "Are you sure want to cancel?", "Cancel", JOptionPane.YES_NO_OPTION);
				if(action == 0){
				contentPane.setVisible(false);
				dispose();
				Login.main(null);
				}
			}
		});
		btnLoginPage.setBounds(328, 157, 102, 29);
		contentPane.add(btnLoginPage);
	}
}

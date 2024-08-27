package project;

import java.sql.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class signin_fullscreen extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField textField_1;
    int c=0;

    public signin_fullscreen() {
        final JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 1400, 710);        

        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon icon = new ImageIcon("D:\\papers\\logo.png");
        frame.setIconImage(icon.getImage());

        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, -12, 600, 722);
        contentPane.add(panel);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("D:\\papers\\chatapp.jpg"));
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("LOGIN");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblNewLabel_1.setBounds(950, 61, 671, 83);
        contentPane.add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(728, 237, 547, 43);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JPasswordField();
        textField_1.setBounds(728, 394, 547, 43);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("PASSWORD");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_2.setBounds(940, 331, 140, 53);
        contentPane.add(lblNewLabel_2);
        
        JToggleButton showPasswordToggle = new JToggleButton("Show Password");
        showPasswordToggle.setBounds(1130, 444, 140, 23);
        contentPane.add(showPasswordToggle);

        JLabel lblNewLabel_2_1 = new JLabel("USERNAME\r\n");
        lblNewLabel_2_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_2_1.setBounds(940, 173, 140, 53);
        contentPane.add(lblNewLabel_2_1);

        JLabel lblNewLabel_2_2 = new JLabel("WELCOME BACK!");
        lblNewLabel_2_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_2_2.setBounds(910, 470, 190, 53);
        contentPane.add(lblNewLabel_2_2);
        
        JLabel attemptLabel = new JLabel("*Total number of attempts: " + (3 - c) + "/3");
        attemptLabel.setForeground(Color.WHITE);
        attemptLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        attemptLabel.setBounds(728, 435, 300, 53);
        contentPane.add(attemptLabel);


        JButton btnNewButton = new JButton("SIGN IN");
        btnNewButton.setBackground(new Color(189, 183, 107));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton.setBounds(730, 554, 218, 43);
        contentPane.add(btnNewButton);

        JButton btnNewButton2 = new JButton("BACK");
        btnNewButton2.setBackground(new Color(189, 183, 107));
        btnNewButton2.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton2.setBounds(1030, 554, 218, 43);
        contentPane.add(btnNewButton2);
        


        // Add ActionListener to the Sign In button
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                String url = "jdbc:mysql://localhost:4306/users";
                String user = "root";
                String pass = "";
            
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, user, pass);

                    String name = textField.getText();
                    String password = textField_1.getText();

                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM users WHERE NAME='" + name + "' AND PASSWORD='" + password + "'");                       
                    if (rs.next()) {
                        // User exists in the database
                        System.out.println("Login successful!");
                        JOptionPane.showMessageDialog(frame, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        st.executeUpdate("UPDATE users SET STATUS=1 WHERE NAME='" + name + "'");
                        api a= new api(name);
            	        frame.dispose();
                    } else {
                        // User does not exist in the database or invalid credentials
                        System.out.println("Invalid username or password!");
                        JOptionPane.showMessageDialog(frame, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
                        c++;
                        if (c >= 3) {
                            c = 0; // Reset failedAttempts counter
                            login_fullscreen login = new login_fullscreen();
                            dispose(); // Close the current frame
                        } else {
                            attemptLabel.setText("Total number of attempts: " + (3 - c) + "/3");
                        }
                    }
                   con.close();
                }            
              catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "An error occurred while signing in.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "An error occurred while connecting to the database.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnNewButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                login_fullscreen login = new login_fullscreen();
            }
        });
        
        showPasswordToggle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPasswordToggle.isSelected()) {
                    
                    textField_1.setEchoChar((char) 0); 
                } else {
                   
                    textField_1.setEchoChar('\u2022'); 
                }
            }
        });

        frame.setVisible(true);
    }
}

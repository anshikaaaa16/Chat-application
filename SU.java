package project;

import java.sql.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.border.EmptyBorder;

import javax.swing.*;
import java.util.Date;
import java.util.Date;

public class SU extends JFrame {
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_2;
    private JPasswordField textField_1;
    private JCheckBox chckbxNewCheckBox;

    public SU() {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 1400, 710);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 0, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 0, 0));
        panel.setBounds(0, -20, 600, 760);
        contentPane.add(panel);

        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\DELL\\Downloads\\Untitled design (4).png"));
        lblNewLabel.setBounds(-10, -20, 600, 760);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("CREATE AN ACCOUNT AND COMMUNICATE THROUGH THE WORLD");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1.setBounds(659, 21, 671, 83);
        contentPane.add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(728, 166, 547, 43);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JPasswordField();
        textField_1.setBounds(728, 324, 547, 43);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("PASSWORD");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_2.setBounds(940, 260, 140, 53);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_2_1 = new JLabel("USERNAME\r\n");
        lblNewLabel_2_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_2_1.setBounds(940, 102, 140, 53);
        contentPane.add(lblNewLabel_2_1);

        JLabel lblNewLabel_2_2 = new JLabel("IP ADDRESS");
        lblNewLabel_2_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_2_2.setBounds(940, 418, 140, 53);
        contentPane.add(lblNewLabel_2_2);

        textField_2 = new JTextField();
        textField_2.setBounds(728, 482, 547, 43);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        chckbxNewCheckBox = new JCheckBox("Agree with the terms and conditions*");
        chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        chckbxNewCheckBox.setBounds(728, 540, 259, 25);
        contentPane.add(chckbxNewCheckBox);

        JButton btnNewButton = new JButton("SIGN UP");
        btnNewButton.setBackground(Color.CYAN);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton.setBounds(730, 580, 218, 43);
        contentPane.add(btnNewButton);

        JButton btnNewButton2 = new JButton("BACK");
        btnNewButton2.setBackground(Color.CYAN);
        btnNewButton2.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton2.setBounds(1030, 580, 218, 43);
        contentPane.add(btnNewButton2);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chckbxNewCheckBox.isSelected()) {
                    String url = "jdbc:mysql://localhost:4306/users";
                    String user = "root";
                    String pass = "";

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection(url, user, pass);
                        System.out.println("connected");

                        Statement st = con.createStatement();
                        st.executeUpdate("CREATE TABLE IF NOT EXISTS users (NAME varchar(20), PASSWORD varchar(20),IP_ADDRESS varchar(30), ACCOUNT_CREATED_ON TIMESTAMP , STATUS int(2) DEFAULT 0)");

                        String name = textField.getText();
                        String password = textField_1.getText();
                        String ip = textField_2.getText();
                        
                        ResultSet rs = st.executeQuery("SELECT * FROM users WHERE NAME='" + name +  "'");
                        if (rs.next()) {
                           
                            JOptionPane.showMessageDialog(frame, "Username already exist!", "Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
                        }

                        else if (!name.isEmpty() || !password.isEmpty()) {
                            if (password.length() < 6) {
                                JOptionPane.showMessageDialog(frame, "Enter password of at least 6 characters!", "Password is short", JOptionPane.INFORMATION_MESSAGE);
                            } 
                           
                           
                            		else {
                                // Generate current timestamp
                                Date cd = new Date();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String formattedDate = sdf.format(cd);
                                st.executeUpdate("INSERT INTO users (NAME, PASSWORD,IP_ADDRESS, ACCOUNT_CREATED_ON,  STATUS) VALUES ('" + name + "', '" + password +"', '" + ip + "', '" + formattedDate + "' , 0)");
                                System.out.println("Inserted");
                                JOptionPane.showMessageDialog(frame, "Signed up successfully!", "Successful", JOptionPane.INFORMATION_MESSAGE);
                                frame.dispose();
                                signin_fullscreen signinFrame = new signin_fullscreen();
                            }
                        } else {
                            System.out.println("Enter values");
                            JOptionPane.showMessageDialog(frame, "Fill in details!", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        con.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "An error occurred while signing up.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please agree to the terms and conditions.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnNewButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                login_fullscreen login = new login_fullscreen();
            }
        });

        frame.setVisible(true);
    }
}

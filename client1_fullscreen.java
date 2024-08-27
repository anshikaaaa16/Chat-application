package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;

public class client1_fullscreen extends JFrame {
    private JPanel contentPane;
    private JScrollPane scrollPane;
    private JTable table;
    private JFrame frame;

    public client1_fullscreen(String name) {
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("interface");
        setBounds(0, 0, 1400, 710);
        setVisible(true);

        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton client2 = new JButton("ALL USERS");
        client2.setBounds(100, 550, 200, 50);
        contentPane.add(client2);
        
        JButton client1 = new JButton("ACTIVE USERS");
        client1.setBounds(600, 550, 200, 50);
        contentPane.add(client1);

        JButton logout = new JButton("LOGOUT");
        logout.setBounds(1050, 550, 200, 50);
        contentPane.add(logout);
        
        // Initialize the table and scroll pane
        table = new JTable();
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 100, 1200, 400);
        contentPane.add(scrollPane);

        client2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to display all users
                String url = "jdbc:mysql://localhost:4306/users";
                String user = "root";
                String pass = "";
                try {
                    Connection con = DriverManager.getConnection(url, user, pass);
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM users WHERE name NOT LIKE '" + name + "'");
                    String[] columnNames = {"Name", "IP_ADDRESS","ACCOUNT_CREATED_ON"};
                    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

                    while (rs.next()) {
                        String name = rs.getString("NAME");
                        String ip = rs.getString( "IP_ADDRESS");
                        String aco = rs.getString("ACCOUNT_CREATED_ON");
                        Object[] data = {name, ip, aco};
                        tableModel.addRow(data);
                    }
                    table.setModel(tableModel);
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        client1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to display active users
                String url = "jdbc:mysql://localhost:4306/users";
                String user = "root";
                String pass = "";
                try {
                    Connection con = DriverManager.getConnection(url, user, pass);
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM users WHERE STATUS = 1 AND name NOT LIKE '" + name + "'");
                    String[] columnNames = {"Name", "IP_ADDRESS","ACCOUNT_CREATED_ON","STATUS"};
                    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

                    while (rs.next()) {
                        String name = rs.getString("NAME");
                        String ip = rs.getString( "IP_ADDRESS");
                        String aco = rs.getString("ACCOUNT_CREATED_ON");
                        String status = rs.getString("STATUS");
                        Object[] data = {name, ip, aco,status};
                        tableModel.addRow(data);
                    }
                    table.setModel(tableModel);
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to handle logout
                String url = "jdbc:mysql://localhost:4306/users";
                String user = "root";
                String pass = "";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, user, pass);
                    Statement st = con.createStatement();
                    st.executeUpdate("UPDATE users SET STATUS=0 WHERE NAME='" + name + "'");
                    signin_fullscreen sf = new signin_fullscreen();
                    frame.dispose();
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                String ip = (String) table.getValueAt(row, 1); // Assuming IP address is in the second column
                String client = (String) table.getValueAt(row, 0); 
                int port = 49152; // Port number on which the other peer's server is listening
                int localPort = 49154;
                String n=name;
                Peer peer;
                try {
                	
                    peer = new Peer(localPort, ip, port,n);
                    peer.startServer();  // Start listening for incoming connections
                    peer.connectToPeer();
                } catch (IOException e1) {
                    System.out.println("Error starting peer: " + e1.getMessage());
                }
            }
        });
    }
}

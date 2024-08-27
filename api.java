package project;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class api extends JFrame {
    private JPanel contentPane;

    public api(String name) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("interface");
        setBounds(0, 0, 1400, 710);
        setVisible(true);

        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton client1 = new JButton("Client1");
        client1.setBounds(550, 250, 200, 50);
        contentPane.add(client1);

        JButton client2 = new JButton("Client2");
        client2.setBounds(550, 350, 200, 50);
        contentPane.add(client2);
       
        
        client1.addActionListener(new ActionListener()
		{

	 public void actionPerformed(ActionEvent e) {
		 client1_fullscreen sf= new client1_fullscreen(name);
		 dispose();
	 }
	
		});
        client2.addActionListener(new ActionListener()
      		{

      	 public void actionPerformed(ActionEvent e) {
      		 client1_fullscreen sf= new client1_fullscreen(name);
      	 }
      	
      		});

    }

    
}

package project;
import project.login_fullscreen;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

public class welcome_fullscreen {
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private static void createAndShowGUI() {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 1400, 710);
        frame.setTitle("WELCOME PAGE");
        ImageIcon icon = new ImageIcon("D:\\papers\\logo.png");
        frame.setIconImage(icon.getImage());
        final ImageIcon bg = new ImageIcon("D:\\project\\chat.jpg");
      
        JLabel backgroundLabel = new JLabel(bg) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Resize the background image to fit the label
                Dimension size = getSize();
                g.drawImage(bg.getImage(), 0, 0, size.width, size.height, null);
            }
        };
        
        frame.setLayout(null);
        frame.setContentPane(backgroundLabel);
        
        // Create a button
        JButton btn = new JButton("CLICK TO CONTINUE");
        btn.setBounds(570, 610, 200, 30); // Set button bounds to overlap the image
        btn.setBackground(Color.GREEN);
        btn.setFont(new Font("Algerian", Font.ITALIC, 11));
        frame.add(btn);
        JLabel label1= new JLabel("CHAT APPLICATION");
        label1.setBounds(550, 2, 700, 20);
        label1.setFont(label1.getFont().deriveFont(Font.BOLD, 25));
        frame.add(label1);
        JLabel label2= new JLabel("	Created By Anshika Negi");
        label2.setBounds(1070,-15, 800,1300);
        label2.setFont(label1.getFont().deriveFont(Font.BOLD, 15));
        frame.add(label2);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the signup frame
               login_fullscreen signupFrame = new login_fullscreen();
                frame.dispose(); // Close the current frame
            }
        });
        
        frame.setVisible(true);
    }
}


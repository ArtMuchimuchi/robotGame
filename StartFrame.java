import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;


public class StartFrame extends JFrame{

    StartFrame () {
        int windowsWidth = 400;
        int windowsHeight = 500;

        JLabel wellcomeLabel = new JLabel();
        wellcomeLabel.setText("Robot Game");
        wellcomeLabel.setForeground(Color.green);
        wellcomeLabel.setFont(new Font("Courier New", Font.BOLD, 50));
        wellcomeLabel.setSize(windowsWidth, windowsHeight);
        wellcomeLabel.setVerticalAlignment(JLabel.TOP);
        wellcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        
        int startButtonWidth = 200;
        int startButtonHeight = 100;
        JButton startButton = new JButton();
        startButton.setText("PLAY");
        startButton.setSize(startButtonWidth, startButtonHeight);
        startButton.setLocation((windowsWidth/2)-(startButtonWidth/2), (windowsHeight/3)-(startButtonHeight/2));
        startButton.setFocusable(false);
        startButton.setForeground(Color.green);
        startButton.setBackground(Color.black);
        startButton.addActionListener(e -> {
            MainGame game = new MainGame();
            this.dispose();
        });

        JButton exitButton = new JButton();
        exitButton.setText("EXIT");
        exitButton.setSize(startButtonWidth, startButtonHeight);
        exitButton.setLocation((windowsWidth/2)-(startButtonWidth/2), 2*(windowsHeight/3)-(startButtonHeight/2));
        exitButton.setFocusable(false);
        exitButton.setForeground(Color.green);
        exitButton.setBackground(Color.black);
        exitButton.addActionListener(e -> {
            this.dispose();
        });

        this.setTitle("Robot Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(windowsWidth,windowsHeight);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);
        this.add(wellcomeLabel);
        this.add(startButton);
        this.add(exitButton);
    }

}

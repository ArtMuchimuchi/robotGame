import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartFrame extends JFrame{

    StartFrame () {
        int windowsWidth = 500;
        int windowsHeight = 400;

        JPanel wellcomePanel = new JPanel();
        wellcomePanel.setBounds(0, 0, windowsWidth, windowsHeight/5);
        wellcomePanel.setBackground(Color.black);

        JLabel wellcomeLabel = new JLabel();
        wellcomeLabel.setText("Robot Game");
        wellcomeLabel.setForeground(Color.green);
        wellcomeLabel.setFont(new Font("Courier New", Font.BOLD, 50));
        wellcomePanel.add(wellcomeLabel);
        
        JPanel textPanel = new JPanel();
        textPanel.setBounds(0, windowsHeight/5, windowsWidth, windowsHeight/10);
        textPanel.setBackground(Color.black);

        JLabel enterName = new JLabel();
        enterName.setText("Please, Enter your name");
        enterName.setForeground(Color.white);
        enterName.setFont(new Font("Courier New", Font.BOLD, 20));
        enterName.setVerticalAlignment(JLabel.CENTER);
        textPanel.add(enterName);

        JTextField name = new JTextField();
        name.setSize(windowsWidth/2, windowsHeight/10);
        name.setLocation(windowsWidth/4, windowsHeight/5 + windowsHeight/10);

        int buttonWidth = 100;
        int buttonHeight = 50;
        JButton okButton = new JButton();
        okButton.setText("Submit");
        okButton.setSize(buttonWidth, buttonHeight);
        okButton.setLocation(windowsWidth/2 - buttonWidth/2, 2*windowsHeight/5);
        okButton.setFocusable(false);
        okButton.setForeground(Color.green);
        okButton.setBackground(Color.black);
        okButton.addActionListener(e -> {
            MainGame game = new MainGame();
            game.playerName = name.getText();
            game.setPlayerName();
            this.dispose();
        });

        // JButton exitButton = new JButton();
        // exitButton.setText("EXIT");
        // exitButton.setSize(startButtonWidth, startButtonHeight);
        // exitButton.setLocation((windowsWidth/2)-(startButtonWidth/2), 2*(windowsHeight/3)-(startButtonHeight/2));
        // exitButton.setFocusable(false);
        // exitButton.setForeground(Color.green);
        // exitButton.setBackground(Color.black);
        // exitButton.addActionListener(e -> {
        //     this.dispose();
        // });

        this.setTitle("Robot Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(windowsWidth,windowsHeight);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);
        this.add(wellcomePanel);
        this.add(textPanel);
        // this.add(wellcomeLabel);
        this.add(name);
        // this.add(enterName);
        this.add(okButton);
        // this.add(exitButton);
    }

}

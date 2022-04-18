import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;


public class Result extends JFrame{
    
    boolean lose;
    JLabel resultLabel = new JLabel();
    String playerName;

    Result (boolean result, String inputName) {
        this.playerName = inputName;

        int windowsWidth = 400;
        int windowsHeight = 300;

        lose = result;

        if(!lose) {
            ifWin();
        }
        else {
            ifLost();
        }

        resultLabel.setFont(new Font("Courier New", Font.BOLD, 50));
        resultLabel.setSize(windowsWidth, windowsHeight/2);
        resultLabel.setVerticalAlignment(JLabel.CENTER);
        resultLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel nameLabel = new JLabel();
        nameLabel.setText(playerName);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Courier New", Font.BOLD, 50));
        nameLabel.setSize(windowsWidth, windowsHeight/3);
        nameLabel.setVerticalAlignment(JLabel.CENTER);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        
        int buttonWidth = 300;
        int buttonHeight = 100;

        JButton okButton = new JButton();
        okButton.setText("OK");
        okButton.setSize(buttonWidth, buttonHeight);
        okButton.setLocation((windowsWidth/2)-(buttonWidth/2), (windowsHeight*2/3)-(buttonHeight/2));
        okButton.setFocusable(false);
        okButton.setForeground(Color.green);
        okButton.setBackground(Color.black);
        okButton.addActionListener(e -> {
            this.dispose();
        });
 
        JPanel namePanel = new JPanel();
        namePanel.setBounds(0, 0, windowsWidth, 100);
        namePanel.setBackground(Color.BLACK);

        JPanel resultPanel = new JPanel();
        resultPanel.setBounds(0, 100, windowsWidth, 100);
        resultPanel.setBackground(Color.BLACK);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 200, windowsWidth, 100);
        buttonPanel.setBackground(Color.BLACK);

        this.setTitle("Robot Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(windowsWidth,windowsHeight);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);
        this.add(namePanel);
        namePanel.add(nameLabel);
        this.add(resultPanel);
        resultPanel.add(resultLabel);
        this.add(buttonPanel);
        buttonPanel.add(okButton);

    }

    void ifWin () {
        resultLabel.setText("You Win!");
        resultLabel.setForeground(Color.green);
    }

    void ifLost () {
        resultLabel.setText("You Lose!");
        resultLabel.setForeground(Color.red);
    }

}

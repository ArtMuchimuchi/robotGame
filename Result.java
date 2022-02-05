import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;


public class Result extends JFrame{
    
    boolean win;
    JLabel resultLabel = new JLabel();

    Result (boolean result) {
        int windowsWidth = 400;
        int windowsHeight = 300;

        win = result;

        if(win) {
            ifWin();
        }
        else {
            ifLost();
        }

        resultLabel.setFont(new Font("Courier New", Font.BOLD, 50));
        resultLabel.setSize(windowsWidth, windowsHeight);
        resultLabel.setVerticalAlignment(JLabel.TOP);
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        
        int buttonWidth = 200;
        int buttonHeight = 100;

        JButton okButton = new JButton();
        okButton.setText("OK");
        okButton.setSize(buttonWidth, buttonHeight);
        okButton.setLocation((windowsWidth/2)-(buttonWidth/2), (windowsHeight/2)-(buttonHeight/2));
        okButton.setFocusable(false);
        okButton.setForeground(Color.green);
        okButton.setBackground(Color.black);
        okButton.addActionListener(e -> {
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
        this.add(resultLabel);
        this.add(okButton);

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

package finalrace.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Amanda Maring
 * @date 2017-12-13
 */
public class MainUI1 extends JFrame{
    //vars
    private JButton ExitButton;
    private JButton ResetButton;
    private JLabel RunnerNumLabel;
    private JTextField RunnerNumTextField;
    private JButton RunnerSelectButton;
    private JButton StartButton;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    
    public MainUI1() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        setTitle("Animal Races");
        setSize(190,310);
        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(optionsPanel(), BorderLayout.NORTH);
        add(bottomPanel(), BorderLayout.SOUTH);
        setVisible(true);
        
    }
    
    private JPanel optionsPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(190,105));
        
        JLabel RunnerNumLabel = new JLabel("Number of runners: ");
        topPanel.add(RunnerNumLabel);
        
        JTextField RunnerNumTextField = new JTextField();
        RunnerNumTextField.setToolTipText("Enter a number greater than 1");
        RunnerNumTextField.setColumns(5);
        topPanel.add(RunnerNumTextField);
                
        JButton RunnerSelectButton = new JButton("Select Runners");
        RunnerSelectButton.addActionListener((ActionEvent) -> {
            doSelectRunner();
        });
        topPanel.add(RunnerSelectButton);
        
        /* Temporarily removing clumsiness
        JLabel ClumsinessLabel = new JLabel("Factor in clumsiness? ");
        panel.add(ClumsinessLabel);
        
        JTextField ClumsinessTextField = new JTextField();
        ClumsinessTextField.setToolTipText("Enter Yes (y) or No (n)");
        ClumsinessTextField.setColumns(5);
        panel.add(ClumsinessTextField);
        */
        
        /*JButton StartButton = new JButton("Start Race!");
        StartButton.addActionListener((ActionEvent) -> {
            //doStartRace();
        });
        topPanel.add(StartButton);
        */
        return topPanel;
    }
    
    private JPanel bottomPanel() {
        JPanel bottomPanel = new JPanel();
        
        /* Reset button not working... think it's because I'm calling the text fields in a differnt method
        JButton ResetButton = new JButton("Play Again?");
        ResetButton.addActionListener((ActionEvent) -> {
            resetButtonClicked();
        });
        bottomPanel.add(ResetButton);
        */
        
        JButton ExitButton = new JButton("Exit");
        ExitButton.addActionListener((ActionEvent) -> {
            exitButtonClicked();
        });
        bottomPanel.add(ExitButton);
        
        return bottomPanel;
    }
    
    /*private void doSelectRunner() {
        RunnerTable runnerTable = new RunnerTable(this, "Select Runner", true);
        runnerTable.setLocationRelativeTo(this);
        runnerTable.setVisible(true);
    }
    */
    
    private void doSelectRunner() {
        int runners;
        try {
            runners = Integer.parseInt(RunnerNumTextField.getText());
            
            
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "The selected runners field is not a valid number. Please try again.",
                    "Invalid Number", JOptionPane.ERROR_MESSAGE);
            
        }
        
        
        /*
        
        try {
            
            if (text == null) {
                JOptionPane.showMessageDialog(null, "Please enter a number into the box above.",
                    "Incorrect Value", JOptionPane.ERROR_MESSAGE);
            } else {
            int x = Integer.parseInt(text);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a whole number.",
                    "Incorrect Value", JOptionPane.ERROR_MESSAGE);
        }
        /*if (x > 1 && x < 10) {
            //
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a value less than 10.",
                    "Missing Fields", JOptionPane.INFORMATION_MESSAGE);
        }
*/
    }
    
    private void resetButtonClicked() {
        RunnerNumTextField.setText("");
        //clearRunners();

    }
    
    private void exitButtonClicked() {
        System.exit(0);
    }
}
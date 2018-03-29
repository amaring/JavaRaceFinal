package finalrace.ui;

import finalRace.business.Racer;
import finalRace.db.DBException;
import finalRace.db.RacerDB;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
public class MainUI extends JFrame{
    //vars
    private JButton ExitButton;
    private JButton ResetButton;
    private JLabel RunnerNumLabel;
    private JComboBox RunnerNumComboBox;
    private JButton RunnerSelectButton;
    private JButton StartButton;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    
    public MainUI() {
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
        
        //initComponents();
    }
    
    private JPanel optionsPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(190,105));
        
        JLabel RunnerNumLabel = new JLabel("Number of runners: ");
        topPanel.add(RunnerNumLabel);
        
        // Combo Box of number of racers
        List<Racer> racers = null;
        try {
            racers = RacerDB.getAll();
        } catch (DBException e) {
            // Change this for a display window
            //Console.display(e + "\n");
        }
        
        if (racers == null) {
            // Change this to throw an error window
            //Console.display("Error! Unable to get runners.\n");
        } else {
            int max = 10;
            int x = racers.size();
            List<String> al = new ArrayList<>();
            for (int j = 2; j <= x && j < max; j++) {
                String newItem = Integer.toString(j);
                al.add(newItem);
            }
            String[] values = al.toArray(new String[al.size()]);
            JComboBox<String> RunnerNumComboBox = new JComboBox<>(values);
            
            topPanel.add(RunnerNumComboBox);
            
        JButton RunnerSelectButton = new JButton("Set Runners");
            RunnerSelectButton.addActionListener((ActionEvent) -> {
                Object obj = RunnerNumComboBox.getSelectedItem();
                int i = Integer.valueOf((String) obj);
                System.out.println(Integer.toString(i));
                for (int j = 0; j < i; j++) {
                    // list of racer names
                    
                    
                }
            });
                
                topPanel.add(RunnerSelectButton);

        }
                
        /*JButton RunnerSelectButton = new JButton("Select Runners");
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
    
    private void resetButtonClicked() {
        //RunnerNumTextField.setText("");
        //clearRunners();
    }
    
    private void exitButtonClicked() {
        System.exit(0);
    }
}
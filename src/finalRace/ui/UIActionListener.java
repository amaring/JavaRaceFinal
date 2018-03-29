package finalRace.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 * @author Amanda Maring
 */
public class UIActionListener implements ActionListener {
    Object oldItem;

    public void actionPerformed(ActionEvent evt) {
        JComboBox cb = (JComboBox) evt.getSource();
        Object newItem = cb.getSelectedItem();
        
        boolean same = newItem.equals(oldItem);
        oldItem = newItem;
        
        if ("comboBoxChanged".equals(evt.getActionCommand())) {
            //do something
        }
    }
}

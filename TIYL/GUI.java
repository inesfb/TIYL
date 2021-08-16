// this example defines ButtonListener to be an inner class

import javax.swing.*;     // JFrame, JPanel, JButton, JLabel, etc.
import java.awt.event.*;  // ActionListener, ActionEvent

public class GUI extends JFrame {
    
    // instance variables
    private JButton button1;
    private JButton button2;
    
    // listener class
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == button1) {
                System.out.println("OK clicked");
            } else if (event.getSource() == button2) {
                System.out.println("Cancel clicked");
            } else {
                System.out.println("This should never happen");
            }
        }
    }
    
    // constructor
    public GUI() {
        // create some GUI components
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField("Your name here");
        JCheckBox boldCheck = new JCheckBox("Bold");
        JCheckBox italicCheck = new JCheckBox("Italic");
        
        JRadioButton amSelect = new JRadioButton("AM");
        JRadioButton fmSelect = new JRadioButton("FM");
        // make the radio buttons mutually exclusive
        ButtonGroup radioButtons = new ButtonGroup();
        radioButtons.add(amSelect);
        radioButtons.add(fmSelect);
        
        // a pull-down menu
        String[] choices = { "First-year", "Sophomore", "Junior", "Senior" };
        JComboBox<String> yearMenu = new JComboBox<String>(choices);
        
        // attach an action listener to both buttons
        ButtonListener listener = new ButtonListener();
        okButton.addActionListener(listener);
        cancelButton.addActionListener(listener);
        // make the buttons accessible to the ButtonListener
        this.button1 = okButton;
        this.button2 = cancelButton;

        // create a panel and add the components to it
        JPanel panel = new JPanel();
        panel.add(okButton);
        panel.add(cancelButton);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(boldCheck);
        panel.add(italicCheck);
        panel.add(amSelect);
        panel.add(fmSelect);
        panel.add(yearMenu);

        // add the panel to this JFrame
        this.add(panel);

        // set the properties of this JFrame
        this.setSize(500, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    // test program
    public static void main(String[] args) {
        GUI gui = new GUI();
    }
}
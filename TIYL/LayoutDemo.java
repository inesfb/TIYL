import javax.swing.*;  // JFrame, JPanel, JButton
import java.awt.*;     // Color, Font, FlowLayout, GridLayout

public class LayoutDemo extends JFrame {
    
    // constructor
    public LayoutDemo() {
        // create some buttons
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        JButton applyButton = new JButton("Apply");
        JButton deleteButton = new JButton("Delete");
        JButton panicButton = new JButton("Panic");
        
        // enhance the panic button
        Font font = new Font("Times New Roman", Font.ITALIC + Font.BOLD, 28);
        panicButton.setFont(font);
        panicButton.setForeground(Color.RED);
        panicButton.setToolTipText("No, no, don't press this!");

        // create a panel to hold buttons
        JPanel panel = new JPanel();

        // add buttons to the panel
        //panel.setLayout(new FlowLayout());
        panel.setLayout(new GridLayout(3, 2));
        panel.add(okButton);
        panel.add(cancelButton);
        panel.add(applyButton);
        panel.add(deleteButton);
        panel.add(panicButton);

        // add the panel to the frame
        this.add(panel);

        // set the properties of the frame
        this.setTitle("Layout Demo");
        this.setSize(400, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    // test program
    public static void main(String[] args) {
        LayoutDemo demo = new LayoutDemo();
    }
}
import javax.swing.*;  
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class birthdaySetter extends JFrame {
  
  private JButton doneButton;
  private JComboBox dayMenu;
  private JComboBox monthMenu;
  private JComboBox yearMenu;
  private File filename;
  
  private class ButtonListener implements ActionListener { 
        public void actionPerformed(ActionEvent event) {
          if (event.getSource() == doneButton) { 
            int day = Integer.parseInt((String)dayMenu.getSelectedItem());
            int month = Integer.parseInt((String)monthMenu.getSelectedItem()); 
            int year = Integer.parseInt((String)yearMenu.getSelectedItem());
            int[] birthday = {day, month, year};
            
            File bday = new  File("birthday.txt");
            if (bday.exists()) {
              bday.delete();
              try {
              bday.createNewFile();
              } catch(IOException ex) {
                ex.printStackTrace();
              }
            }
            
            try { //serialize
              FileOutputStream fileOut = new FileOutputStream("birthday.txt");
              ObjectOutputStream out = new ObjectOutputStream(fileOut);
              out.writeObject(birthday);
              out.close();
              fileOut.close();
              JFrame frame = new JFrame("");
              JOptionPane.showMessageDialog(frame, "Birthday has been set");
            }
            catch(IOException ex) { 
              ex.printStackTrace();
            }
            
          }
        }
  }

  public birthdaySetter() {
    JPanel panel = new JPanel();
    ButtonListener listener = new ButtonListener();
    
    String[] months = new String[13];
    for (int i = 1; i <= 12; i++) {
      months[i] = Integer.toString(i);
    }
    this.monthMenu = new JComboBox<String>(months);
    
    String[] days = new String[32];
    for (int i = 1; i <= 31; i++) {
      days[i] = Integer.toString(i);
    }
    this.dayMenu = new JComboBox<String>(days);
    
    String[] years = new String[300];
    for (int i = 1900; i < 2200; i++) {
      years[i-1900] = Integer.toString(i);
    }
    this.yearMenu = new JComboBox<String>(years);
    
    
    String dateString = new SimpleDateFormat("dd-MM-YYYY").format(new Date());
    String[] date = dateString.split("-");
    String day = date[0];
    if (day.charAt(0) == '0') {
      day = day.substring(1, 2);
    }
    String month = date[1];
    if (month.charAt(0) == '0') {
      month = month.substring(1, 2);
    }
    String year = date[2];
    System.out.println(day + month + year);
    dayMenu.setSelectedItem(day);
    monthMenu.setSelectedItem(month);
    yearMenu.setSelectedItem(year);
    
    
    
    
    this.doneButton = new JButton("Done");
    this.doneButton.addActionListener(listener);
    
    panel.add(monthMenu);
    panel.add(dayMenu);
    panel.add(yearMenu);
    panel.add(doneButton);
    this.add(panel);
    this.setTitle("Enter Your Birthday:");
    this.setBackground(Color.BLUE);
    this.setSize(500, 100);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);
  }
}
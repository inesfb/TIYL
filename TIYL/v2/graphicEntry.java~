import javax.swing.*;  
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class graphicEntry extends JFrame {
  
  private JButton doneButton;
  private JComboBox dayMenu;
  private JComboBox monthMenu;
  private JComboBox yearMenu;
  private JTextArea textArea;
  private File filename;
  private ArrayList<JournalEntry> entriesArray;
  
  private class ButtonListener implements ActionListener { 
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == doneButton) {

                //create new JournalEntry object
                String dayString = (String) dayMenu.getSelectedItem();
                int day = Integer.parseInt(dayString);
                String monthString = (String) monthMenu.getSelectedItem();
                int month = Integer.parseInt(monthString);
                String yearString = (String) yearMenu.getSelectedItem();
                int year = Integer.parseInt(yearString);
                String text = textArea.getText();
                JournalEntry entry = new JournalEntry(day, month, year, text);
                
                System.out.println("Logged: " + entry.toString());
                
                
                //serialize
                try {    
                    File f = new File("entries.txt");
                    if (f.exists() == false) {
                        ArrayList<JournalEntry> a = new ArrayList<JournalEntry>();
                        FileOutputStream outfile = new FileOutputStream("entries.txt");
                        ObjectOutputStream out = new ObjectOutputStream(outfile);
                        out.writeObject(a);
                        out.close();
                    }
                    FileInputStream file = new FileInputStream("entries.txt"); 
                    ObjectInputStream in = new ObjectInputStream(file);
                    entriesArray = (ArrayList<JournalEntry>)in.readObject();
                    in.close();
                    file.close();
                } catch(IOException ex) { ex.printStackTrace();
                } catch(ClassNotFoundException ex) { ex.printStackTrace();
                }
                entriesArray.add(entry);
                
                try {
                  FileOutputStream file = new FileOutputStream("entries.txt");
                  ObjectOutputStream out = new ObjectOutputStream(file); 
                  out.writeObject(entriesArray);
                  out.close(); 
                  file.close();
                } catch (IOException e) {
                  System.out.println(e);
                }

            } else {
                System.out.println("This should never happen");
            }
        }
    }
  
  public graphicEntry() {
    
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
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
    
    JPanel datePanel = new JPanel();
    datePanel.add(monthMenu);
    datePanel.add(dayMenu);
    datePanel.add(yearMenu);
    
    this.textArea = new JTextArea(30, 15);
    JScrollPane pane = new JScrollPane(textArea);
    this.doneButton = new JButton("Done");
    doneButton.addActionListener(listener);
    
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
    
    panel.add(textArea, BorderLayout.CENTER);
    panel.add(doneButton, BorderLayout.SOUTH);
    panel.add(datePanel, BorderLayout.NORTH);
    
   this.add(panel);
   this.setTitle("Make an Entry");
   this.setBackground(Color.BLUE);
   this.setSize(500, 350);
   this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   this.setVisible(true);
  }
}

import javax.swing.*;  
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.File;
import java.io.*; 
import java.io.FileNotFoundException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TIYL extends JFrame {
  
  private JButton LogButton;
  private JButton viewQualitativeButton;
  private JButton viewStatsButton;
  private JButton settingsButton;
  private JButton timelineButton;
  private Scanner lineCheck;
  private ArrayList<JournalEntry> entriesArray;
    
  private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event)  {
            if (event.getSource() == LogButton) {
                
              graphicEntry entry = new graphicEntry();
              
            } else if (event.getSource() == viewQualitativeButton) {
              
              //get todays date
              String dateString = new SimpleDateFormat("dd-MM-YYYY").format(new Date());
              String[] date = dateString.split("-");
              int day = Integer.parseInt(date[0]);
              int month = Integer.parseInt(date[1]);
              int year = Integer.parseInt(date[2]);
              
              
              //fetch & view previous entries of today

              try {
                JournalEntry today = new JournalEntry(day, month, year, "");
                
              
               JFrame mframe = new JFrame("Multiple ambiguous times ago");
                  mframe.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                  mframe.setSize(500, 350);
                  mframe.setVisible(true);
                  JPanel panel = new JPanel();
                  mframe.add(panel);
              
              
                
              try {
                if (today.weekAgo() != null) {
                  System.out.print(today.weekAgo().toString()); //instead of tostring, display in jtextbox
                  
                  JTextArea display = new JTextArea(today.weekAgo().toString());
                  display.setEditable(false);
                  display.setLineWrap(true);
                  display.setColumns(35);
                  display.setRows(5);

                  JScrollPane scroll = new JScrollPane (display, 
                  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                  JLabel week = new JLabel ("One week ago");
                  scroll.setColumnHeaderView(week);
                  panel.add(scroll);                

                }
                
              } catch (IOException ex) { ex.printStackTrace();
              } catch (ClassNotFoundException ex) { ex.printStackTrace(); }
              try {
                if (today.monthAgo() != null) {
                  System.out.print(today.monthAgo().toString());
                  
                  JTextArea display2 = new JTextArea(today.monthAgo().toString());
                  display2.setEditable(false);
                  display2.setLineWrap(true);
                  display2.setColumns(35);
                  display2.setRows(5);

                  JScrollPane scroll2 = new JScrollPane (display2, 
                  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                  JLabel month2 = new JLabel ("One month ago");
                  scroll2.setColumnHeaderView(month2);
                  panel.add(scroll2);
                  
                }
              } catch (IOException ex) { ex.printStackTrace();
              } catch (ClassNotFoundException ex) { ex.printStackTrace(); }
              try {
                if (today.yearAgo() != null) {
                  System.out.print(today.yearAgo().toString());
                  
                  JTextArea display3 = new JTextArea(today.yearAgo().toString());
                  display3.setEditable(false);
                  display3.setLineWrap(true);
                  display3.setColumns(35);
                  display3.setRows(5);

                  JScrollPane scroll3 = new JScrollPane (display3, 
                  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                  JLabel year2 = new JLabel ("One year ago");
                  scroll3.setColumnHeaderView(year2);
                  panel.add(scroll3);

                  
                }
              } catch (IOException ex) { ex.printStackTrace();
              } catch (ClassNotFoundException ex) { ex.printStackTrace(); }
              } catch(IOException ex) { ex.printStackTrace(); 
              } catch (ClassNotFoundException ex) { ex.printStackTrace(); }
              
            } else if (event.getSource() == viewStatsButton) {
              
              graphicLife life = new graphicLife();
            
            } else if (event.getSource() == timelineButton) {
              
              System.out.println("timeline clicked");
              
            } else if (event.getSource() == settingsButton) {
                
              birthdaySetter settings = new birthdaySetter();
              
            } else {
                System.out.println("This should never happen");
            }
        }
    }
  
  
    
    public TIYL() {
      
      JPanel marginPanel = new JPanel();
      marginPanel.setLayout(new BorderLayout(5, 20));
      
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 3, 7, 10));
        ButtonListener listener = new ButtonListener();
        Color lightBlue = new Color(0, 213, 255);
        this.setBackground(lightBlue);
        
        this.LogButton = new JButton("Log An Entry");
        this.LogButton.addActionListener(listener);
        this.viewQualitativeButton = new JButton("Time Capsule");
        this.viewQualitativeButton.addActionListener(listener);
        this.viewStatsButton = new JButton("View Stats");
        this.viewStatsButton.addActionListener(listener);
        this.timelineButton = new JButton("Timeline");
        this.timelineButton.addActionListener(listener);
        this.settingsButton = new JButton("Settings");
        this.settingsButton.addActionListener(listener);
        
        
        Font font = new Font("Helvetica", + Font.BOLD, 17);
        this.LogButton.setFont(font);
        this.viewQualitativeButton.setFont(font);
        this.viewStatsButton.setFont(font);
        this.settingsButton.setFont(font);
        this.timelineButton.setFont(font);
        
        panel.add(this.LogButton);
        panel.add(this.viewQualitativeButton);
        panel.add(this.viewStatsButton);
        panel.add(this.timelineButton);
        panel.add(this.settingsButton);

        JLabel label1 = new JLabel ("               ");
        JLabel label2 = new JLabel ("               ");
        JLabel label3 = new JLabel ("               ");
        JLabel label4 = new JLabel ("               ");
        marginPanel.add(panel, BorderLayout.CENTER);
        marginPanel.add(label1, BorderLayout.EAST);
        marginPanel.add(label2, BorderLayout.WEST);
        marginPanel.add(label3, BorderLayout.NORTH);
        marginPanel.add(label4, BorderLayout.SOUTH);
        
        this.add(marginPanel);
        this.setTitle("This Is Your Life");
        this.setSize(365, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

  public static void main(String[] args) {
    TIYL gui = new TIYL();
  }
}
    
    
    

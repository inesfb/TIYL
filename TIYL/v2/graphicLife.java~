import java.util.Scanner;
import java.io.*;
import javax.swing.*;  
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
      

public class graphicLife extends JFrame {
  
  private JButton viewWeeks;
  private JButton viewMonths;
  private JButton viewYears;
  private int weeksLived;
  private int monthsLived;
  private int yearsLived;
  
  private class ButtonListener implements ActionListener { 
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == viewWeeks) {
                
                JFrame frame = new JFrame("Your life in weeks");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                graphicWeeks weeks = new graphicWeeks();
                frame.add(weeks);
                frame.setSize(600, 800);
                frame.setVisible(true);
                
            } else if (event.getSource() == viewMonths) {
              
                JFrame frame = new JFrame("Your life in months");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                graphicMonths months = new graphicMonths();
                frame.add(months);
                frame.setSize(970, 800);
                frame.setVisible(true);
              
            } else if (event.getSource() == viewYears) {
              
                JFrame frame = new JFrame("Your life in years");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                graphicYears years = new graphicYears();
                frame.add(years);
                frame.setSize(470, 460);
                frame.setVisible(true);
              
            }
        }
      }
    
  private int monthDays(int month, int year) {
    int monthDays = 0;
    if (month == 2) {
      if (year % 400 == 0) {
         monthDays = 29;
      } else if (year % 100 == 0) {
        monthDays = 28;
      } else if (year % 4 == 0) {
        monthDays = 29;
      } else {
        monthDays = 28;
      }
    } else if (month == 1 || month == 3 || month == 5 || month == 7 ||
               month == 8 || month == 10 || month == 12) {
      monthDays = 31;
    } else {
        monthDays = 30;
    }
    return monthDays;
  }
  
  private int countYears(int month, int day, int year, int birthMonth, int birthDay, int birthYear) {
    int years = 0;
    int countDaysYear = 0;
    for (int i = 1; i < month; i++) {
        countDaysYear = countDaysYear + monthDays(i, year); //days in year
    }
    countDaysYear = countDaysYear + day;

    int countDaysBirth = 0;
    for (int i = 1; i < birthMonth; i++) {
        countDaysBirth = countDaysBirth + monthDays(i, year);
    }
    countDaysBirth = countDaysBirth + birthDay;
    
    if (countDaysYear >= countDaysBirth) {
        years = year - birthYear;
    } else {
        years = year - birthYear - 1;
    }
    return years;
  }
  
  
  private int countMonths(int month, int day, int year, int birthMonth, int birthDay, int birthYear) {
    int years = countYears(month, day, year, birthMonth, birthDay, birthYear);
    int monthCount = years*12;
    if (day >= monthDays(month, year)) { 
        monthCount = monthCount + month;
    } else {
        monthCount = monthCount + month - 1;
    }
    return monthCount;
  }
  
  
  private int countWeeks(int month, int day, int year, int birthMonth, int birthDay, int birthYear) {
    int years = countYears(month, day, year, birthMonth, birthDay, birthYear);
    int countDaysBirth = 0;
    for (int i = 1; i < birthMonth; i++) {
        countDaysBirth = countDaysBirth + monthDays(i, year);
    }
    countDaysBirth = countDaysBirth + birthDay;
    int weeks = countDaysBirth/7;
    int weekCount = years*52 + weeks;
    return weekCount;
  }
  
  
  
    public graphicLife() {

      //graphically represent w menu
      File f = new File("birthday.txt");
      if (f.exists() == false) {
        JFrame frame = new JFrame("");
        JOptionPane.showMessageDialog(frame, "You must set your birthday first in settings");
      } else {
      
      JPanel panel = new JPanel();
      panel.setLayout(new BorderLayout());
      ButtonListener listener = new ButtonListener();
      

      
      this.viewWeeks = new JButton("View your life in weeks"); 
      this.viewWeeks.addActionListener(listener);
      panel.add(viewWeeks, BorderLayout.EAST);
      
      this.viewMonths = new JButton("View your life in months");
      this.viewMonths.addActionListener(listener);
      panel.add(viewMonths, BorderLayout.CENTER);
      
      this.viewYears = new JButton("View your life in years");
      this.viewYears.addActionListener(listener);
      panel.add(viewYears, BorderLayout.WEST);
      
      this.add(panel);
      this.setTitle("Graphics");
      this.setBackground(Color.BLUE);
      this.setSize(550, 150);
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      this.setVisible(true);
      
      }

    }
    
}
    
    
    
    
    
    
import javax.swing.*;     
import java.awt.*;        
import java.awt.geom.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;


public class graphicWeeks extends JPanel {
  
  public void paintComponent(Graphics g) {

    int weekCount = 100;
    try {
    weekCount = getWeeks();
    } catch (IOException ex) { System.out.println("IOException"); 
    } catch (ClassNotFoundException ex) { System.out.println("ClassNotFoundException"); }
    System.out.println(weekCount);
      
    super.paintComponent(g);
    Graphics2D pen = (Graphics2D) g;
    
    int height = 40;
    int x = 20;
    int weeksDrawn = 0;
    for (int n = 0; n < 90; n++) {
    for (int i = 0; i < 52; i++) {
      g.setColor(Color.BLUE);
      Rectangle rect = new Rectangle(height + (10*i), x, 4, 4);
      pen.draw(rect);
      weeksDrawn++;
      if (weeksDrawn < weekCount) {
        pen.fill(rect);
      }
    }
    x = x + 8;
    }
  }
    
  
private int getWeeks() throws IOException, ClassNotFoundException{
     
     //get todays date
     String dateString = new SimpleDateFormat("dd-MM-YYYY").format(new Date());
     String[] date = dateString.split("-");
     int day = Integer.parseInt(date[0]);
     int month = Integer.parseInt(date[1]);
     int year = Integer.parseInt(date[2]);
    
     //get bday
      
     int[] birthday = null;      
     File bday = new  File("birthday.txt");
    

     FileInputStream file = new FileInputStream("birthday.txt");
     ObjectInputStream in = new ObjectInputStream(file); 
     birthday = (int[]) in.readObject(); 
     in.close(); 
     file.close(); 
     int birthDay = birthday[0];
     int birthMonth = birthday[1];
     int birthYear = birthday[2];
     
     int weekCount = countWeeks(month, day, year, birthMonth, birthDay, birthYear);
     return weekCount;
     
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
     
} 


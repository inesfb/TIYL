import javax.swing.*;     
import java.awt.*;        
import java.awt.geom.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;


public class graphicYears extends JPanel {
  
  public void paintComponent(Graphics g) {

    int yearCount = 100;
    try {
    yearCount = getYears();
    } catch (IOException ex) { System.out.println("IOException"); 
    } catch (ClassNotFoundException ex) { System.out.println("ClassNotFoundException"); }
    System.out.println(yearCount);
      
    super.paintComponent(g);
    Graphics2D pen = (Graphics2D) g;
    
    int height = 40;
    int x = 40;
    int yearsDrawn = 0;
    for (int n = 0; n < 9; n++) {
    for (int i = 0; i < 10; i++) {
      g.setColor(Color.BLUE);
      Rectangle rect = new Rectangle(height + (40*i), x, 30, 30);
      pen.draw(rect);
      yearsDrawn++;
      if (yearsDrawn < yearCount) {
        pen.fill(rect);
      }
    }
    x = x + 40;
    }
  }
    
  
private int getYears() throws IOException, ClassNotFoundException{
     
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
     
     int yearCount = countYears(month, day, year, birthMonth, birthDay, birthYear);
     return yearCount;
     
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
    return years+1;
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


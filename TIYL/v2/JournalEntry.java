import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.util.*;

public class JournalEntry implements Serializable {
  
  private int day;
  private int month;
  private int year;
  private String text;
  private ArrayList<JournalEntry> entriesArray;
  public int lifeYear;
  public int lifeMonth;
  public int lifeWeek;

  public JournalEntry(int day, int month, int year, String text) throws IOException, ClassNotFoundException {
    this.day = day;
    this.month = month;
    this.year = year;
    this.text = text;
    lifeWeek();
    lifeMonth();
    lifeYear();
  }
  
  public String toString() {
    return this.getDate() + ": " + this.text + "\n";
  }
  
  public String getDate() {
    String day = Integer.toString(this.day);
    String month = Integer.toString(this.month);
    String year = Integer.toString(this.year);
    return day+ "-" + month + "-" + year;
  }


  //To assign week/month/year of life
  public void lifeWeek() throws IOException, ClassNotFoundException {
     File bday = new  File("birthday.txt");
     if (bday.exists()) {
       int[] birthday = null;
       FileInputStream file = new FileInputStream("birthday.txt");
       ObjectInputStream in = new ObjectInputStream(file); 
       birthday = (int[]) in.readObject(); 
       in.close(); 
       file.close(); 
       int birthDay = birthday[0];
       int birthMonth = birthday[1];
       int birthYear = birthday[2];
       
       this.lifeWeek = countWeeks(this.month, this.day, this.year, birthMonth, birthDay, birthYear);
     }
  }
  
  public void lifeMonth() throws IOException, ClassNotFoundException {
     File bday = new  File("birthday.txt");
     if (bday.exists()) {
       int[] birthday = null;
       FileInputStream file = new FileInputStream("birthday.txt");
       ObjectInputStream in = new ObjectInputStream(file); 
       birthday = (int[]) in.readObject(); 
       in.close(); 
       file.close(); 
       int birthDay = birthday[0];
       int birthMonth = birthday[1];
       int birthYear = birthday[2];
       
       this.lifeMonth = countMonths(this.month, this.day, this.year, birthMonth, birthDay, birthYear);
     }
  }
  
  public void lifeYear() throws IOException, ClassNotFoundException {
     File bday = new  File("birthday.txt");
     if (bday.exists()) {
       int[] birthday = null;
       FileInputStream file = new FileInputStream("birthday.txt");
       ObjectInputStream in = new ObjectInputStream(file); 
       birthday = (int[]) in.readObject(); 
       in.close(); 
       file.close(); 
       int birthDay = birthday[0];
       int birthMonth = birthday[1];
       int birthYear = birthday[2];
       
       this.lifeYear = countYears(this.month, this.day, this.year, birthMonth, birthDay, birthYear);
     }
  }
  
  
    
 //To retreive entries     
  public JournalEntry weekAgo() throws IOException, ClassNotFoundException{ //deserialize content
    JournalEntry blankEntry = null;
    int dayAgo = this.day;
    int monthAgo = this.month;
    int yearAgo = this.year;
    if (day >= 8) { //if 1 week ago was within same month
        dayAgo = this.day - 7;
        monthAgo = this.month;
    } else { //if 1 week ago was last month
      if (month > 1) { //if not january, month = previous month
        monthAgo = month - 1;
      } else {
        monthAgo = 12; //if january, month = december
        yearAgo = yearAgo - 1;
      }
      int monthAgoDays = monthDays(monthAgo, year); //days in last month
      int remaining = 7 - day;
      dayAgo = monthAgoDays - remaining; //day of previous month
    }
    File f = new File("entries.txt");
    if (f.exists() == false) {
      return blankEntry;
    }
    FileInputStream fileIn = new FileInputStream("entries.txt");
    ObjectInputStream in = new ObjectInputStream(fileIn);
    entriesArray = (ArrayList<JournalEntry>) in.readObject();
    in.close(); 
    fileIn.close();
    for (int i = 0; i < entriesArray.size(); i++) {
      JournalEntry entry = entriesArray.get(i);
      if (entry.month == monthAgo && entry.day == dayAgo && entry.year == yearAgo) {
        return entry;
      }
    }
    return blankEntry;
  } 
  
  
  public JournalEntry monthAgo() throws IOException, ClassNotFoundException { 
    JournalEntry blankEntry = null;
    int dayAgo = this.day;
    int monthAgo = this.month;
    int yearAgo = this.year;
    if (this.month > 1) {
      monthAgo = this.month - 1;
    } else {
      monthAgo = 12;
      yearAgo = this.year - 1;
    }
    while (monthDays(monthAgo, yearAgo) < this.day) { 
      dayAgo = day - 1;
    }
    File f = new File("entries.txt");
    if (f.exists() == false) {
      return blankEntry;
    }
    FileInputStream fileIn = new FileInputStream("entries.txt");
    ObjectInputStream in = new ObjectInputStream(fileIn);
    entriesArray = (ArrayList<JournalEntry>) in.readObject();
    in.close(); 
    fileIn.close();
    for (int i = 0; i < entriesArray.size(); i++) {
      JournalEntry entry = entriesArray.get(i);
      if (entry.month == monthAgo && entry.day == dayAgo && entry.year == yearAgo) {
        return entry;
      }
    }
    return blankEntry;
  } 


  public JournalEntry yearAgo() throws IOException, ClassNotFoundException { //deserialize content
    JournalEntry blankEntry = null;
    File f = new File("entries.txt");
    if (f.exists() == false) {
      return blankEntry;
    }
    FileInputStream fileIn = new FileInputStream("entries.txt");
    ObjectInputStream in = new ObjectInputStream(fileIn);
    entriesArray = (ArrayList<JournalEntry>) in.readObject();
    in.close(); 
    fileIn.close();
    for (int i = 0; i < entriesArray.size(); i++) {
      JournalEntry entry = entriesArray.get(i);
      if (entry.month == month && entry.day == day) {
        return entry;
      }
    }
    return blankEntry;
  }
  
  
  
  //helpers
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
  
  
}
         
           
      
              
     
  
  
  
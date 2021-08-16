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

  public JournalEntry(int day, int month, int year, String text) {
    this.day = day;
    this.month = month;
    this.year = year;
    this.text = text;
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
  
  
  
}
         
           
      
              
     
  
  
  
import java.util.*;
import java.io.*;
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

public class fixer {
  
  private ArrayList<JournalEntry> entriesArray;
  
  public static void main(String[] args)throws IOException, ClassNotFoundException  {
    
    /*
     FileInputStream fileIn = new FileInputStream("entries.txt");
     ObjectInputStream in = new ObjectInputStream(fileIn);
     ArrayList<JournalEntry>entriesArray = (ArrayList<JournalEntry>) in.readObject();
     in.close(); 
     fileIn.close();
     int i = 0;
     for (JournalEntry entry : entriesArray) {
     System.out.println(entriesArray.get(i).toString());
     i++;
     }
    }
    */
    
    /* this part is what i used to add an empty arraylist, not needed for now
    
    ArrayList<JournalEntry>entriesArray = new ArrayList<JournalEntry>();
    FileOutputStream file = new FileOutputStream("entries.txt");
    ObjectOutputStream out = new ObjectOutputStream(file); 
    out.writeObject(entriesArray);
    file.close(); 
    out.close();
    
    */
    
  }
}

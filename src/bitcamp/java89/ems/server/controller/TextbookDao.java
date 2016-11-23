package bitcamp.java89.ems.server.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.vo.Textbook;


public class TextbookDao {
  static TextbookDao obj;
  private Scanner in;
  private PrintStream out;
  private String Filename = "textbook-v1.7.data";
  private ArrayList<Textbook>list;
  private boolean changed;
  
  public static TextbookDao getInstance() {
    if (obj == null) {
      obj = new TextbookDao();
    }
    return obj;
  }
  
  private TextbookDao() {
    this.load();
  }

  public boolean isChanged() {
    return changed;
  }

  @SuppressWarnings("unchecked")
  private void load() {
    FileInputStream in0 = null;
    ObjectInputStream in = null;
    
    try {
    in0 = new FileInputStream(this.Filename);
    in = new ObjectInputStream(in0);
    list = (ArrayList<Textbook>)in.readObject();
    
  } catch (EOFException e) {
   //파일을 모두 읽었다
  } catch (Exception e) {
  System.out.println("교재 데이터 로딩중 오류 발생!");
  list = new ArrayList<>();
 } finally {
   try {
   in.close();
   in0.close();
   } catch (Exception e) {
  }
 }
}
  public void save() throws Exception {
    FileOutputStream out0 = new FileOutputStream(this.Filename);
    ObjectOutputStream out = new ObjectOutputStream(out0);
    
    out.writeObject(list);
        
    changed = false;
    out.close();
    out0.close();
  }

  public ArrayList<Textbook> getList() {
    return this.list;
  }
  //get one만들기~~!
  public ArrayList<Textbook> getListByTitle(String title) {
    ArrayList<Textbook> results = new ArrayList<>();
    for (Textbook textbook : list) {
      if (textbook.getTitle().equals(title)) {
        results.add(textbook);
      }
    }
    return results;
  }
 synchronized public void insert(Textbook textbook) {
   list.add(textbook);
   changed = true;
 }
 
 synchronized public void update(Textbook textbook) {
   for (int i = 0; i <list.size(); i++) {
     if (list.get(i).getTitle().equals(textbook.getTitle())) {
       list.set(i, textbook);
       changed = true;
       break;
     }
   }
 }

 synchronized public void delete(String title) {
   for (int i = 0; i < list.size(); i++) {
     if (list.get(i).getTitle().equals(title)) {
       list.remove(i);
       changed = true;
       break;
     }
   }
 }
 
 public boolean existTitle(String title) {
   for (Textbook textbook : list) {
     if (textbook.getTitle().toLowerCase().equals(title.toLowerCase())) {
       return true;
     }
   }
   return false;
 }
   
  private ArrayList<Textbook> searchByName(String title) {
    ArrayList<Textbook> searchList = new ArrayList<>();
    for (Textbook textbook : list) {
      if (textbook.getTitle().toLowerCase().equals(title.toLowerCase())) {
        searchList.add(textbook);
      }
    }
    return searchList;
  }
}
 

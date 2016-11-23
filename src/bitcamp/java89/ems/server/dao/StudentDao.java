package bitcamp.java89.ems.server.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import bitcamp.java89.ems.server.vo.Student;

public class StudentDao {
  static StudentDao obj;
  private String filename = "student-v1.7.data";
  private ArrayList<Student> list;
  private boolean changed;
  
  public static StudentDao getInstance() {
    if (obj == null) {
      obj = new StudentDao();
    }
    return obj;
  }
 
  private StudentDao() {
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
      in0 = new FileInputStream(this.filename);
      in = new ObjectInputStream(in0);

      list = (ArrayList<Student>)in.readObject();
      
    } catch (EOFException e) {
      // 파일을 모두 읽었다.
    } catch (Exception e) {
      System.out.println("데이터 로딩 중 오류 발생!");
      list = new ArrayList<Student>(); // 리스트가 없다면 빈 리스트 준비
      
    } finally {
      try {
        in.close();
        in0.close();
      } catch (Exception e) {}
    }
  }

  public void save() throws Exception {
    FileOutputStream out0 = new FileOutputStream(this.filename);
    ObjectOutputStream out = new ObjectOutputStream(out0);

    out.writeObject(list);
    
    changed = false;

    out.close();
    out0.close();
  }
  
  public ArrayList<Student> getList() {
    return this.list;
  }
  
  public ArrayList<Student> getListByName(String name) {
    ArrayList<Student> results = new ArrayList<>();
    for (Student student : list) {
      if (student.getName().equals(name)) {
        results.add(student);
      }
    }
    return results;
  }
  
  synchronized public void insert(Student student) {
    list.add(student);
  }
   
  synchronized public void update(Student student) {
    for (int i = 0; i < list.size(); i++) {
      if (student.getName().equals(student.getName())) {
        list.set(i, student);
        changed = true;
        return;
      }
    }
  }
  
  synchronized public void delete(String name) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getName().equals(name)) {
        list.remove(i);
        changed = true;
        return;
      }
    }
  }

  public boolean existName(String name) {
    for (Student student : list) {
      if (student.getName().toLowerCase().equals(name.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

}
package bitcamp.java89.ems.server.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Classroom;

public class ClassroomDao {
  static ClassroomDao obj;
  private String filename = "classroom-v1.8.data";
  private ArrayList<Classroom> list;

  private ClassroomDao() {
    this.load();
  }
  
  public static ClassroomDao getInstance() {
    if (obj == null) {
      obj = new ClassroomDao();
    }
    return obj;
  }

  @SuppressWarnings("unchecked")
  private void load() {
    try (
      ObjectInputStream in = 
          new ObjectInputStream(new FileInputStream(this.filename));) {
      list = (ArrayList<Classroom>)in.readObject();
    } catch (EOFException e) {
      // 파일을 모두 읽었다.
    } catch (FileNotFoundException e) {
      list = new ArrayList<Classroom>();
    } catch (Exception e) {
      System.out.println("강의실 데이터 로딩 중 오류 발생!");
    }
  }

  public synchronized void insert(Classroom classRoom) {
    list.add(classRoom);
    try {this.save();} catch (Exception e) {e.printStackTrace();}
  }

  public ArrayList<Classroom> getList() {
    return this.list;
  }

  public ArrayList<Classroom> getListByName(String name) {
    ArrayList<Classroom> results = new ArrayList<>();
    for (Classroom classRoom : list) {
      if (classRoom.getName().equals(name)) {
        results.add(classRoom);
      }
    }
    return results;
  }

  public synchronized void update (Classroom classRoom) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getName().equals(classRoom.getName())) {
        list.set(i, classRoom);
      }
    }
    try {this.save();} catch (Exception e) {e.printStackTrace();}
  }

  public synchronized void delete(String name) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getName().equals(name)) {
        list.remove(i);
      }
    }
    try {this.save();} catch (Exception e) {e.printStackTrace();}
  }

  public boolean existName(String name) {
    for (Classroom classRoom : list) {
      if (classRoom.getName().toLowerCase().equals(name.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  public void save() {
    try (
    ObjectOutputStream out = 
        new ObjectOutputStream(new FileOutputStream(this.filename));) {
    out.writeObject(list);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
package bitcamp.java89.ems.server.controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.dao.StudentDao;
import bitcamp.java89.ems.server.vo.Student;

public class StudentController {
  private Scanner in;
  private PrintStream out;
  private StudentDao studentDao;
  
  

  public StudentController(Scanner in, PrintStream out) {
    this.in = in;
    this.out = out;

    studentDao = StudentDao.getInstance();
  }

  public void save() throws Exception {
    if (studentDao.isChanged()) {
      studentDao.save();
    }
  }
  
  public boolean service() {
    while (true) {
      out.println("학생관리> ");
      out.println(); 
      
      String[] commands = in.nextLine().split("\\?");

      try {
        switch (commands[0]) {
        case "add": this.doAdd(commands[1]); break;
        case "list": this.doList(); break;
        case "view": this.doView(commands[1]); break;
        case "delete": this.doDelete(commands[1]); break;
        case "update": this.doUpdate(commands[1]); break;
        case "main": return true;
        case "quit": return false;
        default:
          out.println("지원하지 않는 명령어입니다.");
        }
      } catch (IndexOutOfBoundsException e) {
        out.println("잘못된 입력입니다.");
      } catch (Exception e) {
        out.println("실행 중 오류가 발생했습니다.");
        e.printStackTrace();
      } // try
    } // while
  }
  
  
  private void doList() {
    ArrayList<Student> list = studentDao.getList();
    for (Student student : list) {
      out.printf("%s,%s,%s,%s,%s\n",
          student.getUserId(),
          student.getPassword(),
          student.getName(),
          student.getTel(),
          student.getEmail());
    }
  }
  
  private void doUpdate(String params) {
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();
    
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    
    if (!studentDao.existName(paramMap.get("name"))) {
      out.println("이름을 찾지 못했습니다.");
      return;
    } 
    
    Student student = new Student();
    student.setUserId(paramMap.get("userid"));
    student.setPassword(paramMap.get("password"));
    student.setName(paramMap.get("name"));
    student.setTel(paramMap.get("tel"));
    student.setEmail(paramMap.get("email"));
    
    studentDao.update(student);
    out.println("변경 하였습니다.");
  }
  
  private void doAdd(String params) {
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();
    
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    
    if (studentDao.existName(paramMap.get("name"))) {
      out.println("같은 이름이 존재합니다. 등록을 취소합니다.");
      return;
    } 
    
    Student student = new Student();
    student.setUserId(paramMap.get("userid"));
    student.setPassword(paramMap.get("password"));
    student.setName(paramMap.get("name"));
    student.setTel(paramMap.get("tel"));
    student.setEmail(paramMap.get("email"));
    
    studentDao.insert(student);
    out.println("등록하였습니다.");
  }

  
  private void doView(String params) {
    String[] kv = params.split("=");
    
    ArrayList<Student> list = studentDao.getListByName(kv[1]);
    for (Student student : list) {
      out.println("--------------------------");
      out.printf("Id: %s\n", student.getUserId());
      out.printf("비밀번호: %s\n", student.getPassword());
      out.printf("이름: %s\n", student.getName());
      out.printf("전화: %s\n", student.getTel());
      out.printf("이메일: %s\n", student.getEmail());
    }
  }

  // 클라이언트에서 보낸 데이터 형식
  // => delete?email=hong@test.com
  private void doDelete(String params) { // 마지막 버전
    String[] kv = params.split("=");
        
    if (!studentDao.existName(kv[1])) {
      out.println("같은 이름을 찾지 못했습니다.");
      return;
    } 
    studentDao.delete(kv[1]);
    out.println("해당 데이터 삭제 완료하였습니다.");
    
  }
}
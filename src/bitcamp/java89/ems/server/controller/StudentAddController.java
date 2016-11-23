package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.StudentDao;
import bitcamp.java89.ems.server.vo.Student;

public class StudentAddController implements Command {
  private StudentDao studentDao; 

  public StudentAddController() {
    studentDao = StudentDao.getInstance();
  }
  
  public void service(HashMap<String, String> paramMap, PrintStream out) {
    if (studentDao.existName(paramMap.get("name"))) {
      out.println("같은 이름이 존재합니다. 등록을 취소합니다.");
      return;
    }
    
    Student student = new Student();
    student.setUserId(paramMap.get("userId"));
    student.setPassword(paramMap.get("password"));
    student.setName(paramMap.get("name"));
    student.setTel(paramMap.get("tel"));
    student.setEmail(paramMap.get("email"));
    
    
    studentDao.insert(student);
    out.println("등록하였습니다.");
  }
}
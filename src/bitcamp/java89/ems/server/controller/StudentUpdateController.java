package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.StudentDao;
import bitcamp.java89.ems.server.vo.Student;

public class StudentUpdateController implements Command {
  private StudentDao studentDao;

  public StudentUpdateController() {
    studentDao = StudentDao.getInstance();
  }
  
  public void service(HashMap<String,String> paramMap, PrintStream out) {
    if (!studentDao.existName(paramMap.get("name"))) {
      out.println("이름을 찾지 못했습니다.");
      return;
    } 
    
    Student student = new Student();
    student.setUserId(paramMap.get("userId"));
    student.setPassword(paramMap.get("password"));
    student.setName(paramMap.get("name"));
    student.setTel(paramMap.get("tel"));
    student.setEmail(paramMap.get("email"));
    
    studentDao.update(student);
    out.println("변경 하였습니다.");
  }
}
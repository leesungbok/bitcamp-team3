package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.StudentDao;
import bitcamp.java89.ems.server.vo.Student;

public class StudentViewController implements Command {
  private StudentDao studentDao; 

  public StudentViewController() {
    studentDao = StudentDao.getInstance();
  }
  
  public void service(HashMap<String, String> paramMap, PrintStream out) {
    ArrayList<Student> list = studentDao.getListByName(paramMap.get("name"));
    for (Student student : list) {
      out.println("--------------------------");
      out.printf("Id: %s\n", student.getUserId());
      out.printf("비밀번호: %s\n", student.getPassword());
      out.printf("이름: %s\n", student.getName());
      out.printf("전화: %s\n", student.getTel());
      out.printf("이메일: %s\n", student.getEmail());
    }
  }
}
package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.StudentDao;
import bitcamp.java89.ems.server.vo.Student;

public class StudentListController implements Command {
  private StudentDao studentDao;

  public StudentListController() {
    studentDao = StudentDao.getInstance();
  }
  
  public void service(HashMap<String,String> paramMap, PrintStream out) {
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
}
package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.StudentDao;

public class StudentDeleteController implements Command {
  private StudentDao studentDao;

  public StudentDeleteController() {
    studentDao = StudentDao.getInstance();
  }
  
  public void service(HashMap<String,String> paramMap, PrintStream out) {
    
    if (!studentDao.existName(paramMap.get("name"))) {
      out.println("해당 데이터 없음");
      return;
    }
    
    studentDao.delete(paramMap.get("name"));
    out.println("해당 데이터를 삭제했음");
  }
}

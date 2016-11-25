package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.ContactDao;

public class ContactDeleteController implements Command {
  public void service(HashMap<String,String> paramMap, PrintStream out) {
    try {
      ContactDao contactDao = ContactDao.getInstance();
      if (!contactDao.existEmail(paramMap.get("email"))) {
        out.println("해당 이메일을 찾지 못했습니다.");
        return;
      }

      contactDao.delete((paramMap.get("email")));
      out.println("삭제하였습니다.");
    } catch (Exception e) {
      out.println("작업중 에러가 발생하였습니다.");
      e.printStackTrace();
    }
  }
}
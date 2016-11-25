package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

public class ContactViewController extends AbstractCommand {
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    ArrayList<Contact> list = 
        ContactDao.getInstance().getListByName(paramMap.get("name"));
    if (list.size() == 0) {
      out.println("해당 인물정보를 찾지 못했습니다.");
      return;
    }
    for (Contact contact : list) {
      out.println("-------------------------");
      out.printf("이름: %s\n", contact.getName());
      out.printf("직위: %s\n", contact.getPosition());
      out.printf("전화: %s\n", contact.getTel());
      out.printf("이메일: %s\n", contact.getEmail());
    }
    out.println("-------------------------");
  }
}
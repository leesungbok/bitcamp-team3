package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

public class ContactListController extends AbstractCommand {
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    ArrayList<Contact> list = ContactDao.getInstance().getList();
    if (list.size() == 0) {
      out.println("연락처 데이터가 없습니다.");
      return;
    }
    for (Contact contact : list) {
      out.printf("%s,%s,%s,%s\n",
        contact.getName(),
        contact.getPosition(),
        contact.getTel(),
        contact.getEmail());
    }
  }
}
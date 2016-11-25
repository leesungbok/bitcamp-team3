package bitcamp.java89.ems.server.dao;

import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Contact;

public class ContactDao extends AbstractDao<Contact> {
  static ContactDao obj;
  
  private ContactDao() throws Exception {
    super("contact-v1.9.data");
  }
  
  public static ContactDao getInstance() throws Exception {
    if (obj == null) {
      obj = new ContactDao();
      obj.load();
    }
    return obj;
  }

  public synchronized void insert(Contact contact) {
    list.add(contact);
    try {this.save();} catch (Exception e) {e.printStackTrace();}
  }
  
  public ArrayList<Contact> getList() {
    return this.list;
  }

  public ArrayList<Contact> getListByName(String name) {
    ArrayList<Contact> results = new ArrayList<>();
    for (Contact contact : list) {
      if (contact.getName().equals(name)) {
        results.add(contact);
      }
    }
    return results;
  }
  
  public synchronized void update (Contact contact) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getEmail().equals(contact.getEmail())) {
        list.set(i, contact);
      }
    }
    try {this.save();} catch (Exception e) {e.printStackTrace();}
  }
  
  public synchronized void delete(String email) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getEmail().equals(email)) {
        list.remove(i);
      }
    }
    try {this.save();} catch (Exception e) {e.printStackTrace();}
  }
  
  public boolean existEmail(String email) {
    for (Contact contact : list) {
      if (contact.getEmail().toLowerCase().equals(email.toLowerCase())) {
        return true;
      }
    }
    return false;
  }
}
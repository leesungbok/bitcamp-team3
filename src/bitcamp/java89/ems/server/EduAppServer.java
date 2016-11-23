package bitcamp.java89.ems.server;

import java.net.ServerSocket;
import java.util.HashMap;

import bitcamp.java89.ems.server.controller.ClassroomAddController;
import bitcamp.java89.ems.server.controller.ClassroomDeleteController;
import bitcamp.java89.ems.server.controller.ClassroomListController;
import bitcamp.java89.ems.server.controller.ClassroomUpdateController;
import bitcamp.java89.ems.server.controller.ClassroomViewController;
import bitcamp.java89.ems.server.controller.ContactAddController;
import bitcamp.java89.ems.server.controller.ContactDeleteController;
import bitcamp.java89.ems.server.controller.ContactListController;
import bitcamp.java89.ems.server.controller.ContactUpdateController;
import bitcamp.java89.ems.server.controller.ContactViewController;
import bitcamp.java89.ems.server.controller.CurriculumAddController;
import bitcamp.java89.ems.server.controller.CurriculumDeleteController;
import bitcamp.java89.ems.server.controller.CurriculumListController;
import bitcamp.java89.ems.server.controller.CurriculumUpdateController;
import bitcamp.java89.ems.server.controller.CurriculumViewController;
import bitcamp.java89.ems.server.controller.StudentAddController;
import bitcamp.java89.ems.server.controller.StudentDeleteController;
import bitcamp.java89.ems.server.controller.StudentListController;
import bitcamp.java89.ems.server.controller.StudentUpdateController;
import bitcamp.java89.ems.server.controller.StudentViewController;
import bitcamp.java89.ems.server.controller.TextbookAddController;
import bitcamp.java89.ems.server.controller.TextbookDeleteController;
import bitcamp.java89.ems.server.controller.TextbookListController;
import bitcamp.java89.ems.server.controller.TextbookUpdateController;
import bitcamp.java89.ems.server.controller.TextbookViewController;

public class EduAppServer {
  // Command 구현체 보관소
  // => HashMap<명령문자열,요청처리객체> commandMap
  HashMap<String,Command> commandMap = new HashMap<>();
  
  public EduAppServer() {
    // 클라이언트 요청을 처리할 Command 구현체 준비
    commandMap.put("contact/add", new ContactAddController());
    commandMap.put("contact/list", new ContactListController());
    commandMap.put("contact/view", new ContactViewController());
    commandMap.put("contact/delete", new ContactDeleteController());
    commandMap.put("contact/update", new ContactUpdateController());
    commandMap.put("classroom/add", new ClassroomAddController());
    commandMap.put("classroom/list", new ClassroomListController());
    commandMap.put("classroom/view", new ClassroomViewController());
    commandMap.put("classroom/delete", new ClassroomDeleteController());
    commandMap.put("classroom/update", new ClassroomUpdateController());
    commandMap.put("curriculum/add",    new CurriculumAddController());
    commandMap.put("curriculum/list",   new CurriculumListController());
    commandMap.put("curriculum/view",   new CurriculumViewController());
    commandMap.put("curriculum/delete", new CurriculumDeleteController());
    commandMap.put("curriculum/update", new CurriculumUpdateController());
    commandMap.put("student/add",       new StudentAddController());
    commandMap.put("student/list",      new StudentListController());
    commandMap.put("student/view",      new StudentViewController());
    commandMap.put("student/delete",    new StudentDeleteController());
    commandMap.put("student/update",    new StudentUpdateController());
    commandMap.put("textbook/add",      new TextbookAddController());
    commandMap.put("textbook/list",     new TextbookListController());
    commandMap.put("textbook/view",     new TextbookViewController());
    commandMap.put("textbook/delete",   new TextbookDeleteController());
    commandMap.put("textbook/update",   new TextbookUpdateController());
    
  }
  
  private void service() throws Exception {
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버 실행 중...");
    
    while (true) {
      new RequestThread(ss.accept(), commandMap).start();
    }
  }

  public static void main(String[] args) throws Exception {
    EduAppServer eduServer = new EduAppServer();
    eduServer.service();
  }
}



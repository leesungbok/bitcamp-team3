package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.ClassroomDao;

public class ClassroomDeleteController extends AbstractCommand {
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    ClassroomDao classRoomDao = ClassroomDao.getInstance();
    if (!classRoomDao.existName(paramMap.get("name"))) {
      out.println("해당 강의실을 찾지 못했습니다.");
      return;
    }

    classRoomDao.delete(paramMap.get("name"));
    out.println("삭제하였습니다.");
  }
}
package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;
import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.TextbookDao;
import bitcamp.java89.ems.server.vo.Textbook;

public class TextbookUpdateController implements Command {
  private TextbookDao textbookDao;

  public TextbookUpdateController() {
    textbookDao = TextbookDao.getInstance();
  }

  public void service(HashMap<String,String> paramMap, PrintStream out) {
    if (!textbookDao.existTitle(paramMap.get("title"))) {
      out.println("제목을 찾지 못했쬬잉 잉잉.");
      return;
    }
      Textbook textbook = new Textbook();
      textbook.setTitle(paramMap.get("title"));
      textbook.setAuthor(paramMap.get("author"));
      textbook.setPress(paramMap.get("press"));
      textbook.setPage(Integer.parseInt(paramMap.get("page")));
      textbook.setPrice(Integer.parseInt(paramMap.get("price")));
      textbook.setDayofissue(paramMap.get("dayofissue"));
      
      textbookDao.update(textbook);
      out.println("변경완료쿠? 뿌잉뿌잉");    

    }
  }


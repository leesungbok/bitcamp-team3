package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.TextbookDao;
import bitcamp.java89.ems.server.vo.Textbook;



public class TextbookAddController implements Command {
  private TextbookDao textbookDao;

  public TextbookAddController() {
    textbookDao = TextbookDao.getInstance();
  }

  public void service(HashMap<String,String> paramMap, PrintStream out) {
    if (textbookDao.existTitle(paramMap.get("title"))) {
      out.println("같은 제목이 존재할깝쇼? . 등록을 취소하시렵니까?.");
      return;
    }
           
      Textbook textbook = new Textbook();
      textbook.setTitle(paramMap.get("title"));
      textbook.setAuthor(paramMap.get("author"));
      textbook.setPress(paramMap.get("press"));
      textbook.setPage(Integer.parseInt(paramMap.get("page")));
      textbook.setPrice(Integer.parseInt(paramMap.get("price")));
      textbook.setDayofissue(paramMap.get("dayofissue"));
      
      textbookDao.insert(textbook);
      out.println("등록하였습니돵~ .");
    }
  }


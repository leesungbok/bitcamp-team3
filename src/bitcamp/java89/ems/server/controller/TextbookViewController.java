package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.TextbookDao;
import bitcamp.java89.ems.server.vo.Textbook;



public class TextbookViewController implements Command {
  private TextbookDao textbookDao;

  public TextbookViewController() {
    textbookDao = TextbookDao.getInstance();
  }

  public void service(HashMap<String,String> paramMap, PrintStream out) {
    ArrayList<Textbook> list = textbookDao.getListByTitle(paramMap.get("title"));
    for (Textbook textbook : list) {
      out.printf("교재명 : %s\n", textbook.getTitle());
      out.printf("저자 : %s\n", textbook.getAuthor());
      out.printf("출판사 : %s\n", textbook.getPress());
      out.printf("페이지수 : %d\n", textbook.getPage());
      out.printf("가격 : %d\n", textbook.getPrice());
      out.printf("발행일 : %s\n", textbook.getDayofissue());
      
    }
  }
}

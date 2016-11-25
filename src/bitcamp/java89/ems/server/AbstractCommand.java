package bitcamp.java89.ems.server;

import java.io.PrintStream;
import java.util.HashMap;

public abstract class AbstractCommand implements Command {
  // Command의 메서드를 구현한다.
  // 이 메서드에 예외처리 코드를 둔다.
  @Override
  public void service(HashMap<String, String> paramMap, PrintStream out) {
    try {
      doResponse(paramMap, out);
    } catch (Exception e) {
    out.println("작업중 에러가 발생하였습니다.");
    e.printStackTrace();
    }
  }

  protected void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
    System.out.println("작업을 준비중입니다.");
  }
}
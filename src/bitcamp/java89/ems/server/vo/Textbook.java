package bitcamp.java89.ems.server.vo;

import java.io.Serializable;

public class Textbook implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected String title;
  protected String author;
  protected String press;
  protected int page;
  protected int price;
  protected String dayofissue;

  public Textbook() {}

  public Textbook(String title, String author, String press) {
    this.title = title;
    this.author = author;
    this.press = press;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPress() {
    return press;
  }

  public void setPress(String press) {
    this.press = press;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getDayofissue() {
    return dayofissue;
  }

  public void setDayofissue(String dayofissue) {
    this.dayofissue = dayofissue;
  }

  @Override
  public String toString() {
    return "Textbook [title=" + title + ", author=" + author + ", press=" + press + ", page=" + page + ", price="
        + price + ", dayofissue=" + dayofissue + "]";
  }
}

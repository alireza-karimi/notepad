package notepad;

import java.io.Serializable;

/**
 * reprrsents a single note
 * @author alireza
 *
 */
public class Note implements Serializable {

  private String title;
  private String content;
  private String date;

  /**
   * creating  a new note
   * @param title
   * @param content
   * @param date
   */
  public Note(String title, String content, String date) {
      this.title = title;
      this.content = content;
      this.date = date;
  }

  /**
   * getting content of note
   * @return content of note
   */
  public String getContent(){
	  return content;
  }
  
  /**
   * note to string
   */
  @Override
  public String toString() {
      return "Note{" +
              "title='" + title + '\'' +
              ", date='" + date + '\'' +
              '}';
  }
	
}

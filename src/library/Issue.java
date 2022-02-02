package library;

import java.time.LocalDate;

public class Issue {

  //Setup Attributes
  private int issue_id = 1;
  private int member_id;
  private int book_id;
  private String issueDate;
  private String dueDate;
  private String name;
  private String bookTitle;
  private boolean isActive = true;

public Issue(){}

  //Constructor
  public Issue(
    int member_id,
    int book_id,
    int issue_id,
    String issueDate,
    String dueDate,
    String name,
    String bookTitle, 
    boolean isActive
  ) {
    this.member_id = member_id;
    this.book_id = book_id;
    this.issueDate = issueDate;
    this.dueDate = dueDate;
    this.name = name;
    this.issue_id = issue_id;
    this.bookTitle = bookTitle;
    this.isActive = isActive;
  }

  public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
  }

  public String getBookTitle() {
    return bookTitle;
  }

  public void setIssue_id(int issue_id) {
    this.issue_id = issue_id;
  }

  public int getIssue_id() {
    return issue_id;
  }

  public void setBook_id(int book_id) {
    this.book_id = book_id;
  }

  public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
  }

  public void setIssueDate(String issueDate) {
    this.issueDate = issueDate;
  }

  public void setMember_id(int member_id) {
    this.member_id = member_id;
  }

  public int getBook_id() {
    return book_id;
  }

  public String getDueDate() {
    return dueDate;
  }

  public String getIssueDate() {
    return issueDate;
  }

  public int getMember_id() {
    return member_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setIsActive(boolean active){
    this.isActive = active;
  }

  public boolean getIsActive(){
    return isActive;
  }
  //primary methods
  @Override
  public String toString() {
    return super.toString();
  }

  public void displayIssue() {
    System.out.println(this.toString());
  }

  public void renewIssue() {}

  public void checkOverdue() {}

public LocalDate getDateNow(){
  LocalDate today = LocalDate.now();
  return today;
}

}

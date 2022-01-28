package library;

public class Issue {

  //Setup Attributes
  private int member_id;
  private int book_id;
  private Object issueDate;
  private Object dueDate;
  private String title;
  private Object returnedOn;

  //Constructor
  public Issue(
    int member_id,
    int book_id,
    Object issueDate,
    Object dueDate,
    String title,
    Object returnedOn
  ) {
    this.member_id = member_id;
    this.book_id = book_id;
    this.issueDate = issueDate;
    this.dueDate = dueDate;
    this.title = title;
    this.returnedOn = returnedOn;
  }

  public void setBook_id(int book_id) {
    this.book_id = book_id;
  }

  public void setDueDate(Object dueDate) {
    this.dueDate = dueDate;
  }

  public void setIssueDate(Object issueDate) {
    this.issueDate = issueDate;
  }

  public void setMember_id(int member_id) {
    this.member_id = member_id;
  }

  public void setReturnedOn(Object returnedOn) {
    this.returnedOn = returnedOn;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getBook_id() {
    return book_id;
  }

  public Object getDueDate() {
    return dueDate;
  }

  public Object getIssueDate() {
    return issueDate;
  }

  public int getMember_id() {
    return member_id;
  }

  public Object getReturnedOn() {
    return returnedOn;
  }

  public String getTitle() {
    return title;
  }

  //primary methods
  @Override
  public String toString() {
    return (
      "{" +
      " member_id='" +
      getMember_id() +
      "'" +
      ", book_id='" +
      getBook_id() +
      "'" +
      ", issueDate='" +
      getIssueDate() +
      "'" +
      ", dueDate='" +
      getDueDate() +
      "'" +
      ", title='" +
      getTitle() +
      "'" +
      ", returnedOn='" +
      getReturnedOn() +
      "'" +
      "}"
    );
  }

  public void displayIssue() {
    System.out.println(this.toString());
  }

  public void renewIssue() {}

  public void checkOverdue() {}
}

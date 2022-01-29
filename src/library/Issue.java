package library;

public class Issue {

  //Setup Attributes
  private int issue_id;
  private int member_id;
  private int book_id;
  private Object issueDate;
  private Object dueDate;
  private String title;
  private String name;

  //Constructor
  public Issue(
    int member_id,
    int book_id,
    Object issueDate,
    Object dueDate,
    String title,
    String name,
    int issue_id
  ) {
    this.member_id = member_id;
    this.book_id = book_id;
    this.issueDate = issueDate;
    this.dueDate = dueDate;
    this.title = title;
    this.name = name;
    this.issue_id = issue_id;
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

  public void setDueDate(Object dueDate) {
    this.dueDate = dueDate;
  }

  public void setIssueDate(Object issueDate) {
    this.issueDate = issueDate;
  }

  public void setMember_id(int member_id) {
    this.member_id = member_id;
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

  public String getTitle() {
    return title;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
}

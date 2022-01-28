package library;

public class Member extends Person {

  private int member_id;
  private int noOfBookIssued;
  private String department;

  public Member() {}

  public Member(int member_id, int noOfBookIssued, String department) {
    this.member_id = member_id;
    this.noOfBookIssued = noOfBookIssued;
    this.department = department;
  }

  public int getMember_id() {
    return this.member_id;
  }

  public void setMember_id(int member_id) {
    this.member_id = member_id;
  }

  public int getNoOfBookIssued() {
    return this.noOfBookIssued;
  }

  public void setNoOfBookIssued(int noOfBookIssued) {
    this.noOfBookIssued = noOfBookIssued;
  }

  public String getDepartment() {
    return this.department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }
}

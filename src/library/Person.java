package library;

public class Person {

  protected String fullName;
  protected int phoneNo;
  protected String email;
  protected String address;


  public String getFullName() {
    return this.fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public int getPhoneNo() {
    return this.phoneNo;
  }

  public void setPhoneNo(int phoneNo) {
    this.phoneNo = phoneNo;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
  

  static void searchBook(String key) {}
}

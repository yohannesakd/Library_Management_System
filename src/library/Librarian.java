package library;

public class Librarian extends Person {

  protected String username;
  protected String password;
  protected int id;

  public Librarian() {}

  public Librarian(
    int id,
    String username,
    String password,
    String fullName,
    int phoneNo,
    String email,
    String address
  ) {
    this.id = id;
    super.fullName = fullName;
    this.username = username;
    this.password = password;
    super.phoneNo = phoneNo;
    super.address = address;
    super.email = email;
  }

  public void setFullName(String fullName){
    super.fullName = fullName;
  }
  public void setPhoneNo(int phoneNo){
    super.phoneNo = phoneNo;
  }
  public void setEmail(String email){
    super.email = email;
  }
  public void setAddress(String address){
    super.address = address;
  }
  public String getFullName() {
    return super.fullName;
  }

  public int getPhoneNo() {
    return super.phoneNo;
  }

  public String getEmail() {
    return super.email;
  }

  public String getAddress() {
    return super.address;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  void issueBook(int book_id, int member_id) {}

  void returnBook(int book_id, int member_id) {}

  void changePassword(String oldPass, String newPass) {}

  void changeUsername(String oldUsername, String newUsername) {}
}

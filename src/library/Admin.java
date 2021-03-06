package library;

import static library.FileAlter.saveToFile;

import java.io.IOException;

public class Admin extends Librarian {

  public Admin() {}

  public Admin(
    int id,
    String username,
    String password,
    String fullName,
    int phoneNo,
    String email,
    String address
  ) {
    // super(username, password, id);
    super(id, username, password, fullName, phoneNo, email, address);
  }

  //just for the demonstration lets make it static
  public static String addBook(Book book) {
    book.setIsAvailable();
    String bookinput =
      book.getBook_id() +
      "|" +
      book.getTitle() +
      "|" +
      book.getAuthor() +
      "|" +
      book.getCategory()+
      "|" +
      book.getEdition() +
      "|" +
      book.getIsbn() +
      "|" +
      book.getPages() +
      "|" +
      book.getShelfNo() +
      "|" +
      book.getNoOfBookCopy() +
      "|" +
      book.getNoOfBookIssued();
    try {
      // System.out.println(bookinput);
      saveToFile("Books.txt", bookinput, true);
    } catch (IOException ex) {
      return "UNSUCESSFULL";
    }
    return "SUCESSFULL";
  }

  public void editBook(Book book) {}

  public void removeBook(Book book) {}

  public static String addAdmin(Admin admin) {
    String empInput =
      admin.getId() +
      "|" +
      admin.getUsername() +
      "|" +
      admin.getPassword() +
      "|" +
      admin.getFullName() +
      "|" +
      admin.getPhoneNo() +
      "|" +
      admin.getEmail() +
      "|" +
      admin.getAddress();

    try {
      System.out.println(empInput);
      saveToFile("Admins.txt", empInput, true);
    } catch (IOException ex) {
      return "UNSUCCESSFUL";
    }
    return "SUCCESSFUL";
  }

  public void removeAdmin() {}

  public static String addLibrarian(Librarian librarian) {
    String empInput =
      librarian.getId() +
      "|" +
      librarian.getUsername() +
      "|" +
      librarian.getPassword() +
      "|" +
      librarian.getFullName() +
      "|" +
      librarian.getPhoneNo() +
      "|" +
      librarian.getEmail() +
      "|" +
      librarian.getAddress();

    try {
      System.out.println(empInput);
      saveToFile("Librarians.txt", empInput, true);
    } catch (IOException ex) {
      return "UNSUCCESSFUL";
    }
    return "SUCCESSFUL";
  }

  public void removeLibrarian(int id) {}
}

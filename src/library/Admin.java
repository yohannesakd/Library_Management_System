package library;

import static library.FileAlter.saveToFile;

import java.io.IOException;

public class Admin extends Librarian {

  public Admin() {}

  public Admin(String username, String password, int id) {
    // super(username, password, id);
  }

  //just for the demonstration lets make it static
  public static String addBook(Book book) {
    String bookinput =
      book.getBook_id() +
      "|" +
      book.getTitle() +
      "|" +
      book.getAuthor() +
      "|" +
      book.getEdition() +
      "|" +
      book.getPages() +
      "|" +
      book.getIsbn() +
      "|" +
      book.getShelfNo();
    try {
      // System.out.println(bookinput);
      saveToFile("Books.txt", bookinput, true);
    } catch (IOException ex) {
      return "UNSUCESSFULL";
    }
    return "SUCESSFULL";
  }

  public void editBook(Book book){}
  
  public void removeBook(Book book) {}

  public void addAdmin(Admin admin) {}

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

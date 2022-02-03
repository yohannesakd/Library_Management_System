package library;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileAlter {

  public static void saveToFile(
    String fileName,
    String fileString,
    boolean append
  ) throws IOException {
    System.out.println(fileString);
    FileWriter fw = new FileWriter(new File(fileName), append);
    try (PrintWriter pw = new PrintWriter(fw)) {
      pw.println(fileString);
    }
  }

  public static String deleteBook(int id) throws FileNotFoundException {
    boolean deletion = false;
    ArrayList<Book> booklist = FileAlter.retrieveAllbookFile();

    if (new File("Books.txt").delete()) {} else {
      return "FILE NOT DELETED";
    }

    for (Book booklist1 : booklist) {
      if (booklist1.getBook_id() != id) Admin.addBook(booklist1); else {
        deletion = true;
      }
    }
    if (!deletion) return "NO FILE DELETED";
    return "SUCCESSFULL";
  }

  public static String deleteMember(int id) throws FileNotFoundException {
    boolean deletion = false;
    ArrayList<Member> memlist = retrieveAllMemberFile();

    if (new File("Members.txt").delete()) {} else {
      return "FILE NOT DELETED";
    }

    for (Member memItem : memlist) {
      if (memItem.getMember_id() != id) Librarian.addMember(memItem); else {
        deletion = true;
      }
    }
    if (!deletion) return "NO FILE DELETED";
    return "SUCCESSFULL";
  }

  public static String deleteLibrarian(int id) throws FileNotFoundException {
    boolean deletion = false;
    ArrayList<Librarian> librarianlist = retrieveAllemployeeFile();

    if (new File("Librarians.txt").delete()) {} else {
      return "FILE NOT DELETED";
    }

    for (Librarian libr : librarianlist) {
      if (libr.getId() != id) Admin.addLibrarian(libr); else {
        deletion = true;
      }
    }
    if (!deletion) return "NO FILE DELETED";
    return "SUCCESSFULL";
  }

  public static String deleteAdmin(int id) throws FileNotFoundException {
    boolean deletion = false;
    ArrayList<Admin> adminList = retrieveAllAdminFile();

    if (new File("Admins.txt").delete()) {} else {
      return "FILE NOT DELETED";
    }

    for (Admin admin : adminList) {
      if (admin.getId() != id) Admin.addAdmin(admin); else {
        deletion = true;
      }
    }
    if (!deletion) return "NO FILE DELETED";
    return "SUCCESSFULL";
  }

  public static String editBook(Book book) throws FileNotFoundException {
    ArrayList<Book> booklist = FileAlter.retrieveAllbookFile();
    boolean alter = false;
    if (new File("Books.txt").delete()) {} else {
      return "FILE NOT DELETED";
    }

    for (Book booklist1 : booklist) {
      if (booklist1.getBook_id() != book.getBook_id()) Admin.addBook(
        booklist1
      ); else {
        alter = true;
        Admin.addBook(book);
      }
    }
    if (!alter) return "NO FILE ALTERED";
    return "SUCCESSFULL";
  }

  public static String editIssueState(Issue editedIssue)
    throws FileNotFoundException, IOException {
    ArrayList<Issue> issueList = FileAlter.retrieveAllIssueFile();
    boolean alter = false;
    if (new File("Issues.txt").delete()) {} else {
      return "FILE NOT DELETED";
    }

    for (int i = 0; i < issueList.size(); i++) {
      System.out.println(issueList.get(i));
      System.out.println("lets see again");

      if (
        issueList.get(i).getBook_id() != editedIssue.getBook_id() ||
        issueList.get(i).getMember_id() != editedIssue.getMember_id()
      ) Librarian.addIssue(issueList.get(i)); else {
        alter = true;
        Librarian.addIssue(editedIssue);
      }
    }
    if (!alter) return "NO FILE ALTERED";
    return "SUCCESSFULL";
  }

  public static String retrieveBookTitle(int bookId) throws IOException {
    ArrayList<Book> bookList = new ArrayList<>();
    bookList = retrieveAllbookFile();
    for (int i = 0; i < bookList.size(); i++) {
      if (bookList.get(i).getBook_id() == bookId) {
        return bookList.get(i).getTitle();
      }
    }
    return "";
  }

  public static ArrayList<Book> retrieveAllbookFile()
    throws FileNotFoundException {
    ArrayList<Book> booklist = new ArrayList<>();
    try (Scanner s = new Scanner(new File("Books.txt"))) {
      while (s.hasNextLine()) {
        String line = s.nextLine();
        String[] values = line.split("\\|");

        Book bk = new Book(
          Integer.parseInt(values[0]),
          values[1],
          values[2],
          values[3],
          Long.parseLong(values[4]),
          Integer.parseInt(values[5]),
          values[6],
          Integer.parseInt(values[7]),
          Integer.parseInt(values[8])
        );
        booklist.add(bk);
      }
    }
    return booklist;
  }

  public static ArrayList<Librarian> retrieveAllemployeeFile()
    throws FileNotFoundException {
    ArrayList<Librarian> emplist = new ArrayList<>();
    try (Scanner s = new Scanner(new File("Librarians.txt"))) {
      while (s.hasNextLine()) {
        String line = s.nextLine();
        String[] values = line.split("\\|");

        Librarian lb = new Librarian(
          Integer.parseInt(values[0]),
          values[1],
          values[2],
          values[3],
          Integer.parseInt(values[4]),
          values[5],
          values[6]
        );
        emplist.add(lb);
      }
    }
    return emplist;
  }

  public static ArrayList<Admin> retrieveAllAdminFile()
    throws FileNotFoundException {
    ArrayList<Admin> adminList = new ArrayList<>();
    try (Scanner s = new Scanner(new File("Admins.txt"))) {
      while (s.hasNextLine()) {
        String line = s.nextLine();
        String[] values = line.split("\\|");

        Admin lb = new Admin(
          Integer.parseInt(values[0]),
          values[1],
          values[2],
          values[3],
          Integer.parseInt(values[4]),
          values[5],
          values[6]
        );
        adminList.add(lb);
      }
    }
    return adminList;
  }

  public static Issue retrieveSingleIssue(int memberId, int bookId)
    throws FileNotFoundException {
    ArrayList<Issue> issueList = retrieveAllIssueFile();
    for (int i = 0; i < issueList.size(); i++) {
      if (
        issueList.get(i).getBook_id() == bookId &&
        issueList.get(i).getMember_id() == memberId
      ) {
        return issueList.get(i);
      }
    }
    return new Issue();
  }

  public static Admin retrieveSingleAdmin(int id) throws FileNotFoundException {
    ArrayList<Admin> adminList = retrieveAllAdminFile();
    for (int i = 0; i < adminList.size(); i++) {
      if (adminList.get(i).getId() == id) {
        return adminList.get(i);
      }
    }
    return new Admin();
  }

  public static Librarian retrieveSingleEmployee(int id)
    throws FileNotFoundException {
    ArrayList<Librarian> empList = retrieveAllemployeeFile();
    for (int i = 0; i < empList.size(); i++) {
      if (empList.get(i).getId() == id) {
        return empList.get(i);
      }
    }
    return new Librarian();
  }

  public static ArrayList<Issue> retrieveAllIssueFile()
    throws FileNotFoundException {
    ArrayList<Issue> issueList = new ArrayList<>();
    try (Scanner s = new Scanner(new File("Issues.txt"))) {
      while (s.hasNextLine()) {
        String line = s.nextLine();
        String[] values = line.split("\\|");
        System.out.println(values);
        Issue lb = new Issue(
          Integer.parseInt(values[0]),
          Integer.parseInt(values[1]),
          Integer.parseInt(values[2]),
          values[3],
          values[4],
          values[5],
          values[6],
          Boolean.parseBoolean(values[7])
        );
        issueList.add(lb);
      }
    }
    return issueList;
  }

  public static String retrieveMemberName(int memberId)
    throws FileNotFoundException {
    ArrayList<Member> memberList = new ArrayList<>();
    memberList = retrieveAllMemberFile();

    for (int i = 0; i < memberList.size(); i++) {
      if (memberList.get(i).getMember_id() == memberId) {
        return memberList.get(i).getFullName();
      }
    }
    return "";
  }

  public static ArrayList<Member> retrieveAllMemberFile()
    throws FileNotFoundException {
    ArrayList<Member> memberList = new ArrayList<>();
    try (Scanner s = new Scanner(new File("Members.txt"))) {
      while (s.hasNextLine()) {
        String line = s.nextLine();
        String[] values = line.split("\\|");

        Member mem = new Member(
          values[0],
          Integer.parseInt(values[1]),
          values[2],
          values[3],
          Integer.parseInt(values[4]),
          Integer.parseInt(values[5])
        );
        memberList.add(mem);
      }
    }
    return memberList;
  }
}

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

  public static ArrayList<Issue> retrieveAllIssueFile()
    throws FileNotFoundException {
    ArrayList<Issue> issueList = new ArrayList<>();
    try (Scanner s = new Scanner(new File("Issues.txt"))) {
      while (s.hasNextLine()) {
        String line = s.nextLine();
        String[] values = line.split("\\|");

        Issue lb = new Issue(
          Integer.parseInt(values[0]),
          Integer.parseInt(values[1]),
          Integer.parseInt(values[2]),
          values[3],
          values[4],
          values[5],
          values[6]
        );
        issueList.add(lb);
      }
    }
    return issueList;
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

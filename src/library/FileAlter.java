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
          values[6]
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
}

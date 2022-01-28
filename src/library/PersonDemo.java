package library;

import java.util.ArrayList;

public class PersonDemo {

  public static void main(String arg[]) {
    // Librarian libr = new Librarian("john", "pass", 1);
    // ArrayList<Librarian> librarian = new ArrayList<>(1);
    // librarian.add(libr);

    Admin a = new Admin("yohannes", "enter", 1);
    ArrayList<Admin> admin = new ArrayList<>(1);
    admin.add(a);

    Member m = new Member(1, 1, "Software");
    ArrayList<Member> member = new ArrayList<>(1);
    member.add(m);
  }
}

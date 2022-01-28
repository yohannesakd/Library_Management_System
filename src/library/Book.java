package library;

public class Book {

  //Define Class Attributes
  private int book_id;
  private String title;
  private String author;
  private String edition;
  private long isbn;
  private int pages;
  private boolean isAvailable = true;
  private String shelfNo;

  //Constructor
  public Book(
    int book_id,
    String title,
    String author,
    String edition,
    long isbn,
    int pages,
    String shelfNo
  ) {
    this.book_id = book_id;
    this.title = title;
    this.author = author;
    this.edition = edition;
    this.isbn = isbn;
    this.pages = pages;
    this.shelfNo = shelfNo;
  }

  public Book() {}

  //Setup accessors
  //Get
  public int getBook_id() {
    return book_id;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public String getEdition() {
    return edition;
  }

  public long getIsbn() {
    return isbn;
  }

  public int getPages() {
    return pages;
  }

  public String getShelfNo() {
    return shelfNo;
  }

  public boolean getIsAvailable() {
    return isAvailable;
  }

  //Set
  public void setAuthor(String author) {
    this.author = author;
  }

  public void setAvailable(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  public void setBook_id(int book_id) {
    this.book_id = book_id;
  }

  public void setEdition(String edition) {
    this.edition = edition;
  }

  public void setIsbn(long isbn) {
    this.isbn = isbn;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public void setShelfNo(String shelfNo) {
    this.shelfNo = shelfNo;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  //Primary Methods
  @Override
  public String toString() {
    return (
      "{" +
      " book_id='" +
      getBook_id() +
      "'" +
      ", title='" +
      getTitle() +
      "'" +
      ", author='" +
      getAuthor() +
      "'" +
      ", edition='" +
      getEdition() +
      "'" +
      ", isbn='" +
      getIsbn() +
      "'" +
      ", pages='" +
      getPages() +
      "'" +
      ", available='" +
      getIsAvailable() +
      "'" +
      ", shelfNo='" +
      getShelfNo() +
      "'" +
      "}"
    );
  }

  public void displayBook() {
    System.out.println(this.toString());
  }
}

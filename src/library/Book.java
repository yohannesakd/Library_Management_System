package library;

public class Book {

  //Define Class Attributes
  private int book_id = 1000;
  private String title;
  private String author;
  private String edition;
  private long isbn;
  private int pages;
  private boolean isAvailable = true;
  private String shelfNo;
  private int noOfBookCopy;
  private int noOfBookIssued = 0;
  
  //Constructor
  public Book(
    int book_id,
    String title,
    String author,
    String edition,
    long isbn,
    int pages,
    String shelfNo,
    int noOfBookCopy,
    int noOfBookIssued
  ) {
    this.book_id = book_id;
    this.title = title;
    this.author = author;
    this.edition = edition;
    this.isbn = isbn;
    this.pages = pages;
    this.shelfNo = shelfNo;
    this.noOfBookCopy = noOfBookCopy;
    this.noOfBookIssued = noOfBookIssued;

  }

  public Book() {}

  //Setup accessors
  //Get
    public int getNoOfBookCopy() {
        return noOfBookCopy;
    }

    public void setNoOfBookCopy(int noOfBookCopy) {
        this.noOfBookCopy = noOfBookCopy;
    }

    public int getNoOfBookIssued() {
        return noOfBookIssued;
    }

    public void setNoOfBookIssued(int noOfBookIssued) {
        this.noOfBookIssued = noOfBookIssued;
    }
  
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
  
    public void setIsAvailable() {
        this.isAvailable = this.noOfBookCopy != this.noOfBookIssued;
    }

}

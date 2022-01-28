package LibraryReferal;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import library.Book;

public class BookListGUIController implements Initializable {

  @FXML
  private TableView<Book> tableBook;

  @FXML
  private TableColumn<Book, String> bookid;

  @FXML
  private TableColumn<Book, String> title;

  @FXML
  private TableColumn<Book, String> author;

  @FXML
  private TableColumn<Book, String> edition;

  @FXML
  private TableColumn<Book, String> isbn;

  @FXML
  private TableColumn<Book, String> pages;

  @FXML
  private TableColumn<Book, String> shelf;

  @FXML
  private TextField searchBar;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    bookid.setCellValueFactory(new PropertyValueFactory<>("book_id"));
    title.setCellValueFactory(new PropertyValueFactory<>("title"));
    author.setCellValueFactory(new PropertyValueFactory<>("author"));
    edition.setCellValueFactory(new PropertyValueFactory<>("edition"));
    isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
    pages.setCellValueFactory(new PropertyValueFactory<>("pages"));
    shelf.setCellValueFactory(new PropertyValueFactory<>("shelfNo"));

    try {
      tableBook.setItems(giveBooks());
    } catch (FileNotFoundException ex) {
      System.out.println("FileNotFound");
    }

    //wrap the observable list in a set filter to use to filter out the search

    FilteredList<Book> searchFilter;
    try {
      searchFilter = new FilteredList<>(giveBooks(), b -> true);

      //set the filter whenever the searchBar changes value

      searchBar
        .textProperty()
        .addListener((observable, oldvalue, newvalue) -> {
          searchFilter.setPredicate(book -> {
            //if the search bar is empty then return the orginal table
            if (newvalue == null || newvalue.isEmpty()) {
              return true;
            }

            if (
              Integer.toString(book.getBook_id()).contains(searchBar.getText())
            ) return true; else if (
              book
                .getTitle()
                .toLowerCase()
                .contains(searchBar.getText().toLowerCase())
            ) return true; else if (
              book
                .getAuthor()
                .toLowerCase()
                .contains(searchBar.getText().toLowerCase())
            ) return true; else if (
              book
                .getEdition()
                .toLowerCase()
                .contains(searchBar.getText().toLowerCase())
            ) return true; else if (
              Long.toString(book.getIsbn()).contains(searchBar.getText())
            ) return true; else if (
              Integer.toString(book.getPages()).contains(searchBar.getText())
            ) return true; else if (
              book
                .getShelfNo()
                .toLowerCase()
                .contains(searchBar.getText().toLowerCase())
            ) return true;

            return false;
          });
        });

      SortedList<Book> sortedSearch = new SortedList<>(searchFilter);
      sortedSearch.comparatorProperty().bind(tableBook.comparatorProperty());

      tableBook.setItems(sortedSearch);
    } catch (FileNotFoundException ex) {
      Logger
        .getLogger(BookListGUIController.class.getName())
        .log(Level.SEVERE, null, ex);
    }
  }

  public ObservableList<Book> giveBooks() throws FileNotFoundException {
    ObservableList<Book> books = FXCollections.observableArrayList(
      library.FileAlter.retrieveAllbookFile()
    );
    return books;
  }
}

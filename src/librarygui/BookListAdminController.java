package librarygui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import library.*;

public class BookListAdminController implements Initializable {

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

  @FXML
  private Button add;

  @FXML
  private Button remove;

  @FXML
  private Button edit;

  @FXML
  private Button back;

  @FXML
  private TextField removeId;

  @FXML
  private TextField editId;

  @FXML
  private Label removeAlert;

  @FXML
  public void addBook(ActionEvent event) throws IOException {
    Stage stage = (Stage) add.getScene().getWindow();
    stage.close();
    stage = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("fx/addBook.fxml"));

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void goBack(ActionEvent event) throws IOException {
    Stage stage = (Stage) back.getScene().getWindow();
    stage.close();
    stage = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("fx/FirstPage.fxml"));

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void showRemove(ActionEvent event) {
    removeId.setVisible(true);
    removeId.requestFocus();
  }

  @FXML
  public void showEdit(ActionEvent event) {
    editId.setVisible(true);
    editId.requestFocus();
  }

  @FXML
  public void editBook(ActionEvent event) throws IOException {
    System.out.println("Sth");
    Stage stage = (Stage) back.getScene().getWindow();
    stage.close();
    stage = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("fx/EditBook.fxml"));

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void removeBook(ActionEvent event) throws IOException {
    try {
      removeAlert.setText(
        FileAlter.deleteBook(Integer.parseInt(removeId.getText()))
      );
      System.out.println("Something");
    } catch (NumberFormatException n) {
      System.out.println("Invalid ID");
      removeAlert.setText("Invalid ID");
    }
    Stage stage = (Stage) removeAlert.getScene().getWindow();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/BookListAdmin.fxml")
    );

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

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
        .getLogger(BookListAdminController.class.getName())
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

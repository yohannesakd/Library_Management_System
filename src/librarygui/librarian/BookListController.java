package librarygui.librarian;

import CustomException.NullFieldException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

public class BookListController implements Initializable {

  public static int CurrentBookId;

  @FXML
  private Button home;

  @FXML
  private Button book;

  @FXML
  private Button cust;

  @FXML
  private Button issues;

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
  private TableColumn<Book, String> category;

  @FXML
  private TableColumn<Book, String> isbn;

  @FXML
  private TableColumn<Book, String> pages;

  @FXML
  private TableColumn<Book, String> shelf;

  @FXML
  private TableColumn<Book, String> copies;

  @FXML
  private TableColumn<Book, String> issuesCol;

  @FXML
  private TableColumn<Book, String> availablity;

  @FXML
  private TextField searchBar;

  @FXML
  private MenuItem editProfile;

  @FXML
  private MenuItem logout;

  @FXML
  private MenuItem exit;

  @FXML
  public void editWindow(ActionEvent event) throws IOException {
    Stage stage = (Stage) book.getScene().getWindow();
    stage.close();
    stage = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("EditProfile.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void logOut(ActionEvent event) throws IOException {
    Stage stage = (Stage) book.getScene().getWindow();
    stage.close();
    stage = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("fx/Login.fxml"));

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void quit(ActionEvent event) throws IOException {
    Stage stage = (Stage) book.getScene().getWindow();
    stage.close();
  }

  @FXML
  public void homePage(ActionEvent event) throws IOException {
    Stage stage = (Stage) book.getScene().getWindow();
    Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void custPage(ActionEvent event) throws IOException {
    Stage stage = (Stage) book.getScene().getWindow();
    Parent root = FXMLLoader.load(getClass().getResource("MemberList.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void issuePage(ActionEvent event) throws IOException {
    Stage stage = (Stage) book.getScene().getWindow();
    Parent root = FXMLLoader.load(getClass().getResource("IssueList.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    bookid.setCellValueFactory(new PropertyValueFactory<>("book_id"));
    title.setCellValueFactory(new PropertyValueFactory<>("title"));
    author.setCellValueFactory(new PropertyValueFactory<>("author"));
    category.setCellValueFactory(new PropertyValueFactory<>("category"));
    edition.setCellValueFactory(new PropertyValueFactory<>("edition"));
    isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
    pages.setCellValueFactory(new PropertyValueFactory<>("pages"));
    shelf.setCellValueFactory(new PropertyValueFactory<>("shelfNo"));
    copies.setCellValueFactory(new PropertyValueFactory<>("noOfBookCopy"));
    issuesCol.setCellValueFactory(new PropertyValueFactory<>("noOfBookIssued"));
    availablity.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));

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
            ) {
              return true;
            } else if (
              book
                .getTitle()
                .toLowerCase()
                .contains(searchBar.getText().toLowerCase())
            ) {
              return true;
            } else if (
              book
                .getAuthor()
                .toLowerCase()
                .contains(searchBar.getText().toLowerCase())
            ) {
              return true;
            } else if (
              book
                .getEdition()
                .toLowerCase()
                .contains(searchBar.getText().toLowerCase())
            ) {
              return true;
            } else if (
              Long.toString(book.getIsbn()).contains(searchBar.getText())
            ) {
              return true;
            } else if (
              Integer.toString(book.getPages()).contains(searchBar.getText())
            ) {
              return true;
            } else if (
              book
                .getShelfNo()
                .toLowerCase()
                .contains(searchBar.getText().toLowerCase())
            ) {
              return true;
            }

            return false;
          });
        });

      SortedList<Book> sortedSearch = new SortedList<>(searchFilter);
      sortedSearch.comparatorProperty().bind(tableBook.comparatorProperty());

      tableBook.setItems(sortedSearch);
    } catch (FileNotFoundException ex) {
      Logger
        .getLogger(BookListController.class.getName())
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

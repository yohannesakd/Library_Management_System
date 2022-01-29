package librarygui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.*;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import library.*;

public class BookListController implements Initializable {

  @FXML
  private Button home;

  @FXML
  private Button book;

  @FXML
  private Button issue;

  @FXML
  private Button cust;

  @FXML
  private Button lib;

  @FXML
  private Button admin;

  @FXML
  private Button backBtn;

  @FXML
  private Button add;

  @FXML
  private VBox formContainer;

  @FXML
  private Button saveEdit;

  @FXML
  private Button saveAdd;

  @FXML
  private Button edit;

  @FXML
  private TextField editId;

  @FXML
  private Button remove;

  @FXML
  private TextField removeId;

  @FXML
  private TextField titleField;

  @FXML
  private TextField authorField;

  @FXML
  private TextField editionField;

  @FXML
  private TextField pagesField;

  @FXML
  private TextField ISBNField;

  @FXML
  private TextField shelfNoField;

  @FXML
  private TextField bookIdField;

  @FXML
  private Label response;

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
  private Label removeAlert;

  @FXML
  private Label editAlert;

  
  @FXML
  private void handleBookDelete(ActionEvent event) throws IOException{
      Book toBeDeleted = tableBook.getSelectionModel().getSelectedItem();
      try {
          FileAlter.deleteBook(toBeDeleted.getBook_id());
      } catch (FileNotFoundException ex) {
          removeAlert.setVisible(true);
          removeAlert.setText("FILE NOT DELETED.");
      }
      Stage stage = (Stage)removeAlert.getScene().getWindow();
      Parent root = FXMLLoader.load(getClass().getResource("fx/Admin/BookLIst.fxml"));
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
  
  }
  
  
  @FXML
  public void goBack(ActionEvent event) throws IOException {
    Stage stage = (Stage) book.getScene().getWindow();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/admin/BookList.fxml")
    );
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }


  @FXML
  public void homePage(ActionEvent event) throws IOException {
    Stage stage = (Stage) book.getScene().getWindow();
    Parent root = FXMLLoader.load(getClass().getResource("fx/admin/Home.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void custPage(ActionEvent event) throws IOException {
    Stage stage = (Stage) book.getScene().getWindow();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/admin/MemberList.fxml")
    );
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void libPage(ActionEvent event) throws IOException {
    Stage stage = (Stage) book.getScene().getWindow();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/admin/LibList.fxml")
    );
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void adminPage(ActionEvent event) throws IOException {
    Stage stage = (Stage) book.getScene().getWindow();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/admin/AdminList.fxml")
    );
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void showForm(ActionEvent event) throws IOException {
    editId.setVisible(false);
    removeId.setVisible(false);
    formContainer.setVisible(true);
    bookIdField.requestFocus();
    saveEdit.setVisible(false);
  }

  @FXML
  public void showEditId(ActionEvent event) {
    removeId.setVisible(false);
    formContainer.setVisible(false);
    editId.setVisible(true);
    editId.requestFocus();
  }

  @FXML
  public void showRemoveId(ActionEvent event) {
    editId.setVisible(false);
    formContainer.setVisible(false);
    removeId.setVisible(true);
    removeId.requestFocus();
  }

  @FXML
  private void completeAddBook(ActionEvent event) throws IOException {
    Book inpbook = new Book();
    inpbook.setBook_id(Integer.parseInt(bookIdField.getText()));
    inpbook.setTitle(titleField.getText());
    inpbook.setAuthor(authorField.getText());
    inpbook.setEdition(editionField.getText());
    inpbook.setPages(Integer.parseInt(pagesField.getText()));
    inpbook.setIsbn(Long.parseLong(ISBNField.getText()));
    inpbook.setShelfNo(shelfNoField.getText());
    inpbook.displayBook();
    String str = Admin.addBook(inpbook);
    System.out.println(str);
    response.setText(str);

    Stage stage = (Stage) add.getScene().getWindow();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/admin/BookList.fxml")
    );
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void showEditForm(ActionEvent event) throws IOException {
    editId.setVisible(true);
    removeId.setVisible(false);
    formContainer.setVisible(true);
    saveAdd.setVisible(false);
  }

  @FXML
  public void removeBook(ActionEvent event) throws IOException {
    try {
      removeAlert.setText(
        FileAlter.deleteBook(Integer.parseInt(removeId.getText()))
      );
      System.out.println("Something");
    } catch (NumberFormatException n) {
      removeAlert.setText("Invalid ID");
    }
    Stage stage = (Stage) removeAlert.getScene().getWindow();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/admin/BookList.fxml")
    );

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
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

package librarygui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import CustomException.NullFieldException;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
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
  private Button lib;

  @FXML
  private Button admin;

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
  private Button remove;

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
  private TextField noOfBookCopyField;

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
  private TableColumn<Book, String> availablity;

  @FXML
  private TextField searchBar;

  @FXML
  private Label removeAlert;

  @FXML
  private Label editAlert;

  @FXML
  private Label checkInput;

  @FXML
  private TextField categoryField;

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
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/admin/EditProfile.fxml")
    );
  }



  @FXML
  void logOut(ActionEvent event) throws IOException {
    Stage stage = (Stage) book.getScene().getWindow();
    stage.close();
    stage = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("fx/Login.fxml"));

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  void quit(ActionEvent event) throws IOException {
    Stage stage = (Stage) book.getScene().getWindow();
    stage.close();
  }

  @FXML
  private void handleBookDelete(ActionEvent event) throws IOException {
    Book toBeDeleted = tableBook.getSelectionModel().getSelectedItem();

    if (toBeDeleted == null) {
      removeAlert.setText("PLEASE SELECT A ROW TO DELETE.");
      return;
    }

    removeAlert.setText(FileAlter.deleteBook(toBeDeleted.getBook_id()));

    Stage stage = (Stage) removeAlert.getScene().getWindow();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/Admin/BookList.fxml")
    );
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void editBook(Book book) {

    titleField.setText(book.getTitle());
    authorField.setText(book.getAuthor());
    editionField.setText(book.getEdition());
    categoryField.setText(book.getCategory());
    pagesField.setText(Integer.toString(book.getPages()));
    ISBNField.setText(Long.toString(book.getIsbn()));
    shelfNoField.setText(book.getShelfNo());
    noOfBookCopyField.setText(Integer.toString(book.getNoOfBookCopy()));
  }

  @FXML
  private void handleBookEdit(ActionEvent event) throws IOException {
    Book toBeEdited = tableBook.getSelectionModel().getSelectedItem();
    CurrentBookId = toBeEdited.getBook_id();

    if (toBeEdited == null) {
      editAlert.setText("PLEASE SELECT THE ROW TO EDIT");
      return;
    }
    editBook(toBeEdited);
    formContainer.setVisible(true);
    titleField.requestFocus();
    saveAdd.setVisible(false);
    saveEdit.setVisible(true);
  }

  @FXML
  private void bookEdit(ActionEvent event) throws IOException {
    Book inpbook = new Book();
try{
  if(titleField.getText().equals("") || authorField.getText().equals("") || categoryField.getText().equals("") || editionField.getText().equals("") || pagesField.getText().equals("") || ISBNField.getText().equals("") || shelfNoField.getText().equals("") || noOfBookCopyField.getText().equals("")){
    throw new NullFieldException();
  }
 
    inpbook.setBook_id(CurrentBookId);
    inpbook.setTitle(titleField.getText());
    inpbook.setAuthor(authorField.getText());
    inpbook.setCategory(categoryField.getText());
    inpbook.setEdition(editionField.getText());
    inpbook.setPages(Integer.parseInt(pagesField.getText()));
    inpbook.setIsbn(Long.parseLong(ISBNField.getText()));
    inpbook.setShelfNo(shelfNoField.getText());
    inpbook.setNoOfBookCopy(Integer.parseInt(noOfBookCopyField.getText()));
    FileAlter.editBook(inpbook);

    Stage stage = (Stage) add.getScene().getWindow();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/admin/BookList.fxml")
    );
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
catch(NullFieldException nE){
System.out.println(nE.getMessage());
  checkInput.setText(nE.getMessage());
  checkInput.setTextFill(Color.RED);
}
catch (NumberFormatException e) {
  checkInput.setText("INVALID INPUT! REVIEW YOUR INPUTS!");
  checkInput.setTextFill(Color.RED);
}
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
  public void issuePage(ActionEvent event) throws IOException {
    Stage stage = (Stage) book.getScene().getWindow();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/admin/IssueList.fxml")
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
    formContainer.setVisible(true);
    titleField.requestFocus();
    saveEdit.setVisible(false);
  }



  
  @FXML
  private void completeAddBook(ActionEvent event) throws IOException {
    Book inpbook = new Book();

    ArrayList<Book> booklist = new ArrayList<>();
    try {
      booklist = FileAlter.retrieveAllbookFile();
    } catch (java.io.FileNotFoundException e) {}

    if (!booklist.isEmpty()) inpbook.setBook_id(
      booklist.get(booklist.size() - 1).getBook_id() + 1
    );
    try {
      if(titleField.getText().equals("") || authorField.getText().equals("") || categoryField.getText().equals("") || editionField.getText().equals("") || pagesField.getText().equals("") || ISBNField.getText().equals("") || shelfNoField.getText().equals("") || noOfBookCopyField.getText().equals("")){
        throw new NullFieldException();
      }
      inpbook.setTitle(titleField.getText());
      inpbook.setAuthor(authorField.getText());
      inpbook.setCategory(categoryField.getText());
      inpbook.setEdition(editionField.getText());
      inpbook.setPages(Integer.parseInt(pagesField.getText()));
      inpbook.setIsbn(Long.parseLong(ISBNField.getText()));
      inpbook.setShelfNo(shelfNoField.getText());
      inpbook.setNoOfBookCopy(Integer.parseInt(noOfBookCopyField.getText()));

      String str = Admin.addBook(inpbook);
      System.out.println(str);

      Stage stage = (Stage) add.getScene().getWindow();
      Parent root = FXMLLoader.load(
        getClass().getResource("fx/admin/BookList.fxml")
      );
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } catch (NumberFormatException e) {
      checkInput.setText("INVALID INPUT! REVIEW YOUR INPUTS!");
  checkInput.setTextFill(Color.RED);
    }
    catch(NullFieldException nE){
      System.out.println(nE.getMessage());
        checkInput.setText(nE.getMessage());
        checkInput.setTextFill(Color.RED);
      }
  }

  public void showEditForm(ActionEvent event) throws IOException {
    formContainer.setVisible(true);
    saveAdd.setVisible(false);
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

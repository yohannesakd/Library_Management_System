package librarygui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import library.*;
import static library.FileAlter.retrieveBookTitle;
import static library.FileAlter.retrieveMemberName;

public class IssueListController implements Initializable {

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
  private Button newMemberBtn;

  @FXML
  private TableView<Issue> tableIssue;

  @FXML
  private TableColumn<Issue, String> issue_id;

  @FXML
  private TableColumn<Issue, String> name;

  @FXML
  private TableColumn<Issue, String> title;

  @FXML
  private TableColumn<Issue, String> member_id;

  @FXML
  private TableColumn<Issue, String> book_id;

  @FXML
  private TableColumn<Issue, String> issue_date;

  @FXML
  private TableColumn<Issue, String> due_date;

  @FXML
  private TextField searchBar;

  @FXML
  private Button addIssue;

  @FXML
  private Button removeIssue;

  @FXML
  private VBox issueInfo;

  @FXML
  private TextField memberId;

  @FXML
  private TextField bookId;

  @FXML
  private Label issueDate;

  @FXML
  private DatePicker selectDate;

  @FXML
  private Label daysIssuedFor;

  @FXML
  private Button confirmBtn;

  @FXML 
  public void issueBook(ActionEvent event) throws IOException {
    Issue inpIssue = new Issue();
    inpIssue.setMember_id(Integer.parseInt(memberId.getText()));
    inpIssue.setBook_id(Integer.parseInt(bookId.getText()));
    inpIssue.setBookTitle(retrieveBookTitle(Integer.parseInt(bookId.getText())));
    inpIssue.setName(retrieveMemberName(Integer.parseInt(memberId.getText())));
    inpIssue.setIssueDate(inpIssue.getDateNow().toString());
    inpIssue.setDueDate(getDueDate(event).toString());

    String str = Admin.addIssue(inpIssue);
    System.out.println(str);

    Stage stage = (Stage) book.getScene().getWindow();
    Parent root = FXMLLoader.load(getClass().getResource("fx/Admin/IssueList.fxml"));

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    


  }

@FXML
public void showIssueForm(ActionEvent event) throws IOException{
  issueInfo.setVisible(true);
  LocalDate showDate = LocalDate.now();
  issueDate.setText(showDate.toString());
  
}

@FXML
public LocalDate getDueDate(ActionEvent event){
  LocalDate dueDate = selectDate.getValue();
  daysIssuedFor.setText(String.valueOf(LocalDate.now().until(dueDate).getDays()) + " Days");
  return dueDate;
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
  public void bookPage(ActionEvent event) throws IOException {
    Stage stage = (Stage) book.getScene().getWindow();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/admin/BookList.fxml")
    );

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
  private void addMemberWindow(ActionEvent event) throws IOException {
    Stage stage = (Stage) book.getScene().getWindow();
    stage.close();
    stage = new Stage();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/admin/addMember.fxml")
    );
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void initialize(URL url, ResourceBundle rb) {
    issue_id.setCellValueFactory(new PropertyValueFactory<>("issue_id"));
    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    member_id.setCellValueFactory(new PropertyValueFactory<>("member_id"));
    book_id.setCellValueFactory(new PropertyValueFactory<>("book_id"));
    issue_date.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
    due_date.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
    title.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));

    try {
      tableIssue.setItems(giveIssues());
    } catch (FileNotFoundException ex) {
      System.out.println("FIle Not Found");
    }

    FilteredList<Issue> searchFilter;
    try {
      searchFilter = new FilteredList<>(giveIssues());
      searchBar
        .textProperty()
        .addListener((observable, oldvalue, newvalue) -> {
          searchFilter.setPredicate(issue -> {
            if (newvalue == null || newvalue.isEmpty()) {
              return true;
            }
            if (
              Integer
                .toString(issue.getIssue_id())
                .contains(searchBar.getText())
            ) return true; else if (
              Integer
                .toString(issue.getMember_id())
                .contains(searchBar.getText())
            ) return true; else if (
              Integer.toString(issue.getBook_id()).contains(searchBar.getText())
            ) return true; else if (
              issue
                .getIssueDate()
                .toLowerCase()
                .contains(searchBar.getText().toLowerCase())
            ) return true; else if (
              issue
                .getDueDate()
                .toLowerCase()
                .contains(searchBar.getText().toLowerCase())
            ) return true; else if (
              issue
                .getBookTitle()
                .toLowerCase()
                .contains(searchBar.getText().toLowerCase())
            ) return true; else if (
              issue
                .getName()
                .toLowerCase()
                .contains(searchBar.getText().toLowerCase())
            ) return true;

            return false;
          });
        });

      SortedList<Issue> sortedSearch = new SortedList<>(searchFilter);
      sortedSearch.comparatorProperty().bind(tableIssue.comparatorProperty());

      tableIssue.setItems(sortedSearch);
    } catch (FileNotFoundException ex) {
      Logger
        .getLogger(BookListController.class.getName())
        .log(Level.SEVERE, null, ex);
    }
  }

  public ObservableList<Issue> giveIssues() throws FileNotFoundException {
    ObservableList<Issue> issues = FXCollections.observableArrayList(
      library.FileAlter.retrieveAllIssueFile()
    );
    return issues;
  }
}

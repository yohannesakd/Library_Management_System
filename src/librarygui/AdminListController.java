package librarygui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import library.Admin;

public class AdminListController implements Initializable {

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
  private TableView<Admin> tableAdmin;

  @FXML
  private TableColumn<Admin, String> id;

  @FXML
  private TableColumn<Admin, String> fullName;

  @FXML
  private TableColumn<Admin, String> username;

  @FXML
  private TableColumn<Admin, String> phoneNo;

  @FXML
  private TableColumn<Admin, String> email;

  @FXML
  private TableColumn<Admin, String> address;

  @FXML
  public void goBack(ActionEvent event) throws IOException {
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
  public void homePage(ActionEvent event) throws IOException {
    Stage stage = (Stage) book.getScene().getWindow();
    Parent root = FXMLLoader.load(getClass().getResource("fx/admin/Home.fxml"));
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

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    username.setCellValueFactory(new PropertyValueFactory<>("username"));
    phoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
    email.setCellValueFactory(new PropertyValueFactory<>("email"));
    address.setCellValueFactory(new PropertyValueFactory<>("address"));

    try {
      tableAdmin.setItems(giveAdmins());
    } catch (FileNotFoundException ex) {
      System.out.println("FileNotFound");
    }

    SortedList<Admin> sortedSearch;
    try {
      sortedSearch = new SortedList<>(giveAdmins());
      sortedSearch.comparatorProperty().bind(tableAdmin.comparatorProperty());

      tableAdmin.setItems(sortedSearch);
    } catch (FileNotFoundException e) {
      System.out.println("File not Found.");
    }
  }

  public ObservableList<Admin> giveAdmins() throws FileNotFoundException {
    ObservableList<Admin> admins = FXCollections.observableArrayList(
      library.FileAlter.retrieveAllAdminFile()
    );
    return admins;
  }
}

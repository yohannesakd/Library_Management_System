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

public class MemberListController implements Initializable {

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
  private TableView<Member> tableMember;

  @FXML
  private TableColumn<Member, String> member_id;

  @FXML
  private TableColumn<Member, String> fullName;

  @FXML
  private TableColumn<Member, String> booksIssued;

  @FXML
  private TableColumn<Member, String> phoneNo;

  @FXML
  private TableColumn<Member, String> email;

  @FXML
  private TableColumn<Member, String> address;

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
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/admin/EditProfile.fxml")
    );

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
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

  @FXML
  private void handleMemberDelete(ActionEvent event) throws IOException {
    Member toBeDeleted = tableMember.getSelectionModel().getSelectedItem();

    if (toBeDeleted == null) {
      // removeAlert.setText("PLEASE SELECT A ROW TO DELETE.");
      return;
    }

    FileAlter.deleteMember(toBeDeleted.getMember_id());

    Stage stage = (Stage) book.getScene().getWindow();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/Admin/MemberList.fxml")
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

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    member_id.setCellValueFactory(new PropertyValueFactory<>("member_id"));
    fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    booksIssued.setCellValueFactory(
      new PropertyValueFactory<>("noOfBookIssued")
    );
    phoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
    email.setCellValueFactory(new PropertyValueFactory<>("email"));
    address.setCellValueFactory(new PropertyValueFactory<>("address"));

    try {
      tableMember.setItems(giveMembers());
    } catch (FileNotFoundException ex) {
      System.out.println("FIle Not Found");
    }

    FilteredList<Member> searchFilter;
    try {
      searchFilter = new FilteredList<>(giveMembers(), b -> true);
      //set the filter whenever the searchBar changes value
      searchBar
        .textProperty()
        .addListener((observable, oldvalue, newvalue) -> {
          searchFilter.setPredicate(member -> {
            //if the search bar is empty then return the orginal table
            if (newvalue == null || newvalue.isEmpty()) {
              return true;
            }
            if (
              Integer
                .toString(member.getPhoneNo())
                .contains(searchBar.getText())
            ) return true; else if (
              Integer
                .toString(member.getMember_id())
                .contains(searchBar.getText())
            ) return true; else if (
              Integer
                .toString(member.getNoOfBookIssued())
                .contains(searchBar.getText())
            ) return true; else if (
              member
                .getFullName()
                .toLowerCase()
                .contains(searchBar.getText().toLowerCase())
            ) return true; else if (
              member
                .getEmail()
                .toLowerCase()
                .contains(searchBar.getText().toLowerCase())
            ) return true; else if (
              member
                .getAddress()
                .toLowerCase()
                .contains(searchBar.getText().toLowerCase())
            ) return true;
            return false;
          });
        });

      SortedList<Member> sortedSearch = new SortedList<>(searchFilter);
      sortedSearch.comparatorProperty().bind(tableMember.comparatorProperty());

      tableMember.setItems(sortedSearch);
    } catch (FileNotFoundException ex) {
      Logger
        .getLogger(LibListController.class.getName())
        .log(Level.SEVERE, null, ex);
    }
  }

  public ObservableList<Member> giveMembers() throws FileNotFoundException {
    ObservableList<Member> members = FXCollections.observableArrayList(
      library.FileAlter.retrieveAllMemberFile()
    );
    return members;
  }
}

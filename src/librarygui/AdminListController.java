package librarygui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import library.Admin;
import library.FileAlter;
import library.Librarian;

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
  private Button add;

  @FXML
  private Button confirmAdd;

  @FXML
  private TextField adminID;

  @FXML
  private HBox addInput;

  @FXML
  private Button demote;

  @FXML
  private Button remove;

  @FXML
  private Label promoteAlert;

  @FXML
  private Label demoteAlert;

  @FXML
  private Label removeAlert;

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
  private MenuItem editProfile;

  @FXML
  private MenuItem logout;

  @FXML
  private MenuItem exit;

  @FXML
  private MenuItem userManual;


  @FXML
  public void userManual(ActionEvent event) throws IOException {
    Stage stage = (Stage) book.getScene().getWindow();
    
    stage = new Stage();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/UserManual.fxml")
    );

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

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
  public void showAdd(ActionEvent event) {
    addInput.setVisible(true);
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
  private void handleRemove(ActionEvent event) throws IOException {
    Admin toBeDeleted = tableAdmin.getSelectionModel().getSelectedItem();

    if (toBeDeleted == null) {
      removeAlert.setText("PLEASE SELECT A ROW TO DELETE.");
      return;
    }
    String status = FileAlter.deleteAdmin(toBeDeleted.getId());
    if (status == "cant") {
      removeAlert.setText("Can Not Remove Admin!");
    } else {
      removeAlert.setText(status);
    }

    Stage stage = (Stage) removeAlert.getScene().getWindow();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/Admin/AdminList.fxml")
    );
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void handleDemote(ActionEvent event) throws IOException {
    Admin toBeDemoted = tableAdmin.getSelectionModel().getSelectedItem();

    if (toBeDemoted == null) {
      demoteAlert.setText("Please Select a Field");
      return;
    }
    int tbd = toBeDemoted.getId();

    Librarian newLib = new Librarian();

    ArrayList<Librarian> emplist = new ArrayList<>();
    try {
      emplist = FileAlter.retrieveAllemployeeFile();
    } catch (java.io.FileNotFoundException e) {}

    if (!emplist.isEmpty()) newLib.setId(
      emplist.get(emplist.size() - 1).getId() + 1
    );

    newLib.setAddress(toBeDemoted.getAddress());
    newLib.setEmail(toBeDemoted.getEmail());
    newLib.setFullName(toBeDemoted.getFullName());
    newLib.setId(toBeDemoted.getId());
    newLib.setPassword(toBeDemoted.getPassword());
    newLib.setPhoneNo(toBeDemoted.getPhoneNo());
    newLib.setUsername(toBeDemoted.getUsername());
    Admin.addLibrarian(newLib);
    String status = FileAlter.deleteAdmin(tbd);
    if (status == "cant") {
      demoteAlert.setText("Can not Remove Admin!");
    } else {
      demoteAlert.setText(status);
    }
    Stage stage = (Stage) book.getScene().getWindow();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/admin/AdminList.fxml")
    );

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void handleAdminAdd(ActionEvent event) throws IOException {
    Librarian lib = FileAlter.retrieveSingleEmployee(
      Integer.parseInt(adminID.getText())
    );
    if (lib == null) {
      promoteAlert.setText("Please Select a Valid ID");
      return;
    }

    int tbd = lib.getId();

    Admin newAdmin = new Admin();

    ArrayList<Admin> admlist = new ArrayList<>();
    try {
      admlist = FileAlter.retrieveAllAdminFile();
    } catch (java.io.FileNotFoundException e) {}

    if (!admlist.isEmpty()) newAdmin.setId(
      admlist.get(admlist.size() - 1).getId() + 1
    );

    newAdmin.setAddress(lib.getAddress());
    newAdmin.setEmail(lib.getEmail());
    newAdmin.setFullName(lib.getFullName());
    newAdmin.setPassword(lib.getPassword());
    newAdmin.setPhoneNo(lib.getPhoneNo());
    newAdmin.setUsername(lib.getUsername());
    Admin.addAdmin(newAdmin);
    FileAlter.deleteLibrarian(tbd);

    Stage stage = (Stage) book.getScene().getWindow();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/admin/AdminList.fxml")
    );

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

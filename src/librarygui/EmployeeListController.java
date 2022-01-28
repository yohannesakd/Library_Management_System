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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import library.Librarian;

public class EmployeeListController implements Initializable {

  @FXML
  private TableView<Librarian> tableEmployee;

  @FXML
  private TableColumn<Librarian, String> id;

  @FXML
  private TableColumn<Librarian, String> fullName;

  @FXML
  private TableColumn<Librarian, String> username;

  @FXML
  private TableColumn<Librarian, String> phoneNo;

  @FXML
  private TableColumn<Librarian, String> email;

  @FXML
  private TableColumn<Librarian, String> address;

  @FXML
  private TextField searchBar;

  @FXML
  private Button addLib;

  @FXML
  private Button backBtn;

  @FXML
  public static Label head;

  @FXML
  private void addLibrarian(ActionEvent event) throws IOException {
    Stage newStage = (Stage) addLib.getScene().getWindow();
    newStage.close();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/addLibrarian.fxml")
    );
    Scene scene = new Scene(root);
    newStage.setScene(scene);
    newStage.show();
  }

  @FXML
  private void goBack(ActionEvent event) throws IOException {
    Stage stage = (Stage) backBtn.getScene().getWindow();
    stage.close();
    Parent root = FXMLLoader.load(getClass().getResource("fx/FirstPage.fxml"));
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
      tableEmployee.setItems(giveEmployees());
    } catch (FileNotFoundException ex) {
      System.out.println("FileNotFound");
    }

    FilteredList<Librarian> searchFilter;
    try {
      searchFilter = new FilteredList<>(giveEmployees(), b -> true);
      //set the filter whenever the searchBar changes value
      searchBar
        .textProperty()
        .addListener((observable, oldvalue, newvalue) -> {
          searchFilter.setPredicate(employee -> {
            //if the search bar is empty then return the orginal table
            if (newvalue == null || newvalue.isEmpty()) {
              return true;
            }
            if (
              Integer.toString(employee.getId()).contains(searchBar.getText())
            ) return true; else if (
              employee
                .getFullName()
                .toLowerCase()
                .contains(searchBar.getText().toLowerCase())
            ) return true; else if (
              employee
                .getUsername()
                .toLowerCase()
                .contains(searchBar.getText().toLowerCase())
            ) return true; else if (
              Integer
                .toString(employee.getPhoneNo())
                .contains(searchBar.getText())
            ) return true; else if (
              employee.getEmail().toLowerCase().contains(searchBar.getText())
            ) return true; else if (
              employee.getAddress().toLowerCase().contains(searchBar.getText())
            ) return true;
            return false;
          });
        });

      SortedList<Librarian> sortedSearch = new SortedList<>(searchFilter);
      sortedSearch
        .comparatorProperty()
        .bind(tableEmployee.comparatorProperty());

      tableEmployee.setItems(sortedSearch);
    } catch (FileNotFoundException ex) {
      Logger
        .getLogger(EmployeeListController.class.getName())
        .log(Level.SEVERE, null, ex);
    }
  }

  public ObservableList<Librarian> giveEmployees()
    throws FileNotFoundException {
    ObservableList<Librarian> librarians = FXCollections.observableArrayList(
      library.FileAlter.retrieveAllemployeeFile()
    );
    return librarians;
  }
}

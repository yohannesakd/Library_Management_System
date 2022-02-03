package librarygui;

import static librarygui.LoginController.currentUser;
import static librarygui.LoginController.userType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import library.*;

public class EditProfileController implements Initializable {

  @FXML
  private TextField fullNameField;

  @FXML
  private PasswordField passwordField1;

  @FXML
  private PasswordField passwordField2;

  @FXML
  private TextField phoneField;

  @FXML
  private TextField emailField;

  @FXML
  private TextField addressField;

  @FXML
  private TextField usernameField;

  @FXML
  private Button edit;

  @FXML
  private Label response;

  @FXML
  private void completeEdit(ActionEvent event) throws IOException {
    if (userType == "Admin") {
      Admin edited = new Admin();

      edited.setAddress(addressField.getText());
      edited.setEmail(emailField.getText());
      edited.setPassword(passwordField1.getText());
      edited.setFullName(fullNameField.getText());
      edited.setId(currentUser);
      edited.setUsername(usernameField.getText());
      edited.setPhoneNo(Integer.parseInt(phoneField.getText()));

      FileAlter.deleteAdmin(currentUser);
      Admin.addAdmin(edited);

      Stage stage = (Stage) edit.getScene().getWindow();
      Parent root = FXMLLoader.load(
        getClass().getResource("fx/admin/Home.fxml")
      );

      Screen screen = Screen.getPrimary();
      Rectangle2D bounds = screen.getVisualBounds();

      stage.setX(bounds.getMinX());
      stage.setY(bounds.getMinY());
      stage.setWidth(bounds.getWidth());
      stage.setHeight(bounds.getHeight());

      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } else {
      Librarian edited = new Librarian();

      edited.setAddress(addressField.getText());
      edited.setEmail(emailField.getText());
      edited.setPassword(passwordField1.getText());
      edited.setFullName(fullNameField.getText());
      edited.setId(currentUser);
      edited.setUsername(usernameField.getText());
      edited.setPhoneNo(Integer.parseInt(phoneField.getText()));

      FileAlter.deleteAdmin(currentUser);
      Admin.addLibrarian(edited);

      Stage stage = (Stage) edit.getScene().getWindow();
      Parent root = FXMLLoader.load(
        getClass().getResource("fx/admin/Home.fxml")
      );
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
  }

  public void initialize(URL url, ResourceBundle rb) {
    try {
      if (userType == "Admin") {
        Admin toBeEdited = FileAlter.retrieveSingleAdmin(currentUser);
        if (toBeEdited == null) {
          return;
        }
        fullNameField.setText(toBeEdited.getFullName());
        passwordField1.setText(toBeEdited.getPassword());
        passwordField2.setText(toBeEdited.getPassword());
        phoneField.setText(Integer.toString(toBeEdited.getPhoneNo()));
        emailField.setText(toBeEdited.getEmail());
        addressField.setText(toBeEdited.getAddress());
        usernameField.setText(toBeEdited.getUsername());
      } else {
        Librarian toBeEdited = FileAlter.retrieveSingleEmployee(currentUser);
        if (toBeEdited == null) {
          return;
        }
        fullNameField.setText(toBeEdited.getFullName());
        passwordField1.setText(toBeEdited.getPassword());
        passwordField2.setText(toBeEdited.getPassword());
        phoneField.setText(Integer.toString(toBeEdited.getPhoneNo()));
        emailField.setText(toBeEdited.getEmail());
        addressField.setText(toBeEdited.getAddress());
      }
    } catch (FileNotFoundException e) {
      System.out.println("ERROR,FILENOTFOUND");
    }
  }
}

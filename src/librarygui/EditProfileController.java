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
  private Button edit;

  @FXML
  private Label response;

  @FXML
  private void handleEdit(ActionEvent event) throws FileNotFoundException {
    if (userType == "Admin") {
      Admin toBeEdited = FileAlter.retrieveSingleAdmin(currentUser);
      //   if toBeEdited
    }
  }

  public void initialize(URL url, ResourceBundle rb) {}
}

package librarygui;

import static librarygui.LoginController.currentUser;
import static librarygui.LoginController.userType;

import CustomException.ConfirmPasswordException;
import CustomException.EmailException;
import CustomException.NullFieldException;
import CustomException.PhoneException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
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
  private Label errorMsg;

  @FXML
  private void completeEdit(ActionEvent event) throws IOException {
    if (userType == "Admin") {
      Admin edited = new Admin();
      try {
        if (
          fullNameField.getText().equals("") ||
          usernameField.getText().equals("") ||
          passwordField1.getText().equals("") ||
          passwordField2.getText().equals("") ||
          emailField.getText().equals("") ||
          phoneField.getText().equals("") ||
          addressField.getText().equals("")
        ) {
          throw new NullFieldException();
        }
        if (phoneField.getText().length() != 9) {
          throw new PhoneException();
        }
        if (!emailField.getText().contains("@")) {
          throw new EmailException();
        }

        if (!passwordField1.getText().equals(passwordField2.getText())) {
          throw new ConfirmPasswordException();
        }
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

        Parent alert = FXMLLoader.load(
          getClass().getResource("fx/alertBox.fxml")
        );
        Stage stage1 = new Stage();
        Scene alertScene = new Scene(alert);
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        stage1.getIcons().add(icon);
        stage1.setTitle("Shewe Library Management System");
        stage1.setScene(alertScene);
        stage1.show();
      } catch (EmailException eE) {
        System.out.println(eE.getMessage());
        emailField.setStyle("-fx-text-inner-color: #b50909;");
        errorMsg.setText(eE.getMessage());
        errorMsg.setTextFill(Color.RED);
      } catch (PhoneException pE) {
        System.out.println(pE.getMessage());
        phoneField.setStyle("-fx-text-inner-color: #b50909;");
        errorMsg.setText(pE.getMessage());
        errorMsg.setTextFill(Color.RED);
      } catch (NullFieldException nE) {
        System.out.println(nE.getMessage());
        errorMsg.setText(nE.getMessage());
        errorMsg.setTextFill(Color.RED);
      } catch (ConfirmPasswordException cE) {
        System.out.println(cE.getMessage());
        errorMsg.setText(cE.getMessage());
        errorMsg.setTextFill(Color.RED);
      }
    } else {
      Librarian edited = new Librarian();
      try {
        if (
          fullNameField.getText().equals("") ||
          usernameField.getText().equals("") ||
          passwordField1.getText().equals("") ||
          passwordField2.getText().equals("") ||
          emailField.getText().equals("") ||
          phoneField.getText().equals("") ||
          addressField.getText().equals("")
        ) {
          throw new NullFieldException();
        }
        if (phoneField.getText().length() != 9) {
          throw new PhoneException();
        }
        if (!emailField.getText().contains("@")) {
          throw new EmailException();
        }

        if (!passwordField1.getText().equals(passwordField2.getText())) {
          throw new ConfirmPasswordException();
        }
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
      } catch (EmailException eE) {
        System.out.println(eE.getMessage());
        emailField.setStyle("-fx-text-inner-color: #b50909;");
        errorMsg.setText(eE.getMessage());
        errorMsg.setTextFill(Color.RED);
      } catch (PhoneException pE) {
        System.out.println(pE.getMessage());
        phoneField.setStyle("-fx-text-inner-color: #b50909;");
        errorMsg.setText(pE.getMessage());
        errorMsg.setTextFill(Color.RED);
      } catch (NullFieldException nE) {
        System.out.println(nE.getMessage());
        errorMsg.setText(nE.getMessage());
        errorMsg.setTextFill(Color.RED);
      } catch (ConfirmPasswordException cE) {
        System.out.println(cE.getMessage());
        errorMsg.setText(cE.getMessage());
        errorMsg.setTextFill(Color.RED);
      }
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

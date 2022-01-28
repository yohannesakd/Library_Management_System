package librarygui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import library.Admin;
import library.Librarian;

public class addLibrarianGUIController implements Initializable {

  @FXML
  private TextField libIdField;

  @FXML
  private TextField usernameField;

  @FXML
  private PasswordField password1Field;

  @FXML
  private PasswordField password2Field;

  @FXML
  private TextField fullNameField;

  @FXML
  private TextField phoneField;

  @FXML
  private TextField emailField;

  @FXML
  private TextField addressField;

  @FXML
  private Button addLibBtn;

  @FXML
  private Label response;

  public static void refreshWindow() {}

  @FXML
  private void register(ActionEvent event) throws IOException {
    Librarian inpLib = new Librarian();

    inpLib.setFullName(fullNameField.getText());
    inpLib.setId(Integer.parseInt(libIdField.getText()));
    inpLib.setUsername(usernameField.getText());
    inpLib.setPassword(password1Field.getText());
    inpLib.setPhoneNo(Integer.parseInt(phoneField.getText()));
    inpLib.setEmail(emailField.getText());
    inpLib.setAddress(addressField.getText());

    String str = Admin.addLibrarian(inpLib);
    System.out.println(str);
    response.setText(str);

    Stage stage = (Stage) response.getScene().getWindow();
    stage.close();
    stage = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("EmployeeList.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {}
}

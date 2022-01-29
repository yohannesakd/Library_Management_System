/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarygui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 *
 * @author JOHN
 */
public class LoginController implements Initializable {

  @FXML
  private Label logo;

  @FXML
  private Label alert;

  @FXML
  private TextField userName;

  @FXML
  private PasswordField passWord;

  @FXML
  private Button login;

  @FXML
  private ComboBox<String> loger;

  ObservableList<String> list = FXCollections.observableArrayList(
    "Admin",
    "Librarian"
  );

  @FXML
  private void handleButtonAction(ActionEvent event) throws IOException {
    if (loger.getValue().equals("Admin")) {
      if (
        userName.getText().equals("admin") && passWord.getText().equals("admin")
      ) {
        System.out.println("at least this executes");
        alert.setText("Login Successfull");
        Stage stage = (Stage) login.getScene().getWindow();
        stage.close();
        stage = new Stage();
        stage.setMinWidth(700);
        stage.setMinHeight(700);
        Parent root = FXMLLoader.load(
          getClass().getResource("fx/admin/Home.fxml")
        );
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
      } else alert.setText("Incorrect Username or Password.");
    } else {
      if (
        userName.getText().equals("librarian") &&
        passWord.getText().equals("pass")
      ) alert.setText("Login Successfull"); else alert.setText(
        "Incorrect Username or Password."
      );
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    loger.setItems(list);
  }
}

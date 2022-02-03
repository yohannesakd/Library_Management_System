/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarygui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import library.*;

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

  public static int currentUser;
  public static String userType;

  ObservableList<String> list = FXCollections.observableArrayList(
    "Admin",
    "Librarian"
  );

  @FXML
  private void handleButtonAction(ActionEvent event) throws IOException {
    if (loger.getValue().equals("Admin")) {
      ArrayList<Admin> adminList = FileAlter.retrieveAllAdminFile();
      boolean found = false;
      for (int i = 0; i < adminList.size(); i++) {
        System.out.println(adminList.get(i).getUsername());
        System.out.println(adminList.get(i).getPassword());
        if (
          userName.getText().equals(adminList.get(i).getUsername()) &&
          passWord.getText().equals(adminList.get(i).getPassword())
        ) {
          found = true;
          userType = loger.getValue();
          currentUser = adminList.get(i).getId();
          System.out.println("at least this executes");
          alert.setText("Login Successfull");
          Stage stage = (Stage) login.getScene().getWindow();
          stage.close();
          stage = new Stage();

          Screen screen = Screen.getPrimary();
          Rectangle2D bounds = screen.getVisualBounds();

          stage.setX(bounds.getMinX());
          stage.setY(bounds.getMinY());
          stage.setWidth(bounds.getWidth());
          stage.setHeight(bounds.getHeight());

          stage.setMinWidth(700);
          stage.setMinHeight(700);
          Parent root = FXMLLoader.load(
            getClass().getResource("fx/admin/Home.fxml")
          );
          Scene scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
        }
      }
      if (!found) {
        alert.setText("Incorrect Username or Password");
      }
    } else {
      if (loger.getValue().equals("Librarian")) {
        ArrayList<Librarian> liblist = FileAlter.retrieveAllemployeeFile();
        boolean found = false;
        for (int i = 0; i < liblist.size(); i++) {
          System.out.println(liblist.get(i).getUsername());
          System.out.println(liblist.get(i).getPassword());
          if (
            userName.getText().equals(liblist.get(i).getUsername()) &&
            passWord.getText().equals(liblist.get(i).getPassword())
          ) {
            found = true;
            userType = loger.getValue();
            currentUser = liblist.get(i).getId();
            System.out.println("at least this executes");
            alert.setText("Login Successfull");
            Stage stage = (Stage) login.getScene().getWindow();
            stage.close();
            stage = new Stage();

            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMinY());
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());

            stage.setMinWidth(700);
            stage.setMinHeight(700);
            Parent root = FXMLLoader.load(
              getClass().getResource("librarian/Home.fxml")
            );
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
          }
        }
        if (!found) {
          alert.setText("Incorrect Username or Password");
        }
      }
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    loger.setItems(list);
  }
}

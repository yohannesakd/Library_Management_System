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
import javax.swing.Action;
import library.Admin;
import library.Book;

public class editBookController implements Initializable {

  @FXML
  private Button backBtn;

  @FXML
  public void goBack(ActionEvent event) throws IOException {
    Stage stage = (Stage) backBtn.getScene().getWindow();
    stage.close();
    stage = new Stage();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/BookListAdmin.fxml")
    );
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {}
}

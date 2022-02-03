package librarygui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class registerAlertController implements Initializable {

  @FXML
  private Button ok;

  @FXML
  private void close(ActionEvent event) {
    Stage stage = (Stage) ok.getScene().getWindow();
    stage.close();
  }

  public void initialize(URL url, ResourceBundle rb) {}
}

package librarygui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class UserManualController implements Initializable {

    @FXML
    private WebView userManual;

    private WebEngine engine;

    @FXML
    private Button okBtn;

    @FXML
  private void okBtn(ActionEvent event) throws IOException {
    
    Stage stage = (Stage) userManual.getScene().getWindow();
      
      stage.close();
  }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        engine = userManual.getEngine();
        engine.load("https://tmotuma.000webhostapp.com/");
        
    }
    
    
}

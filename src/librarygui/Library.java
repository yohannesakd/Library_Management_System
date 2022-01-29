package librarygui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Library extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("fx/admin/Home.fxml"));

    Scene scene = new Scene(root);

    // stage.setMinWidth(460);
    // stage.setMinHeight(570);
    // stage.setMinWidth(470);
    // stage.setMinHeight(580);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}

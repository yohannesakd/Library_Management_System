package librarygui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Library extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("fx/admin/Home.fxml"));

    Scene scene = new Scene(root);

    Screen screen = Screen.getPrimary();
    Rectangle2D bounds = screen.getVisualBounds();

    stage.setX(bounds.getMinX());
    stage.setY(bounds.getMinY());
    stage.setWidth(bounds.getWidth());
    stage.setHeight(bounds.getHeight());

    stage.setMinWidth(460);
    stage.setMinHeight(570);
    //    stage.setMaxWidth(470);
    //    stage.setMaxHeight(580);
    Image icon = new Image(getClass().getResourceAsStream("icon.png"));
    stage.getIcons().add(icon);
    stage.setTitle("Shewe Library Management System");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}

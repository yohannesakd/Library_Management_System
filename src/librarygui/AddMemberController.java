package librarygui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.*;
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

public class AddMemberController implements Initializable {

  @FXML
  private TextField fullNameField;

  @FXML
  private TextField phoneField;

  @FXML
  private TextField emailField;

  @FXML
  private TextField addressField;

  @FXML
  private Button registerBtn;

  @FXML
  private Label response;

  @FXML
  private void handleRegister() throws IOException {
    Member mem = new Member();
    
    ArrayList<Member> memberlist =  FileAlter.retrieveAllMemberFile();
    if(!memberlist.isEmpty())
        mem.setMember_id(memberlist.get(memberlist.size()-1).getMember_id()+1);


    mem.setFullName(fullNameField.getText());
    mem.setAddress(addressField.getText());
    mem.setEmail(emailField.getText());
    mem.setPhoneNo(Integer.parseInt(phoneField.getText()));

    String str = Librarian.addMember(mem);
    System.out.println(str);
    response.setText(str);

    Stage stage = (Stage) registerBtn.getScene().getWindow();
    stage.close();

    Screen screen = Screen.getPrimary();
    Rectangle2D bounds = screen.getVisualBounds();

    stage.setX(bounds.getMinX());
    stage.setY(bounds.getMinY());
    stage.setWidth(bounds.getWidth());
    stage.setHeight(bounds.getHeight());
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/admin/MemberList.fxml")
    );
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void initialize(URL url, ResourceBundle rb) {}
}

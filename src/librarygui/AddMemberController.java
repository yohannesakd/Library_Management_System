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
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import library.*;
import CustomException.*;

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
  private Label errorMsg;

  @FXML
  private void handleRegister() throws IOException {
    Member mem = new Member();

    ArrayList<Member> memberlist = FileAlter.retrieveAllMemberFile();
    if (!memberlist.isEmpty()) mem.setMember_id(
      memberlist.get(memberlist.size() - 1).getMember_id() + 1
    );
try{
  if(fullNameField.getText().equals("") || addressField.getText().equals("") || emailField.getText().equals("") || phoneField.getText().equals("")){
    throw new NullFieldException();
  }
  if(phoneField.getText().length() != 9){
    throw new PhoneException();
  }
  if(!emailField.getText().contains("@")){
    throw new EmailException();
  }
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
  catch(EmailException eE){
      System.out.println(eE.getMessage());
      emailField.setStyle("-fx-text-inner-color: #b50909;");
      errorMsg.setText(eE.getMessage());
      errorMsg.setTextFill(Color.RED);
  }

  catch(PhoneException pE){
    System.out.println(pE.getMessage());
      phoneField.setStyle("-fx-text-inner-color: #b50909;");
      errorMsg.setText(pE.getMessage());
      errorMsg.setTextFill(Color.RED);
  }
  catch(NullFieldException nE){
    System.out.println(nE.getMessage());
      errorMsg.setText(nE.getMessage());
      errorMsg.setTextFill(Color.RED);
  }


  }

 

  public void initialize(URL url, ResourceBundle rb) {}
}

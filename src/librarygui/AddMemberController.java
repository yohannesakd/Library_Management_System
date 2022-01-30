package librarygui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import library.Librarian;
import library.Member;

public class AddMemberController {

  @FXML
  private TextField fullNameField;

  @FXML
  private TextField phoneField;

  @FXML
  private TextField emailField;

  @FXML
  private TextField addressField;

  @FXML
  private Button registerButton;

  @FXML
  private Label response;

  @FXML
  private void handleRegister() throws IOException {
    Member mem = new Member();
    mem.setMember_id(1);
    mem.setFullName(fullNameField.getText());
    mem.setAddress(addressField.getText());
    mem.setEmail(emailField.getText());
    mem.setPhoneNo(Integer.parseInt(phoneField.getText()));

    String str = Librarian.addMember
  }
}

package librarygui.Old;

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
import library.Admin;
import library.Book;

public class AddBookController implements Initializable {

  @FXML
  private Button backbtn;

  @FXML
  private Button addBookButton;

  @FXML
  private TextField titleField;

  @FXML
  private TextField authorField;

  @FXML
  private TextField editionField;

  @FXML
  private TextField pagesField;

  @FXML
  private TextField ISBNField;

  @FXML
  private TextField shelfNoField;

  @FXML
  private TextField bookIdField;

  @FXML
  private Label response;

  @FXML
  private void goBack() throws IOException {
    Stage stage = (Stage) backbtn.getScene().getWindow();
    stage.close();
    stage = new Stage();
    Parent root = FXMLLoader.load(
      getClass().getResource("fx/BookListGUIAdmin.fxml")
    );
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  private void completeAddBook(ActionEvent event) throws IOException {
    Book inpbook = new Book();
    inpbook.setBook_id(Integer.parseInt(bookIdField.getText()));
    inpbook.setTitle(titleField.getText());
    inpbook.setAuthor(authorField.getText());
    inpbook.setEdition(editionField.getText());
    inpbook.setPages(Integer.parseInt(pagesField.getText()));
    inpbook.setIsbn(Long.parseLong(ISBNField.getText()));
    inpbook.setShelfNo(shelfNoField.getText());
    inpbook.displayBook();
    String str = Admin.addBook(inpbook);
    System.out.println(str);
    response.setText(str);

    Stage stage = (Stage) addBookButton.getScene().getWindow();
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

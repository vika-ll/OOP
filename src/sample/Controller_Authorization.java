package sample;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller_Authorization {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane regestration;

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private Button authInButton;

    @FXML
    private Button regestrationButton;

    @FXML
    void initialize() {


regestrationButton.setOnAction(actionEvent -> {
regestrationButton.getScene().getWindow().hide();
    FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("/sample/Registration.fxml"));
    try {
        loader.load();
    } catch (IOException e) {
        e.printStackTrace();
    }
    Parent parent=loader.getRoot();
     Stage stage=new Stage();
     stage.setScene(new Scene(parent));
     stage.showAndWait();

});
authInButton.setOnAction(actionEvent -> {
String loginText=login.getText().trim();
String loginPassword=password.getText().trim();
if(!loginText.equals("")&&!loginPassword.equals("")){

    loginUser(login.getText(),password.getText());
}else{
    System.out.println("Error");
}

});

    }

    private void loginUser(String loginText, String loginPassword) {
DataBaseHandler dataBaseHandler =new DataBaseHandler();
User user=new User();
user.setFirstaname(loginText);
user.setLastaname(loginPassword);
    ResultSet resultSet= dataBaseHandler.getUser(user);
int counter=0;
while(true){
    try {
        if (!resultSet.next())
            break;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    counter++;
}
if(counter>=1){
    FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("/sample/sample.fxml"));
    try {
        loader.load();
    } catch (IOException e) {
        e.printStackTrace();
    }
    Parent parent=loader.getRoot();
    Stage stage=new Stage();
    stage.setScene(new Scene(parent));
    stage.showAndWait();

}
    }
}

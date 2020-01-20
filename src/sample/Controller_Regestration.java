package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller_Regestration {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane regestration;

    @FXML
    private TextField name;

    @FXML
    private TextField famil;

    @FXML
    private Button authInButton;

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private CheckBox man;

    @FXML
    private CheckBox wooman;

    @FXML
    private TextField country;

    @FXML
    private Button vxod;

    @FXML
    void initialize() {
vxod.setOnAction(actionEvent -> {
    vxod.getScene().getWindow().hide();
    FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("/sample/Avtorization.fxml"));
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
    signUpNewUsers();
});


    }
    public void signUpNewUsers(){
        DataBaseHandler dbHndler=new DataBaseHandler();
        String firstname=name.getText();
        String lastname=famil.getText();
        String logi=login.getText();
        String passwordText=password.getText();
        String countryText=country.getText();
        String gender="";
        User user=new User(firstname,lastname,logi,passwordText,countryText,gender);
        dbHndler.signUpUser(user);




    }
}

package boxgym.controller;

import boxgym.helper.AlertHelper;
import static boxgym.Constant.*;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane content;

    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private JFXButton loginButton;

    @FXML
    private Label registerLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonProperties();
    }

    private void checkLogin() throws IOException {
        String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(passwordTextField.getText());

        if (userTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            AlertHelper.customAlert(LOGIN_WARNING_ALERT_TITLE, LOGIN_WARNING_ALERT_HEADER, LOGIN_WARNING_ALERT_EMPTY_CONTENT, Alert.AlertType.WARNING);
        } else if (userTextField.getText().equals(DEFAULT_LOGIN_USERNAME) && sha256hex.equals(DEFAULT_LOGIN_PASSWORD)) {
            loginButton.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource(MAINSCREEN_VIEW));
            Scene scene = new Scene(root);
            Stage s1 = new Stage();
            s1.setResizable(false);
            s1.setTitle(MAINSCREEN_TITLE);
            s1.setScene(scene);
            s1.show();
        } else {
            AlertHelper.customAlert(LOGIN_WARNING_ALERT_TITLE, LOGIN_WARNING_ALERT_HEADER, LOGIN_WARNING_ALERT_WRONG_CONTENT, Alert.AlertType.WARNING);
            userTextField.setText("");
            passwordTextField.setText("");
        }
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        checkLogin();
    }

    @FXML
    void register(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/boxgym/view/Register.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
        boxgym.Main.stage.setTitle("Cadastro");
    }

    private void buttonProperties() {
        loginButton.setDefaultButton(true);
        loginButton.setCursor(Cursor.HAND);
        registerLabel.setCursor(Cursor.HAND);
    }

}

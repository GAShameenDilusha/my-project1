package lk.ijse.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fx.dto.SignupDto;
import lk.ijse.fx.model.SignupModel;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpFormController {

    public TextField txtPassword;
    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtUsername;



    @FXML
    void btnRegisterOnAction(ActionEvent event) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        SignupDto signupDto = new SignupDto(username, password);

        try {
            if (SignupModel.registerUser(signupDto)) {
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(new Scene(anchorPane));
                stage.setTitle("Dashboard ");
                stage.centerOnScreen();
            } else {
                // Registration failed, show an error message
                new Alert(Alert.AlertType.ERROR, "Failed to register user").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }



    }

    @FXML
    void btnSignInOnAction(ActionEvent event) throws IOException, IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login Form ");
        stage.centerOnScreen();
    }
}

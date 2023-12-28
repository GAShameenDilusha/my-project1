package lk.ijse.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fx.dto.LoginDto;
import lk.ijse.fx.dto.SignupDto;
import lk.ijse.fx.model.LoginModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class LoginFormController {
    public TextField txtPassword;
    public TextField txtUsername;
    @FXML
    private AnchorPane root;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String u_name = txtUsername.getText();
        String pass = txtPassword.getText();

        LoginDto loginDto = new LoginDto(u_name, pass);

        try {
            Optional<SignupDto> optionalUser = LoginModel.searchUser(loginDto);

            if (optionalUser.isPresent()) {
                // User found, proceed to the next window (dashboard or login form)
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
                Scene scene = new Scene(anchorPane);
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Login Form");
                stage.centerOnScreen();
            } else {
                // User not found, show an error message
                new Alert(Alert.AlertType.ERROR, "Invalid username or password").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/signup_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("SignUp Form ");
        stage.centerOnScreen();
    }
}




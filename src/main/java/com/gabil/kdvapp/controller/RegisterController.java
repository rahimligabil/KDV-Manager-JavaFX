package com.gabil.kdvapp.controller;

import com.gabil.kdvapp.dao.UserDAO;
import com.gabil.kdvapp.dto.UserDTO;
import com.gabil.kdvapp.util.ERole;
import com.gabil.kdvapp.util.FXMLPath;
import com.gabil.kdvapp.util.SceneHelper;
import com.gabil.kdvapp.util.SpecialColor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Optional;

public class RegisterController {
    private UserDAO userDAO;

    public RegisterController() {
        userDAO = new UserDAO();
    }

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField emailField;

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void specialOnEnterPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            register();
        }
    }

    @FXML
    public void register() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String email = emailField.getText().trim();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            showAlert("Hata", "Lütfen tüm alanları doldurun", Alert.AlertType.ERROR);
            return;
        }

        // Kullanıcı adı kontrolü
        if (userDAO.isUsernameExists(username)) {
            showAlert("Hata", "Bu kullanıcı adı zaten kayıtlı!", Alert.AlertType.WARNING);
            return;
        }

        // Email kontrolü
        if (userDAO.isEmailExists(email)) {
            showAlert("Hata", "Bu e-posta adresi zaten kayıtlı!", Alert.AlertType.WARNING);
            return;
        }

        UserDTO userDTO = UserDTO.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(ERole.USER) // enum olarak
                .build();

        Optional<UserDTO> createdUser = userDAO.create(userDTO);
        if (createdUser.isPresent()) {
            showAlert("Başarılı", "Kayıt başarılı", Alert.AlertType.INFORMATION);
            switchToLoginPane();
        } else {
            showAlert("Hata", "Kayıt başarısız oldu", Alert.AlertType.ERROR);
        }
    }


    @FXML
    private void switchToLoginPane() {
        try {
            SceneHelper.switchScene(FXMLPath.LOGIN, usernameField, "Giriş Yap");
        } catch (Exception e) {
            System.out.println(SpecialColor.RED + "Login Sayfasına yönlendirme başarısız" + SpecialColor.RESET);
            e.printStackTrace();
            showAlert("Hata", "Login ekranı yüklenemedi", Alert.AlertType.ERROR);
        }
    }
}
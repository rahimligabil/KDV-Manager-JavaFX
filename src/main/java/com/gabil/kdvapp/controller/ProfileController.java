package com.gabil.kdvapp.controller;


import com.gabil.kdvapp.dto.UserDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfileController {
    @FXML
    public Label usernameLabel;

    @FXML
    public Label emailLabel;

    UserDTO userDTO;

    public void setUser(UserDTO userDTO){
            this.userDTO = userDTO;
            usernameLabel.setText("Username: " + userDTO.getUsername());
            emailLabel.setText("Email: " + userDTO.getEmail());

    }

    public void initialize() {
    }
}

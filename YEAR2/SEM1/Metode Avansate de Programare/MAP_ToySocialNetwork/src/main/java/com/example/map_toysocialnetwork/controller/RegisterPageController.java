package com.example.map_toysocialnetwork.controller;

import com.example.map_toysocialnetwork.domain.Utilizator;
import com.example.map_toysocialnetwork.domain.validators.ValidationException;
import com.example.map_toysocialnetwork.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterPageController {

    @FXML
    TextField FirstName;

    @FXML
    TextField LastName;

    @FXML
    TextField Email;

    @FXML
    PasswordField Password;

    @FXML
    Button Register;

    Service service;

    public void setService(Service service) {
        this.service = service;
    }

    @FXML
    public void handleRegister() {
        System.out.println("Clicked!!!");

        System.out.println("register");

        String firstName = FirstName.getText();
        String lastName = LastName.getText();
        String email = Email.getText();
        String password = Password.getText();

        if (email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Introduceti datele corespunzatoare");
        }
        try
        {
            Utilizator utilizator = service.findOneEmail(email);
            service.saveUser(firstName,lastName,email, password);
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", "V-ati inregistrat cu succes");
            Stage thisStage = (Stage) Register.getScene().getWindow();
            thisStage.close();

        }catch ( NullPointerException e ){
            MessageAlert.showMessage(null,Alert.AlertType.INFORMATION,"Info",e.getMessage());

        }catch(IllegalArgumentException e){
            MessageAlert.showMessage(null,Alert.AlertType.INFORMATION,"Info",e.getMessage());

        }
        catch (ValidationException e ){
            MessageAlert.showMessage(null,Alert.AlertType.INFORMATION,"Info",e.getMessage());

        }
        catch (RuntimeException e){
            MessageAlert.showMessage(null,Alert.AlertType.INFORMATION,"Info",e.getMessage());

        }
    }
}
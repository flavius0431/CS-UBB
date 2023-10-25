package com.example.map_toysocialnetwork.controller;

import com.example.map_toysocialnetwork.domain.Utilizator;
import com.example.map_toysocialnetwork.domain.validators.ValidationException;
import com.example.map_toysocialnetwork.service.Service;
import com.example.map_toysocialnetwork.utils.events.FriendshipEntityChangeEvent;
import com.example.map_toysocialnetwork.utils.observer.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageController implements Observer<FriendshipEntityChangeEvent> {

    private Service service;

    @FXML
    TextField loginEmail;

    @FXML
    PasswordField loginPassword;

    @FXML
    Button loginButton;

    @FXML
    Button registerButton;

    public void setService(Service service) {
        this.service = service;
        service.addObserver(this);
    }
    @FXML
     void LoginButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Clicked!!!");

        System.out.println(loginEmail);

        String email = loginEmail.getText();
        String password = loginPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            MessageAlert.showMessage(null,Alert.AlertType.INFORMATION,"Info","Introduceti datele corespunzatoare");
             return;
        }

     try{
        Utilizator u = service.LogIn(email, password);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/map_toysocialnetwork/homePage.fxml"));
        AnchorPane root = loader.load();

        HomePageController homePageController = loader.getController();
        homePageController.setService(service);
        homePageController.setUtilizator(u);


        Scene scene= new Scene(root,568,392);
        Stage stage = new Stage();

        stage.setTitle("Hello, " + u.getFirstName());
        stage.setScene(scene);
        stage.show();

        Stage thisStage = (Stage) loginButton.getScene().getWindow();
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

    @FXML
    public void RegisterButtonClicked(ActionEvent event)throws IOException{
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/map_toysocialnetwork/registerPage.fxml"));
            AnchorPane root = loader.load();

            RegisterPageController registerPageController = loader.getController();
            registerPageController.setService(service);


            Scene scene= new Scene(root,568,392);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();


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


    @Override
    public void update(FriendshipEntityChangeEvent friendshipEntityChangeEvent) {
        setService(service);
    }
}

package controller;
import domain.OficiuPersoana;
import service.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageController {

    private Service service;

    @FXML
    TextField userField;

    @FXML
    PasswordField passwordField;

    @FXML
    Button loginButton;

    public void setService(Service service) {
        this.service = service;
    }

    @FXML
    void LoginButtonClicked()throws IOException{
        System.out.println("Clicked!!!");
        System.out.println(userField);
        String user = userField.getText();
        String password = passwordField.getText();
        if(user.isEmpty() || password.isEmpty()){
            System.out.println("Introduceti datele corespunzatoare");
            return;
        }
        try{
            service.login(user,password);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            OficiuPersoana op = service.login(user,password);
            System.out.println(op);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/homePage.fxml"));
            AnchorPane root = loader.load();
            HomePageController homePageController = loader.getController();
            homePageController.setService(service);
            homePageController.setOficiuPersoana(op);

            Scene scene =new Scene(root, 730, 505);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Hi,"+op.getUser());
            stage.show();

            Stage thisStage = (Stage) loginButton.getScene().getWindow();
            thisStage.close();
        }catch (Exception e){
            System.out.println(e.getMessage());


        }
    }

}

package com.example.practic.controller;

import com.example.practic.HelloApplication;
import com.example.practic.service.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenPageController {

    private Service service;

    @FXML
    Button OpenClient;



    public void setService(Service service) {
        this.service=service;
    }


    @FXML
    public void handleClickButton() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/practic/SearchDestination.fxml"));

        AnchorPane root = loader.load();

        SerachDestionationPageController controller = loader.getController();
        controller.setService(service);



        Scene scene = new Scene(root, 761, 496);
        Stage stage = new Stage();
        stage.setTitle("Social Network");
        stage.setScene(scene);
        stage.show();








    }
}
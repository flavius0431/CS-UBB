package com.example.map_toysocialnetwork;

import com.example.map_toysocialnetwork.controller.LoginPageController;
import com.example.map_toysocialnetwork.domain.Message;
import com.example.map_toysocialnetwork.domain.validators.MessageValidator;
import com.example.map_toysocialnetwork.domain.validators.PrietenieValidator;
import com.example.map_toysocialnetwork.domain.validators.UtilizatorValidator;
import com.example.map_toysocialnetwork.domain.validators.Validator;
import com.example.map_toysocialnetwork.repository.Repository0;
import com.example.map_toysocialnetwork.repository.database.MessageDB;
import com.example.map_toysocialnetwork.repository.database.PrietenieRepoDB;
import com.example.map_toysocialnetwork.repository.database.UtilizatorDB;
import com.example.map_toysocialnetwork.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("dddddddddddddddddddddddddddddd");
        System.out.println(getClass().getResource("loginPage.fxml"));
        String username = "postgres";
        String password = "postgres";
        String url = "jdbc:postgresql://localhost:5432/ReteaSociala";
        Repository0 repoPr=new PrietenieRepoDB(url,username,password,new PrietenieValidator());
        Repository0 repoU = new UtilizatorDB(url,username,password,new UtilizatorValidator());
        Repository0 mRepo = new MessageDB(url,username,password,new MessageValidator());
        Service service = new Service(repoU,repoPr,mRepo);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("loginPage.fxml"));

        AnchorPane root = loader.load();

        LoginPageController controller = loader.getController();
        controller.setService(service);

        Scene scene = new Scene(root,568,392);
        stage.setTitle("Social Network");
        stage.setScene(scene);
        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }

}
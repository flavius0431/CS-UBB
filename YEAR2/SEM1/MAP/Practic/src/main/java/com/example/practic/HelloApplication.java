package com.example.practic;

import com.example.practic.controller.OpenPageController;
import com.example.practic.domain.City;
import com.example.practic.domain.TrainStation;
import com.example.practic.domain.validators.CityValidator;
import com.example.practic.domain.validators.TrainsStationValidator;
import com.example.practic.repository.AbstractDBRepository;
import com.example.practic.repository.CityDBRepo;
import com.example.practic.repository.TrainStationDBRepo;
import com.example.practic.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        String username = "postgres";
        String password = "postgres";
        String url = "jdbc:postgresql://localhost:5432/Practic";

        AbstractDBRepository<Long, City> repoC = new CityDBRepo(url, username, password, new CityValidator());
        AbstractDBRepository<Long, TrainStation> repoTS = new TrainStationDBRepo(url, username, password,new TrainsStationValidator());
        //AbstractDBRepo<Double, SpecialOffer> repoSO = new SpecialOffertDBRepo(url,username,password,new ValidatorSpecialOffert());
       // AbstractDBRepo<Long, Client> repoC = new ClientDBRepo(url,username,password,new ValidatorClient());
        Service service = new Service(repoC,repoTS);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("OpenPage.fxml"));

        AnchorPane root = loader.load();

        OpenPageController controller = loader.getController();
        controller.setService(service);

        Scene scene = new Scene(root,761,496);
        stage.setTitle("Social Network");
        stage.setScene(scene);
        stage.show();


        //jdbc:postgresql://localhost:5432/Practic

      /*  FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OpenPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
        stage.show();*/
    }

    public static void main(String[] args) {
        launch();
    }
}
package com.example.practic.controller;

import com.example.practic.domain.City;
import com.example.practic.service.Service;
import com.example.practic.utils.Observable;
import com.example.practic.utils.Observer;
import com.example.practic.utils.events.EntityChangeEvent;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SerachDestionationPageController implements Observer<EntityChangeEvent> {

    private Service service;
    ObservableList<City> cityModel = FXCollections.observableArrayList();

    ObservableList<String> routesModel = FXCollections.observableArrayList();

    @FXML
    ComboBox<City>  departureCity;

    @FXML
    ComboBox<City>  destinationCity;

    @FXML
    CheckBox directRoute;

    @FXML
    Button SearchButton;

    @FXML
    TableView<String> tableViewroute;

    @FXML
    TableColumn<String,String> routesColumn;

    @FXML
    Label numberClients;

    private Integer clienti;


    public void setService(Service service){
        this.service=service;
        initModel();
    }

    public void initModel(){
        Iterable<City> message = this.service.getCities();
        List<City> cities = StreamSupport.stream(message.spliterator(),false).collect(Collectors.toList());
        cityModel.setAll(cities);


    }
    @FXML
    public void initialize() {
        departureCity.setItems(cityModel);
        destinationCity.setItems(cityModel);


    }

    public void handleSearchButton(){
        City departure =(City) departureCity.getSelectionModel().getSelectedItem();
        City destination =(City) destinationCity.getSelectionModel().getSelectedItem();

        Boolean check = directRoute.selectedProperty().get();
        System.out.println(check);



        Iterable<String> message = this.service.getRoutes(departure,destination,check);

        List<String> routes = StreamSupport.stream(message.spliterator(),false).collect(Collectors.toList());
        routesColumn.setCellValueFactory(p->new SimpleStringProperty(p.getValue()));
        routesModel.setAll(routes);
        tableViewroute.setItems(routesModel);


    }


    @Override
    public void update(EntityChangeEvent entityChangeEvent) {
        initModel();
    }
}

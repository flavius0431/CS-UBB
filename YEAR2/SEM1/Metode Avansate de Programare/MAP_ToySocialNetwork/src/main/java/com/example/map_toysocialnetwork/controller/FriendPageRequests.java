package com.example.map_toysocialnetwork.controller;

import com.example.map_toysocialnetwork.domain.Utilizator;
import com.example.map_toysocialnetwork.domain.UtilizatorPrietenieDTO;
import com.example.map_toysocialnetwork.service.Service;
import com.example.map_toysocialnetwork.utils.events.FriendshipEntityChangeEvent;
import com.example.map_toysocialnetwork.utils.events.UserEntityChangeEvent;
import com.example.map_toysocialnetwork.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FriendPageRequests implements Observer<FriendshipEntityChangeEvent> {

    private Service service;

    private Utilizator utilizator;

    ObservableList<UtilizatorPrietenieDTO> friendsModel = FXCollections.observableArrayList();

    public void setService(Service service) {
        this.service = service;
        service.addObserver(this);
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
        initModel();
    }

    private void initModel() {
        Iterable<UtilizatorPrietenieDTO> messages = service.getFriendshipsRequest(utilizator.getId());
        List<UtilizatorPrietenieDTO> friends = StreamSupport.stream(messages.spliterator(),false)
                .collect(Collectors.toList());
        System.out.println(friends.toArray().length);
        friendsModel.setAll(friends);
    }
   @FXML
    public void initialize(){
        tableColumnFirstNameP.setCellValueFactory(new PropertyValueFactory<UtilizatorPrietenieDTO,String>("friendfirstName"));
        tableColumnLastNameP.setCellValueFactory(new PropertyValueFactory<UtilizatorPrietenieDTO,String>("friendLastName"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<UtilizatorPrietenieDTO, LocalDateTime>("dateTime"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<UtilizatorPrietenieDTO,String>("status"));

        FriendRequests.setItems(friendsModel);
    }
    @FXML
    TableColumn<UtilizatorPrietenieDTO,String> tableColumnFirstNameP;
    @FXML
    TableColumn<UtilizatorPrietenieDTO,String> tableColumnLastNameP;

    @FXML
    TableColumn<UtilizatorPrietenieDTO,String> tableColumnStatus;

    @FXML
    TableColumn<UtilizatorPrietenieDTO, LocalDateTime> tableColumnDate;

    @FXML
    TableView<UtilizatorPrietenieDTO> FriendRequests;

    @Override
    public void update(FriendshipEntityChangeEvent friendshipEntityChangeEvent) {
       initModel();
    }

}

package com.example.map_toysocialnetwork.controller;

import com.example.map_toysocialnetwork.domain.Prietenie;
import com.example.map_toysocialnetwork.domain.Utilizator;
import com.example.map_toysocialnetwork.domain.UtilizatorPrietenieDTO;
import com.example.map_toysocialnetwork.service.Service;
import com.example.map_toysocialnetwork.utils.events.FriendshipEntityChangeEvent;
import com.example.map_toysocialnetwork.utils.observer.Observable;
import com.example.map_toysocialnetwork.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.BatchUpdateException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SentRequestPage implements Observer<FriendshipEntityChangeEvent> {

    Service service;

    private Utilizator utilizator;

    ObservableList<UtilizatorPrietenieDTO> sentModel =  FXCollections.observableArrayList();

    @FXML
    TableColumn<UtilizatorPrietenieDTO,String> tableColumnFirstNameP;

    @FXML
    TableColumn<UtilizatorPrietenieDTO,String> tableColumnLastNameP;

    @FXML
    TableColumn<UtilizatorPrietenieDTO,String> tableColumnStatus;

    @FXML
    TableColumn<UtilizatorPrietenieDTO, LocalDateTime> tableColumnDate;

    @FXML
    TableView<UtilizatorPrietenieDTO> sentRequests;

    @FXML
    Button cancelSentRequest;

    @Override
    public void update(FriendshipEntityChangeEvent friendshipEntityChangeEvent){
        initModel();
    }


    public void setService(Service service) {
        this.service = service;
        service.addObserver(this);
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
        initModel();
    }

    private void initModel() {
        Iterable<UtilizatorPrietenieDTO> messages = service.getSentRequest(utilizator.getId());
        List<UtilizatorPrietenieDTO> friends = StreamSupport.stream(messages.spliterator(),false)
                .collect(Collectors.toList());
        System.out.println(friends.toArray().length);
        sentModel.setAll(friends);
    }

    @FXML
    public void initialize(){
        tableColumnFirstNameP.setCellValueFactory(new PropertyValueFactory<UtilizatorPrietenieDTO,String>("friendfirstName"));
        tableColumnLastNameP.setCellValueFactory(new PropertyValueFactory<UtilizatorPrietenieDTO,String>("friendLastName"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<UtilizatorPrietenieDTO, LocalDateTime>("dateTime"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<UtilizatorPrietenieDTO,String>("status"));

        sentRequests.setItems(sentModel);
    }

    @FXML
    public void handleCancelSentRequest(ActionEvent event){
        UtilizatorPrietenieDTO request= (UtilizatorPrietenieDTO) sentRequests.getSelectionModel().getSelectedItem();
        if(request !=null){
            service.removeFriends(utilizator.getId(), request.getIdFriend());
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Info","Cererea de prietenie a fost stearsa cu succes");
        }
        else
        {
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Info","Selecteaza userul caruia vrei sa ii stergi cererea trimisa");
        }
    }
}

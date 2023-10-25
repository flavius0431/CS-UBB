package com.example.map_toysocialnetwork.controller;

import com.example.map_toysocialnetwork.domain.Prietenie;
import com.example.map_toysocialnetwork.domain.Utilizator;
import com.example.map_toysocialnetwork.domain.UtilizatorPrietenieDTO;
import com.example.map_toysocialnetwork.service.Service;
import com.example.map_toysocialnetwork.utils.events.FriendshipEntityChangeEvent;
import com.example.map_toysocialnetwork.utils.events.UserEntityChangeEvent;
import com.example.map_toysocialnetwork.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import org.controlsfx.control.table.TableRowExpanderColumn;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FriendsPageController implements Observer<FriendshipEntityChangeEvent> {

    Service service;
    @FXML
    TextField serchUserName;

    @FXML
    Button AddFriend;

    @FXML
    Button cancelFriendReguest;

    @FXML
    TableColumn<Utilizator, String> tableColumnFirstName;

    @FXML
    TableColumn<Utilizator, String> tableColumnLastName;

    @FXML
    TableColumn<Utilizator, String> tableColumnEmail;

    @FXML
    TableView<Utilizator> Users;

    ObservableList<Utilizator> userModel = FXCollections.observableArrayList();

    private Utilizator utilizator;

    public void setService(Service service) {
        this.service = service;
        service.addObserver(this);
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
        initModel();
    }

    public void initModel(){
        Iterable<Utilizator> messages = service.getNotFriend(utilizator.getId());
        List<Utilizator> notfriends = StreamSupport.stream(messages.spliterator(),false)
                .collect(Collectors.toList());
        System.out.println(notfriends.toArray().length);
        userModel.setAll(notfriends);

    }

    @FXML
    public void initialize(){
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<Utilizator,String>("firstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<Utilizator,String>("lastName"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<Utilizator,String>("email"));
        Users.setItems(userModel);

       serchUserName.textProperty().addListener(o->handleSearchByName());
    }

    public void handleSearchByName(){
        Predicate<Utilizator> pName = n->n.getLastName().startsWith((serchUserName.getText()));
        userModel.setAll(service.getNotFriend(utilizator.getId())
                .stream()
                .filter(pName)
                .collect(Collectors.toList()));
    }

    @Override
    public void update(FriendshipEntityChangeEvent userEntityChangeEvent) {
        initModel();
    }

    @FXML
    public void handleAddNewFriend(ActionEvent event){
        Utilizator friend = (Utilizator) Users.getSelectionModel().getSelectedItem();
        if( friend != null)
        {
            if(service.findFriendship(utilizator.getId(),friend.getId())==null){
                System.out.println("suntem pe ramura de nu exista prietenia");
                service.makeFriends(utilizator.getId(), friend.getId());
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Info","Cererea s-a trimis cu succes");

            }
            else
            {
                //insea,ma ca exista prietenia cu un anumit status
                Prietenie prietenie = service.findFriendship(utilizator.getId(), friend.getId());
                if(Objects.equals(prietenie.getStatus(),"pending"))
                {
                    System.out.println("suntem pe ramura de pending");
                    if(!Objects.equals(prietenie.getID1(),utilizator.getId()))
                    {
                        System.out.println("suntem pe ramura de devenim amici");
                        //inseama ca cererea este trimisa de prieten si o accepta,
                        service.updatePrietenie( friend.getId(), utilizator.getId(), LocalDateTime.now(),"accepted");
                        MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Info","Sunteti prieteni acum");

                    }
                    else
                    {
                        System.out.println("suntem pe ramura de  am trimis deja prietenia");
                        //altfel inseamna ca noi deja am trimis cererea si este in pending
                        MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Info","Cererea trimisa se afla inca in pending");
                    }
                }
                else if(Objects.equals(prietenie.getStatus(),"deleted"))
                {
                    System.out.println("suntem pe ramura de deleted");
                    //cand el ne-a trimis cerera, am sters-o si acum o trimitem noi din nou
                    service.removeFriends(friend.getId(), utilizator.getId());
                    service.makeFriends(utilizator.getId(),friend.getId());
                    MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Info","Din deleetd in pending");
                }
            }
        }
        else
        {
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Info","nu ai selectat pe nimeni");
        }
        System.out.println("am gatat ramura de friend");
        initModel();
    }

    @FXML
    public void handleCancelFriendRequest(ActionEvent event){
        Utilizator u= (Utilizator) Users.getSelectionModel().getSelectedItem();
        if(u !=null){
            Prietenie prietenie=service.findFriendship(utilizator.getId(),u.getId());
            if(prietenie!=null && Objects.equals(prietenie.getID1(), utilizator.getId()) && Objects.equals(prietenie.getStatus(), "pending")){
                service.removeFriends(prietenie.getID1(), prietenie.getID2());
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Info","Cererea de prietenie a fost stearsa cu succes");
            }
            else
            {
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Info","User-ul selectat nu a primit cerere de prietenie de la dumneavoastra!");
            }

        }else
        {
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Info","Selecteaza userul caruia vrei sa ii stergi cererea trimisa");
        }
    }
}

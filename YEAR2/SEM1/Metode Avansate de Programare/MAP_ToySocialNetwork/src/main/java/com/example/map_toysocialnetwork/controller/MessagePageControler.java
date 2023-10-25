package com.example.map_toysocialnetwork.controller;

import com.example.map_toysocialnetwork.domain.Message;
import com.example.map_toysocialnetwork.domain.Utilizator;
import com.example.map_toysocialnetwork.domain.UtilizatorPrietenieDTO;
import com.example.map_toysocialnetwork.service.Service;
import com.example.map_toysocialnetwork.utils.events.FriendshipEntityChangeEvent;
import com.example.map_toysocialnetwork.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MessagePageControler  implements Observer<FriendshipEntityChangeEvent> {

    Service service;

    private Utilizator me;

    private Utilizator friend;

    @FXML
    Button send;

    @FXML
    TextField msg;

    @FXML
    VBox msgs;

    @FXML
    ScrollPane scrollPane;



    public void setService(Service service) {
        this.service=service;
        service.addObserver(this);
    }

    public void setMe(Utilizator me, Utilizator friend){
        this.me=me;
        this.friend=friend;
        initModel();
    }

    public void initModel() {
        msgs.getChildren().clear();
        List<Message> mesaje=this.service.getMyMessages(me.getId(), friend.getId());

        if(mesaje.size() > 0) {
            for(Message element : mesaje){
                String mesaj = element.getContent();

                if (!mesaj.isEmpty()) {
                    HBox hBox = new HBox();
                    if (element.getSenderID() == me.getId()) {
                        hBox.setAlignment(Pos.CENTER_RIGHT);
                    } else {
                        hBox.setAlignment(Pos.CENTER_LEFT);
                    }


                    Text text = new Text(mesaj);

                    hBox.getChildren().add(text);

                    msgs.getChildren().add(hBox);
                }

            }
        }
    }


    @FXML
    public void handleSend() {
        String message = msg.getText();
        this.msg.clear();
        if (!message.isEmpty()) {
            service.saveMessage(me.getId(),friend.getId(),message);

            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);

            Text text = new Text(message);

            hBox.getChildren().add(text);
            msgs.getChildren().add(hBox);
        }

    }

    @Override
    public void update(FriendshipEntityChangeEvent friendshipEntityChangeEvent) {
        initModel();
    }
}

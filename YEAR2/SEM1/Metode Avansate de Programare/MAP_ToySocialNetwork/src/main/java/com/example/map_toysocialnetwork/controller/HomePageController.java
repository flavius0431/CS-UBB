package com.example.map_toysocialnetwork.controller;

import com.example.map_toysocialnetwork.domain.Utilizator;
import com.example.map_toysocialnetwork.domain.validators.MessageValidator;
import com.example.map_toysocialnetwork.domain.validators.PrietenieValidator;
import com.example.map_toysocialnetwork.domain.validators.UtilizatorValidator;
import com.example.map_toysocialnetwork.domain.validators.ValidationException;
import com.example.map_toysocialnetwork.repository.Repository0;
import com.example.map_toysocialnetwork.repository.database.MessageDB;
import com.example.map_toysocialnetwork.repository.database.PrietenieRepoDB;
import com.example.map_toysocialnetwork.repository.database.UtilizatorDB;
import com.example.map_toysocialnetwork.service.Service;
import com.example.map_toysocialnetwork.utils.events.FriendshipEntityChangeEvent;
import com.example.map_toysocialnetwork.utils.events.UserEntityChangeEvent;
import com.example.map_toysocialnetwork.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class HomePageController implements Observer<FriendshipEntityChangeEvent> {

    private Service service;

    private Utilizator utilizator;

    ObservableList<Utilizator> usersModel = FXCollections.observableArrayList();

    @FXML
    Button logOut;

    @FXML
    Button removeFriend;

    @FXML
    Button addNewFriend;

    @FXML
    Button friendRequest;

    @FXML
    Button sendRequest;

    @FXML
    Button newLoginButton;

    @FXML
    Button sendMessage;
    @FXML
    TableColumn<Utilizator, String> tableColumnFirstName;

    @FXML
    TableColumn<Utilizator, String> tableColumnLastName;

    @FXML
    TableColumn<Utilizator, String> tableColumnEmail;

    @FXML
    TableView<Utilizator> tableViewUtilizatori;

    public void setService(Service service) {
        this.service = service;
        service.addObserver(this);
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
        initModel();
    }


    public void initialize() {
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<Utilizator, String>("firstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<Utilizator, String>("lastName"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<Utilizator, String>("email"));
        tableViewUtilizatori.setItems(usersModel);
    }

    public void initModel() {
        Iterable<Utilizator> messages = service.friendsList(utilizator.getId());
        List<Utilizator> friendships = StreamSupport.stream(messages.spliterator(), false)
                .collect(Collectors.toList());
        usersModel.setAll(friendships);
    }

    @Override
    public void update(FriendshipEntityChangeEvent userEntityChangeEvent) {
        initModel();
    }

    public void handleLogOut(ActionEvent event) throws IOException {
        System.out.println("Log out");
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/map_toysocialnetwork/loginPage.fxml"));
            AnchorPane root = loader.load();

            LoginPageController loginPageController = loader.getController();
            loginPageController.setService(service);

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 600, 400));
            stage.setTitle("Add new friend");
            stage.show();

            Stage thisStage = (Stage) logOut.getScene().getWindow();
            thisStage.close();


        } catch (NullPointerException e) {
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", e.getMessage());

        } catch (IllegalArgumentException e) {
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", e.getMessage());

        } catch (ValidationException e) {
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", e.getMessage());

        } catch (RuntimeException e) {
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", e.getMessage());
        }

    }

    public void handleNewLoginButton(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/map_toysocialnetwork/loginPage.fxml"));

        AnchorPane root = loader.load();

        LoginPageController controller = loader.getController();
        controller.setService(service);

        Stage stage = new Stage();
        Scene scene = new Scene(root,568,392);
        stage.setTitle("Social Network");
        stage.setScene(scene);
        stage.show();
    }

    public void handleSendMessage(ActionEvent event) throws IOException {
        System.out.println("Sent message");
        Utilizator user = (Utilizator) tableViewUtilizatori.getSelectionModel().getSelectedItem();
        if (user != null) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/map_toysocialnetwork/messagePage.fxml"));
            AnchorPane root = loader.load();

            MessagePageControler messagePageControler = loader.getController();
            messagePageControler.setService(service);
            messagePageControler.setMe(utilizator,user);

            Stage stage = new Stage();
            Scene scene = new Scene(root, 575, 380);
            stage.setScene(scene);
            stage.setTitle("Aici este conversatia ta cu " + user.getFirstName());
            stage.show();


        } catch (NullPointerException e) {
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", e.getMessage());

        } catch (IllegalArgumentException e) {
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", e.getMessage());

        } catch (ValidationException e) {
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", e.getMessage());

        } catch (RuntimeException e) {
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", e.getMessage());

        }
    }
    else {
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Nu ai selectat pe nimeni ba");
        }
    }



    @FXML
    public void handleRemoveFriend(ActionEvent event){
        System.out.println("Remove friend");
        Utilizator user = (Utilizator) tableViewUtilizatori.getSelectionModel().getSelectedItem();
        if(user != null){
            service.updatePrietenie(user.getId(),utilizator.getId(), LocalDateTime.now(),"deleted");
            initModel();
        }
        else
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Info","Nu ai selectat pe nimeni ba");

    }

    public void handleAddNewFriend(ActionEvent event) throws IOException {
        System.out.println("Add new friend");
       try {
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("/com/example/map_toysocialnetwork/friendsPage.fxml"));
           AnchorPane root = loader.load();

           FriendsPageController addNewFriendController = loader.getController();
           addNewFriendController.setService(service);
           addNewFriendController.setUtilizator(utilizator);

           Stage stage = new Stage();
           stage.setScene(new Scene(root, 600, 400));
           stage.setTitle("Add new friend");
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

    @FXML
    public void handleFriendRequest()throws IOException{
        System.out.println("Friend request");

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/map_toysocialnetwork/friendRequest.fxml"));
            AnchorPane root = loader.load();

            FriendPageRequests friendPageRequests= loader.getController();
            friendPageRequests.setService(service);
            friendPageRequests.setUtilizator(utilizator);

            Stage stage = new Stage();
            Scene scene= new Scene(root,575,380);
            stage.setScene(scene);
            stage.setTitle("blavlavla");
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

    @FXML
    public void handleSentRequest()throws IOException{
        System.out.println("Sent request");

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/map_toysocialnetwork/sentrequestspage.fxml"));
            AnchorPane root = loader.load();

            SentRequestPage sentRequestPage= loader.getController();
            sentRequestPage.setService(service);
            sentRequestPage.setUtilizator(utilizator);

            Stage stage = new Stage();
            Scene scene= new Scene(root,575,380);
            stage.setScene(scene);
            stage.setTitle("blavlavla");
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

}



package controller;
import domain.*;

import javafx.util.Pair;
import service.Service;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class HomePageController {

    public Service service;
    public OficiuPersoana oficiuPersoana;

    ObservableList<Proba> probaModel= FXCollections.observableArrayList();

    ObservableList<Participant> participantModel = FXCollections.observableArrayList();
    @FXML
    TableColumn<Proba, String> probaColumn;

    @FXML
    TableColumn<Proba, Integer> varstaMinColumn;

    @FXML
    TableColumn<Proba, Integer> varstaMaxColumn;

    @FXML
    TableColumn<Proba, String> NumarParticipantiColumn;

    @FXML
    TableView<Proba> probaTableView;

    @FXML
    TableColumn<Participant, String> numeColumn;
    @FXML
    TableColumn<Participant, Integer> varstaColumn;

    @FXML
    TableView<Participant> participantTableView;

    @FXML
    TextField NumeTextField;

    @FXML
    TextField VarstaTextField;
    @FXML
    TextField CNPTextField;

    @FXML
    Button AdaugaButton;

    @FXML
    Button LogOutButton;

    public void setService(Service service) {
        this.service = service;
        initModel();
    }

    public void setOficiuPersoana(OficiuPersoana oficiuPersoana) {
        this.oficiuPersoana = oficiuPersoana;
        initModel();
    }

    public void initModel(){
        Iterable<Proba> messages = service.findAllProbs();
        List<Proba> probs = StreamSupport.stream(messages.spliterator(),false).collect(Collectors.toList());
        System.out.println(probs.toArray().length);
        probaModel.setAll(probs);
    }

    public void initParticipant(){
        Proba proba = (Proba) probaTableView.getSelectionModel().getSelectedItem();
        if(proba == null){
            MessageAlert.showErrorMessage(null, "Nu ati selectat nicio proba!");
            return;
        }
        Iterable<Participant> messages1 = service.findProba(proba.getVarstamin(), proba.getVarstamax(), proba.getProba());
        List<Participant> participants = StreamSupport.stream(messages1.spliterator(),false).collect(Collectors.toList());
        participantModel.setAll(participants);
    }

    @FXML
    public void initialize() {
        probaColumn.setCellValueFactory(new PropertyValueFactory<Proba, String>("proba"));
        varstaMinColumn.setCellValueFactory(new PropertyValueFactory<Proba, Integer>("varstamin"));
        varstaMaxColumn.setCellValueFactory(new PropertyValueFactory<Proba, Integer>("varstamax"));
        NumarParticipantiColumn.setCellValueFactory(p->{
            Proba proba = p.getValue();
            Integer value = this.service.participantCategorieProba(proba.getVarstamin(), proba.getVarstamax(), proba.getProba());
            return new SimpleStringProperty(value.toString());
        });

        probaTableView.setItems(probaModel);

        numeColumn.setCellValueFactory(new PropertyValueFactory<Participant,String>("nume"));
        varstaColumn.setCellValueFactory(new PropertyValueFactory<Participant,Integer>("varsta"));
        participantTableView.setItems(participantModel);
    }
    @FXML
    public void handleLogOut() throws IOException {
        System.out.println("Log out");
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/loginPage.fxml"));
            AnchorPane root = loader.load();

            LoginPageController loginPageController = loader.getController();
            loginPageController.setService(service);

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 400, 400));
            stage.show();

            Stage thisStage = (Stage) LogOutButton.getScene().getWindow();
            thisStage.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleAddButton(){
        System.out.println("ADD");
        String nume=NumeTextField.getText();
        String varsta=VarstaTextField.getText();
        String cnp=CNPTextField.getText();
        Proba proba = (Proba) probaTableView.getSelectionModel().getSelectedItem();

        if(proba == null){
            MessageAlert.showErrorMessage(null, "Nu ati selectat nicio proba!");
            return;
        }
        try{
             if(nume.isEmpty()|| varsta.isEmpty() || cnp.isEmpty()){
                 MessageAlert.showErrorMessage(null,"unul din campuri este gol");
             }else {
                 Participant p = service.searchwithCNP(cnp);
                 if(p==null) {
                     MessageAlert.showErrorMessage(null,"Participantul nu exista");

                     List<Proba> l = new ArrayList<>();
                     l.add(proba);
                     Participant p2 = new Participant(nume, Integer.parseInt(varsta), cnp,l);
                     service.addParticipant(p2);
                 }else
                 {
                     MessageAlert.showErrorMessage(null,"Participantul exista");
                     List<Proba> probe;
                     probe =p.getProbe();
                     probe.add(proba);
                     p.setProbe(probe);
                     service.updateParticipant(p);

                 }
             }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        NumeTextField.clear();
        VarstaTextField.clear();
        CNPTextField.clear();
        initParticipant();
        initModel();
        System.out.println("Participantul a fost adaugat");
    }


}

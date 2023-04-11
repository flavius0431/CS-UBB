import controller.LoginPageController;
import domain.Participant;
import repository.database.OficiuPersoanaDBRepo;
import repository.database.ParticipantDBRepo;
import repository.database.ProbaDBRepo;
import service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Properties proberties = new Properties();
        try {
            proberties.load(new FileReader("bd.config.properties"));
        } catch (IOException e) {
            System.out.println("cannot find bd.config" + e);
        }
        OficiuPersoanaDBRepo oficiuPersoanaDBRepo = new OficiuPersoanaDBRepo(proberties);
        ProbaDBRepo probaDBRepo = new ProbaDBRepo(proberties);
        ParticipantDBRepo participantDBRepo = new ParticipantDBRepo(proberties, probaDBRepo);
        Service service = new Service(oficiuPersoanaDBRepo, participantDBRepo, probaDBRepo);
        if (oficiuPersoanaDBRepo.Login("flavius", "1234") != null) {
            System.out.println("e corect");
        } else {
            System.out.println("e gresit");
        }
        System.out.println("Toti participantii din db");
        for (Participant participant : participantDBRepo.findAll()) {
            System.out.println(participant);
        }
        System.out.println("participanti la o anumita proba");
        for (Participant participant : service.ParticipantiProba("Desen")) {
            System.out.println(participant);
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("loginPage.fxml"));
        AnchorPane root = fxmlLoader.load();
        LoginPageController controller = fxmlLoader.getController();
        controller.setService(service);
        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}


module com.example.seminar8 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.seminar8 to javafx.fxml;
    exports com.example.seminar8;
}
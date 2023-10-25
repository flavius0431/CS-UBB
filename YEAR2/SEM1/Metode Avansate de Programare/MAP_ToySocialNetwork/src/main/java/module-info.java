module com.example.map_toysocialnetwork {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.map_toysocialnetwork to javafx.fxml;
    exports com.example.map_toysocialnetwork;

    exports com.example.map_toysocialnetwork.controller;
    opens com.example.map_toysocialnetwork.controller to javafx.fxml;

    exports com.example.map_toysocialnetwork.config;
    opens com.example.map_toysocialnetwork.config to javafx.fxml;

    exports com.example.map_toysocialnetwork.domain;
    opens com.example.map_toysocialnetwork.domain to javafx.fxml;

    exports com.example.map_toysocialnetwork.repository;
    opens com.example.map_toysocialnetwork.repository to javafx.fxml;

    exports com.example.map_toysocialnetwork.service;
    opens com.example.map_toysocialnetwork.service to javafx.fxml;
}
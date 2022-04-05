module com.example.simongame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.media;
    requires org.json;
    requires spring.context;
    requires spring.boot;

    opens com.example.simongame to javafx.fxml;
    exports com.example.simongame;
}
module com.example.bitcoinapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;

    opens com.example.bitcoinapp to javafx.fxml;
    exports com.example.bitcoinapp;
}
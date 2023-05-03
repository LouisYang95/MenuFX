module com.example.menufx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example to javafx.fxml;
    exports com.example;
    exports com.example.controller;
    opens com.example.controller to javafx.fxml;
    exports com.example.controller.dish;
    opens com.example.controller.dish to javafx.fxml;
}
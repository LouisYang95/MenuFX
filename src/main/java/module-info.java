module com.example.menufx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires itextpdf;


    opens com.example to javafx.fxml;
    exports com.example;
    exports com.example.controller.dish;
    opens com.example.controller.dish to javafx.fxml;

    exports com.example.controller.command;
    opens com.example.controller.command to javafx.fxml;
    exports com.example.controller.employee;
    opens com.example.controller.employee to javafx.fxml;
    exports com.example.controller.table;
    opens com.example.controller.table to javafx.fxml;
    exports com.example.controller.home;
    opens com.example.controller.home to javafx.fxml;

    opens com.example.model to javafx.base;
}
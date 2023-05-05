module com.example.menufx {
    requires javafx.controls;
    requires javafx.fxml;
    requires itextpdf;


    opens com.example to javafx.fxml;
    exports com.example;
}
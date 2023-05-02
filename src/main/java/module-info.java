module com.example.menufx {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.menufx to javafx.fxml;
    exports com.example.menufx;
}
module com.example.jrmi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;


    opens com.example.jrmi to javafx.fxml;
    exports com.example.jrmi;
}
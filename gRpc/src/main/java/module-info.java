module com.example.grpc {
    requires javafx.controls;
    requires javafx.fxml;
    requires grpc.api;


    opens com.example.grpc to javafx.fxml;
    exports com.example.grpc;
}
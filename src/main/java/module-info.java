module com.jenginesnake {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.jenginesnake to javafx.fxml;
    exports com.jenginesnake;
}
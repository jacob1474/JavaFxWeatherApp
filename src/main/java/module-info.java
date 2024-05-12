module org.example.worldappgui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;

    opens org.example.worldappgui to javafx.fxml;
    exports org.example.worldappgui;
}
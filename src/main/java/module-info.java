module org.example.worldappgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;

    opens sk.kasv.degro.worldappgui to javafx.fxml;
    exports sk.kasv.degro.worldappgui;
    exports sk.kasv.degro.worldappgui.database;
    opens sk.kasv.degro.worldappgui.database to javafx.fxml;
}
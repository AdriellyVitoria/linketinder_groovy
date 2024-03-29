module org.example.linketinder {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.groovy;


    opens org.example.linketinder.database to javafx.fxml;
    exports org.example.linketinder.database;
}
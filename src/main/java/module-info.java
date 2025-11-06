module org.example.bmicalculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.bmicalculator to javafx.fxml;
    exports org.example.bmicalculator;
}
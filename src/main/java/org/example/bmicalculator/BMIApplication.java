package org.example.bmicalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BMIApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BMIApplication.class.getResource("bmi-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 275, 470);
        stage.setTitle("BMI Calculator - Patrik Skogberg");
        stage.setScene(scene);
        stage.show();
    }
}

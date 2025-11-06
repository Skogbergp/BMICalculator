package org.example.bmicalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class BMIController {

    private Map<String, String> localizedStrings;

    private Locale currentLocale = new Locale("en", "US");


    @FXML Label errorLabel;
    @FXML private Label resultLabel;
    @FXML private Button calculateBtn;
    @FXML private TextField heightTextField;
    @FXML private Label heightLabel;
    @FXML private TextField weightTextField;
    @FXML private Label weightLabel;
    @FXML private Button englishBtn;
    @FXML private Button urduBtn;
    @FXML private Button frenchBtn;
    @FXML private Button vietnameseBtn;
    @FXML private Label timeLabel;
    @FXML private Label welcomeText;
    DecimalFormat df = new DecimalFormat("0.00");


    @FXML
    private void initialize() {
        setLanguage(currentLocale);
    }

    private void setLanguage(Locale locale) {
        resultLabel.setText("");
        localizedStrings = LocalizationService.getLocalizedStrings(locale);
        weightLabel.setText(localizedStrings.getOrDefault("weight", "Weight"));
        heightLabel.setText(localizedStrings.getOrDefault("height", "Height"));
        calculateBtn.setText(localizedStrings.getOrDefault("calculate", "Calculate"));
        updateTimeForLocale(locale);
        currentLocale = locale;
    }

    @FXML
    private void onEnglishClicked(ActionEvent actionEvent) {
        setLanguage(new Locale("en", "US"));
    }
    @FXML
    private void onUrduClicked(ActionEvent actionEvent) {
        setLanguage(new Locale("ur","PK"));
    }
    @FXML
    private void onFrenchClicked(ActionEvent actionEvent) {
        setLanguage(new Locale("fr","FR"));
    }
    @FXML
    private void onVietnameseClicked(ActionEvent actionEvent) {
        setLanguage(new Locale("vi","VN"));
    }
    private void updateTimeForLocale(Locale locale) {
        String country = locale.getCountry();
        String zoneId = "";
        switch (country) {
            case "US" -> zoneId = "America/New_York";
            case "FR" -> zoneId = "Europe/Paris";
            case "PK" -> zoneId = "Asia/Karachi";
            case "VN" -> zoneId = "Asia/Ho_Chi_Minh";
        }
        ZonedDateTime zonedate = ZonedDateTime.now(ZoneId.of(zoneId));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        timeLabel.setText(localizedStrings.getOrDefault("localTime", "Local Time") + " " + formatter.format(zonedate));
    }
    @FXML
    private void onCalculateBMI(ActionEvent actionEvent) {
        errorLabel.setVisible(false);
        try {
            double weight = Double.parseDouble(weightTextField.getText());
            double height = Double.parseDouble(heightTextField.getText()) / 100.0;
            double bmi = weight / (height * height);
            DecimalFormat df = new DecimalFormat("#0.00");
            resultLabel.setText(localizedStrings.getOrDefault("result", "Your BMI is") + " " + df.format(bmi));
            // Save to database
            String language = currentLocale.getLanguage(); // or store current locale
            BMIResultService.saveResult(weight, height, bmi, language);
        } catch (NumberFormatException e) {
            errorLabel.setVisible(true);
            errorLabel.setText(localizedStrings.getOrDefault("invalid", "Invalid input"));
        }

    }
}

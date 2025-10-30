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
import java.util.ResourceBundle;

public class BMIController {

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

    ResourceBundle rb;

    @FXML
    private void initialize() {
        setLanguage("en","US");
        if (englishBtn != null || urduBtn != null || frenchBtn != null || vietnameseBtn != null) {
            setTexts();
            updateTimeForLocale("en","US");

        }

    }

    private void setLanguage(String language,String country) {
        Locale locale = new Locale(language,country);
        rb = ResourceBundle.getBundle("MessagesBundle",locale);
        setTexts();
        updateTimeForLocale(language,country);

    }

    @FXML
    private void onEnglishClicked(ActionEvent actionEvent) {
        setLanguage("en","US");
    }
    @FXML
    private void onUrduClicked(ActionEvent actionEvent) {
        setLanguage("ur","PK");
    }
    @FXML
    private void onFrenchClicked(ActionEvent actionEvent) {
        setLanguage("fr","FR");
    }
    @FXML
    private void onVietnameseClicked(ActionEvent actionEvent) {
        setLanguage("vi","VN");
    }
    private void updateTimeForLocale(String language, String country) {
        String zoneId = "";
        switch (country) {
            case "US" -> zoneId = "America/New_York";
            case "FR" -> zoneId = "Europe/Paris";
            case "PK" -> zoneId = "Asia/Karachi";
            case "VN" -> zoneId = "Asia/Ho_Chi_Minh";
        }
        ZonedDateTime zonedate = ZonedDateTime.now(ZoneId.of(zoneId));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(rb.getString("time.format"));
        timeLabel.setText(rb.getString("timelabel.text") +zonedate.format(formatter));
    }
    @FXML
    private void onCalculateBMI(ActionEvent actionEvent) {
        errorLabel.setVisible(false);
        try {

            if (!heightTextField.getText().isEmpty() && !weightTextField.getText().isEmpty()) {
                double height = Double.parseDouble(heightTextField.getText())/100;
                double weight = Double.parseDouble(weightTextField.getText());
                double bmi = (weight / Math.pow(height, 2));

                resultLabel.setText(rb.getString("result.text") + " "+df.format(bmi));

            }else{
                errorLabel.setVisible(true);
                errorLabel.setText(rb.getString("error.emptyField"));
            }
        }catch (NumberFormatException e) {
            errorLabel.setVisible(true);
            errorLabel.setText(rb.getString("error.number"));
        }
    }
    private void setTexts(){
        englishBtn.setText(rb.getString("button1.text"));
        urduBtn.setText(rb.getString("button2.text"));
        frenchBtn.setText(rb.getString("button3.text"));
        vietnameseBtn.setText(rb.getString("button4.text"));
        welcomeText.setText(rb.getString("label.text"));
        weightLabel.setText(rb.getString("label.weight.text"));
        heightLabel.setText(rb.getString("label.height.text"));
        resultLabel.setText(rb.getString("result.text"));
    }
}

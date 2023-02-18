package com.immunizationcalendar.wiev;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StartViewController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
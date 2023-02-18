package com.immunizationcalendar;

import com.immunizationcalendar.wiev.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage stage)  {
        SceneManager sceneManager = new SceneManager(stage);
        sceneManager.runStartView();

    }

    public static void main(String[] args) {
        launch();
    }
}
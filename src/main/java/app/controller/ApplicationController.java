package app.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import tool.SceneSwitching;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationController extends SceneSwitching implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showComponent("/view/dictionary.fxml");
        dictionaryBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/view/dictionary.fxml");
            }
        });

        translateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/view/translate.fxml");
            }
        });

        gameBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/view/menuGame.fxml");
            }
        });

        tooltip1.setShowDelay(Duration.seconds(0.5));
        tooltip2.setShowDelay(Duration.seconds(0.5));
        tooltip3.setShowDelay(Duration.seconds(0.5));
        tooltip4.setShowDelay(Duration.seconds(0.5));

        closeBtn.setOnMouseClicked(e -> {
            System.exit(0);
        });
    }

    @FXML
    private Tooltip tooltip1, tooltip2, tooltip3, tooltip4;

    @FXML
    private Button addWordBtn, translateBtn, dictionaryBtn, closeBtn, gameBtn;
}

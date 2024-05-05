package app.controller.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tool.SceneSwitching;

import java.io.IOException;

public class MenuGameController extends SceneSwitching {
    private final String VIEWS_PATH = "/view/";

    @FXML
    public void switchToMultipleChoiceScene(ActionEvent event) throws IOException {
        showComponent(VIEWS_PATH + "/MultipleChoice.fxml");
    }

    @FXML
    public void switchToMatchingWordScene(ActionEvent event) throws IOException {
        showComponent(VIEWS_PATH + "/MatchingWord.fxml");
    }

    @FXML
    public void switchToHangManGameScene(ActionEvent event) throws IOException {
        showComponent(VIEWS_PATH + "/HangMan.fxml");
    }
}

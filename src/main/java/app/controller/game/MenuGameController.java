package app.controller.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuGameController extends Controller  {
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

    private void setNode(Node node) {
        container.getChildren().clear();
        container.getChildren().add(node);
    }

    @FXML
    private void showComponent(String path) {
        try {
            AnchorPane component = FXMLLoader.load(getClass().getResource(path));
            setNode(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private AnchorPane container;
}

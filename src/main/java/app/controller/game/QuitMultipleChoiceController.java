package app.controller.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


public class QuitMultipleChoiceController extends Controller {

    String VIEWS_PATH ="/view/";
    @FXML
    private Label scoreBox;
    @FXML
    private Label emotionBox;

    @FXML
    public void switchToGameScene(ActionEvent event) throws IOException {
        FXMLLoader gameScene = new FXMLLoader(getClass().getResource(VIEWS_PATH + "gameScene.fxml"));
        root = gameScene.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public void displayScore(int score) {
        scoreBox.setText("Your Score: " + score);
        if (score == 0) {
            emotionBox.setText("Seriously?");
        } else if (score >= 50) {
            emotionBox.setText("Good job baby!");
        }
    }

    @FXML
    public void replay(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEWS_PATH + "MultipleChoice.fxml"));
        root = loader.load();

        MultipleChoiceController multipleChoiceControllerController = loader.getController();
        multipleChoiceControllerController.initializeQuestion(event);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

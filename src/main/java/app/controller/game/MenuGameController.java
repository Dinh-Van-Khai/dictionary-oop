package app.controller.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuGameController extends Controller  {
    String VIEWS_PATH = "/view/";

    @FXML
    private Button closeButton;

    @FXML
    public void switchToMultipleChoiceScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEWS_PATH + "MultipleChoice.fxml"));
        root = loader.load();

        MultipleChoiceController controller = loader.getController();
        controller.initializeQuestion(event);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToMatchingWordScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEWS_PATH + "MatchingWord.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToHangManGameScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEWS_PATH + "HangMan.fxml"));
        root = loader.load();

        HangManController hangManController = loader.getController();
        hangManController.initializeQuestion(event);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}

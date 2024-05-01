package app.controller.dictionary;

import dictionary.Word;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddWord extends SearchTransDelFix {
    @FXML
    private TextField wordTargetInput;
    @FXML
    private TextArea wordExplainInput;
    @FXML
    private Button addWordButton;
    @FXML
    private Label successLabel;
    @FXML
    private Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        successLabel.setVisible(false);
        errorLabel.setVisible(false);

        if (wordTargetInput.getText().isEmpty() || wordExplainInput.getText().isEmpty()) {
            addWordButton.setDisable(true);
        }

        wordTargetInput.setOnKeyTyped(keyEvent -> addWordButton.setDisable(wordExplainInput.getText().isEmpty() || wordTargetInput.getText().isEmpty()));

        wordExplainInput.setOnKeyTyped(keyEvent -> addWordButton.setDisable(wordExplainInput.getText().isEmpty() || wordTargetInput.getText().isEmpty()));

        addWordButton.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Add Word");
            alert.setHeaderText(null);
            alert.setContentText(wordTargetInput.getText());

            ButtonType ButtonYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType ButtonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(ButtonYes, ButtonCancel);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get().getButtonData() == ButtonBar.ButtonData.YES) {
                String wordTarget = wordTargetInput.getText();
                String wordExplain = wordExplainInput.getText();

                Word newWord = new Word(wordTarget, wordExplain);

                if (dictionary.containsKey(wordTarget)) {
                    errorLabel.setVisible(true);
                    successLabel.setVisible(false);

                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Warning");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Word already exists");
                    alert1.show();
                    System.out.println("Errol");
                } else {
                    dictionary.put(wordTarget, newWord);
                    successLabel.setVisible(true);
                    errorLabel.setVisible(false);

                    resetInput();
                    addWordButton.setDisable(true);

                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Success");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Word added successfully");
                    alert2.show();
                    System.out.println("Success");
                }
            }
        });
    }

    private void resetInput() {
        wordTargetInput.setText("");
        wordExplainInput.setText("");
    }
}

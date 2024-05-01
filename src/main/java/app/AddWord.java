package app;

import dictionary.Word;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
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
    }

    private void resetInput() {
        wordTargetInput.setText("");
        wordExplainInput.setText("");
    }

    private void showSuccessAlert() {
        successLabel.setVisible(true);
        dictionaryManagement.setTimeout(() -> successLabel.setVisible(false), 1500);
    }

    private void showErrorAlert() {
        errorLabel.setVisible(true);
        dictionaryManagement.setTimeout(() -> errorLabel.setVisible(false), 1500);
    }

    public void handleOnClickSaveButton() {
        String engWord = wordTargetInput.getText();
        String explainWord = wordExplainInput.getText();

        Word newword = new Word(engWord, explainWord);
        if (dictionary.containsKey(newword.getWord_target())) {
            showErrorAlert();
        } else {
            dictionary.put(engWord, newword);
            showSuccessAlert();

            addWordButton.setDisable(true);
            resetInput();
        }
    }

}

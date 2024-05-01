package app.controller.dictionary;

import dictionary.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import tool.Language;
import tool.Sound;
import dictionary.Dictionary;
import dictionary.DictionaryManagement;


import java.net.URL;
import java.util.ResourceBundle;

public class DictionaryController implements Initializable {

    private ObservableList<String> list = FXCollections.observableArrayList();
    protected static Dictionary dictionary;
    protected static DictionaryManagement dictionaryManagement = new DictionaryManagement();

    static {
        dictionaryManagement.insertFromFile("src/main/resources/data/dictionary/WordList.txt");
        dictionary = dictionaryManagement.getDictionary();
    }


    @FXML
    private TextField searchTerm;
    @FXML
    private Button  saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button addWordBtn;
    @FXML
    private Label englishWord;
    @FXML
    private Label notAvailableAlert;
    @FXML
    private TextArea explanation;
    @FXML
    private ListView<String> listResults;
    @FXML
    private Pane headerOfExplanation;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //handle the state of search bar
        searchTerm.setOnKeyTyped(keyEvent -> {

            if (searchTerm.getText().isEmpty()) {
                cancelBtn.setVisible(false);
                setListDefault();
            } else {
                cancelBtn.setVisible(true);
                // function that processes keyboard input strings
                handleOnKeyTyped();
                searchTerm.setOnKeyPressed(keyEvent1 -> {
                    if (keyEvent1.getCode() == KeyCode.ENTER) {
                        handleEnterSearch(searchTerm);
                    }
                });
            }
        });

        //handle the state of cancelBtn
        cancelBtn.setOnAction(event -> {
            searchTerm.clear();
            setListDefault();
            cancelBtn.setVisible(false);
        });

        // function handle when click
        listResults.setOnMouseClicked(this::handleMouseClickedAWord);

        explanation.setDisable(false);
        saveBtn.setVisible(false);
        cancelBtn.setVisible(false);
        notAvailableAlert.setVisible(false);
    }

    //
    private void setListDefault() {
    }

    //
    @FXML
    private void handleOnKeyTyped() {
        list = dictionaryManagement.searchBySearchTerm(searchTerm.getText().trim());

        if (!list.isEmpty()) {
            listResults.setItems(list);
        }
    }

    //
    @FXML
    private void handleMouseClickedAWord(MouseEvent event) {
        String selectedWord = listResults.getSelectionModel().getSelectedItem();

        if (selectedWord != null) {
            englishWord.setText(selectedWord);
            explanation.setText(dictionary.get(selectedWord).getWord_explain());
            headerOfExplanation.setVisible(true);
            explanation.setVisible(true);
            explanation.setEditable(false);
            saveBtn.setVisible(false);
            notAvailableAlert.setVisible(false);
        }
    }

    private void handleEnterSearch(TextField searchTerm) {
        String selectedWord = searchTerm.getText();

        if (!selectedWord.isEmpty()) {
            try {
                englishWord.setText(selectedWord);
                explanation.setText(dictionary.get(selectedWord).getWord_explain());
                headerOfExplanation.setVisible(true);
                explanation.setVisible(true);
                explanation.setEditable(false);
                saveBtn.setVisible(false);
                notAvailableAlert.setVisible(false);
            } catch (Exception e) {
                headerOfExplanation.setVisible(false);
                explanation.setVisible(false);
                notAvailableAlert.setText("Word: " + selectedWord + " is not found!");
                notAvailableAlert.setVisible(true);
            }
            
        }
    }

    //
    @FXML
    private void handleClickedEditButton() {
        explanation.setEditable(true);
        saveBtn.setVisible(true);
    }

    @FXML
    private void handleClickedAddWordButton() {
        
    }

    //
    @FXML
    private void handleClickedVoiceButton() {
        Sound.getSound(englishWord.getText(), Language.ENGLISH);
    }

    //
    @FXML
    private void handleClickedSaveButton() {
        Word newWord = new Word(englishWord.getText(), explanation.getText());
        dictionaryManagement.dictionaryUpdateWord(newWord);

        saveBtn.setVisible(false);
        explanation.setEditable(false);
    }

    //
    @FXML
    private void handleClickedDeleteButton() {
        String selectedWord = listResults.getSelectionModel().getSelectedItem();
        dictionaryManagement.dictionaryRemoveWord(selectedWord);

        f5RefreshAfterDeleting();
    }

    //
    @FXML
    private void f5RefreshAfterDeleting() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(englishWord.getText())) {
                list.remove(i);
                break;
            }
        }
        listResults.setItems(list);
        headerOfExplanation.setVisible(false);
        explanation.setVisible(false);
    }
}

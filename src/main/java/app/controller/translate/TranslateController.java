package app.controller.translate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import tool.Language;
import tool.Sound;
import translate.Translate;

public class TranslateController implements Initializable {
    @FXML
    private TextArea textFrom, textTo;
    @FXML
    private Button soundTextFrom, soundTextTo, translateBtn;
    @FXML
    private ComboBox<Language> langFrom, langTo;

    private Translate translate = new Translate("", Language.ENGLISH, Language.VIETNAMESE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Language> list = FXCollections.observableArrayList(Language.values());
        langFrom.setItems(list);
        langTo.setItems(list);
        langFrom.setValue(Language.ENGLISH);
        langTo.setValue(Language.VIETNAMESE);
        langFrom.setOnAction(e -> handleLangFromAction());
        langTo.setOnAction(e -> handleLangToAction());

        translateBtn.setOnAction(e -> {
            try {
                textTo.setText(translate.getTextTo());
            } catch (URISyntaxException | IOException exception) {
                exception.printStackTrace();
            }
        });
        
        textFrom.setOnKeyReleased(e -> translate.setTextFrom(textFrom.getText()));
        textTo.setEditable(false);
        
        translateBtn.setDisable(true);
        textFrom.textProperty().addListener((observable, oldValue, newValue) -> {
            translateBtn.setDisable(newValue.trim().isEmpty());
        });

        soundTextFrom.setOnAction(e -> Sound.getSound(textFrom.getText(), translate.getLangFrom()));
        soundTextTo.setOnAction(e -> Sound.getSound(textTo.getText(), translate.getLangTo()));
    }

    @FXML
    private void handleLangFromAction() {
        Language selectedLanguage = langFrom.getSelectionModel().getSelectedItem();
        if (selectedLanguage != null) {
            translate.setLangFrom(selectedLanguage);
        }
    }
    
    @FXML
    private void handleLangToAction() {
        Language selectedLanguage = langTo.getSelectionModel().getSelectedItem();
        if (selectedLanguage != null) {
            translate.setLangTo(selectedLanguage);
        }
    }
}

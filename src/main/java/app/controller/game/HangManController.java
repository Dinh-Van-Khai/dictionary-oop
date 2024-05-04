package app.controller.game;

import game.gamejavaFX.HangMan;
import game.gamejavaFX.HangManWord;

import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HangManController implements Initializable {
    String VIEWS_PATH = "/view/";

    PauseTransition delay = new PauseTransition(Duration.seconds(2));

    @FXML
    public Label guessWord;
    @FXML
    public Label gameOver;
    @FXML
    public Label correctWord;
    @FXML
    public TextField guessChar;
    @FXML
    private Label resultBox;
    @FXML
    private Label scoreBox;
    @FXML
    private ImageView healthImage;
    @FXML
    private ImageView Man;
    @FXML
    private Button replay;

    private HangMan hangman = new HangMan();
    HangManWord word = new HangManWord(hangman.randWord());

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initializeQuestion(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initializeQuestion(ActionEvent event) throws IOException {
        hangman.insertFromFile();
        setupAnswerField();
        resultBox.setText("Guess the word!");
        word = new HangManWord(hangman.randWord());
        nextWord();
        System.out.println(word.word);
    }

    private void setupAnswerField() {
        if (guessChar != null) {
            guessChar.setEditable(true);

            guessChar.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    handleAnswer();
                }
            });
        } else {
            System.out.println("guessChar is null!");
        }
    }

    private void handleAnswer() {
        String userInput = guessChar.getText().trim().toLowerCase();
        char userChar = userInput.charAt(0);
        // đoán sai
        if (!word.checkAnswers(userChar)) {
            resultBox.setText("Wrong!");
            hangman.decreaseHealth();

            if (hangman.getHealth() <= 0) {
                updateImageHealth();
                correctWord.setText("Correct word: " + word.word);

                delay.setOnFinished(event -> {
                    correctWord.setVisible(false);
                    gameOver.setText("Game Over");
                    gameOver.setVisible(true);
                    replay.setVisible(true);

                    guessWord.setVisible(false);
                    healthImage.setVisible(false);
                    Man.setVisible(false);
                    guessChar.setVisible(false);
                    resultBox.setVisible(false);
                    guessChar.clear();
                });
                delay.play();
            } else {
                updateImageHealth();
            }

            guessChar.clear();
        }
        // đoán đúng
        else {
            resultBox.setText("Correct!");
            scoreBox.setText("Score: " + hangman.getScore());
            guessWord.setText(word.randGuessWord());
            hangman.increaseHighscore();
            guessChar.clear();
        }

        if (word.completedWord()) {
            correctWord.setText("Congratulations! 🎉🎉🎉");
            guessChar.clear();
        }
    }

    private void updateImageHealth() {
        String imageheartName = "";
        String imgMan = "";
        int health = hangman.getHealth();
        if (health == 5) {
            imageheartName = "src/main/resources/img/game/5heart.png";
            imgMan = "src/main/resources/img/game/hangmanfull.png";
        } else if (health == 4) {
            imageheartName = "src/main/resources/img/game/4heart.png";
            imgMan = "src/main/resources/img/game/hangman1leg.png";
        } else if (health == 3) {
            imageheartName = "src/main/resources/img/game/3heart.png";
            imgMan = "src/main/resources/img/game/hangman0leg.png";
        } else if (health == 2) {
            imageheartName = "src/main/resources/img/game/2heart.png";
            imgMan = "src/main/resources/img/game/hangman0leg1hand.png";
        } else if (health == 1) {
            imageheartName = "src/main/resources/img/game/1heart.png";
            imgMan = "src/main/resources/img/game/hangman0leg0hand.png";
        } else if (health == 0) {
            imgMan = "src/main/resources/img/game/hangman0body.png";
        }
        URL imageUrl = getClass().getResource(imageheartName);
        if (imageUrl != null) {
            Image image = new Image(imageUrl.toExternalForm());
            healthImage.setImage(image);
            healthImage.setPreserveRatio(true); // Giữ nguyên tỷ lệ ảnh
        } else {
            System.err.println("Could not find image: " + imageheartName);
        }

        URL imageUrl2 = getClass().getResource(imgMan);
        if (imageUrl2 != null) {
            Image image = new Image(imageUrl2.toExternalForm());
            Man.setImage(image);
            Man.setPreserveRatio(true); // Giữ nguyên tỷ lệ ảnh
        } else {
            System.err.println("Could not find image: " + imgMan);
        }
    }

    @FXML
    private void nextWord() {
        if (hangman != null && word != null) {
            guessWord.setText(word.randGuessWord());
        }
        if (hangman.getHealth() <= 0) {
            hangman.setHealth(5);
        }
        hangman.setScore(0);
        updateImageHealth();
        gameOver.setText("");
        correctWord.setText("");
    }

    @FXML
    public void switchBackToGameScene(ActionEvent event) {
        showComponent(VIEWS_PATH + "/menuGame.fxml");
    }

    @FXML
    public void replay(ActionEvent event) {
        showComponent(VIEWS_PATH + "HangMan.fxml");
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

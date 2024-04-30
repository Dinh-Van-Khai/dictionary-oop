package app.controller.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import game.gamejavaFX.HangMan;
import game.gamejavaFX.HangManWord ;

import java.io.IOException;
import java.net.URL;

public class HangManController extends Controller {
    String VIEWS_PATH ="/view/";

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

    private HangMan hangman = new HangMan();
    HangManWord word = new HangManWord(hangman.randWord());
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
                gameOver.setText("GAME OVER!");
                correctWord.setText("Correct word: " + word.word);
                guessChar.clear();
            } else {
                updateImageHealth();
            }

            guessChar.clear();
        }
        // đoán đúng
        else {
            resultBox.setText("Correct!");
            scoreBox.setText(hangman.getScore() + "");
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
        String imgMan ="";
        int health = hangman.getHealth();
        if (health == 5){
            imageheartName ="/src/main/resources/img/game/5heart.png";
            imgMan = "/src/main/resources/img/game/hangmanfull.png";
        }else if (health == 4) {
            imageheartName ="/src/main/resources/img/game/4heart.png";
            imgMan = "/src/main/resources/img/game/hangman1leg.png";
        } else if (health == 3) {
            imageheartName = "/src/main/resources/img/game/3heart.png";
            imgMan = "/src/main/resources/img/game/hangman0leg.png";
        } else if (health == 2) {
            imageheartName = "/src/main/resources/img/game/2heart.png";
            imgMan = "/src/main/resources/img/game/hangman0leg1hand.png";
        } else if (health == 1) {
            imageheartName = "/src/main/resources/img/game/1heart.png";
            imgMan = "/src/main/resources/img/game/hangman0leg0hand.png";
        }
        else if(health == 0){
            imgMan ="/src/main/resources/img/game/hangman0body.png";
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
        if (hangman.getHealth() <= 0){
            hangman.setHealth(5);
        }
        hangman.setScore(0);
        updateImageHealth();
        gameOver.setText("");
        correctWord.setText("");
    }

    @FXML
    public void switchBackToGameScene(ActionEvent event) throws IOException {
        FXMLLoader gameScene = new FXMLLoader(getClass().getResource(VIEWS_PATH + "menuGame.fxml"));
        root = gameScene.load();

        MenuGameController menu = gameScene.getController();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void end(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEWS_PATH + "QuitHangMan.fxml"));
        root = loader.load();

        QuitHangManController end = loader.getController();
        end.displayScore(hangman.getScore());

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}


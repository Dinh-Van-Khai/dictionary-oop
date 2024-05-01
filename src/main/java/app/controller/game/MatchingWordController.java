package app.controller.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import game.gamejavaFX.MatchingWord;

import java.io.IOException;

public class MatchingWordController extends Controller {
    String VIEWS_PATH ="/view/";
    MatchingWord matchingWord = new MatchingWord();

    private Timeline countdownTimeline;
    private int countdownSeconds = 20;

    @FXML
    private Label computer;
    @FXML
    private TextField user;
    @FXML
    private Label gameOver;
    @FXML
    private Button replay;
    @FXML
    private Label scoreBox;
    @FXML
    private Button start;
    @FXML
    private Label countdownLabel;

    @FXML
    public void initializeQuestion(ActionEvent event) throws IOException {
        start.setVisible(false);
        computer.setVisible(true);
        user.setVisible(true);

        // Tạo danh sách từ theo bảng chữ cái
        matchingWord.WordListByFirstCharactor();

        setupAnswerField();
        // Tạo 1 từ để bắt đầu game
        String word = matchingWord.randomWord();

        // Thêm vào danh sách đáp án
        matchingWord.answerList.add(word);

        // Lấy kỹ tự cuối cùng của từ khởi tạo
        matchingWord.charactorKey = word.charAt(word.length()-1);

        System.out.println(matchingWord.charactorKey);
        computer.setText(word);

        countdownLabel.setVisible(true);
        startCountdown();

    }

    private void startCountdown() {
        countdownTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    countdownSeconds--;
                    if(countdownSeconds<10){
                        countdownLabel.setText("00:0" + countdownSeconds);
                    }
                    else {
                        countdownLabel.setText("00:" + countdownSeconds);
                    }

                    if (countdownSeconds <= 0) {
                            // Nếu hết thời gian :
                            computer.setVisible(false);
                            user.setVisible(false);
                            gameOver.setVisible(true);
                            replay.setVisible(true);
                            countdownLabel.setVisible(false);
                    }
                })
        );
        countdownTimeline.setCycleCount(Timeline.INDEFINITE);
        countdownTimeline.play();
    }

    private void setupAnswerField() {
        if (user != null) {
            user.setEditable(true);

            user.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    handleAnswer();
                }
            });
        } else {
            System.out.println("guessChar is null!");
        }
    }

    private void handleAnswer() {
        String userInput = user.getText().trim().toLowerCase();
        // đoán sai
        if (!matchingWord.checkAnswer(userInput)) {
           computer.setVisible(false);
           user.setVisible(false);
           gameOver.setVisible(true);
           replay.setVisible(true);
           countdownLabel.setVisible(false);
        }
        // đoán đúng
        else {
            // Thêm vào danh sách kiểm tra độ trùng lặp
            matchingWord.answerList.add(userInput);
            // Lấy kỹ tự cuối của từ
            matchingWord.charactorKey = userInput.charAt(userInput.length()-1);
            System.out.println(matchingWord.charactorKey);
            // Tăng điểm
            matchingWord.increaseHighscore();
            // Hiển thị điểm
            scoreBox.setText("Score: " + matchingWord.getScore());
            // Random 1 từ mới cho máy
            String computerWord = matchingWord.randomWord();
            // Máy hiển thị từ mới
            computer.setText(computerWord);
            // lấy ký tự cuối của máy
            matchingWord.charactorKey = computerWord.charAt(computerWord.length()-1);
            user.clear();
            countdownSeconds = 20;
        }
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
    public void replay(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEWS_PATH + "MatchingWord.fxml"));
        root = loader.load();

        MatchingWordController matchingWordController = loader.getController();
        matchingWordController.initializeQuestion(event);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
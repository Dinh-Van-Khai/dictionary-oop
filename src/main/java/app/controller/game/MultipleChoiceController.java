package app.controller.game;

import game.gamejavaFX.MultipleChoice;
import game.gamejavaFX.MultipleChoiceQuestion;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.util.Duration;
import tool.SceneSwitching;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MultipleChoiceController extends SceneSwitching implements Initializable {
    private Timeline countdownTimeline;
    private int countdownSeconds = 20;
    String VIEWS_PATH = "/view/";

    private MultipleChoice game = new MultipleChoice();
    private MultipleChoiceQuestion question = new MultipleChoiceQuestion();

    @FXML
    private Button answerA;
    @FXML
    private Button answerB;
    @FXML
    private Button answerC;
    @FXML
    private Button answerD;
    @FXML
    private Label questionBox;
    @FXML
    private Label resultBox;
    @FXML
    private Label scoreBox;
    @FXML
    private ImageView healthImage;
    @FXML
    private Label gameOver;
    @FXML
    private Button replay;
    @FXML
    private Label countdownLabel;

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
        game.createListQuestion();
        question = game.randomQuestion();
        question.setListAnswers(game.createRandomAnswers(question.getAnswer()));
        questionBox.setText(question.getQuestion());
        answerA.setText(question.getListAnswers().get(0));
        answerB.setText(question.getListAnswers().get(1));
        answerC.setText(question.getListAnswers().get(2));
        answerD.setText(question.getListAnswers().get(3));
        updateImageHealth();
        gameOver.setVisible(false);
        replay.setVisible(false);
        answerA.setVisible(true);
        answerB.setVisible(true);
        answerC.setVisible(true);
        answerD.setVisible(true);
        if (game.getHealth() <= 0) {
            game.setHealth(5);
            updateImageHealth();
            game.setScore(0);
        }
        startCountdown();
    }

    private void startCountdown() {
        countdownTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    countdownSeconds--;
                    if (countdownSeconds < 10) {
                        if (countdownSeconds <= 3) {
                            countdownLabel.setTextFill(Color.RED);
                        }
                        countdownLabel.setText("00:0" + countdownSeconds);
                    } else {
                        countdownLabel.setText("00:" + countdownSeconds);
                    }

                    if (countdownSeconds <= 0) {
                        try {
                            // Nếu hết thời gian, chuyển sang câu hỏi mới và trừ điểm
                            incorrect();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }));
        countdownTimeline.setCycleCount(Timeline.INDEFINITE);
        countdownTimeline.play();
    }

    public void autoChangeQuestion() {
        countdownLabel.setTextFill(Color.BLACK);
        question = game.randomQuestion();
        question.setListAnswers(game.createRandomAnswers(question.getAnswer()));
        questionBox.setText(question.getQuestion());
        answerA.setText(question.getListAnswers().get(0));
        answerB.setText(question.getListAnswers().get(1));
        answerC.setText(question.getListAnswers().get(2));
        answerD.setText(question.getListAnswers().get(3));

    }

    public void correct() {
        resultBox.setText("Correct!");
        game.increaseHighscore();
        scoreBox.setText("Score: " + game.getScore());
        autoChangeQuestion();
    }

    public void incorrect() throws IOException {
        resultBox.setText("Wrong!");
        game.decreaseHealth();
        int health = game.getHealth();
        System.out.println(game.getHealth());
        autoChangeQuestion();
        updateImageHealth();
        if (health == 0) {
            gameOver.setVisible(true);
            replay.setVisible(true);
            questionBox.setText(" ");
            answerA.setVisible(false);
            answerB.setVisible(false);
            answerC.setVisible(false);
            answerD.setVisible(false);
            countdownLabel.setVisible(false);
        }
        countdownSeconds = 20;
    }

    private void updateImageHealth() {
        String imageheartName = "";
        int health = game.getHealth();
        if (health == 5) {
            imageheartName = "/img/game/5heart.png";
        } else if (health == 4) {
            imageheartName = "/img/game/4heart.png";
        } else if (health == 3) {
            imageheartName = "/img/game/3heart.png";
        } else if (health == 2) {
            imageheartName = "/img/game/2heart.png";
        } else if (health == 1) {
            imageheartName = "/img/game/1heart.png";
        }
        URL imageUrl = getClass().getResource(imageheartName);
        if (imageUrl != null) {
            Image image = new Image(imageUrl.toExternalForm());
            healthImage.setImage(image);
            healthImage.setPreserveRatio(true); // Giữ nguyên tỷ lệ ảnh
        } else {
            System.err.println("Could not find image: " + imageheartName);
        }
    }

    @FXML
    private void checkA() throws Exception {
        if (answerA.getText().equals(question.getAnswer())) {
            correct();
        } else {
            incorrect();
        }

    }

    @FXML
    private void checkB() throws Exception {
        if (answerB.getText().equals(question.getAnswer())) {
            correct();
        } else {
            incorrect();
        }

    }

    @FXML
    private void checkC() throws Exception {
        if (answerC.getText().equals(question.getAnswer())) {
            correct();
        } else {
            incorrect();
        }

    }

    @FXML
    private void checkD() throws Exception {
        if (answerD.getText().equals(question.getAnswer())) {
            correct();
        } else {
            incorrect();
        }

    }

    @FXML
    public void switchBackToGameScene(ActionEvent event) {
        showComponent(VIEWS_PATH + "menuGame.fxml");
    }

    @FXML
    public void replay(ActionEvent event) {
        showComponent(VIEWS_PATH + "MultipleChoice.fxml");
    }
}

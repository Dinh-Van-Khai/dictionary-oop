package app.controller.game;
import game.gamejavaFX.MultipleChoice ;
import game.gamejavaFX.MultipleChoiceQuestion ;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;

public class MultipleChoiceController extends Controller{
    private Timeline countdownTimeline;
    private int countdownSeconds = 20;
    String VIEWS_PATH ="/view/";

    private MultipleChoice game = new MultipleChoice();
    private  MultipleChoiceQuestion question = new MultipleChoiceQuestion();

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
    public void initializeQuestion(ActionEvent event) throws IOException {
        game.createListQuestion();
        question = game.randomQuestion();
        question.setListAnswers(game.createRandomAnswers(question.getAnswer()));
        questionBox.setText(question.getQuestion());
        answerA.setText(question.getListAnswers().get(0));
        answerB.setText(question.getListAnswers().get(1));
        answerC.setText(question.getListAnswers().get(2));
        answerD.setText(question.getListAnswers().get(3));

        gameOver.setVisible(false);
        replay.setVisible(false);
        answerA.setVisible(true);
        answerB.setVisible(true);
        answerC.setVisible(true);
        answerD.setVisible(true);
        if(game.getHealth()<=0){
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
                    if(countdownSeconds<10){
                        countdownLabel.setText("00:0" + countdownSeconds);
                    }
                    else {
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
                })
        );
        countdownTimeline.setCycleCount(Timeline.INDEFINITE);
        countdownTimeline.play();
    }
    public void autoChangeQuestion(){
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
        scoreBox.setText("Score: "+ game.getScore());
        autoChangeQuestion();
    }

    public void incorrect() throws IOException {
        resultBox.setText("Wrong!");
        game.decreaseHealth();
        int health = game.getHealth();
        System.out.println(game.getHealth());
        autoChangeQuestion();
        updateImageHealth();
        if(health == 0){
            gameOver.setVisible(true);
            replay.setVisible(true);
            questionBox.setText(" ");
            answerA.setVisible(false);
            answerB.setVisible(false);
            answerC.setVisible(false);
            answerD.setVisible(false);
        }
        countdownSeconds = 20;
    }
    private void updateImageHealth() {
        String imageheartName = "";
        int health = game.getHealth();
        if (health == 5){
            imageheartName ="src/main/resources/img/game/5heart.png";
        }else if (health == 4) {
            imageheartName ="src/main/resources/img/game/4heart.png";
        } else if (health == 3) {
            imageheartName = "src/main/resources/img/game/3heart.png";
        } else if (health == 2) {
            imageheartName = "src/main/resources/img/game/2heart.png";
        } else if (health == 1) {
            imageheartName = "src/main/resources/img/game/1heart.png";
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
    private void checkA() throws Exception{
        if(answerA.getText().equals(question.getAnswer())){
            correct();
        }
        else{
            incorrect();
        }

    }

    @FXML
    private void checkB() throws Exception{
        if(answerB.getText().equals(question.getAnswer())){
            correct();
        }
        else{
            incorrect();
        }

    }

    @FXML
    private void checkC() throws Exception{
        if(answerC.getText().equals(question.getAnswer())){
            correct();
        }
        else{
            incorrect();
        }

    }

    @FXML
    private void checkD() throws Exception{
        if(answerD.getText().equals(question.getAnswer())){
            correct();
        }
        else{
            incorrect();
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
    public void end(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEWS_PATH + "QuitMultipleChoice.fxml"));
        root = loader.load();

        QuitMultipleChoiceController end = loader.getController();
        end.displayScore(game.getScore());

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
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

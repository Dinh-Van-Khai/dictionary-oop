package game.gamejavaFX;

import java.util.ArrayList;

public class MultipleChoiceQuestion {

    private String question;
    private String answer;

    // lưu danh sách các đáp án A,B,C,D
    private ArrayList<String> listAnswers = new ArrayList<>();
//    private String answerA;
//    private String answerB;
//    private String answerC;
//    private String answerD;

    public MultipleChoiceQuestion() {
    }

    public MultipleChoiceQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public ArrayList<String> getListAnswers() {
        return listAnswers;
    }
    public void setListAnswers(ArrayList<String> listAnswers) {
        this.listAnswers = listAnswers;
    }
}

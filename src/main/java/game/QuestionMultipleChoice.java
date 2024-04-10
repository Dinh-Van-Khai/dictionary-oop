package game;

import java.util.ArrayList;

public class QuestionMultipleChoice {
    private String question;
    private String answer;

    // lưu danh sách các đáp án A,B,C,D
    private ArrayList<String> listAnswers = new ArrayList<>();

    public QuestionMultipleChoice(String question, String answer) {
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

    public void printQuestion(int index) {
        String s = "Question " + Integer.toString(index) + ": ";
        s += question + "\n";
        s += "A. " + listAnswers.get(0) + "\n"
                + "B. " + listAnswers.get(1) + "\n"
                + "C. " + listAnswers.get(2) + "\n"
                + "D. " + listAnswers.get(3) + "\n";
        System.out.println(s);
    }

    public boolean checkAnswers(char userAnswers) {
        userAnswers = Character.toUpperCase(userAnswers);
        int index = userAnswers - 'A';
        String youranswer = listAnswers.get(index);
        if (answer.equals(youranswer) == true) {
            return true;
        } else {
            return false;
        }
    }

}

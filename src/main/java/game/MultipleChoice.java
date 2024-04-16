package game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class MultipleChoice implements Game {
    // danh sách các câu hỏi
    private ArrayList<QuestionMultipleChoice> listQuestion = new ArrayList<>();
    private int point;
    private int health;
    private String path;

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void increasePoint() {
        point++;
    }

    public void decreaseHealth() {
        health--;
    }

    public MultipleChoice() {
        point = 0;
        health = 3;
        path = "src/main/resources/app/game/MultipleChoiceData.txt";
    }

    // tạo danh sách câu hỏi
    public void createListQuestion() {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bf.readLine()) != null) {
                String[] word = line.split("\\s+");

                String answer = word[0];
                String question = "";
                for (int i = 1; i < word.length; i++) {
                    String temp = word[i].substring(0, word[i].length() - 1);
                    if (answer.equalsIgnoreCase(word[i]) || answer.equalsIgnoreCase(temp)) {
                        question += "___" + " ";
                    } else {
                        question += word[i] + " ";
                    }
                }
                listQuestion.add(new QuestionMultipleChoice(question, answer));
            }
            bf.close();
        } catch (FileNotFoundException e) {
            System.out.println("không tìm thấy file");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file");
        } catch (Exception e) {
            System.out.println("Lỗi khác với file");
        }
    }

    // tạo ngẫu nhiên 1 câu hỏi
    public QuestionMultipleChoice randomQuestion() {
        Random rand = new Random();
        int index = rand.nextInt(listQuestion.size());
        listQuestion.get(index).toString();
        return listQuestion.get(index);
    }

    // tạo ngẫu nhiên 4 đáp án lưu vào list
    public ArrayList<String> createRandomAnswers(String answer) {
        ArrayList<String> listAnswer = new ArrayList<>();
        Random rand = new Random();
        listAnswer.add(answer);
        while (listAnswer.size() < 5) {
            int index = rand.nextInt(listQuestion.size());
            listAnswer.add(listQuestion.get(index).getAnswer());
        }
        // trộn đáp án.
        Collections.shuffle(listAnswer);
        return listAnswer;
    }

    public void start() {
        createListQuestion();
        Scanner input = new Scanner(System.in);
        int index = 1;

        while (health != 0 || point < 3) {
            QuestionMultipleChoice x = this.randomQuestion();
            x.setListAnswers(createRandomAnswers(x.getAnswer()));
            x.printQuestion(index);
            System.out.println("Your point:" + getPoint());
            System.out.println("Your Health: " + getHealth());
            index++;

            System.out.print("Your Choice [A/B/C/D]: ");
            char userAnswers = input.next().charAt(0);
            if (x.checkAnswers(userAnswers) == true) {
                System.out.println("\n\n\n\nCORRECT !");
                increasePoint();
            } else {
                System.out.println("\n\n\n\nINCORRECT !  \nCORRECT ANSWERS: " + x.getAnswer());
                decreaseHealth();
            }
        }

    }

    // public static void main(String[] args) {
    //     MultipleChoice test = new MultipleChoice();
    //     test.start();
    // }
}

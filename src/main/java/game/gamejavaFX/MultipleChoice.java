package game.gamejavaFX;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MultipleChoice extends Game{
    String path = "src/main/resources/data/game/MultipleChoiceData.txt";
    // danh sách các câu hỏi
    private ArrayList<MultipleChoiceQuestion> listQuestion = new ArrayList<>();

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
                listQuestion.add(new MultipleChoiceQuestion(question, answer));
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
    public MultipleChoiceQuestion randomQuestion() {
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


}

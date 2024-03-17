package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MatchingWord {
    private int point;
    private int health;
    private String path;

    private Hashtable<Character, ArrayList<String>> charStarts = new Hashtable<>();

    // biến này dùng để gán chữ cái cuối cùng của từ trước đó và cũng là chữ cái đầu
    // tiên của từ cần đoán.
    private char charactorKey = randomChar();

    // tạo 1 list để lưu các đáp án của người chơi đóng vai trò kiểm tra độ trùng
    // lặp của từ.
    private ArrayList<String> answerList = new ArrayList<>();

    // random ngẫu nhiên 1 chữ cái bất kỳ
    public char randomChar() {
        Random random = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int randomIndex = random.nextInt(alphabet.length());
        return alphabet.charAt(randomIndex);
    }

    public MatchingWord() {
        point = 0;
        health = 3;
        path = "src/main/resources/app/game/HangmanData.txt";
    }

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void increasePoint() {
        point++;
    }

    public void decreaseHealth() {
        health--;
    }

    // tạo tập hơp các danh sách các từ theo bảng chữ cái
    // ví dụ : a : answer, at,...
    public void WordListByFirstCharactor() {
        try {
            // đọc file
            BufferedReader bf = new BufferedReader(new FileReader(path));
            String line;
            // đọc từng dòng của file sau đó lấy ký tự đầu tiên để kiểm tra.
            while ((line = bf.readLine()) != null) {
                char firstchar = line.charAt(0);
                // nếu ký tự đầu tiên là key của hashtable thì thêm từ đó vào array list
                if (charStarts.containsKey(firstchar)) {
                    charStarts.get(firstchar).add(line);
                }
                // nếu không là key thì mình tạo thêm 1 arraylst
                // để thêm từ đó vào rồi thêm arraylist vào hashtable
                else {
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(line);
                    charStarts.put(firstchar, temp);
                }

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

    // phương thức kiểm tra xem đáp án có hợp lệ không
    public boolean checkAnswer(String s) {
        if (s.charAt(0) != charactorKey || answerList.contains(s)) {
            return false;
        } else {
            if (charStarts.containsKey(charactorKey) && charStarts.get(charactorKey).contains(s)) {
                return true;
            }
            return false;
        }
    }

    public String randomWord() {
        Random random = new Random();
        ArrayList<String> temp = charStarts.get(charactorKey);
        int randomIndex = random.nextInt(temp.size());
        return temp.get(randomIndex);
    }

    public void start() {
        // tạo các danh sách từ theo bảng chữ cái
        WordListByFirstCharactor();
        // tạo ngẫu nhiên 1 từ để bắt đầu.
        String wordStart = randomWord();
        System.out.printf("%s\n", wordStart);
        // thêm vào danh sách đáp án.
        answerList.add(wordStart);
        // lấy ký tự tiếp theo
        charactorKey = wordStart.charAt(wordStart.length() - 1);

        Scanner input = new Scanner(System.in);
        try {
            while (true) {
                System.out.printf("\n\t\t");
                String word = input.nextLine();
                word = word.toLowerCase();
                if (checkAnswer(word)) {
                    increasePoint();
                    answerList.add(word);
                    charactorKey = word.charAt(word.length() - 1);
                } else {
                    decreaseHealth();
                    System.out.printf("ERROR");
                }
                String wordContinue = randomWord();
                System.out.printf("\n%s", wordContinue);
                answerList.add(wordContinue);
                if (point == 10 || health == 0) {
                    break;
                }
            }
        } finally {
            input.close();
        }
    }

    public static void main(String[] args) {
        MatchingWord newgame = new MatchingWord();
        newgame.start();
    }
}

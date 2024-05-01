package game.gamejavaFX;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class MatchingWord extends Game {
    private String path = "src/main/resources/data/game/HangmanData.txt";

    private Hashtable<Character, ArrayList<String>> charStarts = new Hashtable<>();
    // biến này dùng để gán chữ cái cuối cùng của từ trước đó và cũng là chữ cái đầu
    // tiên của từ cần đoán.
    public char charactorKey = 'a';
    // tạo 1 list để lưu các đáp án của người chơi đóng vai trò kiểm tra độ trùng
    // lặp của từ.
    public ArrayList<String> answerList = new ArrayList<>();


    // random ngẫu nhiên 1 chữ cái bất kỳ
    public char randomChar() {
        Random random = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int randomIndex = random.nextInt(alphabet.length());
        return alphabet.charAt(randomIndex);
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
                // nếu không là key thì mình tạo thêm 1 arraylist
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
            if (charStarts.get(charactorKey).contains(s)) {
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

}

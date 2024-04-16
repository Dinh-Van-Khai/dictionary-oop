package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Hangman implements Game {
    private int health;
    private String path;
    private ArrayList<String> listDictionary = new ArrayList<>();

    public Hangman() {
        health = 3;
        path = "src/main/resources/app/game/HangmanData.txt";
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void decreaseHealth() {
        health--;
    }

    public void increaseHealth() {
        health++;
    }

    // tạo 1 list các từ lấy từ file
    public void createListDictionary() {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bf.readLine()) != null) {
                listDictionary.add(line);
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

    // tạo ngẫu nhiên 1 từ trong list từ.
    public String randomWord() {
        Random rand = new Random();
        int randIndex = rand.nextInt(listDictionary.size());
        return listDictionary.get(randIndex);
    }

    // tạo ngẫu nhiên n số nguyên khác nhau trong khoảng [0,m]
    public static Set<Integer> randomNumber(int n, int m) {
        Set<Integer> uniqueValues = new HashSet<>();
        Random random = new Random();

        while (uniqueValues.size() < n) {
            int randomNumber = random.nextInt(m + 1);
            uniqueValues.add(randomNumber);
        }

        return uniqueValues;
    }

    public void start() {
        createListDictionary();
        String word = randomWord();
        // System.out.println("Answer: " + word);
        /*
         * answerList lưu các ký tự đúng.
         * guessList lưu các ký tự cần đoán
         * checkDuplicateChar dùng để lưu các ký tự đã đoán đúng.
         */
        ArrayList<Character> answerList = new ArrayList<>(word.length());
        ArrayList<Character> guessList = new ArrayList<>(word.length());
        ArrayList<Character> checkDuplicateChar = new ArrayList<>();

        // tạo 1 hashset để lưu các index được random để gợi ý ký tự.
        Set<Integer> indexSuggesstion = randomNumber(word.length() / 2, word.length() - 1);

        for (int i = 0; i < word.length(); i++) {
            answerList.add(word.charAt(i));
            guessList.add('_');
        }
        // gợi ý các ký tự
        for (Integer index : indexSuggesstion) {
            char temp = word.charAt(index);
            guessList.set(index, temp);
            if (!checkDuplicateChar.contains(temp)) {
                checkDuplicateChar.add(temp);
            }
        }
        Scanner input = new Scanner(System.in);

        // vòng lặp kết thúc khi guessList không còn ký tự "_" nữa hoặc health = 0
        while ((guessList.contains('_')) && health > 0) {
            System.out.print("Word: ");
            for (int i = 0; i < guessList.size(); i++) {
                System.out.print(guessList.get(i));
            }
            System.out.printf("\nHealth: %d\n", getHealth());
            System.out.print("Enter 1 character: ");
            char inputChar = input.next().charAt(0);

            if (answerList.contains(inputChar)) {
                if (!checkDuplicateChar.contains(inputChar)) {
                    checkDuplicateChar.add(inputChar);
                    for (int i = 0; i < answerList.size(); i++) {
                        if (answerList.get(i) == inputChar) {
                            guessList.set(i, inputChar);
                            increaseHealth();
                        }
                    }
                } else {
                    decreaseHealth();
                }

            } else {
                decreaseHealth();
            }
        }
        if (getHealth() == 0) {
            System.out.println("You lose. Answer: " + word);
        } else {
            System.out.println("You have won. Answer: " + word);
        }

    }

    // public static void main(String[] args) {
    // Hangman test = new Hangman();
    // test.start();
    // }

}

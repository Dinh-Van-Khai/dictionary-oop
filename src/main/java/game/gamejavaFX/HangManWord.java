package game.gamejavaFX;

import java.util.*;

public class HangManWord {
    public String word;
    public boolean[] list;
    public List<Character> listChar = new ArrayList<>();
    private int charCorrect =0;

    public HangManWord(String word) {
        this.word = word;
        list = new boolean[word.length()];
        for (int i = 0; i < word.length(); i++) {
            listChar.add(word.charAt(i));
        }
        this.charCorrect = word.length()/2;

        int len = word.length();
        // tạo 1 hashset để lưu các index được random để gợi ý ký tự.
        Set<Integer> indexSuggesstion = randomNumber(len / 2, len - 1);

        // gợi ý các ký tự
        for (Integer index : indexSuggesstion) {
            list[index] = true;
        }
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

    public String randGuessWord() {
        StringBuilder guessWord = new StringBuilder();
        int len = word.length();

        for (int i = 0; i < len; i++) {
            if (list[i]) {
                guessWord.append(word.charAt(i));
            } else {
                guessWord.append("_ ");
            }
        }

        return guessWord.toString();
    }

    public boolean checkAnswers(char answers) {
        answers = Character.toLowerCase(answers);
        boolean check =false;
        for (int i = 0; i < listChar.size(); i++) {
            if (answers == listChar.get(i) && !list[i]) {
                list[i] = true;
                charCorrect++;
                check = true;
                }
        }
        return check;
    }

    public boolean completedWord() {
        return charCorrect == word.length();
    }
}

package dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class DictionaryManagement {
  private final Dictionary dictionary = new Dictionary();

  /**
   * Constructor initialized without parameters.
   */
  public DictionaryManagement(){
  }

  /**
   * Getter.
   *
   * @return dictionary
   */
  public Dictionary getDictionary() {
    return dictionary;
  }

  /**
   * Insert from commandline Enter num of Word and record your Words.
   */
  public void insertFromCommandline() {
    System.out.println("Enter the number of words you want records:\t");
    Scanner sn = new Scanner(System.in);
    // Convert string to int from commandline
    int numOfWords = Integer.parseInt(sn.nextLine());

    // Insert target and explain word
    for (int i = 0; i < numOfWords; i++) {
      System.out.println("Insert English word:\t");
      String EngWord = sn.nextLine();
      System.out.println("Insert Vietnamese word:\t");
      String VnWord = sn.nextLine();
      Word newWord = new Word(EngWord, VnWord);

      // put into the dictionary TreeMap<String , Word>
      dictionary.put(EngWord, newWord);
    }

    sn.close();
  }

  /**
   * Insert into the dictionary all the words in the file. Each line contains English words and
   * Vietnamese explanations (separated by tab signs).
   *
   * @param fileName full path to direct to dictionaries.txt
   */
  public void insertFromFile(String fileName) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(fileName));

      String line;
      while ((line = br.readLine()) != null) {
        String[] anh_viet = line.split("\t");
        Word word = new Word(anh_viet[0], anh_viet[1]);
        dictionary.put(anh_viet[0], word);
      }
      br.close();
    } catch (FileNotFoundException e) {
      System.out.println("Duc dep trai vai loz 1");
      System.out.println("Error open file");
    } catch (IOException e) {
      System.out.println("Duc dep trai vai loz 2");
      System.out.println("Error read file");
    } catch (Exception e) {
      System.out.println("Duc dep trai vai loz 3");
      System.out.println("Error");
    }
  }

  /**
   * Here we have a special file with following content:
   *
   * @param fileName full path direct to file special file dictionaries
   * |'chutist
   *  *danh từ
   * - (vt của parachutist) lính nhảy dù
   * |'d
   * - (thông tục)
   * - (viết tắt) của had,  should,  would
   * |'em
   * *danh từ
   * - (thông tục)
   *  (viết tắt) của them
   * |'gainst
   * *giới từ
   * - (thơ ca) (như) against
   * |'ll
   * - (vt của will)
   */
  public void insertFromFile_specialWord(String fileName) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(fileName));

      String line;
      String EngWord = "";
      StringBuilder meaningBuilder = new StringBuilder();

      while ((line = br.readLine()) != null) {
        if (line.startsWith("|")) {
          if (!EngWord.isEmpty()) {
            Word word = new Word();
            word.setWord_target(EngWord.trim());
            word.setWord_explain(meaningBuilder.toString().trim());
            dictionary.put(EngWord, word);

            // Reset values for new words and new meanings
            EngWord = "";
            meaningBuilder = new StringBuilder();
          }
          // Handle next Word
          EngWord = line.replace("|", "").trim();
        } else {
          // Handle VN word
          meaningBuilder.append(line).append("\n");
        }
      }

      // Handle the last word
      if (!EngWord.isEmpty()) {
        Word word = new Word();
        word.setWord_target(EngWord.trim());
        word.setWord_explain(meaningBuilder.toString().trim());
        dictionary.put(EngWord, word);
      }

      br.close();

    }
    // In out exception with file
    catch (IOException e) {
      System.out.println("Duc dep trai vai loz 1");
      System.out.println("An error occurred with file: " + e);
    } catch (Exception e) {
      System.out.println("Duc dep trai vai loz 2");
      System.out.println("Something went wrong: " + e);
    }
  }

  /**
   * Search target word by Go through all target words of the dictionary and check if they are equal
   * However, this method of checking is still very slow and resource-consuming, and does not
   * utilize the core of TreeMap.
   *
   * @param word_target the word need to find
   */
  public void dictionaryLookup(String word_target) {
    System.out.println("Table of the results:");
    System.out.printf("%-12s | %-35s | %-35s\n", "No.", "English", "Vietnamese");
    int numOfResults = 0;
    for (Word word : dictionary.values()) {
      if (word.getWord_target().equals(word_target)) {
        numOfResults++;
        System.out.printf("%-12s | %-35s | %-35s\n", numOfResults, word.getWord_target(),
            word.getWord_explain());
      }
    }
    if (numOfResults == 0) {
      System.out.println("404. Word Not Found !!!");
    }
  }

  /**
   * dictionarySearcher() function has function Search for words.
   * For example, enter: tra
   * Returned result: list of vocabulary words starting with “tra”: transport, translate,
   * transform, transit, ...
   *
   * @param key keyword for search
   */
  public void dictionarySearch(String key) {
    Set<String> keySet = dictionary.keySet();
    int len_key = key.length();
    boolean check = false;
    for (String word : keySet) {
      int len_word = word.length();
      if (len_word < len_key)
        continue;
      else {
        String tmp = word.substring(0, len_key);
        if (tmp.compareTo(key) == 0) {
          check = true;
          System.out.println(word);
        }
      }
    }
    if (!check) {
      System.out.println("404. Word Not Found !!!");
    }
  }

  /**
   * We will import the dictionary to file, each line contains 1 eng word and 1 vn word separated by
   * tab.
   *
   * @param fileName full path direct to file needed to write
   */
  public void dictionaryExportToFile(String fileName) {
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

      for (Word word : dictionary.values()) {
        bw.write(word.getWord_target() + "\t" + word.getWord_explain());
        bw.newLine();
      }
      bw.close();
    } catch (IOException e) {
      System.out.println("An error occurred with file: " + e);
    } catch (Exception e) {
      System.out.println("Something went wrong: " + e);
    }
  }

  /**
   * Method to remove eng word and its meaning from dictionaries.
   *
   * @param word_target eng word need to remove
   */
  public void dictionaryRemoveWord(String word_target) {
    if (dictionary.containsKey(word_target)) {
      dictionary.remove(word_target);
      System.out.println("Deleted successfully !!!");
    } else {
      System.out.println("404. Word not found !!!");
    }
  }

  /**
   * Method to add new word with its meaning to dictionary.
   *
   * @param newWord (eng word, vn word)
   */
  public void dictionaryAddWord(Word newWord) {
    if (!dictionary.containsKey(newWord.getWord_target())) {
      dictionary.put(newWord.getWord_target(), newWord);
      System.out.println("Add word successfully !!!");
    } else {
      System.out.println("The word was existed in dictionary !!!");
    }
  }

  /**
   * Method to update new meaning of target word.
   *
   * @param newMeaning_word (target_word, new_meaning)
   */
  public void dictionaryUpdateWord(Word newMeaning_word) {
    if (dictionary.containsKey(newMeaning_word.getWord_target())) {
      dictionary.replace(newMeaning_word.getWord_target(), newMeaning_word);
      System.out.println("Update successfully !!!");
    } else {
      System.out.println("Can not find target word !!!");
    }
  }
}

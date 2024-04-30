package dictionary;

import java.util.Scanner;

import game.gameCommandLine.GameManagement;

public class DictionaryCommandLine {

  private final DictionaryManagement dictionaryManagement = new DictionaryManagement();
  private final GameManagement gameManagement = new GameManagement();

  public DictionaryCommandLine() {
  }

  public void showAllWords() {
    System.out.println("All words of THE DICTIONARY:");
    System.out.printf("%-10s | %-32s | %-32s\n", "No.", "English word", "Vietnamese word");

    int flag = 1;
    for (Word word : dictionaryManagement.getDictionary().values()) {
      System.out.printf("%-10s | %-32s | %-32s\n", flag, word.getWord_target(),
          word.getWord_explain());
      flag++;
    }
  }


  public void dictionaryBasic() {
    dictionaryManagement.insertFromCommandline();
    dictionaryManagement.dictionaryExportToFile("Cak");
    showAllWords();
  }

  public void dictionaryAdvanced() {
    System.out.println("Welcome to My Application!");
    Scanner sn = new Scanner(System.in);
    while (true) {
      System.out.println("[0]\tExit");
      System.out.println("[1]\tAdd");
      System.out.println("[2]\tRemove");
      System.out.println("[3]\tUpdate");
      System.out.println("[4]\tDisplay");
      System.out.println("[5]\tLookup");
      System.out.println("[6]\tSearch");
      System.out.println("[7]\tGame");
      System.out.println("[8]\tImport from file");
      System.out.println("[9]\tExport to file");
      System.out.print("Your action: ");

      String numOfChoose = sn.nextLine();
      switch (numOfChoose) {
        case "0":
          sn.close();
          return;

        case "1":
          Word newWord = new Word();
          System.out.println("ENTER YOUR ENGLISH WORD:");
          newWord.setWord_target(sn.nextLine());
          System.out.println("ENTER MEANING WORD:");
          newWord.setWord_explain(sn.nextLine());
          dictionaryManagement.dictionaryAddWord(newWord);
          break;

        case "2":
          System.out.println("ENTER THE ENGLISH WORD WHICH YOU WANNA REMOVE:");
          dictionaryManagement.dictionaryRemoveWord(sn.nextLine());
          break;

        case "3":
          Word updateWord = new Word();
          System.out.println("ENTER YOUR ENGLISH WORD:");
          updateWord.setWord_target(sn.nextLine());
          System.out.println("ENTER NEW MEANING WORD:");
          updateWord.setWord_explain(sn.nextLine());
          dictionaryManagement.dictionaryUpdateWord(updateWord);
          break;

        case "4":
          showAllWords();
          break;

        case "5":
          System.out.print("Enter your English word which you need to explain: ");
          String wordLookup = sn.nextLine();
          dictionaryManagement.dictionaryLookup(wordLookup);
          break;

        case "6":
          System.out.print("Enter your English word which you need to search: ");
          String wordSearch = sn.nextLine();
          dictionaryManagement.dictionarySearch(wordSearch);
          break;

        case "7":
          gameManagement.chooseGame();
          gameManagement.startGame();
          break;

        case "8":
          dictionaryManagement.insertFromFile("cak");
          break;

        case "9":
          dictionaryManagement.dictionaryExportToFile("cak");
          break;

        default:
          System.out.println("Action not supported.");
      }
        System.out.println("\n=============================================================\n");
    }
  }
}


package game.gameCommandLine;

import java.util.Scanner;

public class GameManagement {
    private Game game;

    public void chooseGame() {
        System.out.println("Choosing game");
        System.out.println("[0]\tBack");
        System.out.println("[1]\tHangman");
        System.out.println("[2]\tMatching Word");
        System.out.println("[3]\tMultiple Choice");
        System.out.print("Your action: ");
        
        Scanner sn = new Scanner(System.in);
        String numOfChoose = sn.nextLine();
        switch (numOfChoose) {
        case "0":
            return;
        case "1":
            game = new Hangman();
            break;
        case "2":
            game = new MatchingWord();
            break;
        case "3":
            game = new MultipleChoice();
            break;
        
        default:
            System.out.println("Action not supported.");
        }
    }

    public void startGame() {
        if(game != null) game.start();
    }
}

package Javaprojectcards;

import java.util.Scanner;

public class Menu {

    public static void greetings() {
        System.out.println("Hello, lets play a game of Royal Capture!");
        System.out.println("Please enter a command to continue:");
    }

    public static void startMenu() {

        System.out.println("[S]tart , [R]ules, [E]xit");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine().toLowerCase();
        switch (command) {
            case "s":
                Engine engine = new Engine();
                engine.gameEngine();
                break;
            case "r":
                Menu.Rules();
                Menu.startMenu();
                break;
            case "e":
                System.exit(0);
                break;
            default:
                System.out.println("Wrong command!");
                System.out.println("Please enter a correct command to continue:");
                Menu.startMenu();
                break;
        }
    }

    public static void playAgain() {
        System.out.println("Do you want to play again? - [Y]es/[N]o");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine().toLowerCase();
        if (command.equals("y")) {
            Engine engine = new Engine();
            engine.gameEngine();
        } else if (command.equals("n")){
            System.out.println("Bye!");
            System.exit(0);
        } else {
            System.out.println("Wrong command!");
            System.out.println("Please enter a correct command to continue:");
            Menu.playAgain();
        }
    }

    private static void Rules() {
        System.out.println("""
                It is played with 16 cards - ace, king, queen, and jack from each suit.
                Each player starts with 5 random cards. Players take turns to play a card.
                The player who plays a card with the higher suit wins the round. If the
                cards have the same suit, the higher card wins. The winning player takes both cards.
                The computer player starts first in the first round, than the player who has won the previous round starts first.
                After five turns, the players count their points according to the following scheme:

                For each card, the points are the rank of the suit multiplied by the rank of the card.
                The ranks of the suits are:
                Clubs - 1
                Diamonds - 2
                Hearts - 3
                Spades - 4
                The ranks of the cards are:
                Ace - 11
                King - 10
                Queen - 9
                Jack - 8.""");

    }
}

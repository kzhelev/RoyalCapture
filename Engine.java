package Javaprojectcards;

import java.util.Random;
import java.util.Scanner;

public class Engine {

    private static Deck computerWiningHands;
    private static Deck humanWiningHands;

    public Engine() {
        computerWiningHands = new Deck();
        humanWiningHands = new Deck();
    }

    public void gameEngine() {

        //create and fill the starting deck

        Deck startingDeck = new Deck();
        startingDeck.fillUp();
        startingDeck.shuffleDeck();

        //first player draws 5 cards of the starting deck
        Deck computersDeck = startingDeck.firstPlayerCardsDraw();

        //second player draws 5 cards of the starting deck
        Deck humansPlayerDeck = startingDeck.secondPlayerCardsDraw();

        Random randomInt = new Random();

        Random intForTurn = new Random();

        boolean computerWon = true;

        //draws a number between 1 and 2 to determine who starts tha game
        int forTurn = intForTurn.nextInt(1, 3);

        //Round one:
        if (forTurn == 1) {

            System.out.println("Computer will be first!");

            //the computer draws and plays a random card from its deck
            Deck.Card cardComp = computerRandomDraw(randomInt, computersDeck);
            computerGaveMessage(cardComp);


            humansPlayerDeck.printDeck();
            System.out.println("Choose a card:");

            //the human player picks a card and plays it
            Deck.Card cardHuman = drawCard(humansPlayerDeck);
            humanGaveMessage(cardHuman);

            computerWon = roundWinner(cardComp, cardHuman);

        } else {

            System.out.println("You will be first!");

            humansPlayerDeck.printDeck();
            System.out.println("Choose a card:");

            //the human player picks a card and plays it
            Deck.Card cardHuman = drawCard(humansPlayerDeck);
            humanGaveMessage(cardHuman);

            //the computer checks if there is a stronger card in its deck and if it finds one it plays it.
            // In case there is no stronger card in its deck, the computer plays a randomly selected card.
            Deck.Card cardComp = computerSmartDraw(computersDeck, randomInt, cardHuman);
            computerGaveMessage(cardComp);

            //checks the result of the round and returns true or false
            computerWon = roundWinner(cardComp, cardHuman);
        }

        //Rounds form 2 to 5:
        for (int i = 0; i < 4; i++) {
            if (computerWon) {

                System.out.println("Computer will be first this round!");

                Deck.Card cardComp = computerRandomDraw(randomInt, computersDeck);
                computerGaveMessage(cardComp);

                humansPlayerDeck.printDeck();
                System.out.println("Choose a card:");

                Deck.Card cardHuman = drawCard(humansPlayerDeck);
                humanGaveMessage(cardHuman);

                computerWon = roundWinner(cardComp, cardHuman);

            } else {

                System.out.println("You will be first this round!");

                humansPlayerDeck.printDeck();
                System.out.println("Choose a card:");

                Deck.Card cardHuman = drawCard(humansPlayerDeck);
                humanGaveMessage(cardHuman);

                Deck.Card cardComp = computerSmartDraw(computersDeck, randomInt, cardHuman);
                computerGaveMessage(cardComp);

                computerWon = roundWinner(cardComp, cardHuman);
            }
        }

        //Calculation of the result:
        int computersPoints = countComputersPoints();

        int humansPoints = countHumansPoints();

        gameResult(computersPoints, humansPoints);

        Menu.playAgain();
    }

    private static void gameResult(int computersPoints, int humansPoints) {
        if (computersPoints > humansPoints) {
            System.out.println("");
            System.out.println("Game over! You loose!");
        } else if (computersPoints < humansPoints) {
            System.out.println("");
            System.out.println("Congratulations! You win!");
        } else {
            System.out.println("");
            System.out.println("Draw!");
        }
    }

    private static void humanGaveMessage(Deck.Card cardHuman) {
        System.out.println("You gave: " + cardHuman.getRank() + " of " + cardHuman.getColor());
    }

    private static void computerGaveMessage(Deck.Card cardComp) {
        System.out.println("Computer gave: " + cardComp.getRank() + " of " + cardComp.getColor());
    }

    private static int countHumansPoints() {

        int humansPoints = 0;

        for (int i = 0; i < humanWiningHands.getDeck().size(); i++) {
            humansPoints += humanWiningHands.getDeck().get(i).getCardRank();
        }

        return humansPoints;
    }

    private static int countComputersPoints() {

        int computersPoints = 0;

        for (int i = 0; i < computerWiningHands.getDeck().size(); i++) {
            computersPoints += computerWiningHands.getDeck().get(i).getCardRank();
        }

        return computersPoints;
    }

    private static boolean roundWinner(Deck.Card cardComp, Deck.Card cardHuman) {

        boolean computerWins;
        int win = Deck.Card.compareTwoCards(cardComp, cardHuman);

        if (win == 1) {
            System.out.println("Computer wins this round!");
            System.out.print(System.lineSeparator());

            computerWiningHands.addCard(cardComp);
            computerWiningHands.addCard(cardHuman);
            computerWins = true;
        } else {
            System.out.println("You win this round!");
            System.out.print(System.lineSeparator());

            humanWiningHands.addCard(cardComp);
            humanWiningHands.addCard(cardHuman);
            computerWins = false;
        }

        return computerWins;
    }

    private static Deck.Card computerSmartDraw(Deck computersDeck, Random randomInt, Deck.Card cardHuman) {
        int compCardIndex = 0;
        boolean computerHasABiggerCard = false;

        for (int i = 0; i < computersDeck.getDeck().size(); i++) {

            if (cardHuman.getRankNumber() < computersDeck.getDeck().get(i).getRankNumber()) {
                compCardIndex = i;
                computerHasABiggerCard = true;
                break;
            } else if (cardHuman.getRankNumber() == computersDeck.getDeck().get(i).getRankNumber()) {

                if (cardHuman.getCardRank() < computersDeck.getDeck().get(i).getColorRank()) {
                    compCardIndex = i;
                    computerHasABiggerCard = true;
                    break;
                }

            }

        }

        if (!computerHasABiggerCard) {

            compCardIndex = randomInt.nextInt(0, computersDeck.getDeck().size());
        }

        Deck.Card cardComp = computersDeck.cardDraw(compCardIndex);
        return cardComp;
    }

    private static Deck.Card drawCard(Deck humansPlayerDeck) {

        Scanner scanner = new Scanner(System.in);

        String input;

        int maxIndex = humansPlayerDeck.getDeck().size()-1;

        do {
            input = scanner.nextLine();
            if (!input.matches("[0-"+maxIndex+"]")) {
                System.out.println("Invalid card index! Try again:");
            }
        } while (!input.matches("[0-"+maxIndex+"]"));

        int index = Integer.parseInt(input);

        Deck.Card cardHuman = humansPlayerDeck.cardDraw(index);

        return cardHuman;
    }

    private static Deck.Card computerRandomDraw(Random randomInt, Deck computersDeck) {

        int indexComp = randomInt.nextInt(0, computersDeck.getDeck().size());
        Deck.Card cardComp = computersDeck.cardDraw(indexComp);

        return cardComp;
    }

}


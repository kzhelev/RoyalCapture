package Javaprojectcards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {

    private List<Card> deck = new ArrayList<>();

    public List<Card> getDeck() {
        return deck;
    }

    public void addCard(Card card) {
        this.deck.add(card);
    }

    public void printDeck() {
        System.out.print(System.lineSeparator());
        for (int i = 0; i < this.deck.size(); i++) {
            System.out.println("["+i+"] "+this.deck.get(i).rank +" of "+this.deck.get(i).color);
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(this.deck);
    }

    public Deck firstPlayerCardsDraw() {
        Random number = new Random();
        int randomInt;
        Deck firstPlayerDeck = new Deck();
        int counter = 15;
        for (int i = 0; i < 5; i++) {
            randomInt = number.nextInt(0,counter);
            counter--;
            firstPlayerDeck.addCard(this.deck.get(randomInt));
            this.deck.remove(randomInt);
            counter--;
        }
        return firstPlayerDeck;
    }

    public Deck secondPlayerCardsDraw() {
        Random number = new Random();
        int randomInt;
        int counter = 10;
        Deck secondPlayerDeck = new Deck();
        for (int i = 0; i < 5; i++) {
            randomInt = number.nextInt(0,counter);
            secondPlayerDeck.addCard(this.deck.get(randomInt));
            this.deck.remove(randomInt);
            counter--;
        }
        return secondPlayerDeck;
    }

    public Card cardDraw(int index) {
        Card card = this.deck.get(index);
        this.deck.remove(index);
        return card;
    }

    public void fillUp() {
        String[] cardsColors = new String[]{"clubs", "diamonds", "hearts", "spades"};
        String[] cardsRanks = new String[]{"Ace", "King", "Queen", "Jack"};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Deck.Card card = new Deck.Card(cardsRanks[i], cardsColors[j]);
                addCard(card);
            }
        }
    }

    public static class Card {
        private String rank;
        private int rankNumber;
        private int colorRank;
        private String color;
        private int cardRank;

        public Card(String rank, String color) {
            this.rank = rank;
            switch (this.rank) {
                case "Ace":
                    this.rankNumber = 11;
                    break;
                case "King":
                    this.rankNumber = 10;
                    break;
                case "Queen":
                    this.rankNumber = 9;
                    break;
                case "Jack":
                    this.rankNumber = 8;
                    break;
            }
            this.color = color;
            switch (this.color) {
                case "clubs":
                    this.colorRank = 1;
                    break;
                case "diamonds":
                    this.colorRank = 2;
                    break;
                case "hearts":
                    this.colorRank = 3;
                    break;
                case "spades":
                    this.colorRank = 4;
                    break;
            }
            this.cardRank = this.rankNumber*this.colorRank;
        }

        public String getRank() {
            return rank;
        }

        public int getRankNumber() {
            return rankNumber;
        }

        public int getColorRank() {
            return colorRank;
        }

        public String getColor() {
            return color;
        }

        public int getCardRank() {
            return cardRank;
        }

        public static Integer compareTwoCards(Card card1, Card card2) {
            if (card1.rankNumber > card2.rankNumber) {
                return 1;
            } else if (card1.rankNumber < card2.rankNumber){
                return -1;
            } else {
                if (card1.colorRank > card2.colorRank) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }

        public void show() {
            System.out.println("Car: " + this.rank + " of " + this.color);
        }
    }
}

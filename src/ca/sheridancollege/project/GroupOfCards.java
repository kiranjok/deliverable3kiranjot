package ca.sheridancollege.project;

import java.util.Collections;
import java.util.Stack;

public class GroupOfCards {
    private final Stack<Card> cards = new Stack<>();
    private final int size;

    public GroupOfCards(int size) {
        this.size = size;
        // Initialization logic for the cards
        initializeDeck();
    }

    private void initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
        
        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                cards.push(new Card(suit, ranks[i], values[i]) {
                    @Override
                    public String toString() {
                        return getRank() + " of " + getSuit();
                    }
                });
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        return cards.pop();
    }

    public int getSize() {
        return size;
    }
}

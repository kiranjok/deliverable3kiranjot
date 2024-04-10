package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// This abstract class represents the generic structure of any game.
public abstract class Game {
    private final ArrayList<Player> players = new ArrayList<>();
    protected Scanner inputScanner = new Scanner(System.in);

    public Game(String name) {
        // Constructor code, if needed, can go here
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Scanner getInputScanner() {
        return inputScanner;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void registerPlayer() {
        System.out.print("Enter player name: ");
        String playerName = inputScanner.nextLine();
        addPlayer(new BlackjackPlayer(playerName));
    }

    public void endGame() {
        inputScanner.close();
    }

    public abstract void play();
    public abstract void declareWinner();
}

class BlackjackGame extends Game {
    private final GroupOfCards deck;

    public BlackjackGame(String name) {
        super(name);
        this.deck = new GroupOfCards(52);
    }

    @Override
    public void play() {
        for (Player player : getPlayers()) {
            player.addCard(deck.dealCard());
            player.addCard(deck.dealCard());
            ((BlackjackPlayer) player).play(inputScanner, deck);
        }
        declareWinner();
    }

    @Override
    public void declareWinner() {
        int winningScore = 0;
        Player winner = null;
        for (Player player : getPlayers()) {
            if (!player.isBusted() && player.getScore() > winningScore && player.getScore() <= 21) {
                winningScore = player.getScore();
                winner = player;
            }
        }
        if (winner != null) {
            System.out.println(winner.getName() + " wins with a score of " + winningScore);
        } else {
            System.out.println("No winners, everyone busted!");
        }
    }
}

abstract class Player {
    protected final String name;
    protected final List<Card> hand = new ArrayList<>();
    protected int score;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        hand.add(card);
        score += card.getValue();
    }

    public int getScore() {
        return score;
    }

    public boolean isBusted() {
        return score > 21;
    }

    public List<Card> getHand() {
        return hand;
    }

    public abstract void play(Scanner scanner, GroupOfCards deck);
}

class BlackjackPlayer extends Player {

    public BlackjackPlayer(String name) {
        super(name);
    }

    @Override
    public void play(Scanner scanner, GroupOfCards deck) {
        boolean isTurnOver = false;
        while (!isTurnOver && !isBusted()) {
            System.out.println(getName() + ", your hand: " + getHand());
            System.out.println("Your score: " + getScore());
            System.out.print("Hit or Stand? (h/s): ");
            String decision = scanner.nextLine();

            if ("h".equalsIgnoreCase(decision)) {
                Card newCard = deck.dealCard();
                addCard(newCard);
                System.out.println("You drew a " + newCard);
                if (getScore() > 21) {
                    System.out.println(getName() + " is bust!");
                    isTurnOver = true;
                }
            } else if ("s".equalsIgnoreCase(decision)) {
                isTurnOver = true;
            } else {
                System.out.println("Invalid input. Please type 'h' for hit or 's' for stand.");
            }
        }
    }

    @Override
    public void addCard(Card card) {
        super.addCard(card);
        if (card.getRank().equals("Ace") && score > 21) {
            score -= 10; // If an Ace is drawn and score goes over 21, count Ace as 1 instead of 11
        }
    }
}

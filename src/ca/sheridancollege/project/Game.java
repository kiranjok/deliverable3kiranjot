package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Game {
    private final ArrayList<Player> players = new ArrayList<>();
    protected Scanner inputScanner = new Scanner(System.in);

    public Game(String name) {
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
        this.deck = new GroupOfCards(52); // A deck of 52 cards
    }

    @Override
    public void play() {
        for (Player player : getPlayers()) {
            player.addCard(deck.dealCard());
            player.addCard(deck.dealCard());
            player.play(super.inputScanner); // Pass the scanner for user input
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
            System.out.println("It's a tie or everyone busted!");
        }
    }
    
}

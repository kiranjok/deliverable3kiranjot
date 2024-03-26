package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Player {
    private final String name;
    private final List<Card> hand = new ArrayList<>();
    private int score;

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
        return getScore() > 21;
    }

    public void play(Scanner scanner) {
    }

    public abstract void declareWinner();

        public List<Card> getHand() {
            return hand; 
        }
        public abstract class Game {
        
            private final Scanner inputScanner = new Scanner(System.in);
        
        
            public Scanner getInputScanner() {
                return inputScanner;
            }
        
        }
        
            private final Scanner inputScanner = new Scanner(System.in);
        
            // Getter method for inputScanner
            public Scanner getInputScanner() {
                return inputScanner;
            }
                }
        
                
    
    


class BlackjackPlayer extends Player {

    public BlackjackPlayer(String name) {
        super(name);

    
    }

    @Override
    public void play(Scanner scanner) {
        while (true) {
            System.out.println(getName() + ", your hand: " + getHand());
            System.out.println("Your score: " + getScore());
            System.out.print("Hit or Stand? (h/s): ");
            String decision = scanner.nextLine();

            if ("h".equalsIgnoreCase(decision)) {
                // Hit logic
                // The card dealing logic would go here
            } else if ("s".equalsIgnoreCase(decision)) {
                // Stand logic
                break;
            } else {

                System.out.println("Invalid input. Please type 'h' for hit or 's' for stand.");
            }
        }
    }

    @Override
    public void declareWinner() {
        // This will be handled in the Game class for determining the winner
    }

    @Override
    public List<Card> getHand() {
        return super.getHand();
    }

    @Override
    public boolean isBusted() {
        return getScore() > 21;
    }


}

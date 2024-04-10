package ca.sheridancollege.project;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame("Blackjack");

        System.out.println("Welcome to Blackjack!");
        System.out.print("Please enter the number of players: ");

        Scanner scanner = game.getInputScanner();
        int playerCount = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left by nextInt()

        for (int i = 0; i < playerCount; i++) {
            game.registerPlayer();
        }

        game.play();

        game.endGame();
    }
}
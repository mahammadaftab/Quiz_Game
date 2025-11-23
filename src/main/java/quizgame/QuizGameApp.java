package quizgame;

import java.util.Scanner;

public class QuizGameApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        
        System.out.println("Welcome to the Interactive Java Quiz Game!");
        
        while (playAgain) {
            QuizManager quizManager = new QuizManager(scanner);
            quizManager.startQuiz();
            
            // Ask if user wants to play again
            System.out.print("\nWould you like to play again? (y/n): ");
            String response = scanner.nextLine().trim().toLowerCase();
            playAgain = response.equals("y") || response.equals("yes");
        }
        
        System.out.println("\nThank you for playing! Goodbye!");
        scanner.close();
    }
}
package quizgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QuizManager {
    private List<Question> questionBank;
    private List<Question> currentQuizQuestions;
    private int score;
    private int totalQuestions;
    private Scanner scanner;
    
    public QuizManager(Scanner scanner) {
        this.questionBank = new ArrayList<>();
        this.currentQuizQuestions = new ArrayList<>();
        this.score = 0;
        this.totalQuestions = 0;
        this.scanner = scanner;
        initializeQuestionBank();
    }
    
    private void initializeQuestionBank() {
        // Adding 15+ questions with different difficulty levels
        
        // Easy Questions
        List<String> options1 = new ArrayList<>();
        options1.add("Guido van Rossum");
        options1.add("James Gosling");
        options1.add("Dennis Ritchie");
        options1.add("Bjarne Stroustrup");
        questionBank.add(new Question("Who created Python?", options1, 1, "Easy", 15));
        
        List<String> options2 = new ArrayList<>();
        options2.add("int");
        options2.add("float");
        options2.add("boolean");
        options2.add("char");
        questionBank.add(new Question("Which data type stores true/false values?", options2, 3, "Easy", 10));
        
        List<String> options3 = new ArrayList<>();
        options3.add("JVM");
        options3.add("JDK");
        options3.add("JRE");
        options3.add("JIT");
        questionBank.add(new Question("What does JVM stand for?", options3, 1, "Easy", 12));
        
        List<String> options4 = new ArrayList<>();
        options4.add("print()");
        options4.add("echo()");
        options4.add("output()");
        options4.add("console.log()");
        questionBank.add(new Question("Which function is used to output text in Python?", options4, 1, "Easy", 10));
        
        List<String> options5 = new ArrayList<>();
        options5.add("List");
        options5.add("Tuple");
        options5.add("Dictionary");
        options5.add("Set");
        questionBank.add(new Question("Which Python collection is ordered and changeable?", options5, 1, "Easy", 12));
        
        // Medium Questions
        List<String> options6 = new ArrayList<>();
        options6.add("Stack");
        options6.add("Queue");
        options6.add("Tree");
        options6.add("Graph");
        questionBank.add(new Question("Which data structure uses LIFO principle?", options6, 1, "Medium", 15));
        
        List<String> options7 = new ArrayList<>();
        options7.add("O(1)");
        options7.add("O(n)");
        options7.add("O(log n)");
        options7.add("O(n^2)");
        questionBank.add(new Question("What is the time complexity of binary search?", options7, 3, "Medium", 20));
        
        List<String> options8 = new ArrayList<>();
        options8.add("Inheritance");
        options8.add("Encapsulation");
        options8.add("Polymorphism");
        options8.add("Abstraction");
        questionBank.add(new Question("Which OOP concept hides internal details?", options8, 2, "Medium", 18));
        
        List<String> options9 = new ArrayList<>();
        options9.add("HTTP");
        options9.add("FTP");
        options9.add("TCP");
        options9.add("UDP");
        questionBank.add(new Question("Which protocol is used for web browsing?", options9, 1, "Medium", 15));
        
        List<String> options10 = new ArrayList<>();
        options10.add("RAM");
        options10.add("ROM");
        options10.add("Cache");
        options10.add("Hard Disk");
        questionBank.add(new Question("Which memory is the fastest?", options10, 3, "Medium", 12));
        
        // Hard Questions
        List<String> options11 = new ArrayList<>();
        options11.add("Depth-First Search");
        options11.add("Breadth-First Search");
        options11.add("A* Algorithm");
        options11.add("Dijkstra's Algorithm");
        questionBank.add(new Question("Which algorithm uses a stack data structure?", options11, 1, "Hard", 25));
        
        List<String> options12 = new ArrayList<>();
        options12.add("Big O");
        options12.add("Big Theta");
        options12.add("Big Omega");
        options12.add("All of the above");
        questionBank.add(new Question("Which notation describes the worst-case complexity?", options12, 1, "Hard", 20));
        
        List<String> options13 = new ArrayList<>();
        options13.add("Deadlock");
        options13.add("Race Condition");
        options13.add("Starvation");
        options13.add("Livelock");
        questionBank.add(new Question("What occurs when two processes wait for each other?", options13, 1, "Hard", 22));
        
        List<String> options14 = new ArrayList<>();
        options14.add("Primary Key");
        options14.add("Foreign Key");
        options14.add("Candidate Key");
        options14.add("Super Key");
        questionBank.add(new Question("Which key uniquely identifies a record?", options14, 1, "Hard", 18));
        
        List<String> options15 = new ArrayList<>();
        options15.add("2NF");
        options15.add("3NF");
        options15.add("BCNF");
        options15.add("4NF");
        questionBank.add(new Question("Which normal form eliminates transitive dependency?", options15, 2, "Hard", 25));
    }
    
    public void startQuiz() {
        System.out.println("\n=== Welcome to the Java Quiz Game ===");
        System.out.println("Test your knowledge with multiple-choice questions!");
        System.out.println("Answer each question by entering the option number (1-4).");
        System.out.println("Good luck!\n");
        
        // Select questions for this quiz (10 random questions)
        selectQuizQuestions();
        
        // Shuffle questions for random order
        Collections.shuffle(currentQuizQuestions);
        
        // Reset score
        score = 0;
        totalQuestions = currentQuizQuestions.size();
        
        // Ask each question
        for (int i = 0; i < currentQuizQuestions.size(); i++) {
            Question q = currentQuizQuestions.get(i);
            askQuestion(q, i + 1);
        }
        
        // Show final results
        showResults();
    }
    
    private void selectQuizQuestions() {
        currentQuizQuestions.clear();
        Collections.shuffle(questionBank);
        // Select first 10 questions after shuffling
        for (int i = 0; i < Math.min(10, questionBank.size()); i++) {
            currentQuizQuestions.add(questionBank.get(i));
        }
    }
    
    private void askQuestion(Question question, int questionNumber) {
        System.out.println("\n--- Question " + questionNumber + " of " + totalQuestions + " (" + question.getDifficulty() + ") ---");
        System.out.println(question);
        
        int userAnswer = -1;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print("Your answer (1-4): ");
            try {
                String input = scanner.nextLine().trim();
                userAnswer = Integer.parseInt(input);
                
                if (userAnswer >= 1 && userAnswer <= 4) {
                    validInput = true;
                } else {
                    System.out.println("Invalid option! Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 4.");
            }
        }
        
        // Check if answer is correct
        if (question.isCorrect(userAnswer)) {
            System.out.println("\u001B[32mCorrect! Well done.\u001B[0m"); // Green text
            score++;
        } else {
            System.out.println("\u001B[31mIncorrect! The correct answer was: " + question.getCorrectAnswerIndex() + ". " + 
                              question.getOptions().get(question.getCorrectAnswerIndex() - 1) + "\u001B[0m"); // Red text
        }
    }
    
    private void showResults() {
        double percentage = (double) score / totalQuestions * 100;
        
        System.out.println("\n=== Quiz Completed ===");
        System.out.println("Final Score: " + score + "/" + totalQuestions);
        System.out.printf("Percentage: %.1f%%\n", percentage);
        
        // Performance feedback
        if (percentage >= 90) {
            System.out.println("\u001B[32mExcellent! You're a Java expert!\u001B[0m");
        } else if (percentage >= 70) {
            System.out.println("\u001B[32mGood job! You have solid Java knowledge.\u001B[0m");
        } else if (percentage >= 50) {
            System.out.println("\u001B[33mNot bad! But there's room for improvement.\u001B[0m"); // Yellow text
        } else {
            System.out.println("\u001B[31mKeep studying! Practice makes perfect.\u001B[0m"); // Red text
        }
    }
    
    public int getScore() {
        return score;
    }
    
    public int getTotalQuestions() {
        return totalQuestions;
    }
}
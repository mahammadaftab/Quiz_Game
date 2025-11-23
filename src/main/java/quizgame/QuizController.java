package quizgame;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "*")
public class QuizController {
    
    private List<Question> questionBank;
    
    public QuizController() {
        initializeQuestionBank();
    }
    
    private void initializeQuestionBank() {
        questionBank = new ArrayList<>();
        
        // Easy Questions
        List<String> options1 = Arrays.asList(
            "Guido van Rossum",
            "James Gosling",
            "Dennis Ritchie",
            "Bjarne Stroustrup"
        );
        questionBank.add(new Question("Who created Python?", options1, 1, "Easy", 15));
        
        List<String> options2 = Arrays.asList(
            "int",
            "float",
            "boolean",
            "char"
        );
        questionBank.add(new Question("Which data type stores true/false values?", options2, 3, "Easy", 10));
        
        List<String> options3 = Arrays.asList(
            "JVM",
            "JDK",
            "JRE",
            "JIT"
        );
        questionBank.add(new Question("What does JVM stand for?", options3, 1, "Easy", 12));
        
        List<String> options4 = Arrays.asList(
            "print()",
            "echo()",
            "output()",
            "console.log()"
        );
        questionBank.add(new Question("Which function is used to output text in Python?", options4, 1, "Easy", 10));
        
        List<String> options5 = Arrays.asList(
            "List",
            "Tuple",
            "Dictionary",
            "Set"
        );
        questionBank.add(new Question("Which Python collection is ordered and changeable?", options5, 1, "Easy", 12));
        
        // Medium Questions
        List<String> options6 = Arrays.asList(
            "Stack",
            "Queue",
            "Tree",
            "Graph"
        );
        questionBank.add(new Question("Which data structure uses LIFO principle?", options6, 1, "Medium", 15));
        
        List<String> options7 = Arrays.asList(
            "O(1)",
            "O(n)",
            "O(log n)",
            "O(n^2)"
        );
        questionBank.add(new Question("What is the time complexity of binary search?", options7, 3, "Medium", 20));
        
        List<String> options8 = Arrays.asList(
            "Inheritance",
            "Encapsulation",
            "Polymorphism",
            "Abstraction"
        );
        questionBank.add(new Question("Which OOP concept hides internal details?", options8, 2, "Medium", 18));
        
        List<String> options9 = Arrays.asList(
            "HTTP",
            "FTP",
            "TCP",
            "UDP"
        );
        questionBank.add(new Question("Which protocol is used for web browsing?", options9, 1, "Medium", 15));
        
        List<String> options10 = Arrays.asList(
            "RAM",
            "ROM",
            "Cache",
            "Hard Disk"
        );
        questionBank.add(new Question("Which memory is the fastest?", options10, 3, "Medium", 12));
        
        // Hard Questions
        List<String> options11 = Arrays.asList(
            "Depth-First Search",
            "Breadth-First Search",
            "A* Algorithm",
            "Dijkstra's Algorithm"
        );
        questionBank.add(new Question("Which algorithm uses a stack data structure?", options11, 1, "Hard", 25));
        
        List<String> options12 = Arrays.asList(
            "Big O",
            "Big Theta",
            "Big Omega",
            "All of the above"
        );
        questionBank.add(new Question("Which notation describes the worst-case complexity?", options12, 1, "Hard", 20));
        
        List<String> options13 = Arrays.asList(
            "Deadlock",
            "Race Condition",
            "Starvation",
            "Livelock"
        );
        questionBank.add(new Question("What occurs when two processes wait for each other?", options13, 1, "Hard", 22));
        
        List<String> options14 = Arrays.asList(
            "Primary Key",
            "Foreign Key",
            "Candidate Key",
            "Super Key"
        );
        questionBank.add(new Question("Which key uniquely identifies a record?", options14, 1, "Hard", 18));
        
        List<String> options15 = Arrays.asList(
            "2NF",
            "3NF",
            "BCNF",
            "4NF"
        );
        questionBank.add(new Question("Which normal form eliminates transitive dependency?", options15, 2, "Hard", 25));
    }
    
    @GetMapping("/questions")
    public List<Question> getQuestions() {
        // Return all questions (in a real app, you might want to randomize or limit)
        return new ArrayList<>(questionBank);
    }
    
    @GetMapping("/quiz-questions/{count}")
    public List<Question> getQuizQuestions(@PathVariable int count) {
        // Create a copy of the question bank and shuffle it
        List<Question> quizQuestions = new ArrayList<>(questionBank);
        Collections.shuffle(quizQuestions);
        
        // Return the requested number of questions (or all if count is greater)
        return quizQuestions.subList(0, Math.min(count, quizQuestions.size()));
    }
    
    @PostMapping("/submit")
    public Map<String, Object> submitQuiz(@RequestBody Map<String, Object> submission) {
        List<Map<String, Object>> answers = (List<Map<String, Object>>) submission.get("answers");
        int score = 0;
        int total = answers.size();
        
        // Calculate score
        for (Map<String, Object> answer : answers) {
            int questionIndex = (int) answer.get("questionIndex");
            int selectedOption = (int) answer.get("selectedOption");
            
            if (questionIndex < questionBank.size() && 
                questionBank.get(questionIndex).isCorrect(selectedOption)) {
                score++;
            }
        }
        
        // Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("score", score);
        response.put("total", total);
        response.put("percentage", (double) score / total * 100);
        
        // Add performance message
        double percentage = (double) score / total * 100;
        if (percentage >= 90) {
            response.put("message", "Excellent! You're a Java expert!");
        } else if (percentage >= 70) {
            response.put("message", "Good job! You have solid Java knowledge.");
        } else if (percentage >= 50) {
            response.put("message", "Not bad! But there's room for improvement.");
        } else {
            response.put("message", "Keep studying! Practice makes perfect.");
        }
        
        return response;
    }
}
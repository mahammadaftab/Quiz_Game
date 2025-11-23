package quizgame;

import java.util.List;
import java.util.ArrayList;

public class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswerIndex;
    private String difficulty;
    private int timeLimitSeconds; // Optional time limit for the question
    
    public Question() {
        // Default constructor for JSON serialization
    }
    
    public Question(String questionText, List<String> options, int correctAnswerIndex, String difficulty, int timeLimitSeconds) {
        this.questionText = questionText;
        this.options = new ArrayList<>(options);
        this.correctAnswerIndex = correctAnswerIndex;
        this.difficulty = difficulty;
        this.timeLimitSeconds = timeLimitSeconds;
    }
    
    // Getters
    public String getQuestionText() {
        return questionText;
    }
    
    public List<String> getOptions() {
        return new ArrayList<>(options);
    }
    
    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
    
    public String getDifficulty() {
        return difficulty;
    }
    
    public int getTimeLimitSeconds() {
        return timeLimitSeconds;
    }
    
    // Setters
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    
    public void setOptions(List<String> options) {
        this.options = new ArrayList<>(options);
    }
    
    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }
    
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    
    public void setTimeLimitSeconds(int timeLimitSeconds) {
        this.timeLimitSeconds = timeLimitSeconds;
    }
    
    public boolean isCorrect(int userAnswer) {
        return userAnswer == correctAnswerIndex;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(questionText).append("\n");
        for (int i = 0; i < options.size(); i++) {
            sb.append((i + 1)).append(". ").append(options.get(i)).append("\n");
        }
        return sb.toString();
    }
}
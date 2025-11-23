# Java Quiz Game

An interactive console-based and web-based Java Quiz Game with real-time scoring and dynamic multiple-choice questions.

## Features

- Multiple-choice questions with varying difficulty levels (Easy, Medium, Hard)
- Randomized question order for each session
- Real-time score calculation and final performance summary
- Color-coded output for better user engagement
- Progress indicators showing current question number
- Option to restart the quiz after completion
- Clean OOP architecture following SOLID principles
- Both console and web-based interfaces

## Prerequisites

- Java JDK 11 or higher

## How to Compile and Run

### Console Version

#### Using Maven

1. Open a terminal/command prompt in the project root directory
2. Compile and package the application:
   ```
   mvn clean package
   ```
3. Run the application:
   ```
   java -jar target/java-quiz-game-1.0-SNAPSHOT.jar
   ```

#### Using Command Line

1. Open a terminal/command prompt in the project root directory
2. Compile the Java files:
   ```
   javac -d target src/main/java/quizgame/*.java
   ```
3. Run the application:
   ```
   java -cp target quizgame.QuizGameApp
   ```

#### Using Build Scripts

##### Windows
Double-click on `build.bat` or run from command prompt:
```
build.bat
```

##### Linux/Mac
Run from terminal:
```
chmod +x build.sh
./build.sh
```

### Web Version

1. Open a terminal/command prompt in the project root directory
2. Compile and run the Spring Boot application:
   ```
   mvn spring-boot:run
   ```
   OR
   ```
   mvn clean package
   java -jar target/java-quiz-game-1.0-SNAPSHOT.jar
   ```
3. Open your web browser and navigate to http://localhost:8080

## How to Play

### Console Version

1. Launch the application
2. Answer each question by entering the option number (1-4)
3. Receive immediate feedback on your answers
4. View your final score and performance summary at the end
5. Choose to play again or exit

### Web Version

1. Open your web browser and navigate to http://localhost:8080
2. Click "Start Quiz" to begin
3. Select an answer for each question
4. Click "Next Question" to proceed
5. View your final score and performance summary at the end
6. Click "Play Again" to restart the quiz

## Implementation Details

The application follows a clean OOP design with the following classes:

- `Question`: Represents a single quiz question with its options and correct answer
- `QuizManager`: Handles quiz logic, question management, and scoring (console version)
- `QuizGameApp`: Main application class with user interface (console version)
- `QuizGameWebApplication`: Main Spring Boot application class (web version)
- `QuizController`: REST controller for handling quiz requests (web version)

The code is organized according to SOLID principles for maintainability and extensibility.
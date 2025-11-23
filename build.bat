@echo off
echo Compiling Java Quiz Game...
javac -d target src/main/java/quizgame/*.java
if %errorlevel% == 0 (
    echo Compilation successful!
    echo.
    echo Running the Java Quiz Game...
    echo.
    java -cp target quizgame.QuizGameApp
) else (
    echo Compilation failed. Please check for errors above.
)
pause
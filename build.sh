#!/bin/bash
echo "Compiling Java Quiz Game..."
javac -d target src/main/java/quizgame/*.java
if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo ""
    echo "Running the Java Quiz Game..."
    echo ""
    java -cp target quizgame.QuizGameApp
else
    echo "Compilation failed. Please check for errors above."
fi
// Global variables
let questions = [];
let currentQuestionIndex = 0;
let userAnswers = [];
let quizStarted = false;

// DOM elements
const welcomeScreen = document.getElementById('welcome-screen');
const quizScreen = document.getElementById('quiz-screen');
const resultsScreen = document.getElementById('results-screen');
const startBtn = document.getElementById('start-btn');
const nextBtn = document.getElementById('next-btn');
const restartBtn = document.getElementById('restart-btn');
const questionText = document.getElementById('question-text');
const optionsContainer = document.getElementById('options-container');
const questionNumber = document.getElementById('question-number');
const difficultyTag = document.getElementById('difficulty');
const progress = document.getElementById('progress');
const finalScore = document.getElementById('final-score');
const totalQuestions = document.getElementById('total-questions');
const percentage = document.getElementById('percentage');
const performanceMessage = document.getElementById('performance-message');

// Event listeners
startBtn.addEventListener('click', startQuiz);
nextBtn.addEventListener('click', nextQuestion);
restartBtn.addEventListener('click', restartQuiz);

// Initialize the quiz
function initQuiz() {
    // Reset variables
    questions = [];
    currentQuestionIndex = 0;
    userAnswers = [];
    quizStarted = false;
    
    // Hide all screens
    welcomeScreen.classList.remove('hidden');
    quizScreen.classList.add('hidden');
    resultsScreen.classList.add('hidden');
}

// Start the quiz
function startQuiz() {
    welcomeScreen.classList.add('hidden');
    quizScreen.classList.remove('hidden');
    
    // Fetch questions from the backend
    fetch('/api/quiz/quiz-questions/10')
        .then(response => response.json())
        .then(data => {
            questions = data;
            quizStarted = true;
            showQuestion();
        })
        .catch(error => {
            console.error('Error fetching questions:', error);
            alert('Failed to load questions. Please try again.');
        });
}

// Show current question
function showQuestion() {
    if (currentQuestionIndex >= questions.length) {
        finishQuiz();
        return;
    }
    
    const question = questions[currentQuestionIndex];
    
    // Update progress bar
    const progressPercent = ((currentQuestionIndex) / questions.length) * 100;
    progress.style.width = `${progressPercent}%`;
    
    // Update question info
    questionNumber.textContent = `Question ${currentQuestionIndex + 1} of ${questions.length}`;
    difficultyTag.textContent = question.difficulty;
    difficultyTag.className = 'difficulty-tag ' + question.difficulty;
    
    // Update question text
    questionText.textContent = question.questionText;
    
    // Clear previous options
    optionsContainer.innerHTML = '';
    
    // Add options
    question.options.forEach((option, index) => {
        const optionElement = document.createElement('div');
        optionElement.className = 'option';
        optionElement.innerHTML = `
            <input type="radio" name="option" id="option${index}" value="${index + 1}">
            <label for="option${index}">${index + 1}. ${option}</label>
        `;
        optionElement.addEventListener('click', () => selectOption(optionElement, index + 1));
        optionsContainer.appendChild(optionElement);
    });
    
    // Enable next button
    nextBtn.disabled = true;
}

// Select an option
function selectOption(optionElement, optionValue) {
    // Deselect all options
    document.querySelectorAll('.option').forEach(option => {
        option.classList.remove('selected');
    });
    
    // Select clicked option
    optionElement.classList.add('selected');
    
    // Store user answer
    userAnswers[currentQuestionIndex] = optionValue;
    
    // Enable next button
    nextBtn.disabled = false;
}

// Move to next question
function nextQuestion() {
    currentQuestionIndex++;
    showQuestion();
}

// Finish the quiz
function finishQuiz() {
    quizScreen.classList.add('hidden');
    resultsScreen.classList.remove('hidden');
    
    // Prepare submission data
    const submission = {
        answers: userAnswers.map((answer, index) => ({
            questionIndex: index,
            selectedOption: answer
        }))
    };
    
    // Submit answers to backend
    fetch('/api/quiz/submit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(submission)
    })
    .then(response => response.json())
    .then(data => {
        // Display results
        finalScore.textContent = data.score;
        totalQuestions.textContent = questions.length;
        percentage.textContent = data.percentage.toFixed(1);
        performanceMessage.textContent = data.message;
    })
    .catch(error => {
        console.error('Error submitting quiz:', error);
        alert('Failed to submit quiz. Please try again.');
    });
}

// Restart the quiz
function restartQuiz() {
    resultsScreen.classList.add('hidden');
    welcomeScreen.classList.remove('hidden');
    initQuiz();
}

// Initialize the quiz when page loads
document.addEventListener('DOMContentLoaded', initQuiz);
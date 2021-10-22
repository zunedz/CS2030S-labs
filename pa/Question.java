abstract class Question {
    private final String question;
    private final int expectedAnswer;
    private final int guessedAnswer;

    Question(String question, int expectedAnswer, int guessedAnswer) {
        this.question = question;
        this.expectedAnswer = expectedAnswer;
        this.guessedAnswer = guessedAnswer;
    }
    
    String getQuestion() {
        return this.question;
    }

    int getExpectedAnswer() {
        return this.expectedAnswer;
    }

    int getGuessedAnswer() {
        return this.guessedAnswer;
    }
    
    abstract Question answer(int guessedAnswer);
}

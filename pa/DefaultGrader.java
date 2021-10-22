class DefaultGrader implements Grader {
    @Override
    public int mark(int expectedAnswer, int guessedAnswer) {
        if (expectedAnswer == guessedAnswer) {
            return 1;
        }
        return 0;
    }
}

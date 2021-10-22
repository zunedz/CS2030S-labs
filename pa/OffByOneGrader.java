class OffByOneGrader implements Grader {
    private final int expectedAnswer;

    OffByOneGrader(int expectedAnswer) {
        this.expectedAnswer = expectedAnswer;
    }
    
    int getExpectedAnswer() {
        return this.expectedAnswer;
    }

    @Override
    public int mark(int expectedAnswer, int guessedAnswer) {
        if (this.getExpectedAnswer() == guessedAnswer) {
            return 2;
        } else if (Math.abs(this.getExpectedAnswer() - guessedAnswer) == 1) {
            return 1;
        }    
        return 0;
    }
}

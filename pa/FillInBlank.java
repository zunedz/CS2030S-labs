class FillInBlank extends Question {
    private final Grader grader;

    FillInBlank(String question, int expectedAnswer) {
        super(question, expectedAnswer, 0);
        this.grader = new DefaultGrader(); 
    } 

    FillInBlank(String question, int expectedAnswer, int guessedAnswer) {
        super(question, expectedAnswer, guessedAnswer);
        this.grader = new DefaultGrader();
    }

    FillInBlank(String question, Grader grader) {
        super(question, 0, 0);
        this.grader = grader;
    }

    FillInBlank answer(int answer) {
        return new FillInBlank(this.getQuestion(), this.getExpectedAnswer(), answer);
    }

    FillInBlank answer() {
        return new FillInBlank(this.getQuestion(), this.getExpectedAnswer());
    }

    Grader getGrader() {
        return this.grader;
    }

    LockedFIB lock() {
        return new LockedFIB(this);
    }

    @Override
    public String toString() {
        return String.format("%s; Your answer: %d", this.getQuestion(), this.getGuessedAnswer());
    }
}

class MCQ extends Question {
    private final String[] options;

    MCQ(String question, String[] options, int expectedAnswer) {
        super(question, expectedAnswer, 0);
        this.options = options;
    }

    String[] getOptions() {
        return this.options;
    }

    MCQ(String question, String[] options, int expectedAnswer, int guessedAnswer) {
        super(question, expectedAnswer, guessedAnswer);
        this.options = options;
    }

    MCQ answer(int guessedAnswer) {
        return new MCQ(this.getQuestion(), this.options, this.getExpectedAnswer(),
                guessedAnswer);
    }

    //LockedMCQ lock() {
    //  return new LockedMCQ(this);
    //}

    @Override
    public String toString() {
        String output = this.getQuestion() + " ";
        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            output += String.format("[%d:%s]", i + 1, option);
        }
        String answer = Integer.toString(this.getGuessedAnswer());
        if (answer.equals("0")) {
            return output + String.format("; Your answer: [ ? ]");
        }
        String option = options[this.getGuessedAnswer() - 1];
        return output + String.format("; Your answer: [ %s:%s ]", answer, option);
    }
}

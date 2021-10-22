class LockedMCQ extends MCQ {
    LockedMCQ(MCQ mcq) {
        super(mcq.getQuestion(), mcq.getOptions(), mcq.getExpectedAnswer(), mcq.getGuessedAnswer());
    }

    int mark() {
        if (this.getExpectedAnswer() == getGuessedAnswer()) {
            return 1;
        }
        return 0;
    }
}

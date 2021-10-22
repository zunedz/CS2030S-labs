class LockedTFQ extends TFQ {
    LockedTFQ(TFQ tfq) {
        super(tfq.getQuestion(), tfq.getOptions(), tfq.getExpectedAnswer(), tfq.getGuessedAnswer());
    }

    int mark() {
        if (this.getExpectedAnswer() == this.getGuessedAnswer()) {
            return 1;
        }
        return 0;
    }
}

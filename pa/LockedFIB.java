class LockedFIB extends FillInBlank {

    LockedFIB(FillInBlank fib) {
        super(fib.getQuestion(), fib.getExpectedAnswer(), fib.getGuessedAnswer());
    }

    int mark() {
        Grader grader = this.getGrader();
        return grader.mark(this.getExpectedAnswer(), this.getGuessedAnswer());
    }
}

class TFQ extends MCQ {
    TFQ(String question, String expectedAnswer) {
        super(question, new String[]{"True", "False"}, expectedAnswer.equals("True")? 1: 2); 
    }

    TFQ(String question, String[] options, int expectedAnswer, int guessedAnswer) {
        super(question, options, expectedAnswer, guessedAnswer);
    }

    @Override 
    TFQ answer(int guessedAnswer) {
        return new TFQ(this.getQuestion(), this.getOptions(), this.getExpectedAnswer(),
                guessedAnswer);
    }
    
    //@Override
    //LockedTFQ lock() {
       // return new LockedTFQ(this);
    //}
    
    TFQ answer(String guessedAnswer) {
        int intAnswer = guessedAnswer.equals("True")? 1: 2;
        return new TFQ(this.getQuestion(), this.getOptions(), this.getExpectedAnswer(),
                intAnswer);
    }
}

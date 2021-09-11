class RubikBack extends Rubik {
    private final Rubik rubik;

    RubikBack(Rubik rubik) {
        this.rubik = rubik.clone();
    }
    
    @Override
    int[][][] toIntArray() {
        return this.rubik.toIntArray();
    }
    
    @Override
    RubikBack right() {
        return new RubikBack(rubik.backView().right().backView());
    }

    @Override
    RubikBack left() {
        return this.right().right().right();
    }

    @Override
    RubikBack half() {
        return this.right().right();
    }

    @Override
    public RubikBack clone() {
        return this.half().half();
    }

    @Override
    public String toString() {
        return this.rubik.toString();
    }

    @Override
    RubikBack rightView() {
        return new RubikBack(rubik.rightView());
    }

    @Override
    RubikBack leftView() {
        return new RubikBack(rubik.leftView());
    }

    @Override
    RubikBack upView() {
        return new RubikBack(rubik.upView());
    }

    @Override
    RubikBack downView() {
        return new RubikBack(rubik.downView());
    }

    @Override
    RubikBack backView() {
        return new RubikBack(rubik.backView());
    }

    @Override
    RubikBack frontView() {
        return new RubikBack(rubik.frontView());
    }

}

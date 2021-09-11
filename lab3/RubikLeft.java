class RubikLeft extends Rubik {
    private final Rubik rubik;

    RubikLeft(Rubik rubik) {
        this.rubik = rubik.clone();
    }
    
    @Override
    int[][][] toIntArray() {
        return this.rubik.toIntArray();
    }

    @Override
    RubikLeft right() {
        return new RubikLeft(rubik.leftView().right().rightView());
    }

    @Override
    RubikLeft left() {
        return this.right().right().right();
    }

    @Override
    RubikLeft half() {
        return this.right().right();
    }

    @Override
    public RubikLeft clone() {
        return this.half().half();
    }

    @Override
    public String toString() {
        return this.rubik.toString();
    }

    @Override
    RubikLeft rightView() {
        return new RubikLeft(rubik.rightView());
    }

    @Override
    RubikLeft leftView() {
        return new RubikLeft(rubik.leftView());
    }

    @Override
    RubikLeft upView() {
        return new RubikLeft(rubik.upView());
    }

    @Override
    RubikLeft downView() {
        return new RubikLeft(rubik.downView());
    }

    @Override
    RubikLeft backView() {
        return new RubikLeft(rubik.backView());
    }

    @Override
    RubikLeft frontView() {
        return new RubikLeft(rubik.frontView());
    }

}

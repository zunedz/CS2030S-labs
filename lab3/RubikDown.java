class RubikDown extends Rubik {
    private final Rubik rubik;

    RubikDown(Rubik rubik) {
        this.rubik = rubik.clone();
    }
    
    @Override
    int[][][] toIntArray() {
        return this.rubik.toIntArray();
    }

    @Override
    RubikDown right() {
        return new RubikDown(rubik.downView().right().upView());
    }

    @Override
    RubikDown left() {
        return this.right().right().right();
    }

    @Override
    RubikDown half() {
        return this.right().right();
    }

    @Override
    public RubikDown clone() {
        return this.half().half();
    }

    @Override
    public String toString() {
        return this.rubik.toString();
    }

    @Override
    RubikDown rightView() {
        return new RubikDown(rubik.rightView());
    }

    @Override
    RubikDown leftView() {
        return new RubikDown(rubik.leftView());
    }

    @Override
    RubikDown upView() {
        return new RubikDown(rubik.upView());
    }

    @Override
    RubikDown downView() {
        return new RubikDown(rubik.downView());
    }

    @Override
    RubikDown backView() {
        return new RubikDown(rubik.backView());
    }

    @Override
    RubikDown frontView() {
        return new RubikDown(rubik.frontView());
    }

}

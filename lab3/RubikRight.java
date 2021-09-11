class RubikRight extends Rubik {
    private final Rubik rubik;

    RubikRight(Rubik rubik) {
        this.rubik = rubik.clone();
    }
    
    @Override
    int[][][] toIntArray() {
        return this.rubik.toIntArray();
    }

    @Override
    RubikRight right() {
        return new RubikRight(rubik.rightView().right().leftView());
    }

    @Override
    RubikRight left() {
        return this.right().right().right();
    }

    @Override
    RubikRight half() {
        return this.right().right();
    }

    @Override
    public RubikRight clone() {
        return this.half().half();
    }

    @Override
    public String toString() {
        return this.rubik.toString();
    }

    @Override
    RubikRight rightView() {
        return new RubikRight(rubik.rightView());
    }

    @Override
    RubikRight leftView() {
        return new RubikRight(rubik.leftView());
    }

    @Override
    RubikRight upView() {
        return new RubikRight(rubik.upView());
    }

    @Override
    RubikRight downView() {
        return new RubikRight(rubik.downView());
    }

    @Override
    RubikRight backView() {
        return new RubikRight(rubik.backView());
    }

    @Override
    RubikRight frontView() {
        return new RubikRight(rubik.frontView());
    }

}

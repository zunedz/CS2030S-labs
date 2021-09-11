class RubikUp extends Rubik {
    private final Rubik rubik;

    RubikUp(Rubik rubik) {
        this.rubik = rubik.clone();
    }
    
    @Override
    int[][][] toIntArray() {
        return this.rubik.toIntArray();
    }

    @Override
    RubikUp right() {
        return new RubikUp(rubik.upView().right().downView());
    }

    @Override
    RubikUp left() {
        return this.right().right().right();
    }

    @Override
    RubikUp half() {
        return this.right().right();
    }

    @Override
    public RubikUp clone() {
        return this.half().half();
    }

    @Override
    public String toString() {
        return this.rubik.toString();
    }

    @Override
    RubikUp rightView() {
        return new RubikUp(rubik.rightView());
    }

    @Override
    RubikUp leftView() {
        return new RubikUp(rubik.leftView());
    }

    @Override
    RubikUp upView() {
        return new RubikUp(rubik.upView());
    }

    @Override
    RubikUp downView() {
        return new RubikUp(rubik.downView());
    }

    @Override
    RubikUp backView() {
        return new RubikUp(rubik.backView());
    }

    @Override
    RubikUp frontView() {
        return new RubikUp(rubik.frontView());
    }
}

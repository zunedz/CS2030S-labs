abstract class Rubik implements Cloneable {
    abstract Rubik right();

    abstract Rubik left();

    abstract Rubik half();

    abstract Rubik rightView();

    abstract Rubik leftView();

    abstract Rubik upView();

    abstract Rubik downView();

    abstract Rubik backView();

    abstract Rubik frontView();
    
    abstract int[][][] toIntArray();
    
    @Override
    public abstract Rubik clone();
}

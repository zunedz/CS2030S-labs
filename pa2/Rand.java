import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

class Rand<R> {
    private final int seed;
    private final Function<Integer, R> mapper;

    private Rand(int seed, Function<Integer, R> mapper) {
        this.seed = seed;
        this.mapper = mapper;
    }

    R get() {
        return mapper.apply(seed);
    }

    Rand<R> next() {
        int newSeed = new Random(seed).nextInt(Integer.MAX_VALUE);
        return new Rand<R>(newSeed, this.mapper);
    }

    Stream<R> stream() {
        return Stream.<Integer>iterate(seed, x -> Rand.of(Rand.of(x).next().get()).get()).map(this.mapper);
    }

    static <R> Stream<R> randRange(int seed, Function<Integer, R> f) {
        return Rand.of(seed).stream().map(f);
    }

    static Rand<Integer> of(int seed) {
        return new Rand<Integer>(seed, x -> x);
    }

    @Override
    public String toString() {
        return "Rand";
    }

    <U> Rand<U> map(Function<Integer, U> mapper) {
        return new Rand<U>(seed, mapper);
    }

    <U> Rand<U> flatMap(Function<Integer, Rand<U>> mapper) {
        return this.map(x -> mapper.apply(this.mapper.apply(x)).get());            
    } 

}

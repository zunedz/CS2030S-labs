import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.Consumer;

class EmptyList<T> implements InfiniteList<T> {
    public boolean isEmpty() {
        return true;
    }

    @Override
    public EmptyList<T> filter(Predicate<? super T> predicate) {
        return new EmptyList<T>();
    }

    @Override
    public <R> EmptyList<R> map(Function<? super T, ? extends R> mapper) {
        return new EmptyList<R>();
    }

    @Override 
    public EmptyList<T> peek() {
        return new EmptyList<T>();
    }

    @Override
    public InfiniteList<T> limit(long n) {
        return new EmptyList<T>();
    }

    @Override
    public InfiniteList<T> takeWhile(Predicate<? super T> predicate) {
        return new EmptyList<T>();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        
    }

    @Override
    public long count() {
        return 0;
    }

    @Override 
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator) {
        return identity;
    }

    @Override 
    public Object[] toArray() {
        return new Object[0]; 
    }
}

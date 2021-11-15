import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Predicate;

class Lazy<T> {
    private final Supplier<T> value;
    private Optional<T> cache;
    private boolean computed;

    private Lazy(T value) {
        this.value = () -> value;
        this.cache = Optional.ofNullable(value);
        this.computed = true;
    }

    private Lazy(Supplier<? extends T> supplier) {
        this.value = () -> (T) supplier.get();
        this.cache = Optional.ofNullable(null);
        this.computed = false;
    }

    static <U> Lazy<U> ofNullable(U value) {
        return new Lazy<U>(value);
    }

    static <U> Lazy<U> of(Supplier<? extends U> supplier) {
        return new Lazy<U>(supplier);
    }

    Optional<T> get() {
        if (computed) {
            return cache;
        }
        this.cache = Optional.ofNullable(this.cache
                .orElseGet(() -> this.value.get()));
        this.computed = true;
        return this.cache;

    }

    <R> Lazy<R> map(Function<? super T, ? extends R> mapper) {
        Supplier<R> supplier = () -> (R) this.get().map(mapper).orElse(null);
        return new Lazy<R>(supplier);
    }

    Lazy<T> filter(Predicate<? super T> predicate) {
        Supplier<T> supplier = () -> this.get().filter(predicate).orElse(null);
        return new Lazy<T>(supplier);
    }

    @Override
    public String toString() {
        return String.format("Lazy[%s]", !computed ? "?" : 
                this.cache.map(x -> x.toString()).orElse("null")); 
    }
}

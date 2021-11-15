import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.BiFunction;
import java.util.List;
import java.util.ArrayList;

class InfiniteListImpl<T> implements InfiniteList<T> {
    private final Lazy<T> head;
    private final Supplier<InfiniteList<T>> tail;

    InfiniteListImpl(Lazy<T> head, Supplier<InfiniteList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    static <U> InfiniteListImpl<U> generate(Supplier<? extends U> supplier) {
        Lazy<U> newHead = Lazy.<U>of(supplier);
        Supplier<InfiniteList<U>> newTail = () -> 
            InfiniteListImpl.<U>generate(supplier);
        return new InfiniteListImpl<U>(newHead, newTail);
    }

    static <U> InfiniteListImpl<U> iterate(U seed, Function<? super U, ? extends U> next) {
        Lazy<U> newHead = Lazy.<U>of(() -> seed);
        Supplier<InfiniteList<U>> newTail = () ->
            InfiniteListImpl.<U>iterate(next.apply(seed), next);
        return new InfiniteListImpl<U>(newHead, newTail);
    }

    public InfiniteList<T> peek() {
        this.head.get().stream().forEach(System.out::println);
        return this.tail.get();
    }

    @Override
    public <R> InfiniteListImpl<R> map(Function<? super T, ? extends R> mapper) {
        Lazy<R> newHead = this.head.map(mapper);
        Supplier<InfiniteList<R>> newTail = () ->
            this.tail.get().map(mapper);
        return new InfiniteListImpl<R>(newHead, newTail);
    }

    @Override
    public InfiniteListImpl<T> filter(Predicate<? super T> predicate)  {
        Lazy<T> newHead = this.head.filter(predicate);
        Supplier<InfiniteList<T>> newTail = () ->
            this.tail.get().filter(predicate);
        return new InfiniteListImpl<T>(newHead, newTail);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public InfiniteList<T> limit(long n) {
        if (n > 0) {
            Lazy<T> newHead = this.head.filter(x -> true);
            if (n == 1) {
                Supplier<InfiniteList<T>> newTail = () ->
                    this.head.get().<InfiniteList<T>>map(x -> new EmptyList<>())
                    .orElseGet(() -> this.tail.get().limit(n)); 
                return new InfiniteListImpl<T>(newHead, newTail);
            }
            Supplier<InfiniteList<T>> newTail = () ->
                this.head.get().map(x -> this.tail.get().limit(n - 1))
                .orElseGet(() -> this.tail.get().limit(n));
            return new InfiniteListImpl<T>(newHead, newTail);
        } 
        return new EmptyList<>();
    }

    @Override
    public InfiniteList<T> takeWhile(Predicate<? super T> predicate) {
        Lazy<T> newHead = this.head.filter(predicate);
        Supplier<InfiniteList<T>> newTail = () ->
            this.head.get().map(z -> 
                    newHead.get().map(x -> this.tail.get().takeWhile(predicate))
                    .orElse(new EmptyList<>())).orElseGet(() ->
                    this.tail.get().takeWhile(predicate));
        return new InfiniteListImpl<T>(newHead, newTail);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        this.head.get().stream().forEach(action);
        this.tail.get().forEach(action);
    }

    @Override
    public long count() {
        return this.head.map(x -> 1).get().orElse(0) + this.tail.get().count();
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator) {
        Function<T, U> tempFunction = x -> accumulator.apply(identity, x); 
        return this.tail.get()
            .reduce(this.head.get().map(tempFunction).orElseGet(() -> identity), accumulator);
    }

    @Override 
    public Object[] toArray() {
        List<Object> ansList = new ArrayList<Object>();
        InfiniteList<T> next = this;
        while (!next.isEmpty()) {
            InfiniteListImpl<T> innerNext = (InfiniteListImpl<T>) next;
            List<T> newList = innerNext.head.get().map(x -> {
                List<T> out = new ArrayList<T>();
                out.add(x);
                return out;
            }).orElse(new ArrayList<T>());
            ansList.addAll(newList);
            next = innerNext.tail.get();
        }
        return ansList.toArray();
    }
}

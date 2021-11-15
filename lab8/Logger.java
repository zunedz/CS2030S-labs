import java.util.function.Function;
import java.util.function.Predicate;
import java.util.Objects;
import java.util.function.Supplier;

class Logger<T> {
    private final Supplier<T> value;
    private final Supplier<String> log;

    private Logger(T value) {
        this.value = () -> value;
        this.log = () -> "";
    }

    private Logger(Supplier<T> newValue, Supplier<String> log) {
        this.value = newValue;
        this.log = log;
    }

    static <V> Logger<V> of(V value) {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("argument cannot be null");
        } else if (value instanceof Logger) {
            throw new IllegalArgumentException("already a Logger");
        }
        return new Logger<V>(value);

    }

    @Override
    public String toString() {
        String tempString = this.getLog();
        if (tempString.equals("")) {
            return String.format("Logger[%s]", this.value.get());
        }
        String[] arrStr = tempString.split("\n", Integer.MAX_VALUE);
        int numMap = arrStr.length;
        String strVal = arrStr[numMap - 1].split(" -> ", 2)[1];
        return String.format("Logger[%s]", strVal)  + tempString;   
    }

    T getValue() {
        return this.value.get();
    }

    String getLog() {
        return this.log.get();
    }

    <U> Logger<U> map(Function<? super T, ? extends U> mapper) {
        Supplier<U> newValue = () -> mapper.apply(this.getValue());
        Supplier<String> newLog = () -> this.getLog() + 
            String.format("\n%s -> %s", this.getValue(), newValue.get());
        return new Logger<U>(newValue, newLog);
    }

    <U> Logger<U> flatMap(Function<? super T, ? extends Logger<? extends U>> mapper) {
        Supplier<U> newValue = () -> mapper.apply(this.getValue()).getValue();
        Supplier<String> newLog = () -> this.getLog() + mapper.apply(this.getValue()).getLog();
        return new Logger<U>(newValue, newLog);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Logger) {
            Logger<?> otherNew = (Logger<?>) other;
            return otherNew.getValue().equals(this.getValue()) && 
                otherNew.getLog().equals(this.getLog()); 
        }   
        return false;
    }

    Logger<T> test(Predicate<? super T> pred, Logger<T> trueLogger, Logger<T> falseLogger) {
        if (pred.test(this.getValue())) {
            return trueLogger;
        } 
        return falseLogger;
    }
}


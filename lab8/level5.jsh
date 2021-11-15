/open Logger.java

Logger<Integer> add(Logger<Integer> a, int b) {
    return a.map(x -> x + b);
}

Logger<Integer> sum(int n) {
    Logger<Integer> identity = Logger.<Integer>of(0);
    return Stream.iterate(1, x -> x <= n, x -> x + 1).
        reduce(identity, (a, b) -> add(a, b), (a, b) -> a);
}

Logger<Integer> helper(Logger<Integer> logger) {
    if (logger.getValue() == 1) {
        return logger;
    } else if (logger.getValue() % 2 == 0) {
        return helper(logger.map(x -> x / 2));
    } else {
        return helper(logger.map(x -> x * 3).map(x -> x + 1));
    }
}

Logger<Integer> f(int n) {
    return helper(Logger.<Integer>of(n));
}


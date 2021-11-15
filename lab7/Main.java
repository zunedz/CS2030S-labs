import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Main {
    static long countTwinPrimes(int n) {
        return IntStream.rangeClosed(2, n)
            .filter(x -> isTwinPrime(x))
            .count();
    }

    static boolean isTwinPrime(int n) {
        return isPrime(n) && (isPrime(n + 2) || isPrime(n - 2));    
    }

    static boolean isPrime(int n) {
        return n > 1 && IntStream.range(2, (int) Math.sqrt(n) + 1)
            .noneMatch(x -> n % x == 0);
    }

    static String reverse(String str) {
        return str.chars()
            .mapToObj(x -> (char) x)
            .reduce("", (string, chr) -> chr + string, (str1, str2) -> str2);
    }

    static long countRepeats(int... array) {
        int size = array.length;
        List<Integer> arr = Arrays.stream(array).boxed().collect(Collectors.toList());
        arr.add(10);
        return IntStream.range(1, size)
            .filter(index -> arr.get(index - 1) == arr.get(index) &&
                    arr.get(index + 1) != arr.get(index))
            .count();
    }

    static double normalizedMean(Stream<Integer> stream) {
        Helper helper = new Helper();
        return stream.reduce(helper, (h, el) -> h.addElement(el), (h1, h2) -> h1).getMean();
    } 
}

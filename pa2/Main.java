class Main {
    static double simulate(int seed, int n) {
        Object[] arr = Rand.randRange(seed, x -> 2.0 * x / (Integer.MAX_VALUE - 1) -1).
            limit(2 * n).
            toArray();
        Circle c = new Circle(new Point(0.0, 0.0), 1.0);
        int count = 0;
        for (int i = 0; i < n; i++) {
            Point temp = new Point((double) arr[2 * i], (double) arr[2 * i + 1]);
            if (c.contains(temp)) {
                count++;
            }
        }
        return 4.0 * count / n; 
    }
}

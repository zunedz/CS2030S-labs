class Helper {
    private final int min;
    private final int max;
    private final int n;
    private final int total;

    Helper() {
        this.min = Integer.MAX_VALUE;
        this.max = Integer.MIN_VALUE;
        this.n = 0;
        this.total = 0;
    }

    Helper(int min, int max, int n, int total) {
        this.min = min;
        this.max = max;
        this.n = n;
        this.total = total;
    }
    
    Helper addElement(int i) {
        int newMin = Math.min(i, this.min);
        int newMax = Math.max(i, this.max);
        return new Helper(newMin, newMax, n + 1, total + i);
    }

    double getMean() {
        try {
            int temp = total / n;
            temp = (temp - min) / (max - min);
            double mean = 1.0 * total / n;
            double normalizedMean = (mean - min) / (max - min);
            return normalizedMean;
        } catch (Exception e) {
            return 0.0;
        }
    }


}

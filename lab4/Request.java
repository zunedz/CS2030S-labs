class Request {
    private final int distance;
    private final int numberOfPassengers;
    private final int time;

    public Request(int distance, int numberOfPassengers, int time) {
        this.distance = distance;
        this.numberOfPassengers = numberOfPassengers;
        this.time = time;
    }
    
    int computeFare(int fareRate, int surcharge, int bookingFee, 
            boolean isDividedEqually) {
        int initialFare = fareRate * distance;
        int fare = initialFare + bookingFee;
        if (time <= 900 && time >= 600) {
            fare = fare + surcharge;
        }
        if (isDividedEqually) {
            fare = (int) fare / numberOfPassengers;
        }
        return fare;
    }

    @Override 
    public String toString() {
        return String.format("%dkm for %dpax @ %dhrs", distance, 
                numberOfPassengers, time);
    }
}

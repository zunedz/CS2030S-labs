class Booking implements Comparable<Booking> {
    private final Driver driver;
    private final Request request;
    private final Services service;
    private final int serviceFare;
    private static final double centPerDollar = 100.0;

    Booking(Driver driver, Request request) {
        this.driver = driver;
        this.request = request;
        this.service = driver.chooseService(request);
        this.serviceFare = this.service.computeFare(request);
    }

    @Override
    public int compareTo(Booking otherBooking) {
        int fareDiff = this.serviceFare - otherBooking.serviceFare;
        if (fareDiff == 0) {
            return this.driver.compareTo(otherBooking.driver);
        }
        return fareDiff;
    }

    @Override
    public String toString() {
        double fareInDollar = serviceFare / Booking.centPerDollar;
        return String.format("$%.2f using ", fareInDollar) 
            + driver + " (" + service + ")";        
    }
}

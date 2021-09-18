class Booking implements Comparable<Booking> {
    private final Drivers driver;
    private final Request request;
    private final Services service;
    private final int serviceFare;

    Booking(Drivers driver, Request request) {
    }

    @Override
    public int compareTo(Booking otherBooking) {
        return this.serviceFare - otherBooking.serviceFare;
    }

    @Override
    public String toString() {
        return driver + " (" + service + ")";        
    }
}

class JustRide implements Services {
    private static final int FARE_RATE = 22;
    private static final int SURCHARGE = 500;
    private static final int BOOKING_FARE = 0;
    private static final boolean IS_DIVIDED_EQUALLY = false;
    
    @Override
    public int computeFare(Request request) {
        return request.computeFare(JustRide.FARE_RATE, JustRide.SURCHARGE,
                JustRide.BOOKING_FARE, JustRide.IS_DIVIDED_EQUALLY);    
    }

    @Override 
    public String toString() {
        return "JustRide";
    }
}

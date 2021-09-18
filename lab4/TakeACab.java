class TakeACab implements Services {
    private static final int FARE_RATE = 33;
    private static final int SURCHARGE = 0;
    private static final int BOOKING_FARE = 200;
    private static final boolean IS_DIVIDED_EQUALLY = false;

    @Override
    public int computeFare(Request request) {
        return request.computeFare(TakeACab.FARE_RATE, TakeACab.SURCHARGE,
                TakeACab.BOOKING_FARE, TakeACab.IS_DIVIDED_EQUALLY);    
    }

    @Override
    public String toString() {
        return "TakeACab";
    }

}

class ShareARide implements Services {
    private static final int FARE_RATE = 50;
    private static final int SURCHARGE = 500;
    private static final int BOOKING_FARE = 0;
    private static final boolean IS_DIVIDED_EQUALLY = true;
    
    @Override
    public int computeFare(Request request) {
        return request.computeFare(ShareARide.FARE_RATE, ShareARide.SURCHARGE,
                ShareARide.BOOKING_FARE, ShareARide.IS_DIVIDED_EQUALLY);    
    }

    @Override 
    public String toString() {
        return "ShareARide";
    }


} 

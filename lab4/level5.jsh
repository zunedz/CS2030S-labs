Booking findBestBooking(Request request, Driver[] driverArray) {
    Booking bestBooking = new Booking(driverArray[0], request);
    for (Driver driver: driverArray) {
        Booking tempBooking = new Booking(driver, request);
        if (tempBooking.compareTo(bestBooking) < 0) {
            bestBooking = tempBooking;
        }
    }
    return bestBooking;
}

class BigCruise extends Cruise {
    private static final double lengthPerLoader = 40.0;
    private static final double passengersPerMinute = 50.0;

    public BigCruise(String identifier, int arrivalTime, int cruiseLength, int numOfPassengers) {
        super(identifier, arrivalTime, (int) Math.ceil(cruiseLength / BigCruise.lengthPerLoader),
                (int) Math.ceil(numOfPassengers / BigCruise.passengersPerMinute));
    }
}

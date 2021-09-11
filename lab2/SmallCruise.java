class SmallCruise extends Cruise {
    private static final int numOfLoader = 1;
    private static final int serviceTime = 30;

    public SmallCruise(String identifier, int arrivalTime) {
        super(identifier, arrivalTime, SmallCruise.numOfLoader, SmallCruise.serviceTime);
    }
}

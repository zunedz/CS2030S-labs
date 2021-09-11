class Cruise {
    private final String identifier;
    private final int arrivalTime;
    private final int numOfLoader;
    private final int serviceTime;
    private static final int hourMinuteDivider = 100;
    private static final int hourToMinute = 60;

    public Cruise(String identifier, int arrivalTime, int numOfLoader, int serviceTime) {
        this.identifier = identifier;
        this.arrivalTime = arrivalTime;
        this.numOfLoader = numOfLoader;
        this.serviceTime = serviceTime;
    }

    Cruise extendDuration() {
        return new Cruise(identifier, arrivalTime, numOfLoader, serviceTime + hourToMinute);
    }

    int getServiceCompletionTime() {
        int hour = (int) this.arrivalTime / Cruise.hourMinuteDivider;
        int minutes = this.arrivalTime % Cruise.hourMinuteDivider + this.serviceTime;
        return hour * Cruise.hourToMinute + minutes;
    }

    int getArrivalTime() {
        int hour = (int) this.arrivalTime / Cruise.hourMinuteDivider;
        int minutes = this.arrivalTime % Cruise.hourMinuteDivider;
        return hour * Cruise.hourToMinute + minutes;
    }

    int getNumOfLoadersRequired() {
        return this.numOfLoader;
    } 

    @Override
    public String toString() {
        return String.format("%s@%04d", this.identifier, this.arrivalTime); 
    }

}

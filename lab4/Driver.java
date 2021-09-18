abstract class Driver implements Comparable<Driver> {
    private final String licenseNumber;
    private final int waitingTime;

    Driver(String licenseNumber, int waitingTime) {
        this.licenseNumber = licenseNumber;
        this.waitingTime = waitingTime;
    }

    abstract Services chooseService(Request request);

    @Override
    public int compareTo(Driver otherDriver) {
        return this.waitingTime - otherDriver.waitingTime; 
    }

    @Override
    public String toString() {
        return String.format("%s (%d mins away)", licenseNumber, waitingTime);
    }
}

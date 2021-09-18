class NormalCab implements Drivers {
    private final String licenseNumber;
    private final int waitingTime;

    NormalCab(String licenseNumber, int waitingTime) {
        this.licenseNumber = licenseNumber;
        this.waitingTime = waitingTime;
    }

    @Override
    public String toString() {
        return String.format("%s (%d mins away) NormalCab", licenseNumber, waitingTime);
    }
}

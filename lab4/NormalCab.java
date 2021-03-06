class NormalCab extends Driver {
    public NormalCab(String licenseNumber, int waitingTime) {
        super(licenseNumber, waitingTime);
    }
    
    @Override
    public Services chooseService(Request request) {
        JustRide justRide = new JustRide();
        TakeACab takeACab = new TakeACab();
        int justRideFare = justRide.computeFare(request);
        int takeACabFare = takeACab.computeFare(request);
        if (justRideFare < takeACabFare) {
            return justRide;
        }
        return takeACab;
    } 

    @Override
    public String toString() {
        String superString = super.toString();
        return superString + " NormalCab";
    }
}

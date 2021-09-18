class PrivateCar extends Driver {
    public PrivateCar(String licenseNumber, int waitingTime) {
        super(licenseNumber, waitingTime);
    }

    @Override
    public Services chooseService(Request request) {
        JustRide justRide = new JustRide();
        ShareARide shareARide = new ShareARide();
        int justRideFare = justRide.computeFare(request);
        int shareARideFare = shareARide.computeFare(request);
        if (justRideFare < shareARideFare) {
            return justRide;
        }
        return shareARide;
    } 

    @Override
    public String toString() {
        String superString = super.toString();
        return superString + " PrivateCar";
    }
}

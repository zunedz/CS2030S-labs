class Loader {
    private final int identifier;
    private final Cruise cruise;

    public Loader(int identifier, Cruise cruise) {
        this.identifier = identifier;
        this.cruise = cruise;
    }
    
    boolean canServe(Cruise cruise) {
        return this.getNextAvailableTime() <= cruise.getArrivalTime();    
    }

    int getIdentifier() {
        return this.identifier;
    }

    int getNextAvailableTime() {
        return cruise.getServiceCompletionTime();
    }

    Loader serve(Cruise cruise) {
        if (canServe(cruise)) {
            return new Loader(this.identifier, cruise);
        } else {
            return this;
        }
    }

    @Override
    public String toString() {
        return String.format("Loader %d serving " + this.cruise, this.identifier);
    }
}

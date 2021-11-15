package cs2030.simulator;

class Rest extends Event {
    private final int serverId;

    Rest(double time, int serverId) {
        super(time, new Customer(0, 0));
        this.serverId = serverId;
    }

    @Override
    public String toString() {
        return super.toString() + "resting";
    }

    @Override
    Rest nextEvent(double time, Customer customer, String next, int serverId, boolean isSelf) {
        return this;
    }

    @Override
    String getType() {
        return "Rest";
    }
    
    int getServerId() {
        return this.serverId;
    }
}
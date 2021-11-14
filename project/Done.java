package cs2030.simulator;

class Done extends Event {
    private final int serverId;

    Done(double time, Customer customer, int serverId) {
        super(time, customer);
        this.serverId = serverId;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("done serving by server %d", this.serverId);
    }

    @Override
    Event nextEvent(double time, Customer customer, String next, int serverId) {
        return this;
    }

    int getServerId() {
        return this.serverId;
    }
}
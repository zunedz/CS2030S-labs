package cs2030.simulator;

class Wait extends Event {
    private final int serverId;

    Wait(double time, Customer customer, int serverId) {
        super(time, customer);
        this.serverId = serverId;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("waits at server %d", this.serverId);
    }

    @Override
    Serve nextEvent(double time, Customer customer, String next, int serverId) {
        return new Serve(time, customer, serverId);
    }

    int getServerId() {
        return this.serverId;
    }
}
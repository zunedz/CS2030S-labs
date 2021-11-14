package cs2030.simulator;

class Serve extends Event {
    private final int serverId;
    Serve(double time, Customer customer, int serverId) {
        super(time, customer);
        this.serverId = serverId;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("serves by server %d", this.serverId);
    }

    int getServerId() {
        return this.serverId;
    }
    
    @Override
    Done nextEvent(double time, Customer customer, String next, int serverId) {
        return new Done(time, customer, serverId);
    }
}
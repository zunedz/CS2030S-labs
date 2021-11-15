package cs2030.simulator;

class Wait extends Event {
    private final int serverId;
    private final boolean isSelf;

    Wait(double time, Customer customer, int serverId) {
        super(time, customer);
        this.serverId = serverId;
        this.isSelf = false;
    }

    Wait(double time, Customer customer, int serverId, boolean isSelf) {
        super(time, customer);
        this.serverId = serverId;
        this.isSelf = isSelf;
    }

    @Override
    public String toString() {
        if (isSelf) {
            return super.toString() + String.format("waits at self-check %d", this.serverId);
        }
        return super.toString() + String.format("waits at server %d", this.serverId);
    }

    @Override
    Serve nextEvent(double time, Customer customer, String next, int serverId, boolean isSelf) {
        return new Serve(time, customer, serverId, isSelf);
    }

    int getServerId() {
        return this.serverId;
    }

    @Override
    String  getType() {
        return "Wait";
    }
}
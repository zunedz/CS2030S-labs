package cs2030.simulator;

class Done extends Event {
    private final int serverId;
    private final boolean isSelf;

    Done(double time, Customer customer, int serverId) {
        super(time, customer);
        this.serverId = serverId;
        this.isSelf = false;
    }

    Done(double time, Customer customer, int serverId, boolean isSelf) {
        super(time, customer);
        this.serverId = serverId;
        this.isSelf = isSelf;
    }

    @Override
    String getType() {
        return "Done";
    }
    
    @Override
    public String toString() {
        if (isSelf) {
            return super.toString() + String.format("done serving by self-check %d", this.serverId);
        }
        return super.toString() + String.format("done serving by server %d", this.serverId);
    }

    @Override
    Rest nextEvent(double time, Customer customer, String next, int serverId, boolean isSelf) {
        return new Rest(time, serverId);
    }

    int getServerId() {
        return this.serverId;
    }
}
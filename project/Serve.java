package cs2030.simulator;

class Serve extends Event {
    private final int serverId;
    private final boolean isSelf;

    Serve(double time, Customer customer, int serverId) {
        super(time, customer);
        this.serverId = serverId;
        this.isSelf = false;
    }

    Serve(double time, Customer customer, int serverId, boolean isSelf) {
        super(time, customer);
        this.serverId = serverId;
        this.isSelf = isSelf;
    }

    @Override
    public String toString() {
        if (isSelf) {
            return super.toString() + String.format("serves by self-check %d", this.serverId);
        }
        return super.toString() + String.format("serves by server %d", this.serverId);
    }

    int getServerId() {
        return this.serverId;
    }
    
    @Override
    Done nextEvent(double time, Customer customer, String next, int serverId, boolean isSelf) {
        return new Done(time, customer, serverId, isSelf);
    }

    @Override
    String getType()  {
        return "Serve";
    }
}
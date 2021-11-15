package cs2030.simulator;

class Arrive extends Event {
    Arrive(double time, Customer customer) {
        super(time, customer);
    }

    @Override
    String getType() {
        return "Arrive";
    }
    
    @Override
    public String toString() {
        return super.toString() + "arrives";
    }
    
    @Override
    Event nextEvent(double time, Customer customer, String next, int serverId, boolean isSelf) {
        if (next.equals("serve")) {
            return new Serve(time, customer, serverId, isSelf);
        } else if (next.equals("wait")) {
            return new Wait(time, customer, serverId, isSelf);
        } else {
            return new Leave(time, customer);
        }
    }
}

package cs2030.simulator;

class Arrive extends Event {
    Arrive(double time, Customer customer) {
        super(time, customer);
    }

    @Override
    public String toString() {
        return super.toString() + "arrives";
    }
    
    @Override
    Event nextEvent(double time, Customer customer, String next, int serverId) {
        if (next.equals("serve")) {
            return new Serve(time, customer, serverId);
        } else if (next.equals("wait")) {
            return new Wait(time, customer, serverId);
        } else {
            return new Leave(time, customer);
        }
    }
}

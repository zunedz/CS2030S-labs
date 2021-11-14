package cs2030.simulator;

class Leave extends Event {
    Leave(double time, Customer customer) {
        super(time, customer);
    }

    @Override
    public String toString() {
        return super.toString() + "leaves";
    } 

    Event nextEvent(double time, Customer customer, String next, int serverId)  {
        return this;
    }
}
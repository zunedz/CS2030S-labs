package cs2030.simulator;

abstract class Event {
    private final double time;
    private final Customer customer;

    Event(double time, Customer customer) {
        this.time = time;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return String.format("%.3f %s ", time, customer);
    }

    double getTime() {
        return this.time;
    }

    Customer getCustomer() {
        return this.customer;
    }

    int getId() {
        return customer.getId();
    }
    
    double getWaitingTime() {
        return this.time - this.customer.getTime();
    }
    
    abstract String getType();
    
    abstract Event nextEvent(double time, Customer customer, 
        String next, int serverId, boolean isSelf);
}

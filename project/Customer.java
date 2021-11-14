package cs2030.simulator;

class Customer {
    private final int id;
    private final double arrivalTime;
    private final double serveTime;

    Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serveTime = 1;
    }

    Customer(int id, double arrivalTime, double serveTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serveTime = serveTime;
    }
    double getTime() {
        return this.arrivalTime;
    }
    
    int getId() {
        return this.id;
    }

    double getServeTime() {
        return this.serveTime;
    }

    @Override
    public String toString() {
        return "" + this.id;
    }
}

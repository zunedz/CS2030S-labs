package cs2030.simulator;

import java.util.function.Supplier;

class Customer {
    private final int id;
    private final double arrivalTime;
    private final Supplier<Double> serveTime;

    Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serveTime = () -> 1.0;
    }

    Customer(int id, double arrivalTime, double serveTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serveTime = () -> serveTime;
    }

    Customer(int id, double arrivalTime, Supplier<Double> serveTime) {
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
        return this.serveTime.get();
    }

    @Override
    public String toString() {
        return "" + this.id;
    }

    boolean isGreedy() {
        return false;
    }
}

package cs2030.simulator;

import java.util.function.Supplier;

class GreedyCustomer extends Customer {
    GreedyCustomer(int id, double arrivalTime, Supplier<Double> serveTime) {
        super(id, arrivalTime, serveTime);
    }

    @Override
    public String toString() {
        return super.toString() + "(greedy)";
    }

    @Override
    boolean isGreedy() {
        return true;
    }
}
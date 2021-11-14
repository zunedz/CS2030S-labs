package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Simulator {
    private final PriorityQueue<Event> pq = new PriorityQueue<Event>(new EventComparator());
    private final List<Server> serverList;
    private final int numOfServers;

    public Simulator(int numOfServers, List<Double> arrivalTimes) {
        this.numOfServers = numOfServers;
        this.serverList = new ArrayList<Server>();
        for (int i = 0; i < arrivalTimes.size(); i++) {
            Customer customer = new Customer(i + 1, arrivalTimes.get(i));
            Event newEvent = new Arrive(customer.getTime(), customer);
            pq.add(newEvent);
        }
        for (int i = 0; i < numOfServers; i++)  {
            serverList.add(new Server(i + 1, 1));
        }
    }

    public Simulator(int numOfServers, List<Double> arrivalTimes, List<Double> servingTimes, int maxQueLength) {
        this.numOfServers = numOfServers;
        this.serverList = new ArrayList<Server>();
        for (int i = 0; i < arrivalTimes.size(); i++) {
            Customer customer = new Customer(i + 1, arrivalTimes.get(i), servingTimes.get(i));
            Event newEvent = new Arrive(customer.getTime(), customer);
            pq.add(newEvent);
        }
        for (int i = 0; i < numOfServers; i++)  {
            serverList.add(new Server(i + 1, maxQueLength));
        }
    }

    public void simulate() {
        while (!pq.isEmpty()) {
            Event current = pq.poll();
            System.out.println(current);
            if (current instanceof Arrive) {
                Arrive newEvent = (Arrive) current;
                handleArrive(newEvent); 
            } else if (current instanceof Serve) {
                Serve newEvent = (Serve) current;
                handleServe(newEvent);
            } else if (current instanceof Wait) {
                Wait newEvent = (Wait) current;
                handleWait(newEvent);
            } else if (current instanceof Done) {
                Done newEvent = (Done) current;
                handleDone(newEvent);
            }
        } 
    }

    void handleArrive(Arrive current) {
        double time = current.getTime();
        Customer cust = current.getCustomer();
        for (int i = 0; i < numOfServers; i++) {
            Server server = serverList.get(i);
            int id = server.getServerId();
            if (server.canServe(current)) {        
                pq.add(current.nextEvent(time, cust, "serve", id));
                return;
            } 
        }
        for (int i = 0; i < numOfServers; i++) {
            Server server = serverList.get(i);
            int id = server.getServerId();
            if (server.canWait(current)) {
                pq.add(current.nextEvent(time, cust, "wait", id));
                return;
            } 
        }
        pq.add(current.nextEvent(time,  cust, "leave", -1));
        
    }

    void handleServe(Serve current) {
        double time = current.getTime();
        Customer cust = current.getCustomer();
        int serverId = current.getServerId();
        Done done = current.nextEvent(time + cust.getServeTime(), cust, "done", serverId);
        serverList.get(serverId - 1).serveEvent(done);
        pq.add(done);
    }

    void handleWait(Wait current) {
        double time = current.getTime();
        Customer cust = current.getCustomer();
        int serverId = current.getServerId();
        //add queue
        Server server = serverList.get(serverId - 1);
        server.queEvent(current);
    }

    void handleDone(Done current) {
        //handle server queue
        int serverId = current.getServerId();
        Server server = serverList.get(serverId - 1);
        server.dequeEvent(pq);
    }
}


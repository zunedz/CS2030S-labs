package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.LinkedList;

public class Simulator {
    private final PriorityQueue<Event> pq = new PriorityQueue<Event>(new EventComparator());
    private final List<Server> serverList;
    private final int numOfServers;
    private final int numOfAutoServers;
    private final LinkedList<Double> restTimes;

    public Simulator(int numOfServers, List<Double> arrivalTimes) {
        this.numOfServers = numOfServers;
        this.numOfAutoServers = 0;
        this.serverList = new ArrayList<Server>();
        this.restTimes = new LinkedList<Double>();
        for (int i = 0; i < arrivalTimes.size(); i++) {
            Customer customer = new Customer(i + 1, arrivalTimes.get(i));
            Event newEvent = new Arrive(customer.getTime(), customer);
            pq.add(newEvent);
            restTimes.add(0.0);
        }
        for (int i = 0; i < numOfServers; i++)  {
            serverList.add(new Server(i + 1, 1));
        }

    }

    public Simulator(int numOfServers, List<Double> arrivalTimes, List<Double> servingTimes, int maxQueLength) {
        this.numOfServers = numOfServers;
        this.numOfAutoServers = 0;
        this.serverList = new ArrayList<Server>();
        this.restTimes = new LinkedList<Double>();
        for (int i = 0; i < arrivalTimes.size(); i++) {
            Customer customer = new Customer(i + 1, arrivalTimes.get(i), servingTimes.get(i));
            Event newEvent = new Arrive(customer.getTime(), customer);
            pq.add(newEvent);
            restTimes.add(0.0);
        }
        for (int i = 0; i < numOfServers; i++)  {
            serverList.add(new Server(i + 1, maxQueLength));
        }
    }

    public Simulator(int numOfServers, List<Double> arrivalTimes, List<Double> servingTimes, int maxQueLength, LinkedList<Double> restTimes) {
        this.numOfServers = numOfServers;
        this.numOfAutoServers = 0;
        this.serverList = new ArrayList<Server>();
        this.restTimes = restTimes;
        for (int i = 0; i < arrivalTimes.size(); i++) {
            Customer customer = new Customer(i + 1, arrivalTimes.get(i), servingTimes.get(i));
            Event newEvent = new Arrive(customer.getTime(), customer);
            pq.add(newEvent);
        }
        for (int i = 0; i < numOfServers; i++)  {
            serverList.add(new Server(i + 1, maxQueLength));
        }
    }

    public Simulator(int numOfServers, int numOfAutoServers, List<Double> arrivalTimes, List<Double> servingTimes, int maxQueLength, LinkedList<Double> restTimes) {
        this.numOfServers = numOfServers;
        this.numOfAutoServers = numOfAutoServers;
        this.serverList = new ArrayList<Server>();
        this.restTimes = restTimes;
        for (int i = 0; i < arrivalTimes.size(); i++) {
            Customer customer = new Customer(i + 1, arrivalTimes.get(i), servingTimes.get(i));
            Event newEvent = new Arrive(customer.getTime(), customer);
            pq.add(newEvent);
        }
        for (int i = 0; i < numOfServers; i++)  {
            serverList.add(new Server(i + 1, maxQueLength));
        }
        for (int i = numOfServers; i < numOfServers + numOfAutoServers; i++)  {
            serverList.add(new AutoServer(i + 1, maxQueLength));
        }

    }

    public void simulate() {
        while (!pq.isEmpty()) {
            Event current = pq.poll();
            if (current instanceof Rest) {
                Rest newEvent = (Rest) current;
                handleRest(newEvent);
                continue;
            } else if (current instanceof Arrive) {
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
            System.out.println(current);
        } 
    }

    void handleArrive(Arrive current) {
        double time = current.getTime();
        Customer cust = current.getCustomer();
        for (int i = 0; i < numOfServers + numOfAutoServers; i++) {
            Server server = serverList.get(i);
            int id = server.getServerId();
            if (server.canServe(current)) {        
                pq.add(current.nextEvent(time, cust, "serve", id));
                return;
            } 
        }
        for (int i = 0; i < numOfServers + numOfAutoServers; i++) {
            Server server = serverList.get(i);
            int id = server.getServerId();
            if (server.canWait(current)) {
                //check if autoserver
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
        double time = current.getTime();
        Customer cust = current.getCustomer();
        int serverId = current.getServerId();
        //handle rest time
        //handle if server is auto
        Server server = serverList.get(serverId - 1);
        Rest rest;
        Done done;
        if (serverId > numOfServers) {
            rest = current.nextEvent(time, cust, "rest", serverId);
            done = new Done(time, cust, serverId);
        } else {
            double restTime = restTimes.poll();
            rest = current.nextEvent(time + restTime, cust, "rest", serverId);
            done = new Done(time + restTime, cust, serverId);
        }
        server.serveEvent(done);
        pq.add(rest);
    }

    void handleRest(Rest current) {
        //handle server queue
        int serverId = current.getServerId();
        Server server = serverList.get(serverId - 1);
        server.dequeEvent(pq);
    }
}


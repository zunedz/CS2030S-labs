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
    private final List<Double> servingTimes;
    private final RandomGenerator rand;
    private final double rhoRest;
    private final int n;

    /**
     * Simulator constructor for level1.
     * @param numOfServers - total number of human servers
     * @param arrivalTimes - list of customers' arrival times
     */
    public Simulator(int numOfServers, List<Double> arrivalTimes) {
        this.numOfServers = numOfServers;
        this.numOfAutoServers = 0;
        this.n = arrivalTimes.size();
        this.serverList = new ArrayList<Server>();
        this.servingTimes = new ArrayList<Double>();
        this.restTimes = new LinkedList<Double>();
        this.rhoRest = 1.0;
        for (int i = 0; i < arrivalTimes.size(); i++) {
            Customer customer = new Customer(i + 1, arrivalTimes.get(i));
            Event newEvent = new Arrive(customer.getTime(), customer);
            pq.add(newEvent);
            servingTimes.add(1.0);
            restTimes.add(0.0);
        }
        for (int i = 0; i < numOfServers; i++)  {
            serverList.add(new Server(i + 1, 1));
        }
        this.rand = new RandomGenerator(0, 0, 0, 0);
    }

    /** 
     * Simulator constructor for level2.
     * @param numOfServers - total number of human servers
     * @param arrivalTimes - list of customers' arrival times
     * @param servingTimes - list of customers' serving times
     * @param maxQueLength - maximum number of person queueing
     */
    public Simulator(int numOfServers, List<Double> arrivalTimes, 
        List<Double> servingTimes, int maxQueLength) {
        this.numOfServers = numOfServers;
        this.numOfAutoServers = 0;
        this.n = arrivalTimes.size();
        this.serverList = new ArrayList<Server>();
        this.restTimes = new LinkedList<Double>();
        this.servingTimes = servingTimes;
        this.rhoRest = 1.0;
        for (int i = 0; i < arrivalTimes.size(); i++) {
            Customer customer = new Customer(i + 1, arrivalTimes.get(i), servingTimes.get(i));
            Event newEvent = new Arrive(customer.getTime(), customer);
            pq.add(newEvent);
            restTimes.add(0.0);
        }
        for (int i = 0; i < numOfServers; i++)  {
            serverList.add(new Server(i + 1, maxQueLength));
        }
        this.rand = new RandomGenerator(0, 0, 0, 0);
    }

    /**
     * Simulator constructor for level3.
     * @param numOfServers - total number of human servers
     * @param arrivalTimes - list of customers' arrival times
     * @param servingTimes - list of customers' serving times
     * @param maxQueLength - maximum number of person queueing
     * @param restTimes - Queue of resting times for server
     */
    public Simulator(int numOfServers, List<Double> arrivalTimes, 
        List<Double> servingTimes, int maxQueLength, LinkedList<Double> restTimes) {
        this.numOfServers = numOfServers;
        this.numOfAutoServers = 0;
        this.n = arrivalTimes.size();
        this.serverList = new ArrayList<Server>();
        this.restTimes = restTimes;
        this.servingTimes = servingTimes;
        this.rhoRest = 1.0;
        for (int i = 0; i < arrivalTimes.size(); i++) {
            Customer customer = new Customer(i + 1, arrivalTimes.get(i), servingTimes.get(i));
            Event newEvent = new Arrive(customer.getTime(), customer);
            pq.add(newEvent);
        }
        for (int i = 0; i < numOfServers; i++)  {
            serverList.add(new Server(i + 1, maxQueLength));
        }
        this.rand = new RandomGenerator(0, 0, 0, 0);
    }

    /**
     * Simulator constructor for level4.
     * @param numOfServers - total number of human servers
     * @param numOfAutoServers - total number of  self-checkout counter
     * @param arrivalTimes - list of customers' arrival times
     * @param servingTimes - list of customers' serving times
     * @param maxQueLength - maximum number of person queueing
     * @param restTimes - Queue of resting times for server
     */
    public Simulator(int numOfServers, int numOfAutoServers, List<Double> arrivalTimes, 
        List<Double> servingTimes, int maxQueLength, LinkedList<Double> restTimes) {
        this.numOfServers = numOfServers;
        this.numOfAutoServers = numOfAutoServers;
        this.n = arrivalTimes.size();
        this.serverList = new ArrayList<Server>();
        this.restTimes = restTimes;
        this.servingTimes = servingTimes;
        this.rhoRest = 1.0;
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
        this.rand = new RandomGenerator(0, 0, 0, 0);
    }

    /**
     * Simulator constructor for level5.
     * @param seed - seed for RandomGenerator
     * @param numOfServers - total number  of human servers
     * @param numOfAutoServers - total number of  self-checkout counter
     * @param maxQueLength - maximum number of person queueing
     * @param n - total number of customers
     * @param lambda  - arrival rate
     * @param mu - service rate
     * @param rho - server resting rate
     * @param rhoRest - probability of  resting
     * @param rhoGreedy - probability of greedy customer
     */
    public Simulator(int seed, int numOfServers, int numOfAutoServers, int maxQueLength, 
        int n, double lambda, double mu, double rho, double rhoRest, double rhoGreedy) {
        this.rand = new RandomGenerator(seed, lambda, mu, rho);
        this.numOfServers = numOfServers;
        this.numOfAutoServers = numOfAutoServers;
        this.n = n;
        this.serverList = new ArrayList<Server>();
        this.restTimes = new LinkedList<Double>();
        this.servingTimes = new ArrayList<Double>();
        this.rhoRest = rhoRest;
        double now = 0.0;
        for (int i = 0; i < n; i++) { //init rest times
            double temp = this.rand.genRestPeriod();
            restTimes.add(temp);
        }
        for (int i = 0; i < n; i++) { //init serving times
            double temp = this.rand.genServiceTime();
            servingTimes.add(temp);
        }
        for (int i = 0; i < n; i++) {
            double temp = rand.genCustomerType();
            Customer customer;
            if (temp < rhoGreedy) {
                customer = new GreedyCustomer(i + 1, now, () -> servingTimes.remove(0));
            } else {
                customer = new Customer(i + 1, now, () -> servingTimes.remove(0));
            }
            Event newEvent = new Arrive(customer.getTime(), customer);
            pq.add(newEvent);
            now = now + this.rand.genInterArrivalTime();
        }
        for (int i = 0; i < numOfServers; i++)  {
            serverList.add(new Server(i + 1, maxQueLength));
        }
        for (int i = numOfServers; i < numOfServers + numOfAutoServers; i++)  {
            serverList.add(new AutoServer(i + 1, maxQueLength));
        }
    }

    /**
     * Simulate the Simulator.
     */
    public void simulate() {
        int served = 0;
        double totalWait = 0;
        while (!pq.isEmpty()) {
            Event current = pq.poll();
            if (current.getType().equals("Rest")) {
                Rest newEvent = (Rest) current;
                handleRest(newEvent);
                continue;
            } else if (current.getType().equals("Arrive")) {
                Arrive newEvent = (Arrive) current;
                if (current.getCustomer().isGreedy()) {
                    handleGreedyArrive(newEvent);
                } else {
                    handleArrive(newEvent);
                }
            } else if (current.getType().equals("Serve")) {
                Serve newEvent = (Serve) current;
                served += 1;
                totalWait += current.getWaitingTime();
                handleServe(newEvent);
            } else if (current.getType().equals("Wait")) {
                Wait newEvent = (Wait) current;
                handleWait(newEvent);
            } else if (current.getType().equals("Done")) {
                Done newEvent = (Done) current;
                handleDone(newEvent);
            }  
            System.out.println(current);
        } 
        double average = totalWait / served;
        System.out.println(String.format("[%.3f %d %d]", average, served, n - served));
    }

    void handleArrive(Arrive current) {
        double time = current.getTime();
        Customer cust = current.getCustomer();
        for (int i = 0; i < numOfServers + numOfAutoServers; i++) {
            Server server = serverList.get(i);
            int id = server.getServerId();
            if (server.canServe(current)) {        
                pq.add(current.nextEvent(time, cust, "serve", id, id > numOfServers));
                return;
            } 
        }
        for (int i = 0; i < numOfServers + numOfAutoServers; i++) {
            Server server = serverList.get(i);
            int id = server.getServerId();
            if (server.canWait(current)) {
                //check if autoserver
                pq.add(current.nextEvent(time, cust, "wait", id, id > numOfServers));
                return;
            } 
        }
        pq.add(current.nextEvent(time,  cust, "leave", -1, false));
    }

    void handleGreedyArrive(Arrive current) {
        double time = current.getTime();
        Customer cust = current.getCustomer();
        for (int i = 0; i < numOfServers + numOfAutoServers; i++) {
            Server server = serverList.get(i);
            int id = server.getServerId();
            if (server.canServe(current)) {        
                pq.add(current.nextEvent(time, cust, "serve", id, id > numOfServers));
                return;
            } 
        }
        int indexServer = 0;
        int queueSize = Integer.MAX_VALUE;
        boolean canQueue = false;
        for (int i = 0; i < numOfServers + numOfAutoServers; i++) {
            Server server = serverList.get(i);
            int id = server.getServerId();
            if (server.canWait(current)) {
                canQueue = true;
                if (server.getQueSize() < queueSize) {
                    indexServer = i;
                    queueSize = server.getQueSize();
                }
            } 
        }
        if (canQueue) {
            Server server = serverList.get(indexServer);
            int id = server.getServerId();
            pq.add(current.nextEvent(time, cust, "wait", id, id > numOfServers));
            return;
        }
        pq.add(current.nextEvent(time, cust, "leave", -1, false));
    }

    void handleServe(Serve current) {
        //use the q 
        double time = current.getTime();
        Customer cust = current.getCustomer();
        int serverId = current.getServerId();
        Done done = current.nextEvent(time + cust.getServeTime(), cust, 
            "done", serverId, serverId > numOfServers);
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
            rest = current.nextEvent(time, cust, "rest", serverId, true);
            done = new Done(time, cust, serverId);
        } else {
            double temp = rand.genRandomRest();
            if (temp < this.rhoRest) {
                double restTime = restTimes.poll();
                rest = current.nextEvent(time + restTime, cust, "rest", serverId, false);
                done = new Done(time + restTime, cust, serverId);
            } else {
                rest = current.nextEvent(time, cust, "rest", serverId, false);
                done = new Done(time, cust, serverId);
            }
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


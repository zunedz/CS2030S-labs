package cs2030.simulator;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Server {
    private final List<Done> event;
    private final int id;
    private final LinkedList<Wait>  q;
    private final int maxSize;

    Server(int id, int maxSize) {
        this.event = new ArrayList<Done>();
        this.id = id;
        this.q = new LinkedList<Wait>();
        this.maxSize = maxSize;
    }

    boolean canServe(Event other) {
        return event.isEmpty() || new EventComparator().compare(event.get(0), other) < 0;
    }

    boolean canWait(Event other) {
        return this.q.size() < maxSize;
    }

    int getServerId() {
        return this.id;
    }

    double getTime() {
        return this.event.get(0).getTime();
    }

    Done getEvent() {
        return event.get(0);
    }

    void serveEvent(Done other) {
        if (event.isEmpty()) {
            this.event.add(other);
            return;
        }
        this.event.set(0, other);
    }

    void queEvent(Wait other) {
        this.q.add(other);
    }

    void dequeEvent(PriorityQueue<Event> pq) {
        if (!q.isEmpty()) {
            Done oldEvent = event.get(0);
            Wait temp = q.poll();
            Serve newEvent = temp.nextEvent(oldEvent.getTime(), temp.getCustomer(), "serve", getServerId());
            pq.add(newEvent);
        }
    }

    int getMaxSize() {
        return maxSize;
    }

    int getQueSize() {
        return this.q.size();
    }
}
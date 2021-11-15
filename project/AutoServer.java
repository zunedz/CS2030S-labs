package cs2030.simulator;

import java.util.LinkedList;
import java.util.PriorityQueue;

class AutoServer extends Server {
    private static final LinkedList<Wait> sharedQ = new LinkedList<Wait>();
    
    AutoServer(int id, int maxSize) {
        super(id, maxSize);
    }

    @Override
    boolean canWait(Event other) {
        return sharedQ.size() < this.getMaxSize();
    }

    @Override
    void queEvent(Wait other) {
        sharedQ.add(other);
    }

    @Override
    void dequeEvent(PriorityQueue<Event> pq) {
        if (!sharedQ.isEmpty()) {
            Done oldEvent = getEvent();
            Wait temp = sharedQ.poll();
            Serve newEvent = temp.nextEvent(oldEvent.getTime(), 
                temp.getCustomer(), "serve", getServerId());
            pq.add(newEvent);
        }
    }
}
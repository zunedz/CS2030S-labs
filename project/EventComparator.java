package cs2030.simulator;

import java.util.Comparator;

class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event event1, Event event2) {
        double delta = event1.getTime() - event2.getTime();
        int delta2 = event1.getId() - event2.getId();
        if (delta < 0) {
            return -1;
        } else if (delta > 0) {
            return 1;
        } 
        return delta2;
    }
}

package calendar;

import calendar.Event;
/**
public class EventCalendar {
    private Event[] events; //the array holding the list of events
    private int numEvents; //current number of events in the array
    public static final int NOT_FOUND = -1;

    //EventCalendar Constructor
    public EventCalendar(Event[] events, int numEvents) {
        this.events = new Event[4]; //array starts with initial capacity of 4
        this.numEvents = numEvents;
    }

    private int find(Event event) {//find the index of the event;
        int n = events.length;
        for (int i = 0; i < n; i++) {
            if (event.equals(event)) {
                return i; //return the index of where the event is
            }
        }
        return NOT_FOUND; //did not find list
    }

    private void grow() {
        int n = events.length;
        Event[] temp = [n + 4]; //increase capacity by 4
        for (int i = 0; i < n; i++) { //copy array and store values temporarily
            temp[i] = events[i];
        }

        this.events = new Event[n + 4];//resize current array
        this.events = temp; //since they are the same size, we can simply set equal to copy elements
        return;
    }

    private boolean hasSpace() {
        for (int i = 0; i < events.length; i++) {
            if (events[i] == null) { //if theres a spot where object = null then it has space to add one
                return true;
            }
        }

        return false;
    }

    public boolean add(Event event) {
        if (!hasSpace) {
            grow(); //if the array does not have space we have to resize it
        }
        for (int i = 0; i < events.length; i++) {
            if (events[i] == null) { //adding it to the end of the list/where ever theres an empty spot
                events[i] = event;
                return true; //once we added the event we can return
            }
        }
        return false; //something to return. Probably wont ever return this seeing that we can always resize before
    }

    public boolean remove(Event event) {
        int index = find(event);
        int lastIndex = events.length - 1
        if (index == lastIndex) {
            event[index] = new Event(); //essentially making everything null since we dont actually decrease capacity
            return true; //the simplest case where we wont have to move any elements up
        }
        if (index != NOT_FOUND) { //as long as its an actual element in the list
            for (int i = index; i < events.length - 1; i++) { //we dont have to go the last element well just set it null manually
                events[index] = events[i + 1]; //next element
            }
            events[lastIndex] = new Event(); //array should have been pushed up one so last element can be set to null
            return true;
        }

        return false;

    }

    public boolean contains(Event event) {
        int didWeFindIt = find(event);
        if (didWeFindIt == NOT_FOUND) { //if we cant find it in the list then list does not contain it
            return false;
        }
        return true;
    }

    public void print() {
        for (int i = 0; i < events.length; i++) {
            System.out.println(events[i].toString()); //this needs to be revised once toString is updated
        }
    }

    public void printByDate() {
    } //ordered by date and timeslot

    public void printByCampus() {
    } //ordered by campus and building/room

    public void printByDepartment() {
    } //ordered by department


}
*/
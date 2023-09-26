package calendar;

import calendar.Event;

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
            if (events[i].equals(event)) {
                return i; //return the index of where the event is
            }
        }
        return NOT_FOUND; //did not find list
    }

    private void grow() {
        int n = events.length;
        Event[] temp = new Event[n + 4]; //increase capacity by 4
        for (int i = 0; i < n; i++) { //copy array and store values temporarily
            temp[i] = events[i];
        }

        this.events = temp; //since they are the same size, we can simply set equal to copy elements
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
        if (!hasSpace()) {
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
        int lastIndex = events.length - 1;
        if (index == lastIndex) {
            events[index] = null; //essentially making everything null since we dont actually decrease capacity
            return true; //the simplest case where we wont have to move any elements up
        }
        if (index != NOT_FOUND) { //as long as its an actual element in the list
            for (int i = index + 1; i < events.length; i++) { // goes to last element to "shift" it down
                events[i - 1] = events[i]; //next element
            }
            events[lastIndex] = null; //array should have been pushed up one so last element can be set to null
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
        int i = 0;
        while (events[i] != null) {
            System.out.println(this.events[i].toString()); //this needs to be revised once toString is updated
            i++;
        }
    }

    /*
    public void printByDate() {
    } //ordered by date and timeslot

    public void printByCampus() {
    } //ordered by campus and building/room

    public void printByDepartment() {
    } //ordered by department
    */

    public static void main(String[] args) {
        Date date1 = new Date(2023, 12, 31);
        Contact contact1 = new Contact(Department.CS, "cs@rutgers.edu");
        Event event1 = new Event(date1, Timeslot.EVENING, Location.HLL114, contact1, 90);
        Event[] events1 = {};

        EventCalendar eventCalendar = new EventCalendar(events1, 1);
        eventCalendar.add(event1);
        System.out.println("Testing initialization...");
        eventCalendar.print();

        Date date2 = new Date(2024, 2, 14);
        Contact contact2 = new Contact(Department.EE, "ee@rutgers.edu");
        Event event2 = new Event(date2, Timeslot.MORNING, Location.AB2225, contact2, 45);
        eventCalendar.add(event2);
        System.out.println("\nTesting add...");
        eventCalendar.print();

        eventCalendar.remove(event1);
        System.out.println("\nTesting remove...");
        eventCalendar.print();
    }

}
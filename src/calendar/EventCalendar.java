package calendar;

import calendar.Event;

public class EventCalendar {
    private Event[] events; //the array holding the list of events
    private int numEvents; //current number of events in the array
    public static final int NOT_FOUND = -1;

    //EventCalendar Constructor
    public EventCalendar(Event[] events, int numEvents) {
        this.events = new Event[4]; //array starts with initial capacity of 4
        int initialSize = 0;
        this.numEvents = initialSize;
    }

    private int findByElements(Event event){
        Date date = event.getDate();
        Location location = event.getLocation();
        int n = this.numEvents;
        for(int i =0; i<n; i++){
            if(this.events[i].dateEquals(date) && this.events[i].locationEquals(location){
                return i;
            }
        }

        return NOT_FOUND;

    }
    private int find(Event event) {//find the index of the event;
        int n = this.numEvents;
        for (int i = 0; i < n; i++) {
            if (this.events[i].equals(event)) {
                return i; //return the index of where the event is
            }
        }
        int compareFindSearch = findByElements(event);

        if(compareFindSearch != NOT_FOUND){
            return compareFindSearch;
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
                numEvents++;
                return true; //once we added the event we can return
            }
        }
        return false; //something to return. Probably wont ever return this seeing that we can always resize before
    }

    public boolean remove(Event event) {
        int index = find(event);
        int lastIndex = this.numEvents - 1;
        if (index == lastIndex) {
            this.events[index] = null; //essentially making everything null since we dont actually decrease capacity
            this.numEvents--;
            return true; //the simplest case where we wont have to move any elements up
        }
        if (index != NOT_FOUND) { //as long as its an actual element in the list
            for (int i = index + 1; i < this.numEvents; i++) { // goes to last element to "shift" it down
                this.events[i - 1] = this.events[i]; //next element
            }
            this.events[numEvents-1] = null; //array should have been pushed up one so last element can be set to null
            this.numEvents--;
            return true;
        }

        return false;

    }

    public boolean contains(Event event) {
        int didWeFindIt = this.find(event);
        if (didWeFindIt == NOT_FOUND) { //if we cant find it in the list then list does not contain it
            return false;
        }
        return true;
    }


    public void print() {
        System.out.println("* Event calendar *");
        for (int i = 0; i < this.numEvents; i++) {
            System.out.println(this.events[i].toString()); //this needs to be revised once toString is updated
        }
        System.out.println("* end of event calendar *");
    }



    public void printByDate() {
        System.out.println("* Event calendar by event date and start time *");
        if(this.numEvents > 1) { // Insertion sort from: https://www.geeksforgeeks.org/insertion-sort/
            for (int i = 1; i < this.numEvents; ++i) {

                Event event = this.events[i];
                int j = i - 1;

                while (j >= 0 && this.events[j].compareTo(event) > 0) {
                    this.events[j+1] = this.events[j];
                    j = j - 1;
                }
                this.events[j + 1] = event;
            }
        }
        for (int i = 0; i < this.numEvents; i++) {
            System.out.println(this.events[i].toString());
        }
        System.out.println("* end of event calendar *");
    } //ordered by date and timeslot

    private boolean greaterThan(Location location1, Location location2) {
        //System.out.println("location1: " + location1.getCampus() + " location2: " + location2.getCampus() + " result: " + location1.getCampus().compareTo(location2.getCampus()));
        /*if (location1.getCampus().compareTo(location2.getCampus()) > 0) {
            return true;
        }
        else if (location1.getCampus().compareTo(location2.getCampus()) < 0) {
            return false;
        }
        else if (location1.getBuilding().compareTo(location2.getBuilding()) > 0) {
            return true;
        }
        else if (location1.getBuilding().compareTo(location2.getBuilding()) < 0) {
            return false;
        }
        else {
            return false;
        }*/
        return location1.compareTo(location2) > 0;
    }

    public void printByCampus() {
        System.out.println("* Event calendar by campus and building *");
        if(this.numEvents > 1) { // Insertion sort from: https://www.geeksforgeeks.org/insertion-sort/
            for (int i = 1; i < this.numEvents; ++i) {

                Event event = this.events[i];
                int j = i - 1;

                while (j >= 0 && greaterThan(this.events[j].getLocation(), event.getLocation())) {
                    this.events[j+1] = this.events[j];
                    j = j - 1;
                }
                this.events[j + 1] = event;
            }
        }
        for (int i = 0; i < this.numEvents; i++) {
            System.out.println(this.events[i].toString());
        }
        System.out.println("* end of event calendar *");

    } //ordered by campus and building/room

    public void printByDepartment() {
        System.out.println("* Event calendar by department *");
        if(this.numEvents > 1) { // Insertion sort from: https://www.geeksforgeeks.org/insertion-sort/
            for (int i = 1; i < this.numEvents; ++i) {

                Event event = this.events[i];
                int j = i - 1;

                while (j >= 0 && event.getContact().getDepartment().compareTo(this.events[j].getContact().getDepartment()) > 0) {
                    this.events[j+1] = this.events[j];
                    j = j - 1;
                }
                this.events[j + 1] = event;
            }
        }
        for (int i = 0; i < this.numEvents; i++) {
            System.out.println(this.events[i].toString());
        }
        System.out.println("* end of event calendar *");
    } //ordered by department

    public static void main(String[] args) {
        Date date1 = new Date(2024, 2, 14);
        Contact contact1 = new Contact(Department.CS, "cs@rutgers.edu");
        Event event1 = new Event(date1, Timeslot.EVENING, Location.HLL114, contact1, 90);
        Event[] events1 = {};

        EventCalendar eventCalendar = new EventCalendar(events1, 0);
        eventCalendar.add(event1);
        System.out.println("Testing initialization...");
        eventCalendar.print();

        Date date2 = new Date(2023, 11, 17);
        Contact contact2 = new Contact(Department.EE, "ee@rutgers.edu");
        Event event2 = new Event(date2, Timeslot.MORNING, Location.AB2225, contact2, 45);
        eventCalendar.add(event2);

        Date date3 = new Date(2023, 10, 17);
        Contact contact3 = new Contact(Department.EE, "ee@rutgers.edu");
        Event event3 = new Event(date3, Timeslot.MORNING, Location.MU302, contact3, 45);
        eventCalendar.add(event3);

        Date date4 = new Date(2023, 10, 17);
        Contact contact4 = new Contact(Department.EE, "ee@rutgers.edu");
        Event event4 = new Event(date4, Timeslot.MORNING, Location.BE_AUD, contact4, 60);
        eventCalendar.add(event4);

        System.out.println("\nTesting add...");
        eventCalendar.print();
        eventCalendar.printByDate();
        eventCalendar.printByCampus();
        eventCalendar.printByDepartment();

        System.out.println("\nTesting remove...");
        eventCalendar.remove(event3);
        eventCalendar.print();

    }

}
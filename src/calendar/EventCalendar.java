package calendar;

/**
 * Array-based implementation to hold list of events
 * @author Kenrick Eagar, Zachary Derish
 */

public class EventCalendar {
    private Event[] events; //the array holding the list of events
    private int numEvents; //current number of events in the array
    public static final int NOT_FOUND = -1;

    /**
     * EventCalendar Two-Parameter Constructor
     * Creates EventCalendar object
     * @param events array of event objects
     * @param numEvents integer representing the number of events
     */
    public EventCalendar(Event[] events, int numEvents) {
        this.events = new Event[4]; //array starts with initial capacity of 4
        int initialSize = 0;
        this.numEvents = initialSize;
    }

    /**
     * Given an event, method will return integer representing the index of the event in the calendar after finding an event that matches particular elements of event
     *
     * @param event object method that will be searched for
     * @return integer representing the index of the event in the calendar
     */
    private int findByElements(Event event) {
        Date date = event.getDate();
        Location location = event.getLocation();
        int n = this.numEvents;
        for (int i = 0; i < n; i++) {
            if (this.events[i].dateEquals(date) && this.events[i].locationEquals(location)) {
                return i;
            }
        }

        return NOT_FOUND;

    }

    /**
     * Given an event, method will return integer representing the index of the matched event in the calendar
     *
     * @param event object method that will be searched for
     * @return integer representing the index of the event in the calendar
     */
    private int find(Event event) {//find the index of the event;
        int n = this.numEvents;
        for (int i = 0; i < n; i++) {
            if (this.events[i].equals(event)) {
                return i; //return the index of where the event is
            }
        }
        int compareFindSearch = findByElements(event);

        if (compareFindSearch != NOT_FOUND) {
            return compareFindSearch;
        }
        return NOT_FOUND; //did not find list
    }

    /**
     * Void method that will grow EventCalendar events array by 4
     * Method will reassign calendars events array to an array of larger capacity with the same event objects
     */
    private void grow() {
        int n = events.length;
        Event[] temp = new Event[n + 4]; //increase capacity by 4
        for (int i = 0; i < n; i++) { //copy array and store values temporarily
            temp[i] = events[i];
        }

        this.events = temp; //since they are the same size, we can simply set equal to copy elements
    }

    /**
     * Method will search array for a null value indicating an empty space in the array and will return boolean indicating if there is a null index
     *
     * @return will return a boolean indicating true if array has space to add objects (has found a null index), false otherwise
     */
    private boolean hasSpace() {
        for (int i = 0; i < events.length; i++) {
            if (events[i] == null) { //if theres a spot where object = null then it has space to add one
                return true;
            }
        }

        return false;
    }

    /**
     * Given an event, method will add it to the eventCalendar events array and return boolean indicating if it was successfully added
     *
     * @param event object that will be added to events array/EventCalendar
     * @return will return a boolean indicating true if event was successfully added to calendar, false otherwise
     */
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

    /**
     * Given an event, method will remove it from the eventCalendar events array and return boolean indicating if it was successfully removed
     *
     * @param event object that will be removed to events array/EventCalendar
     * @return will return a boolean indicating true if event was successfully removed from events array, false otherwise
     */
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
            this.events[numEvents - 1] = null; //array should have been pushed up one so last element can be set to null
            this.numEvents--;
            return true;
        }

        return false;

    }

    /**
     * Given an event, method will search events array to see if input event exists
     *
     * @param event object that we will be searching for in array
     * @return will return a boolean indicating true if event was found in calendar, false otherwise
     */
    public boolean contains(Event event) {
        int didWeFindIt = this.find(event);
        if (didWeFindIt == NOT_FOUND) { //if we cant find it in the list then list does not contain it
            return false;
        }
        return true;
    }

    /**
     * void method that will print out the eventCalendar
     */
    public void print() {
        System.out.println("* Event calendar *");
        for (int i = 0; i < this.numEvents; i++) {
            System.out.println(this.events[i].toString()); //this needs to be revised once toString is updated
        }
        System.out.println("* end of event calendar *");
    }


    /**
     * Method that will print out sorted events by date using insertion sort
     */
    public void printByDate() {
        System.out.println("* Event calendar by event date and start time *");
        if (this.numEvents > 1) { // Insertion sort from: https://www.geeksforgeeks.org/insertion-sort/
            for (int i = 1; i < this.numEvents; ++i) {

                Event event = this.events[i];
                int j = i - 1;

                while (j >= 0 && this.events[j].compareTo(event) > 0) {
                    this.events[j + 1] = this.events[j];
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

    /**
     *
     * @param location1 first Location object to be compared.
     * @param location2 second Location obejct to be compared.
     * @return true if location1 is greater than location2, false otherwise.
     */
    private boolean greaterThan(Location location1, Location location2) {
        return location1.compareTo(location2) > 0;
    }

    /**
     * Method that will print out sorted events by campus using insertion sort
     */
    public void printByCampus() {
        System.out.println("* Event calendar by campus and building *");
        if (this.numEvents > 1) { // Insertion sort from: https://www.geeksforgeeks.org/insertion-sort/
            for (int i = 1; i < this.numEvents; ++i) {

                Event event = this.events[i];
                int j = i - 1;

                while (j >= 0 && greaterThan(this.events[j].getLocation(), event.getLocation())) {
                    this.events[j + 1] = this.events[j];
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

    /**
     * Method that will print out sorted events by department using insertion sort
     */
    public void printByDepartment() {
        System.out.println("* Event calendar by department *");
        if (this.numEvents > 1) { // Insertion sort from: https://www.geeksforgeeks.org/insertion-sort/
            for (int i = 1; i < this.numEvents; ++i) {

                Event event = this.events[i];
                int j = i - 1;

                while (j >= 0 && event.getContact().getDepartment().compareTo(this.events[j].getContact().getDepartment()) > 0) {
                    this.events[j + 1] = this.events[j];
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


    /**
     * @return integer indicating the current number of events in the events calendar
     */
    public int getNumEvents() {
        return this.numEvents;
    }

}

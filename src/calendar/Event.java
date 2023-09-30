package calendar;

//import java.awt.*; <---- This will be deleted

/**
 * Create, compare and convert Event objects.
 * @author Kenrick Eagar, Zachary Derish
 */
public class Event implements Comparable<Event> {
    private Date date; //the event date
    private Timeslot startTime; //the starting time
    private Location location;
    private Contact contact; //include the department name and email
    private int duration; //in minutes

    /**
     * Event constructor.
     * @param date the Date object associated with the Event.
     * @param startTime the Timeslot object associated with the Event.
     * @param location the Location object associated with the Event.
     * @param contact the Contact object associated with the Event.
     * @param duration the Event duration.
     */
    public Event(Date date, Timeslot startTime, Location location, Contact contact, int duration) {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    /**
     * One-Argument Event Constructor.
     * @param date the Date object associated with the Event.
     */
    public Event(Date date, Location location){
        this.date = date;
        this.location = location;
    }

    /**
     *
     * @param minutesInHour Integer for minutes in an hour.
     * @param hoursInAM Integer for hours in AM period.
     * @param startHour Integer for hour value in start time.
     * @param startMinute Integer for minute value in start time.
     * @param amOrPM String that denotes whether the time is in AM or PM.
     * @return the end time for an AM event as a String.
     */
    private String getEndAM(int minutesInHour, int hoursInAM, int startHour, int startMinute, String amOrPM) {
        int totalMinutes = (startHour * minutesInHour) + startMinute;
        float scheduleMinutes = this.duration + totalMinutes;
        String finalEndTime = "";

        if (scheduleMinutes > (hoursInAM * minutesInHour)) {
            amOrPM = "pm";
            scheduleMinutes = scheduleMinutes - (hoursInAM * minutesInHour);
        }

        if (scheduleMinutes == (hoursInAM * minutesInHour)) {
            amOrPM = "pm";
            return "12:00pm";
        }

        int scheduleHour = (int) Math.floor(scheduleMinutes / minutesInHour);
        if (scheduleHour == 0) {
            scheduleHour = 12;
        }
        int scheduleMin = 0;
        if (amOrPM.equals("am")){
            scheduleMin = (int) scheduleMinutes - (scheduleHour * minutesInHour);
        }
        else {
            scheduleMin = (int) scheduleMinutes;
        }

        String finalMin = Integer.toString(scheduleMin);
        if (scheduleMin < 10) {
            finalMin = "0" + scheduleMin;
        }

        finalEndTime = scheduleHour + ":" + finalMin + amOrPM;

        return finalEndTime;
    }

    /**
     * @param minutesInHour Integer for minutes in an hour.
     * @param startHour Integer for hour value in start time.
     * @param startMinute Integer for minute value in start time.
     * @return the end time for a PM Event as a String.
     */
    private String getEndPM(int minutesInHour, int startHour, int startMinute) {
        float totalMinutes = (startHour * minutesInHour) + startMinute;
        float scheduleTotalMinutes = this.duration + totalMinutes;

        int scheduleHour = (int) Math.floor(scheduleTotalMinutes / minutesInHour);
        if (scheduleHour == 0) {
            scheduleHour = 12;
        }
        int scheduleMin = (int) scheduleTotalMinutes - (scheduleHour * minutesInHour);
        String finalMin = Integer.toString(scheduleMin);
        if (scheduleMin < 10) {
            finalMin = "0" + scheduleMin;
        }

        String finalEndTime = scheduleHour + ":" + finalMin + "pm";

        return finalEndTime;
    }

    /**
     * @param event the Event object to get the end time for.
     * @return the end time for an Event as a string.
     */
    private String getEndTime(Event event) {
        int startHour = Integer.parseInt(event.startTime.getStartHour());
        int startMinute = Integer.parseInt(event.startTime.getStartMinute());
        String amOrPM = "pm";
        if (event.startTime == Timeslot.MORNING) {
            amOrPM = "am";
        }
        int minutesInHour = 60;
        int hoursInAM = 12;
        String finalEndTime = "";
        // Need to add conversions
        if (amOrPM.equals("am")) {
            finalEndTime = getEndAM(minutesInHour, hoursInAM, startHour, startMinute, amOrPM);
        }
        else {
            finalEndTime = getEndPM(minutesInHour, startHour, startMinute);
        }

        return "[End: " + finalEndTime + "]";
    }

    /**
     * @param event the Event object to get the start time for.
     * @return the start time for an Event as a string.
     */
    private String getStartTime(Event event) {
        if (event.startTime == Timeslot.MORNING) {
            return "[Start: " + event.startTime.getStartHour() + ":" + event.startTime.getStartMinute() + "am]";
        }
        return "[Start: " + event.startTime.getStartHour() + ":" + event.startTime.getStartMinute() + "pm]";
    }

    /**
     * @return the Event object as a string.
     */
    @Override
    public String toString() {
        String date = "[Event Date: " + this.date.getDate() + "]";
        String startTime = getStartTime(this);
        String endTime = getEndTime(this); // this part needs tweaking
        String location = "@" + this.location + "(" + this.location.getBuilding() + ", " + this.location.getCampus() + ")";
        String contact = "[Contact: " + this.contact.getDepartment().getFullName() + ", " + this.contact.getEmail() + "]";
        return date + startTime + endTime + location + contact;
    }

    /**
     * @param date the Date object to be checked.
     * @return true if the two Date objects are equal, false otherwise.
     */
    public boolean dateEquals(Date date) {
        if (this.date.compareTo(date) == 0) {
            return true;
        }
        return false;
    }

    /**
     * @param startTime Timeslot object to be compared.
     * @param duration Integer for Event duration.
     * @return true if the two Timeslots and durations are equal, false otherwise.
     */
    private boolean timeEquals (Timeslot startTime, int duration) {
        if (this.startTime == startTime && this.duration == duration) {
            return true;
        }
        return false;
    }

    /**
     * @param location Location object to be compared.
     * @return true if the location objects are equal, false otherwise.
     */
    public boolean locationEquals (Location location) {
        if (this.location == location) {
            return true;
        }
        return false;
    }

    /**
     * @param obj generic Object that will be compared if an Event type.
     * @return true if the location, time, and date are equal for two Events, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event event = (Event) obj;
            return this.dateEquals(event.date) && this.timeEquals(event.startTime, event.duration) && this.locationEquals(event.location);
            // method only compares date attribute (others not yet implemented)
        }
        return false;
    }

    /**
     * Events are compared by date, time, and duration in that order.
     * @param event the object to be compared.
     * @return 0 if the events are equal, -1 if the first object is "less than" the second, 1 otherwise.
     */
    @Override
    public int compareTo(Event event) {
        // method only compares date for now
        int dateCompareTo = this.date.compareTo(event.date);
        if (dateCompareTo > 0) {
            return 1;
        }
        if (dateCompareTo < 0) {
            return -1;
        }
        int timeCompareTo = this.startTime.compareTo(event.startTime);
        if (timeCompareTo > 0) {
            return 1;
        }
        if (timeCompareTo < 0) {
            return -1;
        }
        if (this.duration > event.duration) {
            return 1;
        }
        if (this.duration < event.duration) {
            return -1;
        }
        return 0;
    }

    /**
     * Location getter method.
     * @return the Location for an Event object.
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Contact getter method.
     * @return the Contact for an Event object.
     */
    public Contact getContact() {
        return this.contact;
    }

    /**
     * Date getter method.
     * @return the Date for an Event object.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Timeslot getter method.
     * @return the Timeslot for an Event object.
     */
    public Timeslot getTimeslot() {
        return this.startTime;
    }

    /**
     * Testbed main for the equals() method.
     * @param args
     */
    public static void main(String[] args) {
        String test1 = eventsEqual();
        System.out.println(test1);

        String test2 = dateNotEqual();
        System.out.println(test2);

        String test3 = timeNotEqual();
        System.out.println(test3);

        String test4 = locationNotEqual();
        System.out.println(test4);
    }

    private static String eventsEqual() {
        Date date1 = new Date(2023, 11, 29);
        Contact contact1 = new Contact(Department.CS, "cs@rutgers.edu");
        Event event1 = new Event(date1, Timeslot.EVENING, Location.HLL114, contact1, 90);

        Date date2 = new Date(2023, 11, 29);
        Contact contact2 = new Contact(Department.CS, "cs@rutgers.edu");
        Event event2 = new Event(date2, Timeslot.EVENING, Location.HLL114, contact2, 90);

        String returnMessage = "* Running eventsEqual() *\nEvent 1: " + event1.toString() + "\nEvent 2: " + event2.toString() + "\nequals() result: " + event1.equals(event2);
        return returnMessage;

    }

    private static String dateNotEqual () {
        Date date1 = new Date(2024, 2, 17);
        Contact contact1 = new Contact(Department.CS, "cs@rutgers.edu");
        Event event1 = new Event(date1, Timeslot.EVENING, Location.HLL114, contact1, 90);

        Date date2 = new Date(2023, 11, 29);
        Contact contact2 = new Contact(Department.CS, "cs@rutgers.edu");
        Event event2 = new Event(date2, Timeslot.EVENING, Location.HLL114, contact2, 90);

        String returnMessage = "* Running dateNotEqual() *\ndateEvent 1: " + event1.toString() + "\nEvent 2: " + event2.toString() + "\nequals() result: " + event1.equals(event2);
        return returnMessage;
    }

    private static String timeNotEqual () {
        Date date1 = new Date(2023, 11, 29);
        Contact contact1 = new Contact(Department.CS, "cs@rutgers.edu");
        Event event1 = new Event(date1, Timeslot.MORNING, Location.HLL114, contact1, 40);

        Date date2 = new Date(2023, 11, 29);
        Contact contact2 = new Contact(Department.CS, "cs@rutgers.edu");
        Event event2 = new Event(date2, Timeslot.EVENING, Location.HLL114, contact2, 90);

        String returnMessage = "* Running timeNotEqual() *\nEvent 1: " + event1.toString() + "\nEvent 2: " + event2.toString() + "\nequals() result: " + event1.equals(event2);
        return returnMessage;
    }

    private static String locationNotEqual () {
        Date date1 = new Date(2023, 11, 29);
        Contact contact1 = new Contact(Department.CS, "cs@rutgers.edu");
        Event event1 = new Event(date1, Timeslot.EVENING, Location.AB2225, contact1, 90);

        Date date2 = new Date(2023, 11, 29);
        Contact contact2 = new Contact(Department.CS, "cs@rutgers.edu");
        Event event2 = new Event(date2, Timeslot.EVENING, Location.HLL114, contact2, 90);

        String returnMessage = "* Running locationNotEqual() *\nEvent 1: " + event1.toString() + "\nEvent 2: " + event2.toString() + "\nequals() result: " + event1.equals(event2);
        return returnMessage;
    }

}

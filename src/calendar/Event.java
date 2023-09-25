package calendar;


import java.sql.Time;

public class Event implements Comparable<Event> {
    private Date date; //the event date
    private Timeslot startTime; //the starting time
    private Location location;
    private Contact contact; //include the department name and email
    private int duration; //in minutes


    /**
     * Event object constructor
     */
    public Event(Date date, Timeslot startTime, Location location, Contact contact, int duration) {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }


    @Override
    public String toString() {
        return "";
    }


    public boolean dateEquals(Date date) {
        if (this.date.compareTo(date) == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event event = (Event) obj;
            return this.dateEquals(event.date);
            // method only compares date attribute (others not yet implemented)
        }
        return false;
    }

    @Override
    public int compareTo(Event event) {
        // method only compares date for now

        return this.date.compareTo(event.date);
    }

}

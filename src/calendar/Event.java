package calendar;


import java.sql.Time;

public class Event implements Comparable<Event> {
    private Date date; //the event date
    private Timeslot startTime; //the starting time
    private Location location;
    private Contact contact; //include the department name and email
    private int duration; //in minutes

    /**
     * Event constructor
     * @param date the Date object associated with the Event
     * @param startTime the Timeslot object associated with the Event
     * @param location the Location object associated with the Event
     * @param contact the Contact object associated with the Event
     * @param duration the Event duration
     */

    // Lack of creating new objects within constructor may cause issues, will test
    public Event(Date date, Timeslot startTime, Location location, Contact contact, int duration) {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    private String getEndTime() {
        int startHour = Integer.parseInt(this.startTime.getStartHour());
        int startMinute = Integer.parseInt(this.startTime.getStartMinute());
        String amOrPM = "pm";
        if (this.startTime == Timeslot.MORNING) {
            amOrPM = "am";
        }
        int minutesInHour = 60;
        int hoursInAM = 12;
        // Need to add conversions
        if (amOrPM.equals("am")) {
            int totalMinutes = (startHour * minutesInHour) + startMinute;
            int scheduleMinutes = this.duration + totalMinutes;
        }
        else {
            int totalMinutes = (startHour * minutesInHour) + (hoursInAM*minutesInHour) + startMinute;
            int scheduleMinutes = this.duration + totalMinutes;
        }

        return "End Time WIP";
    }

    // need way of adding duration to startTime (getter methods)
    @Override
    public String toString() {
        String date = "[Event Date: " + this.date + "]";
        String startTime = "[Start: " + this.startTime.getStartHour() + ":" + this.startTime.getStartMinute() + "pm]";
        String endTime = "[End: " + (this.startTime) + "pm]"; // this part needs tweaking
        String location = "@" + this.location + "(" + this.location.getBuilding() + ", " + this.location.getCampus() + ")";
        String contact = "[Contact: " + this.contact.getDepartment().getFullName() + ", " + this.contact.getEmail() + "]";
        return date + startTime + endTime + location + contact;
    }


    private boolean dateEquals(Date date) {
        if (this.date.compareTo(date) == 0) {
            return true;
        }
        return false;
    }

    private boolean timeEquals (Timeslot startTime, int duration) {
        if (this.startTime == startTime && this.duration == duration) {
            return true;
        }
        return false;
    }

    private boolean locationEquals (Location location) {
        if (this.location == location) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event event = (Event) obj;
            return this.dateEquals(event.date) && this.timeEquals(event.startTime, event.duration) && this.locationEquals(event.location);
            // method only compares date attribute (others not yet implemented)
        }
        return false;
    }

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

    public static void main(String[] args) {
        Date date1 = new Date(2023, 12, 31);
        Contact contact1 = new Contact(Department.CS, "cs@rutgers.edu");
        Event event1 = new Event(date1, Timeslot.MORNING, Location.HLL114, contact1, 90);
        System.out.println(event1.toString());
    }
}

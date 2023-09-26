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

    private String getEndAM(int minutesInHour, int hoursInAM, int startHour, int startMinute, String amOrPM) {
        int totalMinutes = (startHour * minutesInHour) + startMinute;
        int scheduleMinutes = this.duration + totalMinutes;
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
        int scheduleMin = scheduleMinutes - (scheduleHour * minutesInHour);

        String finalMin = Integer.toString(scheduleMin);
        if (scheduleMin == 0) {
            finalMin = Integer.toString(scheduleMin) + "0";
        }

        finalEndTime = scheduleHour + ":" + finalMin + amOrPM;

        return finalEndTime;
    }

    private String getEndPM(int minutesInHour, int startHour, int startMinute) {
        int totalMinutes = (startHour * minutesInHour) + startMinute;
        int scheduleTotalMinutes = this.duration + totalMinutes;

        int scheduleHour = (int) Math.floor(scheduleTotalMinutes / minutesInHour);
        int scheduleMin = scheduleTotalMinutes - (scheduleHour * minutesInHour);
        String finalMin = Integer.toString(scheduleMin);
        if (scheduleMin == 0) {
            finalMin = Integer.toString(scheduleMin) + "0";
        }

        String finalEndTime = scheduleHour + ":" + finalMin + "pm";

        return finalEndTime;
    }

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
        if (amOrPM == "am") {
            finalEndTime = getEndAM(minutesInHour, hoursInAM, startHour, startMinute, amOrPM);
        }
        else {
            finalEndTime = getEndPM(minutesInHour, startHour, startMinute);
        }

        return "[End: " + finalEndTime + "]";
    }

    private String getStartTime(Event event) {
        if (event.startTime == Timeslot.MORNING) {
            return "[Start: " + event.startTime.getStartHour() + ":" + event.startTime.getStartMinute() + "am]";
        }
        return "[Start: " + event.startTime.getStartHour() + ":" + event.startTime.getStartMinute() + "pm]";
    }

    // need way of adding duration to startTime (getter methods)
    @Override
    public String toString() {
        String date = "[Event Date: " + this.date.getDate() + "]";
        String startTime = getStartTime(this);
        String endTime = getEndTime(this); // this part needs tweaking
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
        Event event1 = new Event(date1, Timeslot.EVENING, Location.HLL114, contact1, 90);
        System.out.println(event1.toString());
    }
}

package calendar;


public class Event implements Comparable<Event>{
    private Date date; //the event date
    private Timeslot startTime; //the starting time
    private Location location;
    private Contact contact; //include the department name and email
    private int duration; //in minutes

    //Event Constructor

    public Event(){ //constructor with no parameters : purpose for finding empty objects in array as this would be the default intialization
        this.date = null;
        this.startTime = null;
        this.location = null;
        this.contact = null;
        this.duration = 0;
    }
    public Event (Date date, Timeslot startTime, Location location, Contact contact, int duration){
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event event = (Event) obj;
            // insert some comparable primitive value attribute
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Date date) {
        // insert some comparable primitive value attribute

        if ((this.objectAttribute(this.objectAttribute)) > 0 )
            return 1;
        if ((this.objectAttribute(this.objectAttribute)) < 0 )
            return -1;
        return 0;
    }

    public Date getDate() {
        return date;
    }
}

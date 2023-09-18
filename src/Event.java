public class Event implements Comparable<Event>{
    private Date date; //the event date
    private Timeslot startTime; //the starting time
    private Location location;
    private Contact contact; //include the department name and email
    private int duration; //in minutes

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
        /*
        if ((this.objectAttribute(this.objectAttribute)) > 0 )
            return 1;
        if ((this.objectAttribute(this.objectAttribute)) < 0 )
            return -1;
         */
        return 0;
    }

}

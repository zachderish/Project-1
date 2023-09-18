public class Event implements comparable<Event>{
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
    public boolean equals(Object anotherObject) {
        return false;
    }

    public int compareTo(Date date) {
        return 0;
    }

}

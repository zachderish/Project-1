package calendar;


import java.sql.Time;

/**
 * Define the enum class Timeslot
 * @author Kenrick Eagar, Zachary Derish
 */
public enum Timeslot{
        MORNING ("10", "30"),
        AFTERNOON ("2", "00"),
        EVENING ("3", "30");

        private final String startHour;
        private final String startMinute;


    /**
     * Timeslot constructor
     * @param startHour the start time hour associated with the Timeslot
     * @param startMinute the start time minute associated with the Timeslot
     */
    Timeslot (String startHour, String startMinute) {
            this.startHour = startHour;
            this.startMinute = startMinute;
    }


    public String getStartHour() {
        return this.startHour;
    }

    public String getStartMinute() {
        return this.startMinute;
    }

    public static void main(String[] args) {
        for (Timeslot timeslot : Timeslot.values()) {
            System.out.println(timeslot + ": " + timeslot.getStartHour() + ":" + timeslot.getStartMinute());
        }
    }
}
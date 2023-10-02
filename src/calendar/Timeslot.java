package calendar;

/**
 * Define the enum class Timeslot
 * @author Kenrick Eagar, Zachary Derish
 */
public enum Timeslot{
        MORNING ("10", "30"),
        AFTERNOON ("2", "00"),
        EVENING ("6", "30");

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

    /**
    * Method will return String representing start hour of Timeslot
    * @return String representing start hour of Timeslot
    */
    public String getStartHour() {
        return this.startHour;
    }

    /**
    * Method will return String representing start minute of Timeslot
    * @return String representing start minute of Timeslot
    */
    public String getStartMinute() {
        return this.startMinute;
    }
}

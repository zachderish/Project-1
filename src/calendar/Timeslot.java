package calendar;


/**
 * Define the enum class Timeslot
 * @author Kenrick Eagar, Zachary Derish
 */
public enum Timeslot{
        MORNING (8, 30),
        AFTERNOON (12, 30),
        EVENING (3, 30);

        private final int startHour;
        private final int startMinute;


    /**
     * Timeslot constructor
     * @param startHour the start time hour associated with the Timeslot
     * @param startMinute the start time minute associated with the Timeslot
     */
    Timeslot (int startHour, int startMinute) {
            this.startHour = startHour;
            this.startMinute = startMinute;
        }
    }
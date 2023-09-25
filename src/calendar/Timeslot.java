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
     */
    Timeslot (int startHour, int startMinute) {
            this.startHour = startHour;
            this.startMinute = startMinute;
        }
    }
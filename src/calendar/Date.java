package calendar;

/**
 * Define the abstract data type Date and its methods
 * @author Kenrick Eagar, Zachary Derish
 */

public class Date implements Comparable<Date>{

    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public static final int JANUARY = 1, FEBRUARY = 2, MARCH = 3;
    public static final int APRIL = 4, MAY = 5, JUNE = 6, JULY = 7;
    public static final int AUGUST = 8, SEPTEMBER = 9, OCTOBER = 10, NOVEMBER = 11, DECEMBER = 12;

    public static final int CURRENT_YEAR = 2023;

    //calendar.Date Object Constructor
    public Date(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * returns true if it's a leap year false otherwise
     * @param year the year to be checked for leap year
     * @return true if leap year, false otherwise
     */
    private boolean isLeapYear(int year) {
        if (year % QUADRENNIAL == 0 && year % CENTENNIAL == 0 && year % QUATERCENTENNIAL == 0) {
            return true;
        }
        return false;
    }

    /**
     * Return number of days in a month based on month itself and leap year status
     * @param month month of the year represented as int
     * @param year year represented as int
     * @return int of number of days contained in month
     */
    public int numberOfDays(int month, int year) { //returns the number of days given the month and year
        if(month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER){ //if its any of those months they have 30 days
            return 30;
        }
        else if (month != FEBRUARY){ // all the rest of the months besides February have 31 days
            return 31;
        }
        else {
            if(isLeapYear(year)) { // depending if February is a leap year it will either have 28 or 29 days
                return 29;
            }
        }
        return 28;

    }

    /**
     * Check that integer representation of month is between January and December
     * @param month integer that represents month of the year
     * @return true if month is >= 1 (January) or <= 12 (December), false otherwise
     */
    public boolean validMonth(int month){
        if(month < JANUARY || month > DECEMBER){ // if it's not between 1 and 12 not a valid month
            return false;
        }
        return true;
    }

    /**
     * Check if year is valid if scheduled year is >= current year
     * @param year year represented as int
     * @return false is year is before the current year, true otherwise
     */
    public boolean validYear(int year){
        if(year < CURRENT_YEAR){ //if the year is not at least 1 its invalid
            return false;
        }
        return true;
    }

    /**
     * Check the Date object to ensure day, month and year attributes are in correct format
     * @param inputDate the Date object to be checked for validity
     * @return true if date fits proper format, false otherwise
     */
    public boolean isValid(Date inputDate){
        int inputMonth = inputDate.month;
        int inputDay = inputDate.day;
        int inputYear = inputDate.year;

        if(!validMonth(inputMonth)){
            return false;
        }

        if(!validYear(inputYear)){
            return false;
        }

        if(inputDay <= 0 || inputDay > numberOfDays(inputMonth, inputYear)) { //if data is below or over the number of days its invalid
            return false;
        }

        return true;
    }

}
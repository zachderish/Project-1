package calendar;

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

public Date(int year, int month, int day){ //calendar.Date Object Constructor
this.year = year;
this.month = month;
this.day = day;
}

private boolean isLeapYear(int year){ //returns true if its a leap year false otherwise
    if(year % QUADRENNIAL == 0){
        if(year % CENTENNIAL == 0){
            if(year % QUATERCENTENNIAL == 0){
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    } 
    return false;
}

public int numberOfDays(int month, int year){ //returns the number of days given the month and year
if(month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER){ //if its any of those months they have 30 days
    return 30;
} else if (month != FEBRUARY){ // all the rest of the months besides februaray have 31 days
    return 31;
}
if(month == FEBRUARY){ 
    if(isLeapYear(year)){ // depending if february is a leap year it will either have 28 or 29 days
        return 29;
    }
}
return 28;

}

public boolean validMonth(int month){
    if(month < JANUARY || month > DECEMBER){ // if its not between 1 and 12 not a valid month
        return false;
    }
    return true;
}

public boolean validYear(int year){
    if(year < 0){ //if the year is not at least 1 its invalid
        return false;
    }
    return true;
}

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

    if(inputDate < numberOfDays(inputMonth, inputYear) || inputDate > numberOfDays(inputMonth, inputYear)){ //if its below or over the number of days its invalid
        return false;
    }

    return true;
}


return true;
}
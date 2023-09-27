package calendar;

import java.sql.Time;
import java.util.Scanner;

public class EventOrganizer {

    final String[] COMMANDS = {"A", "R", "P", "PE", "PC", "PD", "Q"};

    final Boolean INITIALIZED = false;

    private boolean validCommand(String input) {
        for (String command : COMMANDS) {
            if(input.equals(command)) {
                return true;
            }
        }
        return false;
    }

    private Date makeDate(String[] input) {
        String[] dateItemized = input[1].split("/");
        int year = Integer.parseInt(dateItemized[2]);
        int month = Integer.parseInt(dateItemized[0]);
        int day = Integer.parseInt(dateItemized[1]);
        new Date(year, month, day);

        return new Date(year, month, day);
    }

    private Timeslot makeTimeslot(String[] input) {
        if (input[2].equalsIgnoreCase("afternoon")) {
            return Timeslot.AFTERNOON;
        }
        if (input[2].equalsIgnoreCase("evening")){
            return Timeslot.EVENING;
        }
        return Timeslot.MORNING;
    }

    private String runAdd(EventCalendar calendar, String[] input) {
        Date date = makeDate(input);
        Timeslot timeslot = makeTimeslot(input);

        // Making objects for event object in their own methods
        Event newEvent = new Event(date, timeslot, );
        return "";
    }

    private String runCommand(String[] input, EventCalendar calendar) {
        String returnMessage = "";

        if (input[0].equals("Q")) {
            return "QUIT";
        }
        if (input[0].equals("A")) {
            returnMessage = runAdd(calendar, input);

        }
        return returnMessage;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Event organizer running...\n");

        Event[] events = {};
        int initialNumEvents = 0;
        EventCalendar calendar = new EventCalendar(events, initialNumEvents);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] lineItemized = line.split("\\s+");
            if (validCommand(lineItemized[0])) {
                String message = runCommand(lineItemized, calendar);
                if (message.equals("QUIT")) {
                    return;
                }
                System.out.println(message);
            }
            else {
                System.out.println(line + " is an invalid command!");
            }
        }
    }
}

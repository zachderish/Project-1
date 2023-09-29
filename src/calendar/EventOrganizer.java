package calendar;

import java.sql.Time;
import java.util.Scanner;

public class EventOrganizer {

    final String[] COMMANDS = {"A", "R", "P", "PE", "PC", "PD", "Q"};
    public static final int NOT_FOUND = -1;
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
        if (input[2].equalsIgnoreCase("morning")) {
            return Timeslot.MORNING;
        }
        return null;
    }

    private Location makeLocation(String[] input) {
        if (input[3].equalsIgnoreCase("hll114")) {
            return Location.HLL114;
        }
        if (input[3].equalsIgnoreCase("arc103")) {
            return Location.ARC103;
        }
        if (input[3].equalsIgnoreCase("be_aud")) {
            return Location.BE_AUD;
        }
        if (input[3].equalsIgnoreCase("til232")) {
            return Location.TIL232;
        }
        if(input[3].equalsIgnoreCase("ab2225")) {
            return Location.AB2225;
        }
        if(input[3].equalsIgnoreCase("mu302")) {
            return Location.MU302;
        }
        return null;
    }

    private Contact makeContact(String[] input) {
        Department department = null;

        if (input[4].equalsIgnoreCase("bait")) {
            department = Department.BAIT;
        }
        if (input[4].equalsIgnoreCase("cs")) {
            department = Department.CS;
        }
        if (input[4].equalsIgnoreCase("ee")) {
            department = Department.EE;
        }
        if (input[4].equalsIgnoreCase("iti")) {
            department = Department.ITI;
        }
        if (input[4].equalsIgnoreCase("math")) {
            department = Department.MATH;
        }

        return new Contact(department, input[5]);
    }

    private String validDate(Event event, String[] input) {
        Date date = event.getDate();
        if (!date.isValid()) {
            return input[1] + ": Invalid calendar date!";
        }
        if (!date.within6Months().equals("VALID")) {
            return input[1] + ": " + date.within6Months();
        }
        return "VALID";
    }


    private String validAdd(Event event, String[] input, EventCalendar calendar) {
        if (calendar.contains(event)) {
            return "The event is already on the calendar.";
        }
        if (!validDate(event, input).equals("VALID")) {
            return validDate(event, input);
        }
        if (event.getTimeslot() == null) {
            return "Invalid time slot!";
        }
        if (event.getLocation() == null) {
            return "Invalid location!";
        }
        if (!event.getContact().isValid()) {
            return "Invalid contact information!";
        }
        int duration = Integer.parseInt(input[6]);
        if (duration > 120 || duration < 30) {
            return "Event duration must be at least 30 minutes and at most 120 minutes";
        }
        return "VALID";

    }

    private String runAdd(EventCalendar calendar, String[] input) {
        Date date = makeDate(input);
        Timeslot timeslot = makeTimeslot(input);
        Location location = makeLocation(input);
        Contact contact = makeContact(input);
        int duration = Integer.parseInt(input[6]);

        // Making objects for event object in their own methods
        Event newEvent = new Event(date, timeslot, location, contact, duration);

        // Check for reason not to add
        if (validAdd(newEvent, input, calendar).equals("VALID")) {
            calendar.add(newEvent);
            return "Event added to the calendar.";
        }
        return validAdd(newEvent, input, calendar);
    }

    private String removeEvent(EventCalendar calendar, String[] input){
        Date date = makeDate(input);
        Location location = makeLocation(input);
        if(input.length < 7) { //if its less than 7 we dont have all details for event
            Event tempEvent = new Event(date, location);
            if (!validDate(tempEvent, input).equals("VALID")) { //for incomplete events we check the date and ensure its valid
                return validDate(!validDate(tempEvent, input));
            }
            if(!calendar.remove(tempEvent)){
                return "Cannot remove; event is not in the calendar!";
            } else {
                return "Event has been removed from the calendar!"
            }
        }
        Timeslot timeslot = makeTimeslot(input);
        Contact contact = makeContact(input);
        int duration = Integer.parseInt(input[6]);
        Event tempEvent = new Event(tempDate, timeslot, location, contact, duration);
        if(!calendar.remove(tempEvent)){
            return "Cannot remove; event is not in the calendar!";
        }
        return "Event has been removed from the calendar!"
    }

    private String runCommand(String[] input, EventCalendar calendar) {
        String returnMessage = "";

        String command = input[0];
        if (command.equals("Q")) {
            return "QUIT";
        }
        if (command.equals("A")) {
            returnMessage = runAdd(calendar, input);
        }
        if (command.equals("P")) {
            calendar.print();
        }
        if (command.equals("PD")) {
            calendar.printByDepartment();
        }
        if (command.equals("PC")) {
            calendar.printByCampus();
        }
        if (command.equals("PE")) {
            calendar.printByDate();
        }

        if(command.equals("R")){

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

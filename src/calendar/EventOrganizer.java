package calendar;

import java.util.Scanner;

public class EventOrganizer {

    final String[] commands = {"A", "R", "P", "PE", "PC", "PD", "Q"};

    private boolean validCommand(String input) {
        for (String command : commands) {
            if(input.equals(command)) {
                return true;
            }
        }
        return false;
    }

    private String runCommand(String[] input) {
        if (input[0].equals("Q")) {
            return "QUIT";
        }
        return input[0];
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Event organizer running...\n");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] words = line.split("\\s+");
            if (validCommand(words[0])) {
                String message = runCommand(words);
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

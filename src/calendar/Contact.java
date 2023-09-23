package calendar;

/**
 * Define the abstract data type Contact and its methods
 * @author Kenrick Eagar, Zachary Derish
 */

public class Contact {
    private Department department;
    private String email;

    private static final String[] EMAILS = {"cs@rutgers.edu", "iti@rutgers.edu", "bait@rutgers.edu", "ee@rutgers.edu", "math@rutgers.edu"};


    //Contact Constructor
    public Contact(Department department, String email){
        this.department = department;
        this.email = email;
    }

    /**
     *
     * @param email the email to be checked for validity
     * @return true when email meets domain criteria, false otherwise
     */
    public boolean validEmail(String email){
        for(int i = 0; i < EMAILS.length; i++) {
            if (EMAILS[i].equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Need enum info to complete this method
     * @param department the department to be checked for validity
     * @return true when department meets 5 acceptable departments, false otherwise
     */
    public boolean validDepartment(Department department){
        return true; //reminder in case we forget this method needs updating
    }

    /**
     *
     * @param department the department to be checked for Contact
     * @param email the email to be checked for Contact
     * @return true when department and email fit criteria, false otherwise
     */
    public boolean isValid(Department department, String email) {
        return validEmail(email) && validDepartment(department);
    }


}

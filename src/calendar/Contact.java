package calendar;

/**
 * Define the abstract data type Contact and its methods
 * @author Kenrick Eagar, Zachary Derish
 */

public class Contact {
    private Department department;
    private String email;

    private final String[] EMAILS = {"cs@rutgers.edu", "iti@rutgers.edu", "bait@rutgers.edu", "ee@rutgers.edu", "math@rutgers.edu"};


    /**
     * Contact constructor
     * @param department the department object associated with the Contact
     * @param email the email address associated with the Contact
     */
    public Contact(Department department, String email){
        this.department = department;
        this.email = email;
    }

    /**
     * Iterate through valid emails to check Contact email
     * @param email the email checked for validity
     * @return true when email meets domain criteria, false otherwise
     */
    private boolean validEmail(String email){
        for (String emailVal : EMAILS) {
            if (emailVal.equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Iterate through valid departments to check Contact department
     * @param department the department to be checked for validity
     * @return true when department is one of 5 acceptable departments, false otherwise
     */
    private boolean validDepartment(Department department){
        for (Department departmentVal : Department.values()) {
            if (department == departmentVal) {
                return true;
            }
        }
        return false;
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

    public Department getDepartment() {
        return this.department;
    }

    public String getEmail() {
        return this.email;
    }

}

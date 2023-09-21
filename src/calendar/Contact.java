package calendar;

public class Contact {
    private Department department;
    private String email;

    public static final String CS_EMAIL = "cs@rutgers.edu", ITI_EMAIL = "iti@rutgers.edu";
    public static final String BAIT_EMAIL = "bait@rutgers.edu", EE_EMAIL = "ee@rutgers.edu";
    public static final String MATH_EMAIl = "math@rutgers.edu";


    //Contact Constructor
    public Contact(Department department, String email){
        this.department = department;
        this.email = email;
    }

    public boolean validEmail(String email){
        if(!email.equals(EE_EMAIL) && !email.equals(CS_EMAIL) && !email.equals(BAIT_EMAIL) && !email.equals(MATH_EMAIl) && !email.equals(ITI_EMAIL)){
            return false;
        }
        return true;
    }

    public boolean isValid(Department department, String email) {
        if(!validEmail){
            return false;
        }
        return true;
    }

}
 //test

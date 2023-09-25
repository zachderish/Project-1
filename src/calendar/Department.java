package calendar;

/**
 * Define the enum class Department
 * @author Kenrick Eagar, Zachary Derish
 */
public enum Department{

    CS ("Computer Science"),
    EE ("Electrical Engineering"),
    MATH ("Information Technology Informatics"),
    ITI ("Mathematics"),
    BAIT ("Business Analytics and Information Technology");

    private final String fullName;

    /**
     * Department constructor
     * @param fullName the full department name
     */
    Department(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

}
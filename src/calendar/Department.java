package calendar;

/**
 * Define the enum class Department
 * @author Kenrick Eagar, Zachary Derish
 */
public enum Department{

    MATH ("Mathematics"),
    ITI ("Information Technology Informatics"),
    EE ("Electrical Engineering"),
    CS ("Computer Science"),
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

    public static void main(String[] args) {
        for (Department department : Department.values()) {
            System.out.println(department + ": " + department.getFullName());
        }
    }

}
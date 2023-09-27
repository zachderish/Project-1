package calendar;

/**
 * Define the enum class Department
 * @author Kenrick Eagar, Zachary Derish
 */
public enum Department{

    BAIT ("Business Analytics and Information Technology"),
    CS ("Computer Science"),
    EE ("Electrical Engineering"),
    ITI ("Mathematics"),
    MATH ("Information Technology Informatics");

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
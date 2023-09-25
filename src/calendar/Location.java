package calendar;

/**
 * Define the enum class Location
 * @author Kenrick Eagar, Zachary Derish
 */
public enum Location{

    HLL114 ("Hill Center", "Busch"),
    ARC103 ("Allison Road Classroom", "Busch"),
    BE_AUD ("Beck Hall", "Livingston"),
    TIL232 ("Tillet Hall", "Livingston"),
    AB2225 ("Academic Building", "College Avenue"),
    MU302 ("Murray Hall", "College Avenue");

    private final String building;
    private final String campus;

    /**
     * Location constructor
     * @param building the building associated with the Location
     * @param campus the campus associated with the Location
     */
    Location(String building, String campus) {
        this.building = building;
        this.campus = campus;
    }

    public String getBuilding() {
        return this.building;
    }

    public String getCampus() {
        return this.campus;
    }
}
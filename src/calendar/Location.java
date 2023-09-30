package calendar;

/**
 * Define the enum class Location
 * @author Kenrick Eagar, Zachary Derish
 */
public enum Location{

    ARC103 ("Allison Road Classroom", "Busch"),
    HLL114 ("Hill Center", "Busch"),
    AB2225 ("Academic Building", "College Avenue"),
    MU302 ("Murray Hall", "College Avenue"),
    BE_AUD ("Beck Hall", "Livingston"),
    TIL232 ("Tillet Hall", "Livingston");

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
     /**
     * 
     * @return String indicating the building of the location
     */
    public String getBuilding() {
        return this.building;
    }
    /**
     * 
     * @return String indicating the camous of the location
     */
    public String getCampus() {
        return this.campus;
    }
  /**
     * TestBed main method used to test location class
     * 
     */
    public static void main(String[] args) {
        for (Location location : Location.values()) {
            System.out.println(location + ": " + location.getBuilding() + ", " + location.getCampus());
        }
    }
}

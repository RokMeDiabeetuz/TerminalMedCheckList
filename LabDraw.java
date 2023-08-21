/**
 * <h2>LabDraw.java - class representing a laboratory draw or sample collection task.</h2>
 * <p>Instance Variables</p>
 * <ul>
 *     <li>Variables inherited from Intervention parent class.</li>
 *     <li>String sample - string representing what type of sample is to be taken.</li>
 *     <li>String location - string representation of where the sample will be taken from.</li>
 * </ul>
 * <p>Methods: </p>
 * <ul>
 *     <li>All public methods from the parent class Intervention.</li>
 *     <li>no arg constructor - sets the default values of each </li>
 *     <li>getters() - returns the value of the instance variables to the calling program.</li>
 *     <li>setters() - sets a new value to the instance variables.</li>
 *     <li>toString() - returns a string representation of the instance variables to the calling program.</li>
 *     <li>equals() - compares the calling object and a parameter object for equality.</li>
 * </ul>
 * @author Daryll Guiang
 * @version Final Project
 */
public class LabDraw extends Intervention{
    // instance variables
    private String sample;
    private String location;
    // constructor
    public LabDraw() {
        super();
        this.sample = "n/a";
        this.location = "n/a";
    }
    public LabDraw(String name, String orderingPhysician, HospitalTime drawTime, String sample, String location) {
        super(name, orderingPhysician, drawTime);
        setSample(sample);
        setLocation(location);
    }
    /**
     * <p>Returns the type of sample to be taken from the patient in string form.</p>
     * @return string representation of what type of sample will be taken.
     */
    public String getSample() {
        return sample;
    }
    /**
     * <p>Returns the location on the patient where the lab draw will be taken.</p>
     * @return location where the lab draw will be taken in string form.
     */
    public String getLocation() {
        return location;
    }
    /**
     * <p>Changes the type of sample to be taken from the patient.</p>
     * @param sample user given string representing what type of sample is to be taken.
     */
    public void setSample(String sample) {
        String correctedSample = sample.replace(",", " ").trim();
        this.sample = (correctedSample.length() < 1) ? "unknown sample": correctedSample;
    }
    /**
     * <p>Changes the location in which the lab draw will be sampled from.</p>
     * @param location new location where the lab draw will be taken from.
     */
    public void setLocation(String location) {
        String correctedLocation = location.replace(",", " ").trim();
        this.location = (correctedLocation.length() < 1) ? "unknown location": correctedLocation;
    }
    /**
     * <p>Returns the values of the calling LabDraw objects variables as a single string to the calling program.</p>
     * @return string representation of the LabDraw task's instance variables.
     */
    public String toString() {
        return (super.toString().replace("Task", "Lab") + "Sample: " + sample + "\nLocation: " + location);
    }
    /**
     * <p>Compares this calling object to a parameter object for equality.</p>
     * @param obj object that the current object will be compared to.
     * @return boolean representing equality between the calling object and parameter object.
     */
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            LabDraw labComparison = (LabDraw) obj;
            return ( (sample.equals(labComparison.sample)) && (location.equals(labComparison.location)));
        }
        return false;
    }
}

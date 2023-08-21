/**
 * <h2>Intervention.java - class representing a general task to be completed.</h2>
 * <p>Instance Variables</p>
 * <ul>
 *     <li>String name - name of the Intervention task.</li>
 *     <li>boolean isDone - boolean representing whether the task is completed or not.</li>
 *     <li>String orderingPhysician - name of the doctor who ordered the task.</li>
 *     <li>HospitalTime adminTime - time object representing when the task is to be completed.</li>
 * </ul>
 * <p>Methods: </p>
 * <ul>
 *     <li>no arg constructor - sets instance variables to default values.</li>
 *     <li>full constructor - sets the name, orderingPhysician, and HospitalTime to user given input.</li>
 *     <li>getName() - returns the name of the Intervention to the calling program.</li>
 *     <li>isDone() - returns a boolean representing whether the task is completed or not.</li>
 *     <li>getOrderingPhysician() - returns the name of the ordering Doctor to the calling program.</li>
 *     <li>getTime() - returns the HospitalTime object that indicates when the task is to be completed.</li>
 *     <li>setName() - changes the name of the task to an input given by the user.</li>
 *     <li>setOrderingPhysician() - changes the name of the ordering Doctor to a new name.</li>
 *     <li>setDone() - sets whether or not the task is done by applying it the value of a user given boolean.</li>
 *     <li>setTime() - changes the hospital time object to a new one set by the user.</li>
 *     <li>toString() - returns the values of the instance variables to the calling program as a single string.</li>
 *     <li>equals() - compares this object to a comparison object for equality.</li>
 * </ul>
 * @author Daryll Guiang
 * @version Final Project
 */
public class Intervention {
    // instance variables
    private String name;
    private boolean isDone;
    private String orderingPhysician;
    private HospitalTime adminTime;
    // constructor
    public Intervention() {
        this("Default Task", "Default Physician", new HospitalTime(0, 0));
    }
    public Intervention(String name, String orderingPhysician, HospitalTime adminTime) {
        setName(name);
        setOrderingPhysician(orderingPhysician);
        this.isDone = false;
        this.adminTime = adminTime;
    }
    /**
     * <p>Returns the name of the task to the calling program.</p>
     * @return String representation of the name of the task.
     */
    public String getName() {
        return name;
    }
    /**
     * <p>Returns a boolean representation of whether or not the task is complete to the calling program.</p>
     * @return boolean representing task completion.
     */
    public boolean isDone() {
        return isDone;
    }
    /**
     * <p>Returns the name of the ordering Physician to the calling program.</p>
     * @return name of the ordering doctor in String form.
     */
    public String getOrderingPhysician() {
        return orderingPhysician;
    }
    /**
     * <p>Returns a HospitalTime object representing when this Intervention will be done.</p>
     * @return Hospital Time object task is to be done.
     */
    public HospitalTime getTime() {
        return adminTime;
    }
    /**
     * <p>Changes the name of the task to a new string supplied by the user. </p>
     * @param name new name to be applied to the name instance variable.
     */
    public void setName(String name) {
        String correctedName = name.replace(",", " ").trim();
        this.name = (correctedName.length() < 1) ? "Unknown Intervention": correctedName;
    }
    /**
     * <p>Change the name of the physician who ordered the task to be done.</p>
     * @param orderingPhysician new name of the ordering physician.
     */
    public void setOrderingPhysician(String orderingPhysician) {
        String correctedPhysician = orderingPhysician.replace(",", " ").trim();
        this.orderingPhysician = (correctedPhysician.length() < 1) ? "unknown physician": correctedPhysician;
    }
    /**
     * <p>Sets a task as complete or not complete depending on the user given boolean provided.</p>
     * @param done boolean representing whether the task is done or not.
     */
    public void setDone(boolean done) {
        isDone = done;
    }
    /**
     * <p>Sets a new time for when the task is to be completed.</p>
     * @param adminTime new time selected by the user for the task to be done.
     */
    public void setTime(HospitalTime adminTime) {
        this.adminTime = adminTime;
    }
    /**
     * <p>Creates a string representation of the Intervention objects values and returns it to the calling program.</p>
     * @return String representation of this object's instance variables.
     */
    public String toString() {
        return ("Task Name: " + name + ((isDone) ? "(COMPLETED)":"") + "\t"  +
                "Physician: " + orderingPhysician + "\t" +
                "Time: " + adminTime + "\t");
    }
    /**
     * <p>Compares this current object with a parameter object for equality.</p>
     * @param obj object that this current object will be compared to.
     * @return boolean representing equality between the two objects compared.
     */
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            Intervention comparison = (Intervention) obj;
            return (name.equals(comparison.name) &&
                    isDone == comparison.isDone &&
                    orderingPhysician.equalsIgnoreCase(comparison.orderingPhysician) &&
                    adminTime.equals(comparison.getTime()));
        }
        return false;
    }
}

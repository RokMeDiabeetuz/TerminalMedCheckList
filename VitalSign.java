/**
 * <h2>VitalSign.java - object representing a group of vital signs taken for a patient.</h2>
 * <p>Instance Variables: </p>
 * <ul>
 *     <li>double temperature - oral temperature of the patient.</li>
 *     <li>int heartRate - pulse rate or heart rate of the patient.</li>
 *     <li>int respiratoryRate - respiratory rate of the patient.</li>
 *     <li>int systolicBP - systolic blood pressure of the patient, the number in the top.</li>
 *     <li>int diastolicBP - diastolic blood pressure of the patient, the number in the bottom.</li>
 *     <li>int oxygenSaturation - oxygen saturation of the patient, ratio of hemoglobin bound to oxygen to that not bound to oxygen. </li>
 *     <li>HospitalTime timeTaken - time object representing the time when the set of vital signs was taken.</li>
 * </ul>
 * <p>Methods: </p>
 * <ul>
 *     <li>no arg constructor - sets the value of each of the vitals signs to a default of 0.</li>
 *     <li>full constructor - sets the value of each instance variable to a user given value.</li>
 *     <li>getters() - returns the value of each respective instance variables to the calling program.</li>
 *     <li>setters() - updates the value of each instance variable to a value given by the user, updates the values.</li>
 *     <li>toString() - returns the values of all the instance variables as a single formatted string to the calling program.</li>
 *     <li>equals() - compares the calling object to a comparison object and returns the results as a boolean.</li>
 * </ul>
 * @author Daryll Guiang
 * @version Final Project
 */
public class VitalSign {
    // instance variables
    private double temperature;
    private int hearRate;
    private int respiratoryRate;
    private int systolicBP;
    private int diastolicBP;
    private int oxygenSaturation;
    private HospitalTime timeTaken;
    // constructor
    public VitalSign() {
        this (0, 0, 0, 0, 0, 0,new HospitalTime(0, 0));
    }
    public VitalSign(double temperature, int hearRate, int respiratoryRate, int systolicBP, int diastolicBP,
                     int oxygenSaturation, HospitalTime timeTaken) {
        this.temperature = temperature;
        this.hearRate = hearRate;
        this.respiratoryRate = respiratoryRate;
        this.systolicBP = systolicBP;
        this.diastolicBP = diastolicBP;
        this.oxygenSaturation = oxygenSaturation;
        this.timeTaken = timeTaken;
    }
    /**
     * <p>Returns the oral temperature to the calling program.</p>
     * @return oral temperature in double form.
     */
    public double getTemperature() {
        return temperature;
    }
    /**
     * <p>Returns the patient's heart rate to the calling program.</p>
     * @return current value of the heartRate variable.
     */
    public int getHearRate() {
        return hearRate;
    }
    /**
     * <p>Returns the respiratory rate value to the calling program.</p>
     * @return respiratory rate in int form.
     */
    public int getRespiratoryRate() {
        return respiratoryRate;
    }
    /**
     * <p>Returns the systolic blood pressure value to the calling program.</p>
     * @return systolic blood pressure in int form.
     */
    public int getSystolicBP() {
        return systolicBP;
    }
    /**
     * <p>Returns the diastolic blood pressure of the patient to calling program.</p>
     * @return diastolic blood pressure in int form.
     */
    public int getDiastolicBP() {
        return diastolicBP;
    }
    /**
     * <p>Returns the oxygen saturation of the patient.</p>
     * @return oxygen saturation level in int form.
     */
    public int getOxygenSaturation() {
        return oxygenSaturation;
    }
    /**
     * <p>Returns a HospitalTime object representing when the vitals were taken to the calling program.</p>
     * @return time that the vital signs object was taken.
     */
    public HospitalTime getTimeTaken() {
        return timeTaken;
    }
    /**
     * <p>Updates the value of the temperature instance variable to one entered by the user.</p>
     * @param temperature oral temperature entered by the user.
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    /**
     * <p>Updates the heart rate to a value entered by the user.</p>
     * @param heartRate heart rate or pulse entered by the user.
     */
    public void setHearRate(int hearRate) {
        this.hearRate = hearRate;
    }
    /**
     * <p>Updates the respiratory rate to a value entered by the user.</p>
     * @param respiratoryRate respiratory rate entered by the user.
     */
    public void setRespiratoryRate(int respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }
    /**
     * <p>Updates the value of the systolicBP instance variable.</p>
     * @param systolicBP new systolic blood pressure entered by the user.
     */
    public void setSystolicBP(int systolicBP) {
        this.systolicBP = systolicBP;
    }
    /**
     * <p>Updates the value of the diastolicBP instance variable.</p>
     * @param diastolicBP new diastolic blood pressure entered by the user to replace the old value.
     */
    public void setDiastolicBP(int diastolicBP) {
        this.diastolicBP = diastolicBP;
    }
    /**
     * <p>Sets a new value entered by the user to the oxygenSaturation variable.</p>
     * @param oxygenSaturation new oxygen saturation value to replace the old value.
     */
    public void setOxygenSaturation(int oxygenSaturation) {
        this.oxygenSaturation = oxygenSaturation;
    }
    /**
     * <p>Sets a new time in which the vital sign was taken.</p>
     * @param timeTaken HospitalTime object representing when the vital signs were taken.
     */
    public void setTimeTaken(HospitalTime timeTaken) {
        this.timeTaken = timeTaken;
    }
    /**
     * <p>Formats the values of all instance variables into a single string and returns it to the calling program.</p>
     * @return formatted string representation of the instance variable's values.
     */
    public String toString() {
        return (timeTaken.toString() + ")  Tmp: " + String.format("%.2f", temperature) + ",  HR: " + hearRate + ",  RR: " + respiratoryRate +
                ",  BP: " + (systolicBP + "/" + diastolicBP) + ",  MAP: " + String.format("%.1f", (systolicBP + 2 * diastolicBP) / 3.0) +
                ",  Osat: " + oxygenSaturation + "%\n");
    }
    /**
     * <p>Compares the calling object and a parameter object for equality and returns results to the calling program.</p>
     * @param obj object which the calling VitalSign object will be compared to.
     * @return boolean representing equality between the calling object and the comparison object.
     */
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            VitalSign comparison = (VitalSign) obj;
            boolean sameTemp = (Math.abs(temperature - comparison.temperature) < 1e-13);
            boolean sameHR = (hearRate == comparison.hearRate);
            boolean sameRR = (respiratoryRate == comparison.respiratoryRate);
            boolean sameSystolic = (systolicBP == comparison.systolicBP);
            boolean sameDiastolic = (diastolicBP == comparison.diastolicBP);
            boolean sameOSat = (oxygenSaturation == comparison.oxygenSaturation);
            boolean sameTimeTaken = (timeTaken.equals(comparison.timeTaken));
            return (sameTemp && sameHR && sameRR && sameSystolic && sameDiastolic && sameOSat && sameTimeTaken);
        }
        return false;
    }
}

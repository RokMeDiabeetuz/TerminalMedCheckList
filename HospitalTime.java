/**
 * <h2>HospitalTime.java - simulates a time during the day, noted by hours, minutes, and whether the time is at night or day.</h2>
 * <p>Instance Variables</p>
 * <ul>
 *     <li>int hours - current time as a whole hour.</li>
 *     <li>int minutes - current tim as a minute.</li>
 *     <li>boolean isAm - boolean representing if the time given is in the morning or night.</li>
 *     <li>double doubleValue - value of the time in double format.</li>
 * </ul>
 * <p>Methods: </p>
 * <ul>
 *     <li>full constructor - assigns user given hour and minute to instance variables, determines if AM/PM depending on hour.</li>
 *     <li>getHours() - returns the current value of variable hours to the calling program.</li>
 *     <li>getMinutes() - returns the current value of variable minutes to the calling program.</li>
 *     <li>toString() - returns the value of the time object as a string in the 12hr Am/Pm format.</li>
 *     <li>equals() - compares this time object with another time object for equality.</li>
 *     <li>getDoubleValue() - returns a decimal representation of the time.</li>
 * </ul>
 * @author Daryll Guiang
 * @version Final Project
 */
public class HospitalTime {
    // fields
    private int hours;
    private int minutes;
    private boolean isAM = true;
    private double doubleValue;
    // constructor
    public HospitalTime(int hours, int minutes) {
        // set corrected hours and minutes, sets to 0 if a negative number is entered.
        hours = (hours < 0) ? 0: hours;
        minutes = (minutes < 0) ? 0: minutes;
        // calculate hours in the day and minutes
        this.hours = hours % 24;
        this.minutes = minutes % 60;
        // determine if am or pm
        if (this.hours >= 12) {
            this.isAM = false;
        }
        // create numeric value for time comparison
        double whole = hours;
        double fraction = (double) minutes / 60.0;
        this.doubleValue = (whole + fraction);
    }
    /**
     * <p>Returns the hour value instance variable to calling program.</p>
     * @return value of the hours instance variable.
     */
    public int getHours() {
        return hours;
    }
    /**
     * <p>Returns the minutes value instance variable to calling program.</p>
     * @return value of the minutes instance variable.
     */
    public int getMinutes() {
        return minutes;
    }
    /**
     * <p>Converts the hours and minutes variables to a string representing time, time shown in 12hr AM/PM format.</p>
     * @return value of the hour and minutes variables in 12hr AM/PM string format.
     */
    public String toString() {
        int hour = 0;
        if (this.isAM) {
            hour = this.hours;
        } else {
            hour = (this.hours == 12) ? 12: (this.hours - 12);
        }
        return (((hour < 10) ? "0": "") + hour + ":" + ((this.minutes < 10) ? "0":"") + this.minutes + ((isAM) ? " am":" pm"));
    }
    /**
     * <p>Compares this current object to the parameter object for equality.</p>
     * @param obj comparison object that this current object will be compared to.
     * @return boolean representing equality between comparison object and this object.
     */
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            HospitalTime comparison = (HospitalTime) obj;
            return (hours == comparison.hours && minutes == comparison.minutes
                    && isAM == comparison.isAM && (Math.abs(doubleValue - comparison.doubleValue) <= 1e-13));
        }
        return false;
    }
    /**
     * <p>Returns a double representation of the time using hour.minutes format.</p>
     * @return value of the time in double format.
     */
    public double getDoubleValue() {
        return doubleValue;
    }
}


/**
 * <h2>Medication.java - class representing a medication to be given to a patient.</h2>
 * <p>Instance Variables: </p>
 * <ul>
 *     <li>Variables inherit from parent class Intervention.</li>
 *     <li>String unit - describes the unit/type in which a medication dosage is given. Ex) mg, mcg, units, etc.</li>
 *     <li>double dosage - dosage of the drug given, usually in decimal form.</li>
 *     <li>String form - form of the drug or route of administration such as oral tablets, capsules, IV fluids, subcutaneous injections, etc.</li>
 * </ul>
 * <p>Methods: </p>
 * <ul>
 *     <li>no arg constructor - sets default values of 0 to numbers or n/a to string variables.</li>
 *     <li>full constructor - sets the medication name, ordering physician, administration time, unit, dosage, and form to the
 *     input chosen by the user. Calls setter methods to place the values as the setter methods contain validity checks.</li>
 *     <li>getters() - returns the value of each of the instance variables to the calling program.</li>
 *     <li>setters() - updates the value of each of the instance variables to a value input by the user.</li>
 *     <li>toString() - concatenates the instance variable values into a single formatted string and returns it to the calling program.</li>
 *     <li>equals() - compares the calling Medication object with a parameter object for equality of their instance variables.</li>
 * </ul>
 * @author Daryll Guiang
 * @version Final Project
 */
public class Medication extends Intervention {
    // instance variables
    private String unit;
    private double dosage;

    private String form;
    // constructor
    public Medication() {
        super();
        this.unit = "n/a";
        this.dosage = 0.0;
        this.form = "n/a";
    }
    public Medication(String medName, String orderingPhysician, HospitalTime adminTime, String unit, double dosage, String form) {
        super(medName, orderingPhysician, adminTime);
        setUnit(unit);
        setDosage(dosage);
        setForm(form);
    }
    // getters
    public String getUnit() {
        return unit;
    }
    public double getDosage() {
        return dosage;
    }
    public String getForm() {
        return form;
    }
    // setters
    public void setUnit(String unit) {
        String correctedUnit = unit.replace(",", " ").trim();
        this.unit = (correctedUnit.length() < 1) ? "unknown unit": correctedUnit;
    }
    public void setDosage(double dosage) {
        this.dosage = (dosage < 0.0) ? 0: dosage;
    }
    public void setForm(String form) {
        String correctedForm = form.replace(",", " ").trim();
        this.form = (correctedForm.length() < 1) ? "unknown form": correctedForm;
    }
    // to string
    public String toString() {
        return (super.toString().replace("Task", "Medication") + "Dose: " + dosage + " " + unit + "\nForm: " + form);
    }
    // equals
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            Medication comparison = (Medication) obj;
            boolean sameUnit = unit.equals(comparison.unit);
            boolean sameDosage = (Math.abs(dosage - comparison.dosage) < 1e-13);
            return (sameUnit && sameDosage);
        }
        return false;
    }
}


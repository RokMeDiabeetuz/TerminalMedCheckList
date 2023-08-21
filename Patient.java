import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * <h2>Patient.java - class representing a hospital patient.</h2>
 * <p>Instance Variables: </p>
 * <ul>
 *     <li>String fileName - name of the text file the patient's data will be written in.</li>
 *     <li>int roomNumber - room number of the patient.</li>
 *     <li>String name - name of the patient.</li>
 *     <li>int medicalRecordNumber - medical record number of the patient.</li>
 *     <li>int age - age of the patient.</li>
 *     <li>String gender - gender of the patient.</li>
 *     <li>ArrayList dailyVitals - array list of VitalSign objects taken for the patient.</li>
 *     <li>ArrayList tasks - array list of tasks (interventions, medications, and lab draws) to be completed.</li>
 * </ul>
 * <p>Methods: </p>
 * <ul>
 *     <li>Full constructor - sets patient objects variables to input given by the user. There won't be a no-arg constructor due
 *     to not being able to have a blank patient without information.</li>
 *     <li>getters() - returns the values of the patient's instance variables to the calling program. </li>
 *     <li>Setters() - sets a new value for each of the patient's instance variables.</li>
 *     <li>equals() - compares the calling Patient object with a comparison object for equality.</li>
 *     <li>addNewTask() - adds a task object created by the user to the patient's task list.</li>
 *     <li>addNewVitalSign() - adds a VitalSign object created by the user to the patient's list of vital signs.</li>
 *     <li>viewVitalSigns() - displays to the console the vital signs taken for the patient.</li>
 *     <li>toString() turns the patient's instance variables into a single formatted string and return it to the calling program.</li>
 *     <li>saveDate() - using the fileName variable, the patients instance variables as well as the tasks are saved to a text file.
 *     If the PatientFiles directory is missing or misplaced, the data will not be saved.</li>
 *     <li>sortArrayList() - sorts the list of task to be completed in chronological order.</li>
 *     <li>clearCompletedTask() - removes all task that are completed from the list of tasks.</li>
 * </ul>
 * @author Daryll Guiang
 * @version Final Project
 * <p>Other Notes: realistically, a program like this would not be suitable for actual floor use due to the
 * possibility of cyber attacks and security issues with protected health information (for example, the cyber attack on
 * Scripps Health several years ago). The use of a name and other information that can lead to the identification of a patient
 * would be considered a HIPPA violation. A more realistic version of a program like this would not have the name, medical record
 * number, age, gender, or other identifiable information. A general task list like this would most likely only have the room number and a
 * basic list of tasks. For the sake of this project, I added more information just for practice.</p>
 */
public class Patient {
    // instance variables
    private String fileName;
    private int roomNumber;
    private String name;
    private int medicalRecordNumber;
    private int age;
    private String gender;
    private ArrayList<VitalSign> dailyVitals;
    private ArrayList<Intervention> tasks;
    /**
     * Creates a new patient using user given input.
     * @param roomNumber room number of the patient.
     * @param name name of the patient.
     * @param medicalRecordNumber medical record number of the patient.
     * @param age age of the patient.
     * @param gender gender of the patient.
     */
    public Patient(int fileIndex, int roomNumber, String name, int medicalRecordNumber, int age, String gender) {
        this.fileName = ("patient" + fileIndex + ".txt");
        setRoomNumber(roomNumber);
        setName(name);
        setMedicalRecordNumber(medicalRecordNumber);
        setAge(age);
        setGender(gender);
        this.dailyVitals = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }
    /**
     * <p>Returns the room number of the patient.</p>
     * @return room number in int form.
     */
    public int getRoomNumber() {
        return roomNumber;
    }
    /**
     * <p>Returns the medical record number of the patient.</p>
     * @return medical record number
     */
    public int getMedicalRecordNumber() {
        return medicalRecordNumber;
    }
    /**
     * <p>Returns age of the patient</p>
     * @return patient's age
     */
    public int getAge() {
        return age;
    }
    /**
     * <p>Returns the name of the patient.</p>
     * @return name of the patient in String form.
     */
    public String getName() {
        return name;
    }
    /**
     * <p>Returns the gender of the patient.</p>
     * @return gender of the patient in String form.
     */
    public String getGender() {
        return gender;
    }
    /**
     * <p>Returns a list of all the vital signs taken for the patient.</p>
     * @return list of vital signs.
     */
    public ArrayList<VitalSign> getDailyVitals() {
        return dailyVitals;
    }
    /**
     * <p>Returns a list of interventions to do for the patient.</p>
     * @return List of task for the patient.
     */
    public ArrayList<Intervention> getTasks() {
        sortArrayList();
        return tasks;
    }
    /**
     * Returns the file name of the patient data.
     * @return name of the text file the patient information will be written to.
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * <p>Gives the patient new room number.</p>
     * @param roomNumber new room number for the patient.
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = (roomNumber < 0) ? 0: roomNumber;
    }
    /**
     * <p>Changes the patient's MRN to a new MRN.</p>
     * @param medicalRecordNumber new medical record number for the patient.
     */
    public void setMedicalRecordNumber(int medicalRecordNumber) {
        this.medicalRecordNumber = (medicalRecordNumber < 0) ? 0: medicalRecordNumber;
    }
    /**
     * <p>Changes the current age of the patient.</p>
     * @param age new age for the patient.
     */
    public void setAge(int age) {
        this.age = (age < 0) ? 0: age;
    }
    /**
     * <p>Changes the given gender of the patient.</p>
     * @param gender new gender for the patient.
     */
    public void setGender(String gender) {
        String correctedGender = gender.replace(",", " ").trim();
        this.gender = (correctedGender.length() < 1) ? "unknown gender": gender;
    }
    /**
     * <p></p>
     * @param name
     */
    public void setName(String name) {
        String correctedName = name.replace(",", " ").trim();
        this.name = (correctedName.length() < 1) ? "unknown pt name": name;
    }
    // equals
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            Patient comparison = (Patient) obj;
            boolean sameRoomNumber = roomNumber == comparison.roomNumber;
            boolean sameName = name.equals(comparison.name);
            boolean sameMedicalRecordNumber = medicalRecordNumber == comparison.medicalRecordNumber;
            boolean sameAge = age == comparison.age;
            boolean sameGender = gender == comparison.gender;
            return (sameRoomNumber && sameName && sameMedicalRecordNumber && sameAge && sameGender);
        }
        return false;
    }
    // add new task
    public void addNewTask(Intervention task) {
        if (task != null) {
            this.tasks.add(task);
            sortArrayList();
        }
    }
    // add new vital signs
    public void addNewVitalSign(VitalSign currentVS) {
        dailyVitals.add(currentVS);
        boolean forward = true;
        while (forward) {
            forward = false;
            for (int i = 0; i < dailyVitals.size() -1; i++) {
                VitalSign current = dailyVitals.get(i);
                VitalSign next = dailyVitals.get(i + 1);
                if (current.getTimeTaken().getDoubleValue() > next.getTimeTaken().getDoubleValue()) {
                    dailyVitals.set(i, next);
                    dailyVitals.set(i + 1, current);
                    forward = true;
                }
            }
        }
    }
    // view vital signs
    public void viewVitalSigns() {
        System.out.println("\n\t*** Vital Signs for " + name + " ***");
        if (dailyVitals.size() < 1) {
            System.out.println("\n\tNo vital signs taking yet for " + name);
            return;
        }
        for (int i = 0; i < dailyVitals.size(); i++) {
            System.out.println("\t" + dailyVitals.get(i).toString());
        }
    }
    // toString
    @Override
    public String toString() {
        return ("\tRoom: " + roomNumber + "\tName: " + name + "\tMRN: " + medicalRecordNumber +
                "\tAge: " + age + "\tGender: " + gender);
    }
    // save patient data
    public void saveData() {
        String saveFileName = "PatientFiles/" + fileName;
        File fileToSaveTo = new File(saveFileName.trim());
        try {
            PrintWriter writer = new PrintWriter(fileToSaveTo);
            String firstLine = roomNumber + ", " + name + ", " + medicalRecordNumber + ", " + age + ", " + gender;
            writer.println(firstLine);
            for (int i = 0; i < tasks.size(); i++) {
                Intervention current = tasks.get(i);
                int hour = current.getTime().getHours();
                int mins = current.getTime().getMinutes();
                String line = "";
                if (current.getClass().getSimpleName().equals("Medication")) {
                    Medication med = (Medication) current;
                    line = ("med, " + med.getName() + ", " + med.getOrderingPhysician() + ", " + hour + ", " +
                            mins + ", " + med.getUnit() + ", " + med.getDosage() + ", " + med.getForm());
                } else if (current.getClass().getSimpleName().equals("LabDraw")) {
                    LabDraw lab = (LabDraw) current;
                    line = ("lab, " + lab.getName() + ", " + lab.getOrderingPhysician() + ", " + hour + ", " + mins +
                            ", " + lab.getSample() + ", " + lab.getLocation());
                } else {
                    line = ("task, " + current.getName() + ", " + current.getOrderingPhysician() + ", " + hour + ", " + mins);
                }
                writer.println(line);
            }
            System.out.println("\n\tSaving " + name + "'s data to file \"" + fileName + "\"\n");
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find the directory, check the project directly to ensure data file directory is there.");
            System.out.println("Make sure the PatientFiles directory is in the correct place.");
            System.out.println("Unable to save patient data to file at this time");
        }
    }
    // sort array list
    private void sortArrayList() {
        boolean forward = true;
        while (forward) {
            forward = false;
            for (int i = 0; i < tasks.size() - 1; i++) {
                Intervention currentTask = tasks.get(i);
                Intervention nextTask = tasks.get(i + 1);
                if (currentTask.getTime().getDoubleValue() > nextTask.getTime().getDoubleValue()) {
                    tasks.set(i, nextTask);
                    tasks.set((i + 1), currentTask);
                    forward = true;
                }
            }
        }
    }
    // clear array list of complete task
    public void clearCompletedTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isDone()) {
                tasks.set(i, null);
            }
        }
        while (tasks.remove(null)) {}
    }
}

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * <h2>MedicalTaskList.java - starting point of the Checklist program. Contains menus to process the patient list and individual patients.</h2>
 * <p>Problem Statement: </p>
 * <p>Algorithm/Plan</p>
 * <ul>
 *     <li>main() - start of the program. Checks for data files in the PatientFiles directory. If absent, program may continue
 *     though with limited capabilities such as not being able to save to data files.</li>
 *     <ol>
 *          <li>Create an array list of Patient objects to be used to store all patients on the floor.</li>
 *          <li>Create a string with the directory path where patient.txt files are kept.</li>
 *          <li>Check if the file exists, if so, do the following: </li>
 *          <ul>
 *              <li>Create a File object with the previously created string as its argument.</li>
 *              <li>Get the count of total amount of files in the directory chosen.</li>
 *              <li>Send the array list of patients, the string directory path, and count of total files to the HospitalUtility
 *              load patients method to fill the array list with data from patient files.</li>
 *          </ul>
 *          <li>If the file containing the directory name does not exit, notify the user that the ability to save to text files is
 *          unavailable at this time and to check proper placement of the directory or text files.</li>
 *          <li>Send the filled or empty array list, boolean that determines existence of patient file directory, and
 *          the possible total of text files in the directory to the mainMenu() method.</li>
 *     </ol>
 *     <li>mainMenu() - first menu to select from. Can view all the patients on the floor(floor list), your current patients (patient list),
 *     <ol>
 *          <li>Create an array list of patients that will be used as your personal patient list.</li>
 *          <li>Create infinite loop, label it as outer. Inside the loop, do the following: </li>
 *          <li>Print to console the user's choices, create a choice int variable and call the HospitalUtility.getInteger() method to
 *          get a valid integer value.</li>
 *          <li>If 0 is chosen, notify the user that they are exiting the menu and break out of the outer loop.</li>
 *          <li>If 1 is chosen, check if the list of patients in the floor contains any patient objects. If no patients are contained,
 *          notify user and break to the top of the outer loop. If patients on on the floor list, call the viewPatients() method and
 *          send it the array list to view all the patients.</li>
 *          <li>If 2 is chosen, check to see if there are patients on your personal patient list. If none, break to the top of the loop. If
 *          there is at least 1, call the viewPatients() method and send it your list of patients.</li>
 *          <ul>
 *              <li>Create boolean forward, set to true. Create an infinite loop.</li>
 *              <li>Prompt user for the patient to process using the HospitalUtility.getInteger() method.</li>
 *              <li>If an invalid value such as a non-integer or a negative number is chosen, notify user of error and continue to the top of this loop.</li>
 *              <li>If 0 is chosen, break out of this loop and go back to the outer loop.</li>
 *              <li>If a valid patient number is chosen, subtract 1 from the user input and use this as the index number for the patient to retrieve from
 *              your patient list. Retrieve this patient at this index and send to the processPatient() method.</li>
 *              <li>Set boolean forward to false to get out of this inner loop.</li>
 *          </ul>
 *          <li>If 3 chosen, create an infinite loop to prompt user to add a patient from the floor list to the personal patient list.</li>
 *          <ul>
 *              <li>Call the viewPatients() method to see all patients on the floor on the console.</li>
 *              <li>Create int variable, assign it the value retrieved from the getInteger() method, with 0 as the minimum and the size of
 *              the array list as the maximum.</li>
 *              <li>Continue to the top of this loop if an invalid value (negative number or non int) is chosen.</li>
 *              <li>Break out of this inner loop and back to the main menu loop if 0 is chosen.</li>
 *              <li>If valid value is chosen, subtract 1 from it and use this as an index to retrieve a patient object from the floor list.</li>
 *              <li>Check if the retrieved object is already on your patient list with the .contains() method. If so, notify user that the patient
 *              is already on the list and restart to the top of this loop. Otherwise, continue processing the rest of the code.</li>
 *              <li>Add the patient extracted from the floor list to the personal patient list.</li>
 *              <li>Notify user that the patient was extracted and successfully added to the personal list.</li>
 *              <li>Call the sortByRoomNumber() method to sort patient list by room number.</li>
 *          </ul>
 *          <li>If 4 chosen, call the addPatientsToFloor() method and send it the floor list array and the fileCount of text files in the directory.
 *          Method used to add new patients to the floor list.</li>
 *          <li>If 5 chosen, send the personal patient list to the removePatient() method to remove a patient object from the personal list.</li>
 *          <li>If a non valid value chosen from getInteger, notify user of error and start at the top of the outer loop.</li>
 *     </ol>
 *     <li>processPatient() - once a patient has been selected, this menu used to prompt the user what task needs to be done, removed, or created.</li>
 *     <ol>
 *          <li>Create Scanner object, create an infinite outer loop.</li>
 *          <li>Print to console the user of their choice in activities, from 0 to 8 with the getInteger() method in the HospitalUtility class.</li>
 *          <li>If invalid choice given, notify user and restart at the top of the outer loop.</li>
 *          <li>If 0 chosen, exit out of this method and back to main menu.</li>
 *          <li>If 1 chosen, call HospitalUtility.selectATask() method, send it the current patient object, string Medication, and boolean true for processing.</li>
 *          <li>If 2 chosen, call HospitalUtility.selectATask() method, send it the patient, string LabDraw, and true.</li>
 *          <li>If 3 chosen, call HospitalUtility.selectATask() method, send it the patient, string Intervention, and true.</li>
 *          <li>If 4 chosen, call selectATask() method, sent it the patient, string All, and boolean true.</li>
 *          <li>If 5 chosen, call the current patient objects .clearCompletedTask() method to delete completed task from the list.</li>
 *          <li>If 6 chosen, create a boolean and set to true and create an infinite loop></li>
 *          <ul>
 *              <li>Call the .viewVitalSigns() method of the patient object to show all vital signs taken today.</li>
 *              <li>Prompt user if he/she wants to add a new set of vitals.</li>
 *              <li>If invalid choice entered, notify user and start at the top of this loop.</li>
 *              <li>If 0 chosen, break out of this inner loop and into the menu above..</li>
 *              <li>If 1 chosen, create a new VitalSign object and assign it the object returned from the HospitalUtility.getVitalSign() method.
 *              Assign this newly created VitalSign to the list of vital signs for the patient.</li>
 *              <li>Continue looping until user enters a 0 to exit back to the menu above.</li>
 *          </ul>
 *          <li>If 7 chosen, call the HospitalUtility.addNewTask() method and send it the current patient object to add to task for the patient's task list.</li>
 *          <li>If 8 chosen, send it the current patient, the string All, and boolean false to the selectATask() method to undo a completed task.</li>
 *          <li>Set default case to display on console that some calculation has gone wrong and start at the top of the outer loop.</li>
 *     </ol>
 *     <li>addPatientsToFloor() - user creates a new patient and that patient is added to all the patients on the floor.</li>
 *     <ol>
 *          <li>Iterate through the array list of all patients on the floor, extract each patient's room number and place into an array list.</li>
 *          <li>Create a new patient by calling HospitalUtility.getNewPatient(), with argument 1 + the total count of files in the PatientFile directory.</li>
 *          <li>Take newly created patient, check to see if it is null. If so, exit out of the method.</li>
 *          <li>Check if the new patient already exists in the floor patients list. If so, exit the method.</li>
 *          <li>Check if the new patient's room number is in the room number array list created in the previous 4th step. If so,
 *          notify user that the room number has been taken and leave the method.</li>
 *          <li>If all the checks are passed, notify the user that the new patient has been added to the floor list.</li>
 *          <li>Add new patient to the floor list, sort the floor list by room with sortByRoomNumber() method.</li>
 *     </ol>
 *     <li>removePatient() - removes a patient from your patient task list. Does not remove patients from the floor list.</li>
 *     <ol>
 *         <li>Create an infinite loop, inside the loop do the following: </li>
 *         <li>Check if the array list parameter is empty and does not contain any Patient objects. If so, leave the method.</li>
 *         <li>Call the viewPatients() method with the parameter array list as its argument to show the current patients.</li>
 *         <li>Prompt the user to select an object, get user input for option with the getInteger() method.</li>
 *         <li>If choice is 0, notify user that you are exiting the method and leave the method.</li>
 *         <li>If the choice is invalid, notify the user that it is invalid and restart at the top of this loop.</li>
 *         <li>Otherwise, subtract 1 from the integer retrieved and assign it as an index.</li>
 *         <li>Call the remove() method of the array list, assign it the index from the previous step.</li>
 *         <li>Notify user that the patient has been removed from the list.</li>
 *     </ol>
 *     <li>viewPatients() - shows list of patients available for viewing. Can be used on the floor list or your patient list.</li>
 *     <ol>
 *          <li>Notify the user that on the console that the list of patient is to be displayed.</li>
 *          <li>Create index variable, set to 0. While the index is less than the size of the array list: </li>
 *          <ul>
 *              <li>Create a new Patient class variable, assign it the element in the array list at the current index.</li>
 *              <li>Create a position variable, assign it the current index + 1.</li>
 *              <li>Using printf() method, print the position variable and the patient's room number, name, gender, and age using
 *              getter methods from the patient object.</li>
 *              <li>Increment index by 1.</li>
 *          </ul>
 *          <li>Leave method once all elements in the array list have been displayed.</li>
 *     </ol>
 *     <li>sortByRoomNumber() - sorts the list of patients when a new patient is added by room number.</li>
 *     <ol>
 *         <li>Create boolean, set to true. In a loop and while this boolean is true, do the following: </li>
 *         <li>immediately set the created boolean to false.</li>
 *         <li>Iterate through an array list of patients. Extract the current element to the Current variable, extract the next
 *         element on the list (index of the current + 1) and set to the next Variable.</li>
 *         <li>If the Current patient object has a room number higher than the Next patient object, swap places using the set()
 *         method and their respective index locations (current is i, where as next is i + 1). </li>
 *         <li>Set the boolean back to true if any swap is made, and start back at the top of the loop.</li>
 *         <li>If no swaps are made, then the list is already sorted. Boolean remains false and loop ends.</li>
 *     </ol>
 * </ul>
 * @author Daryll Guiang
 * @version Final Project
 */
public class MedicalTaskList {
    public static void main(String[] args) {
        // create arrayList of patient objects
        ArrayList<Patient> patientList = new ArrayList<>();
        // open and check if a directory exists.
        String directory = "PatientFiles/";
        int fileCount = 0;
        try {
            File folderFile = new File(directory);
            int count = folderFile.list().length;
            fileCount = count;
            HospitalUtility.loadPatientData(patientList, count, directory);
        } catch (Throwable e) {
            System.out.println("Unable to load saved data files, check for proper placement of files and PatientFiles directory.");
            System.out.println("The PatientFiles directory should be inside the same directory as the .java source files.");
            System.out.println("May use the program though the original files will not be updated and you won't be able to save the data.");
        } finally {
            // if unable to open data files, continue with program and manually add patient data with the program.
            mainMenu(patientList, ((new File(directory)).exists()), fileCount);
        }
    }
    /**
     * <p>Menu for viewing, selecting, and removing patients from your personal patient list.</p>
     * @param floorList list of all patients on the floor.
     */
    public static void mainMenu(ArrayList<Patient> floorList, boolean ableToSave, int fileCount) {
        ArrayList<Patient> myPatients = new ArrayList<>();
        outer:
        while (true) {
            System.out.println("\n\t*** Main Menu *** ");
            System.out.println("[1] View Patients on the floor");
            System.out.println("[2] View Patients on your list");
            System.out.println("[3] Add patient to your list");
            System.out.println("[4] Admit patient to the unit");
            System.out.println("[5] Remove patient from your list");
            System.out.println("[0] Exit Application");
            System.out.print("\nSelect a choice: ");
            int choice = HospitalUtility.getInteger(0, 5);
            switch (choice) {
                case 0: // exit application if 0 is chosen
                    String exitMessage = (ableToSave) ? ", changes to patient data saved.": ", unable to save patient data...";
                    System.out.println("\n\tExiting application" + exitMessage);
                    break outer;
                case 1: // view all the patients on the floor (the floor list).
                    if (floorList.size() < 1) {
                        System.out.println("\n\tThere are no patients on the floor");
                        break;
                    }
                    viewPatients(floorList, "All Patients");
                    break;
                case 2: // view all patients on your patient list (your personal patient list).
                    if (myPatients.size() < 1) {
                        System.out.println("\n\tYou have no patients on your list, add from the floor list first");
                        break;
                    }
                    boolean forward = true;
                    while (forward) {
                        viewPatients(myPatients, "My Patients");
                        System.out.print("\n\tSelect patient to process, 0 to exit: ");
                        int patientIndex = HospitalUtility.getInteger(0, myPatients.size());
                        if (patientIndex < 0) {
                            System.out.println("\n\tInvalid selection, try again");
                        } else if (patientIndex == 0) {
                            System.out.println("\n\tExiting selection");
                            forward = false;
                        } else {
                            processPatient(myPatients.get(patientIndex -1));
                            forward = false;
                        }
                    }
                    break;
                case 3: // add a patient from the floor list to your patient list
                    while (true) {
                        viewPatients(floorList, "All Patients");
                        System.out.print("\n\tSelect patient to add to your list, 0 to exit: ");
                        int selected = HospitalUtility.getInteger(0, floorList.size());
                        if (selected < 0) {
                            System.out.println("\n\tInvalid selection from the floor list.");
                            continue;
                        } else if (selected == 0) {
                            System.out.println("\n\tExiting patient selection");
                            break;
                        }
                        Patient current = floorList.get(selected -1);
                        if (myPatients.contains(current)) {
                            System.out.println("\n\tThat patient is already on your list");
                            continue ;
                        }
                        System.out.println("\n\tAdded patient " + current.getName() + " from room " + current.getRoomNumber() + " to your list");
                        myPatients.add(current);
                        sortByRoomNumber(myPatients);
                    }
                    break;
                case 4: // create a new patient and add to the floor list (admitting a patient to the unit).
                    addPatientsToFloor(floorList, fileCount);
                    break;
                case 5: // remove a patient from your patient list
                    removePatient(myPatients);
                    break;
                default: // catch all default, should not be able to get here.
                    System.out.println("\n\tShould not be here, error in calculations");
            }
        }
    }
    /**
     * <p>Main menu to process the currently selected patient.</p>
     * @param patient object of type Patient to be processed.
     */
    public static void processPatient(Patient patient) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // notify user of choices
            System.out.println("\n\t*** Patient " + patient.getName() + " Tasklist ***");
            System.out.println("\t[1] View Medications");
            System.out.println("\t[2] View Lab Draws");
            System.out.println("\t[3] View Interventions");
            System.out.println("\t[4] View All Tasks");
            System.out.println("\t[5] Clear Completed Task");
            System.out.println("\t[6] View Vital Signs");
            System.out.println("\t[7] Add new task to list");
            System.out.println("\t[8] Undo a completed task");
            System.out.println("\t[0] Exit Patient Task List");
            System.out.print("\n\tEnter your choice: ");
            int choice = HospitalUtility.getInteger(0, 8);
            if (choice < 0) {
                System.out.println("\n\tInvalid input, try again");
                continue;
            }
            switch (choice) {
                // exit to main menu if 0 is chosen
                case 0:
                    System.out.println("\n\tExiting task list for patient " + patient.getName());
                    patient.clearCompletedTasks();
                    patient.saveData();
                    return;
                case 1: // process the patient's medicaitons
                    HospitalUtility.selectATask(patient, "Medication", true);
                    break;
                case 2: // process patient's lab draws
                    HospitalUtility.selectATask(patient, "LabDraw", true);
                    break;
                case 3: // process patient's interventions
                    HospitalUtility.selectATask(patient, "Intervention", true);
                    break;
                case 4: // process all tasks
                    HospitalUtility.selectATask(patient, "All", true);
                    break;
                case 5: // clear the completed task to remove from your list
                    patient.clearCompletedTasks();
                    break;
                case 6:// ask the patient for new vital signs
                    boolean forward = true;
                    while (forward) {
                        patient.viewVitalSigns();
                        System.out.printf("\n\tSelect 1 to enter a new VitalSign, 0 to exit: ");
                        int yesOrNo = HospitalUtility.getInteger(0, 1);
                        if (yesOrNo < 0) {
                            System.out.println("\n\tInvalid choice");
                        } else if (yesOrNo == 0) {
                            System.out.println("\n\tExiting..");
                            forward = false;
                        } else {
                            VitalSign newVS = HospitalUtility.getVitalSign();
                            patient.addNewVitalSign(newVS);
                            forward = true;
                        }
                    }
                    break;
                case 7: // create a new task and add to the patient's task list
                    HospitalUtility.addNewTask(patient);
                    break;
                case 8: // undo a completed task
                    HospitalUtility.selectATask(patient, "All", false);
                    break;
                default:
                    System.out.println("\n\tSomething went terribly wrong");
            }
        }
    }
    /**
     * <p>Adds new patient objects to the floor list.</p>
     * @param allPatients Array list of all patient objects on the floor, not just on your personal list.
     */
    public static void addPatientsToFloor(ArrayList<Patient> allPatients, int fileCount) {
        // extract room numbers of all current patients to ensure no duplicate rooms are chosens.
        ArrayList<Integer> roomNumbers = new ArrayList<>();
        for (int i = 0; i < allPatients.size(); i++) {
            roomNumbers.add(allPatients.get(i).getRoomNumber());
        }
        // create new patient, file number assigned is the total amount of data files + 1
        Patient newPatient = HospitalUtility.getNewPatient(fileCount + 1);
        // check patient status for validity.
        if (newPatient == null) {
            System.out.println("\n\tInvalid Patient Data");
            return;
        } else if (allPatients.contains(newPatient)) {
            System.out.println("\n\tThat patient is already on the list");
            return;
        } else if (roomNumbers.contains(newPatient.getRoomNumber())) {
            System.out.println("\n\tThat room number is already taken");
            return;
        }
        // prompt user of successful creation of new patient
        System.out.println("\nAdded patient " + newPatient.getName() + " to the floor in room " + newPatient.getRoomNumber());
        allPatients.add(newPatient);
        // sort patient list by room number
        sortByRoomNumber(allPatients);

    }
    /**
     * <p>Removes a patient from your current task list.</p>
     * @param myPatients task list of patients currently available to you.
     */
    public static void removePatient(ArrayList<Patient> myPatients) {
        while (true) {
            // check if your patient list is empty, exit if so.
            if (myPatients.size() < 1) {
                System.out.println("\n\tNo Patients on your task list, exiting to main menu.");
                return;
            }
            // show current patients on the floor/hospital.
            viewPatients(myPatients, "My Patients");
            System.out.println("\nSelect a patient to remove from your list: 0 to exit");
            int choice = HospitalUtility.getInteger(0, myPatients.size());
            if (choice == 0) {
                System.out.println("\n\tExiting to main menu.");
                return;
            } else if (choice < 0) {
                System.out.println("\n\tInvalid selection, try again");
                continue;
            }
            // extract selected patient from the floor list and add to your patient list.
            Patient current = myPatients.get(choice -1);
            myPatients.remove(current);
            // notify user of successful addition to your patient list
            System.out.println("\nRemoved patient " + current.getName() + " in room " + current.getRoomNumber());
            // sort your patient by their room numbers
            sortByRoomNumber(myPatients);
        }
    }
    /**
     * <p>Displays a list of patients on the console.</p>
     * @param myPatients list of patients to be displayed.
     * @param type type of patient list, either your personal list of the whole floor list.
     */
    private static void viewPatients(ArrayList<Patient> myPatients, String type) {
        System.out.println("\n\t*** " + type + " List ***");
        for (int i = 0; i < myPatients.size(); i++) {
            Patient current = myPatients.get(i);
            int position = i + 1;
            System.out.printf("\t[" + ((position < 10) ? "0" + position: position) + "] Room: " + current.getRoomNumber() + "\t  Name: %-23s"
            + "\tGender: %-10s" + "\tAge:%3d\n", current.getName(), current.getGender(), current.getAge());
        }
    }
    /**
     * <p>Sorts the list of patients by their room number</p>
     * @param patients list of patients to be sorted.
     */
    public static void sortByRoomNumber(ArrayList<Patient> patients) {
        boolean forward = true;
        while (forward) {
            forward = false;
            for (int i = 0; i < patients.size() -1; i++) {
                Patient current = patients.get(i);
                Patient next = patients.get(i + 1);
                if (current.getRoomNumber() > next.getRoomNumber()) {
                    patients.set(i, next);
                    patients.set(i + 1, current);
                    forward = true;
                }
            }
        }
    }
}

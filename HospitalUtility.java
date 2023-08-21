import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * <h2>HospitalUtility.java - utility class for methods used by the Hospital Checklist program. Contains methods to process
 * the list of patients and to process each individual patient.</h2>
 * <p>Methods: </p>
 * <ul>
 *     <li>loadPatientData() - scans text files in the PatientFiles directory and uses scanned data to create Patient objects.</li>
 *     <ol>
 *         <li></li>
 *         <li></li>
 *     </ol>
 *     <li>getInteger() - prompts the for an int value between the specified minimum and maximum, returns the number if valid, -1 if invalid.</li>
 *     <ol>
 *         <li></li>
 *     </ol>
 *     <li>getProperString() - prompts user for string input, removes excess white space, and ensures no commas is entered.</li>
 *     <ol>
 *         <li></li>
 *     </ol>
 *     <li>getTime() - </li>
 *     <ol>
 *         <li></li>
 *     </ol>
 *     <li>selectATask() - </li>
 *     <ol>
 *         <li></li>
 *     </ol>
 *     <li>getVitalSign() - </li>
 *     <ol>
 *         <li></li>
 *     </ol>
 *     <li>isAllCompleted() - </li>
 *     <ol>
 *         <li></li>
 *     </ol>
 *     <li>getNewPatient() - </li>
 *     <ol>
 *         <li></li>
 *     </ol>
 *     <li>addNewTask() - </li>
 *     <ol>
 *         <li></li>
 *     </ol>
 *     <li>getNewMedication() - </li>
 *     <ol>
 *         <li></li>
 *     </ol>
 *     <li>getNewLabDraw() - </li>
 *     <ol>
 *         <li></li>
 *     </ol>
 * </ul>
 * @author Daryll Guiang
 * @version Final Project
 */
public class HospitalUtility {
    /**
     * <p>Scans through each patient text file, extracts the patient data, and uses said data to create patient objects
     * that will be used added to the list of patients on the floor.</p>
     * @param floorPatientList array list of all patients currently on the floor.
     * @param count total number of text files currently in the PatientFiles directory.
     * @param directory name of the directory in which all patient text files are stored.
     */
    public static void loadPatientData(ArrayList<Patient> floorPatientList, int count, String directory) {
        for (int i = 1; i <= count; i++) {
            // create string for the full path name of the file.
            String fileName = directory + "patient" + i + ".txt";
            File patientData = new File(fileName);
            try (Scanner scanner = new Scanner(patientData)){
                // split the first line of data into array, assign each element into a variable for the patient constructor.
                String[] ptData = scanner.nextLine().trim().split(",");
                int roomNumber = Integer.parseInt(ptData[0].trim());
                String name = ptData[1].trim();
                int medicalRecordNumber = Integer.parseInt(ptData[2].trim());
                int age = Integer.parseInt(ptData[3].trim());
                String gender = ptData[4].trim();
                Patient newPatient = new Patient(i, roomNumber, name, medicalRecordNumber, age, gender);
                // scan the rest of the file until the end.
                while (scanner.hasNextLine()) {
                    // scan the current line, split into an array.
                    String[] items = scanner.nextLine().trim().split(",");
                    String type = items[0].trim();
                    String taskName = items[1].trim();
                    String orderingPhysician = items[2].trim();
                    int hour = Integer.parseInt(items[3].trim());
                    int minutes = Integer.parseInt(items[4].trim());
                    HospitalTime time = new HospitalTime(hour, minutes);
                    switch (type) {
                        case "med":
                            String unit = items[5].trim();
                            double dosage = Double.parseDouble(items[6].trim());
                            String form = items[7].trim();
                            newPatient.addNewTask(new Medication(taskName, orderingPhysician,
                                    time, unit, dosage, form));
                            break;
                        case "lab":
                            String sample = items[5].trim();
                            String location = items[6].trim();
                            newPatient.addNewTask(new LabDraw(taskName, orderingPhysician, time, sample, location));
                            break;
                        case "task":
                            newPatient.addNewTask(new Intervention(taskName, orderingPhysician, time));
                            break;
                        default:
                            System.out.println("Something went wrong");
                    }
                }
                floorPatientList.add(newPatient);
                // catch if file not found, can be caused by file not in the correct directory or invalid file name.
            } catch (FileNotFoundException e) {
                System.out.println("\nFile " + fileName + " is not found in the correct directory, is missing, or is improperly named.");
                System.out.println("Check for proper placement of the data file or proper naming using the \"patientn.txt\" (n being a number) format.");
                System.out.println("Continuing with loading of patients, skipping this patient data file.");
                // check if an array index if out of bounds, indicates data in data files not properly formatted for scanning
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\nFile " + fileName + " is corrupted and is not properly scanned.");
                System.out.println("Check the data lines for proper separation of data with (\",\") or incorrect formatting of data in the file");
                System.out.println("Continuing with loading of patients, skipping this patient data file.");
                // catches other errors such as missing directories, or other files in the directory that should not be there.
            } catch (Exception e) {
                System.out.println("\nFile error, check data files and directory for possible input issues.");
                System.out.println("Ensure no other files other than patient.txt files are in the directory or files are not empty.");
                System.out.println("Error made while scanning " + fileName);

            }
        }
    }
    /**
     * <p>Picks a valid number than is between the two parameter numbers provided.</p>
     * @param min minimum value that can be chosen by the user
     * @param max maximum value that can be chosen by the user
     * @return the value chosen by the user or the -1 indicating invalid input.
     */
    public static int getInteger(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            if (choice < min || choice > max) {
                return -1;
            }
            return choice;
        }
        scanner.nextLine();
        return -1;
    }
    /**
     * <p>Prompts user for a string. Ensures the string is not empty or has a comma in it to prevent scanning problems in text files.</p>
     * @return properly formatted string that is not empty or contains a comma.
     */
    private static String getProperString() {
        Scanner scanner = new Scanner(System.in);
        String item = "";
        while (true) {
            item = scanner.nextLine().trim();
            if (item.length() < 1 || item.contains(",")) {
                System.out.print("\n\tImproper String, can't be empty or have commas, try again: ");
                continue;
            }
            return item;
        }
    }
    /**
     * <p>Prompts user for an hour and minutes values, returns those values as a time object.</p>
     * @return HospitalTime object representing a time when a task is to be completed.
     */
    public static HospitalTime getTime() {
        while (true) {
            // prompt user for the hour
            System.out.print("\n\tEnter the hour from 0 to 23: ");
            int hour = getInteger(0, 23);
            if (hour < 0) {
                System.out.println("\n\tInvalid input, try again");
                continue;
            }
            // prompt user for the minutes
            System.out.print("\n\tEnter the minutes from 0 to 59: ");
            int minutes = getInteger(0, 59);
            if (minutes < 0) {
                System.out.println("\n\tInvalid input, try again");
                continue;
            }
            // create new HospitalTime object, return to calling program.
            return (new HospitalTime(hour, minutes));
        }
    }
    /**
     * <p>Prompts user to select a task to be completed if not done, or to undo if it is completed.</p>
     * @param patient patient object whose task list is to be processed.
     * @param type type of Intervention object to be completed or to be undone.
     * @param toComplete boolean that determines if a task is to be completed or to be undone (true to complete, false to undo).
     */
    public static void selectATask(Patient patient, String type, boolean toComplete) {
        while (true) {
            // extract task list
            ArrayList<Intervention> list = patient.getTasks();
            // Notify User of Count left
            System.out.println("\n\t*** " + type + " tasks for " + patient.getName() + " ***");
            // create arrayList to hold the type of count wanted.
            ArrayList<Intervention> typeList = new ArrayList<>();
            // iterate through the list
            for (int i = 0, j = 0; i < list.size(); i++) {
                Intervention current = list.get(i);
                String currentType = current.getClass().getSimpleName().trim();
                // prints out only medications if the user chose medications to show
                if (currentType.equals("Medication") && currentType.equals(type)) {
                    Medication med = (Medication) current;
                    typeList.add(med);
                    j++;
                    System.out.println("\tMed " + ((j < 10) ? "0" + j: "" + j) + " - [" + ((med.isDone()) ? "DONE": "    ") + "]  "
                            + med.getTime() + " - " + med.getName() + " - " + med.getDosage() + " " +  med.getUnit() + " - " + med.getForm());
                    // prints out only lab draws
                } else if (currentType.equals("LabDraw") && currentType.equals(type)) {
                    LabDraw lab = (LabDraw) current;
                    typeList.add(lab);
                    j++;
                    System.out.println("\tLab " + ((j < 10) ? "0" + j: "" + j) + " - [" + ((lab.isDone()) ? "DONE": "    ") + "]  "
                            + lab.getTime() + " - " + lab.getName() + " - " + lab.getSample() + " - " + lab.getLocation());
                    // prints out only intervention tasks
                } else if (currentType.equals("Intervention") && currentType.equals(type)) {
                    typeList.add(current);
                    j++;
                    System.out.println("\tTask " + ((j < 10) ? "0" + j: "" + j) + " - [" + ((current.isDone()) ? "DONE": "    ") + "]  "
                            + current.getTime() + " - " + current.getName());
                    // prints out all task
                } else if (type.equals("All")) {
                    typeList.add(list.get(i));
                    j++;
                    System.out.println("\tTask " + ((j < 10) ? "0" + j: "" + j) + " - [" + ((current.isDone()) ? "DONE": "    ") + "]  Type: "
                            + current.getClass().getSimpleName() + " - " + current.getTime() + " - " + current.getName());
                }
            }
            // if the user wants to complete a task, leave menu if all task are complete.
            if (toComplete) {
                if (isAllCompleted(typeList, false)) {
                    System.out.println("\n\tAll tasks are completed, exiting..");
                    return;
                }
                // if user wants to undo a task, leave the menu if no task are complete
            } else {
                if (isAllCompleted(typeList, true)) {
                    System.out.println("\n\tNo task has been completed, exiting...");
                    return;
                }
            }
            // prompt user for task to complete or undo
            System.out.print("\n\tChoose a item to " + ((toComplete) ? "complete":"undo") +", 0 to exit: ");
            int choice = getInteger(0, typeList.size());
            if (choice < 0) {
                System.out.println("\n\tInvalid choice, try again");
                continue;
            } else if (choice == 0) {
                System.out.println("\n\tExiting " + (type.equals("All") ? "All Task": type) + " menu");
                break;
            }
            choice -= 1;
            // if the user wants to complete a task and the task is already done, go back to the start of loop.
            if (toComplete) {
                if (typeList.get(choice).isDone()) {
                    System.out.println("\n\tThat task is already completed, can't complete again");
                    continue;
                }
                // if the user wants to undo a task and the task is not yet done, go back to the top of the loop.
            } else {
                if (!(typeList.get(choice).isDone())) {
                    System.out.println("\n\tThat task is not yet done, can't undo");
                    continue;
                }
            }
            // set if the task is completed using the boolean that determines if the task to be done or undone.
            typeList.get(choice).setDone(toComplete);
        }
    }
    /**
     * <p>Prompts user for vital signs input and returns an object representing a set of vital signs.</p>
     * @return VitalSign object representing the values input by the user.
     */
    public static VitalSign getVitalSign() {
        // create scanner object
        Scanner scanner = new Scanner(System.in);
        // declare default values
        double temperature = 0;
        int hearRate = 0;
        int respiratoryRate = 0;
        int systolicBP = 0;
        int diastolicBP = 0;
        int oxygenSaturation = 0;
        HospitalTime timeTaken = null;
        // prompt user for temperature
        // **note: 80 to 110 are not realistic temps, added for increased range of inputs.
        while (true) {
            System.out.print("\n\tEnter temperature from 0 to 115: ");
            if (scanner.hasNextDouble()) {
                temperature = scanner.nextDouble();
                if (temperature < 0 || temperature > 115) {
                    System.out.println("\t\nInvalid input, try again");
                    continue;
                }
                break;
            } else {
                // yup.. that pesky \n thing again.
                scanner.nextLine();
                System.out.println("\n\tInvalid input, try again");
            }
        }
        // prompt user for a heart rate
        // **note: 200+ is danger territory, 0 is dead.
        while (true) {
            System.out.print("\n\tEnter heart rate from 0 to 250: ");
            hearRate = getInteger(0, 250);
            if (hearRate < 0) {
                System.out.println("\n\tInvalid heart rate, try again");
                continue;
            }
            break;
        }
        // prompt user for the respiratory rate per minute
        // **note: above 40 and the patient is in serious trouble, added more to increase range of user input.
        while (true) {
            System.out.print("\n\tEnter respiratory rate from 0 to 60: ");
            respiratoryRate = getInteger(0, 60);
            if (respiratoryRate < 0) {
                System.out.println("\n\tInvalid input, try again");
                continue;
            }
            break;
        }
        // prompt user for the systolic blood pressure
        // **note: realistically, above 190 is dangerous, moved to 250 for added range (250 is impossible w/o stroking out).
        while (true) {
            System.out.print("\n\tEnter systolic BP from 0 to 250: ");
            systolicBP = getInteger(0, 250);
            if (systolicBP < 0) {
                System.out.println("\n\tInvalid input, try again");
                continue;
            }
            break;
        }
        // prompt user for the diastolic blood pressure
        // ** note: realistically, DBP above 120 is dangerous, moved it to 150 just for added range (not sure this is even possible).
        while (true) {
            System.out.print("\n\tEnter diastolic BP from 0 to 150: ");
            diastolicBP = getInteger(0, 150);
            if (diastolicBP < 0) {
                System.out.println("\n\tInvalid input, try again");
                continue;
            }
            break;
        }
        // prompt user for the patient's oxygen saturation, 100 is max, 0 is min
        // **note: if you get a valid reading anywhere near 0 your patient is pretty much dead.
        while (true) {
            System.out.print("\n\tEnter oxygen saturation: 0 to 100");
            oxygenSaturation = getInteger(0, 100);
            if (oxygenSaturation < 0) {
                System.out.println("\n\tInvalid input, try again");
                continue;
            }
            break;
        }
        // prompt user for a time object
        while (true) {
            timeTaken = getTime();
            if (timeTaken == null) {
                System.out.println("\n\tInvalid time given, try again");
                continue;
            }
            break;
        }
        return (new VitalSign(temperature, hearRate, respiratoryRate, systolicBP, diastolicBP, oxygenSaturation, timeTaken));
    }
    /**
     * <p>Determines if all task are completed if boolean provided is false, or determines if no task completed if the boolean provided is true</p>
     * @param list array list of task from the current patient.
     * @param reversed boolean to determine if the item to be checked is a task that is done or a task that is not yet done.
     * @return boolean value representing if all task are done, or if no task are done.
     */
    private static boolean isAllCompleted(ArrayList<Intervention> list, boolean reversed) {
        for (int i = 0; i < list.size(); i++) {
            if ((list.get(i).isDone()) == reversed) {
                return false;
            }
        }
        return true;
    }
    /**
     * <p>Prompts user for the patients information and creates a new Patient object that is added to the list of all hospital patients.</p>
     * @param currentPatientCount number of total patients in the hospital list
     * @return
     */
    public static Patient getNewPatient(int currentPatientCount) {
        int roomNum = 0;
        String name = "";
        int medicalRecordNumber = 0;
        int age = 0;
        // prompt user for room number
        while (true) {
            System.out.print("\n\tEnter the room number from 100 to 999: ");
            roomNum = getInteger(100, 999);
            if (roomNum < 0) {
                System.out.println("\n\tInvalid room selection, try again");
                continue;
            }
            break;
        }
        // prompt user for patient's name
        System.out.print("\n\tEnter the patients name, can't have a \",\" in it: ");
        name = getProperString();
        // prompt user for the medical record number
        while (true) {
            System.out.print("\n\tEnter medical record number from 1 to 10,000,000: ");
            medicalRecordNumber = getInteger(1, 10000000);
            if (medicalRecordNumber < 0) {
                System.out.println("\n\tInvalid MRN selection");
                continue;
            }
            break;
        }
        // prompt user for the age
        while (true) {
            System.out.printf("\n\tEnter the patient's age from 0 to 130: ");
            age = getInteger(0, 130);
            if (age < 0) {
                System.out.println("\n\tInvalid age selection");
                continue;
            }
            break;
        }
        // prompt user for the patients gender
        System.out.print("\n\tEnter the patients gender, can't have a \",\" in it: ");
        String gender = getProperString();
        // create the new patient
        return (new Patient(currentPatientCount, roomNum, name, medicalRecordNumber, age, gender));
    }
    /**
     * <p>Creates a new Intervention, Medication, or LabDraw object and adds it to the patients to do list.</p>
     * @param patient Patient object which the newly created Intervention object will be given to.
     */
    public static void addNewTask(Patient patient) {
        while (true) {
            // prompt user for choice
            System.out.println("\n\t*** Add New Task Menu ***");
            System.out.println("\t[1] Add new Medication");
            System.out.println("\t[2] Add new LabDraw");
            System.out.println("\t[3] Add new Intervention");
            System.out.print("\n\tEnter type to add, 0 to exit: ");
            int choice = getInteger(0, 3);
            // exit if 0 is selected, prompt user to start again if invalid choice given.
            if (choice == 0) {
                System.out.println("\n\tExiting task addition menu");
                return;
            // if less than 0 is selected, prompt user of error and restart selection
            } else if (choice < 0) {
                System.out.println("\n\tInvalid choice, try again");
                continue;
            }
            // change the label depending on what type of task (Intervention, Medication, LabDraw) is to be done.
            String label = "Task";
            if (choice == 1) {
                label = "Medication";
            } else if (choice == 2) {
                label = "LabDraw";
            }
            // prompt user for the name of the task.
            System.out.print("\n\tEnter the name of the " + label + ": ");
            String name = getProperString();
            // prompt user for the name of the ordering physician
            System.out.print("\n\tEnter the name of the ordering Physician: ");
            String physician = getProperString();
            // prompt user to get the time the task will be done.
            HospitalTime adminTime = getTime();
            // create and add new Intervention object and add to task list.
            switch (choice) {
                case 1:
                    patient.addNewTask(getNewMedication(name, physician, adminTime));
                    break;
                case 2:
                    patient.addNewTask(getNewLabDraw(name, physician, adminTime));
                    break;
                case 3:
                    patient.addNewTask(new Intervention(name, physician, adminTime));
                    break;
                default:
                    // prompt user of error
                    System.out.println("\n\tShould not be here");
                    break;
            }

        }
    }
    /**
     * <p>Creates a new Medication object from values given by the user and returns it to the calling program.</p>
     * @param name name of the Medication.
     * @param physician name of the ordering physician.
     * @param time object representing the time the medication is to be given.
     * @return new Medication object with values given by the user.
     */
    private static Medication getNewMedication(String name, String physician, HospitalTime time) {
        Scanner scanner = new Scanner(System.in);
        // set variables to default values
        double dosage = 0.0;
        // prompt user to enter the dosage
        while (true) {
            System.out.print("\n\tEnter the dosage: ");
            if (scanner.hasNextDouble()) {
                dosage = scanner.nextDouble();
                scanner.nextLine();
                if (dosage < 0.0) {
                    System.out.print("\n\tInvalid input, can't have negative dosage: ");
                    continue;
                }
                break;
            } else {
                scanner.nextLine();
                System.out.print("\n\tInvalid input, try again: ");
            }
        }
        // prompt user to enter the units for the dosage
        System.out.print("\n\tEnter the unit type such as mg, mcg, g, etc: ");
        String unit = getProperString();
        // prompt user for the form of the medication
        System.out.print("\n\tEnter medication form such as Tablet, Capsule, IV, etc: ");
        String form = getProperString();
        // return newly created medication
        return (new Medication(name, physician, time, unit, dosage, form));
    }
    /**
     * <p>Creates a new LabDraw object and returns it to the calling program.</p>
     * @param name name of the lab draw as a string.
     * @param physician name of the doctored who ordered the lab draw.
     * @param time Object representing the time the lab draw is to be done.
     * @return new LabDraw object given the values entered by the user.
     */
    private static LabDraw getNewLabDraw(String name, String physician, HospitalTime time) {
        // prompt user for the type of sample
        System.out.print("\n\tEnter the sample type such as tissue, secretion, blood, etc: ");
        String tissue = getProperString();
        // prompt user for the location of the sample
        System.out.print("\n\tEnter the location of the sample: ");
        String location = getProperString();
        return (new LabDraw(name, physician, time, tissue, location));
    }
}

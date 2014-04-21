package csc207b07.example.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import csc207b07.example.data.PostTriage;
import csc207b07.example.data.Prescription;
import csc207b07.example.data.VitalRecord;

/** A manager for PostTriage
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class PostTriageManager {
		
	/**A map of PostTriage patients*/
	private Map<String, PostTriage> patients;

	/**
	 * Constructs a new Manager to manage a collection of PostTriage stored in 
	 * directory dir in the file fileName.
	 * @param dir The Directory in which the data file is stored.
	 * @param fileName The data file.
	 * @throws IOException
	 * @throws ParseException
	 * 
	 */
	public PostTriageManager(File dir, String fileName) throws IOException, 
	ParseException{
		this.patients = new HashMap<String, PostTriage>();
		
		//stores information from the data file into the patients Map
        File file = new File(dir, fileName);
        if (file.exists()) {
			this.readFromFile(dir, file.getPath());
        } else {
            file.createNewFile();
        }
	}
	
	/**
	 * Adds a PostTriage to this manager.
	 * @param patient A PostTriage object.
	 * @param cardNum The health card number of the given patient.
	 */
    public void put(PostTriage patient, String cardNum) {
    	patients.put(cardNum, patient);
    }
    
    /**
     * Removes a specified PostTriage object from this manager.
     * @param patient The PostTriage object to be removed.
     */
    public void remove(PostTriage patient){
    	patients.remove(patient.getHealthCardNum());
    }
    
    /**
     * Returns the PostTriage objects managed by this manager.
     * @return A Map of PostTriage.
     */
    public Map<String, PostTriage> getPatients() {
        return patients;
    }
    
    /**
     * Saves records to the data file. 
     * @param outputStream The out put stream to write the data file to.
     */
    public void saveToFile(FileOutputStream outputStream){
    	try{
    		for (String i : patients.keySet()){
    			PostTriage patient = patients.get(i);
    			Date date = patient.getAdmittedTime();
    			outputStream.write((date.toString() + "," + patient.toString() + 
    					"\n").getBytes());
    		}
    	}catch (IOException e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * Stores information to patients from the file at path filePath.
     * @param file The directory in which this file is located.
     * @param filePath The file path in which the data is retrieved.
     * @throws ParseException
     * @throws IOException
     */
    public void readFromFile(File file, String filePath) throws ParseException,
    IOException {
        Scanner scanner = new Scanner(new FileInputStream(filePath));
    	String[] record;
     
        while(scanner.hasNextLine()) {
        	record = scanner.nextLine().split(",");
        	String name = record[1];
        	String dob = record[2];
        	String healthCard = record[3];
        	Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", 
        			Locale.ENGLISH).parse(record[0]);
        	
        	//retrieves/creates a map of VitalRecords for this patient
            RecordManager m = new RecordManager(file, record[3] + ".txt");
        	Map<Date, VitalRecord> vitals = m.getRecords();
        	PrescriptionManager pm = new PrescriptionManager(file, record[3] + "Prescription.txt");
        	Map<Date, Prescription> prescriptions = pm.getRecords();
        	
        	//constructs a new PostTriage based on information read from line.
        	PostTriage newPatient = new PostTriage(name, dob, healthCard);
        	newPatient.setVitalRecord(vitals);
        	newPatient.setAdmittedTime(date);
        	newPatient.setPrescriptions(prescriptions);
        	patients.put(healthCard, newPatient);
        }
        scanner.close();
    }	
}
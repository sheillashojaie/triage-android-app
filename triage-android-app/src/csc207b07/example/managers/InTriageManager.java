package csc207b07.example.managers;

import android.annotation.SuppressLint;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import csc207b07.example.data.InTriage;
import csc207b07.example.data.Prescription;
import csc207b07.example.data.VitalRecord;

/** A manager for InTriage.
* @author c2phelan
* @author g3s
* @author c3zhanhk
* @author g3soul
* @author c2zhoush
*/
public class InTriageManager {
		
	/**A list of InTriage patients*/
	private List<InTriage> patients;
	
	/**
	 * Constructs a new Manager to manage a collection of InTriage stored in 
	 * directory dir in the file fileName.
	 * @param dir The directory where the data file is store.
	 * @param fileName The name of the data file.
	 * @throws IOException
	 * @throws ParseException
	 */
	public InTriageManager(File dir, String fileName) throws IOException, 
	ParseException{
		
		this.patients = new ArrayList<InTriage>();
		
		//stores information from the data file into the patients list.
        File file = new File(dir, fileName);
        if (file.exists()) {
			this.readFromFile(dir, file.getPath());
        } else {
            file.createNewFile();
        }
	}
	
	/**
	 * Adds InTriage object to this InTriageManager.
	 * @param patient An InTriage patient.
	 */
    public void add(InTriage patient) {
        patients.add(patient);
    }
    
    public void remove(InTriage patient) {
    	int b = Integer.parseInt(patient.getHealthCardNum());
    		for (int i = 0; i < patients.size(); i++){
    			int a = Integer.parseInt(patients.get(i).getHealthCardNum());
    				if (a == b){
    	patients.remove(i);
    	}
    	}
    }
   
    /**
     * Returns the InTriage objects managed by this manager.
     * @return A list of InTriage.
     */
    public List<InTriage> getPatients() {
        return patients;
    }
    
    /**
     * Saves records to the data file. 
     * @param outputStream The out put stream to write the data file to.
     */
    public void saveToFile(FileOutputStream outputStream){
    	try{
    		for (InTriage i : patients){
    			outputStream.write((i.toString() + "," + i.getArrivalTime().
    					toString() + "\n").getBytes());
    		}
    	}catch (IOException e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * Stores information to patients from the file at path filePath.
     * @param file The directory this file is located in.
     * @param filePath The file path in which the data is retrieved.
     * @throws FileNotFoundException
     * @throws ParseException
     */
    @SuppressLint("SimpleDateFormat")
	public void readFromFile(File file, String filePath) throws ParseException,
	IOException, FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(filePath));
    	String[] record;
        while(scanner.hasNextLine()) {
        	record = scanner.nextLine().split(",");
        	String name = record[0];
        	String dob = record[1];
        	String healthcard = record[2];
        	
        	//converts string to date of specified format.
        	Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", 
        			Locale.ENGLISH).parse(record[3]);
            
        	//retrieves/creates a map of VitalRecords for this patient
        	RecordManager m = new RecordManager(file, record[2] + ".txt");
        	Map<Date, VitalRecord> vitals = m.getRecords();    
        	PrescriptionManager pm = new PrescriptionManager(file, record[2] + "Prescription.txt");
        	Map<Date, Prescription> prescriptions = pm.getRecords();
        	//constructs a new InTriage based on information read from line.
        	InTriage newPatient = new InTriage(name, dob, healthcard);
        	newPatient.setArrivalTime(date);
        	newPatient.setVitalRecord(vitals);
        	newPatient.setUrgencyPoint();
        	newPatient.setPrescriptions(prescriptions);
        	patients.add(newPatient);
        	
      
        }
        scanner.close();
    }
}
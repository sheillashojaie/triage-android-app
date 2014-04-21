package csc207b07.example.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

/**
 * Patient is an abstract superclass of InTriage and PostTriage which contains 
 * information on the personal identity of the Patient as well a record of all 
 * their vital sign updates and a record of their prescriptions.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public abstract class Patient implements Serializable {
	
	/** A unique ID. */
	private static final long serialVersionUID = -9157082823927537545L;
	
	/** This Patient's name*/
	private String name;
	
	/** This Patient's date of birth.*/
	private String dob;
	
	/** This Patient's health card number.*/
	private String healthCardNum;
	
	/** This Patient's record of vital sign updates.*/
	private Map<Date, VitalRecord> vitalSigns;
	
	/** This Patient's record of prescriptions. */
	private Map<Date, Prescription> prescriptions;
	
	/**
	 * Initializes name name, date of birth dob, and health card number 
	 * healthCardNum for subclasses to inherit upon construction, as well as
	 * a record of their vital sign updates vitalSigns and a record of their
	 * prescriptions prescriptions.
	 * @param name The name of this Patient.
	 * @param dob The date of birth of this Patient.
	 * @param healthCardNum The health card number of this Patient.
	 */
	public Patient(String name, String dob, String healthCardNum){
		this.name = name;
		this.dob = dob;
		this.healthCardNum = healthCardNum;
		this.vitalSigns = new HashMap<Date, VitalRecord>();
		this.prescriptions = new HashMap<Date, Prescription>();
	}
	
	/**
	 * Returns the name of this Patient.
	 * @return The name of this Patient.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returns the date of birth of this Patient.
	 * @return The date of birth of this Patient.
	 */
	public String getDob(){
		return dob;
	}
	
	/**
	 * Returns the health card number of this Patient.
	 * @return The health card number of this Patient.
	 */
	public String getHealthCardNum(){
		return healthCardNum;
	}

	/**
	 * Sets the name of this Patient to name name.
	 * @param name The name to be set of this Patient.
	 */
    public void setName(String name){
    	this.name = name;
    }
    
    /**
     * Sets the date of birth of this Patient to date of birth dob.
     * @param dob The date of birth to be set of this Patient.
     */
    public void setDob(String dob){
    	this.dob = dob;
    }
    
    /**
     * Sets the health card number of this Patient to health card number 
     * healthCardNum.
     * @param healthCardNum The health card number to be set of this Patient.
     */
    public void setHealthCard(String healthCardNum){
    	this.healthCardNum = healthCardNum;
    }
    
    /**
     * Sets the vital sign record of this Patient to vital sign record 
     * vitalRecord.
     * @param vitalRecord The vital sign record to be set of this Patient.
     */
    public void setVitalRecord(Map<Date, VitalRecord> vitalRecord){
    	vitalSigns = vitalRecord;
    }
    
    /**
     * Registers a single vital sign entry vitalRecord into this Patient's 
     * record of vital signs with timestamp of update date.
     * @param date The timestamp at which this update is recorded.
     * @param vitalRecord The single vital sign entry to be registered.
     */
    public void setVitalSigns(Date date, VitalRecord vitalRecord){
    	vitalSigns.put(date, vitalRecord);
    	
    }
    
    /**
     * Returns this Patient's vital signs record.
     * @return This Patient's vital signs record.
     */
    public Map<Date, VitalRecord> getVitalRecord(){
    	return vitalSigns;
    }
    
    /**
     * Returns a string representation of this Patient.
     */
    public String toString(){
    	String s = name + "," + dob + "," + healthCardNum;
    	return s;
    }

    /**
     * Returns this Patient's prescriptions record.
     * @return This Patient's prescriptions record.
     */
	public Map<Date, Prescription> getPrescription() {
		return prescriptions;
	}
	
    /**
     * Registers a single prescriptions entry prescription into this Patient's 
     * record of vital signs with prescription timestamp date.
     * @param date The timestamp at which this prescription is prescribed.
     * @param prescription The single prescription entry to be registered.
     */
	public void setPrescription(Date date, Prescription prescription){
		prescriptions.put(date, prescription);
	}
	
    /**
     * Sets the prescription record of this Patient to prescription record 
     * prescriptions.
     * @param prescriptions The prescription record to be set of this Patient.
     */
	public void setPrescriptions(Map<Date, Prescription> prescriptions){
		this.prescriptions = prescriptions;
	}

}


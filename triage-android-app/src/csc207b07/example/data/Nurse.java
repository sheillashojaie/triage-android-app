package csc207b07.example.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * A Nurse represents a type of user of this Triage system and 
 * implements the TriageNurse interface. A Nurse registers InTriage Patients into
 * Triage and records updates of their vital signs. 
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class Nurse extends User implements TriageNurse{

	/** A unique ID. */
	private static final long serialVersionUID = -6102914023182968222L;


	/**
	 * Updates the temperature temp, blood pressure bloodPressue, heart rate 
	 * heartRate of the InTriage Patient with health card number healthCardNum,
	 * as well as setting a comment comment and a timestamp date. 
	 * @param date The timestamp of this vital update.
	 * @param healthCardNum The health card number of the InTriage Patient 
	 * whose vitals are to be updated.
	 * @param temp The updated temperature of the InTriage Patient with the 
	 * specified health card number.
	 * @param bloodPressure The updated blood pressure of the InTriage Patient 
	 * with the specified health card number.
	 * @param heartRate The updated heart rate of the InTriage Patient with the
	 * specified health card number.
	 * @param comment The updated comment of symptoms of the InTriage Patient 
	 * with the specified health card number.
	 */
	public void updateVitals(Date date, String healthCardNum, String temp,
			String bloodPressure, String heartRate) {
		VitalRecord vitalRecord = new VitalRecord(temp, bloodPressure, 
			heartRate);
		InTriage patient = getInTriagePatients().getPatientByCardNum(healthCardNum);
		patient.setVitalSigns(date, vitalRecord);
	}
		
	/**
	 * Returns a list of the InTriage Patients by order of earliest to latest 
	 * arrival. 
	 * @return A List of the InTriage Patients by order of earliest to latest 
	 * arrival.
	 */
	public List<InTriage> getInTriagePatientsByArrivalTime(){
		return getInTriagePatients().getPatientsByArrival();
	}
	
	/**
	 * Returns a list of the InTriage Patients by order of most to least 
	 * urgent. 
	 * @return A List of the InTriage Patients by order of most to least urgent. 
	 */
	public List<InTriage> getInTriagePatientsByUrgency(){
		return getInTriagePatients().getPatientsByUrgency();
	}
	
	/**
	 * Returns a list of the PostTriage Patients.
	 * @return A List of the PostTriage Patients.
	 */
	public List<PostTriage> getPostTriagePatientsAsList(){
		Collection<PostTriage> s = postTriagePatients.values();
		List<PostTriage> L= new ArrayList<PostTriage>(s);
		return L;
	}


}


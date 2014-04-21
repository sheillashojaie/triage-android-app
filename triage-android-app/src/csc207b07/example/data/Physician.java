package csc207b07.example.data;

import java.util.Date;

/**
 * A Physician represents a type of user of this Triage system and 
 * implements the TriagePhysician interface. A Physician can record 
 * prescriptions to Patients.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class Physician extends User implements TriagePhysician{
	
	/** A unique ID. */
	private static final long serialVersionUID = -1264370690255435555L;

	/**
	 * Sets a Prescription entry with medication name name, instructions 
	 * instructions, at timestamp date to the prescription record of Patient
	 * with health card number 
	 * healthCardNum.
	 */
	public void recordPrescription(Date date, String healthCardNum, String name, String instructions){
		
		// The Patient with health card number healthCardNum is InTriage.
	 	if (this.getInTriagePatients().contains(healthCardNum)){
	 		Patient patient = this.getInTriagePatients().getPatientByCardNum(healthCardNum);
			Prescription prescription = new Prescription(name, instructions);
			
			// Sets a prescription entry to the prescription record of this 
			// InTriage Patient.
			patient.setPrescription(date, prescription);
	 	}
	 	
	 // The Patient with health card number healthCardNum is PostTriage.
	 	else if (this.getPostTriagePatients().containsKey(healthCardNum)){
	 		Patient patient 
	 		= this.getPostTriagePatients().get(healthCardNum);
			Prescription prescription = new Prescription(name, instructions);
			
			// Sets a prescription entry to the prescription record of this 
			// PostTriage Patient.
			patient.setPrescription(date, prescription);
	 	}
	}

}

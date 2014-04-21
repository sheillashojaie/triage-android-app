package csc207b07.example.data;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A User is an abstract superclass of Nurse and Physician which represents
 * the user of this Triage system. It contains all of the patients in the 
 * system, both those who are in triage and those who are no longer in triage,
 * and has the ability to register new Patients into the system.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public abstract class User implements Serializable, Triage {
	
	/** A unique ID. */
	private static final long serialVersionUID = -6737693249707497008L;

	/** The InTriage Patients currently in triage.*/
	public static MultiLinkedList inTriagePatients = new MultiLinkedList();

	/** The PostTriage Patients who have been admitted to a doctor.*/	
	public static Map<String, PostTriage> postTriagePatients  = new HashMap<String, PostTriage>();
	
	/**
	 * Returns a MultiLinkedList of the InTriage Patients in this system.
	 */
	public MultiLinkedList getInTriagePatients() {
		return inTriagePatients;
	}

	/**
	 * Returns a Map of the PostTriage Patients in this system, 
	 * mapping each health card number to the respective PostTriage Patient.
	 */
	public Map<String, PostTriage> getPostTriagePatients() {
		return postTriagePatients;
	}
	
	/**
	 * Sets the Map of PostTriage Patients in this system to 
	 * postTriagePatients.
	 */
	public void setPostTriagePatients(Map<String, PostTriage> postTriagePatients){
		User.postTriagePatients = postTriagePatients;
	}
	
	/**
	 * Registers a new InTriage Patient into triage.
	 */
	public void recordInTriagePatient(InTriage patient){
		Node newPatient = new Node();
		newPatient.addData(patient);
		getInTriagePatients().insert(newPatient);
		
	}
	
	/**
	 * Resets the InTriagePatients in this system to a new, empty 
	 * MultiLinkedList.
	 */
	public void resetInTriagePatients(){
		inTriagePatients = new MultiLinkedList();
	
	}

}

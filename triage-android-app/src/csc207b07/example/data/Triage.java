package csc207b07.example.data;

import java.util.Map;

/**
 * Triage is an interface to be implemented by User with subinterfaces 
 * TriageNurse and TriagePhysician to be implemented by respective User 
 * subclasses Nurse and Physician.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public interface Triage {
	
	public MultiLinkedList getInTriagePatients();

	public Map<String, PostTriage> getPostTriagePatients();

	public void setPostTriagePatients(Map<String, PostTriage> postTriagePatients);

	public void recordInTriagePatient(InTriage patient);

	public void resetInTriagePatients();
	
}


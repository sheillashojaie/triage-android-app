package csc207b07.example.data;

import java.util.Date;
import java.util.List;

/**
 * TriageNurse is an interface to be implemented Nurse and is a subclass of the
 * Triage interface.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public interface TriageNurse extends Triage {

	public List<InTriage> getInTriagePatientsByArrivalTime();
	
	public List<InTriage> getInTriagePatientsByUrgency();
	
	public List<PostTriage> getPostTriagePatientsAsList();
	
	public void updateVitals(Date date, String healthCardNum, String temp, 
			String bloodPressure, String heartRate);	
	
}

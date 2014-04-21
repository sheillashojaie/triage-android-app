package csc207b07.example.data;

import java.util.Date;

/**
 * TriagePhysician is an interface to be implemented Physician and is a subclass of the
 * Triage interface.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public interface TriagePhysician extends Triage{

	public void recordPrescription(Date date, String healthCardNum, String name, String instructions);
	
}


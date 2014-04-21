package csc207b07.example.data;

import java.util.Date;

/**
 * A PostTriage is a subclass of Patient and represents a Patient who has left 
 * triage and has been admitted to a doctor.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class PostTriage extends Patient{

	/** A unique ID.*/
	private static final long serialVersionUID = -4273611256993306732L;
    
	/** The time admitted to a doctor. */
	private Date admittedTime;
	
	/**
	 * Constructs a PostTriage Patient with name name, date of birth dob, 
	 * health card number healthcardNum, and time admitted null.
	 * @param name The name of this PostTriage Patient.
	 * @param dob The date of birth of this PostTriage Patient.
	 * @param healthCardNum The health card number of this PostTriage Patient.
	 */
	public PostTriage(String name, String dob, String healthCardNum) {
		super(name, dob, healthCardNum);
		this.admittedTime = null;
	}
	
	/**
	 * Sets this PostTriage Patient's time admitted to a doctor to date date.
	 * @param date The time that this PostTriage Patient was admitted to a 
	 * doctor.
	 */
	public void setAdmittedTime(Date date) {
		admittedTime = date;
	}
	
	/**
	 * Returns the time that this PostTriage Patient was admitted to a doctor.
	 * @return The time that this PostTriage Patient was admitted to a doctor.
	 */
	public Date getAdmittedTime() {
		return admittedTime;
	}

}

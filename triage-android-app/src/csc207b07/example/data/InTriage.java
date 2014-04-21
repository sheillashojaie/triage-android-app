package csc207b07.example.data;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * An InTriage is a subclass of Patient and represents a Patient waiting 
 * in triage.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class InTriage extends Patient {
	
	/** A unique ID. */
	private static final long serialVersionUID = 657231311842109245L;

	/** The timestamp of the admittance of this InTriage Patient to triage. */
	private Date arrivalTime;
	
	/** The urgency of this InTriage Patient as determined by vital updates. */
	private int urgency;
	
	/** Whether this InTriage Patient is younger than two years old. */
	private boolean lessThanTwo;
	
	/**
	 * Constructs an InTriage Patient with name name, date of birth dob, and 
	 * health card number healthcardNum.
	 * @param name This InTriage Patient's name.
	 * @param dob This InTriage Patient's date of birth.
	 * @param healthCardNum This InTriage Patient's health card number.
	 * @see Patient
	 */
	public InTriage(String name, String dob,  String healthCardNum){
		super(name, dob, healthCardNum);
		this.arrivalTime = new Date();
		this.lessThanTwo = false; 
		
		// Determines whether this InTriage Patient is younger than two years 
		// old and sets this.lessThanTwo to reflect the calculations.
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int yearNow = cal.get(Calendar.YEAR); // The current year.
		int monthNow = cal.get(Calendar.MONTH) + 1; // The current month.
		int dayNow = cal.get(Calendar.DAY_OF_MONTH); // The current day.
		String dateOfBirth = this.getDob();
		String[] dobpiece;
		dobpiece = dateOfBirth.split("-");
		
		// The birth year parsed as an Integer.
		int yearBorn = Integer.parseInt(dobpiece[0]);
		
		// The birth month parsed as an Integer.
		int monthBorn = Integer.parseInt(dobpiece[1]);
		
		// The birth day parsed as an Integer.
		int dayBorn = Integer.parseInt(dobpiece[2]);
		
		// This InTriage Patient is younger than 2 years old.
		if (yearNow - yearBorn < 2) { 
			this.lessThanTwo = true;
			this.urgency = 1;
		}else if ((yearNow - yearBorn) == 2) {
			
			// This InTriage Patient is younger than 2 years old.
			if (monthNow < monthBorn) { 
				this.lessThanTwo = true;
				this.urgency = 1;
			}else if (monthNow == monthBorn) {
				
				// This InTriage Patient is younger than 2 years old.
				if (dayNow < dayBorn) { 
					this.lessThanTwo = true;
					this.urgency = 1;
					
				// This patient is 2 years old or older.	
				}else { 
					this.urgency = 0;
				}
				
			// This patient is 2 years old or older.	
			}else { 
				this.urgency = 0;
			}
			
		// This patient is 2 years old or older.	
		}else { 
			this.urgency = 0;
		}
	}
	
	/**
	 * Returns this InTriage Patient's time of arrival into triage.
	 * @return This InTriage Patient's time of arrival into triage.
	 */
	public Date getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Sets this InTriage Patient's time of arrival into triage to arrivalTime.
	 * @param arrivalTime The time of arrival to be set.
	 */
	public void setArrivalTime(Date arrivalTime){
		this.arrivalTime = arrivalTime;
	}
	
	/**
	 * Returns the Date object in the set dates whose value is closest in time to the current time.
	 * @param dates The set of dates to be searched for the most recent date.
	 * @return The most recent date which is contained in dates.
	 */
	public Date getNearestDate(Set<Date> dates) {
		  Date currentDate = new Date();
		  long minDiff = -1, currentTime = currentDate.getTime();		  
		  Date minDate = null;
		  for (Date date : dates) {
			  
			// The difference between the current time and the time in this 
			// date in dates.
		    long diff = Math.abs(currentTime - date.getTime()); 
		    
		    // If this is the first date being checked or the set is empty or this date is the most
		    // recent so far.
		    if ((minDiff == -1) || (diff < minDiff)) {
		      minDiff = diff;
		      minDate = date;
		    }
		  }
		  return minDate;
		}
	
	/**
	 * Sets this InTriage Patient's urgency point to the urgency calculated 
	 * with the data in this InTriage Patient's most recent vital record update.
	 * @see vitalRecord
	 */
	public void setUrgencyPoint() {
		
		// This InTriage Patient is younger than two years old.
		if (this.lessThanTwo){
			this.urgency = 1;
		}
		
		// This InTriage Patient is two years old or older.
		else{
			this.urgency = 0;
		}
		
		Map<Date, VitalRecord> Vitalsigns = this.getVitalRecord();
		
		// There exists at least one vital sign record.
		if (!Vitalsigns.isEmpty()){ 
			Set<Date> dates = Vitalsigns.keySet();
			
			// Checks the most recent record only.
			Date nearestDate = this.getNearestDate(dates); 
			VitalRecord record = Vitalsigns.get(nearestDate);
			
			// Calculates using data in this Intriage Patient's most recent
			// vital record update and sets the urgency point to this plus the
			// point determined in the constructor regarding age.
			urgency += record.calculateUrgency();
		}
	}
	
	/**
	 * Returns this InTriage Patient's urgency point.
	 * @return This InTriage Patient's urgency point.
	 */
	public int getUrgencyPoint() {
		return urgency;
	}
	
}


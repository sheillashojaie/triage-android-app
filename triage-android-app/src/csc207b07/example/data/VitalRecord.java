package csc207b07.example.data;

import java.io.Serializable;

/**
 * A VitalRecord is a single entry of a vital sign update made by a Nurse in 
 * regards to a particular Patient.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class VitalRecord implements Serializable {

	/** A unique ID. */
	private static final long serialVersionUID = 2195193906605925198L;

	/** The temperature of the Patient to which this VitalRecord is in 
	 regards. */
	private double temp;
	
	/** The heart rate of the Patient to which this VitalRecord is in 
	 regards. */
	private double heartRate;
	
	/** The blood pressure of the Patient to which this VitalRecord is 
	 in regards. */
	private double bloodPressure;
	
	
	public VitalRecord() {
		
	}
	/**
	 * Constructs a single entry of vital signs including the temperature temp, 
	 * blood pressure bloodPressure, heart rate heartRatein regards to a 
	 * particular Patient.
	 * @param temp The temperature to be recorded.
	 * @param bloodPressure The blood pressure to be recorded.
	 * @param heartRate The heart rate to be recorded.
	 */
	public VitalRecord(String temp, String bloodPressure, String heartRate){
		
		this.temp = Double.parseDouble(temp);
		this.heartRate = Double.parseDouble(heartRate);
		this.bloodPressure = Double.parseDouble(bloodPressure);
	}

	/**
	 * Returns the temperature recorded in this VitalRecord.
	 * @return The temperature recorded in this VitalRecord.
	 */
	public double getTemp() {
		return temp;
	}

	/**
	 * Returns the heart rate recorded in this VitalRecord.
	 * @return The heart rate recorded in this VitalRecord.
	 */
	public double getHeartRate() {
		return heartRate;
	}

	/**
	 * Returns the blood pressure recorded in this VitalRecord.
	 * @return The blood pressure recorded in this VitalRecord.
	 */
	public double getBloodPressure() {
		return bloodPressure;
	}
	
	/**
	 * Returns a string representation of this VitalRecord.
	 */
	public String toString() {
		return "_" + temp + "_" + bloodPressure + "_" + heartRate  ;
	}
	
	/**
	 * Calculates the urgency of the InTriage Patient to whom this VitalRecord 
	 * is in regards, using the temperature, blood pressure, and heart rate.
	 * @return The calculated urgency.
	 */
	public int calculateUrgency() {
		int urgency = 0;
		
		// In degrees Celsius.
		if (temp >= 39) {
			urgency++;
		}
		
		// Systolic
		if (bloodPressure >= 140) {
			urgency++;
		}
		
		if (heartRate >= 100 || heartRate <= 50) {
			urgency++;
		}
		return urgency;
	}

}

package csc207b07.example.data;

import java.io.Serializable;

/**
 * Prescription is a single prescription entry of a Patient's prescription 
 * records. It contains a medication name and instructions.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class Prescription implements Serializable {
	
	/** A unique ID.*/
	private static final long serialVersionUID = -6926036979879201533L;
	
	/** The name of the prescribed medication. */
	private String nameForMedication;
	
	/** The instructions for the prescribed medication. */
	private String instructions;
	
	/** Constructs a Prescription with medication name name, and instructions
	 *  instructions.
	 * @param name The name of the prescribed medication.
	 * @param instructions The instructions for the prescribed medication.
	 */
	public Prescription(String name, String instructions){
		this.nameForMedication = name;
		this.instructions = instructions;
	}

	/**
	 * Returns the name of the prescribed medication of this Prescription.
	 * @return The name of the prescribed medication of this Prescription.
	 */
	public String getNameForMedication() {
		return nameForMedication;
	}

	/**
	 * Sets the name of the prescribed medication of this Prescription to name 
	 * nameForMedication.
	 * @param nameForMedication The name of the prescribed medication of this 
	 * Prescription to be set.
	 */
	public void setNameForMedication(String nameForMedication) {
		this.nameForMedication = nameForMedication;
	}

	/**
	 * Returns the instructions for the prescribed medication of this 
	 * Prescription.
	 * @return The instructions for the prescribed medication of this 
	 * Prescription.
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * Sets the instructions for the prescribed medication of this 
	 * Prescription to instructions instructions.
	 * @param instructions The instructions for the prescribed medication
	 * of this Prescription to be set.
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
	/**
	 * Returns a string representation of this Prescription.
	 */
	public String toString(){
		return "_" + nameForMedication + "_" + instructions;
	}

}

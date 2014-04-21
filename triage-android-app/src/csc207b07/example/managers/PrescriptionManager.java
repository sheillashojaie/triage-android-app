package csc207b07.example.managers;

import android.annotation.SuppressLint;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import csc207b07.example.data.Prescription;

/** A manager of Prescriptions.
* @author c2phelan
* @author g3s
* @author c3zhanhk
* @author g3soul
* @author c2zhoush
*/
public class PrescriptionManager {

	/** A Map of Prescriptions*/
	private Map<Date, Prescription> records;

	/**
	 * Constructs a new PrescriptionManager that manages a collection of Prescriptions.
	 * stored in directory dir and file fileName.
	 * @param dir The Directory where the data file is stored.
	 * @param fileName The name of the data file.
	 * @throws IOException 
	 * @throws ParseException
	 */
	public PrescriptionManager(File dir, String fileName) throws IOException, 
		ParseException{

		this.records = new HashMap<Date, Prescription>();
		//stores information from the data file into the records Map
		File file = new File(dir, fileName);
		if (file.exists()) {
			this.readFromFile(file.getPath());
		} else {
			file.createNewFile();
		}
	}

	/**
	 * Adds a VitalRecord to this RecordManager.
	 * @param date The date which the VitalRecord was made.
	 * @param record The VitalRecord itself.
	 */
	public void put(Date date , Prescription record) {
		records.put(date, record);
	}

	/**
	 * Returns VitalRecords managed by this RecordManager.
	 * @return A Map of the VitalRecords.
	 */
	public Map<Date, Prescription> getRecords() {
		return records;
	}


	/**
	 * Saves records to the data file. 
	 * @param outputStream The out put stream to write the data file to.
	 */
	public void saveToFile(FileOutputStream outputStream){
		try{
			for (Date i : records.keySet()){
				outputStream.write((i.toString() + records.get(i).toString() + 
						"\n").getBytes());
			}
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * Stores information to records from the file at path filePath.
	 * @param filePath The filepath in which the data is retrieved.
	 * @throws FileNotFoundException.
	 * @throws ParseException.
	 */
	@SuppressLint("SimpleDateFormat")
	public void readFromFile(String filePath) throws FileNotFoundException, 
	ParseException {
		Scanner scanner = new Scanner(new FileInputStream(filePath));
		String[] record;

		while(scanner.hasNextLine()) {
			record = scanner.nextLine().split("_"); 

			//converts string to date of specified format.
			SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm" +
					":ss zzz yyyy", Locale.ENGLISH);

			Date date = format.parse(record[0]);
			String prescriptionName = record[1];
			String Instructions = record[2];

			//constructs a new Prescription based on information read from line.
			Prescription newPrescription = new Prescription(prescriptionName, Instructions);
			records.put(date, newPrescription);
		}
		scanner.close();
	}
}
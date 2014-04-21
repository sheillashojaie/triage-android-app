package csc207b07.example.triage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import csc207b07.example.data.Patient;
import csc207b07.example.data.Physician;
import csc207b07.example.data.Prescription;
import csc207b07.example.managers.PrescriptionManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 * PrescriptionActivity displays a form for filling in a new prescription
 * for a patient.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class PrescriptionActivity extends Activity {
	
	/** The current user of this Triage application.*/
	private Physician user;
	
	/** The patient who is being prescribed. */
	private Patient patient;
	
	/** The prescription manager of the patient who is being prescribed. */
	private PrescriptionManager manager;
	
	/** The health card number of the patient who is being prescribed. */
	private String healthCardNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prescription);
		Intent intent = getIntent();
		patient = (Patient) intent .getSerializableExtra("patientKey");
		user = (Physician) intent.getSerializableExtra("user");
		healthCardNum = patient.getHealthCardNum();
		try {
			manager = new PrescriptionManager(this.getApplicationContext().getFilesDir(), healthCardNum + "Prescription.txt");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.prescription, menu);
		return true;
	}
	
	/**
	 * Registers a new prescription into the prescription records of patient.
	 * @param view
	 */
	public void updatePrescription(View view){
		EditText editText1 = (EditText) findViewById(R.id.medication_field);
    	String name = editText1.getText().toString();
    	EditText editText2 = (EditText) findViewById(R.id.instructions_field);
    	String instructions = editText2.getText().toString();
    	Intent intent1 = new Intent(this, PrescriptionErrorActivity.class);
    	Intent intent2 = new Intent(this, UpdatePrescriptionSuccessActivity.class);
    	
    	// One or more text fields are left blank.
    	if ((name.equals(""))||(instructions.equals(""))){
    		intent1.putExtra("user", user);
    		intent1.putExtra("patientKey", patient);
    		startActivity(intent1);
    	}
    	
    	// The text fields are filled in.
    	else{
    		Date date = new Date();
    		Prescription prescription = new Prescription(name, instructions);
    		user.recordPrescription(date, healthCardNum, name, instructions);
    		manager.put(date, prescription);
    		FileOutputStream outputStream;        
	    	try {
			outputStream = openFileOutput(healthCardNum + "Prescription.txt", MODE_PRIVATE);
			manager.saveToFile(outputStream);
	    	} catch (FileNotFoundException e) {    				
			e.printStackTrace();
	    	}  
	    intent2.putExtra("user", user);
	    intent2.putExtra("timeKey", date);
	    intent2.putExtra("cardKey", healthCardNum);
	    startActivity(intent2);
	}
	}
	
	/**
	 * Returns to the previous page, InTriagePhysicianActivity.
	 * @param view
	 */
	public void returnToPrevious(View view){

		Intent intent = new Intent(this, InTriagePhysicianActivity.class);
		intent.putExtra("user", user);
		intent.putExtra("patientKey", patient);
		startActivity(intent);
		}
	
	/**
	 * Returns to the main menu, MainMenuPhysicianActivity.
	 * @param view
	 */
	public void returnToMainMenu(View view){
		Intent intent = new Intent(this, MainMenuPhysicianActivity.class);
		intent.putExtra("user", user);
		startActivity(intent);
	}

}

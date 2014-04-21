package csc207b07.example.triage;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import csc207b07.example.data.InTriage;
import csc207b07.example.data.Nurse;
import csc207b07.example.data.Patient;
import csc207b07.example.data.Prescription;
import csc207b07.example.data.User;
import csc207b07.example.managers.PrescriptionManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * DisplayPrescriptionActivity displays the prescription records of a patient.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class DisplayPrescriptionActivity extends Activity {
	
	/** The patient whose prescription records are to be displayed.*/
	private Patient patient;
	
	/** The current user of this Triage application.*/
	private User user;
	
	/** The prescription manager for this patient's prescription records. */
	private PrescriptionManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_prescription);
		Intent intent = getIntent();
		patient = (Patient)intent.getSerializableExtra("patientKey");
		user = (User)intent.getSerializableExtra("user");
		String cardNum = patient.getHealthCardNum();
		try {
            manager = new PrescriptionManager(
                    this.getApplicationContext().getFilesDir(), 
                    cardNum + "Prescription.txt" );    
		}  catch (ParseException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}
		TextView Patient 
		= (TextView) findViewById(R.id.intriage_patient);
		String patientRecord = patient.getName() + 
				"(name for medication, instruction)"+ "\n";
	    Map<Date, Prescription> records = manager.getRecords();
		if(!records.isEmpty()) {
			String x = "";
			for (Date i : records.keySet()){
				String[] recordPiece = records.get(i).toString().split("_");
				x += i.toString() + "\n" + recordPiece[1] + "," + 
				    recordPiece[2] +"\n";
			}
			Patient.setText(patientRecord + x);
		}else {
			Patient.setText(patientRecord);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_prescription, menu);
		return true;
	}
	
	/**
	 * Returns to the previous activity- if the User is Nurse: InTriageActivity
	 * if the patient is InTriage, or PostTriageActivity if the patient is 
	 * PostTriage. If the User is Physician: InTriagePhysicianActivity if the
	 * patient is InTriage, or PostTriagePhysicianActivity if the patient is
	 * PostTriage.
	 * @param view
	 */
	public void returnToPrevious(View view){
		if (user instanceof Nurse) {
			if (patient instanceof InTriage){
				Intent intent = new Intent(this, InTriageActivity.class);
				intent.putExtra("intriageKey", patient);
				intent.putExtra("user", user);
				startActivity(intent);
			}
			else{
				Intent intent = new Intent(this, PostTriageActivity.class);
				intent.putExtra("posttriageKey", patient);
				intent.putExtra("user", user);
				startActivity(intent);
			}
		}
		else {
			if (patient instanceof InTriage){
				Intent intent = new Intent(this, InTriagePhysicianActivity.class);
				intent.putExtra("patientKey", patient);
				intent.putExtra("user", user);
				startActivity(intent);
			}
			else{
				Intent intent2 = new Intent(this, PostTriagePhysicianActivity.class);
				intent2.putExtra("patientKey", patient);
				intent2.putExtra("user", user);
				startActivity(intent2);
			}
		}
	}
	
	/**
	 * Returns to the main menu- MainMenuActivity if the user is Nurse, or
	 * MainMenuPhysicianActivity if the user is Physician.
	 * @param view
	 */
	public void returnToMainMenu(View view){
		if (user instanceof Nurse) {
			Intent intent = new Intent(this, MainMenuActivity.class);
			intent.putExtra("user", user);
			startActivity(intent);
		}
		else {
			Intent intent2 = new Intent(this, MainMenuPhysicianActivity.class);
			intent2.putExtra("user", user);
			startActivity(intent2);
		}
	}

}

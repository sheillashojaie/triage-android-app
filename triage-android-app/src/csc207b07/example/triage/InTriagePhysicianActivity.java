package csc207b07.example.triage;

import csc207b07.example.data.InTriage;
import csc207b07.example.data.Physician;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * InTriagePhysicianActivity is the profile of an InTriage Patient patient when
 * the user is a Physician, where vitals and prescriptions can be displayed.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class InTriagePhysicianActivity extends Activity {
	
	/** The patient whose profile is to be displayed.*/
	private InTriage patient;
	
	/** The current user of this Triage application.*/
	private Physician user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_in_triage_physician);
		Intent intent1 = getIntent();
		patient 
		= (InTriage) intent1.getSerializableExtra("patientKey");
		user = (Physician) intent1.getSerializableExtra("user");
		TextView inTriagePatient 
		= (TextView) findViewById(R.id.intriage_patient);
		String patientInfo = patient.toString(); //+ "\n" + "Arrival Time: " 
		// + patient.getArrivalTime().toString();
		inTriagePatient.setText(patientInfo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.in_triage_physician, menu);
		return true;
	}

	/**
	 * Starts DisplayVitalRecordActivity where the vital update records of
	 * patient are displayed.
	 * @param view
	 */
	public void displayVitalRecord(View view) {
		Intent intent = new Intent(this, DisplayVitalRecordActivity.class);
		intent.putExtra("user", user);
		intent.putExtra("patientKey", patient);
		startActivity(intent);
	}
	
	/**
	 * Starts DisplayPrescriptionActivity where the prescription records of
	 * patient are displayed.
	 * @param view
	 */
	public void displayPrescription(View view) {
		Intent intent = new Intent(this, DisplayPrescriptionActivity.class);
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
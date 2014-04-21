package csc207b07.example.triage;

import csc207b07.example.data.Physician;
import csc207b07.example.data.PostTriage;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * PostTriagePhysicianActivity is the profile of a PostTriage Patient when the
 * user is a Nurse, where ecords of prescriptions and vital updates of the
 * patient can be displayed, and a new prescription can be recorded.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class PostTriagePhysicianActivity extends Activity {

	/** The current user of this Triage application.*/
	private Physician user;
	
	/** The patient whose profile is to be displayed.*/
	private PostTriage patient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_triage_physician);
		Intent intent2 = getIntent();
		patient 
		= (PostTriage) intent2.getSerializableExtra("patientKey");
		user = (Physician) intent2.getSerializableExtra("user");
		TextView postTriagePatient 
		= (TextView) findViewById(R.id.posttriage_patient);
		String patientInfo = patient.toString();
		postTriagePatient.setText(patientInfo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.post_triage_physician, menu);
		return true;
	}
	
	/**
	 * Starts PrescriptionActivity where a prescription for patient
	 * can be recorded.
	 * @param view
	 */
	public void updatePrescription(View view){
		Intent intent = new Intent(this, PrescriptionActivity.class)	;
		intent.putExtra("user", user);
		intent.putExtra("patientKey", patient);
		startActivity(intent);
	}
	
	/**
	 * Starts DisplayPrescriptionActivity where the prescription records
	 * of patient are displayed.
	 * @param view
	 */
	public void displayPrescription(View view) {
		Intent intent = new Intent(this, DisplayPrescriptionActivity.class);
		intent.putExtra("user", user);
		intent.putExtra("patientKey", patient);
		startActivity(intent);
	}
	
	/**
	 * Starts DisplayVitalRecordActivity where the vital update records
	 * of patient are displayed.
	 * @param view
	 */
	public void displayVitalRecord(View view) {
		Intent intent = new Intent(this, DisplayVitalRecordActivity.class);
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


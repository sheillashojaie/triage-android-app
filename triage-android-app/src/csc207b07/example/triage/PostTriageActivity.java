package csc207b07.example.triage;

import csc207b07.example.data.Nurse;
import csc207b07.example.data.PostTriage;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * PostTriageActivity is the profile of a PostTriage Patient when the user is a
 * Nurse, where records of prescriptions and vital updates can be displayed.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class PostTriageActivity extends Activity {

	/** The current user of this Triage application.*/
	private Nurse user;
	
	/** The patient whose profile is to be displayed.*/
	private PostTriage patient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_triage);
		Intent intent2 = getIntent();
		patient 
		= (PostTriage) intent2.getSerializableExtra("postTriageKey");
		user = (Nurse) intent2.getSerializableExtra("user");
		TextView postTriagePatient 
		= (TextView) findViewById(R.id.posttriage_patient);
		String patientInfo = patient.toString();
		postTriagePatient.setText("This patient is admitted to doctor \n" + patientInfo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.post_triage, menu);
		return true;
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
	 * Returns to the main menu, MainMenuActivity.
	 * @param view
	 */
	public void returnToMainMenu(View view){
			Intent intent = new Intent(this, MainMenuActivity.class);
			intent.putExtra("user", user);
			startActivity(intent);
	}
}


package csc207b07.example.triage;

import csc207b07.example.data.Patient;
import csc207b07.example.data.Physician;
import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

/**
 * PrescriptionErrorActivity is the page that is started when one or more
 * prescription fields are left blank. As such, it simply displays an error 
 * message.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class PrescriptionErrorActivity extends Activity {

	/** The patient to whom the user was trying to prescribe. */
	private Patient patient;
	
	/** The current user of this Triage application. */
	private Physician user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prescription_error);
		Intent intent = getIntent();
		patient 
		= (Patient) intent.getSerializableExtra("patientKey");
		user 
		= (Physician) intent.getSerializableExtra("user");
		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		// Vibrate for 500 milliseconds
		v.vibrate(500);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.prescription_error, menu);
		return true;
	}
	
	/**
	 * Returns to the previous page, PrescriptionActivity.
	 * @param view
	 */
	public void returnToPrevious(View view){
		Intent intent = new Intent(this, PrescriptionActivity.class);
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

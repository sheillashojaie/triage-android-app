package csc207b07.example.triage;

import csc207b07.example.data.InTriage;
import csc207b07.example.data.Physician;
import csc207b07.example.data.PostTriage;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 * MainMenuPhysicianActivity is the main menu when the user is a Physician. Patients
 * can be looked up from this page and the user can log out of the application.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class MainMenuPhysicianActivity extends Activity {
	
	/** The current user of this Triage application.*/
	private Physician user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu_physician);
		Intent intent2 = getIntent();
		user = (Physician) intent2.getSerializableExtra("user");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu_physician, menu);
		return true;
	}
	
	/**
	 * Searches for a specified patient, including InTriage and PostTriage,
	 * in the whole system. Starts InTriagePhysicianActivity if the patient 
	 * is found and is in triage, starts PostTriagePhysicianActivity if the 
	 * patient is found and is post-triage, or starts NotFoundActivity if 
	 * the patient is not found. 
	 * @param view
	 */
	public void searchPatient(View view){
		Intent intent1 = new Intent(this, InTriagePhysicianActivity.class);
		Intent intent2 = new Intent(this, NotFoundActivity.class);
		Intent intent3 = new Intent(this, PostTriagePhysicianActivity.class);
		EditText healthCardNum 
		= (EditText) findViewById(R.id.cardnumber_field);
	 	String healthCardNumber = healthCardNum.getText().toString();
	 	
	 	// If the patient exists in the system and is in triage.
	 	if (user.getInTriagePatients().contains(healthCardNumber)){
	 		InTriage patient 
	 		= user.getInTriagePatients().getPatientByCardNum(healthCardNumber);
	 		intent1.putExtra("patientKey", patient);
	 		intent1.putExtra("user", user);
	 	 	startActivity(intent1); 
	 	}
	 	// If the patient exists in the system and is post-triage.
	 	else if(user.getPostTriagePatients().containsKey(healthCardNumber)){
	 		PostTriage patient 
	 		= user.getPostTriagePatients().get(healthCardNumber);
	 		intent3.putExtra("patientKey", patient);
	 		intent3.putExtra("user", user);
	 		startActivity(intent3);
	 	}
	 	
	 	// If the patient does not exist in the system.
	 	else{
	 		intent2.putExtra("user", user);
	 		startActivity(intent2);
	 	}
	}
	
	/**
	 * Logs out the user by returning to the login page, MainActivity.
	 * @param view
	 */
	public void logOut(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}

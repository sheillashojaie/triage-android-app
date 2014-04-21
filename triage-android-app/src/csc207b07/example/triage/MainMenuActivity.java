package csc207b07.example.triage;

import csc207b07.example.data.InTriage;
import csc207b07.example.data.Nurse;
import csc207b07.example.data.PostTriage;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;


/**
 * MainMenuActivity is the main menu when the user is a Nurse. Patients
 * can be looked up and registered. A list of InTriage Patients can
 * be displayed by order of earliest to latest arrival or by order of most to
 * least urgent. Also, a list of PostTriage Patients can be displayed, and the
 * user can log out of the application.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class MainMenuActivity extends Activity {

	/** The current user of this Triage application.*/
	private Nurse user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		Intent intent2 = getIntent();
		user = (Nurse) intent2.getSerializableExtra("user");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	/**
	 * Searches for a specified patient, including InTriage and PostTriage,
	 * in the whole system. Starts InTriageActivity if the patient is found
	 * and is in triage, starts PostTriageActivity if the patient is found 
	 * and is post-triage, or starts NotFoundActivity if the patient is not
	 * found. 
	 * @param view
	 */
	public void searchPatient(View view){
		Intent intent1 = new Intent(this, InTriageActivity.class);
		Intent intent2 = new Intent(this, PostTriageActivity.class);
		Intent intent3 = new Intent(this, NotFoundActivity.class);
		EditText healthCardNum 
		= (EditText) findViewById(R.id.cardnumber_field);
	 	String healthCardNumber = healthCardNum.getText().toString();
	 	
	 	// If the patient exists in the system and is in triage.
	 	if (user.getInTriagePatients().contains(healthCardNumber)){
	 		InTriage patient 
	 		= user.getInTriagePatients().getPatientByCardNum(healthCardNumber);
	 		intent1.putExtra("intriageKey", patient);
	 		intent1.putExtra("user", user);
	 	 	startActivity(intent1); 
	 	}
	 	
	 	// If the patient exists in the system and is post-triage.
	 	else if(user.getPostTriagePatients().containsKey(healthCardNumber)){
	 		PostTriage patient 
	 		= user.getPostTriagePatients().get(healthCardNumber);
	 		intent2.putExtra("postTriageKey", patient);
	 		intent2.putExtra("user", user);
	 		startActivity(intent2);
	 	}
	 	
	 	// If the patient does not exist in the system
	 	else{
	 		intent3.putExtra("user",  user);
	 		startActivity(intent3);
	 	}
	}
	
	/**
	 * Starts AddPatientActivity where a new patient can be registered.
	 * @param view
	 */
	public void addPatient(View view){
		Intent intent = new Intent(this, AddPatientActivity.class);
		intent.putExtra("user", user);
		startActivity(intent);
	
	}
	
	/** 
	 * Starts DisplayInTriageActivity where a list of the current triage
	 * patients is displayed by order of earliest to latest arrival.
	 * @param view
	 */
	public void displayIntriage(View view){
		Intent intent = new Intent(this, DisplayIntriageActivity.class);
		intent.putExtra("user", user);
		startActivity(intent);
	}
	
	/** 
	 * Starts DisplayPostTriageActivity where a list of the post-triage
	 * patients is displayed.
	 * @param view
	 */
	public void displayPostTriage(View view){
		Intent intent = new Intent(this, DisplayPostTriageActivity.class);
		intent.putExtra("user", user);
		startActivity(intent);
	}
	
	/** 
	 * Starts DisplayUrgencyActivity where a list of the current triage
	 * patients is displayed by order of most to least urgent.
	 * @param view
	 */
	public void displayUrgency(View view) {
		Intent intent = new Intent(this, DisplayUrgencyActivity.class);
		intent.putExtra("user", user);
		startActivity(intent);
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


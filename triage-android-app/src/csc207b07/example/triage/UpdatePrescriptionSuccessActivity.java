package csc207b07.example.triage;

import java.util.Date;
import csc207b07.example.data.Patient;
import csc207b07.example.data.Physician;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * UpdatePrescriptionSuccessActivity is started when a prescription is updated
 * successfully. As such, it simply displays a message that the update was 
 * successful.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class UpdatePrescriptionSuccessActivity extends Activity {
	
	private Physician user;
	
	private Patient patient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_prescription_success);
		Intent intent = getIntent();
		Date date = (Date) intent.getSerializableExtra("timeKey");
		String cardNum = (String) intent.getSerializableExtra("cardKey");
		user = (Physician) intent.getSerializableExtra("user");
	 	if (user.getInTriagePatients().contains(cardNum)){
	 		patient = user.getInTriagePatients().getPatientByCardNum(cardNum);
	 	}
	 	else if(user.getPostTriagePatients().containsKey(cardNum)){
	 		patient 
	 		= user.getPostTriagePatients().get(cardNum);
	 	}
		
		/*Map<Date, Prescription> p = patient.getPrescription();
		String prescriptionlist = "";
		String[] prescriptionpiece;
		for (Date i : p.keySet()) {
			prescriptionpiece = p.get(i).toString().split("_");
			prescriptionlist += prescriptionpiece[1] + "            " + prescriptionpiece[2] + "\n";
		}*/
		TextView welcomeUser = (TextView) findViewById(R.id.update_prescription_success);
		welcomeUser.setText("Prescriptions are added successfully at time:" 
		+ "\n" + date.toString() + "\n");
		
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_prescription_success, menu);
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

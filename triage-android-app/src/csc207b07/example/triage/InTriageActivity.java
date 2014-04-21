package csc207b07.example.triage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import csc207b07.example.data.InTriage;
import csc207b07.example.data.Nurse;
import csc207b07.example.data.PostTriage;
import csc207b07.example.data.VitalRecord;
import csc207b07.example.managers.InTriageManager;
import csc207b07.example.managers.PostTriageManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * InTriageActivity is the profile of an InTriage Patient patient when the user
 * is a Nurse, where vitals can be updated, vitals and prescriptions can be 
 * displayed, and patient can be admitted to a doctor.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class InTriageActivity extends Activity {
	
	
	private InTriageManager inmanager;
	
	private PostTriageManager pomanager;
	
	/** The current user of this Triage application.*/
	private Nurse user;
	
	/** The patient whose profile is to be displayed.*/
	private InTriage patient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_in_triage);
		Intent intent1 = getIntent();
		patient 
		= (InTriage) intent1.getSerializableExtra("intriageKey");
		user = (Nurse) intent1.getSerializableExtra("user");
		TextView inTriagePatient 
		= (TextView) findViewById(R.id.intriage_patient);
		String patientInfo = patient.toString() + "\n" + "Arrival Time: " 
		 + patient.getArrivalTime().toString();
		inTriagePatient.setText(patientInfo);
		try {
			inmanager = new InTriageManager(
					this.getApplicationContext().getFilesDir(),
					"inTriagePatients.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		try {
			pomanager = new PostTriageManager(
					this.getApplicationContext().getFilesDir(),
					"postTriagePatients.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.in_triage, menu);
		return true;
	}
	
	/**
	 * Starts RecordsActivity where the vitals of patient can be updated.
	 * @param view
	 */
	public void update(View view){
		Intent intent = new Intent(this, RecordsActivity.class)	;
		intent.putExtra("user", user);
		intent.putExtra("intriageKey",patient);
		startActivity(intent);
	}
	
	/**
	 * Admits patient to a doctor, sets their time admitted, and converts them
	 * into a PostTriage Patient.
	 * @param view
	 */
	public void seenByDoctor(View view) {
		Intent intent = new Intent(this, SeenByDoctorActivity.class);
		String name = patient.getName(), dob = patient.getDob(), healthCardNum = patient.getHealthCardNum();
		Map<Date, VitalRecord> vitalRecord = patient.getVitalRecord();
		PostTriage patient2 = new PostTriage(name, dob, healthCardNum);
		patient2.setVitalRecord(vitalRecord);
		Date date = new Date();
		patient2.setAdmittedTime(date);
		pomanager.put(patient2, healthCardNum);
		FileOutputStream outputStream;
		try {
				outputStream = openFileOutput("postTriagePatients.txt",
						MODE_PRIVATE);
				pomanager.saveToFile(outputStream);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		inmanager.remove(patient);
		try {
				outputStream = openFileOutput("inTriagePatients.txt",
						MODE_PRIVATE);
				inmanager.saveToFile(outputStream);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		user.getInTriagePatients().remove(healthCardNum);		
		user.getPostTriagePatients().put(healthCardNum, patient2);
		intent.putExtra("user", user);
		intent.putExtra("postTriageKey", patient2);
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
	
	/**
	 * Starts DisplayVitalRecordActivity where the vital records of patient
	 * are displayed.
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

}


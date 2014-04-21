package csc207b07.example.triage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;

import csc207b07.example.data.InTriage;
import csc207b07.example.data.Node;
import csc207b07.example.data.Nurse;
import csc207b07.example.data.PostTriage;
import csc207b07.example.managers.InTriageManager;
import csc207b07.example.managers.PostTriageManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 * AddPatientActivity displays a form for filling out personal information on
 * the patient to be registered into triage.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class AddPatientActivity extends Activity {
	
	/** The manager for all of the InTriage Patients of this system. */
	private InTriageManager inmanager;
	
	/** The manager for all of the PostTriage Patients of this system. */
	private PostTriageManager pomanager;
	
	/** The current user of this Triage application. */
	private Nurse user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_patient);
		Intent intent = getIntent();
		user = (Nurse)intent.getSerializableExtra("user");
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
		getMenuInflater().inflate(R.menu.add_patient, menu);
		return true;
	}
	
	/**
	 * Registers a new InTriage Patient into the triage system. Starts 
	 * AddErrorActivity if the input fields are left blank, date of birth 
	 * is not numeric, or health card number already belongs to an InTriage
	 * Patient. However, if the health card number already belongs to a
	 * PostTriage Patient, converts that PostTriage Patient into a new
	 * InTriage Patient. Otherwise, registers a new InTriage Patient. 
	 * In the latter 2 cases, starts AddSuccessActivity.
	 * @param view
	 */
	public void startAddPatient(View view){
		Intent intent1 = new Intent(this, AddErrorActivity.class);
		Intent intent2 = new Intent(this, AddSuccessActivity.class);
	 	EditText nameText = (EditText) findViewById(R.id.name_field);
	 	String name = nameText.getText().toString();
	 	EditText yearText = (EditText) findViewById(R.id.year_field);
	 	String year = yearText.getText().toString();
	 	EditText monthText = (EditText) findViewById(R.id.month_field);
	 	String month = monthText.getText().toString();
	 	EditText dayText = (EditText) findViewById(R.id.day_field);
	 	String day = dayText.getText().toString();
	 	String dob = year + "-" + month + "-" + day;
	 	EditText healthCardNumText 
	 	= (EditText) findViewById(R.id.healthCardNum_field);
	 	String healthCardNum = healthCardNumText.getText().toString();
	 	intent1.putExtra("user", user);
	 	intent2.putExtra("user", user);
	 	//Goes to the error page when any string is empty.
	 	if (name.equals("") || year.equals("") || month.equals("") || 
	 			day.equals("") || healthCardNum.equals("")) {
	 		startActivity(intent1);
	 	}
	 	else {
	 		try {
	 			//Tries whether the strings are transferable to integer.
		 		Integer.parseInt(year);
		 		Integer.parseInt(month);
		 		Integer.parseInt(day);
		 		//If the patient is in postTriage, remove it and add it to inTriage.
		 		if (user.getPostTriagePatients().containsKey(healthCardNum)) {
		 			InTriage newPatient = new InTriage(name, dob, healthCardNum);
		 			PostTriage postPatient = user.getPostTriagePatients().get(healthCardNum);
		 			newPatient.setVitalRecord(postPatient.getVitalRecord());
		 			//newPatient.setUrgencyPoint();
		 			inmanager.add(newPatient);
		 			FileOutputStream outputStream;
		 			try {
		 				outputStream = openFileOutput("inTriagePatients.txt",
		 						MODE_PRIVATE);
		 				inmanager.saveToFile(outputStream);
		 			} catch (FileNotFoundException e) {
		 				// TODO Auto-generated catch block
		 				e.printStackTrace();
		 			}
		 			pomanager.remove(postPatient);
		 			try {
		 				outputStream = openFileOutput("postTriagePatients.txt",
		 						MODE_PRIVATE);
		 				pomanager.saveToFile(outputStream);
		 			} catch (FileNotFoundException e) {
		 				// TODO Auto-generated catch block
		 				e.printStackTrace();
		 			}	        
		 			//postPatient = null;
		 			Node node = new Node();
		 			node.addData(newPatient);
		 			user.getInTriagePatients().insert(node);
		 			user.getPostTriagePatients().remove(healthCardNum);
		 			startActivity(intent2);
		 			}
		 		//Goes to error page if the patient is already in inTriage.
		 		else if(user.getInTriagePatients().getPatientByCardNum(healthCardNum) 
		 				!= null) {
		 			startActivity(intent1);
		 		}
		 		//Otherwise create a new inTriage patient.
		 		else {
		 			InTriage patient = new InTriage(name, dob, healthCardNum);
		 			user.recordInTriagePatient(patient);
		 			inmanager.add(patient);
		 			FileOutputStream outputStream;        
		 			try {
		 				outputStream = openFileOutput("inTriagePatients.txt",
		 						MODE_PRIVATE);
		 				inmanager.saveToFile(outputStream);
		 			} catch (FileNotFoundException e) {
		 				// TODO Auto-generated catch block
		 				e.printStackTrace();
		 			}	        
		 			startActivity(intent2);
		 		}
		 	}
	 		catch (NumberFormatException nfe) {
    			startActivity(intent1);
	 		}
	  }
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


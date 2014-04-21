package csc207b07.example.triage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import csc207b07.example.data.InTriage;
import csc207b07.example.data.Node;
import csc207b07.example.data.Nurse;
import csc207b07.example.data.VitalRecord;
import csc207b07.example.managers.RecordManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 * RecordsActivity displays a form for filling in a new vital update.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class RecordsActivity extends Activity {
	
	/** A record manager for the patient whose vitals are being updated.*/
	private RecordManager manager;
	
	/** The patient whose vitals are being updated. */
	private InTriage patient;
	
	/** The current user of this Triage application. */
	private Nurse user;
	
	/** The health card number of the patient whose vitals are being updated. */
	private String cardNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_records);
		Intent intent1 = getIntent();
    	patient = (InTriage) intent1.getSerializableExtra("intriageKey");
    	user = (Nurse) intent1.getSerializableExtra("user");
    	cardNum = patient.getHealthCardNum();
		try {
            manager = new RecordManager(
                    this.getApplicationContext().getFilesDir(), cardNum 
                    + ".txt" );    
		}  catch (ParseException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.records, menu);
		return true;
	}
	
	/**
	 * Sets a new vital update in patient's record of vital updates. If
	 * the fields are valid, starts UpdateSuccessActivity. Otherwise, starts
	 * UpdateErrorActivity.
	 * @param view
	 */
	public void updateVitalSigns(View view) {
		EditText editText1 = (EditText) findViewById(R.id.temp_field);
    	String temp = editText1.getText().toString();
    	EditText editText2 = (EditText) findViewById(R.id.bloodpressure_field);
    	String bloodpressure = editText2.getText().toString();
    	EditText editText3 = (EditText) findViewById(R.id.heartrate_field);
    	String heartrate = editText3.getText().toString();
    	Intent intent2 = new Intent(this, UpdateErrorActivity.class);
    	intent2.putExtra("user", user);
    	intent2.putExtra("intriageKey", patient);
    	
    	// One or more text fields are left blank.
    	if (temp.equals("") || bloodpressure.equals("") 
    			|| heartrate.equals("")) {
	 		startActivity(intent2);
    	}
    	
    	// The text fields are filled in.
    	else {
    		//Tries whether the strings are transferable to double.
    		try {
    			Double.parseDouble(temp);
    			Double.parseDouble(bloodpressure);
    			Double.parseDouble(heartrate);
    			//Creates a new vital record.
    			VitalRecord record = new VitalRecord(temp, bloodpressure, 
    	    			heartrate);
    			Date newdate = new Date();
    	    	patient.setVitalSigns(newdate, record);
    	    	user.getInTriagePatients().remove(cardNum);
    	    	manager.put(newdate, record);
    	    	patient.setUrgencyPoint();
    	    	Node node = new Node();
    	    	node.addData(patient);
    	    	user.getInTriagePatients().insert(node);
    	    	FileOutputStream outputStream;  
    	    	//Save the data to file.
    	    	try {
    			outputStream = openFileOutput(cardNum + ".txt", MODE_PRIVATE);
    			manager.saveToFile(outputStream);
    	    	} catch (FileNotFoundException e) {    				
    			e.printStackTrace();
    	    	}
    	    	//Passes instances to success page.
    	    	Intent intent = new Intent(this, UpdateSuccessActivity.class);
    	    	intent.putExtra("timeKey", newdate);
    	    	intent.putExtra("cardKey", cardNum);
    	    	intent.putExtra("intriageKey", patient);
    	    	intent.putExtra("user", user);
    	    	startActivity(intent);   
    		}
    		catch (NumberFormatException nfe) {
    			startActivity(intent2);
    		}	    
    	}
	}
	
	/**
	 * Returns to the previous page, InTriageActivity.
	 * @param view
	 */
	public void returnToPrevious(View view){
		Intent intent = new Intent(this, InTriageActivity.class);
		intent.putExtra("intriageKey", patient);
		intent.putExtra("user", user);
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

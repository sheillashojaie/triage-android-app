package csc207b07.example.triage;

import java.util.Date;
import csc207b07.example.data.InTriage;
import csc207b07.example.data.Nurse;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * UpdateSuccessActivity is started when a vital update is recorded 
 * successfully. As such, it simply displays a message the to update was
 * successful.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class UpdateSuccessActivity extends Activity {

	/** The current user of this Triage application. */
	private Nurse user;
	
	/** The patient whose vital update was recorded. */
	private InTriage patient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_success);
		Intent intent = getIntent();
		Date date = (Date) intent.getSerializableExtra("timeKey");
		String cardNum = (String) intent.getSerializableExtra("cardKey");
		user = (Nurse) intent.getSerializableExtra("user");
		patient = user.getInTriagePatients().getPatientByCardNum(cardNum);
		TextView welcomeUser = (TextView) findViewById(R.id.update_success);
		welcomeUser.setText("Vital Signs are added successfully at time:" 
		+ "\n" + date.toString());	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_success, menu);
		return true;
	}

	/**
	 * Returns to the previous page, RecordsActivity.
	 * @param view
	 */
	public void returnToPrevious(View view){
		Intent intent = new Intent(this, RecordsActivity.class);
		intent.putExtra("user", user);
		intent.putExtra("intriageKey", patient);
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


package csc207b07.example.triage;

import csc207b07.example.data.InTriage;
import csc207b07.example.data.Nurse;
import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

/**
 * UpdateErrorActivity is started when a vital update is unsuccessful 
 * (i.e. input fields are left blank). As such, it simply displays a message
 * that the update was unsuccessful.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class UpdateErrorActivity extends Activity {

	/** The current user of this Triage application. */
	private Nurse user;
	
	/** The patient whose vitals were trying to be updated. */
	private InTriage patient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_error);
		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		// Vibrate for 500 milliseconds
		v.vibrate(500);
		Intent intent = getIntent();
		patient = (InTriage) intent.getSerializableExtra("intriageKey");
		user = (Nurse) intent.getSerializableExtra("user");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_error, menu);
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


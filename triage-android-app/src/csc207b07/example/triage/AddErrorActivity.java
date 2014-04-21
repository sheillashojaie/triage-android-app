package csc207b07.example.triage;

import csc207b07.example.data.Nurse;
import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

/**
 * AddErrorActivity is started when a patient is not successfully registered.
 * As such, it simply displays a message that the registration was unsuccessful.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class AddErrorActivity extends Activity {
	
	/** The current user of this Triage application. */
	private Nurse user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_error);
		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		// Vibrate for 500 milliseconds
		v.vibrate(500);
		Intent intent = getIntent();
		user = (Nurse)intent.getSerializableExtra("user");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_error, menu);
		return true;
	}

	/**
	 * Returns to the previous page, AddPatientActivity.
	 * @param view
	 */
	public void returnToPrevious(View view){
		Intent intent = new Intent(this, AddPatientActivity.class);
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


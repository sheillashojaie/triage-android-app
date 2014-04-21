package csc207b07.example.triage;

import csc207b07.example.data.Nurse;
import csc207b07.example.data.User;
import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

/**
 * NotFoundActivity is the page that is started when a patient is not found.
 * As such, it simply displays a not found message.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class NotFoundActivity extends Activity {
	
	/** The current user of this Triage application.*/
	private User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_not_found);
		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		// Vibrate for 500 milliseconds
		v.vibrate(500);
		Intent intent = getIntent();
		user = (User)intent.getSerializableExtra("user");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.not_found, menu);
		return true;
	}
	
	/**
	 * Returns to the main menu- MainMenuActivity if the user is a Nurse, or
	 * MainMenuPhysicianActivity if the user is a Physician.
	 * @param view
	 */
	public void returnToMainMenu(View view){
		if (user instanceof Nurse) {
			Intent intent = new Intent(this, MainMenuActivity.class);
			intent.putExtra("user", user);
			startActivity(intent);
		}
		else {
			Intent intent2 = new Intent(this, MainMenuPhysicianActivity.class);
			intent2.putExtra("user", user);
			startActivity(intent2);
		}
	}

}


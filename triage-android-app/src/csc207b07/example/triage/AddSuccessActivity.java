package csc207b07.example.triage;

import csc207b07.example.data.Nurse;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

/**
 * AddSuccessActivity is started when a patient is successfully registered.
 * As such, it simply displays a message that the registration was successful.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class AddSuccessActivity extends Activity {

	/** The current user of this Triage application. */
	private Nurse user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_success);
		Intent intent = getIntent();
		user = (Nurse)intent.getSerializableExtra("user");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_success, menu);
		return true;
	}
	
	/**
	 * Returns to the main main, MainMenuActivity.
	 * @param view
	 */
	public void returnToMainMenu(View view){
		Intent intent = new Intent(this, MainMenuActivity.class);
		intent.putExtra("user", user);
		startActivity(intent);
	}
	
}


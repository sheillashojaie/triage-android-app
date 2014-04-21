package csc207b07.example.triage;

import csc207b07.example.data.InTriage;
import csc207b07.example.data.Nurse;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * DisplayUrgencyActivity displays a list of the current patients in triage
 * by order of most to least urgent.
 * 
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class DisplayUrgencyActivity extends Activity {

	/** The current user of this Triage application.*/
	private Nurse user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_urgency);
		Intent intent = getIntent();
		user = (Nurse) intent.getSerializableExtra("user");
		TextView displayUr = (TextView) findViewById(R.id.displayUr);
		String names = "";
		for (InTriage x : user.getInTriagePatientsByUrgency()) {
			names += x.getName() + ":" + x.getHealthCardNum() + " " + x.getUrgencyPoint()+ "\n";	        
		}
		displayUr.setText("Display patient(s) from the highest urgency: \n" + names);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_urgency, menu);
		return true;
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

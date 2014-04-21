package csc207b07.example.triage;

import csc207b07.example.data.Nurse;
import csc207b07.example.data.PostTriage;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;


/**
 * DisplayPostTriageActivity displays the name and health card numbers 
 * of all of the patients who were once in triage, and have been admitted
 * to a doctor.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class DisplayPostTriageActivity extends Activity {
	
	/** The current user of this Triage application.*/
	private Nurse user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_post_triage);
		Intent intent = getIntent();
		user = (Nurse) intent.getSerializableExtra("user");
		TextView displayPo = (TextView) findViewById(R.id.displayPo);
		String names = "";
		for (PostTriage x : user.getPostTriagePatientsAsList()) {
			names += x.getName() + ":" + x.getHealthCardNum() + "\n";
	        displayPo.setText(names);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_post_triage, menu);
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


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
 * DisplayInTriageActivity displays a list of the current patients in triage,
 * by order of earliest to latest arrival.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class DisplayIntriageActivity extends Activity {

	/** The current user of this Triage application. */
	private Nurse user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_intriage);
		Intent intent = getIntent();
		user = (Nurse) intent.getSerializableExtra("user");
		TextView displayIn = (TextView) findViewById(R.id.displayIn);
		String names = "";
		for (InTriage x : user.getInTriagePatientsByArrivalTime()) {
			names += x.getName() + ":" +x.getHealthCardNum() + "\n";
	        displayIn.setText(names);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_intriage, menu);
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


package csc207b07.example.triage;

import java.util.Date;

import csc207b07.example.data.Nurse;
import csc207b07.example.data.PostTriage;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * SeenByDoctorActivity is started when a patient is admitted to a doctor.
 * As such, it simply displays a message that the admission was successful.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class SeenByDoctorActivity extends Activity {
	
	/** The current user of this Triage application. */
	private Nurse user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seen_by_doctor);
		Intent intent2 = getIntent();
		PostTriage patient = (PostTriage) intent2.getSerializableExtra("postTriageKey");
		user = (Nurse) intent2.getSerializableExtra("user");
		String name = patient.getName();
		Date date = patient.getAdmittedTime();
		TextView text = (TextView) findViewById(R.id.seen_by_doctor);
		text.setText(name + " is seen by a doctor at time:" + date.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.seen_by_doctor, menu);
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

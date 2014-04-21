package csc207b07.example.triage;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

/**
 * DisplayActivity is started when a login attempt is unsuccessful.
 * As such, it simply displays a message that the attempt was unsuccessful.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class DisplayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		// Vibrate for 500 milliseconds
		v.vibrate(500);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display, menu);
		return true;
	}

	/**
	 * Returns to the previous login page, MainActivity.
	 * @param view
	 */
	public void returnToPrevious(View view){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
}


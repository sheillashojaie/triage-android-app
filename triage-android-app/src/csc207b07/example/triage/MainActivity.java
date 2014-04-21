package csc207b07.example.triage;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

import csc207b07.example.data.InTriage;
import csc207b07.example.data.Nurse;
import csc207b07.example.data.Physician;
import csc207b07.example.data.User;
import csc207b07.example.managers.InTriageManager;
import csc207b07.example.managers.PostTriageManager;
import csc207b07.example.triage.R;

/**
 * MainActivity is the login page of the Triage application where a user
 * can enter a username and password to gain access.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class MainActivity extends Activity {

	/** A Map mapping username to password */
	private Map<String, String> loginDirectory;
	
	/** A Map mapping username to identity type. */
	private Map<String, String> identityDirectory;
	
	/** The filename where data of usernames, passwords, and identity types
	    is contained. */
	public static final String FILENAME = "passwords.txt";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginDirectory = new HashMap<String, String>();
        identityDirectory = new HashMap<String, String>();
        File file = new File(this.getApplicationContext().getFilesDir(), 
        		FILENAME);
        try {
			this.readFromFile(file.getPath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }   
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /**
     * Checks that the username and password entered are valid and if so,
     * determines their identity type (Nurse or Physician) and starts the
     * main menu activity corresponding to the identity type. If the login
     * information is invalid, starts DisplayActivity.
     * @param view
     * @throws IOException
     * @throws ParseException
     */
    public void logIn(View view) throws IOException, ParseException{   
    	Intent intent1 = new Intent(this, DisplayActivity.class);
    	Intent intent2 = new Intent(this, MainMenuActivity.class);
    	Intent intent3 = new Intent(this, MainMenuPhysicianActivity.class);
    	EditText editTextu = (EditText) findViewById(R.id.username_field);
    	String u = editTextu.getText().toString();
    	EditText editTextp = (EditText) findViewById(R.id.password_field);
    	String p = editTextp.getText().toString();
    	
    	// The username is invalid.
    	if (!loginDirectory.containsKey(u)) {
    		startActivity(intent1);
    	}
    	
    	// The username is valid, but the password is invalid.
    	else if (!(loginDirectory.get(u).equals(p))) {
    		startActivity(intent1);
    	}
    	
    	// The username and password are both valid.
    	else {
    		
    		// The identity type is "N" for Nurse
    		if (identityDirectory.get(u).equals("N")) {
    			Nurse user = new Nurse();
        		user.resetInTriagePatients();
        		
        		// Constructs the MultiLinkedList of InTriage Patients.
        		InTriageManager inTriageManager 
        		= new InTriageManager(this.getApplicationContext().getFilesDir(), 
        				"inTriagePatients.txt");
        		List<InTriage> inTriageList = new ArrayList<InTriage>();
        		inTriageList = inTriageManager.getPatients();
        		for (InTriage i : inTriageList) {
        			user.recordInTriagePatient(i);
        		}
        		
        		// Constructs the map of PostTriage Patients, mapping each health
        		// card number to the matching PostTriage Patient.
        		PostTriageManager postTriageManager 
        		= new PostTriageManager(this.getApplicationContext().getFilesDir(),
        				"postTriagePatients.txt");
        		User.postTriagePatients = postTriageManager.getPatients();
        		intent2.putExtra("user", user);
        		startActivity(intent2);  
        		
        	// The identity type is "P" for Physician.
    		}else {
    			Physician phy = new Physician();
    			phy.resetInTriagePatients();
    			
    			// Constructs the MultiLinkedList of InTriage Patients.
    			InTriageManager inTriageManager 
        		= new InTriageManager(this.getApplicationContext().getFilesDir(), 
        				"inTriagePatients.txt");
        		List<InTriage> inTriageList = new ArrayList<InTriage>();
        		inTriageList = inTriageManager.getPatients();
        		for (InTriage i : inTriageList) {
        			phy.recordInTriagePatient(i);
        		}
        		
        		// Constructs the map of PostTriage Patients, mapping each health
        		// card number to the matching PostTriage Patient.
        		PostTriageManager postTriageManager 
        		= new PostTriageManager(this.getApplicationContext().getFilesDir(),
        				"postTriagePatients.txt");
        		User.postTriagePatients = postTriageManager.getPatients();
        		intent3.putExtra("user", phy);
        		startActivity(intent3);
    		}
    		  
    	}
        
    }
    
    /**
     * Reads the file containing login data and enters the extracted
     * information into loginDirector and identityDirectory.
     * @param filePath
     * @throws FileNotFoundException
     */
    public void readFromFile (String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(filePath));     
    	String[] usernameAndPassword;
    	
        while(scanner.hasNextLine()) {
            usernameAndPassword = scanner.nextLine().split(":"); 
            String identity = usernameAndPassword[0];
            String username = usernameAndPassword[1];
            String password = usernameAndPassword[2];           
            loginDirectory.put(username, password);  
            identityDirectory.put(username, identity);
        }
        scanner.close();
    } 
    
}

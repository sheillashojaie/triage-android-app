___________      .__                       
\__    ___/______|__|____     ____   ____  
  |    |  \_  __ \  \__  \   / ___\_/ __ \ 
  |    |   |  | \/  |/ __ \_/ /_/  >  ___/ 
  |____|   |__|  |__(____  /\___  / \___  >
                         \//_____/      \/ 


AN ANDROID APPLICATION

Triage is intended for use by triage nurses and physicians to facilitate 
administration. 

--------------------------
SETUP 
--------------------------

Before running Triage for the first time, you must push the passwords.txt file 
found in PIII onto your Android virtual device using adb.

--------------------------
GENERAL USAGE
--------------------------

Upon loading the program, you will be prompted to log in.

You may do this using the username/password combinations below (or another one 
of the username:password combinations in passwords.txt) and you will be taken 
to the main menu:

NURSE: username: nurse, password: 12345
PHYSICIAN: username: admin, password: 12345

Upon successful login for the first time, the program will create two new files 
for storing data: postTriagePatients.txt and inTriagePatients.txt. If returning 
from a previous session, the program will instead load previously saved data 
from these files, by reconstructing objects using the saved parameters.

Note
In the main menu, depending on if you're a Nurse of a Physician, you can 
perform a number of actions described below:

For NURSES:
Search
Searches for a patient by their health card number.

Add a patient
Takes the user to a new activity that will let them register a new patient.

Display admitted patients
Lists all the patients that have gone through triage and been admitted to a 
doctor.

Display triage patients by Urgency
Lists all the patients who are currently still waiting in triage, ordered by 
their current urgency.

Display triage patients by Arrival Time
Lists all the patients who are currently still waiting in triage, ordered by 
their arrival time.

Log Out
Logs the user out of the app.

For PHYSICIANS:
Search
Searches for a patient by their healh card number.

Log Out
Logs the user out of the app.

A successful search for NURSE users takes you to a new activity where you will 
be able to see personal patient data (name, date of birth, health card number, 
vitals, time arrived/admitted):

If you are a NURSE, you have a few options here:
If the patient is currently in triage, you can update their vital signs or 
admit them to a doctor (NOTE: This removes them from the list of in triage 
patients and add them to a list of post triage patients). When updating vital 
signs for a patient, the program records a new set of vitals for them that will 
be saved to a unique .txt file for each patient the name of which corresponds 
to their health card number (e.g. Alice's health card number is 110456 so her 
vitals file will be called 110456.txt). For both in triage and post, you can 
display vital records and prescriptions for this patient.

If you are a PHYSICIAN, you have a couple limited options:
You can display vital records and prescriptions for this patient, as well as 
prescribe medication for triage who are currently post triage.

Note that Triage autosaves patient records upon each patient registration, 
and vital/prescription update.


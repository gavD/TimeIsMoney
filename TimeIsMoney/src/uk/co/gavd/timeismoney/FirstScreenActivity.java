package uk.co.gavd.timeismoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * First screen of the app - corresponds to screen1.xml
 * 
 * This screen allows the user to select the number of people attending
 * a meeting, and then hit "start"
 * 
 * Once set, this is set, so latecomers are not counted
 * 
 * @author Gavin Davies
 */
public class FirstScreenActivity extends Activity {

	/**
	 * Displays the number of people attending this meeting
	 */
	private TextView textPeopleCount;
	
	/**
	 * The action button - in this case, "start"
	 */
	private Button btnAction;
	
	/**
	 * Sets the number of people attending this meeting
	 */
	private SeekBar seekBar;
	
	/**
	 * The fewest people that can attend a meeting
	 */
	public static final int MIN_PEOPLE = 2;
	
	/**
	 * The most people that can reasonably attend a meeting
	 */
	public static final int MAX_PEOPLE = 13;
	
	/**
	 * Special for "all company" meetings
	 */
	public static final int ALL_STAFF = 50;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        goFullScreen();
    
        setContentView(R.layout.screen1);
        
        btnAction = (Button) findViewById(R.id.btnAction);
        btnAction.setOnClickListener(startListener);
       
        textPeopleCount = (TextView) findViewById(R.id.peopleCount);
        textPeopleCount.setText("" + MIN_PEOPLE);
        
        seekBar = (SeekBar) findViewById(R.id.peopleCountSeek);
        seekBar.setMax(MAX_PEOPLE - MIN_PEOPLE);

        seekBar.setProgress(0);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener); 
    }

	/**
	 * Full screen this app so it looks nice on tablets
	 */
    private void goFullScreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
	}

	/**
	 * Listener for the seek bar so when the seek bar is changed we
	 * change the number of people attending the meeting
	 */
    private OnSeekBarChangeListener seekBarChangeListener = new OnSeekBarChangeListener() {
		
    	@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			
			int numPeople;
			if(seekBar.getProgress() >= seekBar.getMax() - 1) {
				numPeople = ALL_STAFF;
			} else {
				numPeople = seekBar.getProgress() + MIN_PEOPLE;
			}
			
			textPeopleCount.setText("" + numPeople);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) { }

		@Override
		public void onStopTrackingTouch(SeekBar seekBar)  { }
	};
	
	/**
	 * Listener for the "start" button - when clicked, we start the
	 * timer and go to the next screen
	 */
	private OnClickListener startListener = new OnClickListener() {
		public void onClick(View v) {
            Intent nextScreen = new Intent(getApplicationContext(), SecondScreenActivity.class);

            //Sending data to another Activity
            nextScreen.putExtra("people", Integer.parseInt(textPeopleCount.getText().toString()));
            
            startActivity(nextScreen);
		}
	};

}
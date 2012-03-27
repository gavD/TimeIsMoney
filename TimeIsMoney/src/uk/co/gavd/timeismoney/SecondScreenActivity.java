package uk.co.gavd.timeismoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SecondScreenActivity extends Activity {
	private TextView textCost;
	private CostTimer counter;
	private Button btnAction;
	private int people;
	
	private static final int PENCE_PER_HOUR = 115 * 100;
	
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.screen2);
		
        textCost = (TextView) findViewById(R.id.textCost);
		
        Intent i = getIntent();
		// Receiving the Data
		this.people = i.getIntExtra("people", 2);
		
		counter = new CostTimer(this);
    	counter.setPencePerHour(PENCE_PER_HOUR);
    	counter.setPeople(people);
		
		counter.start();
		
        btnAction = (Button) findViewById(R.id.btnAction);
        btnAction.setOnClickListener(new OnClickListener() {
    		public void onClick(View v) {
    			counter.cancel();
    			
                Intent nextScreen = new Intent(getApplicationContext(), ThirdScreenActivity.class);

                //Sending data to another Activity
                nextScreen.putExtra("cost", textCost.getText().toString());
                nextScreen.putExtra("people", people);
                nextScreen.putExtra("seconds", counter.getSeconds());
                
                startActivity(nextScreen);
    		}
    	});
    }
   
    public void reportCost(double costInPounds) {
    	textCost.setText("£" + Math.round(costInPounds));
    }
}

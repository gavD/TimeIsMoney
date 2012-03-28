package uk.co.gavd.timeismoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ThirdScreenActivity extends Activity {
	
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.screen3);
		
        Intent i = getIntent();
		// Receiving the Data
		String cost = i.getStringExtra("cost");
		int people = i.getIntExtra("people", 0);
		int seconds = i.getIntExtra("seconds", 0);
		
		TextView textInfo = (TextView) findViewById(R.id.textInfo);
		textInfo.setText(people + " people at £" + (people * Constants.POUNDS_PER_HOUR)
			+ " p/h attended.\nIt cost " + Math.round(seconds / 60) + " company minutes.");
		Log.println(Log.INFO, "BOX", "cost : " + cost);
		
		Button btnAction = (Button) findViewById(R.id.btnAction);
        btnAction.setOnClickListener(new OnClickListener() {
    		public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), FirstScreenActivity.class);
                startActivity(nextScreen);
    		}
    	});
    }
}
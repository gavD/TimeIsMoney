package uk.co.gavd.timeismoney;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.Window;

/**
 * A full screened activity
 * 
 * Provided as a base class for convenience for the time being but might work better
 * as a mixin if this grows 
 * 
 * @author Gavin Davies http://www.gavd.co.uk/
 */
public class FullScreenActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    }
}

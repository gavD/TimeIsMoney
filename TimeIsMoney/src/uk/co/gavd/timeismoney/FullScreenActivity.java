package uk.co.gavd.timeismoney;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.Window;

public class FullScreenActivity extends Activity {

    /**
     * Full screen this app so it looks nice on tablets
     */
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    }
}

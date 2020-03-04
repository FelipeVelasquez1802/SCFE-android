package com.diegoasencio.scfe.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.diegoasencio.scfe.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        int SPLASH_DISPLAY_LENGTH = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(MainActivity.this, PrincipalMenuActivity.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }
}

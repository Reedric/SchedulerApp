package com.example.schedulerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class PopUpWindow extends Activity {

    Button btn_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_window);

        // Set the function of the close button to close the window
        btn_close = (Button) findViewById(R.id.cancel_button);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Get the metrics for our current display
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        // Get the width and height from the display metrics
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        // Set width and height of popup window
        getWindow().setLayout((int)(width*.8), (int)(height*.5));

        // Get and edit attributes so that positioning of popup is better
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        // Set attributes of popup window
        getWindow().setAttributes(params);
    }
}
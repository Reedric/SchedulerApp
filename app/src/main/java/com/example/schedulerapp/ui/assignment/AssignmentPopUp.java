package com.example.schedulerapp.ui.assignment;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.schedulerapp.R;

public class AssignmentPopUp extends Activity {

    Button btn_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_assignment);

        // Set function of close button to close window
        btn_close = findViewById(R.id.assignmentCancelButton);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Get the metric for our current display
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        // Get the width and height from display metrics
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        // Set width and height of popup window
        getWindow().setLayout((int)(width*.8),(int)(height*.7));

        // Get and edit attributes so that positioning of popup is better
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        // Set attributes of popup window
        getWindow().setAttributes(params);
    }
}

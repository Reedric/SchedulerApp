package com.example.schedulerapp.ui.classes;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.schedulerapp.R;

public class ClassPopUp extends Activity {

    Button btn_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_class);

        // Set the function of the close button to close the window
        btn_close = (Button) findViewById(R.id.cancel_button);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Display start times in drop down menu
        Spinner classStartTime = (Spinner) findViewById(R.id.spinnerClassStartTime);
        String[] startTimes = {"8:00am", "8:30am", "9:00am", "9:30am", "10:00am", "10:30am", "11:00am", "11:30am", "12:00pm", "12:30pm", "1:00pm", "1:30pm", "2:00pm", "2:30pm", "3:00pm", "3:30pm", "4:00pm", "4:30pm", "5:00pm"};
        ArrayAdapter<String> startAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, startTimes);
        startAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classStartTime.setAdapter(startAdapter);

        // Display end times in drop down menu
        Spinner classEndTime = (Spinner) findViewById(R.id.spinnerClassEndTime);
        String[] endTimes = {"9:00am", "9:30am", "10:00am", "10:30am", "11:00am", "11:30am", "12:00pm", "12:30pm", "1:00pm", "1:30pm", "2:00pm", "2:30pm", "3:00pm", "3:30pm", "4:00pm", "4:30pm", "5:00pm", "5:30pm", "6:00pm"};
        ArrayAdapter<String> endAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, endTimes);
        endAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classEndTime.setAdapter(endAdapter);

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
package com.example.schedulerapp.ui.classes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.schedulerapp.R;

import java.io.Serializable;

public class EditClassPopUp extends Activity implements ViewModelStoreOwner {
    Button cancel_edit_btn, confirm_edit_btn, delete_edit_btn;
    Spinner editClassStartTime, editClassEndTime;
    EditText editClassName, editProfessorName;
    private ClassViewModel classViewModel;
    private ClassInfo selectedClassInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_edit_class);

        cancel_edit_btn = (Button) findViewById(R.id.cancel_button2);
        confirm_edit_btn = (Button) findViewById(R.id.confirm_button);
        delete_edit_btn = (Button) findViewById(R.id.delete_class_button);
        editClassStartTime = (Spinner) findViewById(R.id.spinnerClassEditStartTime);
        editClassEndTime = (Spinner) findViewById(R.id.spinnerClassEditEndTime);
        editClassName = (EditText) findViewById(R.id.editTextEditClassName);
        editProfessorName = (EditText) findViewById(R.id.editTextEditProfessorName);

        // Retrieve data from Intent
        Intent intent = getIntent();
        if (intent != null) {
            Serializable serializable = intent.getSerializableExtra("SEL_EDITED_CLASS_INFO");
            if (serializable instanceof ClassInfo) {
                selectedClassInfo = (ClassInfo) serializable;

                // Populate the UI with the retrieved data
                editClassName.setText(selectedClassInfo.getName());

                // Display start times in drop down menu
                String[] startTimes = {"8:00am", "8:30am", "9:00am", "9:30am", "10:00am", "10:30am", "11:00am", "11:30am", "12:00pm", "12:30pm", "1:00pm", "1:30pm", "2:00pm", "2:30pm", "3:00pm", "3:30pm", "4:00pm", "4:30pm", "5:00pm"};
                ArrayAdapter<String> startAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, startTimes);
                startAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                editClassStartTime.setAdapter(startAdapter);

                // Display end times in drop down menu
                String[] endTimes = {"9:00am", "9:30am", "10:00am", "10:30am", "11:00am", "11:30am", "12:00pm", "12:30pm", "1:00pm", "1:30pm", "2:00pm", "2:30pm", "3:00pm", "3:30pm", "4:00pm", "4:30pm", "5:00pm", "5:30pm", "6:00pm"};
                ArrayAdapter<String> endAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, endTimes);
                endAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                editClassEndTime.setAdapter(endAdapter);

                // Set the selected start time in the spinner
                int startTimeIndex = startAdapter.getPosition(selectedClassInfo.getStartTime());
                if (startTimeIndex != -1) {
                    editClassStartTime.setSelection(startTimeIndex);
                }
                // Set the selected end time in the spinner
                int endTimeIndex = endAdapter.getPosition(selectedClassInfo.getEndTime());
                if (endTimeIndex != -1) {
                    editClassEndTime.setSelection(endTimeIndex);
                }
                editProfessorName.setText(selectedClassInfo.getProfessorName());

                // Set hints based on selected class info
                editClassName.setHint(selectedClassInfo.getName());
                editProfessorName.setHint(selectedClassInfo.getProfessorName());
            }
        }

        cancel_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        confirm_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve values from EditText and Spinners
                String name = editClassName.getText().toString();
                String startTime = editClassStartTime.getSelectedItem().toString();
                String endTime = editClassEndTime.getSelectedItem().toString();
                String profName = editProfessorName.getText().toString();

                // Create a ClassInfo object
                ClassInfo editedClassInfo = new ClassInfo(name, startTime, endTime, profName);

                // Send intent back to ClassFragment with edited data
                Intent resultIntent = new Intent();
                resultIntent.putExtra("FROM_EDIT_CLASS_POPUP", true);
                resultIntent.putExtra("CONFIRMED_EDIT", true);
                resultIntent.putExtra("ORIGINAL_CLASS_INFO", selectedClassInfo);
                resultIntent.putExtra("EDITED_CLASS_INFO", editedClassInfo);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        delete_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedClassInfo != null) {
                    Intent delIntent = new Intent();
                    delIntent.putExtra("FROM_EDIT_CLASS_POPUP", true);
                    delIntent.putExtra("ORIGINAL_CLASS_INFO", selectedClassInfo);
                    setResult(Activity.RESULT_OK, delIntent);

                    Toast.makeText(EditClassPopUp.this, "Class deleted", Toast.LENGTH_SHORT).show();

                    finish();
                }
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

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return new ViewModelStore();
    }
}
package com.example.schedulerapp.ui.assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.example.schedulerapp.R;

public class AssignmentPopUp extends Activity {

    Button btnAdd, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_assignment);

        btnAdd = findViewById(R.id.assignmentAddButton);
        btnCancel = findViewById(R.id.assignmentCancelButton);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameEditText = findViewById(R.id.editAssignmentName);
                EditText courseNameEditText = findViewById(R.id.editCourseName);
                DatePicker assignmentDatePicker = findViewById(R.id.assignmentDatePicker);

                String name = nameEditText.getText().toString();
                String courseName = courseNameEditText.getText().toString();

                // Retrieve the date from DatePicker
                int day = assignmentDatePicker.getDayOfMonth();
                int month = assignmentDatePicker.getMonth();
                int year = assignmentDatePicker.getYear();

                // Format the date
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
                String dueDate = dateFormat.format(calendar.getTime());

                Intent data = new Intent();
                data.putExtra("assignmentName", name);
                data.putExtra("courseName", courseName);
                data.putExtra("dueDate", dueDate);

                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the pop-up
                finish();
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .7));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);
    }
}


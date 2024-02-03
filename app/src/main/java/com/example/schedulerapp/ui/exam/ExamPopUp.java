package com.example.schedulerapp.ui.exam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.schedulerapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ExamPopUp extends Activity {
    Spinner examTimeSpinner;
    EditText examNameEditText, examLocationEditText;
    DatePicker examDatePicker;
    Button addButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_exam);

        examNameEditText = findViewById(R.id.editExamName);
        examLocationEditText = findViewById(R.id.editLocation);
        examDatePicker = findViewById(R.id.ExamDatePicker);
        examTimeSpinner = findViewById(R.id.spinnerExamTime);
        addButton = findViewById(R.id.ExamAddButton);
        cancelButton = findViewById(R.id.ExamCancelButton);

        String[] examTimes = {"8:00am", "8:30am", "9:00am", "9:30am", "10:00am", "10:30am", "11:00am", "11:30am", "12:00pm", "12:30pm", "1:00pm", "1:30pm", "2:00pm", "2:30pm", "3:00pm", "3:30pm", "4:00pm", "4:30pm", "5:00pm"};
        ArrayAdapter<String> startAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, examTimes);
        startAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        examTimeSpinner.setAdapter(startAdapter);

        addButton.setOnClickListener(v -> {
            String examName = examNameEditText.getText().toString();
            String examLocation = examLocationEditText.getText().toString();
            String examTime = examTimeSpinner.getSelectedItem().toString();
            int day = examDatePicker.getDayOfMonth();
            int month = examDatePicker.getMonth();
            int year = examDatePicker.getYear();

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            String examDate = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(calendar.getTime());

            Intent resultIntent = new Intent();
            resultIntent.putExtra("examName", examName);
            resultIntent.putExtra("examLocation", examLocation);
            resultIntent.putExtra("examDate", examDate);
            resultIntent.putExtra("examTime", examTime);

            setResult(Activity.RESULT_OK, resultIntent);

            finish();
        });

        cancelButton.setOnClickListener(v -> {
            finish();
        });
    }
}

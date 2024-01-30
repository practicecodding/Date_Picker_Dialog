package com.hamidul.datepickerdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.datepicker.MaterialStyledDatePickerDialog;

import java.lang.reflect.Type;
import java.time.Year;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    EditText editText;
    Calendar calendar;

    private Long selectedDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        calendar = Calendar.getInstance();

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog();
            }
        });

    }

    private void dateDialog (){

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(year,month,dayOfMonth);

                String myFormat="dd/MM/yyyy";
                SimpleDateFormat dateFormat= null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    dateFormat = new SimpleDateFormat(myFormat, Locale.US);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    editText.setText(dateFormat.format(calendar.getTime()));
                }


//                editText.setText(year+" -"+m+" -"+dayOfMonth);
                editText.setTypeface(editText.getTypeface(), Typeface.BOLD);
            }
        }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();

    }

    private void mDateDialog(){

        // Create a MaterialDatePicker.Builder
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select a date");

        // Set the initial selection if a date has been previously selected
        if (selectedDate != null) {
            builder.setSelection(selectedDate);
        }

        // Create a MaterialDatePicker
        MaterialDatePicker<Long> datePicker = builder.build();

        // Set a listener for when the positive button is clicked
        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                // Store the selected date
                selectedDate = selection;

                editText.setText(""+datePicker.getHeaderText());

                // Do something with the selected date
                // For example, convert it to a readable format
                // using SimpleDateFormat or any other date/time library
            }
        });

        // Show the MaterialDatePicker when a button or any other UI element is clicked
        // For example, you can show it on a button click
            datePicker.show(getSupportFragmentManager(), "Date Picker");

    }





}